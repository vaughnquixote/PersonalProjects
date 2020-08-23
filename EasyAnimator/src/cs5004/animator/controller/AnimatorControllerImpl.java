package cs5004.animator.controller;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.Shape;
import cs5004.animator.view.AnimationReader;
import cs5004.animator.view.AnimatorView;

import java.io.IOException;
import java.util.IllformedLocaleException;
import java.util.Scanner;

/**
 * This is the implementation for the Controller for the animator. It has a model and a view for the
 * animation. The controller allows for the data in the model to be read in and set from a file. It
 * also has a method that can start the animation and will display it based on the specifications of
 * the view provided.
 */
public class AnimatorControllerImpl implements AnimatorController {

  private AnimatorModel model;
  private AnimatorView view;

  /**
   * The constructor which takes both a model and a view. Initializes both fields for the
   * controller.
   *
   * @param model the model instantiated by the user
   * @param view  the view instantiated by the user
   */
  public AnimatorControllerImpl(AnimatorModel model, AnimatorView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * A method to take Readable which should be a file filled with Shapes and Actions. It reads the
   * contents of the file and stores them in the model.
   * <p>
   * The input file format consists of two types of lines:
   * <p>
   * Shape lines: the keyword "shape" followed by two identifiers (i.e. alphabetic strings with no
   * spaces), giving the unique name of the shape, and the type of shape it is. Motion lines: the
   * keyword "motion" followed by an identifier giving the name of the shape to move, and 16
   * integers giving the initial and final conditions of the motion: eight numbers giving the time,
   * the x and y coordinates, the width and height, and the red, green and blue color values at the
   * start of the motion; followed by eight numbers for the end of the motion.  See {@link
   * AnimationBuilder#addMotion}</li>
   *
   * @param readable the file to be read an parsed
   */
  public void setModelFromFile(Readable readable) {
    if (readable == null) {
      throw new IllegalArgumentException("no file specified");
    }
    this.model = AnimationReader.parseFile(readable, new AnimationBuilderImpl());
    view.setSizeWrapper(model.getBounds());
  }

  /**
   * The model to begin the animation. It passes the needed information from the model to the view
   * through the display method which begins the animation
   *
   * @throws IOException if there is an error reading or writing to a file
   */
  public void go() {

    view.display(model.getShapeCopies());
  }

  /**
   * Returns the model being used by the controller.
   *
   * @return the model
   */
  public AnimatorModel getModel() {
    return model;
  }

}
