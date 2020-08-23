import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.controller.AnimatorControllerImpl;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.view.AnimatorView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;

public class TestAnimatorController {

  private AnimatorController c;
  private AnimatorModel m;
  private AnimatorView v;

  @Before
  public void setUp() {
  }
/*
  @Test
  public void testSetModelFromFile() throws IOException {
    m = new AnimatorModelImpl();
    v = new TextView();

    c = new AnimatorControllerImpl(m,v);
    c.setModelFromFile(new FileReader("smalldemo.txt"));

    // ADDED GETMODEL() SOLELEY FOR TEST. DOESNT SEEM GOOD?

    assertEquals("Name: R\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,130.0), xWidth: 50.0, yHeight: 100.0, Color: (255,0,0)\n" +
            "Appears at t = 1\n" +
            "Disappears at t = 100\n" +
            "Name: C\n" +
            "Type: oval\n" +
            "Center: (240.0,0.0), X radius: 120.0, Y radius: 60.0, Color: (0,0,255)\n" +
            "Appears at t = 6\n" +
            "Disappears at t = 100\n" +
            "\n" +
            "Shape R moves from (0.0,130.0) to (0.0,130.0) from t=1 to t=10\n" +
            "Shape R moves from (0.0,130.0) to (100.0,230.0) from t=10 to t=50\n" +
            "Shape R moves from (100.0,230.0) to (100.0,230.0) from t=50 to t=51\n" +
            "Shape R scales from Width: 50, Height: 100 to Width: 25, Height: 100 from t=51 to t=70\n" +
            "Shape R moves from (100.0,230.0) to (0.0,130.0) from t=70 to t=100\n" +
            "Shape C moves from (240.0,0.0) to (240.0,0.0) from t=6 to t=20\n" +
            "Shape C moves from (240.0,0.0) to (240.0,180.0) from t=20 to t=50\n" +
            "Shape C moves from (240.0,180.0) to (240.0,300.0) from t=50 to t=70\n" +
            "Shape C changes color from (0,0,255) to (0,170,85) from t=50 to t=70\n" +
            "Shape C changes color from (0,170,85) to (0,255,0) from t=70 to t=80\n" +
            "Shape C moves from (240.0,300.0) to (240.0,300.0) from t=80 to t=100\n", c.getModel().toString());
  }
*/

/*
  @Test(expected = IllegalArgumentException.class)
  public void testSetModelFromFileException() {
    m = null;
    v = new SwingView(1);

    c = new AnimatorControllerImpl(m,v);
    c.setModelFromFile(null);

  }
  //NEED TEST for go
  */

}
