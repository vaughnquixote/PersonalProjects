import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import cs5004.animator.model.Oval;

/**
 * Test class for the Oval.
 */

public class TestOval {

  private Oval c1;
  private Oval c2;

  @Before
  public void setUp() {
    c1 = new Oval("test1", new Point2D.Double(10, 10), Color.BLUE, 5, 5,
            0, 10);
    c2 = new Oval("test2", new Point2D.Double(5.564, 5.1123), Color.RED, 3.454,
            4.56, 10, 100);
  }

  @Test
  public void testConstructor() {
    Oval t1 = new Oval("O", new Point2D.Double(3, 3), Color.GREEN, 3,
            5, 1, 2);
    assertEquals("Name: O\n" +
                    "Type: oval\n" +
                    "Center: (3.0,3.0), X radius: 3.0, Y radius: 5.0, Color: (255,255,0)\n" +
                    "Appears at t = 1\n" +
                    "Disappears at t = 2\n",
            t1.toString());
  }

  @Test
  public void testConstructorException() {

    try {
      Oval t1 = new Oval("fail", new Point2D.Double(3, 3), Color.YELLOW, 0,
              5, 1, 10);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Oval t2 = new Oval("failagain", new Point2D.Double(3, 3), Color.GREEN,
              5, -5, 1, 10);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Oval t2 = new Oval("failagain", new Point2D.Double(3, 3), Color.GREEN,
              5, 5, -1, 10);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Oval t2 = new Oval("failagain", new Point2D.Double(3, 3), Color.GREEN,
              5, 5, 1, -11);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  @Test
  public void testReSize() {
    c1.reSize(2, 1);
    assertEquals("Name: test1\n" +
                    "Type: oval\n" +
                    "Center: (10.0,10.0), X radius: 5.0, Y radius: 10.0, Color: (0,0,255)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n",
            c1.toString());

    c2.reSize(6.3453, 0);
    assertEquals("Name: test2\n" +
                    "Type: oval\n" +
                    "Center: (5.564,5.1123), X radius: 21.9166662, Y radius: 4.56, " +
                    "Color: (255,0,0)\n" +
                    "Appears at t = 10\n" +
                    "Disappears at t = 100\n",
            c2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReSizeException1() {
    c1.reSize(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeInvalidDimension() {
    c1.reSize(5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testResizeInvalidDimension2() {
    c1.reSize(5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReSizeInvalidScale() {
    c1.reSize(-5, 1);
  }

  @Test
  public void testReColor() {
    c1.reColor(new Color(0, 204, 0));
    assertEquals("Name: test1\n" +
                    "Type: oval\n" +
                    "Center: (10.0,10.0), X radius: 5.0, Y radius: 5.0, Color: (0,204,0)\n" +
                    "Appears at t = 0\n" +
                    "Disappears at t = 10\n",
            c1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReColorException() {
    c1.reColor(Color.BLUE);
  }

  @Test
  public void testMove() {
    c1.move(new Point2D.Double(5, 5));
    assertEquals("Name: test1\n" +
            "Type: oval\n" +
            "Center: (5.0,5.0), X radius: 5.0, Y radius: 5.0, Color: (0,0,255)\n" +
            "Appears at t = 0\n" +
            "Disappears at t = 10\n", c1.toString());
  }

  @Test
  public void testToString() {
    assertEquals("Name: test1\n" +
            "Type: oval\n" +
            "Center: (10.0,10.0), X radius: 5.0, Y radius: 5.0, Color: (0,0,255)\n" +
            "Appears at t = 0\n" +
            "Disappears at t = 10\n", c1.toString());
    assertEquals("Name: test2\n" +
                    "Type: oval\n" +
                    "Center: (5.564,5.1123), X radius: 3.454, Y radius: 4.56, " +
                    "Color: (255,0,0)\n" +
                    "Appears at t = 10\n" +
                    "Disappears at t = 100\n",
            c2.toString());
  }

  @Test
  public void testGetName() {
    assertEquals("test1", c1.getName());
    assertEquals("test2", c2.getName());
  }

  @Test
  public void testGetCreationTime() {
    assertEquals(0, c1.getCreationTime());
    assertEquals(10, c2.getCreationTime());
  }

  @Test
  public void testGetDestructionTime() {
    assertEquals(10, c1.getDestructionTime());
    assertEquals(100, c2.getDestructionTime());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLUE, c1.getColor());
    assertEquals(Color.RED, c2.getColor());
  }

  @Test
  public void testValidateActionTimes() {
    assertTrue(c1.validateActionTimes(5, 9));
    assertFalse(c1.validateActionTimes(5, 100));
    assertTrue(c2.validateActionTimes(54, 99));
    assertFalse(c2.validateActionTimes(49, 101));
  }

  @Test
  public void testGetXRadius() {
    assertEquals(5, c1.getXRadius(), .01);
    assertEquals(3.454, c2.getXRadius(), .01);
  }

  @Test
  public void testGetYRadius() {
    assertEquals(5, c1.getYRadius(), .01);
    assertEquals(4.56, c2.getYRadius(), .01);
  }
}