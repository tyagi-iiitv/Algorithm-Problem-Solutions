/**
 * @author Alexander Niema Moshiri <cs12smj>
 * @since 2013-05-02
 *
 * This is the class for the <tt>Deque12</tt> object type. This class will be
 * implementing the <tt>BoundedDeque</tt> object type.
 */

import java.util.*;

/**
 * Outer Class: Deque12<E>
 */
public class Deque12<E> implements BoundedDeque<E>
{
  private E[] valArray; // Array of Values
  private int size;     // Size
  private int frontPos; // Front Index
  private int backPos;  // Back Index

  /**
   * Constructor: Create an array of the specified size and type.
   */
  public Deque12( int inSize )
  {
    // Throw exception if size is negative
    if(inSize < 0)
    {
      throw new IllegalArgumentException("Size must not be negative.");
    }

    this.valArray = (E[]) new Object[inSize];
  }

  /**
   * This method will return the capacity of the <tt>Deque12</tt>.
   *
   * @return capacity of the <tt>Deque12</tt>
   */
  public int capacity()
  {
    return this.valArray.length;
  }

  /**
   * This method will return the current size of the <tt>Deque12</tt>.
   *
   * @return current size of the <tt>Deque12</tt>
   */
  public int size()
  {
    return this.size;
  }

  /**
   * This method checks if this specific <tt>Deque12</tt> is equivalent to a
   * specified other <tt>Deque12</tt>.
   *
   * @param o the other <tt>Deque12</tt> to compare with
   * @return <tt>true</tt> if the 2 <tt>Deque12</tt> objects are equal, or
   *         <tt>false</tt> if they are not
   */
  public boolean equals( java.lang.Object o )
  {
    if(o == null)
    {
      return false;
    }

    // Check o is Deque12
    if(this.getClass() != o.getClass())
    {
      return false;
    }

    // Check size
    else if(this.size != ((Deque12)o).size())
    {
      return false;
    }

    // If size == 0, no matter what type they are, they're the same
    else if(this.size == 0)
    {
      return true;
    }

    // Now to actually test the elements...
    else
    {
      Deque12 other = (Deque12)o;
      int myIndex = this.frontPos;
      int oIndex  = other.frontPos;

      while(myIndex != this.backPos)
      {
        // Check values
        if(!this.valArray[myIndex].equals(other.valArray[oIndex]))
        {
          return false;
        }

        // Increment myIndex
        if(myIndex == (this.capacity()-1))
        {
          myIndex = 0;
        }
        else
        {
          myIndex++;
        }

        // Increment oIndex
        if(oIndex == (other.capacity()-1))
        {
          oIndex = 0;
        }
        else
        {
          oIndex++;
        }
      }

      // Now I'm at the last element (myIndex == backPos), so 1 more check
      if(this.valArray[myIndex] != null &&
         !this.valArray[myIndex].equals(other.valArray[oIndex]))
      {
        return false;
      }

      return true;
    }
  }

  /**
   * This method just returns the element at the back of the <tt>Deque12</tt>.
   *
   * @return the last element of the <tt>Deque12</tt>
   */
  public E peekBack()
  {
    return this.valArray[this.backPos];
  }

  /**
   * This method just returns the element at the front of the <tt>Deque12</tt>.
   *
   * @return the first element of the <tt>Deque12</tt>
   */
  public E peekFront()
  {
    return this.valArray[this.frontPos];
  }

  /**
   * This method removes the last element of the <tt>Deque12</tt> and returns
   * its value. It will return <tt>null</tt> if size is zero.
   *
   * @return the last element (which was removed)
   */
  public E removeBack()
  {
    // Return null if there are no elements
    if(this.size == 0)
    {
      return null;
    }

    // Special Case: only 1 element
    else if(this.size == 1)
    {
      E val = this.valArray[this.backPos];
      this.valArray[this.backPos] = null;
      this.size--;
      return val;
    }

    // All other cases
    else
    {
      // Save value, set position to null, size--
      E val = this.valArray[this.backPos];
      this.valArray[this.backPos] = null;
      this.size--;

      // If at beginning of array, move to index capacity-1 (wrap around)
      if(this.backPos == 0)
      {
        this.backPos = this.capacity() - 1;
      }

      // If at any other index of array, just backPos--
      else
      {
        this.backPos--;
      }

      return val;
    }
  }

  /**
   * This method adds an item to the back of the <tt>Deque12</tt>, assuming
   * there is still space (size != capacity).
   *
   * @param e the specified item to add to the back of the <tt>Deque12</tt>
   * @return <tt>true</tt> if the item was added successfully, otherwise
   *         <tt>false</tt> if it wasn't (if size == capacity)
   */
  public boolean addBack( E e )
  {
    // Throw exception if element is null
    if(e == null)
    {
      if(this.size == this.capacity())
      {
        return false;
      }
      else
      {
        throw new NullPointerException("Null elements are not supported.");
      }
    }

    // Do nothing and return false if size == capacity
    if(this.size == this.capacity())
    {
      return false;
    }

    // Special Case: size == 0 (just store value, don't change indeces)
    else if(this.size == 0)
    {
      this.valArray[this.backPos] = e;
      this.size++;
      return true;
    }

    // All other cases, 0 < size < capacity (store, change indeces)
    else
    {
      // Move backPos index
      if(this.backPos == (this.capacity()-1))
      {
        this.backPos = 0;
      }
      else
      {
        this.backPos++;
      }

      // Store value at new index
      this.valArray[this.backPos] = e;
      this.size++;
      return true;
    }
  }

  /**
   * This method removes the first element of the <tt>Deque12</tt> and returns
   * its value. It will return <tt>null</tt> if size is zero.
   *
   * @return the first element (which was removed)
   */
  public E removeFront()
  {
    // Return null if there are no elements
    if(this.size == 0)
    {
      return null;
    }

    // Special Case: only 1 element
    else if(this.size == 1)
    {
      E val = this.valArray[this.frontPos];
      this.valArray[this.frontPos] = null;
      this.size--;
      return val;
    }

    else
    {
      // Save value, set position to null, size--
      E val = this.valArray[this.frontPos];
      this.valArray[this.frontPos] = null;
      this.size--;

      // If at end of array, move to index 0 (wrap around)
      if(this.frontPos == (this.valArray.length-1))
      {
        this.frontPos = 0;
      }

      // If at any other index of array, just frontPos++
      else
      {
        this.frontPos++;
      }

      return val;
    }
  }

  /**
   * This method adds an item to the front of the <tt>Deque12</tt>, assuming
   * there is still space (size != capacity).
   *
   * @param e the specified item to add to the front of the <tt>Deque12</tt>
   * @return <tt>true</tt> if the item was added successfully, otherwise
   *         <tt>false</tt> if it wasn't (if size == capacity)
   */
  public boolean addFront( E e )
  {
    // Throw exception if element is null
    if(e == null)
    {
      if(this.size == this.capacity())
      {
        return false;
      }
      else
      {
        throw new NullPointerException("Null elements are not supported.");
      }
    }

    // Do nothing and return false if size == capacity
    if(this.size == this.capacity())
    {
      return false;
    }

    // Special Case: size == 0 (just store value, don't change indeces)
    else if(this.size == 0)
    {
      this.valArray[this.frontPos] = e;
      this.size++;
      return true;
    }

    // All other cases: 0 < size < capacity (store value and change indeces)
    else
    {
      // Move frontPos index
      if(frontPos == 0)
      {
        this.frontPos = this.capacity() - 1;
      }
      else
      {
        this.frontPos--;
      }

      // Store value at new index
      this.valArray[frontPos] = e;
      this.size++;
      return true;
    }
  }
} // End of public class Deque12<E> implements BoundedDeque
