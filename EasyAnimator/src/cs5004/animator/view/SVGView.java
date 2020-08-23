package cs5004.animator.view;

import org.w3c.dom.css.Rect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import cs5004.animator.model.Action;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * This is the view which outputs an appropriate SVG file given the shapes and actions in the
 * anmation.
 */
public class SVGView implements AnimatorView {

  private int tempo;
  private int width;
  private int height;
  private StringBuilder sb;
  private String out;
  private PrintWriter output;

  /**
   * This constructs the view sets the tempo, the stringbuilder to be used as output and the String
   * "out" which is used to check the user's input and output to the option that they selected.
   *
   * @param tempo
   * @param out
   * @param sb
   */
  public SVGView(int tempo, String out, StringBuilder sb) {
    this.tempo = tempo;
    this.sb = sb;
    this.out = out;
  }

  @Override
  public void display(ArrayList<Shape> shapes) {
    sb.append(createHeaderLine()).append("\n").append("\n");
    for (Shape s : shapes) {
      sb.append(s.getSVGRepresentation(tempo)).append("\n");
    }
    sb.append("\n</svg>");

    if (out == "out") {
      output = new PrintWriter(System.out);
    } else {
      try {
        output = new PrintWriter(out);
      } catch (IOException e) {

        throw new IllegalStateException("error printing to output");
      }
    }

    output.print(sb.toString());
    output.close();
    return;
  }

  @Override
  public void setSizeWrapper(int[] bounds) {
    this.width = bounds[2];
    this.height = bounds[3];
  }

  private String createHeaderLine() {
    StringBuilder sb1 = new StringBuilder();

    sb1.append("<svg width=\"").append(this.width).append("\" height=\"").append(this.height)
            .append("\" viewBox=\"0 0 ").append(this.width).append(" ").append(this.height)
            .append("\" ").append("version=\"1.1\" xmlns=\"http://www.w3.org.2000/svg\">");
    return sb1.toString();
  }

}
