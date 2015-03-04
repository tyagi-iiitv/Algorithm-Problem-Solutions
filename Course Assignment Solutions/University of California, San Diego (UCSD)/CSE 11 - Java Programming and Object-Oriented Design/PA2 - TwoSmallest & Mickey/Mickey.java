/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  January 18, 2013
 * File:  Mickey.java
 * Sources of Help: The PA2 instructions posted on Ord's website.
 *
 * This applet will create a Mickey Mouse face that can be dragged and
 * reacts to different types of user mouse commands.
 */

/*
 * Name:    Mickey
 * Purpose: The public class of this program.
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    Mickey
 * Purpose: The public class that runs the entire java applet
 */

public class Mickey extends WindowController
{
  private static final int INSTR1_X = 50;       // x coord of 1st instruction
  private static final int INSTR1_Y = 50;       // y coord of 1st instruction
  private static final int INSTR2_X = INSTR1_X; // x coord of 2nd instruction
  private static final int INSTR2_Y = 70;       // y coord of 2nd instruction
  private static final int FACE_RADIUS = 50;    // radius of mickey face
  private static final int EAR_RADIUS = 30;     // radius of mickey ear
  private static final int EAR_OFFSET = 50;     // Center of each ear is this
                                                // offset up & over (x & y)
                                                // from center of face

  private Text instr1,                          // Instructions Line 1
               instr2,                          // Instructions Line 2
               debug;                           // Niema's debugging print

  private double ptX,                           // x coord of click
                 ptY;                           // y coord of click

  private FilledOval face,                      // Mickey Face
                     ear1,                      // Mickey Ear 1
                     ear2;                      // Mickey Ear 2

  private Location lastPt;                      // Last  mouse position

  private boolean isInstr       = true;         // Are instructions displayed?
  private boolean isMickey      = false;        // Is there a Mickey?
  private boolean mickeyGrabbed = false;        // Is Mickey grabbed?

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
    debug  = new Text("", 2 * INSTR1_X, 2 * INSTR1_Y, canvas);  // Niema debug
    
    // Set text for instructions
    instr1.setText("Click to display a Mickey silhouette centered " +
                    "at the mouse click.");

    instr2.setText("Mouse press in any part of the image and drag " +
                   "to move image around.");
    isInstr = true;           // Set instruction display boolean to true

//    debug.setText("Instructions are shown.");
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
    ptX = pt.getX();          // Assign X coordinate to ptX
    ptY = pt.getY();          // Assign Y coordinate to ptY

    if(isInstr)               // If the instructions are showing:
    {
      instr1.hide();          // Hide the instructions
      instr2.hide();
      isInstr = false;        // Set instruction display boolean to false
    }

    if(!isMickey)             // If there is no Mickey when mouse is pressed:
    {
      // Draw Face
      face = new FilledOval(ptX - FACE_RADIUS, // Top Left X oval
                            ptY - FACE_RADIUS, // Top Left Y oval
                            2 * FACE_RADIUS,   // width
                            2 * FACE_RADIUS,   // height
                            canvas);           // canvas

      // Draw Ear 1 (left ear)
      ear1 = new FilledOval(ptX - EAR_OFFSET - EAR_RADIUS,
                            ptY - EAR_OFFSET - EAR_RADIUS,
                            2 * EAR_RADIUS,
                            2 * EAR_RADIUS,
                            canvas);

      // Draw Ear 2 (right ear)
      ear2 = new FilledOval(ptX + EAR_OFFSET - EAR_RADIUS,
                            ptY - EAR_OFFSET - EAR_RADIUS,
                            2 * EAR_RADIUS,
                            2 * EAR_RADIUS,
                            canvas);

      isMickey = true;        // Set Mickey display boolean to true

//      debug.setText("Mickey has been drawn.");
    }

    else                      // If there is a Mickey
    {
      // If user pressed in the face or ears:
      if(face.contains(pt)||ear1.contains(pt)||ear2.contains(pt))
      {
        mickeyGrabbed = true; // Mickey was grabbed
//        debug.setText("Mickey has been grabbed.");
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
      face.move(pt.getX() - lastPt.getX(),  // move face
                pt.getY() - lastPt.getY());

      ear1.move(pt.getX() - lastPt.getX(),  // move left ear
                pt.getY() - lastPt.getY());

      ear2.move(pt.getX() - lastPt.getX(),  // move right ear
                pt.getY() - lastPt.getY());

      lastPt = pt;                          // set current point as last point

//      debug.setText("Mickey is moved.");
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
    if(isMickey)               // If there was a Mickey before mouse leaves:
    {
      face.removeFromCanvas(); // remove face
      ear1.removeFromCanvas(); // remove left ear
      ear2.removeFromCanvas(); // remove right ear
      isMickey = false;        // Set Mickey display boolean to false
    }

//    debug.setText("Mickey is cleared.");
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

      isInstr = true;          // Set instructions display boolean to true

//    debug.setText("Instructions are shown.");
    }
  }
} // End of public class Mickey extends WindowController
