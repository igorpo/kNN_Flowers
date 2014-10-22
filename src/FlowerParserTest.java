import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;


public class FlowerParserTest {
	
	@Test
	public final void testMaxFeatures() {
		Flower[] f = FlowerParser.parse("irisTrainingSet.data");
		double[] a = FlowerParser.findMaxFeatures(f);
		double[] ans = {7.9, 4.4, 6.9, 2.5};
		for (int i = 0; i < 4; i++) {
			assertTrue(a[i] == ans[i]);
		}
	}
	
	@Test
	public final void testMinFeatures() {
		Flower[] f = FlowerParser.parse("irisTrainingSet.data");
		double[] a = FlowerParser.findMinFeatures(f);
		double[] ans = {4.3, 2.0, 1.0, 0.1};
		for (int i = 0; i < 4; i++) {
			assertTrue(a[i] == ans[i]);
		}
	}

	
	@Test
	public final void testParseNull() {
		try {
			FlowerParser.parse(null); 
		} catch (IllegalArgumentException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	
	@Test
	public final void testParseFirstLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[0].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[5.4, 3.7, 1.5, 0.2]"));
	}
	
	@Test
	public final void testParseLAstLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[119].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[6.9, 3.1, 5.4, 2.1]"));
	}
	
	@Test
	public final void testParseSeventhLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[6].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[5.4, 3.9, 1.3, 0.4]"));
	}

	@Test
	public final void testParse100thLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[99].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[6.0, 2.2, 5.0, 1.5]"));
	}
	
	@Test
	public final void testParse20thLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[19].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[4.7, 3.2, 1.6, 0.2]"));
	}
	
	@Test
	public final void testParse57thLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[56].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[6.8, 2.8, 4.8, 1.4]"));
	}
	
	@Test
	public final void testParse87thLine() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		double[] arr = flowers[86].getFeatures();
		assertTrue(Arrays.toString(arr).equals("[4.9, 2.5, 4.5, 1.7]"));
	}
	
	@Test
	public final void testParseSpeciesFirst() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[0].getLabel().equals("Iris-setosa"));
	}
	
	@Test
	public final void testParseSpecies10() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[9].getLabel().equals("Iris-setosa"));
	}
	
	@Test
	public final void testParseSpecies23() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[22].getLabel().equals("Iris-setosa"));
	}
	
	@Test
	public final void testParseSpecies32() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[31].getLabel().equals("Iris-setosa"));
	}
	
	@Test
	public final void testParseSpecies40() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[39].getLabel().equals("Iris-setosa"));
	}
	
	@Test
	public final void testParseSpecies41() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[40].getLabel().equals("Iris-versicolor"));
	}
	
	@Test
	public final void testParseSpecies52() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[51].getLabel().equals("Iris-versicolor"));
	}
	
	@Test
	public final void testParseSpecies61() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[60].getLabel().equals("Iris-versicolor"));
	}
	
	@Test
	public final void testParseSpecies80() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[79].getLabel().equals("Iris-versicolor"));
	}
	
	@Test
	public final void testParseSpecies81() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[80].getLabel().equals("Iris-virginica"));
	}
	
	@Test
	public final void testParseSpecies97() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[96].getLabel().equals("Iris-virginica"));
	}
	
	@Test
	public final void testParseSpecies105() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[104].getLabel().equals("Iris-virginica"));
	}
	
	@Test
	public final void testParseSpecies114() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[113].getLabel().equals("Iris-virginica"));
	}
	
	@Test
	public final void testParseSpecies120() {
		Flower[] flowers = FlowerParser.parse("irisTrainingSet.data");
		assertTrue(flowers[119].getLabel().equals("Iris-virginica"));
	}
}
