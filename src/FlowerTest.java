import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class FlowerTest {
	
	
	@SuppressWarnings("static-access")
	@Test
	public final void testDataSet() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[0];
		Flower[] norm = test.kNN(trainedFlowers, 10);
		String labelForTestFlower = test.predict(norm);
		assertTrue(labelForTestFlower.equals("Iris-setosa"));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public final void testDataSet1() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[10];
		Flower[] norm = test.kNN(trainedFlowers, 20);
		String labelForTestFlower = test.predict(norm);
		assertTrue(labelForTestFlower.equals("Iris-versicolor"));
	}
	
	
	@SuppressWarnings("static-access")
	@Test
	public final void testDataSet2() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[29];
		Flower[] norm = test.kNN(trainedFlowers, 20);
		String labelForTestFlower = test.predict(norm);
		assertTrue(labelForTestFlower.equals("Iris-virginica"));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public final void testDataSetTooMany() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[29];
		Flower[] norm = test.kNN(trainedFlowers, 200);
		String labelForTestFlower = test.predict(norm);
		assertTrue(labelForTestFlower.equals("Iris-setosa"));
	}
	
	@Test
	public final void testDataSetNull() {
		try {
			Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
			Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
			Flower test = testFlowers[29];
			test.kNN(trainedFlowers, -2);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
		
	}
	
	@Test
	public final void testEuclideanNull() {
		try {
			Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
			Flower f = flowers[0];
			Flower[] flowersNorm = f.normalize(flowers);
			flowersNorm[0].computeEuclidean(null);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
		
	}
	
	@Test
	public final void testCompareTo1() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[29];
		Flower[] norm = test.kNN(trainedFlowers, 200);
		assertTrue(norm[0].compareTo(norm[1]) == 1);
	}
	
	@Test
	public final void testCompareTo2() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[29];
		Flower[] norm = test.kNN(trainedFlowers, 200);
		assertTrue(norm[0].compareTo(norm[100]) == -1);
	}

	@Test
	public final void testEuclideanFlowers1And2() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		Flower f = flowers[0];
		Flower[] flowersNorm = f.normalize(flowers);
		double e = flowersNorm[0].computeEuclidean(flowersNorm[2]);
		assertEquals(e, .20902, .0001);
	}
	
	@Test
	public final void testEuclideanFlowersFirstAndLast() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		Flower f = flowers[0];
		Flower[] flowersNorm = f.normalize(flowers);
		double e = flowersNorm[0].computeEuclidean(flowersNorm[120]);
		assertEquals(e, 1.1401, .0001);
	}
}
