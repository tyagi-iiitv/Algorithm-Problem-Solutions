/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 22, 2013
 * File:  SlotWheel.java
 * Sources of Help: The PA7 instructions posted on Ord's website.
 *
 * This file creates a "Slot Wheel" object type. It is an ActiveObject.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * Name:    SlotWheel
 * Purpose: The public class of this Java file. It creates a Slot Wheel object.
 */

public class SlotWheel extends ActiveObject implements ActionListener
{
  private final int             BORDER_THICK=1; // Border Thickness
  private Image[]               picArray;       // Array of the Pictures
  private Location              wheelLoc;       // Slot Wheel Location
  private DrawingCanvas         canvas;         // The Drawing Canvas
  private FilledRect            border;         // Black Border
  private int                   arrayCounter,   // Array Counter
                                lastPicNum= -1, // Last Image # (start bogus)
                                wheelNum,       // This wheel #
                                imgWidth,       // Image Width
                                imgHeight,      // Image Height
                                delay,          // Delay for this wheel
                                maxTicks,       // Maximum Ticks for this wheel
                                ticks;          // Current Ticks for this wheel
  private VisibleImage          currentPic;     // Current picture
  private boolean               stored = true;  // Is last image # stored?
  private RandomDoubleGenerator randomDouble;   // Random Generator


  /*
   * Name:       SlotWheel
   * Purpose:    Constructor for the SlotWheel object
   * Parameters: array:  Array of Slot Wheel images
   *             loc:    Location of top left of the Slot Wheel
   *             canvas: Drawing Canvas of the applet
   * Return:     void
   */

  public SlotWheel(Image[] picArray,  int maxTicks, int delay,
                   Location wheelLoc, int imgWidth, int imgHeight,
                   int wheelNum,      DrawingCanvas canvas)
  {
    // PASS VARIABLES
    this.picArray  = picArray;
    this.wheelLoc  = wheelLoc;
    this.delay     = delay;
    this.maxTicks  = maxTicks;
    this.canvas    = canvas;
    this.imgWidth  = imgWidth;
    this.imgHeight = imgHeight;
    this.wheelNum  = wheelNum;
    randomDouble   = new RandomDoubleGenerator(0,1);

    start();
  }

  /*
   * Name:       actionPerformed
   * Purpose:    When the user clicks the "Click to Spin" button, run the
   *             slot machine simulation (spin wheels)
   * Parameters: e: ActionEvent (click on "Click to Spin" button)
   * Return:     void
   */

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals(Slots.SPIN_TEXT))
    {
      synchronized(this)
      {
        ticks = maxTicks;   // Reset the ticks
      }

      stored = false;       // Last image is not stored
      
      arrayCounter = getWheelIndex(randomDouble.nextValue());
      setPic(arrayCounter);
    }
  }

  /*
   * Name:       getWheelIndex
   * Purpose:    Generate a random number and return the associated index:
   *               If 0.00 <= random < 0.25, return 0
   *               If 0.25 <= random < 0.50, return 2
   *               If 0.50 <= random < 0.75, return 4
   *               If anything else,         return 6
   * Parameters: random: A randomly generated double, between 0 and 1
   * Return:     int
   */

  public int getWheelIndex(double random)
  {
    if(random < 0.25)       // If 0.00 <= random < 0.25
    {
      return 0;
    }
    else if(random < 0.5)   // If 0.25 <= random < 0.50
    {
      return 2;
    }
    else if(random < 0.75)  // If 0.50 <= random < 0.75
    {
      return 4;
    }
    else                    // If anything else
    {
      return 6;
    }
  }

  /*
   * Name:       setPic
   * Purpose:    This method sets the current picture to a specific one of the
   *             array.
   * Parameters: picNum: The number in the array of the desired picture
   * Return:     void
   */

  public void setPic(int picNum)
  {
    currentPic = new VisibleImage( picArray[picNum], wheelLoc, canvas );
  }

  /*
   * Name:       getPicNum
   * Purpose:    Return the current image number of the array (1st image = 0,
   *             2nd image = 1, etc)
   * Parameters: N/A
   * Return:     int
   */

  public int getPicNum()
  {
    return arrayCounter % picArray.length;
  }

  /*
   * Name:       getLastPicNum
   * Purpose:    Return the last (final) image number of the array. -1 means
   *             bogus (spinning not finished).
   * Parameters: N/A
   * Return:     int
   */

  public int getLastPicNum()
  {
    return lastPicNum;
  }

  /*
   * Name:       getTicks
   * Purpose:    Return the number of ticks left
   * Parameters: N/A
   * Return:     int
   */

  public int getTicks()
  {
    return ticks;
  }

  /*
   * Name:       run
   * Purpose:    This is the method that is run when start(); is called in this
   *             object. It controls the wheel spin process.
   * Parameters: N/A
   * Return:     void
   */

  public void run()
  {
    border = new FilledRect( wheelLoc.getX()-BORDER_THICK, // Border X
                             wheelLoc.getY()-BORDER_THICK, // Border Y
                             imgWidth  + 2*BORDER_THICK,   // Border Width
                             imgHeight + 2*BORDER_THICK,   // Border Height
                             canvas);                      // Canvas

    setPic(0);              // Start with wheel as first image

    while(true)             // Infinite Loop
    {
      if(ticks > 0)         // If there are still ticks
      {
        lastPicNum = -1;    // Set Last Image Number to Bogus
        Slots.lastPics[wheelNum] = lastPicNum;
        arrayCounter++;     // Increment Array Counter
        setPic(arrayCounter % picArray.length); // Set the current picture
        synchronized(this)
        {
          ticks--;          // Decrement ticks
        }
      }
      else if(ticks == 0 && !stored) // If done spinning and haven't stored,
      {
        lastPicNum = getPicNum();    // Find Last Pic
        Slots.lastPics[wheelNum] = lastPicNum; // Store Last Pic
        stored = true;
        
        if(wheelNum == 2)   // If this is the last wheel,
        {
          Slots.checkWin(); // Check for win
        }
      }

      pause(delay);         // Pause before repeat
    }
  }
}//End of public class SlotWheel extends ActiveObject implements ActionListener
