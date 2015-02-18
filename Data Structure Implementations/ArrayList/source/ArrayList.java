/**
 * This is my implementation of an <tt>ArrayList</tt>.
 *
 * @author Alexander Niema Moshiri
 */
public class ArrayList<E> implements java.util.List<E>, java.lang.Iterable<E>, java.lang.Cloneable {
    private E[] arr;  // underlying array behind the ArrayList
    private int size; // number of elements in the ArrayList
    
    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        arr = (E[]) new Object[10]; // default initial capacity is 10
        size = 0;
    }
    
    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    @SuppressWarnings("unchecked")
    public ArrayList( int initialCapacity ) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity is negative");
        }
        arr = (E[]) new Object[initialCapacity]; // defined initial capacity
        size = 0;
    }
    
    /**
     * Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    @SuppressWarnings("unchecked")
    public ArrayList( java.util.Collection<? extends E> c ) {
        if(c == null) {
            throw new NullPointerException("Collection c is null");
        }
        arr = (E[]) new Object[c.size()];
        
        // Add all elements from c to this ArrayList
        java.util.Iterator it = c.iterator();
        while(it.hasNext()) {
            add((E)it.next());
        }
    }
    
    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    public boolean add( E e ) {
        add(size,e);
        return true;
    }
    
    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (<tt>index {@literal <} 0 || index {@literal >} size()</tt>)
     */
    @SuppressWarnings("unchecked")
    public void add( int index, E element ) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        
        // If internal array is full, replace it with one of length 2n
        if(size == arr.length) {
            Object[] temp = new Object[2*arr.length];
            for(int i = 0; i < arr.length; ++i) {
                temp[i] = arr[i];
            }
            arr = (E[])temp;
        }
        
        // Shift all elements over by 1
        for(int i = size; i > index; --i) {
            arr[i] = arr[i-1];
        }
        
        // Insert element into index and increment size
        arr[index] = element;
        ++size;
    }
    
    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's Iterator.
     *
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @SuppressWarnings("unchecked")
    public boolean addAll( java.util.Collection<? extends E> c ) {
        if(c == null) {
            throw new NullPointerException("Collection c is null");
        }
        
        if(c.size() == 0) {
            return false;
        }
        
        // Add all elements from c to this ArrayList
        java.util.Iterator it = c.iterator();
        while(it.hasNext()) {
            add((E)it.next());
        }
        return true;
    }
    
    /**
     * Inserts all of the elements in the specified collection into this list, starting at the specified position.
     *
     * @param index index at which to insert the first element from the specified collection
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws IndexOutOfBoundsException if the index is out of range (<tt>index {@literal <} 0 || index {@literal >} size()</tt>)
     * @throws NullPointerException if the specified collection is null
     */
    @SuppressWarnings("unchecked")
    public boolean addAll( int index, java.util.Collection<? extends E> c ) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        
        if(c == null) {
            throw new NullPointerException("Collection c is null");
        }
        
        if(c.size() == 0) {
            return false;
        }
        
        // If current array is too small, create one that is big enough
        if(size + c.size() > arr.length) {
            Object[] temp = new Object[size+c.size()];
            for(int i = 0; i < arr.length; ++i) {
                temp[i] = arr[i];
            }
            arr = (E[])temp;
        }
        
        // Shift all elements over by |c|
        for(int i = size-1; i >= index; --i) {
            arr[i+c.size()] = arr[i];
        }
        
        // Insert all elements of c
        java.util.Iterator it = c.iterator();
        int pos = index;
        while(it.hasNext()) {
            arr[pos++] = (E)it.next();
        }
        return true;
    }
    
    /**
     * Removes all of the elements from this list.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        arr = (E[]) new Object[arr.length];
    }
    
    /**
     * Returns a shallow copy of this ArrayList instance. (The elements themselves are not copied.)
     *
     * @return a clone of this ArrayList instance
     */
    @SuppressWarnings("unchecked")
    public Object clone() {
        ArrayList<E> out = new ArrayList<>(size);
        for(int i = 0; i < size; ++i) {
            out.add(arr[i]);
        }
        return out;
    }
    
    /**
     * Returns true if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains( Object o ) {
        for(int i = 0; i < size; ++i) {
            if(o == null && arr[i] == null) {
                return true;
            }
            else if(o != null && o.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return true if this collection contains all of the elements in the specified collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolean containsAll( java.util.Collection<?> c ) {
        java.util.Iterator it = c.iterator();
        while(it.hasNext()) {
            if(!contains(it.next())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Increases the capacity of this ArrayList instance, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    @SuppressWarnings("unchecked")
    public void ensureCapacity( int minCapacity ) {
        if(arr.length < minCapacity) {
            Object[] temp = new Object[minCapacity];
            for(int i = 0; i < arr.length; ++i) {
                temp[i] = arr[i];
            }
            arr = (E[])temp;
        }
    }
    
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (<tt>index {@literal <} 0 || index {@literal >=} size()</tt>)
     */
    public E get( int index ) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return arr[index];
    }
    
    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public int indexOf( Object o ) {
        for(int i = 0; i < size; ++i) {
            if(o == null && arr[i] == null) {
                return i;
            }
            else if(o != null && o.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }
    
    /**
     * Private inner class for iterator
     */
    private class ArrayListIterator implements java.util.Iterator<E> {
        private int cursor; // iterator's cursor
        
        public ArrayListIterator() {
            cursor = 0;
        }
        
        public boolean hasNext() {
            return cursor < size;
        }
        
        public E next() {
            if(hasNext()) {
                return arr[cursor++];
            }
            throw new java.util.NoSuchElementException("No more elements");
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }
    
    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf( Object o ) {
        for(int i = size-1; i >= 0; ++i) {
            if(o == null && arr[i] == null) {
                return i;
            }
            else if(o != null && o.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * listIterator() is not supported
     *
     * @throws UnsupportedOperationException because listIterator() is not supported
     */
    public java.util.ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("listIterator() is not supported");
    }
    
    /**
     * listIterator(index) is not supported
     *
     * @throws UnsupportedOperationException because listIterator(index) is not supported
     */
    public java.util.ListIterator<E> listIterator( int index ) {
        throw new UnsupportedOperationException("listIterator(index) is not supported");
    }
    
    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (<tt>index {@literal <} 0 || index {@literal >=} size()</tt>)
     */
    public E remove( int index ) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        
        E val = arr[index];
        for(int i = index; i < size-1; ++i) {
            arr[i] = arr[i+1];
        }
        --size;
        return val;
    }
    
    /**
     * Removes the first occurrence of the specified element from this list, if it is present. If the list does not contain the element, it is unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove( Object o ) {
        int ind = indexOf(o);
        if(ind == -1) {
            return false;
        }
        remove(ind);
        return true;
    }
    
    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    public boolean removeAll( java.util.Collection<?> c ) {
        int oriSize = size;
        java.util.Iterator it = c.iterator();
        while(it.hasNext()) {
            remove(it.next());
        }
        return size != oriSize;
    }
    
    /**
     * Removes from this list all of the elements whose index is between fromIndex, inclusive, and toIndex, exclusive. Shifts any succeeding elements to the left (reduces their index). This call shortens the list by <tt>(toIndex - fromIndex)</tt> elements. (If <tt>toIndex==fromIndex</tt>, this operation has no effect.)
     *
     * @param fromIndex index of first element to be removed
     * @param toIndex index after last element to be removed
     * @throws IndexOutOfBoundsException if fromIndex or toIndex is out of range (<tt>fromIndex {@literal <} 0 || fromIndex {@literal >=} size() || toIndex {@literal >} size() || toIndex {@literal <} fromIndex</tt>)
     */
    public void removeRange( int fromIndex, int toIndex ) {
        if(fromIndex < 0 || fromIndex >= size()) {
            throw new IndexOutOfBoundsException("fromIndex is out of bounds");
        }
        if(toIndex > size() || toIndex < fromIndex) {
            throw new IndexOutOfBoundsException("toIndex is out of bounds");
        }
        
        if(fromIndex != toIndex) {
            int dist = toIndex - fromIndex;
            for(int i = fromIndex; i < toIndex && i + dist < size; ++i) {
                arr[i] = arr[i+dist];
            }
            size -= dist;
        }
    }
    
    /**
     * Retains only the elements in this list that are contained in the specified collection. In other words, removes from this list all of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @SuppressWarnings("unchecked")
    public boolean retainAll( java.util.Collection<?> c ) {
        if(c == null) {
            throw new NullPointerException("Collection c is null");
        }
        
        ArrayList<E> out = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            if(c.contains(arr[i])) {
                out.add(arr[i]);
            }
        }
        
        int oriSize = size;
        arr = (E[]) out.toArray();
        size = out.size();
        return oriSize != size;
    }
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (<tt>index {@literal <} 0 || index {@literal >=} size()</tt>)
     */
    public E set( int index, E element ) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        
        E val = arr[index];
        arr[index] = element;
        return val;
    }
    
    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.)
     * 
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException if an endpoint index value is out of range (<tt>fromIndex {@literal <} 0 || toIndex {@literal >} size</tt>)
     * @throws IllegalArgumentException if the endpoint indices are out of order (<tt>fromIndex {@literal >} toIndex</tt>)
     */
    public java.util.List<E> subList( int fromIndex, int toIndex ) {
        if(fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex is out of bounds");
        }
        if(toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex is out of bounds");
        }
        if(fromIndex > toIndex) {
            throw new IllegalArgumentException("Indices are out of order");
        }
        
        ArrayList<E> out = new ArrayList<>();
        for(int i = fromIndex; i < toIndex; ++i) {
            out.add(arr[i]);
        }
        return out;
    }
    
    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray() {
        Object[] out = new Object[size];
        for(int i = 0; i < size; ++i) {
            out[i] = arr[i];
        }
        return out;
    }
    
    /**
     * toArray(a) is not supported
     *
     * @throws UnsupportedOperationException because toArray(a) is not supported
     */
    public <T> T[] toArray( T[] a ) {
        throw new UnsupportedOperationException("toArray(a) is not supported");
    }
}