package cs5004.animator.view;

import java.awt.*;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.Shape;

/**
 * This is the visual view for for our animator. It takes a sole argument of tempo and displays the
 * view using Java's Swing API. It extends jframe and acts as the frame to which the JPanel, ie
 * DrawArea, gets added.
 */
public class SwingView implements AnimatorView {

  private JFrame frame;
  private int tempo;
  private DrawArea drawArea;

  /**
   * The constructor for the class. It initializes the frame and sets the tempo.
   *
   * @param tempo ticks per second of the animation
   */
  public SwingView(int tempo) {
    this.frame = new JFrame("Matt and Vaughn's Quarantine Swing Animator Funstravaganza");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.tempo = tempo;
  }

  @Override
  public void display(ArrayList<Shape> shapes) {
    drawArea = new DrawArea(shapes, tempo);
    drawArea.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
    frame.add(drawArea);

    addScrollPane();

    frame.setVisible(true);
  }

  /**
   * This is a method to add a scroll pane to the visual view. allows various parts of the animation
   * to be seen.
   */
  private void addScrollPane() {
    JScrollPane scrollPane = new JScrollPane(drawArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
    frame.add(scrollPane, BorderLayout.CENTER);
  }

  @Override
  public void setSizeWrapper(int[] bounds) {

    frame.setLocation(bounds[0], bounds[1]);
    frame.setSize(bounds[2], bounds[3]);

  }
}
