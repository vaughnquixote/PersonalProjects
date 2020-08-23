package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Interface for the Animator's model. Specifies the necessary methods for adding shapes and actions
 * on the shapes to the animation.
 */
public interface AnimatorModel {

  /**
   * This is a method used to add a rectangle to the animation. The parameters are all the essential
   * attributes of a rectangle including the times at which it appears and disappears, its
   * dimensions, location of the bottom left corner, the layer on which it appears as well as the
   * name to be used for later reference. The method ensures that the name is not already in use,
   * that the location has positive coordinates, the height and width are positive, and that the
   * layer is not already in use.
   *
   * @param name        the name of the rectangle to be referred to later and to be used as the key
   *                    for the hashtable of shapes stored in the model
   * @param color       the color of the rectangle at the time of creation
   * @param location    the initial location of the bottom left corner of the rectangle
   * @param height      the initial height of the rectangle
   * @param width       the initial width of the rectangle
   * @param createTime  the time at which the rectangle appears
   * @param destroyTime the time at which the rectangle disappears
   * @throws IllegalArgumentException if passed invalid arguments
   */
  void addRectangle(String name, Color color, Point2D.Double location, double height, double width,
                    int createTime, int destroyTime)
          throws IllegalArgumentException;

  /**
   * @param name
   */
  void addRectangle(String name);

  /**
   * This is a method used to add an oval to the animation. The parameters are all the essential
   * attributes of an oval including the times at which it appears and disappears, the x and y
   * radius of the oval, the location of the center, the layer on which it appears, and a name to be
   * used for later reference to the oval. The name will become the key to store the shape in a
   * hashtable in the model.  The method ensures that the name is not already in use, that the
   * location has positive coordinates, the x and y radius are positive, and that the layer is not
   * already in use.
   *
   * @param name        the name of the oval to be referred to later and to be used as the key for
   *                    the hashtable of shapes stored in the model
   * @param color       the color of the oval at the time of creation.
   * @param location    the initial location of the center of the oval.
   * @param xRadius     the x radius of the oval.
   * @param yRadius     the y radius of the oval.
   * @param createTime  the time at which the oval appears.
   * @param destroyTime the time at which the oval disappears.
   * @throws IllegalArgumentException if passed invalid arguments.
   */
  void addOval(String name, Color color, Point2D.Double location, double xRadius, double yRadius,
               int createTime, int destroyTime) throws IllegalArgumentException;

  /**
   * @param name
   */
  void addOval(String name);

  /**
   * This is a method to add a movement to the model. It takes the name of the  shape to be moved,
   * the times at which the movement begins and ends, the starting and ending locations of the
   * shape. The locations must have positive coordinates, the starting time must be before the end
   * time and both must be positive. Additionally, the shape must exist within the model. An
   * exception is thrown if the shape does not exist the start and end times are not valid for the
   * given shape etc. Cannot occur concurrently with another movement on the same shape. The method
   * functions by validating the parameters and then creating and storing an action with the given
   * specifications in the model.
   *
   * @param name      the name of the shape to be moved
   * @param startTime the time at which the movement will begin
   * @param endTime   the time at which the movement will end
   * @param startLoc  the starting location of the shape for the movement
   * @param endLoc    the ending location of the shape for the movement
   * @throws IllegalArgumentException if passed invalid arguments
   */

  void addAction(String name, int startTime, int endTime, Point2D.Double startLoc,
                 Point2D.Double endLoc,
                 int startWidth, int endWidth, int startHeight, int endHeight, Color startColor,
                 Color endColor)
          throws IllegalArgumentException;

  /**
   * This is a method which will remove a shape with the given name from the model.
   *
   * @param name the name of the shape to be removed
   * @throws IllegalArgumentException if passed invalid arguments
   */
  void destroyShape(String name) throws IllegalArgumentException;


  /**
   * This is a method to return a string representation of the animation. It returns a string of
   * this form (with arbitrary numbers of shapes and actions represented). Shapes: Name: R Type:
   * rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0) Appears
   * at t=1 Disappears at t=100
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
  String toString();

  /**
   * This is a method to remove an Action from the animation. It removes the action on the given
   * shape with the given starting time and the given type represented by a String. The action type
   * must be the same as "resize" "recolor" or "move" not case sensitive.
   * <p></p>
   *
   * @param shape      the shape that the action operates on
   * @param startTime  the starting time of the action
   * @param actionType the type of the action in string form
   */
  void removeAction(String shape, int startTime, ActionType actionType);

  /**
   * Set the bounds of the model.
   *
   * @param xBound the xBound of the model, an int.
   * @param yBound the yBound of the model, an int.
   * @param width  the width of the model, an int.
   * @param height the height of the model, an int.
   * @throws IllegalArgumentException
   */

  void setBounds(int xBound, int yBound, int width, int height) throws IllegalArgumentException;

  /**
   * This is a method which returns the bounds for the animation. These are stored in an integer
   * array. the first value in the array is the x location of the frame, the second the y value of
   * the location
   *
   * @return an integer array with
   */
  int[] getBounds();

  /**
   * This is a method to create and return a list of copies of the shapes that exist within the
   * model. It does a deep copy of each shape and all its associated actions. It iterates through
   * all of the shapes and the associated actions
   *
   * @return an ArrayList of all the shapes in the animation.
   */
  ArrayList<Shape> getShapeCopies();

  /**
   * This is a method which will retrieve and return the shape with the given name from the model.
   * It throws an exception if the shape doesnt exist.
   *
   * @param name the name of the shape to be retrieved
   * @return the desired shape
   */
  Shape getShape(String name);
}
