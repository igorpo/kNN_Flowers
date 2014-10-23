
import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A binary heap, with the maximum at the root.
 * @param <K> A comparable element stored by this heap.
 * @author CIS 121 Staff
 * @version 2.0 - 10/16/12
 */
public class BinaryMaxHeap<E extends Comparable<? super E>> 
		implements BinaryMaxHeapI<E>{
	E[] arr; // SUPER IMPORTANT: do NOT change this to private! Leave as package-private
	Class<E> type;
	private int n; // size 
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Class<E> type) { 
		this.type = type;
		arr = (E[]) Array.newInstance(type, 127);
		n = 0;
	}

	@Override
	/**
	 * @return true if this heap is empty.
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	/**
     * Removes the maximum key in this heap, and returns it.
     * @return The maximum key in this heap.
     * @throws NoSuchElementException If the heap is empty.
     */
	public E removeMax() throws NoSuchElementException {
		if (n == 0) {
			throw new NoSuchElementException();
		}
		E removedElem = arr[1];
		swap(1, n);
		arr[n--] = null;
		sink(1);
		if (n == arr.length / 4) {
			resize(arr.length / 2);
		}
		return removedElem;
	}

	@Override
	 /**
     * Returns, but does not remove, the maximum key in this heap.
     * @return The maximum key in this heap.
     * @throws NoSuchElementException If the heap is empty.
     */
     public E max() throws NoSuchElementException {
     	if (n == 0) {
     		throw new NoSuchElementException();
     	}
     	return arr[1];
     }

	@Override
	/**
     * Insert the given key into the heap.
     * @param k The key to insert.
     * @throws NullPointerException if e is null.
     */
	public void insert(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (arr.length - 1 == n) {
			resize(2 * arr.length);
		}
		arr[++n] = e;
		swim(n);
	}

	@Override
	/**
     * @return the size of the binary max-heap (i.e. the number of elements)
     *		in it.
     */
	public int size() {
		return n;
	}

	@Override
	/**
     * This would normally be a violation of abstraction, but this is included
     *      solely so we can test your heap implementation.
     * @return arr
     */
    public E[] getUnderlyingArray() {
    	return arr;
    }
	
	/**
	 * Promote a key in the heap
	 * @param k
	 *  	index of key we want to promote
	 */
	void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			swap(k, k / 2);
			k = k / 2;
		}
	}
	
	/**
	 * Demote a key in the heap
	 * @param k
	 * 		index of key to demote
	 */
	void sink(int k) {
		while (2 * k <= n) {
			if (2 * k < n && less(2 * k, 2 * k + 1)) {
				swap(k, 2 * k + 1);
				k = 2 * k + 1;
			} 
			else if (2 * k < n && !less(2 * k, 2 * k + 1)) {
				swap(k, 2 * k);
				k = 2 * k;
			}
			else break;			
		}
//		while (2 * k <= n) {
//			int j = 2 * k;
//			if (j < n && less(j, j + 1)) {
//				j++;
//			}
//			if (!less(k, j)) {
//				break;
//			}
//			swap(k, j);
//			k = j;
//		}
	}
	
	/**
	 * double or halve the array as needed for efficient storage
	 * @param length
	 *   		new length of array (underlying)
	 */
	void resize(int length) {
		@SuppressWarnings("unchecked")
		E[] buffer = (E[]) Array.newInstance(type, length);
		
		//populate array
		for (int i = 1; i <= n; i++) {
			buffer[i] = arr[i];
		}
		
		arr = buffer;
	}
	
	/**
	 * swaps items at low index and high index
	 * @param low
	 *  		index of lower elem of the heap
	 * @param high
	 * 			index of higher elem of the heap
	 */
	void swap(int low, int high) {
		E temp = arr[low];
		arr[low] = arr[high];
		arr[high] = temp;
	}
	
	/**
	 * compares two elements in the underlying array to tell if one comes
	 * before another
	 * @param k
	 *  		index of kth elem
	 * @param j
	 *   		index of jth elem
	 * @return true if kth elem comes before jth elem
	 */
	boolean less(int k, int j) {
		return arr[k].compareTo(arr[j]) < 0;
	}
}
