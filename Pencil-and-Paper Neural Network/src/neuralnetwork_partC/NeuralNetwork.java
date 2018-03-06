package neuralnetwork_partC;

import java.util.Arrays;

public class NeuralNetwork {

	private static double[] weightArray;
	private static double[][] synapticMatrix;

	private MatrixUtility MatrixUtility = new MatrixUtility();
	private double threshold = 0.9;
	private double learningRate = 0.01;
	
	public NeuralNetwork(int height, int width) {
		synapticMatrix = new double[height*width][height*width];
		for (int i = 0; i < synapticMatrix.length; i++) {
			for (int j = 0; j < synapticMatrix[i].length; j++) {
				synapticMatrix[i][j] = Math.random()*0.1;
			}
		}
		weightArray = new double[height * width];
	}

	public void Learning(double[][] inputPattern, double[][] expectedOutputPattern) {
		// Creating 1D arrays from inputs as easier to handle
		double[] inputPatternArray = MatrixUtility.create1DArrayFrom2D(inputPattern);
		double[] outputPatternArray = new double[inputPatternArray.length];
		double[] expectedOutputPatternArray = MatrixUtility.create1DArrayFrom2D(expectedOutputPattern);
		//MatrixUtility.printArray2D(synapticMatrix);
		double[] integratorArray = calculateIntegratorArray(inputPatternArray);
		//MatrixUtility.printArray1D(integratorArray);
		for (int j = 0; j < outputPatternArray.length; j++) {
			outputPatternArray[j] = Activation(integratorArray[j]);
		}
		
		//System.out.println(Arrays.toString(outputPatternArray));
		double[] differenceArray = MatrixUtility.differenceBetweenArrays(outputPatternArray, expectedOutputPatternArray);
		
		for (int i = 0; i < synapticMatrix.length; i++) {
			//System.out.println("output="+outputPatternArray[i]+" expectOutput="+expectedOutputPatternArray[i]);
			//System.out.println("learning rate="+learningRate+" differenceArray[i]="+differenceArray[i]+" inputPatternArray[i]="+inputPatternArray[i]);
			for (int j = 0; j < synapticMatrix[i].length; j++) {
				if(!Arrays.equals(outputPatternArray, expectedOutputPatternArray)) {
					synapticMatrix[j][i] = synapticMatrix[j][i] + (learningRate * differenceArray[i] * inputPatternArray[i]);
				}
			}
		}
	}

	public double[][] recall(double[][] inputPattern) {
		double[] output = new double[synapticMatrix.length];
		// MatrixUtility.printArray2D(inputPattern);
		double[] inputPatternArray = MatrixUtility.create1DArrayFrom2D(inputPattern);
		// System.out.println("Input Pattern Array");
		// MatrixUtility.printArray1D(inputPatternArray);
		double[] integratorArray = calculateIntegratorArray(inputPatternArray);
		//MatrixUtility.printArray1D(integratorArray);
		for (int j = 0; j < output.length; j++) {
			output[j] = Activation(integratorArray[j]);
		}
		// MatrixUtility.printArray1D(output);
		// System.out.println(inputPattern.length+" "+inputPattern[0].length);
		double[][] output2DArray = MatrixUtility.create2DArrayFrom1D(output, inputPattern.length, inputPattern[0].length);
		// MatrixUtility.printArray2D(output2DArray);

		return output2DArray;
	}

	public static double[][] createSynapticMatrix(double[] inputArray, double[] outputArray) {
		double[][] synapticMatrix = new double[inputArray.length][outputArray.length];
		for (int i = 0; i < synapticMatrix.length; i++) {
			for (int j = 0; j < synapticMatrix[i].length; j++) {
				synapticMatrix[i][j] = inputArray[i] * outputArray[j];
			}
		}
		return synapticMatrix;
	}

	public static double[] calculateIntegratorArray(double[] inputPatternArray) {
		double[] integratorArray = new double[synapticMatrix.length];
		for (int i = 0; i < synapticMatrix.length; i++) {
			double[] tempArray = new double[synapticMatrix.length];
			for (int j = 0; j < synapticMatrix.length; j++) {
				//Set up weights array from synaptic matrix
				weightArray[j] = synapticMatrix[j][i];
			}
			integratorArray[i] = Integrator(inputPatternArray);
		}
		return integratorArray;
	}

	public static double Integrator(double[] firing) {
		double result = 0;
		for (int i = 0; i < synapticMatrix.length; i++) {
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

	public void setThreshold(double[][] inputPattern) {
		for (int i = 0; i < inputPattern.length; i++) {
			for (int j = 0; j < inputPattern[i].length; j++) {
				if (inputPattern[i][j] == 1) {
					threshold++;
				}
			}
		}
	}

	public void printSynapticMatrix() {
		MatrixUtility.printArray2D(synapticMatrix);
	}
	
}
