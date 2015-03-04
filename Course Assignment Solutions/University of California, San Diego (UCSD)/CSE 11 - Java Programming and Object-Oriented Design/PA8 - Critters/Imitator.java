/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  Imitator.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This is the Imitator type object. It is a black triangle. Its behavior is to
 * mimic the movement of the critter that is closest to it. While imitating, it
 * should change its color to the color of the critter it is imitating.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Name:    Imitator
 * Purpose: The public class of this Java file (Imitator class)
 */

public class Imitator extends Critter
{
  private Line    line1,           // Down-Left Line of ImitatorGraphic
                  line2,           // Down-Right Line of ImitatorGraphic
                  line3;           // Bottom Line of ImitatorGraphic

  private boolean imitateChaser;   // Imitating a Chaser?

  private int     orientation = 1; // 1: Up; 2: Right; 3: Down; 4: Left

  public Imitator(Location loc,int size,DrawingCanvas canvas,int canvasHeight)
  {
    super(loc,size,canvas,canvasHeight);

    double locX = loc.getX();      // Store Location X
    double locY = loc.getY();      // Store Location Y

    // CREATE IMITATOR GRAPHIC (upright triangle)
    line1 = new Line(locX,
                     locY - (double)size/2,
                     locX - (double)size/2,
                     locY + (double)size/2,
                     canvas);
    line2 = new Line(locX,
                     locY - (double)size/2,
                     locX + (double)size/2,
                     locY + (double)size/2,
                     canvas);
    line3 = new Line(locX - (double)size/2,
                     locY + (double)size/2,
                     locX + (double)size/2,
                     locY + (double)size/2,
                     canvas);
  }

  /*
   * Name:       kill
   * Purpose:    Remove Imitator from canvas
   * Parameters: N/A
   * Return:     void
   */

  public void kill()
  {
    if(line1 != null)               // If there is an Imitator
    {
      line1.removeFromCanvas();     // Remove Line 1
      line2.removeFromCanvas();     // Remove Line 2
      line3.removeFromCanvas();     // Remove Line 3
      line1 = line2 = line3 = null; // There is no more Imitator
    }
  }

  /*
   * Name:       isChaser
   * Purpose:    Set "imitating chaser" boolean to true
   * Parameters: N/A
   * Return:     void
   */

  public void isChaser()
  {
    imitateChaser = true;
  }

  /*
   * Name:       notChaser
   * Purpose:    Set "imitating chaser" boolean to false
   * Parameters: N/A
   * Return:     void
   */

  public void notChaser()
  {
    imitateChaser = false;
  }

  /*
   * Name:       returnBool
   * Purpose:    Return the "imitator chaser" boolean (see if it's true/false)
   * Paramers:   void
   * Return:     imitateChaser: Boolean saying if imitator is imitating a
   *             chaser or not
   */

  public boolean returnBool()
  {
    return imitateChaser;
  }

  /*
   * Name:       reactTo
   * Purpose:    Define how an Imitator is going to react to its nearest other
   *             critter (copies the nearest critter)
   * Parameters: other: The other (closest) critter
   * Return:     void
   */

  public void reactTo(Critter other)
  {
    if(line1 != null && other != null) // If both critters exist
    {
      // IF CLOSEST IS A RUNNER
      if(!imitateChaser && other.getClass() == Runner.class)
      {
        // Set Imitator to Green
        line1.setColor(Color.GREEN);
        line2.setColor(Color.GREEN);
        line3.setColor(Color.GREEN);

        double furthestDistance = 0;   // Initialize furthest distance
        int    furthestPath = -1;      // Initialize furthest path

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
          default: System.out.println("Something went wrong, Imitator Move");
                   break;
        }

        line1.move(dx,dy);             // Move Line 1
        line2.move(dx,dy);             // Move Line 2
        line3.move(dx,dy);             // Move Line 2=3

        loc = locs[furthestPath];      // Update Imitator Location

        // If Imitator hits canvas edge
        if(loc.getX() - (double)size/2 <= 0 ||
           loc.getX() + (double)size/2 >= canvasWidth ||
           loc.getY() - (double)size/2 <= 0 ||
           loc.getY() + (double)size/2 >= canvasHeight)
        {
          // Teleport to a random location on canvas
          RandomIntGenerator randomX = new RandomIntGenerator(0,
                                              canvasWidth-size);
          RandomIntGenerator randomY = new RandomIntGenerator(0,
                                             canvasHeight-size);
          int newX = randomX.nextValue();
          int newY = randomY.nextValue();
          double randomDx = newX - loc.getX();
          double randomDy = newY - loc.getY();
          line1.move(randomDx,randomDy);
          line2.move(randomDx,randomDy);
          line3.move(randomDx,randomDy);
          loc = new Location(loc.getX() + randomDx, loc.getY() + randomDy);
        }
      }

      // IF CLOSEST IS A RANDOM
      else if(!imitateChaser && other.getClass() == Random.class)
      {
        // Set Imitator to Blue
        line1.setColor(Color.BLUE);
        line2.setColor(Color.BLUE);
        line3.setColor(Color.BLUE);

        // Find a random dx and dy between -10 and 10
        RandomIntGenerator randomX = new RandomIntGenerator(-10,10);
        RandomIntGenerator randomY = new RandomIntGenerator(-10,10);
        int dx = randomX.nextValue();
        int dy = randomY.nextValue();

        // Move Imitator dx and dy, and update location
        loc = new Location(loc.getX() + dx, loc.getY() + dy);
        line1.move(dx,dy);
        line2.move(dx,dy);
        line3.move(dx,dy);

        // If Imitator hits edge of canvas
        if(loc.getX() - (double)size/2 < 0 ||
           loc.getX() + (double)size/2 > canvasWidth ||
           loc.getY() - (double)size/2 < 0 ||
           loc.getY() + (double)size/2 > canvasHeight)
        {
          // Undo! Go back!
          line1.move(-dx,-dy);
          line2.move(-dx,-dy);
          line3.move(-dx,-dy);
          loc = new Location(loc.getX() - dx, loc.getY() - dy);
        }
      }

      // IF CLOSEST IS CHASER
      else if(imitateChaser)
      {
        // Set Imitator to Red
        line1.setColor(Color.RED);
        line2.setColor(Color.RED);
        line3.setColor(Color.RED);

        // If the other critter is NOT a chaser
        if(other.getClass() != Chaser.class)
        {
          double shortestDistance = Double.MAX_VALUE;
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
            default: System.out.println("Something went wrong, Imitator Move");
                     break;
          }
          line1.move(dx,dy);        // Move Shortest Path
          line2.move(dx,dy);
          line3.move(dx,dy);
          loc = locs[shortestPath]; // Update location

          // If go too far (hit canvas edge)
          if(loc.getX() - (double)size/2 < 0 ||
             loc.getX() + (double)size/2 > canvasWidth ||
             loc.getY() - (double)size/2 < 0 ||
             loc.getY() + (double)size/2 > canvasHeight)
          {
            // Undo! Go back!
            line1.move(-dx,-dy);
            line2.move(-dx,-dy);
            line3.move(-dx,-dy);
            loc = new Location(loc.getX() - dx, loc.getY() - dy);
          }
        }
      }

      // IF CLOSEST IS IMITATOR
      else if(!imitateChaser && other.getClass() == Imitator.class)
      {
        double locX = loc.getX(); // Store Location X
        double locY = loc.getY(); // Store Location Y

        // Set Imitator to Black
        line1.setColor(Color.BLACK);
        line2.setColor(Color.BLACK);
        line3.setColor(Color.BLACK);

        if(orientation == 1)      // If Up
        {
          orientation = 2;

          // Turn Right
          line1.setEndPoints(locX - (double)size/2,
                             locY - (double)size/2,
                             locX - (double)size/2,
                             locY + (double)size/2);
          line2.setEndPoints(locX - (double)size/2,
                             locY - (double)size/2,
                             locX + (double)size/2,
                             locY);
          line3.setEndPoints(locX - (double)size/2,
                             locY + (double)size/2,
                             locX + (double)size/2,
                             locY);
        }
        else if(orientation == 2) // If Right
        {
          orientation = 3;
 
          // Turn Down
          line1.setEndPoints(locX - (double)size/2,
                             locY - (double)size/2,
                             locX + (double)size/2,
                             locY - (double)size/2);
          line2.setEndPoints(locX - (double)size/2,
                             locY - (double)size/2,
                             locX,
                             locY + (double)size/2);
          line3.setEndPoints(locX + (double)size/2,
                             locY - (double)size/2,
                             locX,
                             locY + (double)size/2);
        }
        else if(orientation == 3) // If Down
        {
          orientation = 4;

          // Turn Left
          line1.setEndPoints(locX + (double)size/2,
                             locY - (double)size/2,
                             locX + (double)size/2,
                             locY + (double)size/2);
          line2.setEndPoints(locX - (double)size/2,
                             locY,
                             locX + (double)size/2,
                             locY - (double)size/2);
          line3.setEndPoints(locX - (double)size/2,
                             locY,
                             locX + (double)size/2,
                             locY + (double)size/2);
        }
        else if(orientation == 4) // If Left
        {
          orientation = 1;

          // Turn Up
          line1.setEndPoints(locX,
                             locY - (double)size/2,
                             locX - (double)size/2,
                             locY + (double)size/2);
          line2.setEndPoints(locX,
                             locY - (double)size/2,
                             locX + (double)size/2,
                             locY + (double)size/2);
          line3.setEndPoints(locX - (double)size/2,
                             locY + (double)size/2,
                             locX + (double)size/2,
                             locY + (double)size/2);
        }
      }
    }
  }

  /*
   * Name:       actionPerformed
   * Purpose:    Clear the Imitator when Clear is clicked
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
} // End of public class Imitator extends Critter
