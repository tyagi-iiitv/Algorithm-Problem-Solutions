/**
 * @author Alexander Niema Moshiri <cs12smj>
 * @since 2013-05-02
 *
 * This is the class for the <tt>Stack12</tt> object type. This class will be
 * implementing the <tt>BoundedStack</tt> object type. It will be using the
 * Adapter Pattern to use the <tt>Deque12</tt> object type as its underlying
 * data structure.
 */

import java.util.*;

/**
 * Outer Class: Stack12<E>
 */
public class Stack12<E> implements BoundedStack<E>
{
  private Deque12 deque; // The underlying Deque12 Object

  /**
   * Constructor: Create a Stack12 of the specified size and type.
   */
  public Stack12( int inSize )
  {
    // Throw exception if size is negative
    if(inSize < 0)
    {
      throw new IllegalArgumentException("Size must not be negative.");
    }

    this.deque = new Deque12(inSize);
  }

  /**
   * This method will return the capacity of the <tt>Stack12</tt>.
   *
   * @return capacity of the <tt>Stack12</tt>
   */
  public int capacity()
  {
    return this.deque.capacity();
  }

  /**
   * This method will return the current size of the <tt>Stack12</tt>.
   *
   * @return current size of the <tt>Stack12</tt>
   */
  public int size()
  {
    return this.deque.size();
  }

  /**
   * This method will reveal the item at the top of the stack, without removing
   * it.
   *
   * @return element at the top of the stack
   */
  public E peek()
  {
    return (E)this.deque.peekBack();
  }

  /**
   * This method will remove the element at the top of the stack and return it.
   *
   * @return the element that was on the top of the stack (now removed)
   */
  public E pop()
  {
    return (E)this.deque.removeBack();
  }

  /**
   * This method will add a specified element to the top of the stack. It will
   * return <tt>true</tt> if the element was added successfully, and it will
   * remove <tt>false</tt> otherwise.
   *
   * @param e the element that is to be added to the top of the stack
   * @return <tt>true</tt> if element was added successfully, <tt>false</tt>
   *         otherwise
   */
  public boolean push( E e )
  {
    return this.deque.addBack(e);
  }

  /**
   * This method will check if this <tt>Stack12</tt> is equivalent to another
   * specified <tt>Stack12</tt> object.
   *
   * @param o the specified object to compare this <tt>Stack12</tt> to
   * @return <tt>true</tt> if the objects are equivalent, or <tt>false</tt> if
   *         they are not
   */
  public boolean equals( java.lang.Object o )
  {
    if(this.getClass() != o.getClass())
    {
      return false;
    }
    else
    {
      return this.deque.equals(((Stack12)o).deque);
    }
  }
} // End of public class Stack12<E> implements BoundedStack<E>
