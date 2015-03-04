/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 11, 2013
 * File:  Puppy.java
 * Sources of Help: The PA5 instructions posted on Ord's website.
 *
 * This file simply creates a Puppy object type using the interface AnimalCard.
 * The Puppy object has parameters (Image, Location, DrawingCanvas).
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    Puppy
 * Purpose: The public class of this Java file (read description above).
 */

public class Puppy implements AnimalCard
{
  private static String PUPPY_SPEAK = "Woof"; // What the Cow says
  private        Color highlightColor;        // Highlight Color (Green or Red)
  private        VisibleImage puppyPic;       // The Image file (jpg)
  private        FilledRect border;           // The border (Green or Red)
  private        FilledRect cover;            // The white cover
  private        boolean shown = true;        // Is the animal shown?

  /*
   * Name:       Puppy
   * Purpose:    This constructor defines the Puppy object.
   * Parameters: pic:    The image file (puppy.jpg in this case)
   *             loc:    The top-left corner of the Puppy object
   *             canvas: The canvas that Puppy is made on
   * Return:     N/A
   */

  public Puppy(Image pic, Location loc, DrawingCanvas canvas)
  {
    puppyPic = new VisibleImage( pic, loc, canvas ); // Create image on canvas
    border = new FilledRect( loc.getX()-AnimalSpeak.BORDER_THICKNESS,//borderX
                             loc.getY()-AnimalSpeak.BORDER_THICKNESS,//borderY
                             puppyPic.getWidth()+
                             (2*AnimalSpeak.BORDER_THICKNESS), // Border Width
                             puppyPic.getHeight()+
                             (2*AnimalSpeak.BORDER_THICKNESS), // BorderHeight
                             canvas);                          // Canvas
    cover  = new FilledRect( border.getX()+Memory.COVER_THICKNESS, // cover X
                             border.getY()+Memory.COVER_THICKNESS, // cover Y
                             border.getWidth()-2*Memory.COVER_THICKNESS,
                             border.getHeight()-2*Memory.COVER_THICKNESS,
                             canvas);
    border.hide();               // Hide Border
    border.sendBackward();       // Send Border behind animal
    cover.setColor(Color.WHITE); // Set cover to white
    cover.sendToFront();         // Send cover to front
    cover.hide();                // Hide cover
  }

  /*
   * Name:       speak
   * Purpose:    Return what the animal says (as a String)
   * Parameters: N/A
   * Return:     String
   */

  public String speak()
  {
    return PUPPY_SPEAK; // Return what the animal says
  }

  /*
   * Name:       contains
   * Purpose:    See if a given point (click) is within the animal card
   * Parameters: pt: Location (in this case, of user's click)
   * Return:     boolean
   */

  public boolean contains(Location point)
  {
    return puppyPic.contains(point); // Does the animal card contain the point?
  }

  /*
   * Name:       showHighlight
   * Purpose:    Show the highlight border (Green for right, Red for wrong)
   * Parameters: color: Color of border (Green for right, Red for wrong)
   * Return:     void
   */

  public void showHighlight(Color color)
  {
    border.setColor(color); // Set Border's color as "color"
    border.show();          // Show the border
  }

  /*
   * Name:       hideHighlight
   * Purpose:    Hide the highlight border
   * Parameters: N/A
   * Return:     void
   */

  public void hideHighlight()
  {
    border.hide(); // Hide the border
  }

  /*
   * Name:       getHighlightColor
   * Purpose:    Return whatever color the border is
   * Parameters: N/A
   * Return:     highlightColor: Color that the border currently is
   */

  public Color getHighlightColor()
  {
    return border.getColor();
  }

  /*
   * Name:       setHighlightColor()
   * Purpose:    Set the highlight border color
   * Parameters: color: The color you want to set border to
   * Return:     void
   */

  public void setHighlightColor(Color color)
  {
    border.setColor(color);
  }

  /*
   * Name:       hide
   * Purpose:    Hide the animal (aka show the white cover)
   * Parameters: N/A
   * Return:     void
   */

  public void hide()
  {
    cover.show();                 // Show cover (hide animal)
    border.setColor(Color.BLACK); // Set border to black
    border.show();                // Show border
    shown = false;                // No longer shown
  }

  /*
   * Name:       show
   * Purpose:    Show the animal (aka hide the white cover)
   * Parameters: N/A
   * Return:     void
   */

  public void show()
  {
    cover.hide(); // Hide cover (show animal)
    shown = true; // Now shown
  }

  /*
   * Name:       isShown
   * Purpose:    Tell if the animal is currently shown or hidden
   * Parameters: N/A
   * Return:     shown: Boolean (true if shown, false if hidden)
   */

  public boolean isShown()
  {
    return shown;
  }

  /*
   * Name:       equals
   * Purpose:    Compare this object to another object. If they are the same
   *             class, return true. If not, return false.
   * Parameters: o: The object you're comparing this object to.
   * Return:     boolean
   */

  public boolean equals(Object o)
  {
    return this.getClass() == o.getClass();
  }
} // End of public class Puppy implements AnimalCard
