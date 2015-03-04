/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 1, 2013
 * File:  ResizableBall.java
 * Sources of Help: The PA4 instructions posted on Ord's website.
 *
 * This file is to be used hand-in-hand with the main program
 * (ResizableBallController.java). This class's purpose is to define the
 * ResizableBall object (size, color, how it grows, etc). It also listens
 * for speed slider changes and button presses and has the balls react
 * accordingly.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * Name:    ResizableBall
 * Purpose: The public class of this Java file (read description above).
 */

public class ResizableBall extends ActiveObject
                           implements ActionListener, ChangeListener,
                                      MouseListener, MouseMotionListener,
                                      ComponentListener
{
  private static final int DELAY_TIME = 50; // Pause delay time
  private static final int BALL_SIZE = ResizableBallController.BALL_SIZE;
  private static final int MAX_BALL_SIZE = 2*BALL_SIZE;
  private static final int MARGIN_HORIZONTAL =
                             ResizableBallController.MARGIN_HORIZONTAL;
  private static final int MARGIN_VERTICAL =
                             ResizableBallController.MARGIN_VERTICAL;

  private double           canvasHeight = ResizableBallController.canvasHeight,
                           canvasWidth = ResizableBallController.canvasWidth;

  private Line             hLine,           // Horizontal Line (x-axis)
                           vLine;           // Vertical Line (y-axis)

  private FilledOval       ballGraphic;     // The ResizableBall

  private int              speed = ResizableBallController.MIN_SPEED,
                           saveSpeed;

  private boolean          growing     = true,
                           ballGrabbed = false;
  private Location         pt, lastPt;

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
                        DrawingCanvas canvas, Line hLine, Line vLine,
                        int startSpeed )
  {
    this.hLine = hLine;      // Assign the hLine here as the hLine passed
    this.vLine = vLine;      // Assign the vLine here as the vLine passed
    speed = startSpeed;

    ballGraphic = new FilledOval( xLoc - size/2, yLoc - size/2, // create ball
                                  size, size,
                                  canvas);

    if(ResizableBallController.createActive)
    {
      startResize();
    }
    else
    {
      stopResize();
    }


    setColors(); // Set colors based on what quadrant ball is in
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
    boolean isCleared = false;
    double  halfSpeed;

    while(growing) // If ball is growing:
    {
      if(ballGraphic != null)
      {
        if(ballGraphic.getWidth() >= MAX_BALL_SIZE)           // If ball max
        {
          growing = false;                                    // Stop growing
        }

        halfSpeed = speed * 0.5;      // Set HalfSpeed
        ballGraphic.setWidth(ballGraphic.getWidth() + speed); //IncreaseWidth
        ballGraphic.setHeight(ballGraphic.getHeight()+speed); //IncreaseHeight
        ballGraphic.move(-halfSpeed,-halfSpeed);              // Recenter

        if(ResizableBallController.hLineMoved ||              // If the axes
           ResizableBallController.vLineMoved &&              // moved
           ballGraphic != null)
        {
          setColors();                                        // Set colors
        }

        pause(DELAY_TIME);                                    // Pause
      }
    }

    while(!growing) // If the ball is shrinking:
    {
      if(ballGraphic != null)
      {
        if(ballGraphic.getWidth() <= BALL_SIZE)               // If ball min
        {
          growing = true;                                     //StopShrinking
        }

        halfSpeed = speed * 0.5;      // Set HalfSpeed
        ballGraphic.setWidth(ballGraphic.getWidth() - speed); //DecreaseWidth
        ballGraphic.setHeight(ballGraphic.getHeight()-speed); //DecreaseHeight
        ballGraphic.move(halfSpeed,halfSpeed);                // Recenter

        if(ResizableBallController.hLineMoved ||              // If the axes
           ResizableBallController.vLineMoved &&              // moved
           ballGraphic != null)
        {
          setColors();                            // Set colors
        }

        pause(DELAY_TIME);                                    // Pause
      }
    }

    if(!isCleared) // Stop repeating if the object was cleared
    {
      run(); // YAY FOR RECURSION! :) Thanks Ord!
    }
  }

  /* 
   * Name:       actionPerformed
   * Purpose:    This method listens for ActionEvents (in this case, button
   *             presses). If the Start button is pressed, start resizing. If
   *             the Stop button is pressed, stop resizing. If the Clear All
   *             button is pressed, remove all balls.
   * Parameters: e: ActionEvent (the button presses)
   * Return:     void
   */

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Start")) // If Start
    {
      startResize(); // Start the Resize
    }
    else if(e.getActionCommand().equals("Stop"))// If Stop
    {
      stopResize();  // Stop the Resize
    }
    else if(e.getActionCommand().equals("Clear All"))// If Clear All
    {
      if(ballGraphic != null)
      {
        ballGraphic.removeFromCanvas();    // Clear all balls
        ballGraphic = null;
      }
    }
  }

  /*
   * Name:       stateChanged
   * Purpose:    This method listens for ChangeEvents (in this case, slider
   *             sliding). The user moves the slider, and the balls adjust to
   *             the speed the slider is now set on.
   * Parameters: e: ChangeEvent (the slider changing)
   * Return:     void
   */

  public void stateChanged(ChangeEvent e)
  {
    if(speed == 0) // If stopped:
    {
      saveSpeed = ((JSlider)e.getSource()).getValue();
    }
    else           // If not stopped:
    {
      speed = ((JSlider)e.getSource()).getValue();
    }
  }

  /*
   * Name:       mousePressed
   * Purpose:    This method listens for a MouseEvent (the mouse press) and
   *             sets "lastPt" (the last point) to wherever the mouse was
   *             pressed.
   * Parameters: e: MouseEvent (the mouse press)
   * Return:     void
   */

  public void mousePressed(MouseEvent e)
  {
    lastPt = new Location(e.getX(),e.getY());
    if(ballGraphic != null)
    {
      ballGrabbed = ballGraphic.contains(lastPt);
    }
  }

  /*
   * Name:       mouseDragged
   * Purpose:    This method listens for a MouseEvent (the mouse drag). If a
   *             ball is pressed on, then the mouse is dragged, the ball is
   *             dragged with the mouse, keeping in mind a 5 pixel margin on
   *             all 4 sides of the canvas.
   * Parameters: e: MouseEvent (the mouse drag)
   * Return:     void
   */

  public void mouseDragged(MouseEvent e)
  {
    pt = new Location(e.getX(),e.getY());     // Save drag point

    double dx = pt.getX() - lastPt.getX();    // Set delta X
    double dy = pt.getY() - lastPt.getY();    // Set delta Y

    if(ballGraphic != null)
    {
      if(ballGrabbed &&        // If ball is clicked and
         pt.getX() >= MARGIN_VERTICAL &&               // left margin and
         pt.getX() <= canvasWidth - MARGIN_VERTICAL && // right margin and
         pt.getY() >= MARGIN_HORIZONTAL &&             // top margin and
         pt.getY() <= canvasHeight -MARGIN_HORIZONTAL) // bottom margin
      {
        ballGraphic.move(dx,dy);                // Move the ball dx and dy
      }

      setColors();
    }

    lastPt = pt;                              // Save current point as lastPt
  }

  /*
   * Name:       mouseReleased
   * Purpose:    This method listens for a MouseEvent (the mouse release). If
   *             the mouse is released, all balls are released.
   * Parameters: e: MouseEvent (the mouse release)
   * Return:     void
   */

  public void mouseReleased(MouseEvent e)
  {
    ballGrabbed = false;
  }

  /*
   * Name:       stopResize
   * Purpose:    Stop the balls from resizing.
   * Parameters: N/A
   * Return:     void
   */

  public void componentResized(ComponentEvent e)
  {
    double canvasDx = ((DrawingCanvas)e.getSource()).getWidth() -
                      canvasWidth;
    double canvasDy = ((DrawingCanvas)e.getSource()).getHeight() -
                      canvasHeight;

    ballGraphic.move(canvasDx/2, canvasDy/2);

    canvasWidth  = ((DrawingCanvas)e.getSource()).getWidth();
    canvasHeight = ((DrawingCanvas)e.getSource()).getHeight();
  }

  public void stopResize()
  {
    if(speed != 0) // If started,
    {
      saveSpeed = speed;
      speed = 0; // Save speed and set speed to 0
    }
  }

  /*
   * Name:       startResize
   * Purpose:    Start the resizing of the balls.
   * Parameters: N/A
   * Return:     void
   */

  public void startResize()
  {
    if(speed == 0) // If stopped,
    {
      speed = saveSpeed;
    }
  }

  /*
   * Name:       setColors
   * Purpose:    Set colors of the balls depending on which quadrant it's in
   *               Quadrant I:   Magenta
   *               Quadrant II:  Cyan
   *               Quadrant III: Yellow
   *               Quadrant IV:  Black
   * Parameters: hLine: The Horizontal Line on the canvas
   *             vLine: The Vertical Line on the canvas
   * Return:     void
   */

  public void setColors()
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

  // EMPTY METHODS
  public void mouseExited(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}
  public void componentHidden(ComponentEvent e){}
  public void componentShown(ComponentEvent e){}
  public void componentMoved(ComponentEvent e){}
} // End of public class ResizableBall extends ActiveObject
