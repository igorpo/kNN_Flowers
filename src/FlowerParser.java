import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for parsing input .data files into POJOs.
 * @author Max Scheiber (scheiber), 14fa
 */
public class FlowerParser {
	
	 static Flower[] flwrArray;
	
	/**
	 * In the event of a missing file, print the stack trace and
	 * terminate the program.
	 * 
	 * @param filename the input .data file
	 * @return the parsed data as an array of Flowers
	 * @throws IllegalArgumentException if the file is malformed
	 */
	public static Flower[] parse(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException();
		}
		Scanner sc = null;
		ArrayList<Flower> flowers = new ArrayList<Flower>();
		try {
			sc = new Scanner(new FileInputStream(filename));
		
			String label;
			while (sc.hasNextLine()) {
				double[] features = new double[4];
				String line = sc.nextLine();
				String[] feats = line.split("\t");
				for (int i = 0; i < 4; i++) {
					features[i] = Double.parseDouble(feats[i]);
				}
				label = feats[4];
				Flower flower = new Flower(features, label);
				flowers.add(flower);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		sc.close();
		flwrArray = flowers.toArray(new Flower[flowers.size()]);
		return flwrArray;
	}
	
	/**
	 * Finds maximum features of a data set of Flowers
	 * @param f array of Flowers in data set
	 * @return array of corresponding maximum feature values
	 */
	static double[] findMaxFeatures(Flower[] f) {
		double f0 = 0;
		double f1 = 0;
		double f2 = 0;
		double f3 = 0;
		double[] maxFeatures = new double[4];
		for (int i = 0; i < f.length; i++) {
			double[] features = f[i].getFeatures();
			if (features[0] > f0) {
				f0 = features[0];
			}
			if (features[1] > f1) {
				f1 = features[1];
			}
			if (features[2] > f2) {
				f2 = features[2];
			}
			if (features[3] > f3) {
				f3 = features[3];
			}
		}
		maxFeatures[0] = f0;
		maxFeatures[1] = f1;
		maxFeatures[2] = f2;
		maxFeatures[3] = f3;
		return maxFeatures;
	}
	
	/**
	 * Finds minimum features of a set of flowers
	 * @param f array of Flowers
	 * @return
	 */
	static double[] findMinFeatures(Flower[] f) {
		double f0 = Double.POSITIVE_INFINITY;
		double f1 = Double.POSITIVE_INFINITY;
		double f2 = Double.POSITIVE_INFINITY;
		double f3 = Double.POSITIVE_INFINITY;
		double[] minFeatures = new double[4];
		for (int i = 0; i < f.length; i++) {
			double[] features = f[i].getFeatures();
			if (features[0] < f0) {
				f0 = features[0];
			}
			if (features[1] < f1) {
				f1 = features[1];
			}
			if (features[2] < f2) {
				f2 = features[2];
			}
			if (features[3] < f3) {
				f3 = features[3];
			}
		}
		minFeatures[0] = f0;
		minFeatures[1] = f1;
		minFeatures[2] = f2;
		minFeatures[3] = f3;
		return minFeatures;
	}
}