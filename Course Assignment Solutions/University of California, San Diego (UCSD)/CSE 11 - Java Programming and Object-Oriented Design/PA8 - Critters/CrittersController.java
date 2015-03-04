/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  CrittersController.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This is the main controller that does the GUI layout, which is as follows:
 *   At top:    Instructions and 3 buttons (Start, Stop, Clear)
 *   At bottom: Text and 3 buttons (Chaser, Runner, Random)
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/*
 * Name:    CrittersController
 * Purpose: The public class of this Java file. Initializes JComponents and
 *          places canvas and components where they need to be.
 */

public class CrittersController extends    WindowController
                                implements ActionListener, MouseListener
{
  private static final int CRITTER_SIZE = 15;

  private JPanel     north,          // Overall North Panel
                     northRight,     // Right half of North (buttons)
                     south,          // Overall South Panel
                     southBottom;    // Bottom half of South (buttons)

  private JLabel     status,         // Status Label
                     select;         // Selection Label

  private JButton    start,          // Start Button
                     stop,           // Stop Button
                     clear,          // Clear Button
                     chaser,         // Chaser Button
                     runner,         // Runner Button
                     random,         // Random Button
                     imitator;       // Imitator Button

  private int        canvasWidth,    // Canvas Width
                     canvasHeight,   // Canvas Height
                     numCritters,    // Number of Critters
                     selectNum;      // 1 = Chaser,   2 = Runner,
                                     // 3 = Imitator, 4 = Random
  private String     selectText;     // "Chaser","Runner","Imitator","Random"


  private boolean    running = true; // Is the simulation running?

  private ArrayList<Critter> critterList; // The Critter List

  private CrittersSimulator  simulator;   // The Critters Simulator

  /*
   * Name:       begin
   * Purpose:    This is the method that is run when the applet starts. It sets
   *             up the 2 labels (status and selection) and the 6 buttons
   *             (Start, Stop, and Clear at top; Chaser, Runner, and Random at
   *             bottom).
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    // CANVAS STUFF
    canvasWidth  = canvas.getWidth();
    canvas.addMouseListener(this);

    // CREATE AND CONFIGURE COMPONENTS
    // Create North Panel
    north  = new JPanel();
    north.setLayout(new GridLayout(1,2));
    northRight = new JPanel();
    northRight.setLayout(new GridLayout(1,3));

    // Create South Panel
    south  = new JPanel();
    south.setLayout(new GridLayout(2,1));
    southBottom = new JPanel();
    southBottom.setLayout(new GridLayout(1,4));

    // Create Status Label
    status = new JLabel("Please add two or more critters.");
    status.setHorizontalAlignment(SwingConstants.CENTER);

    // Create Selection Label
    select = new JLabel("Select which critter to place:");
    select.setHorizontalAlignment(SwingConstants.CENTER);

    // Create Start Button
    start = new JButton("Start");
    start.addActionListener(this);

    // Create Stop Button
    stop = new JButton("Stop");
    stop.addActionListener(this);

    // Create Clear Button
    clear = new JButton("Clear");
    clear.addActionListener(this);

    // Create Chaser Button
    chaser = new JButton("Chaser");
    chaser.addActionListener(this);

    // Create Runner Button
    runner = new JButton("Runner");
    runner.addActionListener(this);

    // Create Random Button
    random = new JButton("Random");
    random.addActionListener(this);

    // Create Imitator Button
    imitator = new JButton("Imitator");
    imitator.addActionListener(this);

    // ADD COMPONENTS AND VALIDATE
    this.add(north,BorderLayout.NORTH);
    north.add(status);
    north.add(northRight);
    northRight.add(start);
    northRight.add(stop);
    northRight.add(clear);
    this.add(south,BorderLayout.SOUTH);
    south.add(select);
    south.add(southBottom);
    southBottom.add(chaser);
    southBottom.add(runner);
    southBottom.add(random);
    southBottom.add(imitator);
    this.validate();

    // Create Simulator Stuff
    critterList = new ArrayList<Critter>();
    simulator   = new CrittersSimulator(critterList, running, canvas);
    start.addActionListener(simulator);
    stop.addActionListener(simulator);

    canvasHeight = canvas.getHeight(); // Now that everything's edited, set
                                       // the height
  }

  /*
   * Name:       actionPerformed
   * Purpose:    Depending on which button is clicked, do a certain action
   * Parameters: e: ActionEvent (user click on a button)
   * Return:     void
   */

  public void actionPerformed(ActionEvent e)
  {
    // If Start Button is clicked
    if(e.getActionCommand().equals("Start"))
    {
      running = true;
      updateStatus();
    }

    // If Stop Button is clicked
    else if(e.getActionCommand().equals("Stop"))
    {
      running = false;
      updateStatus();
    }

    // If Clear Button is clicked
    else if(e.getActionCommand().equals("Clear"))
    {
      numCritters = 0;
      critterList.subList(0, critterList.size()).clear();
      updateStatus();
      simulator = new CrittersSimulator(critterList, running, canvas);
      start.addActionListener(simulator);
      stop.addActionListener(simulator);
    }

    // If Chaser Button is clicked
    else if(e.getActionCommand().equals("Chaser"))
    {
      selectNum = 1;
      selectText = "Chaser";
      updateSelect();
    }

    // If Runner Button is clicked
    else if(e.getActionCommand().equals("Runner"))
    {
      selectNum = 2;
      selectText = "Runner";
      updateSelect();
    }

    // If Random Button is clicked
    else if(e.getActionCommand().equals("Random"))
    {
      selectNum = 3;
      selectText = "Random";
      updateSelect();
    }

    // If Imitator Button is clicked
    else if(e.getActionCommand().equals("Imitator"))
    {
      selectNum = 4;
      selectText = "Imitator";
      updateSelect();
    }
  }

  /*
   * Name:       mouseClicked
   * Purpose:    Create the selected Critter
   * Parameters: e: MouseEvent (the mouse click event)
   * Return:     void
   */

  public void mouseClicked(MouseEvent e)
  {
    Location pt = new Location(e.getX(), e.getY()); // Store Click Location

    if(pt.getX() > (double)CRITTER_SIZE/2 &&               // Left Margin
       pt.getX() < canvasWidth - (double)CRITTER_SIZE/2 && // Right Margin
       pt.getY() > (double)CRITTER_SIZE/2 &&               // Top Margin
       pt.getY() < canvasHeight - (double)CRITTER_SIZE/2)  // Bottom Margin
    {
      if(selectNum != 0)        // If something is selected
      {
        if(selectNum == 1)      // If Chaser is selected
        {
          critterList.add(new Chaser(pt, CRITTER_SIZE, canvas, canvasHeight));
        }
        else if(selectNum == 2) // If Runner is selected
        {
          critterList.add(new Runner(pt, CRITTER_SIZE, canvas, canvasHeight));
        }
        else if(selectNum == 3) // If Random is selected
        {
          critterList.add(new Random(pt, CRITTER_SIZE, canvas, canvasHeight));
        }
        else if(selectNum == 4) // If Imitator is selected
        {
          critterList.add(new Imitator(pt,CRITTER_SIZE,canvas,canvasHeight));
        }

        clear.addActionListener(critterList.get(numCritters)); // Listen Clear
        numCritters++;          // Increment # of Critters
        updateStatus();
        simulator = new CrittersSimulator(critterList, running, canvas);
        start.addActionListener(simulator);                    // Listen Start
        stop.addActionListener(simulator);                     // Listen Stop
      }
    }
  }

  /*
   * Name:       updateStatus
   * Purpose:    Update the Status Label
   * Parameters: N/A
   * Return:     void
   */

  private void updateStatus()
  {
    if(running)           // If simulation is running (not Stop)
    {
      if(numCritters < 2) // If there are <2 critters,
      {
        status.setText("Please add two or more critters.");
      }
      else                // If there are >3 critters,
      {
        status.setText("Simulation is running.");
      }
    }

    else                  // If simulation is Stopped
    {
      status.setText("Simulation is stopped.");
    }
  }

  /*
   * Name:       updateSelect
   * Purpose:    Update the Selection Label
   * Parameters: N/A
   * Return:     void
   */

  private void updateSelect()
  {
    if(selectText == "Imitator") // If starts in vowel
    {
      select.setText("Click on canvas to place an " + selectText);
    }
    else                         // If starts in consonant
    {
      select.setText("Click on canvas to place a " + selectText);
    }
  }

  // EMPTY METHODS
  public void mouseExited(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
}// End of public class CrittersController extends    WindowController
 //                                        implements ActionListener
