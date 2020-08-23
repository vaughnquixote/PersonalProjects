package cs5004.animator.view;

import cs5004.animator.model.Action;
import cs5004.animator.model.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This is a textual view. It produces a textual representation of an animation using our animator.
 * The format of the output is as follows :
 * <p></p>
 * Shapes: Name: R Type: rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color:
 * (1.0,0.0,0.0) Appears at t=1 Disappears at t=100
 * <p></p>
 * Name: C Type: oval Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
 * Appears at t=6 Disappears at t=100
 * <p></p>
 * Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50 Shape C moves from
 * (500.0,100.0) to (500.0,400.0) from t=20 to t=70 Shape C changes color from (0.0,0.0,1.0) to
 * (0.0,1.0,0.0) from t=50 to t=80 Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0,
 * Height: 100.0 from t=51 to t=70 Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to
 * t=100
 */
public class TextView implements AnimatorView {

  private PrintWriter output1;
  private String out;
  private StringBuilder sb;

  /**
   * The constructor for the text view, takes out which gives some indication from the user as to
   * where the output of the textual representation should go.
   *
   * @param out indication of the output
   * @param sb  the stringbuilder which will be used to build the text output
   */
  public TextView(String out, StringBuilder sb) {

    this.out = out;
    this.sb = sb;
  }


  @Override
  public void display(ArrayList<Shape> shapes) {
    StringBuilder shapesStrings = new StringBuilder();
    StringBuilder actionStrings = new StringBuilder();

    for (Shape s : shapes) {
      shapesStrings.append(s.toString());

      for (Action a : s.getActions()) {
        actionStrings.append(a.toString());
      }

    }
    sb.append(shapesStrings.toString());
    sb.append(actionStrings.toString());
    /*
    if (out.equals("out")) {
      output1 = new PrintWriter(System.out);
    } else {
      try {
        output1 = new PrintWriter(out);
      } catch (IOException e) {
        throw new IllegalStateException("error printing to output");
      }
    }

    output1.print(sb.toString());
    output1.close();

     */
    return;
  }

  @Override
  public void setSizeWrapper(int[] bounds) {
    // do nothing
  }

}
