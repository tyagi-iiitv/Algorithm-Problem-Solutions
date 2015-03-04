/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  Critter.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This is the Critter class. All of the critters will be extending this class.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Name:    Critter
 * Purpose: The public class of this Java file (Critter class) 
 */

public abstract class Critter implements ActionListener
{
  public int       size,         // Critter Size (Width & Height)
                   canvasWidth,  // Canvas Width
                   canvasHeight; // Canvas Height

  public Location  loc;

  public Critter(Location loc,int size,DrawingCanvas canvas,int canvasHeight)
  {
    // PASS VARIABLES
    this.size         = size;
    this.loc          = loc;
    canvasWidth       = canvas.getWidth();
    this.canvasHeight = canvasHeight;
  }

  /*
   * Name:       reactTo
   * Purpose:    Define how a critter is going to react to its nearest other
   *             critter
   * Parameters: other: The other (closest) critter
   * Return:     void
   */

  public abstract void reactTo(Critter other);

  /*
   * Name:       kill
   * Purpose:    Remove critter from canvas
   * Parameters: N/A
   * Return:     void
   */

  public abstract void kill();

  /*
   * Name:       getLoc
   * Purpose:    Return the location (center) of the critter
   * Parameters: N/A
   * Return:     Location (center of critter)
   */

  public Location getLoc()
  {
    return loc;
  }

  /*
   * Name:       setLoc
   * Purpose:    Set the location (center) of the critter
   * Parameters: loc: Desired center location
   * Return:     void
   */

  public void setLoc(Location loc)
  {
    this.loc = loc;
  }
} // End of public abstract class Critter
