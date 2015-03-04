/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  Triangle.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 * 
 * This class is the Triangle class, which will be extending class Shape.
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    Triangle
 * Purpose: The public class of this Java file (Triangle class)
 */

public class Triangle extends Shape
{
  private static final Point  DEFAULT_P1    = new Point(0,0);
  private static final Point  DEFAULT_P2    = new Point(0,0);
  private static final Point  DEFAULT_P3    = new Point(0,0);
  private static final Color  DEFAULT_COLOR = Color.BLACK;

  private Point p1; // Point 1 of Triangle
  private Point p2; // Point 2 of Triangle
  private Point p3; // Point 3 of Triangle

  /*
   * Name:       Triangle
   * Purpose:    Constructor for Triangle type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public Triangle()
  {
    super("Triangle");                // Superclass Constructor
    this.setP1(this.DEFAULT_P1);      // Set Point1
    this.setP2(this.DEFAULT_P2);      // Set Point2
    this.setP3(this.DEFAULT_P3);      // Set Point3
  }

  /*
   * Name:       Triangle
   * Purpose:    Constructor for Triangle type (3 points)
   * Parameters: p1, p2, p3: The 3 points of the triangle (Point)
   * Return:     N/A
   */

  public Triangle( Point p1, Point p2, Point p3 )
  {
    super("Triangle");                // Superclass Constructor
    this.setP1(p1);                   // Set Point1
    this.setP2(p2);                   // Set Point2
    this.setP3(p3);                   // Set Point3
  }

  /*
   * Name:       Triangle
   * Purpose:    Constructor for Triangle type (Triangle as param)
   * Parameters: tri: Another Triangle
   * Return:     N/A
   */

  public Triangle( Triangle tri )
  {
    super(tri.getName());             // Superclass Constructor
    this.setP1(tri.getP1());          // Set Point1
    this.setP2(tri.getP2());          // Set Point2
    this.setP3(tri.getP3());          // Set Point3
  }

  /*
   * Name:       move
   * Purpose:    Move Triangle xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal Offset
   *             yDelta: Vertical Offset
   * Return:     void
   */

  public void move( int xDelta, int yDelta )
  {
    this.getP1().move(xDelta,yDelta); // Move Point1
    this.getP2().move(xDelta,yDelta); // Move Point2
    this.getP3().move(xDelta,yDelta); // Move Point3
  }

  /*
   * Name:       toString
   * Purpose:    What to display when Triangle is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return this.getName()+": "+this.getP1()+" "+this.getP2()+" "+this.getP3();
  }

  /*
   * Name:       equals
   * Purpose:    See if this Triangle equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                                  // Check Exists
           o.getClass() == Triangle.class &&             // Check Class
           this.getName() == ((Triangle)o).getName()  && // Check Name
           this.getP1().equals(((Triangle)o).getP1()) && // Check P1
           this.getP2().equals(((Triangle)o).getP2()) && // Check P2
           this.getP3().equals(((Triangle)o).getP3());   // Check P3
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
   * Purpose:    Draw the Triangle
   * Parameters: canvas: Drawing Canvas
   *             c:      Triangle color
   *             fill:   Is it filled? (boolean)
   * Return:     void
   */

  public void draw( DrawingCanvas canvas, Color c, boolean fill )
  {
    if(c == null) // If invalid color,
    {
      c = this.DEFAULT_COLOR; // Use DEFAULT_COLOR (black)
    }

    // Create Lines
    CSE11_Line line1 = new CSE11_Line(this.getP1(),this.getP2());
    CSE11_Line line2 = new CSE11_Line(this.getP2(),this.getP3());
    CSE11_Line line3 = new CSE11_Line(this.getP3(),this.getP1());

    // Draw Lines
    line1.draw(canvas,c,fill);
    line2.draw(canvas,c,fill);
    line3.draw(canvas,c,fill);
  }

  /*
   * Name:       getP1
   * Purpose:    Get Point 1 of Triangle
   * Parameters: N/A
   * Return:     p1: Point 1 of Triangle (Point)
   */

  public Point getP1()
  {
    return this.p1;
  }

  /*
   * Name:       getP2
   * Purpose:    Get Point 2 of Triangle
   * Parameters: N/A
   * Return:     p2: Point 2 of Triangle (Point)
   */

  public Point getP2()
  {
    return this.p2;
  }

  /*
   * Name:       getP3
   * Purpose:    Get Point 1 of Triangle
   * Parameters: N/A
   * Return:     p1: Point 1 of Triangle (Point)
   */

  public Point getP3()
  {
    return this.p3;
  }

  /*
   * Name:       setP1
   * Purpose:    Set Point 1 of Triangle
   * Parameters: p: Desired Point
   * Return:     void
   */

  private void setP1( Point p )
  {
    this.p1 = new Point(p);
  }

  /*
   * Name:       setP2
   * Purpose:    Set Point 2 of Triangle
   * Parameters: p: Desired Point
   * Return:     void
   */

  private void setP2( Point p )
  {
    this.p2 = new Point(p);
  }

  /*
   * Name:       setP3
   * Purpose:    Set Point 3 of Triangle
   * Parameters: p: Desired Point
   * Return:     void
   */

  private void setP3( Point p )
  {
    this.p3 = new Point(p);
  }
} // End of public class Triangle extends Shape
