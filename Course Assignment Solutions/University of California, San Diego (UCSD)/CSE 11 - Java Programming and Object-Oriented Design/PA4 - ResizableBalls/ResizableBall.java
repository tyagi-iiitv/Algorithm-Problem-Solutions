/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 1, 2013
 * File:  ResizableBall.java
 * Sources of Help: The PA4 instructions posted on Ord's website.
 *
 * This file is to be used hand-in-hand with the main program
 * (ResizableBallController.java). This class's purpose is to define the
 * ResizableBall object (size, color, how it grows, etc).
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    ResizableBall
 * Purpose: The public class of this Java file (read description above).
 */

public class ResizableBall extends ActiveObject
{
  private static final int DELAY_TIME = 50; // Pause delay time
  private static final int RESIZE     = 2;  // How much size is changed

  private double           origBallSize;    // Original ball size

  private Line             hLine,           // Horizontal Line (x-axis)
                           vLine;           // Vertical Line (y-axis)

  private FilledOval       ballGraphic;     // The ResizableBall

  /*
   * Name:       ResizableBall
   * Purpose:    This constructor defines the ResizableBall object (starting
   *             point, size (diameter), canvas, and axes).
   * Parameters: xLoc:   X-coordinate of center of ResizableBall (click point)
   *             yLoc:   Y-coordinate of center of ResizableBall (click point)
   *             size:   Starting diameter of ResizableBall
   *             canvas: The canvas that ResizableBall is drawn on
   *             hLine:  Horizontal Line on canvas (x-axis)
   *             vLine:  Vertical Line on canvas (y-axis)
   * Return:     N/A
   */

  public ResizableBall( double xLoc, double yLoc, double size,
                        DrawingCanvas canvas, Line hLine, Line vLine )
  {
    this.hLine = hLine;      // Assign the hLine here as the hLine passed
    this.vLine = vLine;      // Assign the vLine here as the vLine passed

    ballGraphic = new FilledOval( xLoc - size/2, yLoc - size/2, // create ball
                                  size, size,
                                  canvas);
    setColors(hLine, vLine); // Set colors based on what quadrant ball is in

    start();                 // Run the run() method
  }

  /*
   * Name:       run
   * Purpose:    This is the method that is run when start(); is called in this
   *             class. It controls the growing/shrinking of the turtle (when
   *             to grow/shrink, how to go about growing/shrinking, etc) as
   *             well as controls updating the turtle's color if/when the user
   *             moves the axes. There is a delay between resizing (in other
   *             words, the resizing is not continuous) to give the object
   *             the illusion of being in fluid motion. I used recursion
   *             (calling run() at the end of itself) to keep it going
   *             continuously.
   * Parameters: N/A
   * Return:     void
   */

  public void run()
  {
    boolean growing = true;                // Ball starts as growing
    origBallSize = ballGraphic.getWidth(); // Store original ball size

    while(growing) // If ball is growing:
    {
      ballGraphic.setWidth(ballGraphic.getWidth()+RESIZE);   // Increase Width
      ballGraphic.setHeight(ballGraphic.getHeight()+RESIZE); // Increase Height
      ballGraphic.move(-RESIZE/2,-RESIZE/2);                 // Recenter

      if(ballGraphic.getWidth() == origBallSize * 2)         // If ball max
      {
        growing = false;                                     // Stop growing
      }

      if(ResizableBallController.hLineMoved ||               // If the axes are
         ResizableBallController.vLineMoved)                 // moved:
      {
        setColors(hLine, vLine);                             // Set colors
      }

      pause(DELAY_TIME);                                     // Pause
    }

    while(!growing) // If the ball is shrinking:
    {
      ballGraphic.setWidth(ballGraphic.getWidth()-RESIZE);   // Decrease Width
      ballGraphic.setHeight(ballGraphic.getHeight()-RESIZE); // Decrease Height
      ballGraphic.move(RESIZE/2,RESIZE/2);                   // Recenter

      if(ballGraphic.getWidth() == origBallSize)             // If ball min
      {
        growing = true;                                      // Stop shrinking
      }

      if(ResizableBallController.hLineMoved ||               // If the axes are
         ResizableBallController.vLineMoved)                 // moved:
      {
        setColors(hLine, vLine);                             // Set colors
      }

      pause(DELAY_TIME);                                     // Pause
    }

    run(); // YAY FOR RECURSION! :) Thanks Ord!
  }

  /*
   * Name:       run
   * Purpose:    This is the method that I wrote to try to neaten up the code
   *             a bit. Instead of writing the checks and specifications of
   *             the color assignment within the run() method, I created its
   *             own method to be called when I want to do the color assignment
   *             stuff.
   * Parameters: N/A
   * Return:     void
   */

  public void setColors( Line hLine, Line vLine)
  {
    boolean upperQuadrants = false, // Upper Quadrants (I and II)
            rightQuadrants = false; // Right Quadrants (I and IV)

    if((ballGraphic.getLocation()).getY() + // If ball center is above hLine
        ballGraphic.getWidth()/2 <
       (hLine.getStart()).getY())
    {
      upperQuadrants = true;                // Ball is in an Upper Quadrant
    }

    if((ballGraphic.getLocation()).getX() + // If ball center is right of vLine
        ballGraphic.getWidth()/2 >
       (vLine.getStart()).getX())
    {
      rightQuadrants = true;                // Ball is in a Right Quadrant
    }

    if(upperQuadrants && rightQuadrants)    // Quadrant I (upper right)
    {
      ballGraphic.setColor(Color.MAGENTA);  // Set color to Magenta
    }

    if(upperQuadrants && !rightQuadrants)   // Quadrant II (upper left)
    {
      ballGraphic.setColor(Color.CYAN);     // Set color to Cyan
    }

    if(!upperQuadrants && !rightQuadrants)  // Quadrant III (lower left)
    {
      ballGraphic.setColor(Color.YELLOW);   // Set color to Yellow
    }

    if(!upperQuadrants && rightQuadrants)   // Quadrant IV (lower right)
    {
      ballGraphic.setColor(Color.BLACK);    // Set color to Black
    }
  }
} // End of public class ResizableBall extends ActiveObject
