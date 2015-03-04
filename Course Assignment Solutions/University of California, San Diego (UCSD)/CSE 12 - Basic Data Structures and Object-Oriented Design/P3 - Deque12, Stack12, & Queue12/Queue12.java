/**
 * @author Alexander Niema Moshiri <cs12smj>
 * @since 2013-05-02
 *
 * This is the class for the <tt>Queue12</tt> object type. This class will be
 * implementing the <tt>BoundedQueue</tt> object type. It will be using the
 * Adapter Pattern to use the <tt>Deque12</tt> object type as its underlying
 * data structure.
 */

import java.util.*;

/**
 * Outer Class: Queue12<E>
 */
public class Queue12<E> implements BoundedQueue<E>
{
  private Deque12 deque; // The underlying Deque12 Object

  /**
   * Constructor: Create a Queue12 of the specified size and type
   */
  public Queue12( int inSize )
  {
    // Throw exception if size is negative
    if(inSize < 0)
    {
      throw new IllegalArgumentException("Size must not be negative.");
    }

    this.deque = new Deque12(inSize);
  }

  /**
   * This method will return the capacity of the <tt>Queue12</tt>.
   *
   * @return capacity of the <tt>Queue12</tt>
   */
  public int capacity()
  {
    return this.deque.capacity();
  }

  /**
   * This method will return the current size of the <tt>Queue12</tt>.
   *
   * @return current size of the <tt>Queue12</tt>
   */
  public int size()
  {
    return this.deque.size();
  }

  /**
   * This method will check if this <tt>Queue12</tt> is equivalent to another
   * specified <tt>Queue12</tt> object.
   *
   * @param o the specified object to compare this <tt>Queue12</tt> to
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
      return this.deque.equals(((Queue12)o).deque);
    }
  }

  /**
   * This method will remove the element at the head of the queue.
   *
   * @return the element that was removed
   */
  public E dequeue()
  {
    return (E)this.deque.removeFront();
  }

  /**
   * This method will add the specified element to the tail of the queue.
   *
   * @param e the specified element to be added to the tail of the queue
   * @return <tt>true</tt> if the element was successfully added, otherwise
   *         <tt>false</tt>
   */
  public boolean enqueue( E e )
  {
    return this.deque.addBack(e);
  }

  /**
   * This method will return the element at the head of the queue, or
   * <tt>null</tt> if there are no elements.
   *
   * @return the element at the head of the queue
   */
  public E peek()
  {
    return (E)this.deque.peekFront();
  }
} // End of public class Queue12<E> implements BoundedQueue<E>
