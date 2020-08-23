import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.ActionType;
import cs5004.animator.model.AnimatorModelImpl;

/**
 * Tests for the model class.
 */

public class TestAnimatorModel {
/*
  private AnimatorModelImpl model;

  @Before
  public void setUp() {
    model = new AnimatorModelImpl();
  }

  @Test
  public void addRectangle() {
    Color c = Color.RED;

    model.addRectangle("testRect",c,new Point2D.Double(3, 3), 5,6,
            8,10);
    assertEquals("Name: testRect\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, Color: (255,0,0)\n" +
            "Appears at t = 8\n" +
            "Disappears at t = 10\n\n", model.toString());
  }

  @Test
  public void addMultipleRectangles() {
    Color c = Color.RED;

    model.addRectangle("testRect", c, new Point2D.Double(3,3), 5,
            6, 7, 8);

    model.addRectangle("testRect2", c, new Point2D.Double(5, 5), 6,
            5, 9, 15);
    model.addRectangle("testRect3", c, new Point2D.Double(100, 50), 100,
            500, 150, 350);
    assertEquals("Name: testRect3\n" +
            "Type: rectangle\n" +
            "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, Color: (255,0,0)\n" +
            "Appears at t = 150\n" +
            "Disappears at t = 350\n" +
            "Name: testRect2\n" +
            "Type: rectangle\n" +
            "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, Color: (255,0,0)\n" +
            "Appears at t = 9\n" +
            "Disappears at t = 15\n" +
            "Name: testRect\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, Color: (255,0,0)\n" +
            "Appears at t = 7\n" +
            "Disappears at t = 8\n" + "\n", model.toString());
  }

  @Test
  public void addRectangleSameNameException() {

    Color c = Color.RED;

    try {
      model.addRectangle("testRect", c, new Point2D.Double(3, 3), 5,
              6, 7, 8);
      model.addRectangle("testRect", c, new Point2D.Double(5, 5), 6,
              5, 9, 15);
      fail("Exception should have been thrown!");
    } catch (Exception e) {
      // do nothing
    }
  }

  @Test
  public void addOval() {
    model.addOval("test1", new Color(0,0,0), new Point2D.Double(5, 5), 200, 100,
            50, 100);
    assertEquals("Name: test1\n" +
            "Type: oval\n" +
            "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 50\n" +
            "Disappears at t = 100\n" +
            "\n", model.toString());
  }

  @Test
  public void addMultipleOvals() {
    model.addOval("test1", new Color(0,0,0), new Point2D.Double(5, 5), 200,
            100, 50, 100);
    model.addOval("test2", new Color(0,204,0), new Point2D.Double(59, 599), 200,
            100, 20, 100);
    model.addOval("test3", new Color(0,0,0), new Point2D.Double(5, 5), 200, 100,
            50, 100);
    assertEquals("Name: test3\n" +
            "Type: oval\n" +
            "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 50\n" +
            "Disappears at t = 100\n" +
            "Name: test2\n" +
            "Type: oval\n" +
            "Center: (59.0,599.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,204,0)\n" +
            "Appears at t = 20\n" +
            "Disappears at t = 100\n" +
            "Name: test1\n" +
            "Type: oval\n" +
            "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 50\n" +
            "Disappears at t = 100\n" +
            "\n" , model.toString());
  }

  @Test
  public void addOvalSameNameException() {
    try {
      model.addOval("testOval", new Color(0,0,0), new Point2D.Double(3, 3), 5,
              6, 7, 8);
      model.addOval("testOval", new Color(0,0,0), new Point2D.Double(5, 5), 6,
              5, 9, 15);
      fail("Exception should have been thrown!");
    } catch (Exception e) {
      // do nothing
    }
  }

  @Test
  public void testMoveShapeDoesNotExist() {
    try {
      model.move("oval", 10, 20, new Point2D.Double(24, 25)
              , new Point2D.Double(1,1));
      fail("Exception should have been thrown!");
    } catch (Exception e) {
      //do nothing
    }
  }

  @Test
  public void testMove() {
    model.addOval("O1", new Color(0,0,0), new Point2D.Double(5, 5), 200,
            100, 50, 100);
    model.addOval("O2", new Color(0,0,0), new Point2D.Double(59, 599), 200,
            100, 20, 100);
    model.addOval("O3", new Color(0,0,0), new Point2D.Double(5, 5), 200, 100,
            50, 100);
    model.addRectangle("R1", new Color(0,0,0), new Point2D.Double(3, 3), 5,
            6, 7, 700);
    model.addRectangle("R2", new Color(0,0,0), new Point2D.Double(5, 5), 6,
            5, 9, 15);
    model.addRectangle("R3", new Color(0,0,0), new Point2D.Double(100, 50), 100,
            500, 150, 350);
    model.move("R1", 10, 200,
            new Point2D.Double(3, 3), new Point2D.Double(10, 10));
    model.move("O2", 25, 70,
            new Point2D.Double(59, 599), new Point2D.Double(59, 60));
    model.move("O2", 71, 85,
            new Point2D.Double(59, 60), new Point2D.Double(20, 60));


    assertEquals("Name: R3\n" +
            "Type: rectangle\n" +
            "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 150\n" +
            "Disappears at t = 350\n" +
            "Name: R2\n" +
            "Type: rectangle\n" +
            "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 9\n" +
            "Disappears at t = 15\n" +
            "Name: R1\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 7\n" +
            "Disappears at t = 700\n" +
            "Name: O3\n" +
            "Type: oval\n" +
            "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 50\n" +
            "Disappears at t = 100\n" +
            "Name: O2\n" +
            "Type: oval\n" +
            "Center: (59.0,599.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 20\n" +
            "Disappears at t = 100\n" +
            "Name: O1\n" +
            "Type: oval\n" +
            "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
            "Color: (0,0,0)\n" +
            "Appears at t = 50\n" +
            "Disappears at t = 100\n" +
            "\n" +
            "Shape R1 moves from (3.0,3.0) to (10.0,10.0) from t=10 to t=200\n" +
            "Shape O2 moves from (59.0,599.0) to (59.0,60.0) from t=25 to t=70\n" +
            "Shape O2 moves from (59.0,60.0) to (20.0,60.0) from t=71 to t=85\n"
            , model.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveInvalidTime() {
    model.addOval("O2", new Color(0,0,0), new Point2D.Double(59, 599), 200,
            100, 20, 100);
    model.move("O2", 75, 70,
            new Point2D.Double(59, 599), new Point2D.Double(59, 60));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveInvalidTime2() {
    model.addOval("O2", new Color(0,0,0), new Point2D.Double(59, 599), 200,
            100, 20, 100);
    model.move("O2", -1, 70,
            new Point2D.Double(59, 599), new Point2D.Double(59, 60));
  }

  @Test
  public void testRemoveAction() {
    model.addOval("O1", new Color(0,0,0), new Point2D.Double(5, 5), 200,
            100, 50, 100);
    model.addOval("O2", new Color(0,204,0), new Point2D.Double(59, 599), 200,
            100, 20, 100);
    model.addOval("O3", new Color(0,0,0), new Point2D.Double(5, 5), 200, 100,
            50, 100);
    model.addRectangle("R1", Color.ORANGE, new Point2D.Double(3, 3), 5,
            6, 7, 700 );
    model.addRectangle("R2", Color.ORANGE, new Point2D.Double(5, 5), 6,
            5, 9, 15);
    model.addRectangle("R3", Color.GREEN, new Point2D.Double(100, 50), 100,
            500, 150, 350);
    model.move("R1", 10, 200,
            new Point2D.Double(3, 3), new Point2D.Double(10, 10));
    model.move("O2", 25, 70,
            new Point2D.Double(59, 599), new Point2D.Double(59, 60));
    model.move("O2", 71, 85,
            new Point2D.Double(59, 60), new Point2D.Double(20, 60));

    assertEquals("Name: R3\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, " +
                    "Color: (0,255,0)\n" +
                    "Appears at t = 150\n" +
                    "Disappears at t = 350\n" +
                    "Name: R2\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 9\n" +
                    "Disappears at t = 15\n" +
                    "Name: R1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 7\n" +
                    "Disappears at t = 700\n" +
                    "Name: O3\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "Name: O2\n" +
                    "Type: oval\n" +
                    "Center: (59.0,599.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,204,0)\n" +
                    "Appears at t = 20\n" +
                    "Disappears at t = 100\n" +
                    "Name: O1\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "\n" +
                    "Shape R1 moves from (3.0,3.0) to (10.0,10.0) from t=10 to t=200\n" +
                    "Shape O2 moves from (59.0,599.0) to (59.0,60.0) from t=25 to t=70\n" +
                    "Shape O2 moves from (59.0,60.0) to (20.0,60.0) from t=71 to t=85\n"
            , model.toString());
    model.removeAction("O2", 25, ActionType.MOVE);
    assertEquals("Name: R3\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, " +
                    "Color: (0,255,0)\n" +
                    "Appears at t = 150\n" +
                    "Disappears at t = 350\n" +
                    "Name: R2\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 9\n" +
                    "Disappears at t = 15\n" +
                    "Name: R1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 7\n" +
                    "Disappears at t = 700\n" +
                    "Name: O3\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "Name: O2\n" +
                    "Type: oval\n" +
                    "Center: (59.0,599.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,204,0)\n" +
                    "Appears at t = 20\n" +
                    "Disappears at t = 100\n" +
                    "Name: O1\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "\n" +
                    "Shape R1 moves from (3.0,3.0) to (10.0,10.0) from t=10 to t=200\n" +
                    "Shape O2 moves from (59.0,60.0) to (20.0,60.0) from t=71 to t=85\n"
            , model.toString());
    model.removeAction("R1", 10, ActionType.MOVE);
    assertEquals("Name: R3\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, " +
                    "Color: (0,255,0)\n" +
                    "Appears at t = 150\n" +
                    "Disappears at t = 350\n" +
                    "Name: R2\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 9\n" +
                    "Disappears at t = 15\n" +
                    "Name: R1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 7\n" +
                    "Disappears at t = 700\n" +
                    "Name: O3\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "Name: O2\n" +
                    "Type: oval\n" +
                    "Center: (59.0,599.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,204,0)\n" +
                    "Appears at t = 20\n" +
                    "Disappears at t = 100\n" +
                    "Name: O1\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "\n" +
                    "Shape O2 moves from (59.0,60.0) to (20.0,60.0) from t=71 to t=85\n"
            , model.toString());
    model.removeAction("R1", 10, ActionType.MOVE);
    assertEquals("Name: R3\n" +
                    "Type: rectangle\n" +
                    "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, " +
                    "Color: (0,255,0)\n" +
                    "Appears at t = 150\n" +
                    "Disappears at t = 350\n" +
                    "Name: R2\n" +
                    "Type: rectangle\n" +
                    "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 9\n" +
                    "Disappears at t = 15\n" +
                    "Name: R1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, " +
                    "Color: (255,200,0)\n" +
                    "Appears at t = 7\n" +
                    "Disappears at t = 700\n" +
                    "Name: O3\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "Name: O2\n" +
                    "Type: oval\n" +
                    "Center: (59.0,599.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,204,0)\n" +
                    "Appears at t = 20\n" +
                    "Disappears at t = 100\n" +
                    "Name: O1\n" +
                    "Type: oval\n" +
                    "Center: (5.0,5.0), X radius: 200.0, Y radius: 100.0, " +
                    "Color: (0,0,0)\n" +
                    "Appears at t = 50\n" +
                    "Disappears at t = 100\n" +
                    "\n" +
                    "Shape O2 moves from (59.0,60.0) to (20.0,60.0) from t=71 to t=85\n"
            , model.toString());
  }

  @Test
  public void testDestroyShape() {
    model.addRectangle("testRect", Color.ORANGE, new Point2D.Double(3, 3), 5,
            6, 7, 8);
    model.addRectangle("testRect2", Color.ORANGE, new Point2D.Double(5, 5), 6,
            5, 9, 15);
    model.addRectangle("testRect3", new Color(0,204,0), new Point2D.Double(100, 50),
            100, 500, 150, 350);
    assertEquals("Name: testRect3\n" +
            "Type: rectangle\n" +
            "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, Color: (0,204,0)\n" +
            "Appears at t = 150\n" +
            "Disappears at t = 350\n" +
            "Name: testRect2\n" +
            "Type: rectangle\n" +
            "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, Color: (255,200,0)\n" +
            "Appears at t = 9\n" +
            "Disappears at t = 15\n" +
            "Name: testRect\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, Color: (255,200,0)\n" +
            "Appears at t = 7\n" +
            "Disappears at t = 8\n" + "\n", model.toString());
    model.destroyShape("testRect");
    assertEquals("Name: testRect3\n" +
            "Type: rectangle\n" +
            "Min corner: (100.0,50.0), Width: 500.0, Height: 100.0, Color: (0,204,0)\n" +
            "Appears at t = 150\n" +
            "Disappears at t = 350\n" +
            "Name: testRect2\n" +
            "Type: rectangle\n" +
            "Min corner: (5.0,5.0), Width: 5.0, Height: 6.0, Color: (255,200,0)\n" +
            "Appears at t = 9\n" +
            "Disappears at t = 15\n" + "\n", model.toString());
  }

  @Test
  public void testReColor() {
    model.addRectangle("testRect", Color.ORANGE, new Point2D.Double(3, 3), 5,
            6, 7, 100);
    assertEquals("Name: testRect\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, Color: (255,200,0)\n" +
            "Appears at t = 7\n" +
            "Disappears at t = 100\n\n", model.toString());

    model.reColor("testRect",8,90,Color.ORANGE,
            new Color(0,0,0));
    assertEquals("Name: testRect\n" +
            "Type: rectangle\n" +
            "Min corner: (3.0,3.0), Width: 6.0, Height: 5.0, Color: (255,200,0)\n" +
            "Appears at t = 7\n" +
            "Disappears at t = 100\n\n" + "Shape testRect changes color from (255,200,0) to "
                   + "(0,0,0) from t=8 to t=90"
            , model.toString());
  }

  @Test
  public void testReColorException() {

    model.addRectangle("testRect", Color.ORANGE, new Point2D.Double(3, 3), 5,
            6, 7, 10);

    try {
      model.reColor("testRect",-1,8,Color.ORANGE,
              new Color(0,0,0));
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model.reColor("testRect",8,-8,Color.ORANGE,
              new Color(0,0,0));
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model.reColor("testtRect",8,30,Color.ORANGE,
              Color.BLUE);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model.reColor("testRect",8,30,Color.ORANGE,
              Color.ORANGE);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model.reColor("testRect",8,30,Color.ORANGE,
              Color.BLUE);
      model.reColor("testRect",8,30,Color.ORANGE,
              Color.BLUE);
      fail("Exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

 */
}
