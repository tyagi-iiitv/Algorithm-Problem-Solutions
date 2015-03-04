/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  January 25, 2013
 * File:  MickeyController.java
 * Sources of Help: The PA2 instructions posted on Ord's website.
 *
 * This applet creates a Mickey object (made of 3 ovals) that can be moved
 * around by a user's mouse input. It can be dragged, flipped, color-changed,
 * and more.
 *
 * This is the main GUI controller class that handles al mouse events and
 * controls what we see on the canvas. Methods defined in this class include:
 * begin(), onMouseClick(), onMouseDrag(), onMousePres(), onMouseRelease(),
 * onMouseExit(), onMouseEnter().
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    MickeyController
 * Purpose: The public class that runs the entire java applet
 */

public class MickeyController extends WindowController
{
  private static final int INSTR1_X = 50;       // x coord of 1st instruction
  private static final int INSTR1_Y = 50;       // y coord of 1st instruction
  private static final int INSTR2_X = INSTR1_X; // x coord of 2nd instruction
  private static final int INSTR2_Y = 70;       // y coord of 2nd instruction
  private static final int INSTR3_X = INSTR2_X; // x coord of 3rd instruction
  private static final int INSTR3_Y = 90;       // y coord of 3rd instruction

  private static final int FLIP_PRESS_THRESHOLD = 500; // Half a second

  private Text instr1,                          // Instructions Line 1
               instr2,                          // Instructions Line 2
               instr3;                          // Instructions Line 3

  private FilledOval face,                      // Mickey Face
                     ear1,                      // Mickey Ear 1
                     ear2;                      // Mickey Ear 2

  private Location lastPt;                      // Last  mouse position

  private boolean isInstr       = true;         // Are instructions displayed?
  private boolean isMickey      = false;        // Is there a Mickey?
  private boolean mickeyGrabbed = false;        // Is Mickey grabbed?
  private boolean mickeyDragged = false;        // Was Mickey dragged?

  private Mickey mickey;                        // Mickey object
  private Timer timer;                          // Timer object

  /*
   * Name:       begin
   * Purpose:    Start the applet (show the instructions)
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    instr1 = new Text("", INSTR1_X, INSTR1_Y, canvas); // create instructions1
    instr2 = new Text("", INSTR2_X, INSTR2_Y, canvas); // create instructions2
    instr3 = new Text("", INSTR3_X, INSTR3_Y, canvas); // create instructions3
    
    // Set text for instructions
    instr1.setText("Click to display a Mickey silhouette centered " +
                    "at the mouse click.");

    instr2.setText("Mouse press in any part of the image and drag " +
                   "to move image around.");

    instr3.setText("Mouse click in any part of the image with a mouse " +
                   "press for more than 0.5 seconds to flip image.");

    isInstr = true;           // Set instruction display boolean to true
  }

  /*
   * Name:       onMousePress
   * Purpose:    On a mouse press, do the following:
   *             If instructions are shown, hide them
   *             If there is no Mickey, draw one
   *             If there is a Mickey, if it is pressed, consider it "grabbed"
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMousePress( Location pt ) // When mouse is pressed:
  {
    mickeyDragged = false;    // Set "Was Mickey Dragged?" back to false

    if(isInstr)               // If the instructions are showing:
    {
      instr1.hide();          // Hide the instructions
      instr2.hide();
      instr3.hide();
      isInstr = false;        // Set instruction display boolean to false
    }

    if(!isMickey)             // If there is no Mickey when mouse is pressed:
    {
      mickey = new Mickey( pt, canvas ); // Create a Mickey
      isMickey = true;        // Set Mickey display boolean to true
    }

    else                      // If there is a Mickey
    {
      mickeyGrabbed = mickey.contains( pt ); // Check if user clicked on it
      
      if(mickeyGrabbed)       // If user clicked on Mickey:
      {
        timer = new Timer();  // Start a new timer
      }
    }

    lastPt = pt;              // Set the point that was pressed as "lastPt"
  }

  /*
   * Name:       onMouseRelease
   * Purpose:    On a mouse release, do the following:
   *             If Mickey was grabbed, now consider it not grabbed.
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMouseRelease( Location pt ) // When mouse is released:
  {
    if(mickeyGrabbed)         // If Mickey was grabbed:
    {
      // If Mickey wasn't dragged AND user held long enough:
      if(!mickeyDragged && timer.elapsedMilliseconds() >= FLIP_PRESS_THRESHOLD)
      {
        mickey.flip();        // Flip Mickey
      }
      mickeyGrabbed = false;  // Mickey is now NOT grabbed
    }
  }

  /*
   * Name:       onMouseDrag
   * Purpose:    On a mouse drag, do the following:
   *             If Mickey was grabbed, move it with the mouse
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMouseDrag( Location pt ) // When mouse is pressed and dragged:
  {
    if(mickeyGrabbed) // If Mickey was grabbed when mouse was dragged:
    {
      double dx = pt.getX() - lastPt.getX(); // Create x displacement
      double dy = pt.getY() - lastPt.getY(); // Create y displacement
      mickey.move(dx,dy);                    // Move Mickey dx and dy
      mickeyDragged = true;                  // Mickey was dragged (don't flip)
      lastPt = pt;                           // Set current point as last point
    }
  }

  /*
   * Name:       onMouseExit
   * Purpose:    When the mouse exits the canvas, do the following:
   *             If there is a Mickey, remove it from the canvas
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMouseExit( Location pt ) // When mouse leaves the canvas:
  {
    if(isMickey)                 // If there was a Mickey before mouse leaves:
    {
      mickey.removeFromCanvas(); // Remove Mickey from the canvas
      isMickey = false;          // Set Mickey display boolean to false
    }
  }

  /*
   * Name:       onMouseEnter
   * Purpose:    When the mouse enters the canvas, do the following:
   *             If the instructions are hidden, show them
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMouseEnter( Location pt ) // When mouse enters the canvas:
  {
    if(!isInstr)               // If the instructions are hidden:
    {
      instr1.show();           // Show the instructions
      instr2.show();
      instr3.show();

      isInstr = true;          // Set instructions display boolean to true
    }
  }
} // End of public class MickeyController extends WindowController
