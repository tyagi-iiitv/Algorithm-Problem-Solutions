/*
 * Name: Alexander Niema Moshiri
 * Login: cs11wdt
 * Date: February 22, 2013
 * File: Slots.java
 * Sources of Help: The PA7 instructions posted on Ord's website.
 *
 * This is the main controller object that does the GUI layout with a label on
 * a panel at the top and a button on a panel att he bottom with the drawing
 * canvas in the center. It will initialize an array of images, that are given. 
 */

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * Name:    Slots
 * Purpose: The public class of this Java file. Initializes panels and places
 *          canvas and objects where they need to be.
 */

public class Slots extends WindowController implements ActionListener
{
  private static final int    NUM_OF_WHEELS        = 3;   // # of Wheels
  private static final int    NUM_OF_IMAGES        = 8;   // # o fImages
  private static final int    IMAGE_WIDTH          = 110; // Image Width
  private static final int    IMAGE_HEIGHT         = 145; // Image Height
  private static final double WHEELS_Y_OFFSET      = 5;   // Y Offset of Wheels
  private static final double SPACE_BETWEEN_WHEELS = 5;   // Space Between
  private static final int    WHEEL_1_TICKS        = 22;  // Wheel 1 Ticks
  private static final int    WHEEL_2_TICKS = WHEEL_1_TICKS + 6;
  private static final int    WHEEL_3_TICKS = WHEEL_2_TICKS + 6;
  private static final int    WHEEL_1_DELAY        = 100; // Wheel 1 Delay
  private static final int    WHEEL_2_DELAY = WHEEL_1_DELAY + 25;
  private static final int    WHEEL_3_DELAY = WHEEL_2_DELAY + 25;
  private static final int    HEADER_FONT_SIZE     = 30;  // Header Font Size
  private static final int    WINPIC_WIDTH         = 400; // Win Pic Width
  private static final int    WINPIC_HEIGHT        = 100; // Win Pic Height
  private static final String HEADER_FONT  = "Century Schoolbook L";
  private static final String HEADER_TEXT  = "Niema's Money Maker!";
  public  static final String SPIN_TEXT    = "Click to Spin";
  public  static final String DEPOSIT_TEXT = "Deposit: $";
  public  static final String BET_TEXT     = "Bet: $";

  private static VisibleImage winPic;                     // Win Picture

  private int                 canvasWidth,                // Canvas Width
                              canvasHeight;               // Canvas Height

  private        JPanel       south,                      // South JPanel
                              depositPanel,               // Deposit JPanel
                              betPanel;                   // Bet JPanel
  private        JButton      spin;                       // Spin Button
  private static JTextField   spinOutput,                 // Display Spin #
                              winOutput,                  // Display Win #
                              bankOutput,                 // Display Bank
                              deposit;                    // Deposit Field
  private        JLabel       header;                     // Header
  private static JLabel       depositLabel,               // Deposit Label
                              betLabel;                   // Bet Label

  private static JComboBox<String> bet;                   // Bet

  private Image[]             picArray;                   // Image Array

  public static int[]         lastPics;                   // Store last pic #'s

  private static boolean      checkedWin = true;          // Checked if win?

  private Location            loc1,                       // Location Wheel 1
                              loc2,                       // Location Wheel 2
                              loc3;                       // Location Wheel 3

  private double              loc1X,                      // Location 1 X
                              loc2X,                      // Location 2 X
                              loc3X;                      // Location 3 X

  private SlotWheel           wheel1,                     // Slot Wheel 1
                              wheel2,                     // Slot Wheel 2
                              wheel3;                     // Slot Wheel 3

  private static int          currentTicks1,              // Wheel 1's ticks
                              currentTicks2,              // Wheel 2's ticks
                              currentTicks3,              // Wheel 3's ticks
                              bank,                       // Bank $$$
                              currentBet,                 // Bet on ButtonClick
                              winCount,                   // Win Counter
                              spinCount;                  // Spin Counter

  /*
   * Name:       begin
   * Purpose:    This is the method that is run when the applet starts. It
   *             sets up the upper text on a panel, the lower text on a panel,
   *             and the canvas in the center.
   * Parameters: N/A
   * Return:     void
   */

  public void begin()
  {
    lastPics = new int[NUM_OF_WHEELS];

    // STORE CANVAS WIDTH AND HEIGHT
    canvasWidth  = canvas.getWidth();
    canvasHeight = canvas.getHeight();

    // CREATE AND CONFIGURE COMPONENTS
    // Create South Panel
    south  = new JPanel();
    south.setLayout(new GridLayout(2,3));

    // Create Header
    header = new JLabel(HEADER_TEXT);
    header.setFont(new Font(HEADER_FONT, Font.ITALIC, HEADER_FONT_SIZE));
    header.setHorizontalAlignment(SwingConstants.CENTER);

    // Create Spin Output
    spinOutput = new JTextField("# Spins: "+spinCount);
    spinOutput.setEditable(false); // Prevent editing
    spinOutput.setHorizontalAlignment(SwingConstants.CENTER);

    // Create Win Output
    winOutput = new JTextField("# Wins: "+winCount);
    winOutput.setEditable(false);  // Prevent editing
    winOutput.setHorizontalAlignment(SwingConstants.CENTER);

    // Create Bank Output
    bankOutput = new JTextField("Bank: $"+bank);
    bankOutput.setEditable(false); // Prevent editing
    bankOutput.setHorizontalAlignment(SwingConstants.CENTER);

    // Create "Click to Spin" Button
    spin   = new JButton(SPIN_TEXT);
    spin.addActionListener(this);

    // Create Deposit Section
    depositPanel = new JPanel();
    depositPanel.setLayout(new GridLayout(1,2));
    depositLabel = new JLabel(DEPOSIT_TEXT);
    deposit      = new JTextField(5);
    depositPanel.add(depositLabel);
    depositPanel.add(deposit);
    depositLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    deposit.addActionListener(this);

    // Create Bet Section
    betPanel = new JPanel();
    betPanel.setLayout(new GridLayout(1,2));
    betLabel = new JLabel(BET_TEXT);
    bet      = new JComboBox<String>();
    bet.addItem("1");
    bet.addItem("2");
    bet.addItem("3");
    bet.addItem("4");
    bet.addItem("5");
    betPanel.add(betLabel);
    betPanel.add(bet);
    betLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    // ADD COMPONENTS AND VALIDATE
    this.add(header,BorderLayout.NORTH);
    south.add(spinOutput);
    south.add(spin);
    south.add(winOutput);
    south.add(depositPanel);
    south.add(bankOutput);
    south.add(betPanel);
    this.add(south,BorderLayout.SOUTH);
    this.validate();

    // CREATE AND SET IMAGE ARRAY
    picArray = new Image[NUM_OF_IMAGES];
    picArray[0] = getImage("sungod.jpg");
    picArray[1] = getImage("sungod-bear.jpg");
    picArray[2] = getImage("bear.jpg");
    picArray[3] = getImage("bear-triton.jpg");
    picArray[4] = getImage("triton.jpg");
    picArray[5] = getImage("triton-library.jpg");
    picArray[6] = getImage("library.jpg");
    picArray[7] = getImage("library-sungod.jpg");

    // CALCULATE SPINNER LOCATIONS
    loc2X = canvasWidth/2 - IMAGE_WIDTH/2;
    loc1X = loc2X - SPACE_BETWEEN_WHEELS - IMAGE_WIDTH;
    loc3X = loc2X + SPACE_BETWEEN_WHEELS + IMAGE_WIDTH;

    // SET SPINNER LOCATIONS
    loc1 = new Location(loc1X , WHEELS_Y_OFFSET);
    loc2 = new Location(loc2X , WHEELS_Y_OFFSET);
    loc3 = new Location(loc3X , WHEELS_Y_OFFSET);

    // CREATE SLOT WHEELS
    wheel1 = new SlotWheel(picArray, WHEEL_1_TICKS, WHEEL_1_DELAY,
                           loc1, IMAGE_WIDTH, IMAGE_HEIGHT, 0, canvas);
    spin.addActionListener(wheel1);

    wheel2 = new SlotWheel(picArray, WHEEL_2_TICKS, WHEEL_2_DELAY,
                           loc2, IMAGE_WIDTH, IMAGE_HEIGHT, 1, canvas);
    spin.addActionListener(wheel2);

    wheel3 = new SlotWheel(picArray, WHEEL_3_TICKS, WHEEL_3_DELAY,
                           loc3, IMAGE_WIDTH, IMAGE_HEIGHT, 2, canvas);
    spin.addActionListener(wheel3);

    // CREATE WIN PIC
    winPic = new VisibleImage(getImage("win.png"),
                              (canvasWidth/2)  - (WINPIC_WIDTH/2),
                              (canvasHeight/2) - (WINPIC_HEIGHT),
                              canvas);
    winPic.hide();
  }

  /*
   * Name:       checkWin
   * Purpose:    Check for a win. If the 3 wheels are not done (value -1), do
   *             nothing. If they're all equal, show You Win!!!, increment
   *             win counter, and update South Panel.
   * Parameters: N/A
   * Return:     void
   */

  public static void checkWin()
  {
    if(!checkedWin && lastPics[0] != -1 && lastPics[1] != -1 &&
       lastPics[2] != -1)
    {
      if(lastPics[0] == lastPics[1] && lastPics[0] == lastPics[2])
      {
        winPic.sendToFront();
        winPic.show();
        winCount++;
        bank += (15 * currentBet);
        updateSouth();
      }
      
      checkedWin = true;
    }
  }

  /*
   * Name:       updateSouth
   * Purpose:    Update all text portions of the south panel
   * Parameters: N/A
   * Return:     void
   */

  public static void updateSouth()
  {
    spinOutput.setText("# Spins: "+spinCount);
    spinOutput.setHorizontalAlignment(SwingConstants.CENTER);
    winOutput.setText("# Wins: "+winCount);
    winOutput.setHorizontalAlignment(SwingConstants.CENTER);
    bankOutput.setText("Bank: $"+bank);
    bankOutput.setHorizontalAlignment(SwingConstants.CENTER);
  }

  /*
   * Name:       actionPerformed
   * Purpose:    Erase "You Win!" text when user starts slot machine again
   * Parameters: e: ActionEvent (user click on "Click to Spin" button)
   * Return:     void
   */

  public void actionPerformed(ActionEvent e)
  {
    // On "Click to Spin" Press
    if(e.getActionCommand().equals(Slots.SPIN_TEXT))
    {
      spinCount++;
      currentBet= Integer.parseInt((String)bet.getSelectedItem());
      bank -= currentBet;
      winPic.hide();
      checkedWin = false;
    }

    // On Deposit
    else if(e.getSource() == deposit)
    {
      bank += Integer.parseInt(deposit.getText());
      deposit.setText("");
    }

    updateSouth();
  }
}//End of public class Slots extends WindowController implements ActionListener
