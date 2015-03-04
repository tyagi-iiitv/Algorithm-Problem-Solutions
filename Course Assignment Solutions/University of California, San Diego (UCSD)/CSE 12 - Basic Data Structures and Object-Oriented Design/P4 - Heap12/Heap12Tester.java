/**
 * @author Alexander Moshiri <cs12smj>
 * @since 2013-05-10
 *
 * This is a tester for the Heap12 class.
 */

import java.util.*;

public class Heap12Tester extends junit.framework.TestCase
{
  private PQueue<Integer> intHeap;

  /**
   * Run the JUnit TestRunner to test the Heap12 class (graphical).
   */
  public static void main(String args[])
  {
    junit.swingui.TestRunner.main(new String[] {"Heap12Tester"});
  }

  /**
   * Set up the test environment prior to each test method. For my setup, I
   * create a Heap12 of Integer objects (named intHeap).
   */
  public void setUp()
  {
    intHeap = new Heap12<Integer>();
  }

  /**
   * Tear down the test environment after each test method. In my case, I just
   * set the Heap12 object I created (intHeap) to null. Then, the setup method
   * can set it back up again, if need be.
   */
  public void tearDown()
  {
    intHeap = null;
  }

  /**
   * This method adds a bunch of Integer objects (0-999) to the Heap12. It will
   * check size(), peek(), and isEmpty() at each add().
   */
  public void testAddSizePeekEmpty()
  {
    assertEquals(0,intHeap.size());
    assertTrue(intHeap.isEmpty());
    assertNull(intHeap.peek());
    assertNull(intHeap.remove());

    // Add 0-999, test peek(), test size(), and test isEmpty()
    for(int i = 0; i < 1000; i++)
    {
      intHeap.add(new Integer(i));
      assertEquals(new Integer(i),intHeap.peek());
      assertEquals((i+1),intHeap.size());
      assertFalse(intHeap.isEmpty());
    }

    // Now remove all of the elements
    for(int i = 999; i >= 0; i--)
    {
      assertEquals(new Integer(i),intHeap.remove());
      if(i > 0){assertEquals(new Integer(i-1),intHeap.peek());}
      assertEquals(i,intHeap.size());
    }

    // Make sure that the Heap12 is empty
    assertEquals(0,intHeap.size());
    assertTrue(intHeap.isEmpty());
    assertNull(intHeap.peek());
    assertNull(intHeap.remove());

    // Now add 0-999, but in reverse order (should add them sorted)
    for(int i = 999; i >= 0; i--)
    {
      intHeap.add(new Integer(i));
      assertEquals(new Integer(999),intHeap.peek()); // largest ALWAYS 999
      assertEquals((1000-i),intHeap.size());
      assertFalse(intHeap.isEmpty());
    }

    // Now remove all elements
    for(int i = 999; i >= 0; i--)
    {
      assertEquals(new Integer(i),intHeap.remove());
      assertEquals(i,intHeap.size());
      if(i > 0){assertEquals(new Integer(i-1),intHeap.peek());}
    }

    // Make sure that the Heap12 is empty
    assertEquals(0,intHeap.size());
    assertTrue(intHeap.isEmpty());
    assertNull(intHeap.peek());
    assertNull(intHeap.remove());
  }

  /**
   * This method tests correct handling of equal values. Heap12 should sort
   * in nondescending order (which allows duplicates), NOT in ascending order
   * (which does not allow duplicates).
   */
  public void testDuplicates()
  {
    // Add 0-999 twice (have it sort itself)
    for(int i = 0; i < 1000; i++)
    {
      intHeap.add(new Integer(i));
      assertEquals(new Integer(i),intHeap.peek());
      assertEquals((i+1),intHeap.size());
    }
    for(int i = 0; i < 1000; i++)
    {
      intHeap.add(new Integer(i));
      assertEquals(new Integer(999),intHeap.peek());
      assertEquals((1001+i),intHeap.size());
    }

    assertEquals(2000,intHeap.size());

    // Now remove all elements, and check values along the way
    for(int i = 1999; i >= 0; i--)
    {
      assertEquals(new Integer(i/2),intHeap.remove());
    }

    assertTrue(intHeap.isEmpty());
  }

  /**
   * This method tests the equals() method of the Heap12 object. I will add
   * the numbers 0-999 to one Heap12, then add 0-999 in reverse order to the
   * other. That one SHOULD automatically sort them, so the two Heap12 objects
   * SHOULD be equal. I will also test the hashCode() method to make sure the
   * hashcodes are equal. I will also test the constructor that takes an
   * existing Heap12 as an argument.
   */
  public void testEqualsHash()
  {
    // Create my first 2 test objects
    PQueue<Integer> test1 = new Heap12<Integer>();
    PQueue<Integer> test2 = new Heap12<Integer>();
    PQueue<Integer> testNull = new Heap12<Integer>();
    PQueue<Character> testchar = new Heap12<Character>();

    // Verify all are equal
    assertTrue(intHeap.equals(test1));
    assertTrue(intHeap.equals(test2));
    assertTrue(intHeap.equals(testNull));
    assertTrue(intHeap.equals(testchar));

    for(int i = 0; i < 1000; i++)
    {
      intHeap.add(new Integer(i));
      test1.add(new Integer(i));
      test2.add(new Integer(999-i));
    }
    // Now, all 3 SHOULD have the numbers 0-999 (test2 should sort)

    // For test3, add WAY MORE elements, then remove too
    for(int i = 0; i < 10000; i++)
    {
      testNull.add(new Integer(i));
    }

    // Now remove enough so that it has only 0-999
    for(int i = 0; i < 9000; i++)
    {
      testNull.remove();
    }
    // Now test3 should have numbers 0-999, but many empty spaces

    // Create my last 2 test objects (using copy constructor)
    PQueue<Integer> test3 = new Heap12<Integer>((Heap12)intHeap);
    PQueue<Integer> test4 = new Heap12<Integer>((Heap12)test2);

    // Verify that they all have the same hashcode
    int testHash = intHeap.hashCode();
    assertTrue(testHash == test1.hashCode());
    assertFalse(testHash == test2.hashCode());
    assertTrue(testHash == test3.hashCode());
    assertFalse(testHash == test4.hashCode());
    assertTrue(testHash == testNull.hashCode());
    assertTrue(test2.hashCode() == test4.hashCode());
    

    // Now verify equality
    assertTrue(intHeap.equals(test1));
    assertFalse(intHeap.equals(test2));
    assertTrue(intHeap.equals(test3));
    assertFalse(intHeap.equals(test4));
    assertTrue(intHeap.equals(testNull));
    assertTrue(test2.equals(test4));

    // Remove 1 item from intHeap and make sure now unequal
    intHeap.remove();
    assertFalse(intHeap.equals(test1));
    assertFalse(intHeap.equals(test2));
    assertFalse(intHeap.equals(test3));
    assertFalse(intHeap.equals(test4));
    assertFalse(intHeap.equals(testNull));

    // Verify that hashcode is different now
    testHash = intHeap.hashCode();
    assertFalse(testHash == test1.hashCode());
    assertFalse(testHash == test2.hashCode());
    assertFalse(testHash == test3.hashCode());
    assertFalse(testHash == test4.hashCode());
    assertFalse(testHash == testNull.hashCode());
    assertTrue(test2.hashCode() == test4.hashCode());

    // Now remove 1 item from the rest
    test1.remove();
    test2.remove();
    test3.remove();
    test4.remove();
    testNull.remove();

    // Now verify equality
    assertTrue(intHeap.equals(test1));
    assertFalse(intHeap.equals(test2));
    assertTrue(intHeap.equals(test3));
    assertFalse(intHeap.equals(test4));
    assertTrue(intHeap.equals(testNull));
    assertTrue(test2.equals(test4));

    // Now verify hashcode is equal
    assertTrue(testHash == test1.hashCode());
    assertFalse(testHash == test2.hashCode());
    assertTrue(testHash == test3.hashCode());
    assertFalse(testHash == test4.hashCode());
    assertTrue(testHash == testNull.hashCode());
    assertTrue(test2.hashCode() == test4.hashCode());
  }

  /**
   * This method tests the sort() method of the Heap12 object. Given a specific
   * test array of Integer objects, I pass said array into the sort function
   * and then check for if it's correctly sorted. I will try negative numbers,
   * 0, and positive numbers.
   */
  public void testSort()
  {
    // Create my unsorted array (500 to -500)
    Integer[] testArr = new Integer[1001];
    for(int i = 0; i < 1001; i++)
    {
      testArr[i] = (500-i);
    }

    Heap12.sort(testArr);

    // Verify that they are now correctly in order
    for(int i = 0; i < 1001; i++)
    {
      assertEquals(new Integer(i-500),testArr[i]);
    }
  }

  /**
   * This method tests that the correct exceptions are thrown.
   */
  public void testExceptions()
  {
    Integer   nullInteger = null;
    Integer[] nullArray = null;

    // TEST EXCEPTION OF ADD() (NullPointerException)
    try
    {
      intHeap.add(nullInteger);
      fail();
    }
    catch(NullPointerException e){}

    // TEST EXCEPTION OF SORT() (NullPointerException)
    try
    {
      Heap12.sort(nullArray);
      fail();
    }
    catch(NullPointerException e){}
  }
} // End of public class Heap12Tester extends junit.framework.TestCase
