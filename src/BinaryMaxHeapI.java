import java.util.NoSuchElementException;

/**
 * A binary heap, with the maximum at the root.
 * @param <K> A comparable element stored by this heap.
 * @author CIS-121 Staff
 * @version 2.0 - 10/16/12
 */
public interface BinaryMaxHeapI<E extends Comparable<? super E>>
{
	/**
	 * Returns true if this heap is empty.
	 * @return true if this heap is empty.
	 */
    public boolean isEmpty();
    
    /**
     * Removes the maximum key in this heap, and returns it.
     * @return The maximum key in this heap.
     * @throws NoSuchElementException If the heap is empty.
     */
    public E removeMax() throws NoSuchElementException;

    /**
     * Returns, but doe snot remove, the maximum key in this heap.
     * @return The maximum key in this heap.
     * @throws NoSuchElementException If the heap is empty.
     */
    public E max() throws NoSuchElementException;
    
    /**
     * Insert the given key into the heap.
     * @param e The key to insert.
     * @throws NullPointerException if e is null.
     */
    public void insert(E e);

    /**
     * @return the size of the binary min-heap (i.e. the number of elements)
     *		in it.
     */
    public int size();

    /**
     * This would normally be a violation of abstraction, but this is included
     *      solely so we can test your heap implementation.
     * @return arr
     */
    public E[] getUnderlyingArray();
}
