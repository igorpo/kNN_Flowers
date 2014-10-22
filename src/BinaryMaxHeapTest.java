import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;


public class BinaryMaxHeapTest {

	@Test
	public final void testIsEmptyTrue() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		assertTrue(b.isEmpty());
	}
	
	@Test
	public final void testIsEmptyFalse() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(1);
		assertFalse(b.isEmpty());
	}

	@Test
	public final void testRemoveMaxheight3() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(22);
		b.insert(18);
		b.insert(19);
		b.insert(12);
		b.insert(4);
		b.insert(7);
		b.insert(13);
		b.insert(6);
		b.insert(25);
		b.removeMax();
		Integer[] ans = {null, 22, 18, 19, 12, 4, 7, 13, 6};
		for (int i = 1; i <= 8; i++) {
			assertTrue(ans[i] == b.getUnderlyingArray()[i]);
		}
	}
	
	@Test
	public final void testRemoveMaxError() {
		try {
			BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
			b.removeMax(); 
		} catch (NoSuchElementException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	
	@Test
	public final void testRemoveMaxheight3SubordinateDecisionRight() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(25);
		b.insert(24);
		b.insert(22);
		b.insert(7);
		b.insert(12);
		b.insert(14);
		b.insert(4);
		b.insert(1);
		b.removeMax();
		Integer[] ans = {null, 24, 12, 22, 7, 1, 14, 4};
		for (int i = 1; i <= 7; i++) {
			assertTrue(ans[i] == b.getUnderlyingArray()[i]);
		}
	}
	
	@Test
	public final void testRemoveMaxheight3SubordinateDecisionLeft() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(25);
		b.insert(22);
		b.insert(24);
		b.insert(7);
		b.insert(12);
		b.insert(14);
		b.insert(4);
		b.insert(1);
		b.removeMax();
		Integer[] ans = {null, 24, 22, 14, 7, 12, 1, 4};
		for (int i = 1; i <= 7; i++) {
			assertTrue(ans[i] == b.getUnderlyingArray()[i]);
		}
	}

	@Test
	public final void testMax() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(1);
		b.insert(12);
		b.insert(33);
		b.insert(22);
		b.removeMax();
		assertTrue(b.max() == 22);
	}
	
	@Test
	public final void testMaxEmpty() {
		try {
			BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
			b.removeMax(); 
		} catch (NoSuchElementException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	@Test
	public final void testInsertHeapOfThree() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(10);
		b.insert(5);
		b.insert(12);
		Integer[] test = b.getUnderlyingArray();
		Integer[] arrShouldBe = new Integer[127];
		arrShouldBe[1] = 12;
		arrShouldBe[2] = 5;
		arrShouldBe[3] = 10;
		for (int i = 1; i < test.length; i++) {
			assertTrue(test[i] == arrShouldBe[i]);
		}
	}
	
	@Test 
	public final void testInsertNull() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		try {
			b.insert(null);
		} catch (NullPointerException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	
	@Test
	public final void testInsertHeapOfSeven() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(5);
		b.insert(7);
		b.insert(12);
		b.insert(4);
		b.insert(21);
		b.insert(34);
		Integer[] test = b.getUnderlyingArray();
		Integer[] arrShouldBe = new Integer[127];
		arrShouldBe[1] = 34;
		arrShouldBe[2] = 12;
		arrShouldBe[3] = 21;
		arrShouldBe[4] = 4;
		arrShouldBe[5] = 5;
		arrShouldBe[6] = 7;
		for (int i = 1; i < test.length; i++) {
			assertTrue(test[i] == arrShouldBe[i]);
		}
	}

	@Test
	public final void testSize() {
		BinaryMaxHeap<Integer> b = new BinaryMaxHeap<Integer>(Integer.class);
		b.insert(1);
		b.insert(2);
		b.insert(3);
		b.removeMax();
		assertTrue(b.size() == 2);
	}
}
