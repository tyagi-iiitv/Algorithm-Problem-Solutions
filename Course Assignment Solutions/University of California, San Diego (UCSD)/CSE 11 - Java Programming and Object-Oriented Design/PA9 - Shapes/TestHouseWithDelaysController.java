/**
 * Helper program to test various Shapes per CSE 11 - Fall 2012 Inheritance
 * homework.
 *
 * Basic WindowController (with canvas) to support drawing from the
 * ActiveObject TestHouseWithDelays object.
 *
 * Invoked from TestHouseWithDelays.html
 */

import objectdraw.*;

public class TestHouseWithDelaysController extends WindowController
{

  public void begin()
  {
    new TestHouseWithDelays( canvas );
  }

}
