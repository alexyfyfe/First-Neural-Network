package neuralnetwork_partC;

import java.text.DecimalFormat;

public class MatrixUtility {

	// Transforms a 2Dimentional array to a 1Dimentional array
	public double[] create1DArrayFrom2D(double[][] inputPattern) {
		// printArray2D(array2D);
		double[] array1D = new double[inputPattern.length * inputPattern[0].length];
		for (int i = 0; i < inputPattern.length; i++) {
			for (int j = 0; j < inputPattern[i].length; j++) {
				// System.out.println("i: "+i+" j: "+j+" length: "+array2D[0].length);
				array1D[i * inputPattern[0].length + j] = inputPattern[i][j];
			}
		}
		// printArray1D(array1D);
		return array1D;
	}

	// Transforms and 1Dimensional array to a 2Dimensional array based on given
	// height and width
	public double[][] create2DArrayFrom1D(double[] output, int height, int width) {
		// printArray1D(array1D);
		double[][] array2D = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// System.out.println("i: "+i+" j: "+j+" width: "+width);
				array2D[i][j] = output[i * width + j];
			}
		}
		// printArray2D(array2D);
		return array2D;
	}

	// Outputs to the console an string version of a 2Dimensional array
	public void printArray2D(double[][] array2D) {
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// Outputs to the console an string version of a 1Dimensional array
	public void printArray1D(double[] array1D) {
		for (int i = 0; i < array1D.length; i++) {
			System.out.print(array1D[i] + ", ");
		}
		System.out.println();
		System.out.println();
	}

	// Outputs to console input, expected output, acutal output
	public void printResult(double[][] input, double[][] expectedOutput, double[][] output) {
		System.out.println();
		System.out.println("RESULT");
		System.out.println("------");
		System.out.println("-Input:");
		this.printArray2D(input);
		System.out.println("-Expected Output:");
		this.printArray2D(expectedOutput);
		System.out.println("-Actual Output:");
		this.printArray2D(output);
	}

	// Compares two arrays and returns an array of the differences between values
	public double[] differenceBetweenArrays(double[] outputPatternArray, double[] expectedOutputPatternArray) {
		double[] differenceArray = new double[outputPatternArray.length];

		for (int i = 0; i < outputPatternArray.length; i++) {
			differenceArray[i] = (expectedOutputPatternArray[i] - outputPatternArray[i]);
		}

		return differenceArray;
	}

}
