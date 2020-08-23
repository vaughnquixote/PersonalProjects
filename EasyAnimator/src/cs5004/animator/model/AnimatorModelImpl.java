package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * This is a class used to represent the model for a basic animator. The animator has the ability to
 * add shapes (Rectangles or Ovals) to the animation as well as three different actions which
 * pertain to shapes: a movement, a change in color, and a change in size. The shapes and actions
 * are stored in the model using a Hashtable and an ArrayList respectively.
 */
public class AnimatorModelImpl implements AnimatorModel {

  private LinkedHashMap<String, Shape> shapes;
  private ArrayList<Action> actions;
  private int[] bounds;

  /**
   * The constructor for the class. It initializes the data structures used to store the shapes and
   * the actions present in the animation: a Hashtable with strings (the names) as keys for the
   * shapes; an ArrayList for the actions present in the animation; and a integer array that holds
   * the xBound, yBound, width, and height respectively.
   */
  public AnimatorModelImpl()
          throws IllegalArgumentException {

    this.shapes = new LinkedHashMap<String, Shape>();
    this.actions = new ArrayList<Action>();
    // dont know about initializing these values. are we going to check for shapes being initialized
    // within the bounds or no?
    this.bounds = new int[]{1, 1, 0, 0};
  }

  /**
   * @param name
   * @throws IllegalArgumentException
   */
  @Override
  public void addRectangle(String name) throws IllegalArgumentException {
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is already a shape with that name.");
    }
    Shape newRec = new Rectangle(name);
    shapes.put(name, newRec);
  }

  /**
   * @param name
   * @throws IllegalArgumentException
   */
  @Override
  public void addOval(String name) throws IllegalArgumentException {
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is already a shape with that name.");
    }
    Shape newOval = new Oval(name);
    shapes.put(name, newOval);
  }

  @Override
  public void addAction(String name, int startTime, int endTime, Point2D.Double startLoc,
                        Point2D.Double endLoc,
                        int startWidth, int endWidth, int startHeight, int endHeight, Color startColor,
                        Color endColor)
          throws IllegalArgumentException {

    Shape s = this.shapes.get(name);

    Action aToAdd = new ActionImpl(s, startTime, endTime, startLoc, endLoc, startWidth, endWidth,
            startHeight,
            endHeight, startColor, endColor);

    if (!isValidTime(startTime, endTime)) {
      throw new IllegalArgumentException("Invalid start and end times.");
    }

    if (!(isValidAction(aToAdd))) {
      throw new IllegalArgumentException("Action conflicts with another action");
    }

    s.addAction(aToAdd);
    this.actions.add(aToAdd);
  }

  /**
   * This is a method used to add a rectangle to the animation. The parameters are all the essential
   * attributes of a rectangle including the times at which it appears and disappears, its
   * dimensions, location of the bottom left corner, the layer on which it appears as well as the
   * name to be used for later reference. The method ensures that the name is not already in use,
   * that the location has positive coordinates, the height and width are positive, and that the
   * layer is not already in use.
   * <p></p>
   *
   * @param name        the name of the rectangle to be referred to later and to be used as the key
   *                    for the hashtable of shapes stored in the model
   * @param color       the color of the rectangle at the time of creation
   * @param location    the initial location of the bottom left corner of the rectangle
   * @param height      the initial height of the rectangle
   * @param width       the initial width of the rectangle
   * @param createTime  the time at which the rectangle appears
   * @param destroyTime the time at which the rectangle disappears
   * @throws IllegalArgumentException if passed illegal arguments.
   */
  @Override
  public void addRectangle(String name, java.awt.Color color, Point2D.Double location, double width,
                           double height, int createTime, int destroyTime)
          throws IllegalArgumentException {
    // check that the specified name for the shape is not already in use
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is already a shape with that name.");
    }
    // make sure that the times for appearance and disappearance a are positive and are not
    // contradictory
    if (!(this.isValidTime(createTime, destroyTime))) {
      throw new IllegalArgumentException("Invalid time parameters");
    }
    // instantiate the new rectangle and add it to the model
    Shape newRec = new Rectangle(name, location, color, width, height, createTime, destroyTime);

    shapes.put(name, newRec);
  }

  /**
   * This is a method used to add an oval to the animation. The parameters are all the essential
   * attributes of an oval including the times at which it appears and disappears, the x and y
   * radius of the oval, the location of the center, the layer on which it appears, and a name to be
   * used for later reference to the oval. The name will become the key to store the shape in a
   * hashtable in the model.  The method ensures that the name is not already in use, that the
   * location has positive coordinates, the x and y radius are positive, and that the layer is not
   * already in use.
   * <p></p>
   *
   * @param name        the name of the oval to be referred to later and to be used as the key for
   *                    the hashtable of shapes stored in the model
   * @param color       the color of the oval at the time of creation
   * @param location    the initial location of the center of the oval
   * @param xRadius     the x radius of the oval
   * @param yRadius     the y radius of the oval
   * @param createTime  the time at which the oval appears
   * @param destroyTime the time at which the oval disappears
   * @throws IllegalArgumentException if passed invalid arguments
   */

  @Override
  public void addOval(String name, Color color, Point2D.Double location, double xRadius,
                      double yRadius, int createTime, int destroyTime) {
    // check that the specified name for the shape is not already in use
    if (this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is already a shape with that name.");
    }
    // make sure that the times for appearance and disappearance a are positive and are not
    // contradictory
    if (!(this.isValidTime(createTime, destroyTime))) {
      throw new IllegalArgumentException("Invalid time parameters");
    }

    // instantiate the new oval and store it in the model
    Shape newOval = new Oval(name, location, color, xRadius, yRadius, createTime, destroyTime);

    shapes.put(name, newOval);
  }


  /**
   * This is a method which will remove a shape with the given name from the model.
   *
   * @param name the name of the shape to be removed
   * @throws IllegalArgumentException if the shape doesn't exist.
   */

  @Override
  public void destroyShape(String name) throws IllegalArgumentException {
    // the case that the shape is not present
    if (shapes.get(name) == null) {
      throw new IllegalArgumentException("Shape doesn't exist!");
    }
    // otherwise remove the shape from the Hashtable
    shapes.remove(name);
  }

  /**
   * This is a method to validate the coherence of the time arguments that might be passed to
   * another method. It checks that they are positive, end is non-zero and that end comes after
   * start.
   *
   * @param start the starting time
   * @param end   the end time
   * @return a boolean indicating that the the time is valid or not
   */
  private boolean isValidTime(int start, int end) {
    if (start < 0 || end < 0) {
      return false;
    } else {
      return end >= start;
    }
  }

  /**
   * This is a method to check whether or not a given action conflicts with actions currently stored
   * in the class. An action is invalid if there is already an action of that type operating on the
   * given shape at any point during its time interval. The method iterates through all actions
   * present checks to see if they operate on the same shape and if so checks that they are not
   * overlapping and of the same type
   * <p></p>
   *
   * @param a the action being examined
   * @return a boolean indicating whether or not the action is valid
   */
  private boolean isValidAction(Action a) {
    boolean valid = true;

    for (Action action : actions) {
      if (!(action.checkCompatibility(a))) {
        valid = false;
        break;
      }
    }
    return valid;
  }

  /**
   * This is a method to return a string representation of the animation. It returns a string of
   * this form (with arbitrary numbers of shapes and actions represented).
   * <p></p>
   * Shapes: Name: R Type: rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color:
   * (1.0,0.0,0.0) Appears at t=1 Disappears at t=100
   * <p></p>
   * Name: C Type: oval Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
   * Appears at t=6 Disappears at t=100
   * <p></p>
   * Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50 Shape C moves from
   * (500.0,100.0) to (500.0,400.0) from t=20 to t=70 Shape C changes color from (0.0,0.0,1.0) to
   * (0.0,1.0,0.0) from t=50 to t=80 Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0,
   * Height: 100.0 from t=51 to t=70 Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to
   * t=100
   *
   * @return a string representation of the animation
   */

  @Override
  public String toString() {

    StringBuilder strRep = new StringBuilder();
    Set<String> keys = this.shapes.keySet();
    for (String key : keys) {
      strRep.append(this.shapes.get(key).toString());
    }
    strRep.append("\n");
    for (Action a : this.actions) {
      strRep.append(a.toString());
    }
    return strRep.toString();
  }

  /**
   * Get the bounds of the model.
   *
   * @return the bounds of the model
   */

  public int[] getBounds() {
    return bounds;
  }

  /**
   * Remove an action from the action list.
   *
   * @param shape      the shape that the action operates on
   * @param startTime  the starting time of the action
   * @param actionType the type of the action in string form
   */

  @Override
  public void removeAction(String shape, int startTime, ActionType actionType) {

    actions = actions.stream().filter(x -> !(x.getStartTime() == startTime &&
            x.isType(actionType) &&
            x.getShape().equals(this.shapes.get(shape)))).
            collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public void setBounds(int xBound, int yBound, int width, int height)
          throws IllegalArgumentException {
    bounds[0] = xBound;
    bounds[1] = yBound;
    bounds[2] = width;
    bounds[3] = height;
  }

  @Override
  public ArrayList<Shape> getShapeCopies() {

    ArrayList<Shape> list = new ArrayList<Shape>();
    Set<String> keys = this.shapes.keySet();
    for (String key : keys) {
      list.add(this.shapes.get(key).copy());
    }
    return list;
  }

  @Override
  public Shape getShape(String name) {
    return this.shapes.get(name);
  }

}





