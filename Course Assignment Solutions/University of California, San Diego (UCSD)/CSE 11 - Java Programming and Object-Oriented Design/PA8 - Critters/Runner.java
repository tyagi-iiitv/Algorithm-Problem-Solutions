/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  Runner.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This is the Runner object type. It is a green square. Its behavior is to
 * move AWAY FROM the critter that is closest to it. If it touches any of the
 * four borders of the canvas, it should be randomly moved to a different
 * location inside the canvas.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Name:    Runner
 * Purpose: The public class of this Java file (Runner class)
 */

public class Runner extends Critter
{
  private FilledRect runnerGraphic; // The Runner Graphic

  public Runner(Location loc,int size,DrawingCanvas canvas,int canvasHeight)
  {
    super(loc,size,canvas,canvasHeight);

    double locX = loc.getX();       // Store Location X
    double locY = loc.getY();       // Store Location Y

    // CREATE RUNNER GRAPHIC (Green FilledRect Square)
    runnerGraphic = new FilledRect(locX - (double)size/2,
                                   locY - (double)size/2,
                                   size, size, canvas);
    runnerGraphic.setColor(Color.GREEN);
  }

  /*
   * Name:       kill
   * Purpose:    Remove Runner from canvas
   * Parameters: N/A
   * Return:     void
   */

  public void kill()
  {
    if(runnerGraphic != null)           // If there is a Runner
    {
      runnerGraphic.removeFromCanvas(); // Remove it
      runnerGraphic = null;             // There is no more Runner
    }
  }

  /*
   * Name:       reactTo
   * Purpose:    Define how a Runner is going to react to its nearest other
   *             critter
   * Parameters: other: The other (closest) critter
   * Return:     void
   */

  public void reactTo(Critter other)
  {
    if(runnerGraphic != null && other != null) // If Runner and Other exist
    {
      double furthestDistance = 0;             // Initialize Furthest Distance
      int    furthestPath = -1;                // Initialize Furthest Path

      // CREATE POTENTIAL LOCATIONS
      Location[] locs = new Location[8];
      locs[0] = new Location(loc.getX() + 0, loc.getY() + 1);
      locs[1] = new Location(loc.getX() + 1, loc.getY() + 1);
      locs[2] = new Location(loc.getX() + 1, loc.getY() + 0);
      locs[3] = new Location(loc.getX() + 1, loc.getY() - 1);
      locs[4] = new Location(loc.getX() + 0, loc.getY() - 1);
      locs[5] = new Location(loc.getX() - 1, loc.getY() - 1);
      locs[6] = new Location(loc.getX() - 1, loc.getY() + 0);
      locs[7] = new Location(loc.getX() - 1, loc.getY() + 1);

      // FIND SHORTEST PATH/DISTANCE
      for(int i = 0; i < locs.length; i++)
      {
        if(other.getLoc().distanceTo(locs[i]) > furthestDistance)
        {
          furthestDistance = other.getLoc().distanceTo(locs[i]);
          furthestPath = i;
        }
      }

      int dx = 0, dy = 0;
      switch(furthestPath)
      {
        case 0:  dx = 0; dy = 1;
                 break;
        case 1:  dx = 1; dy = 1;
                 break;
        case 2:  dx = 0; dy = 0;
                 break;
        case 3:  dx = 1; dy = -1;
                 break;
        case 4:  dx = 0; dy = -1;
                 break;
        case 5:  dx = -1; dy = -1;
                 break;
        case 6:  dx = -1; dy = 0;
                 break;
        case 7:  dx = -1; dy = 1;
                 break;
        default: System.out.println("Something went wrong, Runner Move");
                 break;
      }

      // Move Runner and Update Location
      runnerGraphic.move(dx,dy);
      loc = locs[furthestPath];

      // If Runner hits edge of canvas
      if(loc.getX() - (double)size/2 <= 0 ||
         loc.getX() + (double)size/2 >= canvasWidth ||
         loc.getY() - (double)size/2 <= 0 ||
         loc.getY() + (double)size/2 >= canvasHeight)
      {
        // Move to random location on canvas
        RandomIntGenerator randomX = new RandomIntGenerator(0,
                                            canvasWidth-size);
        RandomIntGenerator randomY = new RandomIntGenerator(0,
                                           canvasHeight-size);
        int newX = randomX.nextValue();
        int newY = randomY.nextValue();
        runnerGraphic.moveTo(newX,newY);
        loc = new Location(newX + (double)size/2, newY + (double)size/2);
      }
    }
  }

  /*
   * Name:       actionPerformed
   * Purpose:    Clear the Runner when Clear is clicked
   * Parameters: e: ActionEvent (the Clear Button click)
   * Return:     void
   */

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Clear"))
    {
      kill();
    }
  }
} // End of public class Runner extends Critter
