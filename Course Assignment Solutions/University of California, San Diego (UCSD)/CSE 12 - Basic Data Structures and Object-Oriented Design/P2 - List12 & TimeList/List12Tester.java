/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-04-02
 *
 * This is a tester for the List12 object type.
 */

import java.util.*;
import java.util.List;

public class List12Tester extends junit.framework.TestCase
{
  // Create 2 Types of Lists to Test: One of Integers, One of Strings
  private List<String>  stringL;
  private List<Integer> intL;

  /**
   * Run the JUnit TestRunner to test the List12 object (graphical).
   */
  public static void main(String args[])
  {
    junit.swingui.TestRunner.main(new String[] {"List12Tester"});
  }

  /**
   * Set up the test environment prior to each test method. For my setup, I
   * create a List12 of String objects (named stringL) and a List12 of Integer
   * objects (named intL). These are the objects that will be tested on.
   */
  public void setUp()
  {
    // Initialize the 2 List12 objects before test
    stringL = new List12<String>();
    intL    = new List12<Integer>();
  }

  /**
   * Tear down the test environment after each test method. In my case, I just
   * set the two List12 objects I created (stringL and intL) to null. Then, the
   * setup program can set them back up again, if need be.
   */
  public void tearDown()
  {
    // Nullify the 2 List12 objects after test
    stringL = null;
    intL    = null;
  }

  /**
   * This method will be testing the add() and contains() methods of the List12
   * object. Specifically, I will add two String objects to stringL, and each
   * addition should return true. Then, I will check if stringL contains the
   * two String objects I just added, which should return true. Lastly, I will
   * check if stringL contains some String object that I did NOT add, which
   * should return false.
   */
  public void testAddContains()
  {
    // Add "A" and "B"
    assertTrue(stringL.add("A"));
    assertTrue(stringL.add("B"));

    // Check if stringL contains "A" (true), "B" (true), and "C" (false)
    assertTrue(stringL.contains("A"));
    assertTrue(stringL.contains("B"));
    assertFalse(stringL.contains("C"));
  }

  /**
   * This method will be testing the add() method of the List12 object that
   * adds an object in a specific index. Specifically, I will add a bunch of
   * Integers to intL in the first index, and then check each number to make
   * sure they're all equal to what they should be. Then, to check exceptions,
   * I will test the IndexOutOfBoundsException for a negative index and an
   * index that is larger than the size of the List12.
   */
  public void testAddFirstIndex()
  {
    // Set Max Number (in this case, 1000)
    int maxNumber = 1000;

    // Add a bunch of numbers (up to Max Number)
    for(int i = 1; i <= maxNumber; i++)
    {
      intL.add(0,i);
    }

    // Check that all the numbers equal what they should (Max Number, ... 2, 1)
    for(int i = 0; i < 1000; i++)
    {
      assertEquals(intL.get(i),new Integer(maxNumber--));
    }

    // Test IndexOutOfBoundsException in add(int index, E element) negative
    try
    {
      intL.add(-1,420);
      fail();
    }
    catch(IndexOutOfBoundsException e){}

    // Test IndexOutOfBoundsException in add(int index, E element) > size
    try
    {
      intL = new List12<Integer>();
      intL.add(1,420);
      fail();
    }
    catch(IndexOutOfBoundsException e){}
  }

  /**
   * This method will be testing the get() method of the List12 object type,
   * and it will use the add() method to do so. Specifically, it will add a
   * bunch of numbers to the List12 and then call the get() method on each
   * element to verify that it equals what it should. It will also test for
   * IndexOutOfBoundsExceptions for having an index that is negative and having
   * an index that is larger than the List12 size.
   */
  public void testAddGet()
  {
    // Add all of the numbers from 1-1000 to the List12
    for(int i=1; i<=1000; i++)
    {
      intL.add(i);
    }

    // Check each number using get()
    for(int i=0; i<1000; i++)
    {
      assertEquals(intL.get(i),new Integer(i+1));
    }

    // Test IndexOutOfBoundsException in get(int index) negative
    try
    {
      intL.get(-1);
      fail();
    }
    catch(IndexOutOfBoundsException e){}

    // Test IndexOutOfBoundsException in get(int index) > size
    try
    {
      intL = new List12<Integer>();
      intL.get(1);
      fail();
    }
    catch(IndexOutOfBoundsException e){}
  }

  /**
   * This method will test the add() method of the List12 object type. It will
   * add 3 string objects to stringL and verify that the add() method returns
   * true (which it will when something is added successfully).
   */
  public void testAddReturn()
  {
    // Add "A", "B", and "A" (repeats should be okay with List12)
    assertTrue(stringL.add("A"));
    assertTrue(stringL.add("B"));
    assertTrue(stringL.add("A"));
  }

  /**
   * This method will be testing the add(), remove(), and size() methods. It
   * will first attempt to remove an item from both intL and stringL, which
   * should fail, as both are initially empty. Next, it will Add the numbers
   * 1-1000, and as it does, it will check to ensure that the size of the
   * List12 increases correctly (1 item: size() = 1; 2 items: size() = 2; etc).
   * Then, it will remove each item, 1 by 1, and check size each time (1000
   * items: size() = 1000; 999 items: size() = 999; etc). Those will be testing
   * the remove() method that takes an index as a parameter. Then, I will test
   * the remove() method that takes an object as a parameter. I will first
   * clear the list using the clear() method, then add a few numbers that I
   * will know. I will then try to remove a number that I know is not in the
   * List12, and it should return false. I will then try to remove a number
   * that IS in the list, and it should return true. I will then check each
   * number in the list to make sure it all is the same as what I expect it
   * to be, after first verifying that the size is what it should be. Lastly,
   * I will check for IndexOutOfBoundsException for a negative index as well as
   * for an index that is larger than the size of the List12.
   */
  public void testAddRemoveSize()
  {
    // Try removing some item from empty list (should be fail, so false)
    assertFalse(intL.remove(new Integer(420)));
    assertFalse(stringL.remove("420"));

    // ***TEST REMOVE remove(int index)***
    int maxNumber = 0;

    // Add #'s 1-1000, and check size each time
    for(int i = 1; i <= 1000; i++)
    {
      intL.add(i);
      assertEquals(new Integer(intL.size()),new Integer(++maxNumber));
    }

    // Remove all items, each time from 1st position, and check size
    for(int i = 0; i < 1000; i++)
    {
      assertEquals(intL.remove(0),new Integer(i+1));
      assertEquals(new Integer(intL.size()),new Integer(--maxNumber));
    }

    // ***TEST REMOVE remove(Object o) (FIRST OCCURRENCE)***
    // Clear the List12
    intL = new List12<Integer>();

    // Add some numbers that I know
    intL.add(1);
    intL.add(2);
    intL.add(420);
    intL.add(3);
    intL.add(4);
    intL.add(420);
    intL.add(5);
    // List should be 1,2,420,3,4,420,5

    // Try to remove a number that isn't in the list (should be false)
    assertFalse(intL.remove(new Integer(69)));

    // Try to remove a number that is in the list (should return true)
    assertTrue(intL.remove(new Integer(420)));

    // List should now be 1,2,3,4,420,5 (so check each item)
    assertEquals(intL.size(),6);   
    assertEquals(intL.get(0),new Integer(1));
    assertEquals(intL.get(1),new Integer(2));
    assertEquals(intL.get(2),new Integer(3));
    assertEquals(intL.get(3),new Integer(4));
    assertEquals(intL.get(4),new Integer(420));
    assertEquals(intL.get(5),new Integer(5));

    // Test IndexOutOfBoundsException in remove(int index) negative
    try
    {
      intL.remove(-1);
      fail();
    }
    catch(IndexOutOfBoundsException e){}

    // Test IndexOutOfBoundsException in remove(int index) > size
    try
    {
      intL = new List12<Integer>();
      intL.remove(1);
      fail();
    }
    catch(IndexOutOfBoundsException e){}
  }

  /**
   * This method will be testing the set() and get() methods. Specifically, I
   * will be calling the add() method to add a bunch of numbers, then call the
   * set() method on each number to reset each number to a different specific
   * value, and then use the get() method on each value to verify that they all
   * equal that test-number I chose. I will then test for
   * IndexOutOfBoundException in the set() method for a negative index and for
   * an index that is larger than the List12 size.
   */
  public void testSetGet()
  {
    // Define a Test Number
    int testNumber = 420;

    // Add the #'s 1-1000 to the List12
    for(int i = 1; i <= 1000; i++)
    {
      intL.add(i);
    }

    // Replace each number with the Test Number
    for(int i = 0; i < 1000; i++)
    {
      intL.set(i,testNumber);
    }

    // Check each number to make sure they all equal the Test Number
    for(int i = 0; i < 1000; i++)
    {
      assertEquals(intL.get(i),new Integer(testNumber));
    }

    // Test IndexOutOfBoundsException in set(int index, E element) negative
    try
    {
      intL.set(-1,420);
      fail();
    }
    catch(IndexOutOfBoundsException e){}

    // Test IndexOutOfBoundsException in set(int index, E element) > size
    try
    {
      intL = new List12<Integer>();
      intL.set(1,420);
      fail();
    }
    catch(IndexOutOfBoundsException e){}
  }

  /**
   * This method will test the iterator() method of the List12 object. It will
   * first add just a test number to intL, then create an interator, and then
   * make sure the iterator is not null. Then, it will test the hasNext(),
   * next(), and remove() methods of the Iterator type object, to make sure we
   * have a valid iterator.
   */
  public void testIterator()
  {
    // Add Test Number to List12
    intL.add(420);

    // Create Iterator
    Iterator<Integer> theIterator = intL.iterator();

    // Check that Iterator is no longer null
    assertNotNull(theIterator);

    // Test hasNext() (should be true)
    assertTrue(theIterator.hasNext());

    // Test next() (should return Test Number)
    assertEquals(theIterator.next(),new Integer(420));

    // Remove Test Number
    theIterator.remove();

    // Test hasNext() (should be false)
    assertFalse(theIterator.hasNext());

    // Test next() (should throw exception)
    try
    {
      theIterator.next();
      fail();
    }
    catch(NoSuchElementException e){}
  }
} // End of public class List12Tester extends junit.framework.TestCase
