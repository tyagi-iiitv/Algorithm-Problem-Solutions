/**
 * @author Alexander Moshiri <cs12smj>
 * @since  2013-05-10
 *
 * This is the class for the <tt>Heap12</tt> object type. This class will be
 * implementing the <tt>PQueue</tt> (Priority Queue) interface.
 */

import java.util.*;

/**
 * Outer Class: Heap12<E>
 */
public class Heap12<E extends java.lang.Comparable<? super E>>
                      implements PQueue<E>
{
  private final int DEFAULT_SIZE = 5;

  private E[] heapArray; // HeapArray of Values
  private int size;      // Size

  /**
   * Empty Constructor: Create an empty heap.
   */
  public Heap12()
  {
    this.heapArray = (E[]) new Comparable[this.DEFAULT_SIZE];
  }

  /**
   * Copy Constructor: Make a new heap that copies a specified one (deep copy).
   *
   * @param o the specified <tt>Heap12</tt> object to copy
   */
  public Heap12( Heap12<E> o )
  {
    // Copy other Heap12's size
    this.size = o.size();

    // Create this heapArray to be equal length of other Heap12's heapArray
    this.heapArray = (E[])new Comparable[o.heapArray.length];

    // Copy all of other Heap12's heapArray elements to this heapArray
    for(int i = 0; i < this.size; i++)
    {
      this.heapArray[i] = o.heapArray[i];
    }
  }

  private Heap12( E[] arr )
  {
    // Very Shallow Copy, so directly use arr as this heapArray
    this.heapArray = arr;
  }

  /**
   * This method returns the current size of the <tt>Heap12</tt>.
   *
   * @return the current size
   */
  public int size()
  {
    return this.size;
  }

  /**
   * This method tells the user if the <tt>Heap12</tt> is empty.
   *
   * @return <tt>true</tt> if empty; <tt>false</tt> if not
   */
  public boolean isEmpty()
  {
    return (this.size == 0);
  }

  /**
   * This method checks for equality between this object and another
   * <tt>Heap12</tt> object.
   *
   * @param o the given object to compare this <tt>Heap12</tt> to
   * @return <tt>true</tt> if this equals o, or <tt>false</tt> otherwise
   */
  public boolean equals( java.lang.Object o )
  {
    if(o == null)
    {
      return false;
    }

    // Check o is Heap12
    if(this.getClass() != o.getClass())
    {
      return false;
    }

    // Check size
    else if(this.size != ((Heap12)o).size)
    {
      return false;
    }

    // If size == 0, no matter what type they are, they're the same
    else if(this.size == 0)
    {
      return true;
    }

    // Test backing arrays
    else
    {
      for(int i = 0; i < this.size; i++)
      {
        if(!this.heapArray[i].equals(((Heap12)o).heapArray[i]))
        {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * This method returns a hashcode for this <tt>Heap12</tt> object.
   *
   * @return the hashcode for this <tt>Heap12</tt> object.
   */
  public int hashCode()
  {
    final int prime     = 31;
    final int nullPrime = 37;
    int hashCode = 1;

    for(int i = 0; i < this.size; i++)
    {
      if(this.heapArray[i] != null)
      {
        hashCode = hashCode * prime + this.heapArray[i].hashCode();
      }
      else
      {
        hashCode += nullPrime * i;
      }
    }

    return hashCode;
  }

  /**
   * This method adds a given element to the <tt>Heap12</tt>. If the
   * <tt>Heap12</tt> is at capacity, it will create a new <tt>heapArray</tt> at
   * double the current capacity and copy all values there. ALWAYS the parent
   * MUST be larger than both of its children.
   *
   * @param e the specified element to add to the <tt>Heap12</tt>
   */
  public void add( E e )
  {
    if(e != null)
    {
      // First check for size issues (size == capacity)
      if(this.size == this.heapArray.length)
      {
        // Create new array of 2*length, copy elements, update pointer
        E[] newArray = (E[])new Comparable[2 * this.heapArray.length];
        for(int i = 0; i < this.heapArray.length; i++)
        {
          newArray[i] = this.heapArray[i];
        }
        this.heapArray = newArray;
      }

      // Since size == 1st open index, add at heapArray[size]
      this.heapArray[this.size] = e;

      // Bubble Up to keep PQueue stuff correct
      this.bubbleUp(this.size);

      // Increment size now that addition is complete
      this.size++;
    }

    // If null, throw exception
    else
    {
      throw new NullPointerException("Argument cannot be null.");
    }
  }

  /**
   * This method removes the highest-priority element from the <tt>Heap12</tt>
   * and returns its value.
   *
   * @return the highest-priority element
   */
  public E remove()
  {
    // If empty, return null
    if(this.size == 0)
    {
      return null;
    }

    // If not empty, remove root, put last element into root, and trickle down
    else
    {
      // Store largest value (root) to return later
      E returnVal = this.heapArray[0];

      // Set last item as root (replace current item) and trickle down
      this.heapArray[0] = this.heapArray[this.size-1];
      this.heapArray[this.size-1] = null;
      this.size--;
      this.trickleDown(0);

      return returnVal;
    }
  }

  /**
   * This method returns the value of the highest-priority element of the
   * <tt>Heap12</tt>.
   *
   * @return the value of the highest-priority element of the <tt>Heap12</tt>
   */
  public E peek()
  {
    // If empty, return null
    if(this.size == 0)
    {
      return null;
    }

    // If not empty, return value of root item
    else
    {
      return this.heapArray[0]; // MaxHeap, so root is highest-priority element
    }
  }

  /**
   * This method sorts a given array of Comparable elements, using HeapSort.
   *
   * @param a the specified array
   */
  public static <T extends java.lang.Comparable<? super T>> void sort( T[] a )
  {
    if(a != null)
    {
      // Create new Heap12 using the given array as heapArray (should autosort)
      Heap12<T> myHeap = new Heap12<T>(a);
    
      // Add all elements
      for(int i = 0; i < a.length; i++)
      {
        myHeap.add(a[i]);
      }

      // Now remove elements, adding them to the back (since remove = largest)
      for(int i = a.length-1; i >= 0; i--)
      {
        a[i] = myHeap.remove();
      }
    }
    else
    {
      throw new NullPointerException("Argument cannot be null.");
    }
  }

  /*
   * PRIVATE METHOD: This method, given an index, returns what the parent index
   * should be.
   */
  private int getParent( int index )
  {
    return (index-1)/2;
  }

  /*
   * PRIVATE METHOD: This method, given an index, returns what the left child
   * index should be.
   */
  private int getLeftChild( int index )
  {
    return 2*index+1;
  }

  /*
   * PRIVATE METHOD: This method, given an index, returns what the right child
   * index should be.
   */
  private int getRightChild( int index )
  {
    return 2*index+2;
  }

  /*
   * PRIVATE METHOD: Bubble Up the heap.
   */
  private void bubbleUp( int index )
  {
    int parent = this.getParent(index);
    while(index != 0 && // Do nothing if index is root
          this.heapArray[index] != null && this.heapArray[parent] != null &&
          this.heapArray[index].compareTo(this.heapArray[parent]) > 0)
    {
      // Swap elements
      E swap = this.heapArray[parent];
      this.heapArray[parent] = this.heapArray[index];
      this.heapArray[index] = swap;

      // Reassign index and parent
      index = parent;
      if(index != 0)
      {
        parent = this.getParent(index);
      }
    }
  }

  /*
   * PRIVATE METHOD: Trickle Down the heap.
   */
  private void trickleDown( int index )
  {
    int leftChild = this.getLeftChild(index);
    int rightChild = this.getRightChild(index);
    int largerChild = -1;

    if(leftChild <= this.size && rightChild <= this.size
       && this.heapArray[index] != null)
    {
      // If right child is null, HAVE to try trickle left
      if(this.heapArray[rightChild] == null)
      {
        largerChild = leftChild;
      }

      // If right child isn't null, compare the two children
      else if(this.heapArray[leftChild] != null)
      {
        // If right child is larger than left child
        if(this.heapArray[leftChild].compareTo(this.heapArray[rightChild]) < 0)
        {
          largerChild = rightChild;
        }

        // If left child is larger than right child, or they're equal
        else
        {
          largerChild = leftChild;
        }
      }

      // Compare value at index to larger child (if it's less than larger child)
      if(this.heapArray[leftChild] != null &&
         this.heapArray[index].compareTo(this.heapArray[largerChild]) < 0)
      {
        // Swap elements
        E swap = this.heapArray[largerChild];
        this.heapArray[largerChild] = this.heapArray[index];
        this.heapArray[index] = swap;

        // Reassign index and larger child
        index = largerChild;
        this.trickleDown(index);
      }
    }
  }
} // End of public class Heap12<E> implements PQueue<E>
