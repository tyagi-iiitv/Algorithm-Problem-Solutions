/*
 * Name: Alexander Niema Moshiri
 * Login: cs11wdt
 * Date: February 22, 2013
 * File: CS11Turtle_Threaded.java
 * Sources of Help: The PA7 instructions posted on Ord's website.
 *
 * This program draws any text desired using a walking turtle.
 * In this example, I will be writing "CS11WDT WINTER 2012".
 * This time, the program makes use of threading to write the letters
 * in parallel.
 */

import turtleClasses.*;
import java.awt.Color;

/*
 * Name:    CS11Turtle
 * Purpose: The public class that runs the entire java program.
 */

public class CS11Turtle_Threaded extends Turtle implements Runnable
{
  private final static int CHAR_WIDTH   = 40;  // Set Character Width
  private final static int LINE_HEIGHT  = 80;  // Set Line Height
  private final static int CHAR_PADDING = 40;  // Set Padding between chars
  private final static int LINE_PADDING = 40;  // Set Padding between lines
  private final static int LINE_SPACE = LINE_HEIGHT + CHAR_PADDING;
  private final static int CHAR_SPACE = CHAR_WIDTH + LINE_PADDING;

  private final static int WORLD_WIDTH  = 600; // Set World Width
  private final static int WORLD_HEIGHT = 400; // Set World Height

  private final static int NUM_CHAR_1   = 7;   // Set # of Chars in Line 1
  private final static int NUM_CHAR_2   = 6;   // Set # of Chars in Line 2
  private final static int NUM_CHAR_3   = 4;   // Set # of Chars in Line 3
  private final static int NUM_LINES    = 3;   // Set # of Lines

  private char ch;                             // Holder "ch" character
  private int x,y;                             // Holder x and y values

  /*
   * Delay between turtle actions (turns, moves) in milliseconds.
   * Change this value accordingly.
   * 1000 = 1 sec. / 100 = 0.1 sec.
   */

  private final static int DELAY = 500;        // Set delay to 500 ms

  /*
   * Name:       CS11Turtle
   * Purpose:    Constructor for the CS11Turtle class.
   * Parameters: w:     World
   *             delay: Delay between turtle actions
   */

  public CS11Turtle_Threaded(World w, char ch, int x, int y, int delay)
  {
    super(w, delay);
    this.ch = ch;                              // Assign param ch to class ch
    this.x = x;                                // Assign param x to class x
    this.y = y;                                // Assign param y to class y
    this.setPenWidth(10);                      // Set Pen Width to 10 pixels
    this.setPenColor(Color.BLUE);              // Set Pen Color to Blue
    new Thread(this).start();                  // Create thread and run "run()"
  }

  /*
   * Name:       drawC
   * Purpose:    Draw the letter C using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawC(int x, int y)
  {
    penUp();
    moveTo(x, y); // always start in upper left corner of this char block
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);
    penUp();
    backward(CHAR_WIDTH);
    turnRight();
    penDown();
    forward(LINE_HEIGHT);
    turnLeft();
    forward(CHAR_WIDTH);
  }

  /*
   * Name:       drawS
   * Purpose:    Draw the letter S using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawS(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top horizontal line
    penUp();
    backward(CHAR_WIDTH);                 // MOVE to start
    turnRight();                          // TURN face down
    penDown();
    forward(LINE_HEIGHT / 2);             // DRAW left vertical line
    turnLeft();                           // TURN face right
    forward(CHAR_WIDTH);                  // DRAW middle horizontal line
    turnRight();                          // TURN face down
    forward(LINE_HEIGHT / 2);             // DRAW right vertical line
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH);                  // DRAW bottom horizontal line
  }

  /*
   * Name:       draw1
   * Purpose:    Draw the number 1 using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void draw1(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    forward(CHAR_WIDTH / 2);              // MOVE to horizontal center
    turn(135);                            // TURN 135 degrees (Down-Left)
    penDown();
    forward(LINE_HEIGHT / 4);             // DRAW tip of 1
    penUp();
    backward(LINE_HEIGHT / 4);            // MOVE back to starting point
    turn(-45);                            // TURN face down
    penDown();
    forward(LINE_HEIGHT);                 // DRAW vertical line
    penUp();
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH / 2);              // MOVE to left point of bottom line
    turn(180);                            // TURN around
    penDown();
    forward(CHAR_WIDTH);                  // DRAW bottom line
  }

  /*
   * Name:       drawW
   * Purpose:    Draw the letter W using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */


  private void drawW(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos() + 1); // face down(?)
    penDown();
    // DRAW THE 4 LINES OF W
    moveTo(getXPos() + (CHAR_WIDTH / 4), getYPos() + LINE_HEIGHT);       // L1
    moveTo(getXPos() + (CHAR_WIDTH / 4), getYPos() - (LINE_HEIGHT / 2)); // L2
    moveTo(getXPos() + (CHAR_WIDTH / 4), getYPos() + (LINE_HEIGHT / 2)); // L3
    moveTo(getXPos() + (CHAR_WIDTH / 4), getYPos() - LINE_HEIGHT);       // L4
  }

  /*
   * Name:       drawD
   * Purpose:    Draw the letter D using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawD(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos() + 1); // face down
    penDown();
    forward(LINE_HEIGHT);                 // DRAW left line
    turnLeft();                           // TURN face right
    // DRAW THE 4 PARTS OF THE CURVE
    forward(CHAR_WIDTH / 2);
    moveTo(getXPos() + (CHAR_WIDTH / 4), getYPos() - (LINE_HEIGHT / 16));
    moveTo(getXPos() + (CHAR_WIDTH / 4), getYPos() - (LINE_HEIGHT / 5));
    moveTo(getXPos(), getYPos() - ((19 * LINE_HEIGHT) / 40));
    moveTo(getXPos() - (CHAR_WIDTH / 4), getYPos() - (LINE_HEIGHT / 5));
    moveTo(getXPos() - (CHAR_WIDTH / 4), getYPos() - (LINE_HEIGHT / 16));
    moveTo(getXPos() - (CHAR_WIDTH / 2), getYPos());
  }

  /*
   * Name:       drawT
   * Purpose:    Draw the letter T using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawT(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW horizontal line
    penUp();
    backward(CHAR_WIDTH / 2);
    turnRight();                          // TURN face down
    penDown();
    forward(LINE_HEIGHT);                 // DRAW vertical line
  }

  /*
   * Name:       drawI
   * Purpose:    Draw the letter I using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawI(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top horizontal line
    penUp();
    backward(CHAR_WIDTH / 2);
    turnRight();                          // TURN face down
    penDown();
    forward(LINE_HEIGHT);                 // DRAW vertical line
    penUp();
    turnLeft();                           // TURN face right
    backward(CHAR_WIDTH / 2);
    penDown();
    forward(CHAR_WIDTH);                  // DRAW bottom horizontal line
  }

  /*
   * Name:       drawN
   * Purpose:    Draw the letter N using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawN(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos(), getYPos() + 1);                    // face down
    penDown();
    forward(LINE_HEIGHT);                                    // DRAW left line
    penUp();
    backward(LINE_HEIGHT);
    penDown();
    moveTo(getXPos() + CHAR_WIDTH, getYPos() + LINE_HEIGHT); // DRAW mid line
    moveTo(getXPos(), getYPos() - LINE_HEIGHT);              // DRAW right line
  }

  /*
   * Name: drawE
   * Purpose: Draw the letter E using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawE(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top line
    penUp();
    backward(CHAR_WIDTH);
    turnRight();                          // TURN face down
    penDown();
    forward(LINE_HEIGHT / 2);             // DRAW half of left line
    turnLeft();                           // TURN face right
    forward(CHAR_WIDTH);                  // DRAW middle line
    penUp();
    backward(CHAR_WIDTH);
    turnRight();                          // TURN face down
    penDown();
    forward(LINE_HEIGHT / 2);             // DRAW rest of left line
    turnLeft();                           // TURN face right
    forward(CHAR_WIDTH);                  // DRAW bottom line
  }

  /*
   * Name:       drawR
   * Purpose:    Draw the letter R using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void drawR(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top line
    turnRight();                          // TURN face down
    forward(LINE_HEIGHT / 2);             // DRAW right line
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH);                  // DRAW bottom line
    penUp();
    turnRight();                          // TURN face up
    forward(LINE_HEIGHT / 2);
    turn(180);                            // TURN face down
    penDown();
    forward(LINE_HEIGHT);                 // DRAW left line
    penUp();
    backward(LINE_HEIGHT / 2);
    penDown();
    moveTo(getXPos() + CHAR_WIDTH, getYPos() + (LINE_HEIGHT / 2)); // DRAW diag
  }

  /*
   * Name:       draw2
   * Purpose:    Draw the number 2 using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void draw2(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top horizontal
    turnRight();                          // TURN face down
    forward(LINE_HEIGHT / 2);             // DRAW right vertical
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH);                  // DRAW middle horizontal
    turnLeft();                           // TURN face down
    forward(LINE_HEIGHT / 2);             // DRAW left vertical
    turnLeft();                           // TURN face right
    forward(CHAR_WIDTH);                  // DRAW bottom horizontal
  }

  /*
   * Name: draw0
   * Purpose: Draw the number 0 using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void draw0(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top line
    turnRight();                          // TURN face down
    forward(LINE_HEIGHT);                 // DRAW right line
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH);                  // DRAW bottom line
    turnRight();                          // TURN face up
    forward(LINE_HEIGHT);                 // DRAW left line
  }

  /*
   * Name: draw3
   * Purpose: Draw the number 3 using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void draw3(int x, int y)
  {
    penUp();
    moveTo(x, y);
    turnToFace(getXPos() + 1, getYPos()); // face right
    penDown();
    forward(CHAR_WIDTH);                  // DRAW top line
    turnRight();                          // TURN face down
    forward(LINE_HEIGHT / 2);             // DRAW half of right line
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH);                  // DRAW middle line
    penUp();
    backward(CHAR_WIDTH);
    turnLeft();                           // TURN face down
    penDown();
    forward(LINE_HEIGHT / 2);             // DRAW rest of right line
    turnRight();                          // TURN face left
    forward(CHAR_WIDTH);                  // DRAW bottom line
  }

  /*
   * Name:       run
   * Purpose:    Handle what to do with various letters
   * Parameters: none
   * Return:     void
   */

  public void run()
  {
    switch(ch)
    {
      case 'C': this.drawC(x,y); break;
      case 'S': this.drawS(x,y); break;
      case '1': this.draw1(x,y); break;
      case 'W': this.drawW(x,y); break;
      case 'D': this.drawD(x,y); break;
      case 'T': this.drawT(x,y); break;
      case 'I': this.drawI(x,y); break;
      case 'N': this.drawN(x,y); break;
      case 'E': this.drawE(x,y); break;
      case 'R': this.drawR(x,y); break;
      case '2': this.draw2(x,y); break;
      case '0': this.draw0(x,y); break;
      case '3': this.draw3(x,y); break;
    }
  }

  /*
   * Name:       main
   * Purpose:    The main method, so create world and turtles and have them
   *             draw the letters.
   * Parameters: None
   * Return:     void
   */

  public static void main(String [] args)
  {
  // STARTING OFFSETS (X LINE 1, X LINE 2, X LINE 3, AND Y)
    int startX1 = (WORLD_WIDTH - NUM_CHAR_1 * CHAR_SPACE + LINE_PADDING) / 2,
        startX2 = (WORLD_WIDTH - NUM_CHAR_2 * CHAR_SPACE + LINE_PADDING) / 2,
        startX3 = (WORLD_WIDTH - NUM_CHAR_3 * CHAR_SPACE + LINE_PADDING) / 2;
    int startY  = (WORLD_HEIGHT - NUM_LINES * LINE_SPACE + CHAR_PADDING) / 2;

    int x, y;

    World w = new World(WORLD_WIDTH, WORLD_HEIGHT);

    // Write CS11WDT
    new CS11Turtle_Threaded(w,'C',x = startX1,y = startY,DELAY);
    new CS11Turtle_Threaded(w,'S',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'1',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'1',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'W',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'D',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'T',x += CHAR_SPACE,y,DELAY);

    // Write WINTER
    new CS11Turtle_Threaded(w,'W',x = startX2,y = startY + LINE_SPACE,DELAY);
    new CS11Turtle_Threaded(w,'I',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'N',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'T',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'E',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'R',x += CHAR_SPACE,y,DELAY);

    // Write 2013
    new CS11Turtle_Threaded(w,'2',x = startX3,y = startY + 2*LINE_SPACE,DELAY);
    new CS11Turtle_Threaded(w,'0',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'1',x += CHAR_SPACE,y,DELAY);
    new CS11Turtle_Threaded(w,'3',x += CHAR_SPACE,y,DELAY);
  }
}  // End of public class CS11Turtle_Threaded extends Turtle
