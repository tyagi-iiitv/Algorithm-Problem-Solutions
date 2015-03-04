/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  Chaser.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This is the Chaser object type. It us a red circle. Its behavior is to move
 * TOWARDS the critter that it is closest to. It should never chase another
 * Chaser.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Name:    Chaser
 * Purpose: The public class of this Java file (Chaser class)
 */

public class Chaser extends Critter
{
  private FilledOval chaserGraphic; // The Chaser Graphic

  public Chaser(Location loc,int size,DrawingCanvas canvas,int canvasHeight)
  {
    super(loc,size,canvas,canvasHeight);

    double locX = loc.getX();       // Store Location X
    double locY = loc.getY();       // Store Location Y

    // Create Chaser (Red FilledOval)
    chaserGraphic = new FilledOval(locX - (double)size/2,
                                   locY - (double)size/2,
                                   size, size, canvas);
    chaserGraphic.setColor(Color.RED);
  }

  /*
   * Name:       kill
   * Purpose:    Remove Chaser from canvas
   * Parameters: N/A
   * Return:     void
   */

  public void kill()
  {
    if(chaserGraphic != null)           // If there is a Chaser Graphic
    {
      chaserGraphic.removeFromCanvas(); // Remove it
      chaserGraphic = null;             // There is no more Chaser Graphic
    }
  }

  /*
   * Name:       reactTo
   * Purpose:    Define how a Chaser is going to react to its nearest other
   *             critter (chase all except for other chasers and imitators
   *             imitating chasers)
   * Parameters: other: The other (closest) critter
   * Return:     void
   */

  public void reactTo(Critter other)
  {
    if(chaserGraphic != null && other != null)    // If everything is there
    {
      double shortestDistance = Double.MAX_VALUE; // Max "short" distance
      int    shortestPath = -1;

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
        if(other.getLoc().distanceTo(locs[i]) < shortestDistance)
        {
          shortestDistance = other.getLoc().distanceTo(locs[i]);
          shortestPath = i;
        }
      }

      int dx = 0, dy = 0;
      switch(shortestPath)
      {
        case 0:  dx = 0; dy = 1;
                 break;
        case 1:  dx = 1; dy = 1;
                 break;
        case 2:  dx = 1; dy = 0;
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
        default: System.out.println("Something went wrong, Chaser Move");
                 break;
      }
      chaserGraphic.move(dx,dy); // Move Chaser the shortest path
      loc = locs[shortestPath];  // Update Chaser location

      // IF CHASER GOES TOO FAR, GO BACK! (EDGES)
      if(loc.getX() - (double)size/2 < 0 ||
         loc.getX() + (double)size/2 > canvasWidth ||
         loc.getY() - (double)size/2 < 0 ||
         loc.getY() + (double)size/2 > canvasHeight)
      {
        chaserGraphic.move(-dx,-dy);
        loc = new Location(loc.getX() - dx, loc.getY() - dy);
      }
    }
  }

  /*
   * Name:       actionPerformed
   * Purpose:    Clear the Random when Clear is clicked
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
} // End of public class Chaser extends Critter
