package neuralnetwork_partB;

public class NeuralNetwork {

	private static int[] weightArray;
	private static int[][] synapticMatrix;

	private MatrixUtility MatrixUtility = new MatrixUtility();
	private int threshold = 1;
	private int numberOfTrainingPatterns=0;

	public NeuralNetwork(int height, int width) {
		// Setting up a synaptic matrix
		synapticMatrix = new int[height*width][height*width];
		for (int i = 0; i < synapticMatrix.length; i++) {
			for (int j = 0; j < synapticMatrix[i].length; j++) {
				synapticMatrix[i][j] = 0;
			}
		}
		//Setting up weights
		weightArray = new int[height * width];
		setUpWeights();
	}

	public NeuralNetwork(int[][] inputPattern, int[][] outputPattern) {
		// Setting up synaptic Matrix
		synapticMatrix = new int[inputPattern.length * inputPattern[0].length][outputPattern.length
				* outputPattern[0].length];
		// Setting up weights
		weightArray = new int[inputPattern.length * inputPattern[0].length];
		setUpWeights();
		//Setting threshold to equal number of 1 in input array
		setThreshold(inputPattern);
		//Taking one off threshold to allow it find incomplete versions of input patterns
		threshold--;
		// Teaching Neural Network first pair
		Learning(inputPattern, outputPattern);
	}

	public void Learning(int[][] newinputpattern, int[][] newoutputpattern) {
		numberOfTrainingPatterns++;
//		System.out.println("Synaptic Matrix before learning");
//		MatrixUtility.printArray2D(synapticMatrix);
		// Creating 1D arrays from inputs as easier to handle
		int[] inputPatternArray = MatrixUtility.create1DArrayFrom2D(newinputpattern);
		int[] outputPatternArray = MatrixUtility.create1DArrayFrom2D(newoutputpattern);
		// Creating newSynapticMatrix based on inputs to compare to current synaptic
		// matrix
		int[][] newSynapticMatrix = SynapticMatrix(inputPatternArray, outputPatternArray);
		for (int i = 0; i < synapticMatrix.length; i++) {
			for (int j = 0; j < synapticMatrix[i].length; j++) {
				if (synapticMatrix[i][j] == 1 || newSynapticMatrix[i][j] == 1) {
					synapticMatrix[i][j] = 1;
				} else {
					synapticMatrix[i][j] = 0;
				}
			}
		}
//		System.out.println("New Synaptic Matrix to combine with Original Synaptic Matrix");
//		MatrixUtility.printArray2D(newSynapticMatrix);
//		System.out.println("Synaptic Matrix after learning");
//		MatrixUtility.printArray2D(synapticMatrix);
	}

	public int[][] recall(int[][] inputPattern) {
		int[] output = new int[synapticMatrix.length];
		int[] inputPatternArray = MatrixUtility.create1DArrayFrom2D(inputPattern);
		int[] integratorArray = calculateIntegratorArray(inputPatternArray);
		 //MatrixUtility.printArray1D(integratorArray);
		for (int j = 0; j < output.length; j++) {
			output[j] = Comparator(integratorArray[j], threshold);
		}
		 //MatrixUtility.printArray1D(output);
		 //System.out.println(inputPattern.length+" "+inputPattern[0].length);
		int[][] output2DArray = MatrixUtility.create2DArrayFrom1D(output, inputPattern.length, inputPattern[0].length);		
		return output2DArray;
	}

	//Creating a synaptic matrix based on input and output values
	public static int[][] SynapticMatrix(int[] inputArray, int[] outputArray) {
		int[][] synapticMatrix = new int[inputArray.length][outputArray.length];
		for (int i = 0; i < synapticMatrix.length; i++) {
			for (int j = 0; j < synapticMatrix[i].length; j++) {
				if (inputArray[i] == 1 && outputArray[j] == 1) {
					synapticMatrix[i][j] = 1;
				} else {
					synapticMatrix[i][j] = 0;
				}
			}
		}
		return synapticMatrix;
	}

	//Creating an integrator array which sums the columns on a synaptic matrix
	public static int[] calculateIntegratorArray(int[] inputPatternArray) {
		int[] integratorArray = new int[synapticMatrix.length];
		for (int i = 0; i < synapticMatrix.length; i++) {
			int[] tempArray = new int[synapticMatrix.length];
			for (int j = 0; j < synapticMatrix.length; j++) {
				tempArray[j] = synapticMatrix[j][i] * inputPatternArray[j];
			}
			integratorArray[i] = Integrator(tempArray);
		}
		return integratorArray;
	}

	public static int Integrator(int[] firing) {
		int result = 0;
		for (int i = 0; i < synapticMatrix.length; i++) {
			result += weightArray[i] * firing[i];
		}
		return result;
	}

	public static int Comparator(int sum, int threshold) {
		int result = 0;
		if (sum >= threshold) {
			result = 1;
		}
		return result;
	}

	public void setUpWeights() {
		for (int i = 0; i < weightArray.length; i++) {
			weightArray[i] = 1;
		}
	}
	
	public void setThreshold(int[][] inputPattern) {
		for(int i=0;i<inputPattern.length;i++) {
			for(int j=0;j<inputPattern[i].length;j++) {
				if(inputPattern[i][j]==1) {
					threshold++;
				}
			}
		}
	}
	
	public void setThreshold(int num) {
		threshold = num;
	}

	
	public void printSynapticMatrix() {
		MatrixUtility.printArray2D(synapticMatrix);		
	}

	public double calculateLoadParamter() {
		double output=0;
		double inputLength = synapticMatrix[0].length;
		output = numberOfTrainingPatterns/inputLength;
		return output;
	}
	
	public double calculateStrengthSynapseFraction() {
		double output=0;
		double numberOfOnes=0;
		double totalNumberOfValues = synapticMatrix.length*synapticMatrix[0].length;
		for(int i=0;i<synapticMatrix.length;i++) {
			for(int j=0;j<synapticMatrix[i].length;j++) {
				if(synapticMatrix[i][j]==1) {
					numberOfOnes++;
				}
			}
		}
		output = numberOfOnes/totalNumberOfValues;
		return output;		
	}
	
}
