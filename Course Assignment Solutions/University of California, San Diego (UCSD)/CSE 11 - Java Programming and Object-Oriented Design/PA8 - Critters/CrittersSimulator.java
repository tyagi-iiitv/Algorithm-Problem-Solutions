/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  March 2, 2013
 * File:  CrittersSimulator.java
 * Sources of Help: The PA8 instructions posted on Ord's website.
 *
 * This file actually runs the simulation. It depends on the controller for
 * information about the critters and for knowing when to stop/run.
 */

import java.util.*;
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * Name:    CrittersSimulator
 * Purpose: The public class of this Java file, to run the simulation.
 */

public class CrittersSimulator extends    ActiveObject
                               implements ActionListener
{
  private static final int   DELAY = 40;
  private ArrayList<Critter> critterList;
  private boolean            running;
  private double             closestDistance;
  private Critter            closestCritter,
                             critter1,
                             critter2;
  private Location           critter1loc,
                             critter2loc;

  /*
   * Name:       CrittersSimulator
   * Purpose:    This is the constructor for the simulator. It passes the
   *             array list from the controller.
   * Parameters: critterList: ArrayList<Critter> of all of the critters
   * Return:     N/A
   */

  public CrittersSimulator(ArrayList<Critter> critterList, boolean running,
                           DrawingCanvas canvas)
  {
    // Pass Variables
    this.critterList = critterList;
    this.running     = running;

    closestDistance = Double.MAX_VALUE;

    start(); // Start run()
  }

  /*
   * Name:       actionPerformed
   * Purpose:    Start simulation on "Start" and stop on "Stop"
   * Parameters: e: ActionEvent (user click on a button)
   * Return:     void
   */

  public void actionPerformed(ActionEvent e)
  {
    // If Start Button is clicked
    if(e.getActionCommand().equals("Start"))
    {
      running = true;  // Simulation is running
    }
    // If Stop Button is clicked
    else if(e.getActionCommand().equals("Stop"))
    {
      running = false; // Simulation is stopped
    }
  }

  /* 
   * Name:       run
   * Purpose:    Cycle through list of critters, find the closest to each one,
   *             and have it react accordingly
   * Parameters: N/A
   * Return:     void
   */

  public void run()
  {
    while(true) // Forever Loop
    {
      pause(DELAY); // Pause (to make motion look smooth)

      if(critterList.size() > 1) // If there are more than 1 critters
      {
        for(int i = 0; i < critterList.size(); i++)   // Pick Critter
        {
          for(int j = 0; j < critterList.size(); j++) // Pick Compare Critter
          {
            if(i != j) // Make sure it's not the same critter
            {
              // Assign to variables
              critter1    = critterList.get(i);
              critter1loc = critter1.getLoc();
              critter2    = critterList.get(j);
              critter2loc = critter2.getLoc();

              // If Critter 1 is Runner or Random
              if(critter1.getClass() == Runner.class ||
                 critter1.getClass() == Random.class)
              {
                // Find closest critter
                if(critter1loc.distanceTo(critter2loc) < closestDistance)
                {
                  closestDistance = critter1loc.distanceTo(critter2loc);
                  closestCritter = critter2;
                }
              }

              // If Critter 1 is Chaser
              else if(critter1.getClass() == Chaser.class)
              {
                // Find closest critter
                if(critter1loc.distanceTo(critter2loc) < closestDistance)
                {
                  // If it's not a chaser/imitator, react normally
                  if(critter2.getClass() != Chaser.class &&
                     critter2.getClass() != Imitator.class)
                  {
                    closestDistance = critter1loc.distanceTo(critter2loc);
                    closestCritter = critter2;
                  }

                  // If it's an Imitator
                  else if(critter2.getClass() == Imitator.class)
                  {
                    // If it's not imitating a chaser, react normally
                    if(!((Imitator)critter2).returnBool())
                    {
                      closestDistance = critter1loc.distanceTo(critter2loc);
                      closestCritter = critter2;
                    }
                  }
                }
              }

              // If Critter 1 is Imitator
              else if(critter1.getClass() == Imitator.class)
              {
                // Find closest critter
                if(critter1loc.distanceTo(critter2loc) < closestDistance)
                {
                  closestDistance = critter1loc.distanceTo(critter2loc);
                  closestCritter = critter2;

                  // If it's a runner, random, or imitator
                  if(closestCritter.getClass() == Runner.class ||
                     closestCritter.getClass() == Random.class ||
                     closestCritter.getClass() == Imitator.class)
                  {
                    ((Imitator)critter1).notChaser(); // Not imitating a chaser
                  }

                  // If it's a chaser
                  else if(closestCritter.getClass() == Chaser.class)
                  {
                    ((Imitator)critter1).isChaser();  // Imitating a chaser

                    // Find next closest critter (to chase)
                    for(int k = 0; k < critterList.size(); k++)
                    {
                      critter2 = critterList.get(k);

                      // If next closest is another imitator,
                      if(critter2.getClass() == Imitator.class)
                      {
                        // Make sure it's not also imitating a chaser first
                        if(!((Imitator)critter2).returnBool())
                        {
                          closestDistance =critter1loc.distanceTo(critter2loc);
                          closestCritter = critter2;
                        }
                      }

                      // If next closest isn't an imitator, and isn't a chaser
                      else if(critter2.getClass() != Chaser.class)
                      {
                        // Find normally
                        closestDistance = critter1loc.distanceTo(critter2loc);
                        closestCritter = critter2;
                      }
                    }
                  }
                }
              }
            }
          }

          if(closestCritter != null && running) // If closest critter exists
          {                                     // and simulation is running,
            critter1.reactTo(closestCritter);   // React to closest critter
          }

          closestDistance = Double.MAX_VALUE;   // Reset Closest Distance
          closestCritter = null;                // Reset Closest Critter
        }
      }
    }
  }
} // End of public class CrittersSimulator extends    ActiveObject
  //                                       implements ActionListener
