/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  CSE11_Line.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 * 
 * This class is the CSE11_Line class, which will be extending class Shape.
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    CSE11_Line
 * Purpose: The public class of this Java file (CSE11_Line class)
 */

public class CSE11_Line extends Shape
{
  private static final int    DEFAULT_STARTX = 0;
  private static final int    DEFAULT_STARTY = 0;
  private static final int    DEFAULT_ENDX   = 0;
  private static final int    DEFAULT_ENDY   = 0;
  private static final Color  DEFAULT_COLOR  = Color.BLACK;

  private Point start; // Start point of line
  private Point end;   // End point of line

  /*
   * Name:       CSE11_Line
   * Purpose:    Constructor for CSE11_Line type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public CSE11_Line()
  {
    super("CSE11_Line");         // Superclass Constructor
    this.setStart(this.DEFAULT_STARTX,this.DEFAULT_STARTY);
    this.setEnd(this.DEFAULT_ENDX,this.DEFAULT_ENDY);
  }

  /*
   * Name:       CSE11_Line
   * Purpose:    Constructor for CSE11_Line type (start/end point)
   * Parameters: start: Starting point of CSE11_Line
   *             end:   Ending point of CSE11_Line
   * Return:     N/A
   */

  public CSE11_Line( Point start, Point end )
  {
    super("CSE11_Line");         // Superclass Constructor
    this.setStart(start);        // Set Start Point
    this.setEnd(end);            // Set End Point
  }

  /*
   * Name:       CSE11_Line
   * Purpose:    Copy constructor for CSE11_Line type (line as param)
   * Parameters: line: Another CSE11_Line
   * Return:     N/A
   */

  public CSE11_Line( CSE11_Line l )
  {
    super(l.getName());          // Superclass Constructor
    this.setStart(l.getStart()); // Set Start Point
    this.setEnd(l.getEnd());     // Set End Point
  }

  /*
   * Name:       move
   * Purpose:    Move CSE11_Line xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal Offset
   *             yDelta: Vertical Offset
   * Return:     void
   */

  public void move( int xDelta, int yDelta )
  {
    this.getStart().move(xDelta,yDelta);
    this.getEnd().move(xDelta,yDelta);
  }

  /*
   * Name:       toString
   * Purpose:    What to display when CSE11_Line is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return this.getName()+": "+this.getStart()+" to "+this.getEnd();
  }

  /*
   * Name:       equals
   * Purpose:    See if this CSE11_Line equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                                          //Check Exists
           o.getClass() == CSE11_Line.class &&                   //Check Class
           this.getName() == ((CSE11_Line)o).getName() &&        //Check Name
           this.getStart().equals(((CSE11_Line)o).getStart()) && //Check Start
           this.getEnd().equals(((CSE11_Line)o).getEnd());       //Check End
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
   * Purpose:    Draw the CSE11_Line
   * Parameters: canvas: Drawing Canvas
   *             c:      CSE11_Line color
   *             fill:   Is it filled? (boolean)
   * Return:     void
   */

  public void draw( DrawingCanvas canvas, Color c, boolean fill )
  {
    Line line = new Line(this.getStart().getX(),
                         this.getStart().getY(),
                         this.getEnd().getX(),
                         this.getEnd().getY(), canvas);
    if(c == null)             // If Color is invalid,
    {
      c = this.DEFAULT_COLOR; // Use DEFAULT_COLOR (black)
    }
    line.setColor(c);         // Set Line to Color c
  }

  /*
   * Name:       getStart
   * Purpose:    Get Start Point of CSE11_Line
   * Parameters: N/A
   * Return:     start: Start Point
   */

  public Point getStart()
  {
    return this.start;
  }

  /*
   * Name:       getEnd
   * Purpose:    Get End Point of CSE11_Line
   * Parameters: N/A
   * Return:     end: End Point
   */

  public Point getEnd()
  {
    return this.end;
  }

  /*
   * Name:       setStart
   * Purpose:    Set Start Point of CSE11_Line
   * Parameters: p: Desired Start Point
   * Return:     void
   */

  private void setStart( Point p )
  {
    this.start = new Point(p); // Update Start Point
  }

  /*
   * Name:       setStart
   * Purpose:    Set Start Point of CSE11_Line
   * Parameters: x: X-Coordinate of Desired Start Point
   *             y: Y-Coordinate of Desired Start Point
   * Return:     void
   */

  private void setStart( int x, int y )
  {
    this.start = new Point(x,y);
  }

  /*
   * Name:       setEnd
   * Purpose:    Set End Point of CSE11_Line
   * Parameters: p: Desired End Point
   * Return:     void
   */

  private void setEnd( Point p )
  {
    this.end = new Point(p);   // Update Start Point
  }

  /*
   * Name:       setEnd
   * Purpose:    Set End Point of CSE11_Line
   * Parameters: x: X-Coordinate of Desired End Point
   *             y: Y-Coordinate of Desired End Point
   * Return:     void
   */

  private void setEnd( int x, int y )
  {
    this.end = new Point(x,y);
  }
} // End of public class CSE11_Line extends Shape
