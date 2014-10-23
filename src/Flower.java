import java.util.HashMap;
import java.util.Map;



/**
 * A class to represent a Flower, and various methods on it.
 * @author Max Scheiber (scheiber), 14fa
 */
public class Flower implements Comparable<Flower> {
	
	public double flwr[] = new double[4];
	private String flwrClass;
	private Flower test;
	
	
	/**
	 * @param label The flower's species.
	 * @throws IllegalArgumentException is label is null.
	 */
	public Flower(double f0, double f1, double f2, double f3, String label) {
		if (label == null) {
			throw new IllegalArgumentException();
		}
		flwr[0] = f0;
		flwr[1] = f1;
		flwr[2] = f2;
		flwr[3] = f3;
		flwrClass = label;
	}

	/**
	 * @param features f0, f1, f2, and f3.
	 * @param label The flower's species.
	 * @throws IllegalArgumentException if features is not length 4, or label is null.
	 */
	public Flower(double[] features, String label) {
		if (label == null || features.length != 4) {
			throw new IllegalArgumentException();
		}
		flwr = features;
		flwrClass = label;
	}

	/**
	 * @param training The training data points
	 * @param k parameter for kNN
	 * @return the normalized k nearest Flowers
	 * @throws IllegalArgumentException if training is null/empty or if
	 * 		k is not positive.
	 */
	public Flower[] kNN(Flower[] training, int k) {
		if (training == null || training.length == 0 || k <= 0) {
			throw new IllegalArgumentException();
		}
		BinaryMaxHeap<Flower> b = new BinaryMaxHeap<Flower>(Flower.class);
		if (k > training.length) {
			k = training.length;
		}
		Flower[] nf = this.normalize(training); // norm test flwr is nf[0]
		
		// make each flowers test flower the correct test flower
		for (int i = 1; i < nf.length; i++) {
			nf[i].test = this;
		}
		
		for (int i = 1; i <= k; i++) {
			b.insert(nf[i]);
		}
		
		 
		for (int i = k + 1; i < nf.length; i++) {
			b.insert(nf[i]);	
			b.removeMax();
		}
				
		Flower[] kClosestNormalized = new Flower[b.size()];
		// whatever remains must be the k smallest distances
		for (int i = k - 1; i >= 0 ; i--) {
			kClosestNormalized[i] = b.removeMax();
		}
		return kClosestNormalized;
	}

	/**
	 * @return the majority label of the inputted labels. If there is a tie,
	 *		return any one of the correct labels.
	 * @throws IllegalArgumentException if neighbors is null/empty.
	 */
	public static String predict(Flower[] neighbors) {
		HashMap<String, Integer> labelMap = new HashMap<String, Integer>();
		int highest = 0;
		String mostUsed = "";
		for (int i = 1; i < neighbors.length; i++) {
			if (labelMap.containsKey(neighbors[i].getLabel())) {
				int n = labelMap.get(neighbors[i].getLabel()) + 1;
				labelMap.put(neighbors[i].getLabel(), n);
			} else {
				labelMap.put(neighbors[i].getLabel(), 1);
			}
		}
		
		// find most common label
		for (Map.Entry<String, Integer> species : labelMap.entrySet()) {
			if (species.getValue() > highest) {
				highest = species.getValue();
				mostUsed = species.getKey();
			}
		}
		return mostUsed;
	}

	/**
	 * @return a length-four array of features f0, f1, f2, and f3.
	 */
	public double[] getFeatures() {
		return flwr;
	}

	@Override
	/**
	 * Should compare the distance between this and the test point to g and the test point.
	 */
	public int compareTo(Flower g) {
		
		Flower[] n = {test};
		Flower nTest = test.normalize(n)[0];
		double gToTest = g.computeEuclidean(nTest);
		double fToTest = this.computeEuclidean(nTest);
		
		if (fToTest > gToTest) {
			return 1;
		} else if (fToTest == gToTest) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * @return the Euclidean distance between this and g (NOT the test point and g).
	 * @throws IllegalArgumentException is g is null.
	 */
	double computeEuclidean(Flower g) {
		if (g == null) {
			throw new IllegalArgumentException();
		}
		double[] fFtrs = this.getFeatures();
		double[] gFtrs = g.getFeatures();
		double df0Sq = (fFtrs[0] - gFtrs[0]) * (fFtrs[0] - gFtrs[0]);
		double df1Sq = (fFtrs[1] - gFtrs[1]) * (fFtrs[1] - gFtrs[1]);
		double df2Sq = (fFtrs[2] - gFtrs[2]) * (fFtrs[2] - gFtrs[2]);
		double df3Sq = (fFtrs[3] - gFtrs[3]) * (fFtrs[3] - gFtrs[3]);
		return Math.sqrt(df0Sq + df1Sq + df2Sq + df3Sq);
	}
	
	
	Flower[] normalize(Flower[] flwrs) {
//		System.out.println("Training: " + Arrays.toString(flwrs));
		Flower[] normalized = new Flower[flwrs.length + 1];
		// max/min features of the training data set
		double[] maxFtrs = FlowerParser.findMaxFeatures(flwrs);
		double[] minFtrs = FlowerParser.findMinFeatures(flwrs);

		double[] thisFeatures = this.getFeatures();
		// add test flower to normalized data
		for (int i = 0; i < 4; i++) {
			if (thisFeatures[i] > maxFtrs[i]) {
				maxFtrs[i] = thisFeatures[i];
			} else if (thisFeatures[i] < minFtrs[i]) {
				minFtrs[i] = thisFeatures[i];
			}
		}
		
		// add test flower normalized
		double[] fNorm = new double[4];
		for (int i = 0; i < 4; i++) {
			fNorm[i] = (thisFeatures[i] - minFtrs[i])/(maxFtrs[i] - minFtrs[i]);
		}
		Flower normalizedTestFlwr = new Flower(fNorm, this.getLabel());
		normalized[0] = normalizedTestFlwr;

		for (int i = 0; i < flwrs.length; i++) {
			double[] ftrs = flwrs[i].getFeatures();
			fNorm = new double[4];
			for (int j = 0; j < 4; j++) {
				fNorm[j] = (ftrs[j] - minFtrs[j]) / (maxFtrs[j] - minFtrs[j]);
			}
			Flower normalizedFlower = new Flower(fNorm, flwrs[i].getLabel());
			normalized[i + 1] = normalizedFlower;
		}
		return normalized;
	}
	
	
	/**
	 * gets the species label of the flower
	 * @return species of current flower
	 */
	String getLabel() {
		return flwrClass;
	}
}