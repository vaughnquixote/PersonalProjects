package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.awt.Color;


/**
 * This class represents an Oval shape. An oval implements all the methods specified in the Shape
 * interface, as well as other methods specific to an oval. An oval is a type of shape that is
 * circular, with a radius in the x and y direction.
 */

public class Oval extends AbstractShape {

  /**
   * The constructor for an Oval. It takes the name for the shape, the location of the center, the x
   * radius and the y radius, the time of appearance in the animation, the time of disappearance the
   * color, and the layer which a shape will occupy. The name and layer must be unique to the shape
   * in the animation. The location of the center must have positive coordinates. The destroy time
   * must be after the creation time and both must be positive.
   *
   * @param name        the name to be used for future reference.
   * @param location    the location for the center of the oval
   * @param color       the color of the shape
   * @param xWidth      the radius in the x dimension
   * @param yHeight     the radius in the y dimension
   * @param createTime  the time at which the shape appears
   * @param destroyTime the time at which the shape disappears
   */
  public Oval(String name, Point2D.Double location, Color color, double xWidth, double yHeight,
              int createTime, int destroyTime)
          throws IllegalArgumentException {
    // default to the super class for these parameters
    super(name, location, color, xWidth, yHeight, createTime, destroyTime);

    //this.location = new Point2D.Double(this.getLocation().getX() + getXRadius(),
    //this.getLocation().getY() + getYRadius());
  }

  /**
   * @param name
   */
  public Oval(String name) {
    super(name);
  }

  /**
   * This is a method which allows for the resizing of an oval in a given dimension. The method
   * takes a scaling factor which will be multiplied by the radius in the dimension. The dimension
   * is an integer which can be either 0 or 1. 0 represents the x dimension and 1 represents the y
   * dimension. The method validates the input and then updates the specified radius by the
   * specified scaling factor.
   *
   * @param scalingFactor the multiplier for the radius
   * @param dimension     the dimension to be scaled, 0 -- x or 1 -- y
   * @throws IllegalArgumentException if passed illegal arguments.
   */
  @Override
  public void reSize(double scalingFactor, int dimension) throws IllegalArgumentException {
    // validate the parameters
    if ((scalingFactor <= 0) || (dimension > 1) || (dimension < 0)) {
      throw new IllegalArgumentException("Invalid scaling factor or dimension");
    }
    // update needed dimension
    double newRad;
    if (dimension == 0) {
      newRad = xWidth * scalingFactor;
      xWidth = newRad;
    } else {
      newRad = yHeight * scalingFactor;
      yHeight = newRad;
    }
  }

  /**
   * A method designed to return a string representation of the oval.
   *
   * @return string representation of the oval.
   */
  @Override
  public String toString() {
    return "Name: " + name + "\n" + "Type: oval\n" + "Center: (" + location.x
            + "," + location.y + "), X radius: " + xWidth + ", Y radius: " + yHeight + ", Color: ("
            + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")\n"
            + "Appears at t = "
            + createTime + "\n" + "Disappears at t = " + destroyTime + "\n";
  }

  /**
   * A method which returns a copy of the given shape along with all of its associated data. It
   * returns a "deep" copy of the shape including all of the actions associated withe shape if any
   * exist.
   *
   * @return a copy of the shape
   */
  @Override
  public Shape copy() {
    Point2D.Double newLoc = new Point2D.Double(location.getX(), location.getY());
    Color newCol = new Color(color.getRed(), color.getGreen(), color.getBlue());
    Oval copy = new Oval(this.name, newLoc, newCol, this.xWidth, this.yHeight, this.createTime,
            this.destroyTime);
    for (Action a : this.actions) {
      copy.addAction(a.copy(copy));
    }
    return copy;
  }

  /**
   * This is a method which returns a string representation of the "type" of the shape. It returns
   * either "rect" for rectangle or "ellipse" for oval. It is used in the construction of certain
   * text representations of animations.
   *
   * @return a string representing the shape's type.
   */
  @Override
  public String getShapeTypeString() {
    return "ellipse";
  }


  /**
   * @return
   */
  public double getXRadius() {
    return xWidth / 2;
  }

  /**
   * @return
   */
  public double getYRadius() {
    return yHeight / 2;
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
  public String getSVGRepresentation(int tempo) {
    String beg = super.getSVGRepresentation(tempo);

    StringBuilder sb = new StringBuilder().append(beg);

    sb.append("\" cx=\"")
            .append(this.getLocation().getX()).append("\" cy=\"").append(this.getLocation().getY())
            .append("\" rx=\"").append(this.getXRadius()).append("\" ry=\"").append(this.getYRadius())
            .append("\" fill=\"rgb(").append(this.getColor().getRed()).append(",")
            .append(this.getColor().getGreen()).append(",").append(this.getColor().getBlue())
            .append(")\" visibility=\"").append(this.isVisible(this.getCreationTime())).append("\" >")
            .append(this.getActionSVG(tempo)).append("\n</").append(this.getShapeTypeString())
            .append(">");

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
    return super.getActionSVG(tempo);
  }
}