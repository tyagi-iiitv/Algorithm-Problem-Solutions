/**
 * A <tt>PQueue</tt> is a priority queue.
 *
 * Elements in a <tt>PQueue</tt> must be comparable to each other with respect
 * to their "priority".  In particular, the elements must be instances of
 * a type <tt>E</tt> that implements the interface
 * <tt>Comparable&lt;? super E&gt;</tt>.
 * Then the natural ordering imposed by the <tt>compareTo()</tt> of
 * the elements determines their priority,
 * with <b>larger</b> elements having <b>higher</b> priority.
 * 
 * <p>An implementation of <tt>PQueue</tt> must allow duplicate elements,
 * but should not permit <tt>null</tt> elements, since some <tt>PQueue</tt>
 * methods use <tt>null</tt> as a signalling return value.
 *
 * <p>In addition to the instance methods shown here, a class implementing
 * the <tt>PQueue</tt> interface must provide a static method with this signature:
 * 
 *  <pre>public static &lt;T extends Comparable&lt;? super T&gt;&gt; void sort(T[] a)</pre>
 *
 * The <tt>sort()</tt> method should use the heapsort algorithm to sort its
 * argument array in nondecreasing order according to the natural ordering
 * of its elements.  It is understood that this will create a <tt>PQueue</tt>
 * object and use the <tt>PQueue</tt> instance methods to perform the sort, but without
 * allocating a new array; the sort will be done 'in place' in the argument
 * array.
 */
public interface PQueue<E extends Comparable<? super E>> {
  
  /**
   * Adds the specified element to this PQueue.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the element has been added to this PQueue,
   * none of the other elements have been changed,
   * and the size is increased by 1.
   * @param e The element to add.
   * @throws NullPointerException if the specified element is <tt>null</tt>.
   */
  public void add(E e);

  /**
   * Removes and returns the highest priority element in this PQueue.
   * If more than one element has the same highest priority, one
   * of them is removed and returned.
   * <br>PRECONDITION: this PQueue contains at least one element.
   * <br>POSTCONDITION: the highest priority element has been removed from
   * this PQueue, none of the other elements have been changed,
   * and the size is decreased by 1.
   * @return The highest priority element in this PQueue, or <tt>null</tt> if none.
   */
  public E remove();

  /**
   * Returns the element in this PQueue that would be returned by <tt>remove()</tt>.
   * <br>PRECONDITION: this PQueue contains at least one element.
   * <br>POSTCONDITION: the PQueue is unchanged.
   * @return The highest priority element in this PQueue, or <tt>null</tt> if none.
   * @see #remove()
   */
  public E peek();

  /**
   * Returns the number of elements in this PQueue.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the PQueue is unchanged.
   * @return the number of elements in this PQueue.
   */
  public int size();

  /**
   * Determine if this PQueue contains any elements.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the PQueue is unchanged.
   * @return <tt>true</tt> if this PQueue is empty, <tt>false</tt> otherwise.
   */
  public boolean isEmpty();


}
