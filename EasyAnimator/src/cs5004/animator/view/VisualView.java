package cs5004.animator.view;

import java.util.ArrayList;

import javax.swing.*;

import cs5004.animator.model.Shape;

public class VisualView extends JFrame implements AnimatorView {

  private int tempo;


  public VisualView(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public void display(ArrayList<Shape> shapes) {

  }

  @Override
  public void setSizeWrapper(int[] bounds) {

  }
}
