/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  ARectangle.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 *
 * This class is the overarching "ARectangle" abstract class that the Square
 * and Rectnalge classes will be branching off of (extends, not implements).
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    ARectangle
 * Purpose: The public class of this Java file (ARectangle abstract class)
 */

public abstract class ARectangle extends Shape
{
  private static final int    DEFAULT_UPPERLEFTX = 0;
  private static final int    DEFAULT_UPPERLEFTY = 0;

  public Point upperLeft; // Upper Left Point

  /*
   * Name:       ARectangle
   * Purpose:    Constructor for ARectangle type (no name)
   * Parameters: N/A
   * Return:     N/A
   */

  public ARectangle()
  {
    super("ARectangle");                         // Superclass Constructor
    this.setUpperLeft(this.DEFAULT_UPPERLEFTX,this.DEFAULT_UPPERLEFTY);
  }

  /*
   * Name:       ARectangle
   * Purpose:    Constructor for ARectangle type (name,x,y)
   * Parameters: name: String (the ARectangle's name)
   *             x:    X-Coordinate of upper-left of ARectangle (int)
   *             y:    Y-Coordinate of upper-left of ARectangle (int)
   * Return:     N/A
   */

  public ARectangle( String name, int x, int y )
  {
    super(name);             // Superclass Constructor
    this.setUpperLeft(x,y);          // Set Upper Left
  }

  /*
   * Name:       ARectangle
   * Purpose:    Constructor for ARectangle type (name, upperLeft)
   * Parameters: name:      String (the ARectangle's name)
   *             upperLeft: Point (Upper Left point of ARectangle)
   * Return:     N/A
   */

  public ARectangle( String name, Point upperLeft )
  {
    super(name);             // Superclass Constructor
    this.setUpperLeft(upperLeft);    // Set Upper Left
  }

  /*
   * Name:       ARectangle
   * Purpose:    Constructor for ARectangle type (ARectangle)
   * Parameters: r: Another ARectangle
   * Return:     N/A
   */

  public ARectangle( ARectangle r )
  {
    super(r.getName());                             // Superclass Constructor
    this.setUpperLeft(new Point(r.getUpperLeft())); // Set Upper Left
  }

  /*
   * Name:       move
   * Purpose:    Move ARectangle by xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal offset (int)
   *             yDelta: Vertical offset (int)
   * Return:     void
   */

  public void move( int xDelta, int yDelta )
  {
    this.getUpperLeft().move(xDelta,yDelta);
  }

  /*
   * Name:       toString
   * Purpose:    What to display when ARectangle is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return this.getName()+":  Upper Left Corner: "+this.getUpperLeft();
  }

  /*
   * Name:       equals
   * Purpose:    See if this ARectangle equals some other object 'o'.
   *             Note that Early-Out of && prevents any errors.
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                                   // Check Exists
           o.getClass() == ARectangle.class &&            // Check Class
           this.getName() == ((ARectangle)o).getName() && // Check Name
           this.getUpperLeft().equals(((ARectangle)o).getUpperLeft()); // U-L
  }

  /*
   * Name:       hashCode
   * Purpose:    Generate hash code
   * Parameters: N/A
   * Return:     int
   */

  public int hashCode()
  {
    return this.toString().hashCode(); // Use toString Hash Code
  }

  /*
   * Name:       getUpperLeft
   * Purpose:    Get Upper Left Point
   * Parameters: N/A
   * Return:     Point (Upper-Left Point)
   */

  public Point getUpperLeft()
  {
    return this.upperLeft;
  }

  /*
   * Name:       setUpperLeft
   * Purpose:    Set Upper Left Point
   * Parameters: p: Destination Point
   * Return:     void
   */

  public void setUpperLeft( Point p )
  {
    this.upperLeft = new Point(p);     // Update Upper Left
  }

  /*
   * Name:       setUpperLeft
   * Purpose:    Set Upper Left Point
   * Parameters: x: X-Coordinate of Destination Point
   *             y: Y-Coordinate of Destination Point
   * Return:     void
   */

  public void setUpperLeft( int x, int y )
  {
    this.upperLeft = new Point(x,y);   // Update Upper Left
  }
} // End of public abstract class ARectangle extends Shape
