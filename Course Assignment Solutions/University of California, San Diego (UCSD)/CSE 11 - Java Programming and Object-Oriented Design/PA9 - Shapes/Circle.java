/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  Circle.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 * 
 * This class is the Circle class, which will be extending class Shape.
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    Circle
 * Purpose: The public class of this Java file (Circle class)
 */

public class Circle extends Shape
{
  private static final int    DEFAULT_RADIUS  = 0;
  private static final int    DEFAULT_CENTERX = DEFAULT_RADIUS;
  private static final int    DEFAULT_CENTERY = DEFAULT_RADIUS;
  private static final Color  DEFAULT_COLOR   = Color.BLACK;

  private Point center; // Center of Circle
  private int   radius; // Radius of Circle

  /*
   * Name:       Circle
   * Purpose:    Constructor for Circle type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public Circle()
  {
    super("Circle");                      // Superclass Constructor
    this.setCenter(new Point(this.DEFAULT_CENTERX,this.DEFAULT_CENTERY));
    this.setRadius(this.DEFAULT_RADIUS);  // Set Radius
  }

  /*
   * Name:       Circle
   * Purpose:    Constructor for Circle type (center/radius)
   * Parameters: center: Center of circle
   *             radius: Radius of circle
   * Return:     N/A
   */

  public Circle( Point center, int radius )
  {
    super("Circle");                      // Superclass Constructor
    this.setCenter(center);               // Set Center
    this.setRadius(radius);               // Set Radius
  }

  /*
   * Name:       Circle
   * Purpose:    Constructor for Circle type (circle as param)
   * Parameters: c: Another circle
   * Return:     N/A
   */

  public Circle( Circle c )
  {
    super(c.getName());                   // Superclass Constructor
    this.setCenter(c.getCenter());        // Set Center
    this.setRadius(c.getRadius());        // Set Radius
  }

  /*
   * Name:       move
   * Purpose:    Move Circle xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal Offset
   *             yDelta: Vertical Offset
   * Return:     void
   */

  public void move( int xDelta, int yDelta )
  {
    this.getCenter().move(xDelta,yDelta); // Move Center xDelta x yDelta
  }

  /*
   * Name:       toString
   * Purpose:    What to display when Circle is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return this.getName()+": Center: "+this.getCenter()+
           "; Radius: "+this.getRadius();
  }

  /*
   * Name:       equals
   * Purpose:    See if this Circle equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                                        // Check Exists
           o.getClass() == Circle.class &&                     // Check Class
           this.getName() == ((Circle)o).getName() &&          // Check Name
           this.getCenter().equals(((Circle)o).getCenter()) && // Check Center
           this.getRadius() == ((Circle)o).getRadius();        // Check Radius
  }

  /*
   * Name:       hashCode
   * Purpose:    Generate hash code
   * Parameters: N/A
   * Return:     int
   */

  public int hashCode()
  {
    return this.toString().hashCode();
  }

  /*
   * Name:       draw
   * Purpose:    Draw the Circle
   * Parameters: canvas: Drawing Canvas
   *             c:      Circle color
   *             fill:   Is it filled? (boolean)
   * Return:     void
   */

  public void draw( DrawingCanvas canvas, Color c, boolean fill )
  {
    int topLeftX = this.getCenter().getX()-this.getRadius(); // Top Left X
    int topLeftY = this.getCenter().getY()-this.getRadius(); // Top Left Y
    int diameter = this.getRadius() * 2;                     // Diameter
    if(c == null) // If color is invalid,
    {
      c = this.DEFAULT_COLOR; // Use DEFAULT_COLOR (black)
    }

    if(fill) // If filled,
    {
      // Create a FilledOval of color "c"
      FilledOval o=new FilledOval(topLeftX,topLeftY,diameter,diameter,canvas);
      o.setColor(c);
    }
    else     // If not filled,
    {
      // Create a FramedOval of color "c"
      FramedOval o=new FramedOval(topLeftX,topLeftY,diameter,diameter,canvas);
      o.setColor(c);
    }
  }

  /*
   * Name:       getCenter
   * Purpose:    Get Center of Circle
   * Parameters: N/A
   * Return:     center: Center of Circle (Point)
   */

  public Point getCenter()
  {
    return this.center;
  }

  /*
   * Name:       getRadius
   * Purpose:    Get Radius of Circle
   * Parameters: N/A
   * Return:     radius: Radius of Circle (int)
   */

  public int getRadius()
  {
    return this.radius;
  }

  /*
   * Name:       setCenter
   * Purpose:    Set Center Point of Circle
   * Parameters: p: Desired Center Point
   * Return:     void
   */

  private void setCenter( Point p )
  {
    this.center = new Point(p);
  }

  /*
   * Name:       setRadius
   * Purpose:    Set Radius of Circle
   * Parameters: rad: Desired Radius
   * Return:     void
   */

  private void setRadius( int rad )
  {
    this.radius = rad;
  }
} // End of public class Circle extends Shape
