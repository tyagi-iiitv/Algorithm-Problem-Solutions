/**
 * @author Alexander Niema Moshiri <cs12smj>
 * @since  2013-04-12
 *
 * This is the class for the <tt>List12</tt> object type. This class will be
 * implementing the <tt>List</tt> object type, but various methods will be
 * overridden.
 */

import java.util.*;
import java.util.List;

/**
 * Outer Class: List12<E>
 */
public class List12<E> implements java.util.List<E>
{
  private SLNode<E> head = new SLNode<E>(null,null); // Head Pointer of List12
  private int size;                                  // Size of List12

  /**
   * Create an empty <tt>List12</tt> (ctor)
   */
  public List12(){}

  /**
   * This method will add the specified element to the end of the list.
   *
   * @param  o object to be appended to this list
   * @return true if the element is added correctly
   */
  public boolean add( E o )
  {
    add(this.size,o);
    return true;
  }

  /**
   * This method will add the specified element to the specified index of the
   * list. Note that the list goes from index 0 to index size-1.
   *
   * @param  index   the index to add the specified element at
   * @param  element the actual element that is to be added
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public void add( int index, E element )
  {
    if(index < 0)              // If Negative Index
    {
      throw new IndexOutOfBoundsException("add(int,E) negative index");
    }
    else if(index > this.size) // If Index > Size
    {
      throw new IndexOutOfBoundsException("add(int,E) index > List12 size");
    }
    else if(index == 0)        // If at first index (start)
    {
      if(size == 0)            // If List is empty
      {
        this.head = new SLNode<E>(element,null);
      }
      else                     // If List is not empty
      {
        this.head = new SLNode<E>(element,this.head);
      }
    }
    else                       // If 0 <= index <= size
    {
      // Find node JUST BEFORE insertion point
      SLNode<E> cursor = head;
      while(--index > 0)
      {
        cursor = cursor.getSuccessor();
      }

      // Create a new node that has old node's successor
      SLNode<E> newnode = new SLNode<E>(element,cursor.getSuccessor());

      // Have old node point to the new node
      cursor.setSuccessor(newnode);
    }
    this.size++;
  }

  /**
   * This method will check if the specified element is contained within the
   * list.
   *
   * @param  o object to be checked
   * @return true if this list contains the specified element
   */
  public boolean contains( Object o )
  {
    for(int i = 0; i < this.size; i++)
    {
      if(get(i).equals(o))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns the element at the specified index. Note that the list
   * goes from index 0 to index size-1.
   *
   * @param  index the index to return an element from
   * @return the element at the specified position in this list
   */
  public E get( int index )
  {
    if(index < 0)               // If Negative Index
    {
      throw new IndexOutOfBoundsException("get(int) negative index");
    }
    else if(index >= this.size) // If Index >= Size
    {
      throw new IndexOutOfBoundsException("get(int) index > List12 size");
    }
    else                        // If index is valid
    {
      // Find node at index and return its data
      SLNode<E> cursor = head;
      while(--index >= 0)
      {
        cursor = cursor.getSuccessor();
      }
      return cursor.getData();
    }
  }

  /**
   * This method returns an Iterator from this list.
   *
   * @return an iterator over the elements in this list in proper sequence
   */
  public Iterator<E> iterator()
  {
    return new List12Iterator();
  }

  /**
   * This method removes the first occurrence of the specified object in this
   * list, if it exists at all.
   *
   * @param  o the object to be removed (if possible) from this list
   * @return true if the specified object existed in the list and was
   *         successfully removed
   */
  public boolean remove( Object o )
  {
    for(int i = 0; i < this.size; i++)
    {
      if(get(i).equals(o))
      {
        remove(i);
        return true;
      }
    }
    return false;
  }

  /**
   * This method removes the element at the specified index of this list and
   * then returns said element.
   *
   * @param  index the index to remove an element at
   * @return the element that was removed
   */
  public E remove( int index )
  {
    if(index < 0)               // If Negative Index
    {
      throw new IndexOutOfBoundsException("remove(int) negative index");
    }
    else if(index >= this.size) // If Index >= Size
    {
      throw new IndexOutOfBoundsException("remove(int) index > List12 size");
    }
    else if(index == 0)
    {
      E temp = head.getData();
      head = head.getSuccessor();
      this.size--;
      return temp;
    }
    else                        // If index is valid
    {
      // Find node JUST BEFORE index and point it to node JUST AFTER index
      SLNode<E> before = head;
      while(--index > 0)
      {
        before = before.getSuccessor();
      }
      E temp = before.getSuccessor().getData();

      before.setSuccessor(before.getSuccessor().getSuccessor());

      this.size--;
      return temp;
    }
  }

  /**
   * This method sets a specified element at the specified index and then
   * returns the element that was previously at said index.
   *
   * @param  index   the index to set the specified element at
   * @param  element the element to set in the specified index
   * @return the element that was previously at the specified index
   */
  public E set( int index, E element )
  {
    if(index < 0)               // If Negative Index
    {
      throw new IndexOutOfBoundsException("set(int,E) negative index");
    }
    else if(index >= this.size) // If Index >= Size
    {
      throw new IndexOutOfBoundsException("set(int,E) index >= List12 size");
    }
    else                        // If in middle of List12
    {
      // Hold old data
      E temp = get(index);

      // Find node at index
      SLNode<E> atIndex = head;
      while(--index >= 0)
      {
        atIndex = atIndex.getSuccessor();
      }

      // Set new data
      atIndex.setData(element);

      // Return old data
      return temp;
    }
  }

  /**
   * This method returns the size of this list.
   *
   * @return the size of this list
   */
  public int size()
  {
    return this.size;
  }

  /* UNUSED METHODS START */
  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public List<E> subList( int fromIndex, int toIndex )
  {throw new UnsupportedOperationException("subList(int,int)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public ListIterator<E> listIterator( int index )
  {throw new UnsupportedOperationException("listIterator(int)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public ListIterator<E> listIterator()
  {throw new UnsupportedOperationException("listIterator()");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public int lastIndexOf( Object o )
  {throw new UnsupportedOperationException("lastIndexOf(Object)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public int indexOf( Object o )
  {throw new UnsupportedOperationException("indexOf(Object)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public void clear()
  {throw new UnsupportedOperationException("clear()");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean retainAll( Collection<?> c )
  {throw new UnsupportedOperationException("retainAll(Collection<?>)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean removeAll( Collection<?> c )
  {throw new UnsupportedOperationException("removeAll(Collection<?>)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean addAll( Collection<? extends E> c )
  {throw new UnsupportedOperationException("addAll(Collection<? extends E>)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean addAll( int index, Collection<? extends E> c)
  {throw new UnsupportedOperationException("addAll(index,Collection)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean containsAll( Collection<?> c )
  {throw new UnsupportedOperationException("containsAll(Collection<?>)");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public <T> T[] toArray( T[] a )
  {throw new UnsupportedOperationException("toArray(T[])");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public Object[] toArray()
  {throw new UnsupportedOperationException("toArray()");}

  /**
   * This method is not supported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean isEmpty()
  {throw new UnsupportedOperationException("isEmpty()");}
  /* UNUSED METHODS END */

  /**
   * Inner Class: SLNode<T>
   */
  private static class SLNode<T>
  {
    private T         data; // The Data
    private SLNode<T> next; // Link to Next Node

    /**
     * Create an empty <tt>SLNode</tt> (ctor)
     */
    public SLNode()
    {
      this.data = null;
      this.next = null;
    }

    /**
     * Create an <tt>SLNode</tt> that stores <tt>theElement</tt> and whose
     * successor is <tt>theSuccessor</tt>.
     *
     * @param theElement   the element to store in this node
     * @param theSuccessor this node's successor
     */
    public SLNode( T theElement, SLNode<T> theSuccessor )
    {
      this.data = theElement;
      this.next = theSuccessor;
    }

    /**
     * Successor Accessor
     */
    public SLNode<T> getSuccessor()
    {
      return this.next;
    }

    /**
     * Successor Mutator
     *
     * @param n the successor node
     */
    public void setSuccessor( SLNode<T> n )
    {
      this.next = n;
    }

    /**
     * This method returns the data of this node.
     *
     * @return the data of this node
     */
    public T getData()
    {
      return this.data;
    }

    /**
     * This method sets the data of this node
     *
     * @param e the data that you want to inject into the node
     */
    public void setData( T e )
    {
      this.data = e;
    }
  } // End of private static class SLNode<T>

  /**
   * Inner Class: List12Iterator<E>
   */
  private class List12Iterator implements java.util.Iterator<E>
  {
    private SLNode<E> next;    // The Node to be visited
    private SLNode<E> last;    // The Node last visited
    private SLNode<E> pred;    // The Predecessor of lastReturned
    private boolean   ranNext; // Has "next()" been run?

    /**
     * Create a <tt>List12Iterator</tt>
     */
    List12Iterator()
    {
      next = head;
    }

    /**
     * This method checks if there are any more items in the iterator
     *
     * @return true if there are any more elements in the iterator
     */
    public boolean hasNext()
    {
      if(next == null)
      {
        return false;
      }
      else
      {
        return true;
      }
    }

    /**
     * This method returns the next element (if any)
     *
     * @returns the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public E next()
    {
      // Throw NoSuchElementException if there are no more elements
      if(next == null)
      {
        throw new NoSuchElementException("No More Elements");
      }

      // Update Pointers
      pred = last;
      last = next;
      next = next.next;

      ranNext = true;
      return last.data;
    }

    /**
     * This method removes from the underlying collection the last element
     * returned by this iterator. This method can be called only once per
     * call to next()
     *
     * @throws IllegalStateException if the next method has not yet been called
     *         or if the remove method has already been called after the last
     *         call to the next() method
     */
    public void remove()
    {
      // Throw exception if next() hasn't been run yet
      if(!ranNext)
      {
        throw new IllegalStateException("next() has not been run");
      }

      // If pred == null (so if at header)
      if(pred == null)
      {
        head = next;
      }

      // If NOT at header
      else
      {
        pred.next = next;
      }
      size--;
      ranNext = false;
    }
  } // End of private class List12Iterator implements java.util.Iterator<T>
} // End of public class List12<E> implements java.util.List<E>
