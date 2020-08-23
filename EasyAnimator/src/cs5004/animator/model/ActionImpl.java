package cs5004.animator.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Implementation of the Actions that occur during an animation. The Action represents one of three
 * different types of animation that can occur to a {@Shape}. These are either a resizing of the
 * shape, a movement of the shape or  a recolor of the shape. The action stores the shape itself
 * along with all of the starting and ending data for the shape as well as the times at which the
 * action begins and ends. The Action may also store data which indicate no change in a shape over a
 * time period.
 */
public class ActionImpl implements Action {

  private Shape shape;
  private int startTime;
  private int endTime;
  private Point2D.Double startLocation;
  private Point2D.Double endLocation;
  private int startWidth;
  private int endWidth;
  private int startHeight;
  private int endHeight;
  private Color startColor;
  private Color endColor;
  private ArrayList<ActionType> types;

  /**
   * The constructor for the class which takes in the shape being acted on as well as all of the
   * starting and ending attributes of the shape. These are included in the list of parameters
   * below.
   *
   * @param shape         the shape being animated
   * @param startTime     the time at which the action starts
   * @param endTime       the time at which the action is complete
   * @param startLocation the starting location of the shape
   * @param endLocation   the ending location of the shape
   * @param startWidth    the starting width of the shape
   * @param endWidth      the ending width of the shape
   * @param startHeight   the starting height of the shape
   * @param endHeight     the ending height of the shape
   * @param startColor    the starting color for the shape
   * @param endColor      the ending color for the shape
   */
  public ActionImpl(Shape shape, int startTime, int endTime, Point2D.Double startLocation,
                    Point2D.Double endLocation
          , int startWidth, int endWidth, int startHeight, int endHeight, Color startColor,
                    Color endColor) {

    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Time must be positive!");
    } else if (startTime > endTime) {
      throw new IllegalArgumentException("Start time cannot be greater than end time!");
    }

    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.startWidth = startWidth;
    this.endWidth = endWidth;
    this.startHeight = startHeight;
    this.endHeight = endHeight;
    this.startColor = startColor;
    this.endColor = endColor;
    this.types = new ArrayList<ActionType>();

    // these statements reflect the storage of an ActionType for the action all or none of which may be the case
    // the action type indicates what sort of change is occurring over the period
    if (startLocation.getX() != endLocation.getX()) {
      this.types.add(ActionType.MOVE_X);
    }

    if (startLocation.getY() != endLocation.getY()) {
      this.types.add(ActionType.MOVE_Y);
    }

    if (startWidth != endWidth) {
      this.types.add(ActionType.RESIZE_X);
    }

    if (startHeight != endHeight) {
      this.types.add(ActionType.RESIZE_Y);
    }

    if (startColor.getRed() != endColor.getRed()) {
      this.types.add(ActionType.RECOLOR_R);
    }

    if (startColor.getGreen() != endColor.getGreen()) {
      this.types.add(ActionType.RECOLOR_G);
    }

    if (startColor.getBlue() != endColor.getBlue()) {
      this.types.add(ActionType.RECOLOR_B);
    }

  }

  @Override
  public ArrayList<ActionType> getTypes() {
    return this.types;
  }

  @Override
  public Shape getShape() {
    return this.shape;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public Point2D.Double getStartLocation() {
    return this.startLocation;
  }

  @Override
  public Point2D.Double getEndLocation() {
    return this.endLocation;
  }

  @Override
  public int getStartWidth() {
    return this.startWidth;
  }

  @Override
  public int getEndWidth() {
    return this.endWidth;
  }

  @Override
  public int getStartHeight() {
    return this.startHeight;
  }

  @Override
  public int getEndHeight() {
    return this.endHeight;
  }

  @Override
  public Color getStartColor() {
    return this.startColor;
  }

  @Override
  public Color getEndColor() {
    return this.endColor;
  }

  @Override
  public Action copy(Shape shape) {
    Point2D.Double startLocCopy = new Point2D.Double(this.startLocation.getX(),
            this.startLocation.getY());
    Point2D.Double endLocCopy = new Point2D.Double(this.endLocation.getX(),
            this.endLocation.getY());
    Color startColorCopy = new Color(this.startColor.getRed(), this.startColor.getGreen()
            , this.startColor.getBlue());
    Color endColorCopy = new Color(this.endColor.getRed(), this.endColor.getGreen(),
            this.endColor.getBlue());

    return new ActionImpl(shape, this.startTime, this.endTime, startLocCopy, endLocCopy,
            this.startWidth
            , this.endWidth, this.startHeight, this.endHeight, startColorCopy, endColorCopy);
  }

  @Override
  public Point2D.Double interpolateLocation(int time) {

    int curX = interpolate((int) startLocation.getX(), (int) endLocation.getX(), time);

    int curY = interpolate((int) startLocation.getY(), (int) endLocation.getY(), time);

    return new Point2D.Double(curX, curY);
  }

  @Override
  public Color interpolateColor(int time) {

    int curR = interpolate(startColor.getRed(), endColor.getRed(), time);

    int curG = interpolate(startColor.getGreen(), endColor.getGreen(), time);

    int curB = interpolate(startColor.getBlue(), endColor.getBlue(), time);

    return new Color(curR, curG, curB);
  }

  @Override
  public int interpolateX(int time) {
    return interpolate(startWidth, endWidth, time);
  }

  @Override
  public int interpolateY(int time) {
    return interpolate(startHeight, endHeight, time);
  }

  @Override
  public boolean checkCompatibility(Action a) {

    if (this.types.isEmpty() || a.getTypes().isEmpty()) {
      return true;
    }

    if (shape.equals(a.getShape()) && a.getStartTime() >= this.startTime
            && a.getEndTime() <= this.endTime) {

      for (ActionType t : types) {
        if (a.isType(t)) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public boolean isType(ActionType t) {
    return this.types.contains(t);
  }

  private int interpolate(int start, int end, int time) {
    double firstTerm = (start * ((double) (endTime - time) / (double) (endTime - startTime)));
    double secondTerm = (end * ((double) (time - startTime) / (double) (endTime - startTime)));
    int curr = (int) (firstTerm + secondTerm);
    return curr;
  }

  @Override
  public String toString() {

    StringBuilder strRep = new StringBuilder();
    if (this.types.contains(ActionType.MOVE_X) || this.types.contains(ActionType.MOVE_Y)
            || this.types.isEmpty()) {
      strRep.append("Shape " + shape.getName() + " moves from (" + startLocation.getX() + ","
              + startLocation.getY() + ") to (" + endLocation.getX() + "," + endLocation.getY() +
              ") from t=" + startTime + " to t=" + endTime + "\n");
    }

    if (this.types.contains(ActionType.RECOLOR_B) || this.types.contains(ActionType.RECOLOR_R)
            || this.types.contains(ActionType.RECOLOR_G)) {
      strRep.append("Shape " + shape.getName() + " changes color from (" + startColor.getRed() + ","
              + startColor.getGreen() + "," + startColor.getBlue() + ") to (" + this.endColor.getRed()
              + "," + endColor.getGreen() + "," + endColor.getBlue() + ") from t=" + startTime
              + " to t="
              + endTime + "\n");
    }

    if (this.types.contains(ActionType.RESIZE_X) || this.types.contains(ActionType.RESIZE_Y)) {
      strRep.append("Shape ").append(shape.getName()).append(" scales from ");
      if (shape instanceof Rectangle) {
        strRep.append("Width: ").append(startWidth);
        strRep.append(", Height: ").append(startHeight);
        strRep.append(" to Width: ").append(endWidth);
        strRep.append(", Height: ").append(endHeight);
      } else if (shape instanceof Oval) {
        strRep.append("x radius :");
        strRep.append(startWidth / 2);
        strRep.append(", y radius: ");
        strRep.append(startHeight / 2);
        strRep.append("to x radius: ");
        strRep.append(endWidth / 2);
        strRep.append(", y radius: ");
        strRep.append(endHeight / 2);
      }
      strRep.append(" from t=").append(startTime).append(" to t=").append(endTime + "\n");
    }

    return strRep.toString();
  }

  @Override
  public String getSVGRepresentation(int tempo) {

    int tempoConstant = 1000 / tempo;

    StringBuilder sb = new StringBuilder();
    sb.append(getSVGMoveX(tempoConstant));
    sb.append(getSVGMoveY(tempoConstant));
    sb.append(getSVGChangeX(tempoConstant));
    sb.append(getSVGChangeY(tempoConstant));
    sb.append(getSVGChangeColor(tempoConstant));

    sb.append("\n");

    return sb.toString();
  }

  /**
   * This is a private helper method for the construction of the SVG string representation of the
   * action. It constructs the SVG string for a change of location in the x dimension.
   *
   * @param tempo the tempo of the animation
   * @return the string representation formatted according to SVG specification
   */
  private String getSVGMoveX(int tempo) {
    StringBuilder sb = new StringBuilder();

    sb.append("    ").append("<animate attributeType=\"xml\" begin=\"")
            .append(this.getStartTime() * tempo)
            .append(".0ms\" dur=\"").append(((this.getEndTime() - this.getStartTime()) * tempo))
            .append(".0ms\" attributeName=\"");

    if (this.getShape() instanceof Oval) {
      sb.append("cx\"");
    } else if (this.getShape() instanceof Rectangle) {
      sb.append("x\"");
    }
    sb.append(" from=\"").append(this.getStartLocation().getX()).append("\" ")
            .append("to=\"").append(this.getEndLocation().getX()).append("\"");

    return sb.append("/>").append("\n").toString();
  }

  /**
   * This is a private helper method for the construction of the SVG string representation of the
   * action. It constructs the SVG string for a change of location in the y dimension.
   *
   * @param tempo the tempo of the animation
   * @return the string representation formatted according to SVG specification
   */
  private String getSVGMoveY(int tempo) {
    StringBuilder sb = new StringBuilder();

    sb.append("    ").append("<animate attributeType=\"xml\" begin=\"")
            .append(this.getStartTime() * tempo)
            .append(".0ms\" dur=\"").append(((this.getEndTime() - this.getStartTime()) * tempo))
            .append(".0ms\" attributeName=\"");

    if (this.getShape() instanceof Oval) {
      sb.append("cy\"");
    } else if (this.getShape() instanceof Rectangle) {
      sb.append("y\"");
    }
    sb.append(" from=\"").append(this.getStartLocation().getY()).append("\" ")
            .append("to=\"").append(this.getEndLocation().getY()).append("\"");

    return sb.append("/>").append("\n").toString();


  }

  /**
   * This is a private helper method for the construction of the SVG string representation of the
   * action. It constructs the SVG string for a change of size in the x dimension.
   *
   * @param tempo the tempo of the animation
   * @return the string representation formatted according to SVG specification
   */
  private String getSVGChangeX(int tempo) {
    StringBuilder sb = new StringBuilder();

    sb.append("    ").append("<animate attributeType=\"xml\" begin=\"")
            .append(this.getStartTime() * tempo)
            .append(".0ms\" dur=\"").append(((this.getEndTime() - this.getStartTime()) * tempo))
            .append(".0ms\" attributeName=\"");

    if (this.getShape() instanceof Oval) {
      sb.append("rx\"").append(" from=\"").append(this.getStartWidth() / 2).append("\" ")
              .append("to=\"").append(this.getEndWidth() / 2).append("\"");
    } else if (this.getShape() instanceof Rectangle) {
      sb.append("width\"").append(" from=\"").append(this.getStartWidth()).append("\" ")
              .append("to=\"").append(this.getEndWidth()).append("\"");
    }

    return sb.append("/>").append("\n").toString();


  }

  /**
   * This is a private helper method for the construction of the SVG string representation of the
   * action. It constructs the SVG string for a change of size in the y dimension.
   *
   * @param tempo the tempo of the animation
   * @return the string representation formatted according to SVG specification
   */
  private String getSVGChangeY(int tempo) {

    StringBuilder sb = new StringBuilder();

    sb.append("    ").append("<animate attributeType=\"xml\" begin=\"")
            .append(this.getStartTime() * tempo)
            .append(".0ms\" dur=\"").append(((this.getEndTime() - this.getStartTime()) * tempo))
            .append(".0ms\" attributeName=\"");

    if (this.getShape() instanceof Oval) {
      sb.append("ry\"").append(" from=\"").append(this.getStartHeight() / 2).append("\" ")
              .append("to=\"").append(this.getEndHeight() / 2).append("\"");
    } else if (this.getShape() instanceof Rectangle) {
      sb.append("height\"").append(" from=\"").append(this.getStartHeight()).append("\" ")
              .append("to=\"").append(this.getEndHeight()).append("\"");
    }

    return sb.append("/>").append("\n").toString();

  }

  /**
   * This is a private helper method for the construction of the SVG string representation of the
   * action. It constructs the SVG string for a change of color.
   *
   * @param tempo the tempo of the animation
   * @return the string representation formatted according to SVG specification
   */
  private String getSVGChangeColor(int tempo) {

    StringBuilder sb = new StringBuilder();

    sb.append("    ").append("<animate attributeType=\"xml\" begin=\"")
            .append(this.getStartTime() * tempo)
            .append(".0ms\" dur=\"").append(((this.getEndTime() - this.getStartTime()) * tempo))
            .append(".0ms\" attributeName=\"");

    sb.append("fill\"").append(" from=\"rgb(").append(this.getStartColor().getRed())
            .append(",").append(this.getStartColor().getGreen()).append(",")
            .append(this.getStartColor().getBlue()).append(")\"")
            .append(" to=\"rgb(").append(this.getEndColor().getRed()).append(",")
            .append(this.getEndColor().getGreen()).append(",")
            .append(this.getEndColor().getBlue()).append(")\"");

    return sb.append("/>").append("\n").toString();

  }
}


