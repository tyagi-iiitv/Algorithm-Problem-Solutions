/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  Shape.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 *
 * This class will define the Point type, which all of the shapes use. It is a
 * combination of an x-coordinate and a y-coordinate.
 *
 * The basic structure of this code was given to us by Ord.
 */

/*
 * Name:    Point
 * Purpose: The public class of this Java file (Point class)
 */

public class Point
{
  private static final int    DEFAULT_X = 0;
  private static final int    DEFAULT_Y = 0;
  private static final String DEFAULT_NAME = "Point";

  private int    x;    // X Coordinate of Point
  private int    y;    // Y Coordinate of Point
  private String name; // Name of Point

  /*
   * Name:       Point
   * Purpose:    Constructor for Point type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public Point()
  {
    this.setName(this.DEFAULT_NAME); // Set Name (Point)
    this.setX(this.DEFAULT_X);       // Set X (0)
    this.setY(this.DEFAULT_Y);       // Set Y (0)
  }

  /*
   * Name:       Point
   * Purpose:    Constructor for Point type (x,y)
   * Parameters: x: X-Coordinate of Point (int)
   *             y: Y-Coordinate of Point (int)
   * Return:     N/A
   */

  public Point( int x, int y )
  {
    this.setName(this.DEFAULT_NAME); // Set Name (Point)
    this.setX(x);                    // Set X
    this.setY(y);                    // Set Y
  }

  /*
   * Name:       Point
   * Purpose:    Constructor for Point type (Point as param)
   * Parameters: point: Point
   * Return:     N/A
   */

  public Point( Point point )
  {
    this.setName(point.getName());   // Set Name
    this.setX(point.getX());         // Set X
    this.setY(point.getY());         // Set Y
  }

  /*
   * Name:       getX
   * Purpose:    Get X-Coordinate of Point
   * Parameters: N/A
   * Return:     int
   */

  public int getX()
  {
    return this.x;
  }

  /*
   * Name:       getY
   * Purpose:    Get Y-Coordinate of Point
   * Parameters: N/A
   * Return:     int
   */

  public int getY()
  {
    return this.y;
  }

  /*
   * Name:       getName
   * Purpose:    Get Point Name
   * Parameters: N/A
   * Return:     String (name)
   */

  public String getName()
  {
    return this.name;
  }

  /*
   * Name:       setName
   * Purpose:    Set Name
   * Parameters: name (String)
   * Return:     void
   */

  public void setName( String name )
  {
    this.name = name;
  }

  /*
   * Name:       setX
   * Purpose:    Set X-Coordinate of Point
   * Parameters: x: Desired X-Coordinate
   * Return:     void
   */

  public void setX( int x )
  {
    this.x = x;
  }

  /*
   * Name:       setY
   * Purpose:    Set Y-Coordinate of Point
   * Parameters: y: Desired Y-Coordinate
   * Return:     void
   */

  public void setY( int y )
  {
    this.y = y;
  }

  /*
   * Name:       move
   * Purpose:    Move Point xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal Offest
   *             yDelta: Vertical Offset
   * Return:     void
   */

  public void move( int xDelta, int yDelta )
  {
    this.x += xDelta;
    this.y += yDelta;
  }

  /*
   * Name:       toString
   * Purpose:    What to display when Point is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return this.name+": ("+this.x+","+this.y+")";
  }

  /*
   * Name:       equals
   * Purpose:    See if this Point equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                        // Check Exists
           o.getClass() == Point.class &&      // Check Class
           this.getX() == ((Point)o).getX() && // Check X
           this.getY() == ((Point)o).getY();   // Check Y
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
} // End of public class Point
