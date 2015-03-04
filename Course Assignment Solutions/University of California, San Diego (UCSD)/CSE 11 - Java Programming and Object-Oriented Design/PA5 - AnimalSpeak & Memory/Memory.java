/*
 * Name:  Alexander Niema Moshiri
 * Login: cs11wdt
 * Date:  February 11, 2013
 * File:  AnimalSpeak.java
 * Sources of Help: The PA5 instructions posted on Ord's website.
 *
 * DO THIS!!!!!!!!!!!!!!!!!!!!!
 */

import objectdraw.*;
import java.awt.*;

/*
 * Name:    Memory
 * Purpose: The public class of this Java file (read description above).
 */

public class Memory extends WindowController
{
  static final int   IMAGE_WIDTH      = 100; // Image Width
  static final int   IMAGE_HEIGHT     = 100; // Image Height
  static final int   BORDER_THICKNESS = 2;   // 1 Pxl Border Thickness
  static final int   COVER_THICKNESS  = 1;
  static final int   TILE_SPACE       = 5;   // 2 Pxl Space between tiles
  static final int   NUM_LOCS         = 12;  // Number of Locations (12)
  static final int   NUM_ANIMALS      = 6;   // Number of animals (6)
  static final int   NOISE_OFFSET     = 20;  // Offset for Animal Noise

  private int        numFlipped       = 0;   // Number Flipped this try
  private double     noiseX           = 0,   // Noise X value
                     noiseY           = 0;   // Noise Y value

  private boolean[]  animalArray1,           // Animals used once
                     animalArray2;           // Animals used twice

  private boolean    match      = false,     // Do the two cards match?
                     hidePicked = false;     // Should I hide the picked cards?

  private RandomIntGenerator randomInt;      // Random Integer Generator

  private Text       noise;                  // Animal Noise

  private Image      kittyPic,               // Create Kitty Object
                     duckPic,                // Create Duck Object
                     puppyPic,               // Create Puppy Object
                     lionPic,                // Create Lion Object
                     cowPic,                 // Create Cow Object
                     lambPic;                // Create Lamb Object

  private Location   loc1, loc2, loc3,       // Create Locations 1-3
                     loc4, loc5, loc6,       // Create Locations 4-6
                     loc7, loc8, loc9,       // Create Locations 7-9
                     loc10,loc11,loc12;      // Create Locations 10-12

  private AnimalCard card1, card2, card3,    // Create cards 1-3
                     card4, card5, card6,    // Create cards 4-6
                     card7, card8, card9,    // Create cards 7-9
                     card10, card11, card12; // Create cards 10-12

  private AnimalCard picked1, picked2;       // Create "picked" cards

  /*
   * Name:       begin
   * Purpose:    This method is run at the start of the applet. It creates the
   *             canvas, initializes the following: animal noise, arrays, int
   *             generator, it fills the arrays with "false". It then sets the
   *             animalPic images to their corresponding .jpg files. It then
   *             calculates the coordinates for the 12 locations of the cards.
   *             It then places all the animals (randomly). It then hides all
   *             the animals.
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    noise = new Text("",0,0,canvas);            // Initialize Animal Noise
    animalArray1 = new boolean[NUM_ANIMALS];    // Initialize Animal Array 1
    animalArray2 = new boolean[NUM_ANIMALS];    // Initialize Animal Array 2
    fillArray(animalArray1, NUM_ANIMALS, false);// Fill Animal with False
    fillArray(animalArray2, NUM_ANIMALS, false);// Fill Animal with False
    randomInt = new RandomIntGenerator(0,5);    // Init RandomIntGenerator

    noiseY = canvas.getHeight() - NOISE_OFFSET;

    // SET ALL ANIMAL PICTURES
    kittyPic = getImage("kitty.jpg");
    duckPic  = getImage("duck.jpg");
    puppyPic = getImage("puppy.jpg");
    lionPic  = getImage("lion.jpg");
    cowPic   = getImage("cow.jpg");
    lambPic  = getImage("lamb.jpg");

    calcLocations(); // Calculate all the locations (loc1-loc12)
    placeAll();      // Place All Cards  
    hideAll();       // Hide All Cards
  }

  /*
   * Name:       onMouseClick
   * Purpose:    This method handles what happens when the mouse is clicked.
   *             If the user has flipped 0 or 1 animals so far and has clicked
   *             on a card, flip that card. If the cards match, keep them there
   *             and highlight them green. If not, highlight them red and flip
   *             them back down on the next mouse click anywhere.
   * Parameters: pt: Click location
   * Return:     void
   */

  public void onMouseClick( Location pt )
  {
    if(hidePicked)        // If the cards were wrong last click:
    {
      picked1.hide();     // Hide the first picked
      picked2.hide();     // Hide the second picked
      picked1 = null;     // Reset the first picked
      picked2 = null;     // Reset the second picked
      hidePicked = false; // No cards are set to be hidden
    }

    if(numFlipped == 0) // If none have been flipped yet, just flip next one
    {
      if(card1.contains(pt))       // If user clicked on card 1:
      {
        if(!card1.isShown())       // If it's not already shown:
        {
          card1.show();            // Show it
          picked1 = card1;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card2.contains(pt))  // If user clicked on card 2: 
      {
        if(!card2.isShown())       // If it's not already shown:
        {
          card2.show();            // Show it
          picked1 = card2;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card3.contains(pt))  // If user clicked on card 3:
      {
        if(!card3.isShown())       // If it's not already shown:
        {
          card3.show();            // Show it
          picked1 = card3;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card4.contains(pt))  // If user clicked on card 4:
      {
        if(!card4.isShown())       // If it's not already shown:
        {
          card4.show();            // Show it
          picked1 = card4;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card5.contains(pt))  // If user clicked on card 5:
      {
        if(!card5.isShown())       // If it's not already shown:
        {
          card5.show();            // Show it
          picked1 = card5;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card6.contains(pt))  // If user clicked on card 6:
      {
        if(!card6.isShown())       // If it's not already shown:
        {
          card6.show();            // Show it
          picked1 = card6;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card7.contains(pt))  // If user clicked on card 7:
      {
        if(!card7.isShown())       // If it's not already shown:
        {
          card7.show();            // Show it
          picked1 = card7;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card8.contains(pt))  // If user clicked on card 8:
      {
        if(!card8.isShown())       // If it's not already shown:
        {
          card8.show();            // Show it
          picked1 = card8;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card9.contains(pt))  // If user clicked on card 9:
      {
        if(!card9.isShown())       // If it's not already shown:
        {
          card9.show();            // Show it
          picked1 = card9;         // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card10.contains(pt)) // If user clicked on card 10:
      {
        if(!card10.isShown())      // If it's not already shown:
        {
          card10.show();           // Show it
          picked1 = card10;        // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card11.contains(pt)) // If user clicked on card 11:
      {
        if(!card11.isShown())      // If it's not already shown:
        {
          card11.show();           // Show it
          picked1 = card11;        // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card12.contains(pt)) // If user clicked on card 12:
      {
        if(!card12.isShown())      // If it's not already shown:
        {
          card12.show();           // Show it
          picked1 = card12;        // It is the first picked card
          numFlipped++;            // A card has been flipped
        }
      }
    }

    else if(numFlipped == 1)       // If one has already been flipped:
    {
      if(card1.contains(pt))       // If user clicked on card 1:
      {
        if(!card1.isShown())       // If it's not already shown:
        {
          card1.show();            // Show it
          picked2 = card1;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card2.contains(pt))  // If user clicked on card 2: 
      {
        if(!card2.isShown())       // If it's not already shown:
        {
          card2.show();            // Show it
          picked2 = card2;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card3.contains(pt))  // If user clicked on card 3:
      {
        if(!card3.isShown())       // If it's not already shown:
        {
          card3.show();            // Show it
          picked2 = card3;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card4.contains(pt))  // If user clicked on card 4:
      {
        if(!card4.isShown())       // If it's not already shown:
        {
          card4.show();            // Show it
          picked2 = card4;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card5.contains(pt))  // If user clicked on card 5:
      {
        if(!card5.isShown())       // If it's not already shown:
        {
          card5.show();            // Show it
          picked2 = card5;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card6.contains(pt))  // If user clicked on card 6:
      {
        if(!card6.isShown())       // If it's not already shown:
        {
          card6.show();            // Show it
          picked2 = card6;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card7.contains(pt))  // If user clicked on card 7:
      {
        if(!card7.isShown())       // If it's not already shown:
        {
          card7.show();            // Show it
          picked2 = card7;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card8.contains(pt))  // If user clicked on card 8:
      {
        if(!card8.isShown())       // If it's not already shown:
        {
          card8.show();            // Show it
          picked2 = card8;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card9.contains(pt))  // If user clicked on card 9:
      {
        if(!card9.isShown())       // If it's not already shown:
        {
          card9.show();            // Show it
          picked2 = card9;         // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card10.contains(pt)) // If user clicked on card 10:
      {
        if(!card10.isShown())      // If it's not already shown:
        {
          card10.show();           // Show it
          picked2 = card10;        // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card11.contains(pt)) // If user clicked on card 11:
      {
        if(!card11.isShown())      // If it's not already shown:
        {
          card11.show();           // Show it
          picked2 = card11;        // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
      else if(card12.contains(pt)) // If user clicked on card 12:
      {
        if(!card12.isShown())      // If it's not already shown:
        {
          card12.show();           // Show it
          picked2 = card12;        // It is the second picked card
          numFlipped++;            // A card has been flipped
        }
      }
    }

    if(numFlipped == 2 && picked1 != null && picked2 != null) // If 2 cards
    {                                                         // are flipped:
      match = picked1.equals(picked2);          // See if they match
      numFlipped = 0;                           // Reset the flips counter

      if(!match)                                // If they don't match:
      {
        picked1.setHighlightColor(Color.RED);   // Highlight first card Red
        picked2.setHighlightColor(Color.RED);   // Highlight second card Red
        hidePicked = true;                      // Set them to hide on next
      }                                         // mouse click
      else                                      // If they DO match:
      {
        picked1.setHighlightColor(Color.GREEN); // Highlight first card Green
        picked2.setHighlightColor(Color.GREEN); // Highlight second card Green
        noise.setText(picked1.speak());         // Show animal noise
        noiseX = (canvas.getWidth() - noise.getWidth()) / 2; // Recenter
        noise.moveTo(noiseX,noiseY);                         // Recenter
      }
    }
  }

  /*
   * Name:       placeAll
   * Purpose:    Place all of the animal cards (face up).
   * Parameters: N/A
   * Return:     void
   */

  public void placeAll()
  {
    placeCard1(loc1);   // Place Card 1
    placeCard2(loc2);   // Place Card 2
    placeCard3(loc3);   // Place Card 3
    placeCard4(loc4);   // Place Card 4
    placeCard5(loc5);   // Place Card 5
    placeCard6(loc6);   // Place Card 6
    placeCard7(loc7);   // Place Card 7
    placeCard8(loc8);   // Place Card 8
    placeCard9(loc9);   // Place Card 9
    placeCard10(loc10); // Place Card 10
    placeCard11(loc11); // Place Card 11
    placeCard12(loc12); // Place Card 12
  }

  /*
   * Name:       hideAll
   * Purpose:    Hide all of the animal cards.
   * Parameters: N/A
   * Return:     void
   */

  public void hideAll()
  {
    card1.hide();  // Hide Card 1
    card2.hide();  // Hide Card 2
    card3.hide();  // Hide Card 3
    card4.hide();  // Hide Card 4
    card5.hide();  // Hide Card 5
    card6.hide();  // Hide Card 6
    card7.hide();  // Hide Card 7
    card8.hide();  // Hide Card 8
    card9.hide();  // Hide Card 9
    card10.hide(); // Hide Card 10
    card11.hide(); // Hide Card 11
    card12.hide(); // Hide Card 12
  }

  /*
   * Name:       showAll
   * Purpose:    Show all of the animal cards (used for debugging).
   * Parameters: N/A
   * Return:     void
   */

  public void showAll()
  {
    card1.show();  // Show Card 1
    card2.show();  // Show Card 2
    card3.show();  // Show Card 3
    card4.show();  // Show Card 4
    card5.show();  // Show Card 5
    card6.show();  // Show Card 6
    card7.show();  // Show Card 7
    card8.show();  // Show Card 8
    card9.show();  // Show Card 9
    card10.show(); // Show Card 10
    card11.show(); // Show Card 11
    card12.show(); // Show Card 12
  }

  /*
   * Name:       fillArray
   * Purpose:    Fill the specified boolean array of the specified length with
   *             a specified value.
   * Parameters: a:      The boolean array that needs to be filled
   *             length: The length of said array
   *             bool:   The boolean value that is to fill the array
   * Return:     void
   */

  public void fillArray(boolean[] a, int length, boolean bool)
  {
    for(int i = 0; i < length; ++i) // Start from 0 and go to array's length
    {
      a[i] = bool; // Fill the 'i'th slot of the array with "bool"
    }
  }

  /*
   * Name:       calcLocations
   * Purpose:    Calculate the 12 locations of the 12 cards. Made this into its
   *             own method for the sake of keeping my begin() method clean and
   *             easy to read.
   * Parameters: N/A
   * Return:     void
   */

  public void calcLocations()
  {
    loc1  = new Location(BORDER_THICKNESS+TILE_SPACE,      // Location 1  X
                         BORDER_THICKNESS+TILE_SPACE);     // Location 1  Y
    loc2  = new Location(loc1.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 2  X
                         loc1.getY());                     // Location 2  Y
    loc3  = new Location(loc2.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 3  X
                         loc2.getY());                     // Location 3  Y
    loc4  = new Location(loc1.getX(),                      // Location 4  X
                         loc1.getY()+IMAGE_HEIGHT+
                         2*BORDER_THICKNESS+TILE_SPACE);   // Location 4  Y
    loc5  = new Location(loc4.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 5  X
                         loc4.getY());                     // Location 5  Y
    loc6  = new Location(loc5.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 6  X
                         loc5.getY());                     // Location 6  Y
    loc7  = new Location(loc4.getX(),                      // Location 7  X
                         loc4.getY()+IMAGE_HEIGHT+
                         2*BORDER_THICKNESS+TILE_SPACE);   // Location 7  Y
    loc8  = new Location(loc7.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 8  X
                         loc7.getY());                     // Location 8  Y
    loc9  = new Location(loc8.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 9  X
                         loc8.getY());                     // Location 9  Y
    loc10 = new Location(loc7.getX(),                      // Location 10 X
                         loc7.getY()+IMAGE_HEIGHT+
                         2*BORDER_THICKNESS+TILE_SPACE);   // Location 10 Y
    loc11 = new Location(loc10.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 11 X
                         loc10.getY());                    // Location 11 Y
    loc12 = new Location(loc11.getX()+IMAGE_WIDTH+
                         2*BORDER_THICKNESS+TILE_SPACE,    // Location 12 X
                         loc11.getY());                    // Location 12 Y
  }

  /*
   * Name:       placeCard1
   * Purpose:    Choose a random animal and assign it to Card 1
   * Parameters: loc: Location of Card 1
   * Return:     void
   */

  public void placeCard1(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card1 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card1 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card1 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card1 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card1 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card1 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card1 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card1 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card1 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card1 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card1 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card1 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard1(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard2(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card2 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card2 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card2 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card2 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card2 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card2 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card2 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card2 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card2 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card2 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card2 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card2 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard2(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard3(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card3 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card3 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card3 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card3 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card3 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card3 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card3 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card3 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card3 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card3 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card3 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card3 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard3(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard4(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card4 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card4 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card4 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card4 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card4 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card4 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card4 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card4 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card4 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card4 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card4 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card4 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard4(loc); // Recurse (try again)
      }
    }
  }
  public void placeCard5(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card5 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card5 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card5 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card5 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card5 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card5 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card5 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card5 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card5 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card5 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card5 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card5 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard5(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard6(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card6 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card6 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card6 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card6 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card6 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card6 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card6 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card6 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card6 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card6 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card6 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card6 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard6(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard7(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card7 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card7 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card7 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card7 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card7 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card7 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card7 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card7 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card7 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card7 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card7 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card7 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard7(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard8(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card8 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card8 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card8 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card8 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card8 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card8 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card8 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card8 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card8 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card8 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card8 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card8 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard8(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard9(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card9 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card9 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card9 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card9 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card9 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card9 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card9 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card9 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card9 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card9 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card9 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card9 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard9(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard10(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card10 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card10 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card10 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card10 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card10 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card10 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card10 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card10 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card10 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card10 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card10 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card10 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard10(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard11(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card11 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card11 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card11 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card11 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card11 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card11 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card11 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card11 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card11 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card11 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card11 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card11 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard11(loc); // Recurse (try again)
      }
    }
  }

  public void placeCard12(Location loc)
  {
    int n = randomInt.nextValue(); // Pick a random integer from 0-5

    if(!animalArray1[n]) // If it hasn't been chosen at all:
    {
      switch(n)
      {
        case 0: card12 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                break;
        case 1: card12 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                break;
        case 2: card12 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                break;
        case 3: card12 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                break;
        case 4: card12 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                break;
        case 5: card12 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                break;
      }
      animalArray1[n] = true; // It has now been used once
    }

    else // If it's been chosen at least once:
    {
      if(!animalArray2[n]) // If it was chosen once but not twice:
      {
        switch(n)
        {
          case 0: card12 = new Kitty(kittyPic,loc,canvas); // If 0, choose Kitty
                  break;
          case 1: card12 = new Puppy(puppyPic,loc,canvas); // If 1, choose Puppy
                  break;
          case 2: card12 = new Lion(lionPic,  loc,canvas); // If 2, choose Lion
                  break;
          case 3: card12 = new Lamb(lambPic,  loc,canvas); // If 3, choose Lamb
                  break;
          case 4: card12 = new Cow(cowPic,    loc,canvas); // If 4, choose Cow
                  break;
          case 5: card12 = new Duck(duckPic,  loc,canvas); // If 5, choose Duck
                  break;
        }
        animalArray2[n] = true; // It has now been used twice
      }

      else // If it's been chosen twice:
      {
        placeCard12(loc); // Recurse (try again)
      }
    }
  }
} // End of public class Memory extends WindowController
