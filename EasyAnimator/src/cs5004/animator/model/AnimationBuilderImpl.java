package cs5004.animator.model;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

/**
 * The builder for the model which assists AnimationReader to read input from a file and store it in
 * our model, the AnimatorModelIMpl. It defines a number of methods particular to our implementation
 * that allows the reader to access our model appropriately.
 */
public class AnimationBuilderImpl implements AnimationBuilder<AnimatorModelImpl> {

  private AnimatorModelImpl model;
  private Set<String> supportedShapes;

  public AnimationBuilderImpl() {
    this.model = new AnimatorModelImpl();
    this.supportedShapes = new HashSet<String>();
  }


  @Override
  public AnimatorModelImpl build() {
    // return the model with all shapes/motions written to it
    return model;
  }

  @Override
  public AnimationBuilder setBounds(int x, int y, int width, int height) {

    // going to have to modify the ModelImpl class and set the bounds to it
    model.setBounds(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder declareShape(String name, String type) {

    // my idea is to use the validation in the model methods instead of validating here.
    // the try/catch should handle any bad input

    try {
      //need to add a default case
      switch (type) {
        case "rectangle":
          model.addRectangle(name);
          break;
        case "ellipse":
          model.addOval(name);
          break;
        case "other shapes":
          break;
      }
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    // return the current AnimationBuilder
    return this;

  }

  @Override
  public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
                                    int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
                                    int g2, int b2) {
    // get the bounds from the model
    int[] bounds = model.getBounds();

    // wrap in try block to catch exceptions from the
    try {
      // set the starting and ending locations as well as colors
      Point2D.Double startingLoc = new Point2D.Double(x1 - bounds[0], y1 - bounds[1]);
      Point2D.Double endingLoc = new Point2D.Double(x2 - bounds[0], y2 - bounds[1]);

      Color startCol = new Color(r1, g1, b1);
      Color endCol = new Color(r2, g2, b2);

      // add the action with the given information to the model
      model.addAction(name, t1, t2, startingLoc, endingLoc, w1, w2, h1, h2, startCol, endCol);

      // if action occurs before the current creation time reset starting attributes for the shape
      if (t1 < model.getShape(name).getCreationTime()) {
        model.getShape(name)
                .setStartingAttributes(new Point2D.Double(x1 - bounds[0], y1 - bounds[1])
                        , new Color(r1, g1, b1), w1, h1, t1, t2);
      } else if (t2 > model.getShape(name)
              .getDestructionTime()) { // reset destroy time if after destroy time
        model.getShape(name).setDestroyTime(t2);
      }

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    return this;
  }

}
