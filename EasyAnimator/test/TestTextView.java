import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import cs5004.animator.view.*;
import cs5004.animator.model.*;
import cs5004.animator.controller.*;

import static org.junit.Assert.assertEquals;

public class TestTextView {

  @Test
  public void testTextViewEmptyModel() {
    String output = "out";
    StringBuilder sb = new StringBuilder();
    AnimatorView view = new TextView(output, sb);
    AnimatorModel emptyModel = new AnimatorModelImpl();
    AnimatorController contr = new AnimatorControllerImpl(emptyModel, view);
    contr.go();
    assertEquals("", sb.toString());
  }

  @Test
  public void testTextSingleRectangle() {
    AnimatorModel singleR = new AnimatorModelImpl();
    singleR.addRectangle("rect1", Color.ORANGE, new Point2D.Double(75, 75),
            100, 500, 5, 50);
    String output = "";
    StringBuilder sb = new StringBuilder();
    AnimatorView view= new TextView(output, new StringBuilder());
    AnimatorController contr = new AnimatorControllerImpl(singleR, view);
    contr.go();
    assertEquals("Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (75.0,75.0), xWidth: 100.0, yHeight: 500.0, Color: (255,200,0)\n" +
            "Appears at t = 5\n" +
            "Disappears at t = 50\n" +
            "\n", sb.toString());
  }

  @Test
  public void testTextSingleOval() {
    AnimatorModel singleO = new AnimatorModelImpl();
    singleO.addOval("oval1", Color.BLUE, new Point2D.Double(100, 150),
            100, 500, 5, 50);
    String output = "";
    StringBuilder sb = new StringBuilder();
    AnimatorView view = new TextView(output, new StringBuilder());
    AnimatorController contr = new AnimatorControllerImpl(singleO, view);
    contr.go();
    assertEquals("Name: oval1\n" +
            "Type: oval\n" +
            "Center: (100.0,150.0), X radius: 100.0, Y radius: 500.0, Color: (0,0,255)\n" +
            "Appears at t = 5\n" +
            "Disappears at t = 50\n" +
            "\n", sb.toString());
  }

  @Test
  public void testTextMultipleShapes() {
    AnimatorModel multi = new AnimatorModelImpl();
    multi.addOval("oval1", Color.BLUE, new Point2D.Double(100, 150),
            100, 500, 5, 50);
    multi.addRectangle("rect1", Color.ORANGE, new Point2D.Double(75, 75),
            100, 500, 5, 50);
    String output = "";
    StringBuilder sb = new StringBuilder();
    AnimatorView view = new TextView(output, new StringBuilder());
    AnimatorController contr  = new AnimatorControllerImpl(multi, view);
    contr.go();
    assertEquals("Name: oval1\n" +
            "Type: oval\n" +
            "Center: (100.0,150.0), X radius: 100.0, Y radius: 500.0, Color: (0,0,255)\n" +
            "Appears at t = 5\n" +
            "Disappears at t = 50\n" +
            "Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (75.0,75.0), xWidth: 100.0, yHeight: 500.0, Color: (255,200,0)\n" +
            "Appears at t = 5\n" +
            "Disappears at t = 50\n", sb.toString());
  }

  @Test
  public void testTextShapeWithActions() {
    AnimatorModel shapeWith = new AnimatorModelImpl();
    shapeWith.addRectangle("rect1", Color.ORANGE, new Point2D.Double(75, 75),
            100, 500, 5, 50);
    shapeWith.addAction("rect1", 1, 10, new Point2D.Double(1, 1),
            new Point2D.Double(2, 2), 5, 5, 10, 10,
            new Color(0, 0, 0), new Color(0, 0, 0));
    shapeWith.addAction("rect1", 10, 20, new Point2D.Double(2, 2),
            new Point2D.Double(2, 2), 5, 15, 10, 12,
            new Color(0, 0, 0), new Color(255, 255, 255));
    String output = "";
    StringBuilder sb = new StringBuilder();
    AnimatorView view = new TextView(output, sb);
    AnimatorController contr  = new AnimatorControllerImpl(shapeWith, view);
    contr.go();
    assertEquals("Name: rect1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (75.0,75.0), xWidth: 100.0, yHeight: 500.0, Color: (255,200,0)\n" +
                    "Appears at t = 5\n" +
                    "Disappears at t = 50\n" +
                    "Shape rect1 moves from (1.0,1.0) to (2.0,2.0) from t=1 to t=10\n" +
                    "Shape rect1 changes color from (0,0,0) to (255,255,255) from t=10 to t=20\n" +
                    "Shape rect1 scales from Width: 5, Height: 10 to Width: 15, Height: 12 from t=10 to t=20\n",
            sb.toString());
  }

  @Test
  public void testTextShapesWithActions() {
    AnimatorModel shapeWith = new AnimatorModelImpl();
    shapeWith.addRectangle("rect1", Color.ORANGE, new Point2D.Double(75, 75),
            100, 500, 5, 50);
    shapeWith.addAction("rect1", 1, 10, new Point2D.Double(1, 1),
            new Point2D.Double(2, 2), 5, 5, 10, 10,
            Color.ORANGE, Color.ORANGE);
    shapeWith.addAction("rect1", 10, 20, new Point2D.Double(2, 2),
            new Point2D.Double(2, 2), 5, 15, 10, 12,
            Color.ORANGE, new Color(255, 255, 255));
    shapeWith.addOval("oval1", Color.BLUE, new Point2D.Double(100, 150),
            100, 500, 5, 50);
    shapeWith.addAction("oval1", 25, 100, new Point2D.Double(50, 50),
            new Point2D.Double(100, 100), 10, 10, 10, 10,
            Color.GREEN, Color.BLUE);
    String output = "";
    StringBuilder sb = new StringBuilder();
    AnimatorView view = new TextView(output, new StringBuilder());
    AnimatorController contr = new AnimatorControllerImpl(shapeWith, view);
    contr.go();
    assertEquals("Name: rect1\n" +
                    "Type: rectangle\n" +
                    "Min corner: (75.0,75.0), xWidth: 100.0, yHeight: 500.0, Color: (255,200,0)\n" +
                    "Appears at t = 5\n" +
                    "Disappears at t = 50\n" +
                    "Name: oval1\n" +
                    "Type: oval\n" +
                    "Center: (100.0,150.0), X radius: 100.0, Y radius: 500.0, Color: (0,0,255)\n" +
                    "Appears at t = 5\n" +
                    "Disappears at t = 50\n" +
                    "Shape rect1 moves from (1.0,1.0) to (2.0,2.0) from t=1 to t=10\n" +
                    "Shape rect1 changes color from (255,200,0) to (255,255,255) from t=10 to t=20\n" +
                    "Shape rect1 scales from Width: 5, Height: 10 to Width: 15, Height: 12 from t=10 to t=20\n" +
                    "Shape oval1 moves from (50.0,50.0) to (100.0,100.0) from t=25 to t=100\n" +
                    "Shape oval1 changes color from (0,255,0) to (0,0,255) from t=25 to t=100\n",
            sb.toString());
  }

}