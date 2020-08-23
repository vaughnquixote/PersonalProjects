import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.Rectangle;

import java.awt.Color;

/**
 * Test class for a Rectangle.
 */

public class TestRectangle {

  private Rectangle c1;
  private Rectangle c2;

  @Before
  public void setUp() {
    c1 = new Rectangle("test1", new Point2D.Double(0, 0), Color.DARK_GRAY, 2,
            10, 1, 10);
    c2 = new Rectangle("test2", new Point2D.Double(2.25, 5.75), Color.ORANGE,
            .0356, 100, 50, 100);
  }

  @Test
  public void testConstructor() {
    Rectangle t1 = new Rectangle("test", new Point2D.Double(3, 3), Color.GREEN,
            3, 4, 13, 15);
    assertEquals("Name: test\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 4.0, Height: 3.0, Color: (0,255,0)\n" +
            "Appears at t = 13\n" +
            "Disappears at t = 15\n", t1.toString());
  }

  @Test
  public void testConstructorExceptions() {

    try {
      Rectangle t1 = new Rectangle("e1", new Point2D.Double(3, 3), Color.GREEN, 0,
              1, 5, 10);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Rectangle t2 = new Rectangle("e2", new Point2D.Double(3, 3), Color.GREEN, -5,
              10, 5, 10);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Rectangle t3 = new Rectangle("e3", new Point2D.Double(3, 3), Color.GREEN, 5,
              0, 6, 10);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Rectangle t4 = new Rectangle("e4", new Point2D.Double(3, 3), Color.GREEN, 5,
              -2, 106, 108);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Rectangle t4 = new Rectangle("e4", new Point2D.Double(3, 3), Color.GREEN, 5,
              2, 166, 108);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Rectangle t4 = new Rectangle("e4", new Point2D.Double(3, 3), Color.GREEN, 5,
              2, -106, 108);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }

    try {
      Rectangle t4 = new Rectangle("e4", new Point2D.Double(3, 3), Color.GREEN, 5,
              2, 106, -108);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //do nothing
    }
  }

  @Test
  public void testReSize() {
    c1.reSize(2, 0);
    assertEquals("Name: test1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 20.0, Height: 2.0, Color: (64,64,64)\n" +
            "Appears at t = 1\n" +
            "Disappears at t = 10\n", c1.toString());

    c2.reSize(2.5, 1);
    assertEquals("Name: test2\n" +
            "Type: rectangle\n" +
            "Min corner: (2.25,5.75), Width: 100.0, Height: 0.089, Color: (255,200,0)\n" +
            "Appears at t = 50\n" +
            "Disappears at t = 100\n", c2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReSizeException1() {
    c1.reSize(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReSizeException2() {
    c1.reSize(-5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReSizeException3() {
    c1.reSize(5, 3);
  }

  @Test
  public void testReColor() {
    c1.reColor(new Color(0, 204, 0));
    assertEquals("Name: test1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,0.0), Width: 10.0, Height: 2.0, Color: (0,204,0)\n" +
                    "Appears at t = 1\n" +
                    "Disappears at t = 10\n",
            c1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReColorException() {
    c1.reColor(Color.DARK_GRAY);
  }

  @Test
  public void testMove() {
    c1.move(new Point2D.Double(1, 1));
    assertEquals("Name: test1\n" +
            "Type: rectangle\n" +
            "Min corner: (1.0,1.0), Width: 10.0, Height: 2.0, Color: (64,64,64)\n" +
            "Appears at t = 1\n" +
            "Disappears at t = 10\n", c1.toString());
  }

  @Test
  public void testGetName() {
    assertEquals("test1", c1.getName());
    assertEquals("test2", c2.getName());
  }

  @Test
  public void testGetCreationTime() {
    assertEquals(1, c1.getCreationTime());
    assertEquals(50, c2.getCreationTime());
  }

  @Test
  public void testGetDestructionTime() {
    assertEquals(10, c1.getDestructionTime());
    assertEquals(100, c2.getDestructionTime());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.DARK_GRAY, c1.getColor());
    assertEquals(Color.ORANGE, c2.getColor());
  }

  @Test
  public void testValidateActionTimes() {
    assertTrue(c1.validateActionTimes(5, 9));
    assertFalse(c1.validateActionTimes(5, 100));
    assertTrue(c2.validateActionTimes(54, 99));
    assertFalse(c2.validateActionTimes(49, 100));
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, c1.getWidth(), .01);
    assertEquals(100, c2.getWidth(), .01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(2, c1.getHeight(), .01);
    assertEquals(.0356, c2.getHeight(), .01);
  }
}