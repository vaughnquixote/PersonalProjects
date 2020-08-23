package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.awt.Color;
import java.util.ArrayList;

/**
 * This is an interface for a shape in a simple animation. It works in conjunction with {@link
 * AnimatorModel} & {@link Action} to create animations. Actions operate on the shapes and the model
 * stores all the shapes and actions for the animation.
 */

public interface Shape {

  /**
   * This is a method which mutates the shape changing the current location to the location given by
   * the parameter.
   *
   * @param newLoc the new location of the shape, a Point2D.Double
   */
  void move(Point2D.Double newLoc);

  /**
   * This is a method to resize the shape by the given scaling factor in the given dimension. The
   * scaling factor is multiplied by the value of the shape in the given dimension which is either 0
   * or 1. 0 for x; 1 for y.
   *
   * @param scalingFactor the factor by which the value of the shape in the dimension is changed
   * @param dimension     the dimension of the change, 0 or 1
   */
  void reSize(double scalingFactor, int dimension) throws IllegalArgumentException;

  /**
   * This is a method to change the color of the shape. It mutates the shape's color to the new
   * color provided as a parameter.
   *
   * @param c the new color for the shape
   */
  void reColor(java.awt.Color c) throws IllegalArgumentException;

  /**
   * This is a method to return the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * This is a method to return the creation time for the shape. The time at which the shape appears
   * in the animation.
   *
   * @return the value of the appearance time for the shape.
   */
  int getCreationTime();

  /**
   * This is a method to return the destruction time for the shape. The time at which the shape
   * disappears in the animation.
   *
   * @return the value of the disappearance time for the shape
   */
  int getDestructionTime();

  /**
   * A method to return the current color of the shape in the animation.
   *
   * @return the color of the shape.
   */
  Color getColor();

  /**
   * This is a method that validates that the times for an action to be created conform to the
   * appearance and disappearance of the shape. They must be between the creation and destruction of
   * the shape.
   *
   * @param startTime the starting time for the action
   * @param endTime   the ending time for the action
   * @return a boolean indicating if the given start time and end time are valid for the shape
   */
  boolean validateActionTimes(int startTime, int endTime);

  /**
   * A method to return a string representation of the shape it follows the form:
   * <p></p>
   * Name: R Type: rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color:
   * (1.0,0.0,0.0) Appears at t=1 Disappears at t=100
   * <p></p>
   * or
   * <p></p>
   * Name: C Type: oval Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
   * Appears at t=6 Disappears at t=100
   *
   * @return a string representation of the shape at hand.
   */
  String toString();

  /**
   * This method adds an action to the shape. The action should pertain to and be linked to the
   * shape. It stores the action in a data structure internal to the shape.
   *
   * @param a the action that will be added
   */
  void addAction(Action a);

  /**
   * A method which returns a copy of the given shape along with all of its associated data. It
   * returns a "deep" copy of the shape including all of the actions associated withe shape if any
   * exist.
   *
   * @return a copy of the shape
   */
  Shape copy();

  /**
   * This is a method which returns all of the actions relevant to the shape that take place during
   * the animation.
   *
   * @return an ArrayList containing all of the actions which happen on the shape over the course of
   * the animation.
   */
  ArrayList<Action> getActions();

  /**
   * A method to return the width of the shape. It is the size of the shape in the x dimension.
   *
   * @return the width of the shape
   */
  double getWidth();

  /**
   * A method to return the height of the shape. It is the size of the shape in the y dimension.
   *
   * @return the height of the shape.
   */
  double getHeight();

  /**
   * A method to return the current location of the shape. It returns a Point2D.Double which
   * represents the location of the shape on the screen.
   *
   * @return the coordinates of the shape on the screen
   */
  Point2D.Double getLocation();

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
  void setStartingAttributes(Point2D.Double location, Color color, double xWidth,
                             double yHeight, int createTime, int destroyTime);

  /**
   * Setting the destroy time for the shape. this needs to be done if a new action is added which
   * finishes after all current actions have run their course.
   *
   * @param destroyTime
   */
  void setDestroyTime(int destroyTime);

  /**
   * This is a method that will change the shapes attributes to the attributes specified by the
   * actions for the current time passed into the method as a parameter. The action could specify a
   * change in color, change in size or change in location. Since the actions store starting and
   * ending states of the shape linear interpolation is used to get the intermediate values of the
   * attributes for a given time.
   *
   * @param time the current time in the animation
   */
  void mutateShape(int time);

  /**
   * This is a method which takes an action and a time as parameters and updates the shapes
   * attributes according to the specification of the action. It uses linear interpolation along
   * with the starting/ending states specified by the action to compute the new values of the
   * attributes for a given time.
   *
   * @param a    the action which is taking place on the shape
   * @param time the current time in the animation
   */
  void interpolate(Action a, int time);

  /**
   * This is a method which returns a boolean indicating whether or not the given shape is visible
   * in its animation at a time specified by the parameter.
   *
   * @param time the current time in the animation
   * @return a boolean indicating the visibility
   */
  boolean isVisible(int time);

  /**
   * This is a method which returns a string representation of the "type" of the shape. It returns
   * either "rect" for rectangle or "ellipse" for oval. It is used in the construction of certain
   * text representations of animations.
   *
   * @return a string representing the shape's type.
   */
  String getShapeTypeString();

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
   * @param tempo the tempo of the animation
   * @return a string representation of the shapes compliant with svg
   */
  String getSVGRepresentation(int tempo);

  /**
   * This is a method to loop through the actions for the given shape and to get each of their
   * string representations, concatenate them and return them.
   *
   * @param tempo the tempo of the animation
   * @return a string representation of all the actions for the shape
   */
  String getActionSVG(int tempo);

}

