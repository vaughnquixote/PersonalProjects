package cs5004.animator.model;

import java.awt.geom.Point2D;
import java.awt.Color;


/**
 * This is a class being used to represent a rectangle in an animation. It stores some of the unique
 * features of rectangles, namely a yHeight and xWidth. It can be resized, recolored, and moved.
 */

public class Rectangle extends AbstractShape {

  /**
   * The constructor for the rectangle. It takes the name for the rectangle which is a unique string
   * representation for the shape in the animation. It will be used to reference the rectangle after
   * instantiation. The layerPriority must also be unique to the rectangle in the animation. It also
   * takes details needed to instantiate the shape including the location of the bottom left corner
   * the color, the yHeight and xWidth and the times at which it appears and disappears.
   *
   * @param name        the name for the shape which must be unique to it and will be used for
   *                    future reference
   * @param location    the location of the bottom left corner of the rectangle
   * @param color       the color of the shape
   * @param yHeight     the yHeight of the rectangle
   * @param xWidth      the xWidth of the rectangle
   * @param createTime  the time at which the rectangle appears
   * @param destroyTime the time at which the rectangle disappears
   */
  public Rectangle(String name, Point2D.Double location, java.awt.Color color, double xWidth,
                   double yHeight,
                   int createTime, int destroyTime)
          throws IllegalArgumentException {
    // default common features to abstract constructor
    super(name, location, color, xWidth, yHeight, createTime, destroyTime);
  }

  /**
   * @param name
   */
  public Rectangle(String name) {
    super(name);
  }

  @Override
  public void reSize(double scalingFactor, int dimension) throws IllegalArgumentException {

    if (scalingFactor <= 0 || dimension < 0 || dimension > 1) {
      throw new IllegalArgumentException("Invalid scaling factor or dimension!");
    }
    double newDimension;
    if (dimension == 0) {
      newDimension = scalingFactor * this.xWidth;
      this.xWidth = newDimension;
    } else {
      newDimension = scalingFactor * this.yHeight;
      this.yHeight = newDimension;
    }
  }

  /**
   * A method which returns a string representation for the rectangle.
   *
   * @return a string representation for the
   */
  @Override
  public String toString() {

    return "Name: " + name + "\n" + "Type: rectangle\n" + "Min corner: (" + location.x
            + "," + location.y + "), xWidth: " + xWidth + ", yHeight: " + yHeight + ", " + "Color: ("
            + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")\n"
            + "Appears at t = " + createTime + "\n" + "Disappears at t = " + destroyTime + "\n";
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
    Rectangle copy = new Rectangle(this.name, newLoc, newCol, this.xWidth, this.yHeight,
            this.createTime,
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
    return "rect";
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
    String beg = super.getSVGRepresentation(tempo);

    StringBuilder sb = new StringBuilder().append(beg);

    sb.append("\" x=\"")
            .append(this.getLocation().getX()).append("\" y=\"").append(this.getLocation().getY())
            .append("\" width=\"").append(this.getWidth()).append("\" height=\"")
            .append(this.getHeight())
            .append("\" fill=\"rgb(").append(this.getColor().getRed()).append(",")
            .append(this.getColor().getGreen()).append(",").append(this.getColor().getBlue())
            .append(")\" visibility=\"").append(this.isVisible(this.getCreationTime())).append("\" >")
            .append(this.getActionSVG(tempo)).append("\n</").append(this.getShapeTypeString())
            .append(">")
            .append("\n");

    //sb.append(this.getActionSVG());

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
