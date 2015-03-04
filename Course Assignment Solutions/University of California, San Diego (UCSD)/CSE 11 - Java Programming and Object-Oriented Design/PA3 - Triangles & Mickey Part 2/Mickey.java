/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  January 25, 2013
 * File:  Mickey.java
 * Sources of Help: The PA2 instructions posted on Ord's website.
 *
 * This class defines what a Mickey figure is (the 3 filled ovals) and what a
 * Mickey figure can do (what messages it can respond to). This class will
 * define a constructor to initialize a new Mickey object placing it on the
 * canvas back in the MickeyController along with the methods to determine if
 * the mouse pointer is contained in the Mickey figure, move the Mickey figure
 * some delta, remove the Mickey figure from the canvas, and flip the Mickey
 * figure.
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    Mickey
 * Purpose: The public class of this Java file (read description above).
 */

public class Mickey
{
  private static final int FACE_RADIUS = 50;    // radius of mickey face
  private static final int EAR_RADIUS = 30;     // radius of mickey ear
  private static final int EAR_OFFSET = 50;     // Center of each ear is this
                                                // offset up & over (x & y)
                                                // from center of face
  private static final int MAX_COLORS = 7;      // Maximum number of colors
  private static int colorNum = 0;              // Color number counter
  private static Color mickeyColor;             // Mickey color (default black)

  private static boolean isFlipped = false;     // Is Mickey flipped?

  private FilledOval face,                      // Mickey Face
                     ear1,                      // Mickey Ear 1
                     ear2;                      // Mickey Ear 2

  private DrawingCanvas canvas;

  /*
   * Name:       Mickey
   * Purpose:    Constructor for the Mickey class
   * Parameters: point:  Click location
   *             canvas: Drawing Canvas
   * Return:     void
   */

  public Mickey( Location point, DrawingCanvas canvas )
  {
    // Draw Face
    face = new FilledOval( point.getX() - FACE_RADIUS, // Top Left X oval
                           point.getY() - FACE_RADIUS, // Top Left Y oval
                           2 * FACE_RADIUS,            // width
                           2 * FACE_RADIUS,            // height
                           canvas);                    // canvas

    // Draw Left Ear
    ear1 = new FilledOval( point.getX() - EAR_OFFSET - EAR_RADIUS,
                           point.getY() - EAR_OFFSET - EAR_RADIUS,
                           2 * EAR_RADIUS,
                           2 * EAR_RADIUS,
                           canvas);

    // Draw Right Ear
    ear2 = new FilledOval( point.getX() + EAR_OFFSET - EAR_RADIUS,
                           point.getY() - EAR_OFFSET - EAR_RADIUS,
                           2 * EAR_RADIUS,
                           2 * EAR_RADIUS,
                           canvas);
  }


  /*
   * Name:       contains
   * Purpose:    See if the click location is within the Mickey object
   * Parameters: point: Click location
   * Return:     boolean (contains)
   */

  public boolean contains( Location point )
  {
    // If user's click location is within Mickey:
    if(face.contains(point)||ear1.contains(point)||ear2.contains(point))
    {
      return true;  // Return a value of "true" (yes)
    }

    else // If user's click location is NOT within Mickey
    {
      return false; // Return a value of "false" (no)
    }
  }


  /*
   * Name:       move
   * Purpose:    Move the Mickey object some delta X and Y
   * Parameters: dx: How much it is moved in the X direction (DeltaX)
   *             dy: How much it is moved in the Y direction (DeltaY)
   * Return:     void
   */

  public void move( double dx, double dy )
  {
    face.move(dx,dy);  // Move face
    ear1.move(dx,dy);  // Move left ear
    ear2.move(dx,dy);  // Move right ear
  }


  /*
   * Name:       removeFromCanvas
   * Purpose:    Remove the Mickey object from the canvas
   * Parameters: N/A
   * Return:     void
   */

  public void removeFromCanvas()
  {
    face.removeFromCanvas(); // Remove face
    ear1.removeFromCanvas(); // Remove left ear
    ear2.removeFromCanvas(); // Remove right ear
    colorNum = 0;            // Set color to default (black)
    mickeyColor = Color.BLACK;
    isFlipped = false;
  }


  /*
   * Name:       flip
   * Purpose:    Flip the Mickey object vertically
   * Parameters: N/A
   * Return:     void
   */

  public void flip()
  {
    double dy = FACE_RADIUS + EAR_OFFSET; // Set Y displacement of ears on flip
 
    if(!isFlipped) // If Mickey is right side up
    {  
      ear1.move(0,dy);  // Flip left ear
      ear2.move(0,dy);  // Flip right ear
      isFlipped = true; // Mickey is now upside down
    }

    else           // If Mickey is upside down
    {
      ear1.move(0, -1 * dy); // Flip left ear
      ear2.move(0, -1 * dy); // Flip right ear
      isFlipped = false;     // Mickey is now right side up
    }

    ++colorNum; // Increment the color number counter
    switch(colorNum % MAX_COLORS) // 
    {
      case 0: mickeyColor = Color.BLACK;   // 0 ---> Black
              break;
      case 1: mickeyColor = Color.RED;     // 1 ---> Red
              break;
      case 2: mickeyColor = Color.ORANGE;  // 2 ---> Orange
              break;
      case 3: mickeyColor = Color.YELLOW;  // 3 ---> Yellow
              break;
      case 4: mickeyColor = Color.BLUE;    // 4 ---> Blue
              break;
      case 5: mickeyColor = Color.GREEN;   // 5 ---> Green
              break;
      case 6: mickeyColor = Color.MAGENTA; // 6 ---> Magenta
              break;
    }

    face.setColor(mickeyColor); // Set face to new Mickey Color
    ear1.setColor(mickeyColor); // Set left ear to new Mickey Color
    ear2.setColor(mickeyColor); // Set right  to new Mickey Color
  }
} // End of public class Mickey
