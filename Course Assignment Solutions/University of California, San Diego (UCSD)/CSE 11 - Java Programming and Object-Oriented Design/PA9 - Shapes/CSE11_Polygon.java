/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  CSE11_Polygon.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 * 
 * This class is the CSE11_Polygon class, which will be extending class Shape.
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    CSE11_Polygon
 * Purpose: The public class of this Java file (CSE11_Polygon class)
 */

public class CSE11_Polygon extends Shape
{
  private static final Point[] DEFAULT_PTS   = new Point[0];
  private static final Color   DEFAULT_COLOR = Color.BLACK;

  private Point[] points; // Array of Points

  /*
   * Name:       CSE11_Polygon
   * Purpose:    Constructor for CSE11_Polygon type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public CSE11_Polygon()
  {
    super("CSE11_Polygon");                        // Superclass Constructor
    this.setPoints(new Point[DEFAULT_PTS.length]); // Set Points Array
  }

  /*
   * Name:       CSE11_Polygon
   * Purpose:    Constructor for CSE11_Polygon type
   * Parameters: Point[]: Array of Points in the polygon
   * Return:     N/A
   */

  public CSE11_Polygon( Point[] pts )
  {
    super("CSE11_Polygon");                        // Superclass Constructor
    this.setPoints(pts);                           // Set Points Array
  }

  /*
   * Name:       CSE11_Polygon
   * Purpose:    Constructor for CSE11_Polygon type (CSE11_Polygon as param)
   * Parameters: polygon: Another CSE11_Polygon
   * Return:     N/A
   */

  public CSE11_Polygon( CSE11_Polygon p )
  {
    super(p.getName());                            // Superclass Constructor
    this.setPoints(p.getPoints());                 // Set Points
  }

  /*
   * Name:       move
   * Purpose:    Move CSE11_Polygon xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal Offset
   *             yDelta: Vertical Offset
   * Return:     void
   */

  public void move( int xDelta, int yDelta )
  {
    for(int i = 0; i < this.getPoints().length; i++)
    {
      Point orig = this.getPoint(i);
      Point fill = new Point(orig.getX()+xDelta, orig.getY()+yDelta);
      this.setPoint(i,fill);
    }
  }

  /*
   * Name:       toString
   * Purpose:    What to display when CSE11_Polygon is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    String output = this.getName()+": #Points: "+this.getPoints().length+" ";

    for(int i = 0; i < this.getPoints().length; i++)
    {
      output += this.getPoint(i)+" ";
    }
    return output;
  }

  /*
   * Name:       equals
   * Purpose:    See if this CSE11_Polygon equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    boolean equals = true;

    if(o == null || o.getClass() != CSE11_Polygon.class)
    {
      equals = false;
    }

    else if(this.getName() != ((CSE11_Polygon)o).getName())
    {
      equals = false;
    }

    else if(this.getPoints().length != ((CSE11_Polygon)o).getPoints().length)
    {
      equals = false;
    }

    else
    {
      for(int i = 0; i < this.getPoints().length; i++)
      {
        if(this.getPoint(i) != ((CSE11_Polygon)o).getPoint(i))
        {
          equals = false;
        }
      }
    }

    return equals;
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
   * Purpose:    Draw the CSE11_Polygon
   * Parameters: canvas: Drawing Canvas
   *             c:      CSE11_Polygon color
   *             fill:   Is it filled? (boolean)
   * Return:     void
   */

  public void draw( DrawingCanvas canvas, Color c, boolean fill )
  {
    if(c == null) // If invalid color,
    {
      c = this.DEFAULT_COLOR; // Use DEFAULT_COLOR (black)
    }

    CSE11_Line[] lines = new CSE11_Line[this.getPoints().length];

    for(int i = 0; i < this.getPoints().length; i++)
    {
      Point pt = this.getPoint(i);
      int startPos = i;
      int endPos = i+1;
      if(endPos == this.getPoints().length)
      {
        endPos = 0;
      }

      Point startPt = this.getPoint(startPos);
      Point endPt = this.getPoint(endPos);

      lines[i] = new CSE11_Line(startPt,endPt);
      lines[i].draw(canvas,c,fill);
    }
  }

  /*
   * Name:       getPoints
   * Purpose:    Get Point[] of CSE11_Polygon
   * Parameters: N/A
   * Return:     pts: Point[] of CSE11_Polygon
   */

  public Point[] getPoints()
  {
    return this.points;
  }

  /*
   * Name:       getPoint
   * Purpose:    Get a specific point
   * Parameters: pos: Position of point to get
   * Return:     Point
   */

  public Point getPoint( int pos )
  {
    return this.points[pos];
  }

  /*
   * Name:       setPoints
   * Purpose:    Set Point[] of CSE11_Polygon
   * Parameters: pts: Point[] desired
   * Return:     void
   */

  private void setPoints( Point[] pts )
  {
    this.points = new Point[pts.length];
    for(int i = 0; i < pts.length; i++)
    {
      this.points[i] = pts[i];
    }
  }

  /*
   * Name:       setPoint
   * Purpose:    Set value for specific point
   * Parameters: pos: Position of point to set
   *             pt:  Desired point
   * Return:     void
   */

  private void setPoint( int pos, Point pt )
  {
    this.points[pos] = pt;
  }
} // End of public class CSE11_Polygon extends Shape
