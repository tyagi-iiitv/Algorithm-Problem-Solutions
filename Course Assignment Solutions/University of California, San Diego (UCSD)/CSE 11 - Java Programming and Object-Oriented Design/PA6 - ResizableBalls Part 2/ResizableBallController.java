/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 1, 2013
 * File:  ResizableBallController.java
 * Sources of Help: The PA4 instructions posted on Ord's website.
 *
 * The applet starts as just an X and a Y axis that can be clicked on and
 * moved around. On a mouse click anywhere in the canvas, a ball is created
 * that grows to 2x its original size, then shrinks back to its original size,
 * then grows back to 2x its original size, etc. Depending on the quadrant
 * (Upper-Left, Upper-Right, Lower-Left, Lower-Right), the ball has a specific
 * color. If the axes are moved, resulting in a ball being in a different
 * quadrant, that ball updates color to maintain the quadrant-color
 * correlation. When the canvas is resized, the horizontal line maintains its
 * Y value and the vertical line maintains its X value (in other words, if the
 * canvas is extended or shrunk in one direction, the corresponding line
 * extends or shrinks to touch the canvas edge). Also, when the canvas is
 * extended/shrunk, the balls move along with the edge. Balls can be clicked
 * on and dragged to move them around, and multiple balls/lines can be grabbed
 * and moved at the same time.
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * Name:    ResizableBallController
 * Purpose: The public class of this Java file (read description above).
 */

public class ResizableBallController extends    WindowController
                                     implements ActionListener,
                                                ChangeListener,
                                                MouseListener,
                                                MouseMotionListener
{
  public static final int MARGIN_HORIZONTAL = 5;   // Horizontal Margin
  public static final int MARGIN_VERTICAL   = 5;   // Vertical Margin
  public static final int BALL_SIZE         = 50;  // Ball Diameter
  public static final int MIN_SPEED         = 1;   // Minimum Speed
  public static final int MAX_SPEED         = 100; // Maximum Speed

  private double          visibleHeight,           // Visible Height
                          hLineProportion,         // H Line Proportion
                          vLineProportion;         // V Line Proportion

  public static double    canvasWidth,             // Canvas Width
                          canvasHeight,            // Canvas Height
                          dragDx,
                          dragDy;

  private boolean         hLineGrabbed = false,    // Is H Line grabbed?
                          vLineGrabbed = false;    // Is V Line grabbed?


  public static boolean   hLineMoved   = false,    // Was H Line moved?
                          vLineMoved   = false,    // Was V Line moved?
                          createActive = true;     // Create balls active?

  public static Line      hLine,                   // Horizontal Line
                          vLine;                   // Vertical Line

  private Location        lastPt;                  // Last mouse position

  private JLabel          header,                  // Header Text
                          dummy;                   // Dummy object
  public static  JLabel   slideLabel;              // Slider Label

  public JButton  start,                   // Start Button
                          stop,                    // Stop Button
                          clearAll;                // Clear All Button

  public  static JSlider  speedSlider;             // Slider to Set Speed
  private Dimension       sliderDimension;         // Slider's Dimensions

  public static  Location origPress;               // Original Press (drag)

  /*
   * Name:       begin
   * Purpose:    This is the method that is run when the applet starts. It
   *             sets up the canvas with the vertical and horizontal lines
   *             (the axes). It also sets up the buttons and whatnot.
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    canvasWidth      = canvas.getWidth();           // Store canvas width
    canvasHeight     = canvas.getHeight();          // Store canvas height

    sliderDimension = new Dimension((int) Math.round(canvasWidth / 3),
                                    (int) Math.round(canvasHeight / 15));

    JPanel north  = new JPanel();                   // Create overall north
    JPanel north1 = new JPanel();                   // Create top row north
    JPanel north2 = new JPanel();                   // Create bottom row north

    JPanel south  = new JPanel();                   // Create overall south

    // FIX UP NORTH OVERALL LAYOUT
    north.setLayout(new GridLayout(2,1));           // North divided 2 rows
    north.add(north1);                              // Add North1 (header text)
    north.add(north2);                              // Add North2 (buttons)

    // CREATE GUI OBJECTS
    header      = new  JLabel("Resizable Balls Controls");
    header.setHorizontalAlignment(SwingConstants.CENTER);
    start       = new JButton("Start");
    stop        = new JButton("Stop");
    clearAll    = new JButton("Clear All");
    speedSlider = new JSlider(JSlider.HORIZONTAL,   // Horizontal JSlider
                              MIN_SPEED,            // Goes from Slow Speed,
                              MAX_SPEED,            // to Fast Speed
                              MIN_SPEED);           // Starts at Slow Speed
    speedSlider.setPreferredSize(sliderDimension);  // Adjust Slider Size
    slideLabel  = new  JLabel("The speed is " + speedSlider.getValue());

    // CREATE LISTENERS
    speedSlider.addChangeListener(this);
    start.addActionListener(this);
    stop.addActionListener(this);
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);

    // FIX UP NORTH1 (NORTH TOP ROW)
    north1.add(header);
    
    // FIX UP NORTH2 (NORTH BOTTOM ROW)
    north2.add(start);
    north2.add(stop);
    north2.add(clearAll);

    // FIX UP SOUTH
    south.add(slideLabel);
    south.add(speedSlider);
    
    // ADD COMPONENTS AND VALIDATE
    this.add(north, BorderLayout.NORTH);
    this.add(south, BorderLayout.SOUTH);
    this.validate();

    visibleHeight = canvasHeight - north.getHeight() - south.getHeight();

    // Create Horizontal Line
    hLine = new Line( 0,
                      visibleHeight/2, // Left Point
                      canvasWidth,
                      visibleHeight/2, // Right Point
                      canvas);                      // Canvas

    // Create Vertical Line
    vLine = new Line( canvasWidth/2, 0,             // Top Point
                      canvasWidth/2, canvasHeight,  // Bottom Point
                      canvas);                      // Canvas

    hLineProportion = (hLine.getStart()).getY() / canvasHeight;
    vLineProportion = (vLine.getStart()).getX() / canvasWidth;
  }

  /* 
   * Name:       mousePressed
   * Purpose:    This is the method that is run when the user presses (not
   *             necessarily a click, but a press specifically) the mouse. It
   *             checks if the user has pressed on one of the lines (or both,
   *             if the intersection is clicked). If so, the toggle is set to
   *             true (so when a user moves the mouse, the line(s) move).
   * Parameters: e: MouseEvent (the mouse press event)
   * Return:     void
   */

  public void mousePressed(MouseEvent e) // When mouse is pressed:
  {
    Location pt = new Location(e.getX(),e.getY());
    origPress = pt;

    if(hLine.contains(pt)) // If press is on hLine:
    {
      hLineGrabbed = true;                     // hLine is grabbed
    }

    if(vLine.contains(pt)) // If press is on vLine:
    {
      vLineGrabbed = true;                     // vLine is grabbed
    }

    lastPt = pt;                               // Set the point to lastPt
  }

  /*
   * Name:       mouseReleased
   * Purpose:    This is the method that is run when the user, after having
   *             pressed the mouse, releases it. It sets both "line grabbed"
   *             toggles to false (so when a user moves the mouse, the line(s)
   *             no longer move).
   * Parameters: e: MouseEvent (the mouse release event)
   * Return:     void
   */

  public void mouseReleased(MouseEvent e)   // When mouse is released:
  {
    hLineGrabbed = vLineGrabbed =           // Neither line is grabbed
    hLineMoved   = vLineMoved   = false;    // Neither line is moved anymore
  }

  /*
   * Name:       mouseDragged
   * Purpose:    This is the method that is run when the user, after having
   *             pressed the mouse, moves it without releasing. If the user
   *             does a drag while one or both of the lines is grabbed, then
   *             the line(s) grabbed move with the user's mouse, and the lines
   *             are forced to remain within a horizontal and vertical margin,
   *             which are defined at the beginning of this class.
   * Parameters: e: MouseEvent (the mouse drag event)
   * Return:     void
   */

  public void mouseDragged(MouseEvent e) // When mouse is dragged:
  {
    Location pt = new Location(e.getX(),e.getY());

    dragDx = pt.getX() - lastPt.getX();
    dragDy = pt.getY() - lastPt.getY();

    // If H Line was grabbed and drag is within the margins:
    if(hLineGrabbed && pt.getY() >= MARGIN_HORIZONTAL && // If hLine grabbed
       pt.getY() <= canvasHeight - MARGIN_HORIZONTAL)    // and drag is in
    {                                                    // the margins:
      hLine.setEndPoints(0, (hLine.getStart()).getY() + dragDy,
                         canvasWidth,
                         (hLine.getStart()).getY() + dragDy);// Move hLine

      if(lastPt != pt)                                   // If new point is
      {                                                  // different (moved):
        hLineMoved = true;                               // hLine was moved
      }

      else                                               // If new point is
      {                                                  // same (not moved):
        hLineMoved = false;                              // hLine not moved
      }
    }

    // If V Line was grabbed and drag is within the margins:
    if(vLineGrabbed && pt.getX() >= MARGIN_VERTICAL &&   // If vLine grabbed
       pt.getX() <= canvasWidth - MARGIN_VERTICAL)       // and drag is in
    {                                                    // the margins:
      vLine.setEndPoints((vLine.getStart()).getX() + dragDx, 0,
                         (vLine.getStart()).getX() + dragDx, 
                         canvasHeight);            // Move vLine

      if(lastPt != pt)                                   // If new point is
      {                                                  // different (moved):
        vLineMoved = true;                               // vLine was moved
      }

      else                                               // If new point is
      {                                                  // same (not moved):
        vLineMoved = false;                              // vLine not moved
      }
    }

    hLineProportion = (hLine.getStart()).getY() / canvasHeight;
    vLineProportion = (vLine.getStart()).getX() / canvasWidth;

    lastPt = pt;                                         // Set the point to
    dragDx = dragDy = 0;                                 // Reset drag delta
  }

  /*
   * Name:       mouseClicked
   * Purpose:    This is the method that is run when the user clicks the mouse
   *             (a click is when the mouse is pressed AND released with no
   *             movement). It creates a ResizableBall object (the description
   *             of the "ResizableBall" is given at the beginning of this
   *             file).
   * Parameters: e: MouseEvent (the mouse click event)
   * Return:     void
   */

  public void mouseClicked(MouseEvent e) // When mouse is clicked:
  {
    Location pt = new Location(e.getX(),e.getY());
    ResizableBall t = new ResizableBall( pt.getX(), pt.getY(),
                          BALL_SIZE, canvas, hLine, vLine,
                          speedSlider.getValue());

    start.addActionListener(t);
    stop.addActionListener(t);
    clearAll.addActionListener(t);
    speedSlider.addChangeListener(t);
    canvas.addMouseListener(t);
    canvas.addMouseMotionListener(t);
    canvas.addComponentListener(t);
  }

  /*
   * Name:       stateChanged
   * Purpose:    This method handles ChangeEvent (in this case, just moving
   *             the slider). When the slider is changed, update the slider
   *             label.
   * Parameters: e: ChangeEvent (the slider sliding)
   * Return:     void
   */

  public void stateChanged(ChangeEvent e)
  {
    slideLabel.setText("The speed is " + speedSlider.getValue());
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Start")) // If Start
    {
      createActive = true;
    }
    else if(e.getActionCommand().equals("Stop"))// If Stop
    {
      createActive = false;
    }
  }

  /*
   * Name:       paint
   * Purpose:    This is the method that is run when the canvas is resized. It
   *             extends (or shrinks) the line(s) that hit the moved canvas
   *             side so that both lines continue to perfectly hit the canvas
   *             edge. It also sets up dx and dy to move the balls too.
   * Parameters: g: Graphics
   * Return:     void
   */

  public void paint( java.awt.Graphics g )
  {
    super.paint( g ); // Ord gave us this

    canvasWidth  = canvas.getWidth();  // Update original
    canvasHeight = canvas.getHeight(); // canvas dimensions

    double newHLineY = hLineProportion * canvas.getHeight();
    double newVLineX = vLineProportion * canvas.getWidth();

    hLine.setEndPoints(0, newHLineY, canvasWidth, newHLineY);
    vLine.setEndPoints(newVLineX, 0, newVLineX, canvasHeight);

    hLineMoved = vLineMoved = true;
  }

  // EMPTY METHODS:
  public void mouseExited(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}
} // End of public class ResizableBallController extends WindowController
