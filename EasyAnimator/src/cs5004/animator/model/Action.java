package cs5004.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This interface represents an animation action on a shape. It works in conjunction with * {@link
 * AnimatorModel} & {@link Shape} to create animations. Actions operate on the shapes and * the
 * model stores all the shapes & actions for the animation. There are many types of actions,
 * including resize, recolor, and move, among others. An action is used to transcribe the animation
 * and bring it to text, so someone could read it and understand what happens in the model. This
 * interface contains methods that are common amongst all types of actions.
 */

public interface Action {

  /**
   * Get the shape object that the action acts on.
   *
   * @return the shape object that the action acts on.
   */
  Shape getShape();

  /**
   * Get the start time of the action.
   *
   * @return the start time of the action.
   */
  int getStartTime();

  /**
   * Get the end time of the action.
   *
   * @return the end time of the action
   */
  int getEndTime();

  /**
   * This a method to get the starting location of the shape relating to the action.
   *
   * @return the coordinates of the starting location relating to the action stored in a
   * Point2D.Double
   */
  Point2D.Double getStartLocation();

  /**
   * This a method to get the ending location of the shape relating to the action.
   *
   * @return the coordinates of the ending location relating to the action stored in a
   * Point2D.Double
   */
  Point2D.Double getEndLocation();

  /**
   * This is a method to return the starting width of the shape relating to the action.
   *
   * @return the starting width of the shape
   */
  int getStartWidth();

  /**
   * This is a method to return the ending width of the shape relating to the action.
   *
   * @return the ending width of the shape
   */
  int getEndWidth();

  /**
   * This is a method to return the starting height of the shape relating to the action.
   *
   * @return the starting height of the shape
   */
  int getStartHeight();

  /**
   * This is a method to return the ending height of the shape relating to the action.
   *
   * @return the shape's ending height
   */
  int getEndHeight();

  /**
   * This is a method to return the starting color of the shape relating to the action.
   *
   * @return the shape's starting color
   */
  Color getStartColor();

  /**
   * This is a method to return the ending color of the shape relating to the action.
   *
   * @return the shape's ending color
   */
  Color getEndColor();

  /**
   * This is a method which returns a "deep" copy of an Action along with all of its attributes. The
   * usage of this in our model here is to copy the data to the view. So the shape being passed in
   * is typically a copy of the shape in question.
   *
   * @param shape the shape to which the action relates
   */
  Action copy(Shape shape);

  /**
   * This is a method which returns the interpolated value for the location at the current time
   * based on the starting and ending values of location and starting and ending values of the time
   * stored in the action. It uses a basic linear interpolation to calculate the intermediate
   * value.
   *
   * @param time the current time in the animation
   * @return the current location of the action's shape for the given time
   */
  Point2D.Double interpolateLocation(int time);

  /**
   * This is a method which returns the interpolated value for the Color at the current time based
   * on the starting and ending values of color and starting and ending values of the time  stored
   * in the action. It uses a basic linear interpolation to calculate the intermediate value.
   *
   * @param time the current time in the animation
   * @return the current color of the action's shape for the given time
   */
  Color interpolateColor(int time);

  /**
   * This is a method which returns the interpolated value for the width at the current time based
   * on the starting and ending values of width and starting and ending values of the time  stored
   * in the action. It uses a basic linear interpolation to calculate the intermediate value.
   *
   * @param time the current time in the animation
   * @return the current width of the action's shape for the given time
   */
  int interpolateX(int time);

  /**
   * This is a method which returns the interpolated value for the height at the current time based
   * on the starting and ending values of height and starting and ending values of the time  stored
   * in the action. It uses a basic linear interpolation to calculate the intermediate value.
   *
   * @param time the current time in the animation
   * @return the current height of the action's shape for the given time
   */
  int interpolateY(int time);

  /**
   * This is a method to check that a given action is compatible with the calling action. The two
   * actions are compatible if they do not attempt to perform the same type of animation on the
   * shape at the same time. The shape is given from context of the calling method as data is read
   * into the model.
   *
   * @param a the other action
   * @return a boolean indicating if the action is compatible with the calling action
   */
  boolean checkCompatibility(Action a);

  /**
   * This is a method to check whether or not an action conforms to given ActionType, these types
   * indicate what attributes a given action is changing. For example, RESIZE_X indicates that the
   * size in the x dimension is changing.
   *
   * @param t an ActionType for example MOVE_X indicates a movement in the x dimension
   * @return a boolean indicating whether the current action transforms its shape in this way
   */
  boolean isType(ActionType t);

  /**
   * This is a method which creates a string representation of the Action. The strings are formatted
   * as follows: Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70 Shape C changes
   * color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80 Shape R scales from Width: 50.0,
   * Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
   */
  @Override
  String toString();

  /**
   * This is a method which returns an ArrayList of all of the ActionTypes for the given action, an
   * action type indicates the attributes that are transformed by the actions.
   *
   * @return a list of all the action's ActionType s
   */
  ArrayList<ActionType> getTypes();

  /**
   * This is a method to return an svg string representation of the current action. The strings take
   * the form : <animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cx"
   * from="500" to="600" ill="remove" /> <animate attributeType="xml" begin="2000.0ms"
   * dur="5000.0ms" attributeName="cy" from="100" to="400" fill="remove" />
   *
   * @return the svg string representation of an action for the given tempo
   */
  String getSVGRepresentation(int tempo);

}
