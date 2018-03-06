package neuralnetwork_partE;

import java.util.Arrays;

public class NeuralNetwork {

	private static double[] weightArray;
	private static double[][] synapticMatrix1;
	private static double[][] synapticMatrix2;
	private static double[] hiddenLayer;

	private MatrixUtility MatrixUtility = new MatrixUtility();
	private double learningRate = 0.01;

	public NeuralNetwork(int height, int width) {
		synapticMatrix1 = new double[height * width][height * width];
		synapticMatrix2 = new double[height * width][height * width];
		hiddenLayer = new double[height * width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				synapticMatrix1[i][j] = Math.random() * 0.1;
				synapticMatrix2[i][j] = Math.random() * 0.1;
			}
		}
		weightArray = new double[height * width];
	}

	public void Learning(double[][] inputPattern, double[][] expectedOutputPattern) {
		// Creating 1D arrays from inputs as easier to handle
		double[] inputPatternArray = MatrixUtility.create1DArrayFrom2D(inputPattern);
		double[] outputPatternArray = new double[inputPatternArray.length];
		double[] expectedOutputPatternArray = MatrixUtility.create1DArrayFrom2D(expectedOutputPattern);
		// Calculating values for hidden layer first based on inputs
		double[] integratorArray = calculateIntegratorArray(inputPatternArray, synapticMatrix1);
		for (int j = 0; j < outputPatternArray.length; j++) {
			hiddenLayer[j] = Activation(integratorArray[j]);
		}
		// Calculating values for output layer based on hidden layer
		integratorArray = calculateIntegratorArray(inputPatternArray, synapticMatrix2);
		for (int j = 0; j < outputPatternArray.length; j++) {
			outputPatternArray[j] = Activation(integratorArray[j]);
		}

		// System.out.println(Arrays.toString(outputPatternArray));
		double[] differenceArray = MatrixUtility.differenceBetweenArrays(outputPatternArray,
				expectedOutputPatternArray);

		for (int i = 0; i < synapticMatrix1.length; i++) {
			// System.out.println("output="+outputPatternArray[i]+"
			// expectOutput="+expectedOutputPatternArray[i]);
			// System.out.println("learning rate="+learningRate+"
			// differenceArray[i]="+differenceArray[i]+"
			// inputPatternArray[i]="+inputPatternArray[i]);
			for (int j = 0; j < synapticMatrix1[i].length; j++) {
				if (!Arrays.equals(outputPatternArray, expectedOutputPatternArray)) {
					synapticMatrix1[j][i] = synapticMatrix1[j][i]
							+ (learningRate * differenceArray[i] * inputPatternArray[i]);
					synapticMatrix2[j][i] = synapticMatrix2[j][i]
							+ (learningRate * differenceArray[i] * inputPatternArray[i]);
				}
			}
		}
	}

	public double[][] recall(double[][] inputPattern) {
		double[] outputPatternArray = new double[synapticMatrix1.length];
		// MatrixUtility.printArray2D(inputPattern);
		double[] inputPatternArray = MatrixUtility.create1DArrayFrom2D(inputPattern);
		// System.out.println("Input Pattern Array");
		// MatrixUtility.printArray1D(inputPatternArray);
		// Calculating values for hidden layer first based on inputs
		double[] integratorArray = calculateIntegratorArray(inputPatternArray, synapticMatrix1);
		for (int j = 0; j < hiddenLayer.length; j++) {
			hiddenLayer[j] = Activation(integratorArray[j]);
		}
		// Calculating values for output layer based on hidden layer
		integratorArray = calculateIntegratorArray(inputPatternArray, synapticMatrix2);
		for (int j = 0; j < outputPatternArray.length; j++) {
			outputPatternArray[j] = Activation(integratorArray[j]);
		}
		// MatrixUtility.printArray1D(output);
		// System.out.println(inputPattern.length+" "+inputPattern[0].length);
		double[][] output2DArray = MatrixUtility.create2DArrayFrom1D(outputPatternArray, inputPattern.length,
				inputPattern[0].length);
		// MatrixUtility.printArray2D(output2DArray);

		return output2DArray;
	}

	public static double[] calculateIntegratorArray(double[] inputPatternArray, double[][] synapticMatrix) {
		double[] integratorArray = new double[synapticMatrix.length];
		for (int i = 0; i < synapticMatrix.length; i++) {
			double[] tempArray = new double[synapticMatrix.length];
			for (int j = 0; j < synapticMatrix.length; j++) {
				// Set up weights array from synaptic matrix
				weightArray[j] = synapticMatrix[j][i];
			}
			integratorArray[i] = Integrator(inputPatternArray);
		}
		return integratorArray;
	}

	public static double Integrator(double[] firing) {
		double result = 0;
		for (int i = 0; i < firing.length; i++) {
			result += weightArray[i] * firing[i];
		}
		return result;
	}

	public static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public static double Activation(double sum) {
		double result = sigmoid(sum);
		return result;
	}

	public void printSynapticMatrix() {
		System.out.println("MATRIX 1");
		MatrixUtility.printArray2D(synapticMatrix1);
		System.out.println("MATRIX 2");
		MatrixUtility.printArray2D(synapticMatrix2);
	}

}
