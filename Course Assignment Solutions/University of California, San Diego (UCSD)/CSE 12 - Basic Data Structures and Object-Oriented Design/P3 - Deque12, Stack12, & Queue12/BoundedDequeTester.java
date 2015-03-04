/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-04-26
 *
 * This is a tester for the BoundedDeque interface.
 */

import java.util.*;

public class BoundedDequeTester extends junit.framework.TestCase
{
  private BoundedDeque<Integer> intBD;

  /**
   * Run the JUnit TestRunner to test the BoundedDeque interface (graphical).
   */
  public static void main(String args[])
  {
    junit.swingui.TestRunner.main(new String[] {"BoundedDequeTester"});
  }

  /**
   * Set up the test environment prior to each test method. For my setup, I
   * create a Deque12 of Integer objects (named intBD).
   */
  public void setUp()
  {
    intBD = new Deque12<Integer>(1000);
  }

  /**
   * Tear down the test environment after each test method. In my case, I just
   * set the Deque12 object I created (intBD) to null. Then, the setup method
   * can set it back up again, if need be.
   */
  public void tearDown()
  {
    intBD = null;
  }

  /**
   * This method will be testing the addBack() method using both removeFront()
   * and removeBack(). It will test size() at every modification.
   */
  public void testAddBackRemove()
  {
    // Add the numbers 1 to capacity in ascending order
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addBack(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(0),intBD.peekFront());
      assertEquals(new Integer(i),intBD.peekBack());
    }

    // Back element should equal capacity-1, front should equal 0
    assertEquals(intBD.peekBack(), new Integer(intBD.capacity()-1));
    assertEquals(intBD.peekBack(), new Integer(intBD.size()-1));
    assertEquals(intBD.peekFront(),new Integer(0));

    // Remove elements from the back, checking each value and size
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertEquals(new Integer(intBD.capacity()-1-i),intBD.removeBack());
      assertEquals((intBD.capacity()-i-1),intBD.size());
      if(intBD.size() > 0)
      {
        assertEquals(new Integer(0),intBD.peekFront());
        assertEquals(new Integer(intBD.size()-1),intBD.peekBack());
      }
      else
      {
        assertNull(intBD.peekFront());
        assertNull(intBD.peekBack());
      }
    }

    // Should be empty
    assertEquals(0,intBD.size());

    // Add numbers 1 to capacity in ascending order
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addBack(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(0),intBD.peekFront());
      assertEquals(new Integer(i),intBD.peekBack());
    }

    // Back element should equal capacity-1, front should equal 0
    assertEquals(intBD.peekBack(), new Integer(intBD.capacity()-1));
    assertEquals(intBD.peekBack(), new Integer(intBD.size()-1));
    assertEquals(intBD.peekFront(),new Integer(0));

    // Remove elements from the front, checking each value and size
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertEquals(new Integer(i),intBD.removeFront());
      assertEquals((intBD.capacity()-i-1),intBD.size());
      if(intBD.size() > 0)
      {
        assertEquals(new Integer(i+1),intBD.peekFront());
        assertEquals(new Integer(intBD.capacity()-1),intBD.peekBack());
      }
      else
      {
        assertNull(intBD.peekFront());
        assertNull(intBD.peekBack());
      }
    }
  }

  /**
   * This method will be testing the addFront() method using both removeFront()
   * and removeBack(). It will test size() at every modification.
   */
  public void testAddFrontRemove()
  {
    // Add the numbers 1 to capacity in descending order
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addFront(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(i),intBD.peekFront());
      assertEquals(new Integer(0),intBD.peekBack());
    }

    // Front element should equal capacity-1, back should equal 0
    assertEquals(intBD.peekFront(),new Integer(intBD.capacity()-1));
    assertEquals(intBD.peekFront(),new Integer(intBD.size()-1));
    assertEquals(intBD.peekBack(), new Integer(0));

    // Remove elements from the back, checking each value and size
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertEquals(new Integer(i),intBD.removeBack());
      assertEquals((intBD.capacity()-i-1),intBD.size());
      if(intBD.size() > 0)
      {
        assertEquals(new Integer(intBD.capacity()-1),intBD.peekFront());
        assertEquals(new Integer(i+1),intBD.peekBack());
      }
      else
      {
        assertNull(intBD.peekFront());
        assertNull(intBD.peekBack());
      }
    }

    // Add numbers 1 to capacity in descending order
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addFront(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(i),intBD.peekFront());
      assertEquals(new Integer(0),intBD.peekBack());
    }

    // Front element should equal capacity-1, back should equal 0
    assertEquals(intBD.peekFront(),new Integer(intBD.capacity()-1));
    assertEquals(intBD.peekFront(),new Integer(intBD.size()-1));
    assertEquals(intBD.peekBack(), new Integer(0));

    // Remove elements from the front, checking each value and size
    for(int i = 0; i < intBD.size(); i++)
    {
      assertEquals(new Integer(intBD.capacity()-1-i),intBD.removeFront());
      assertEquals((intBD.capacity()-i-1),intBD.size());
      if(intBD.size() > 0)
      {
        assertEquals(new Integer(intBD.capacity()-2-i),intBD.peekFront());
        assertEquals(new Integer(0),intBD.peekBack());
      }
    }
  }

  /**
   * This method will be testing the equals() method. I will create a new
   * BoundedDeque object that should be equivalent to intBD, and then I will
   * make sure they're equal.
   */
  public void testEquals()
  {
    BoundedDeque<Integer> testBD = new Deque12<Integer>(1000);
    assertTrue(intBD.equals(testBD));
    assertTrue(testBD.equals(intBD));
    assertTrue(intBD.equals(intBD));

    // Add the numbers 1 to capacity in ascending order to both BD's
    for(int i = 0; i < intBD.capacity(); i++)
    {
      intBD.addBack(i);
      testBD.addBack(i);
    }

    // Test that they are equal
    assertTrue(intBD.equals(testBD));
    assertTrue(testBD.equals(intBD));
    assertTrue(intBD.equals(intBD));

    // If they are diff. sizes but same contents, should still return true
    testBD = new Deque12<Integer>(2000);
    for(int i = 0; i < (intBD.capacity()-1); i++)
    {
      testBD.addBack(i);
    }
    assertFalse(intBD.equals(testBD));

    // Add last one (now they're the same)
    assertTrue(testBD.addBack(intBD.capacity()-1));
    assertTrue(intBD.equals(testBD));
    assertTrue(testBD.equals(intBD));
    assertTrue(intBD.equals(intBD));

    // Start over, but a simple one from Piazza
    intBD  = new Deque12<Integer>(5);
    testBD = new Deque12<Integer>(4);
    intBD.addFront(1);
    intBD.addFront(2);
    testBD.addBack(2);
    testBD.addBack(1);
    assertTrue(intBD.equals(testBD));

    intBD = new Deque12<Integer>(1000);
    BoundedDeque<String> stringBD = new Deque12<String>(50);
    assertTrue(intBD.equals(stringBD));
  }

  /**
   * This method will be testing the exceptions of all of the methods that I'm
   * testing.
   */
  public void testExceptions()
  {
    Integer nullInteger = null;

    // TEST EXCEPTION OF ADDBACK() (NullPointerException)
    try
    {
      intBD.addBack(nullInteger);
      if(intBD.size() < intBD.capacity()){fail();}
    }
    catch(NullPointerException e){}

    // TEST EXCEPTION OF ADDFRONT()
    try
    {
      intBD.addFront(nullInteger);
      if(intBD.size() < intBD.capacity()){fail();}
    }
    catch(NullPointerException e){}

    // TEST EXCEPTION OF CONSTRUCTOR (IllegalArgumentException)
    try
    {
      new Deque12<Integer>(-10);
      fail();
    }
    catch(IllegalArgumentException e){}
  }

  /**
   * This method will be ensuring that both remove methods return null if the
   * BoundedDeque is empty.
   */
  public void testRemoveNull()
  {
    assertNull(intBD.removeFront());
    assertNull(intBD.removeBack());
    assertNull(intBD.peekFront());
    assertNull(intBD.peekBack());
    assertEquals(0,intBD.size());
  }

  /**
   * This method will ensure that, if you try to add more items than capacity,
   * the add method will return false.
   */
  public void testAddFalse()
  {
    // Add the numbers 1 to capacity in ascending order
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addBack(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(0),intBD.peekFront());
      assertEquals(new Integer(i),intBD.peekBack());
    }

    // Try adding to front and to back. Should return false (full).
    assertFalse(intBD.addBack(69));
    assertFalse(intBD.addFront(69));

    // Make sure that, when full, adding null returns false, NOT exception!
    assertFalse(intBD.addFront(null));
    assertFalse(intBD.addBack(null));
  }

  /**
   * This method will test that the addFront(), addBack(), removeFront(), and
   * removeBack() methods wrap correctly.
   */
  public void testWrap()
  {
    // Add numbers 0-499 in ascending order
    for(int i = 0; i < 500; i++)
    {
      assertTrue(intBD.addBack(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(0),intBD.peekFront());
      assertEquals(new Integer(i),intBD.peekBack());
    }

    // Now remove all elements from the front to mess with front/back index
    for(int i = 0; i < 500; i++)
    {
      assertEquals(new Integer(i),intBD.removeFront());
      assertEquals((500-i-1),intBD.size());
    }

    // Make sure it's empty
    assertEquals(0,intBD.size());
    assertNull(intBD.peekFront());
    assertNull(intBD.peekBack());

    // NOW add all the elements from 0 to 999 in ascending order (wraps)
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addBack(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(0),intBD.peekFront());
      assertEquals(new Integer(i),intBD.peekBack());
    }

    // Now try again, but using addFront() to wrap
    intBD = new Deque12<Integer>(1000);
    for(int i = 0; i < 500; i++)
    {
      assertTrue(intBD.addBack(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(0),intBD.peekFront());
      assertEquals(new Integer(i),intBD.peekBack());
    }

    // Remove all elements from the front to mess with front/back index
    for(int i = 0; i < 500; i++)
    {
      assertEquals(new Integer(i),intBD.removeFront());
      assertEquals((500-i-1),intBD.size());
    }

    // Make sure it's empty
    assertEquals(0,intBD.size());
    assertNull(intBD.peekFront());
    assertNull(intBD.peekBack());

    // NOW add all the elements from 0 to 999 in descending order (wraps)
    for(int i = 0; i < intBD.capacity(); i++)
    {
      assertTrue(intBD.addFront(i));
      assertEquals((i+1),intBD.size());
      assertEquals(new Integer(i),intBD.peekFront());
      assertEquals(new Integer(0),intBD.peekBack());
    }
  }
} // End of public class BoundedDequeTester extends junit.framework.TestCase
