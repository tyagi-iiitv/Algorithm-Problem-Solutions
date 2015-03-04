/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  Random.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This is the Random type object. It is a blue X. Its behavior is to move
 * randomly from its current location to a nearby location (therefore not
 * influenced by any other critters around it).
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Name:    Random
 * Purpose: The public class of this Java file (Random class)
 */

public class Random extends Critter
{
  private Line               line1,   // Down-Right Line of RandomGraphic
                             line2;   // Down-Left Line of RandomGraphic

  private RandomIntGenerator randomX, // Random X generator
                             randomY; // Random Y generator

  public Random(Location loc,int size,DrawingCanvas canvas,int canvasHeight)
  {
    super(loc,size,canvas,canvasHeight);

    // size/2 to make sure it doesn't go even partially off-screen
    randomX = new RandomIntGenerator(-10,10);
    randomY = new RandomIntGenerator(-10,10);

    double locX = loc.getX();         // Store Location X
    double locY = loc.getY();         // Store Location Y

    // CREATE RANDOM GRAPHIC
    line1 = new Line(locX - (double)size/2,
                     locY - (double)size/2,
                     locX + (double)size/2,
                     locY + (double)size/2,
                     canvas);
    line2 = new Line(locX + (double)size/2,
                     locY - (double)size/2,
                     locX - (double)size/2,
                     locY + (double)size/2,
                     canvas);

    // Set Random to Blue
    line1.setColor(Color.BLUE);
    line2.setColor(Color.BLUE);
  }

  /*
   * Name:       kill
   * Purpose:    Remove Random from canvas
   * Parameters: N/A
   * Return:     void
   */

  public void kill()
  {
    if(line1 != null)           // If there is a Random
    {
      line1.removeFromCanvas(); // Remove it from the canvas
      line2.removeFromCanvas();
      line1 = line2 = null;     // There is no more Random
    }
  }

  /*
   * Name:       reactTo
   * Purpose:    Define how a Random is going to react to its nearest other
   *             critter
   * Parameters: other: The other (closest) critter
   * Return:     void
   */

  public void reactTo(Critter other)
  {
    int dx = randomX.nextValue(); // Generate Random dx
    int dy = randomY.nextValue(); // Generate Random dy

    // Move Random by dx and dy and update location
    loc = new Location(loc.getX() + dx, loc.getY() + dy);
    line1.move(dx,dy);
    line2.move(dx,dy);

    // If Random hits edge of canvas,
    if(loc.getX() - (double)size/2 < 0 ||
       loc.getX() + (double)size/2 > canvasWidth ||
       loc.getY() - (double)size/2 < 0 ||
       loc.getY() + (double)size/2 > canvasHeight)
    {
      // Too far! Go back!
      line1.move(-dx,-dy);
      line2.move(-dx,-dy);
      loc = new Location(loc.getX() - dx, loc.getY() - dy);
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
} // End of public class Random extends Critter
