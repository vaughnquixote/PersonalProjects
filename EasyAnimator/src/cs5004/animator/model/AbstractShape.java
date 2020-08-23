package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Color;

/**
 * This class represents an Abstract Shape. An abstract shape implements some of the methods
 * specified in the Shape interface that are identical amongst all types of shapes.
 */

public abstract class AbstractShape implements Shape {

  protected String name;
  protected Color color;
  protected Point2D.Double location;
  protected double xWidth;
  protected double yHeight;
  protected int createTime;
  protected int destroyTime;
  protected ArrayList<Action> actions;

  /**
   * Construct a shape with the given parameters.
   *
   * @param name        the name of the shape, a String.
   * @param location    the location of the shape, a Point2D.
   * @param color       the color of the shape, a Color.
   * @param createTime  the creation time of the shape, an int.
   * @param destroyTime the destruction time of the shape, an int.
   * @throws IllegalArgumentException if the starting location is invalid (no part of the shape on
   *                                  the screen).
   */

  public AbstractShape(String name, Point2D.Double location, Color color, double xWidth,
                       double yHeight, int createTime,
                       int destroyTime) throws IllegalArgumentException {
    // validate certain shape attributes including the shapes times appearance and disappearance
    if (!(createTime >= 0 && destroyTime >= 0 && createTime <= destroyTime)) {
      throw new IllegalArgumentException("Create time and destroy time must be > 0! AND destroy"
              + "time must be greater than create time!");
    } else if (xWidth <= 0 || yHeight <= 0) {
      throw new IllegalArgumentException("Length and width must be greater than 0!");
    }

    this.name = name;
    this.location = location;
    this.color = color;
    this.xWidth = xWidth;
    this.yHeight = yHeight;
    this.createTime = createTime;
    this.destroyTime = destroyTime;
    this.actions = new ArrayList<Action>();
  }

  /**
   * This is a single parameter constructor for the shape. It allows shapes to be instantiated with
   * a name alone. This is useful for contexts where the behavior of the shape is not defined at the
   * outset, specifically when it is being read from a file. The attributes are then determined and
   * set as the actions are read into the model.
   *
   * @param name the name of the shape
   */
  public AbstractShape(String name) {
    this.createTime = Integer.MAX_VALUE;
    this.destroyTime = Integer.MIN_VALUE;
    this.xWidth = 1;
    this.yHeight = 1;
    this.location = new Point2D.Double(0, 0);
    this.actions = new ArrayList<Action>();
    this.color = new Color(0, 0, 0);
    this.name = name;
  }

  /**
   * A method to modify the location of a shape by moving it.
   *
   * @param newLoc the new location of the shape, a Point2D.Double.
   */

  @Override
  public void move(Point2D.Double newLoc) {
    this.location = newLoc;
  }

  /**
   * Re-color a shape. This will change the color of the shape to a new color.
   *
   * @param c the new color of the shape, a Color.
   * @throws IllegalArgumentException if the new color == existing color.
   */

  @Override
  public void reColor(Color c) throws IllegalArgumentException {

    if (c == this.color) {
      throw new IllegalArgumentException("That is already the color of the shape!");
    }

    this.color = c;
  }

  /**
   * Get the name of the shape.
   *
   * @return the name of the shape, a String.
   */

  @Override
  public String getName() {
    return name;
  }

  /**
   * Get the color of the shape.
   *
   * @return the color of the shape, a Color.
   */

  @Override
  public Color getColor() {
    return color;
  }

  /**
   * This is a method to return the creation time for the shape. The time at which the shape appears
   * in the animation.
   *
   * @return the value of the appearance time for the shape.
   */
  @Override
  public int getCreationTime() {
    return createTime;
  }

  /**
   * This is a method to return the destruction time for the shape. The time at which the shape
   * disappears in the animation.
   *
   * @return the value of the disappearance time for the shape
   */
  @Override
  public int getDestructionTime() {
    return destroyTime;
  }

  /**
   * This is a method that validates that the times for an action to be created conform to the
   * appearance and disappearance of the shape. They must be between the creation and destruction of
   * the shape.
   *
   * @param startTime the starting time for the action
   * @param endTime   the ending time for the action
   * @return a boolean indicating if the given start time and end time are valid for the shape
   */
  @Override
  public boolean validateActionTimes(int startTime, int endTime) {
    return this.createTime <= startTime && endTime <= this.destroyTime;
  }

  /**
   * This method adds an action to the shape. The action should pertain to and be linked to the
   * shape. It stores the action in a data structure internal to the shape.
   *
   * @param a the action that will be added
   */
  @Override
  public void addAction(Action a) {
    actions.add(a);
  }

  /**
   * This is a method which returns all of the actions relevant to the shape that take place during
   * the animation.
   *
   * @return an ArrayList containing all of the actions which happen on the shape over the course of
   * the animation.
   */
  @Override
  public ArrayList<Action> getActions() {
    return this.actions;
  }

  /**
   * A method to return the width of the shape. It is the size of the shape in the x dimension.
   *
   * @return the width of the shape
   */
  @Override
  public double getWidth() {
    return this.xWidth;
  }

  /**
   * A method to return the height of the shape. It is the size of the shape in the y dimension.
   *
   * @return the height of the shape.
   */
  @Override
  public double getHeight() {
    return this.yHeight;
  }

  /**
   * A method to return the current location of the shape. It returns a Point2D.Double which
   * represents the location of the shape on the screen.
   *
   * @return the coordinates of the shape on the screen
   */
  @Override
  public Point2D.Double getLocation() {
    return location;
  }

  /**
   * This is a method which will set the starting attributes of the shape. It is meant to be used as
   * actions are being added to the shape. If the action precedes any of the other actions which
   * have been added then the initial state of the shape changes to accommodate that.
   *
   * @param location    the new starting location of the shape
   * @param color       the new starting color for the shape
   * @param xWidth      new starting width
   * @param yHeight     new starting height
   * @param createTime  new creation time
   * @param destroyTime new destroy time
   */
  @Override
  public void setStartingAttributes(Point2D.Double location, Color color, double xWidth,
                                    double yHeight, int createTime, int destroyTime) {
    this.location = location;
    this.color = color;
    this.xWidth = xWidth;
    this.yHeight = yHeight;
    this.createTime = createTime;
    if (destroyTime > this.destroyTime) {
      this.destroyTime = destroyTime;
    }
  }

  /**
   * Setting the destroy time for the shape. this needs to be done if a new action is added which
   * finishes after all current actions have run their course.
   *
   * @param destroyTime
   */
  @Override
  public void setDestroyTime(int destroyTime) {
    this.destroyTime = destroyTime;
  }

  /**
   * This is a method that will change the shapes attributes to the attributes specified by the
   * actions for the current time passed into the method as a parameter. The action could specify a
   * change in color, change in size or change in location. Since the actions store starting and
   * ending states of the shape linear interpolation is used to get the intermediate values of the
   * attributes for a given time.
   *
   * @param time the current time in the animation
   */
  @Override
  public void mutateShape(int time) {
    // for every current action
    for (Action a : getCurrentActions(time)) {
      // interpolate the action/shape
      this.interpolate(a, time);
    }
  }

  /**
   * This is a method which takes an action and a time as parameters and updates the shapes
   * attributes according to the specification of the action. It uses linear interpolation along
   * with the starting/ending states specified by the action to compute the new values of the
   * attributes for a given time.
   *
   * @param a    the action which is taking place on the shape
   * @param time the current time in the animation
   */
  @Override
  public void interpolate(Action a, int time) {
    if (a.isType(ActionType.RECOLOR_B) || a.isType(ActionType.RECOLOR_G) || a
            .isType(ActionType.RECOLOR_B)) {
      this.color = a.interpolateColor(time);
    }
    if (a.isType(ActionType.MOVE_X) || a.isType(ActionType.MOVE_Y) || a.getTypes().isEmpty()) {
      this.location = a.interpolateLocation(time);
    }
    if (a.isType(ActionType.RESIZE_X)) {
      this.xWidth = a.interpolateX(time);
    }
    if (a.isType(ActionType.RESIZE_Y)) {
      this.yHeight = a.interpolateY(time);
    }
  }

  /**
   * This is a method defined for internal use in the class. It gets and returns a list of the
   * actions occurring on a shape for a given time in the animation. It loops through the actions
   * stored here and returns a list of the ones that overlap with the specified time.
   *
   * @param time the time in the animation
   * @return a list of all the actions for the time
   */
  private ArrayList<Action> getCurrentActions(int time) {
    ArrayList<Action> curr = new ArrayList<Action>();

    for (Action a : actions) {
      if (a.getStartTime() <= time && a.getEndTime() >= time) {
        curr.add(a);
      }
    }

    return curr;
  }

  /**
   * This is a method which returns a boolean indicating whether or not the given shape is visible
   * in its animation at a time specified by the parameter. A shape is visible if the time supplied
   * is between its create time and destroy time.
   *
   * @param time the current time in the animation
   * @return a boolean indicating the visibility
   */
  @Override
  public boolean isVisible(int time) {
    if (time >= this.createTime && time <= this.destroyTime) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * A method to return the SVG text representation for a Shape. It first creates a a string which
   * represents the shape and then loops through the actions that the shape stores and calls the
   * appropriate method to get each of their string representations. The format of this
   * representation is as follows:
   * <p>
   * <rect id="P" x="200" y="200" width="50" height="100" fill="rgb(128,0,128)" visibility="visible"
   * > <animate attributeType="xml" begin="1000ms" dur="4000ms" attributeName="x" from="200"
   * to="300" fill="freeze" />
   * </rect>
   * <p>
   * for a rectangle. And :
   * <p>
   * <ellipse id="E" cx="500" cy="100" rx="60" ry="30" fill="rgb(255,128,0)"
   * </ellipse>
   *
   * @return a string representation of the shapes compliant with svg
   */
  @Override
  public String getSVGRepresentation(int tempo) {
    StringBuilder sb = new StringBuilder();
    sb.append("<").append(this.getShapeTypeString()).append(" id=\"").append(this.getName());
    return sb.toString();
  }

  /**
   * This is a method to loop through the actions for the given shape and to get each of their
   * string representations, concatenate them and return them.
   *
   * @return a string representation of all the actions for the shape
   */
  @Override
  public String getActionSVG(int tempo) {
    StringBuilder sb = new StringBuilder();

    for (Action a : this.actions) {
      sb.append(a.getSVGRepresentation(tempo)).append("\n");
    }

    return sb.toString();
  }
}