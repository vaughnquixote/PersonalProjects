package cs5004.animator.view;

import cs5004.animator.model.Shape;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The interface to be used for all the views of the animator. It stores the essential methods of
 * display and setSizeWrapper. The display begins the animation according to the view.
 */
public interface AnimatorView {

  /**
   * Displays the animation.
   */
  void display(ArrayList<Shape> shapes);

  /**
   * Set the size and location of the frame in which the animation is displayed.
   *
   * @param bounds first two elements are the x and y coordinates of the location for the frame the
   *               second two are the width and height (x and y length)
   */
  void setSizeWrapper(int[] bounds);






}
