/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 1, 2013
 * File:  ResizableBallController.java
 * Sources of Help: The PA4 instructions posted on Ord's website.
 *
 * The applet starts as just an X and a Y axis that can be clicked on and
 * moved around. On a mouse click anywhere in the canvas, a ball is created
 * that grows to 2x its original size, then shrinks back to its original size,
 * then grows back to 2x its original size, etc. Depending on the quadrant
 * (Upper-Left, Upper-Right, Lower-Left, Lower-Right), the ball has a specific
 * color. If the axes are moved, resulting in a ball being in a different
 * quadrant, that ball updates color to maintain the quadrant-color
 * correlation. When the canvas is resized, the horizontal line maintains its
 * Y value and the vertical line maintains its X value (in other words, if the
 * canvas is extended or shrunk in one direction, the corresponding line
 * extends or shrinks to touch the canvas edge).
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    ResizableBallController
 * Purpose: The public class of this Java file (read description above).
 */

public class ResizableBallController extends WindowController
{
  private static final int MARGIN_HORIZONTAL = 5;  // Horizontal Margin
  private static final int MARGIN_VERTICAL   = 5;  // Vertical Margin
  private static final int BALL_SIZE         = 50; // Ball Diameter

  private double           canvasWidth,            // Canvas Width
                           canvasHeight;           // Canvas Height

  private boolean          hLineGrabbed = false,   // Is H Line grabbed?
                           vLineGrabbed = false;   // Is V Line grabbed?

  public static boolean    hLineMoved   = false,   // Was H Line moved?
                           vLineMoved   = false;   // Was V Line moved?

  private Line             hLine,                  // Horizontal Line
                           vLine;                  // Vertical Line

  private Location         lastPt;                 // Last mouse position

  /*
   * Name:       begin
   * Purpose:    This is the method that is run when the applet starts. It
   *             sets up the canvas with the vertical and horizontal lines
   *             (the axes).
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    canvasWidth  = canvas.getWidth();              // Store canvas width
    canvasHeight = canvas.getHeight();             // Store canvas height

    // Create Horizontal Line
    hLine = new Line( 0, canvasHeight/2,           // Left Point
                      canvasWidth, canvasHeight/2, // Right Point
                      canvas);                     // Canvas

    // Create Vertical Line
    vLine = new Line( canvasWidth/2, 0,            // Top Point
                      canvasWidth/2, canvasHeight, // Bottom Point
                      canvas);                     // Canvas
  }

  /* 
   * Name:       onMousePress
   * Purpose:    This is the method that is run when the user presses (not
   *             necessarily a click, but a press specifically) the mouse. It
   *             checks if the user has pressed on one of the lines (or both,
   *             if the intersection is clicked). If so, the toggle is set to
   *             true (so when a user moves the mouse, the line(s) move).
   * Parameters: pt: Press location
   * Return:     void
   */

  public void onMousePress( Location pt ) // When mouse is pressed:
  {
    if(pt.getY() == (hLine.getStart()).getY()) // If press is on hLine:
    {
      hLineGrabbed = true;                     // hLine is grabbed
    }

    if(pt.getX() == (vLine.getStart()).getX()) // If press is on vLine:
    {
      vLineGrabbed = true;                     // vLine is grabbed
    }

    lastPt = pt;                               // Set the point to lastPt
  }

  /*
   * Name:       onMouseRelease
   * Purpose:    This is the method that is run when the user, after having
   *             pressed the mouse, releases it. It sets both "line grabbed"
   *             toggles to false (so when a user moves the mouse, the line(s)
   *             no longer move).
   * Parameters: pt: Release location
   * Return:     void
   */

  public void onMouseRelease( Location pt ) // When mouse is released:
  {
    hLineGrabbed = vLineGrabbed = false;    // Neither line is grabbed
    hLineMoved   = vLineMoved   = false;    // Neither line is moved anymore
  }

  /*
   * Name:       onMouseDrag
   * Purpose:    This is the method that is run when the user, after having
   *             pressed the mouse, moves it without releasing. If the user
   *             does a drag while one or both of the lines is grabbed, then
   *             the line(s) grabbed move with the user's mouse, and the lines
   *             are forced to remain within a horizontal and vertical margin,
   *             which are defined at the beginning of this class.
   * Parameters: pt: Drag location
   * Return:     void
   */

  public void onMouseDrag( Location pt ) // When mouse is dragged:
  {
    // If H Line was grabbed and drag is within the margins:
    if(hLineGrabbed && pt.getY() >= MARGIN_HORIZONTAL && // If hLine is grabbed
       pt.getY() <= canvasHeight - MARGIN_HORIZONTAL)    // and drag is in
    {                                                    // the margins:
      hLine.move(0, pt.getY() - lastPt.getY());          // Move hLine

      if(lastPt != pt)                                   // If the new point is
      {                                                  // different (moved):
        hLineMoved = true;                               // hLine was moved
      }

      else                                               // If new point is
      {                                                  // same (not moved):
        hLineMoved = false;                              // hLine not moved
      }
    }

    // If V Line was grabbed and drag is within the margins:
    if(vLineGrabbed && pt.getX() >= MARGIN_VERTICAL &&   // If vLine is grabbed
       pt.getX() <= canvasWidth - MARGIN_VERTICAL)       // and drag is in
    {                                                    // the margins:
      vLine.move(pt.getX() - lastPt.getX(), 0);          // Move vLine

      if(lastPt != pt)                                   // If the new point is
      {                                                  // different (moved):
        vLineMoved = true;                               // vLine was moved
      }

      else                                               // If new point is
      {                                                  // same (not moved):
        vLineMoved = false;                              // vLine not moved
      }
    }
    lastPt = pt;                                         // Set the point to
  }                                                      // lastPt

  /*
   * Name:       onMouseClick
   * Purpose:    This is the method that is run when the user clicks the mouse
   *             (a click is when the mouse is pressed AND released with no
   *             movement). It creates a ResizableBall object (the description
   *             of the "ResizableBall" is given at the beginning of this
   *             file).
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMouseClick( Location pt ) // When mouse is clicked:
  {
    new ResizableBall( pt.getX(), pt.getY(), // Create a new ResizableBall
                       BALL_SIZE, canvas,    // with the given specs.
                       hLine, vLine);
  }

  /*
   * Name:       paint
   * Purpose:    This is the method that is run when the canvas is resized. It
   *             extends (or shrinks) the line(s) that hit the moved canvas
   *             side so that both lines continue to perfectly hit the canvas
   *             edge.
   * Parameters: g: Graphics
   * Return:     void
   */

  public void paint( java.awt.Graphics g )
  {
    super.paint( g ); // Ord gave us this

    double hLineY = (hLine.getStart()).getY(); // Record H Line's Y value
    double vLineX = (vLine.getStart()).getX(); // Record V Line's X value

    hLine.setEndPoints(0, hLineY, canvas.getWidth(), hLineY);  // Redraw H Line
    vLine.setEndPoints(vLineX, 0, vLineX, canvas.getHeight()); // Redraw V Line
  }
} // End of public class ResizableBallController extends WindowController
