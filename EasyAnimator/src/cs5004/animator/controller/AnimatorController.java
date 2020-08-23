package cs5004.animator.controller;

import java.io.IOException;

import cs5004.animator.model.AnimatorModel;

/**
 * This is the controller for the animator. It has only the basic methods needed to run the
 * animation. A method to load an animation from a file, and a method go() to start the animation
 * based on the view in question.
 */

public interface AnimatorController {

  /**
   * This is the method which will start the animation in question either loading a visual or
   * producing a text representation of the animation.
   */
  void go();

  /**
   * This is a method which takes a readable file, it will load the files contents into the model
   * for the animation, storing them as {@Action} and {@Shape} objects in the model.
   *
   * @param readable the file to be read and loaded
   */
  void setModelFromFile(Readable readable);

  /**
   * This is a method which returns the model for the current controller if it has been
   * initialized.
   *
   * @return the model of the animation
   */
  AnimatorModel getModel();

}
