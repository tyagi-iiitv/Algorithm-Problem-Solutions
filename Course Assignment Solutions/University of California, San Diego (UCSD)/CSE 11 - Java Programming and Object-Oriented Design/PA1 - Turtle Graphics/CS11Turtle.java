/*
 * Name: Alexander Niema Moshiri
 * Login: cs11wdt
 * Date: January 10, 2013
 * File: CS11Turtle.java
 * Sources of Help: N/A
 *
 * This program draws any text desired using a walking turtle.
 * In this example, I will be writing "CS11WDT WINTER 2012".
 * I may or may not include my own personalized styles.
 */

import turtleClasses.*;
import java.awt.Color;

/*
 * Name:    CS11Turtle
 * Purpose: The public class that runs the entire java program.
 */

public class CS11Turtle extends Turtle
{
  private final static int CHAR_WIDTH   = 40;
  private final static int LINE_HEIGHT  = 80;
  private final static int CHAR_PADDING = 40;
  private final static int LINE_PADDING = 40;
  private final static int LINE_SPACE = LINE_HEIGHT + CHAR_PADDING;
  private final static int CHAR_SPACE = CHAR_WIDTH + LINE_PADDING;

  private final static int WORLD_WIDTH  = 600;
  private final static int WORLD_HEIGHT = 400;

  private final static int NUM_CHAR_1   = 7;
  private final static int NUM_CHAR_2   = 6;
  private final static int NUM_CHAR_3   = 4;
  private final static int NUM_LINES    = 3;

  /*
   * Delay between turtle actions (turns, moves) in milliseconds.
   * Change this value accordingly.
   * 1000 = 1 sec. / 100 = 0.1 sec.
   */

  private final static int DELAY = 100;

  /*
   * Name:       CS11Turtle
   * Purpose:    Constructor for the CS11Turtle class.
   * Parameters: w:     World
   *             delay: Delay between turtle actions
   */

  public CS11Turtle(World w, int delay){
    super(w, delay);
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
   * Name: bgStar
   * Purpose: Draw a background star using the turtle.
   * Parameters: x: X-coordinate of top-left point of the letter.
   *             y: Y-coordinate of top-left point of the letter.
   * Return:     void
   */

  private void bgStar()
  {
    penUp();
    moveTo(0, WORLD_HEIGHT);               // MOVE to bottom left
    penDown();
    moveTo(WORLD_WIDTH / 2, 0);            // DRAW to top midpoint
    moveTo(WORLD_WIDTH, WORLD_HEIGHT);     // DRAW to bottom right
    moveTo(0, WORLD_HEIGHT / 3);           // DRAW to top left
    moveTo(WORLD_WIDTH, WORLD_HEIGHT / 3); // DRAW to top right
    moveTo(0, WORLD_HEIGHT);               // DRAW to start
  }

  /*
   * Name:       main
   * Purpose:    The main method (do all the stuff)
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
    CS11Turtle myTurtle = new CS11Turtle(w, DELAY);

    myTurtle.setPenWidth(15);
    myTurtle.setPenColor(Color.GRAY);

    myTurtle.bgStar();

// START BLACK SHADOW
    myTurtle.setPenWidth(10);
    myTurtle.setPenColor(Color.BLACK);

    myTurtle.drawC(x = startX1, y = startY);
    myTurtle.drawS(x += CHAR_SPACE, y);
    myTurtle.draw1(x += CHAR_SPACE, y);
    myTurtle.draw1(x += CHAR_SPACE, y);
    myTurtle.drawW(x += CHAR_SPACE, y);
    myTurtle.drawD(x += CHAR_SPACE, y);
    myTurtle.drawT(x += CHAR_SPACE, y);

    myTurtle.drawW(x = startX2, y = startY + LINE_SPACE );
    myTurtle.drawI(x += CHAR_SPACE, y);
    myTurtle.drawN(x += CHAR_SPACE, y);
    myTurtle.drawT(x += CHAR_SPACE, y);
    myTurtle.drawE(x += CHAR_SPACE, y);
    myTurtle.drawR(x += CHAR_SPACE, y);

    myTurtle.draw2(x = startX3, y = startY + 2 * LINE_SPACE);
    myTurtle.draw0(x += CHAR_SPACE, y);
    myTurtle.draw1(x += CHAR_SPACE, y);
    myTurtle.draw3(x += CHAR_SPACE, y);
// END BLACK SHADOW

// START BLUE LETTERS
    myTurtle.setPenWidth(10);
    myTurtle.setPenColor(Color.BLUE);

    myTurtle.drawC(x = startX1-4, y = startY+4);
    myTurtle.drawS(x += CHAR_SPACE, y);
    myTurtle.draw1(x += CHAR_SPACE, y);
    myTurtle.draw1(x += CHAR_SPACE, y);
    myTurtle.drawW(x += CHAR_SPACE, y);
    myTurtle.drawD(x += CHAR_SPACE, y);
    myTurtle.drawT(x += CHAR_SPACE, y);

    myTurtle.drawW(x = startX2-4, y = startY+4 + LINE_SPACE );
    myTurtle.drawI(x += CHAR_SPACE, y);
    myTurtle.drawN(x += CHAR_SPACE, y);
    myTurtle.drawT(x += CHAR_SPACE, y);
    myTurtle.drawE(x += CHAR_SPACE, y);
    myTurtle.drawR(x += CHAR_SPACE, y);

    myTurtle.draw2(x = startX3-4, y = startY+4 + 2 * LINE_SPACE);
    myTurtle.draw0(x += CHAR_SPACE, y);
    myTurtle.draw1(x += CHAR_SPACE, y);
    myTurtle.draw3(x += CHAR_SPACE, y);
//END BLUE LETTERS
  }
}  // End of public class CS11Turtle extends Turtle
