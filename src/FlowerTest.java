import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;


public class FlowerTest {

	@Test
	public final void testDataSet1() {
		Flower[] trainedFlowers = FlowerParser.parse("irisTrainingSet.data");
		Flower[] testFlowers = FlowerParser.parse("irisTestSet.data");
		Flower test = testFlowers[0];
		Flower[] norm = test.kNN(trainedFlowers, 10);
		String labelForTestFlower = test.predict(norm);
		System.out.println(labelForTestFlower);
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
