/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 7, 2013
 * File:  Shape.java
 * Sources of Help: The PA9 instructions posted on Ord's website.
 *
 * This class is the overarching "Shape" abstract class that all other shape
 * classes will be branching off of (extends, not implements).
 *
 * The basic structure of this code was given to us by Ord.
 */

import java.awt.*;
import objectdraw.*;

/*
 * Name:    Shape
 * Purpose: The public class of this Java file (Shape abstract class)
 */

public abstract class Shape
{
  private static final String DEFAULT_NAME = "Shape";

  private String name; // Name of Shape

  /*
   * Name:       Shape
   * Purpose:    Constructor for Shape type (no name)
   * Parameters: N/A
   * Return:     N/A
   */

  public Shape()
  {
    this.setName(this.DEFAULT_NAME); // Set Name (Shape)
  }

  /*
   * Name:       Shape
   * Purpose:    Constructor for Shape type (name)
   * Parameters: name: String (the shape's name)
   * Return:     N/A
   */

  public Shape( String name )
  {
    this.setName(name);              // Set Name (Shape)
  }

  /*
   * Name:       getName
   * Purpose:    Return the shape's name
   * Parameters: N/A
   * Return:     String
   */

  public String getName()
  {
    return this.name;
  }

  /*
   * Name:       setName
   * Purpose:    Set shape's name
   * Parameters: name: String (the shape's name)
   * Return:     void
   */

  private void setName( String name )
  {
    this.name = name;
  }

  /*
   * Name:       move
   * Purpose:    Move shape by xDelta horizontally and yDelta vertically
   * Parameters: xDelta: Horizontal offset (int)
   *             yDelta: Vertical offset (int)
   * Return:     void
   */

  public abstract void move( int xDelta, int yDelta );

  /*
   * Name:       draw
   * Purpose:    Draw shape on canvas (filled or unfilled)
   * Parameters: canvas: Drawing Canvas
   *             c:      Color of shape
   *             fill:   Filled or unfilled shape? (boolean)
   * Return:     void
   */

  public abstract void draw( DrawingCanvas canvas, Color c, boolean fill );
} // End of public abstract class Shape
