package cs5004.animator;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.controller.AnimatorControllerImpl;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.view.*;

/**
 * The class housing the main method for the program. When it is run a variety of input can be
 * offered to produce animations. Valid input for the main method is as follows: Characteristics of
 * a valid input are:
 * <p>
 * Each pair of arguments (-in "input-file", -out "output-file", etc.) may appear in any order (e.g.
 * the -view pair can appear first, followed by -in and so on)
 * <p>
 * Each pair of arguments are ordered. That is, if the user types -in then the next input must be
 * the name of an input file, and so on.
 * <p>
 * Providing an input file (the -in pair) and a view (the -view pair) are mandatory. If the output
 * set is not specified and the view needs it, the default should be System.out. If the speed is not
 * specified and the view needs it, the default is 1 tick per second.
 */
public final class EasyAnimator {

  public static void main(String[] args) throws IOException, ParserConfigurationException {

    AnimatorModel m = new AnimatorModelImpl();
    AnimatorView v = null;
    AnimatorController c;
    Readable file = null;
    String out = "out";
    int tempo = 1;
    String view = "";

    try {
      for (int i = 0; i < args.length; i = i + 2) {
        switch (args[i]) {
          case "-in":
            //exception handle
            file = new FileReader(args[i + 1]);
            break;
          case "-view":

            switch (args[i + 1]) {
              case "text":
                view = "out";
                break;
              case "svg":
                view = "svg";
                break;
              case "visual":
                view = "visual";
                break;
            }
            break;

          case "-speed":
            // exception
            tempo = Integer.parseInt(args[i + 1]);
            break;
          case "-out":
            if (args[i + 1].equals("out")) {
              out = "out";
            } else {
              out = args[i + 1];
            }
            break;
        }
      }

      switch (view) {
        case "svg":
          v = new SVGView(tempo, out, new StringBuilder());
          break;
        case "visual":
          v = new SwingView(tempo);
          break;
        case "out":
          v = new TextView(out, new StringBuilder());
          break;
      }

      c = new AnimatorControllerImpl(m, v);
      c.setModelFromFile(file);
      c.go();

    } catch (Exception e) {
      JFrame errorFrame = new JFrame("Error Box");
      JOptionPane.showMessageDialog(errorFrame, "There was invalid input!");

    }
    return;
  }
}