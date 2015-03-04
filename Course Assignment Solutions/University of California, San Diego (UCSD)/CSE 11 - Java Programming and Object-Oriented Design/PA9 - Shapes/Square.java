/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  Square.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 * 
 * This class is the Square. class, which will be extending class ARectangle.
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    Square
 * Purpose: The public class of this Java file (Square class)
 */

public class Square extends ARectangle
{
  private static final int    DEFAULT_UPPERLEFTX = 0;
  private static final int    DEFAULT_UPPERLEFTY = 0;
  private static final int    DEFAULT_SIDE       = 0;
  private static final Color  DEFAULT_COLOR      = Color.BLACK;

  private int side; // Square Side Length

  /*
   * Name:       Square
   * Purpose:    Constructor for Square type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public Square()
  {
    super("Square",0,0);                 // Superclass Constructor
    this.setSide(this.DEFAULT_SIDE);     // Set Sidelength
  }

  /*
   * Name:       Square
   * Purpose:    Constructor for Square type (x,y,side)
   * Parameters: x:    X-Coordinate of Upper Left Point (int)
   *             y:    Y-Coordinate of Upper Left Point (int)
   *             side: Square Side length
   * Return:     N/A
   */

  public Square( int x, int y, int side )
  {
    super("Square", x, y);               // Superclass Constructor
    this.setSide(side);                  // Set Sidelength
  }

  /*
   * Name:       Square
   * Purpose:    Constructor for Square type (upper-left, side)
   * Parameters: upperLeft: Upper Left Point of Square (Point)
   *             side:      Square Side Length
   * Return:     N/A
   */

  public Square( Point upperLeft, int side )
  {
    super("Square", upperLeft);          // Superclass Constructor
    this.setSide(side);                  // Set Sidelength
  }

  /*
   * Name:       Square
   * Purpose:    Constructor for Square type (Square as param)
   * Parameters: r: Another Square
   * Return:     N/A
   */

  public Square( Square r )
  {
    super(r.getName(),r.getUpperLeft()); // Superclass Constructor
    this.setSide(r.getSide());           // Set Sidelength
  }

  /*
   * Name:       toString
   * Purpose:    What to display when Square is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return super.toString()+" Sides: "+this.getSide();
  }

  /*
   * Name:       equals
   * Purpose:    See if this Square equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                               // Check Exists
           o.getClass() == Square.class &&            // Check Class
           this.getName() == ((Square)o).getName() && // Check Name
           this.upperLeft.equals(((Square)o).getUpperLeft()) && // U-L
           this.getSide() == ((Square)o).getSide();   // Check Sidelength
  }

  /*
   * Name:       draw
   * Purpose:    Draw the Square
   * Parameters: canvas: Drawing Canvas
   *             c:      Square color
   *             fill:   Is it filled? (boolean)
   * Return:     void
   */

  public void draw( DrawingCanvas canvas, Color c, boolean fill )
  {
    int ulX = this.upperLeft.getX();                  // Calculate UL X
    int ulY = this.upperLeft.getY();                  // Calculate UL Y
    if(c == null)             // If color is invalid,
    {
      c = this.DEFAULT_COLOR; // Use DEFAULT_COLOR (black)
    }

    if(fill) // If Filled,
    {
      // Create FilledRect of color "c"
      FilledRect square = new FilledRect(ulX,ulY,
                                         this.getSide(),this.getSide(),
                                         canvas);
      square.setColor(c);
    }
    else     // If Not Filled,
    {
      // Create FramedRect of color "c"
      FramedRect square = new FramedRect(ulX,ulY,
                                         this.getSide(),this.getSide(),
                                         canvas);
      square.setColor(c);
    }
  }

  /*
   * Name:       getSide
   * Purpose:    Get Side Length of Square
   * Parameters: N/A
   * Return:     side: Side Length of Square (int)
   */

  public int getSide()
  {
    return this.side;
  }

  /*
   * Name:       setSide
   * Purpose:    Set Side Length of Square
   * Parameters: s: Desired Square Side Length (int)
   * Return:     void
   */

  private void setSide( int s )
  {
    this.side = s;
  }
} // End of public class Square extends ARectangle
