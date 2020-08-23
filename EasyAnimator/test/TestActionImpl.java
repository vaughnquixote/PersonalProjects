import org.junit.Before;

import java.awt.*;
import java.awt.geom.Point2D;

import cs5004.animator.model.ActionImpl;
import cs5004.animator.model.Rectangle;

public class TestActionImpl {

  private ActionImpl t1;
  private Rectangle r1;

  @Before
  public void setUp() {
    r1 = new Rectangle("r1", new Point2D.Double(5, 5), new Color(40, 5, 6),
            4, 5, 6, 70);
    t1 = new ActionImpl(r1, 10, 40, r1.getLocation(), new Point2D.Double(1, 1),
            (int) r1.getWidth(), (int) r1.getWidth() + 5, (int) r1.getHeight(),
            (int) r1.getHeight() + 2, r1.getColor(), new Color(100, 100, 200));
  }





}
