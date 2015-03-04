/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 11, 2013
 * File:  AnimalSpeak.java
 * Sources of Help: The PA5 instructions posted on Ord's website.
 *
 * The applet first shows the 6 Animal Cards: Kitty, Duck, Puppy, Lion, Cow,
 * and Lamb. It gives the user a random animal noise, and the user must click
 * on the corresponding animal.
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    AnimalSpeak
 * Purpose: The public class of this Java file (read description above).
 */

public class AnimalSpeak extends WindowController
{
  private static final int PROMPT_Y_OFF     = 20;  // Prompt Y Offset
  private static final int RIGHT_WRONG_OFF  = 40;  // Right/Wrong Offset
  private static final int IMAGE_WIDTH      = 100; // Image Width
  private static final int IMAGE_HEIGHT     = 100; // Image Height
  static final int         BORDER_THICKNESS = 2;   // 2 Pxl Border Thickness

  private String right = new String("CORRECT! -- Click mouse to restart.");
  private String wrong = new String("WRONG - Try Again!");

  private static double promptX;        // X coord of prompt
  private static double promptY;        // Y coord of prompt
  private static double rightWrongX;    // X coord of Right/Wrong
  private static double rightWrongY;    // Y coord of Right/Wrong

  private Text          prompt,         // Prompt
                        rightWrong;     // Right or Wrong

  private Image         kittyPic,       // Create Kitty Object
                        duckPic,        // Create Duck Object
                        puppyPic,       // Create Puppy Object
                        lionPic,        // Create Lion Object
                        cowPic,         // Create Cow Object
                        lambPic;        // Create Lamb Object

  private Location      kittyLoc,       // Create Kitty Location
                        duckLoc,        // Create Duck Location
                        puppyLoc,       // Create Puppy Location
                        lionLoc,        // Create Lion Location
                        cowLoc,         // Create Cow Location
                        lambLoc;        // Create Lamb Location

  private RandomIntGenerator randomInt; // Create Random Generator

  private AnimalCard animal;            // Create card for Randomly Picked

  private Kitty kitty;                  // Kitty Object
  private Duck  duck;                   // Duck Object
  private Puppy puppy;                  // Puppy Object
  private Lion  lion;                   // Lion Object
  private Cow   cow;                    // Cow Object
  private Lamb  lamb;                   // Lamb Object

  /*
   * Name:       begin
   * Purpose:    This is the method that is run when the applet starts. It
   *             sets up the canvas with the Animal Cards and starts the game.
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    promptY = canvas.getHeight()-PROMPT_Y_OFF;        // Set Prompt Y coord
    rightWrongY = canvas.getHeight()-RIGHT_WRONG_OFF; // Set Right/Wrong Y
    prompt = new Text("",0,0, canvas);                // Initialize prompt
    rightWrong = new Text("",0,0, canvas);            // Initialize Right/Wrong
    randomInt = new RandomIntGenerator(0,5); // Initialize RandomIntGenerator

    // SET ALL ANIMAL PICTURES
    kittyPic = getImage("kitty.jpg");
    duckPic  = getImage("duck.jpg");
    puppyPic = getImage("puppy.jpg");
    lionPic  = getImage("lion.jpg");
    cowPic   = getImage("cow.jpg");
    lambPic  = getImage("lamb.jpg");

    // SET ALL ANIMAL LOCATIONS
    kittyLoc = new Location(BORDER_THICKNESS,
                            BORDER_THICKNESS);
    duckLoc  = new Location(kittyLoc.getX()+IMAGE_WIDTH+
                            (2 * BORDER_THICKNESS),      // duckLoc X
                            BORDER_THICKNESS);           // duckLoc Y
    puppyLoc = new Location(duckLoc.getX()+IMAGE_WIDTH+
                            2 * BORDER_THICKNESS,        // puppyLoc X
                            BORDER_THICKNESS);           // puppyLoc Y
    lionLoc  = new Location(kittyLoc.getX(),             // lionLoc X
                            kittyLoc.getY()+IMAGE_HEIGHT+
                            2 * BORDER_THICKNESS);       // lionLoc Y
    cowLoc   = new Location(duckLoc.getX(),              // cowLoc X
                            lionLoc.getY());             // cowLoc Y
    lambLoc  = new Location(puppyLoc.getX(),             // lambLoc X
                            cowLoc.getY());              // lambLoc Y

    // CREATE ALL ANIMAL OBJECTS
    kitty = new Kitty(kittyPic, kittyLoc, canvas);
    duck  = new Duck( duckPic,  duckLoc,  canvas);
    puppy = new Puppy(puppyPic, puppyLoc, canvas);
    lion  = new Lion( lionPic,  lionLoc,  canvas);
    cow   = new Cow(  cowPic,   cowLoc,   canvas);
    lamb  = new Lamb( lambPic,  lambLoc,  canvas);

    pickAnAnimal(); // Start the random animal choice function
  }

  public void onMouseClick( Location pt )
  {
    // HIDE ALL HIGHLIGHTS
    kitty.hideHighlight();
    puppy.hideHighlight();
    lion.hideHighlight();
    lamb.hideHighlight();
    cow.hideHighlight();
    duck.hideHighlight();

    if(animal == null) // Animal is null after correct is chosen, so if correct
    {
      pickAnAnimal();  // Start the game again
    }

    if(animal.contains(pt)) // If the right animal is picked:
    {
      animal.showHighlight(Color.GREEN); // Show Green highlight
      rightWrong.setText(right);         // Set Correct text
      rightWrong.setColor(Color.GREEN);  // Change text color to green
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
      animal = null;                     // Reset animal
    }

    else if(kitty.contains(pt)) // If Kitty (wrong) is picked:
    {
      kitty.showHighlight(Color.RED);    // Show Red highlight
      rightWrong.setText(wrong);         // Set Wrong text
      rightWrong.setColor(Color.RED);    // Set text color to red
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
    }

    else if(duck.contains(pt))  // If Duck(wrong) is picked:
    {
      duck.showHighlight(Color.RED);     // Show Red highlight
      rightWrong.setText(wrong);         // Set Wrong text
      rightWrong.setColor(Color.RED);    // Set text color to red
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
    }

    else if(puppy.contains(pt)) // If Puppy (wrong) is picked:
    {
      puppy.showHighlight(Color.RED);    // Show Red highlight
      rightWrong.setText(wrong);         // Set Wrong text
      rightWrong.setColor(Color.RED);    // Set text color to red
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
    }

    else if(lion.contains(pt))  // If Lion (wrong) is picked:
    {
      lion.showHighlight(Color.RED);     // Show Red highlight
      rightWrong.setText(wrong);         // Set Wrong text
      rightWrong.setColor(Color.RED);    // Set text color to red
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
    }

    else if(cow.contains(pt))   // If Cow (wrong) is picked:
    {
      cow.showHighlight(Color.RED);      // Show Red highlight
      rightWrong.setText(wrong);         // Set Wrong text
      rightWrong.setColor(Color.RED);    // Set text color to red
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
    }

    else if(lamb.contains(pt))  // If Lamb (wrong) is picked:
    {
      lamb.showHighlight(Color.RED);     // Show Red highlight
      rightWrong.setText(wrong);         // Set Wrong text
      rightWrong.setColor(Color.RED);    // Set text color to red
      rightWrongX = (canvas.getWidth() - rightWrong.getWidth()) / 2;
      rightWrong.moveTo(rightWrongX,rightWrongY); // Recenter
    }

    else                        // If blank space is clicked on:
    {
      // HIDE ALL HIGHLIGHTS
      kitty.hideHighlight();
      puppy.hideHighlight();
      lion.hideHighlight();
      lamb.hideHighlight();
      cow.hideHighlight();
      duck.hideHighlight();

      rightWrong.setText("");   // Clear Right/Wrong text
    }
  }

  /*
   * Name:       pickAnAnimal
   * Purpose:    Start the game: Pick a random animal (using a switch) and ask
   *             the user to identify it based on its noise.
   * Parameters: N/A
   * Return:     void
   */

  public void pickAnAnimal()
  {
    switch(randomInt.nextValue()) // Pick a random integer from 0-5
    {
      case 0: animal = kitty;     // If 0, choose Kitty
              break; 
      case 1: animal = puppy;     // If 1, choose Puppy
              break;
      case 2: animal = lion;      // If 2, choose Lion
              break;
      case 3: animal = lamb;      // If 3, choose Lamb
              break;
      case 4: animal = cow;       // If 4, choose Cow
              break;
      case 5: animal = duck;      // If 5, choose Duck
              break;
    }

    prompt.setText("Which animal says " + animal.speak() + "?");
    promptX = (canvas.getWidth() - prompt.getWidth()) / 2; // Recenter
    prompt.moveTo(promptX,promptY);                        // Recenter
  }
} // End of public class AnimalSpeak extends WindowController
