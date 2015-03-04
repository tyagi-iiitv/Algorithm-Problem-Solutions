/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  Rectangle.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 * 
 * This class is the Rectangle class, which will be extending class ARectangle.
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    Rectangle
 * Purpose: The public class of this Java file (Rectangle class)
 */

public class Rectangle extends ARectangle
{
  private static final int    DEFAULT_UPPERLEFTX = 0;
  private static final int    DEFAULT_UPPERLEFTY = 0;
  private static final int    DEFAULT_WIDTH  = 0;
  private static final int    DEFAULT_HEIGHT = 0;
  private static final Color  DEFAULT_COLOR  = Color.BLACK;

  private int width;  // Rectangle Width
  private int height; // Rectangle Height

  /*
   * Name:       Rectangle
   * Purpose:    Constructor for Rectangle type (no params)
   * Parameters: N/A
   * Return:     N/A
   */

  public Rectangle()
  {
    super("Rectangle",0,0);              // Superclass Constructor
    this.setWidth(this.DEFAULT_WIDTH);   // Set Width
    this.setHeight(this.DEFAULT_HEIGHT); // Set Height
  }

  /*
   * Name:       Rectangle
   * Purpose:    Constructor for Rectangle type (x,y,width,height)
   * Parameters: x:      X-Coordinate of Upper Left Point (int)
   *             y:      Y-Coordinate of Upper Left Point (int)
   *             width:  Rectangle Width (int)
   *             height: Rectangle Width (int)
   * Return:     N/A
   */

  public Rectangle( int x, int y, int width, int height )
  {
    super("Rectangle", x, y);            // Superclass Constructor
    this.setWidth(width);                // Set Width
    this.setHeight(height);              // Set Height
  }

  /*
   * Name:       Rectangle
   * Purpose:    Constructor for Rectangle type (upper-left, width, height)
   * Parameters: upperLeft: Upper Left Point of Rectangle (Point)
   *             width:     Rectangle Width (int)
   *             height:    Rectangle Height (int)
   * Return:     N/A
   */

  public Rectangle( Point upperLeft, int width, int height )
  {
    super("Rectangle", upperLeft);       // Superclass Constructor
    this.setWidth(width);                // Set Width
    this.setHeight(height);              // Set Height
  }

  /*
   * Name:       Rectangle
   * Purpose:    Constructor for Rectangle type (Rectangle as param)
   * Parameters: r: Another Rectangle
   * Return:     N/A
   */

  public Rectangle( Rectangle r )
  {
    super(r.getName(), r.getUpperLeft()); // Superclass Constructor
    this.setWidth(r.getWidth());          // Set Width
    this.setHeight(r.getHeight());        // Set Height
  }

  /*
   * Name:       toString
   * Purpose:    What to display when Square is printed as a string.
   * Parameters: N/A
   * Return:     String
   */

  public String toString()
  {
    return super.toString()+" Width: "+this.getWidth()+
           " Height: "+this.getHeight();
  }

  /*
   * Name:       equals
   * Purpose:    See if this Rectangle equals some other object 'o'
   * Parameters: o: The other object that this will be compared to
   * Return:     boolean
   */

  public boolean equals( Object o )
  {
    return o != null &&                                    // Check Exists
           o.getClass() == Rectangle.class &&              // Check Class
           this.getName() == ((Rectangle)o).getName() &&   // Check Name
           this.upperLeft.equals(((Rectangle)o).getUpperLeft()) && // U-L
           this.getWidth() == ((Rectangle)o).getWidth()&&  // Check Width
           this.getHeight() == ((Rectangle)o).getHeight(); // Check Width
  }

  /*
   * Name:       draw
   * Purpose:    Draw the Rectangle
   * Parameters: canvas: Drawing Canvas
   *             c:      Rectangle color
   *             fill:   Is it filled? (boolean)
   * Return:     void
   */

  public void draw( DrawingCanvas canvas, Color c, boolean fill )
  {
    int ulX = this.upperLeft.getX();
    int ulY = this.upperLeft.getY();
    if(c == null)
    {
      c = this.DEFAULT_COLOR;
    }

    if(fill)
    {
      FilledRect rect = new FilledRect(ulX,ulY,
                                       this.getWidth(),this.getHeight(),
                                       canvas);
      rect.setColor(c);
    }
    else
    {
      FramedRect rect = new FramedRect(ulX,ulY,
                                       this.getWidth(),this.getHeight(),
                                       canvas);
      rect.setColor(c);
    }
  }

  /*
   * Name:       getWidth
   * Purpose:    Get Width of Rectangle
   * Parameters: N/A
   * Return:     width: Width of Rectangle (int)
   */

  public int getWidth()
  {
    return this.width;
  }

  /*
   * Name:       getHeight
   * Purpose:    Get Height of Rectangle
   * Parameters: N/A
   * Return:     height: Height of Rectangle (int)
   */

  public int getHeight()
  {
    return this.height;
  }

  /*
   * Name:       setWidth
   * Purpose:    Set Width of Rectangle
   * Parameters: w: Desired Rectangle Width (int)
   * Return:     void
   */

  private void setWidth( int w )
  {
    this.width = w;
  }

  /*
   * Name:       setHeight
   * Purpose:    Set Height of Rectangle
   * Parameters: h: Desired Rectangle Height (int)
   * Return:     void
   */

  private void setHeight( int h )
  {
    this.height = h;
  }
} // End of public class Rectangle extends ARectangle
