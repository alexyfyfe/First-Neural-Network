package neuralnetwork_partB;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	// Creating neural network objects
	private static NeuralNetwork synapseNetwork16;
	private static NeuralNetwork synapseNetwork36;
	private static NeuralNetwork synapseNetwork16LoadTest;
	private static NeuralNetwork synapseNetwork36LoadTest;

	private static int[][] output;
	private static MatrixUtility MatrixUtility = new MatrixUtility();

	public static void main(String[] args) {

		
		
		/* QUESTION 1. */
		// Creating Input pattern and Output pattern
		int[][] inputPattern16 = new int[][] { { 1, 0 }, { 1, 0 } };
		int[][] outputPattern16 = new int[][] { { 0, 1 }, { 0, 1 } };

		// Initialise a 16 synapse Neural Network with initial input and output pattern
		synapseNetwork16 = new NeuralNetwork(inputPattern16, outputPattern16);

		// Recalling the neural network to see if it can recall the first patterns
		// output
		output = synapseNetwork16.recall(inputPattern16);
		System.out.println("QUESTION 1");
		MatrixUtility.printResult(inputPattern16,outputPattern16,output);

		/* QUESTION 2. */
		// Creating an incomplete version of the original input pattern
		int[][] incompleteInputPattern16 = new int[][] { { 1, 0 }, { 0, 0 } };
		// Recalling the neural network to see if it can recall based on incomplete
		// pattern
		//Have to set Threshold to 1 to allow for this to work
		synapseNetwork16.setThreshold(1);
		output = synapseNetwork16.recall(incompleteInputPattern16);
		System.out.println("QUESTION 2");
		MatrixUtility.printResult(incompleteInputPattern16,outputPattern16,output);
		
		/* QUESTION 3. */
		// Creating an incomplete version of the original input pattern
		int[][] fuzzyInputPattern16 = new int[][] { { 1, 1 }, { 1, 0 } };
		// Recalling the neural network to see if it can recall based on fuzzy
		// pattern
		synapseNetwork16.setThreshold(2);
		output = synapseNetwork16.recall(fuzzyInputPattern16);
		System.out.println("QUESTION 3");
		MatrixUtility.printResult(fuzzyInputPattern16,outputPattern16,output);

		
		
		
		/* QUESTION 4. */
		// Creating Input pattern and Output pattern
		int[][] inputPattern36 = new int[][] { { 1, 0, 1 }, { 0, 1, 0 } };
		int[][] outputPattern36 = new int[][] { { 0, 1, 0 }, { 1, 0, 1 } };

		// Initialise a 16 synapse Neural Network with initial input and output pattern
		synapseNetwork36 = new NeuralNetwork(inputPattern36, outputPattern36);

		// Creating second Input pattern and Output pattern
		int[][] secondInputPattern36 = new int[][] { { 1, 1, 0 }, { 1, 0, 0 } };
		int[][] secondOutputPattern36 = new int[][] { { 0, 0, 1 }, { 1, 0, 0 } };

		// Teaching neural network second pattern
		synapseNetwork36.Learning(secondInputPattern36, secondOutputPattern36);

		// Recalling both input patterns
		System.out.println("QUESTION 4");
		output = synapseNetwork36.recall(inputPattern36);
		MatrixUtility.printResult(inputPattern36,outputPattern36,output);
		output = synapseNetwork36.recall(secondInputPattern36);
		MatrixUtility.printResult(secondInputPattern36,secondOutputPattern36,output);

		/* QUESTION 5. */
		// Creating an distorted version of the original input pattern
		int[][] distoredInputPattern36 = new int[][] { { 1, 0, 1 }, { 0, 1, 1 } };
		// Recalling the neural network to see if it can recall based on distorted
		// pattern
		output = synapseNetwork36.recall(distoredInputPattern36);
		System.out.println("QUESTION 5");
		MatrixUtility.printResult(distoredInputPattern36,outputPattern36,output);

		/*
		 * Hamming distance between distoredInputPattern36 and inputPattern36 is 1,
		 * Hamming distance between distoredInputPattern36 and secondInputPattern36 is
		 * 5, Therefore the novel pattern was treated as if it was learned pattern with
		 * the smaller hamming distance
		 */

		// Creating an distorted version with hamming distance value the same for both inputs
		int[][] equalHammingInputPattern36 = new int[][] { { 1, 0, 0 }, { 1, 0, 0 } };
		// Recalling the neural network with equal hamming distance pattern
		System.out.println("Hamming distance value equal for both inputs");
		output = synapseNetwork36.recall(equalHammingInputPattern36);
		MatrixUtility.printResult(equalHammingInputPattern36,outputPattern36,output);

		/*
		 * Hamming distance bet equalHammingInputPattern36 and both taught input
		 * patterns is 3 The output from the recall is every position is 0.
		 */

		/* QUESTION 6. */
		// Creating neural network to load test on with a threshold of 3
		System.out.println("QUESTION 6");
		synapseNetwork36LoadTest = new NeuralNetwork(2,3);
		synapseNetwork36LoadTest.setThreshold(3);
		
		//Hard coding inputs and outputs as will not need many
		int[][] loadInputPattern36 = new int[][] { { 1, 0, 1 }, { 0, 1, 0 } };
		int[][] loadOutputPattern36 = new int[][] { { 0, 1, 0 }, { 1, 0, 1 } };
		int[][] loadSecondInputPattern36 = new int[][] { { 1, 1, 0 }, { 1, 0, 0 } };
		int[][] loadSecondOutputPattern36 = new int[][] { { 0, 0, 1 }, { 1, 0, 0 } };
		int[][] loadThirdInputPattern36 = new int[][] { { 1, 1, 0 }, { 1, 0, 0 } };
		int[][] loadThirdOutputPattern36 = new int[][] { { 0, 0, 1 }, { 1, 0, 0 } };
		int[][] loadFourthInputPattern36 = new int[][] { { 1, 1, 1 }, { 0, 0, 0 } };
		int[][] loadFourthOutputPattern36 = new int[][] { { 0, 1, 1 }, { 1, 1, 1 } };
		int[][] loadFithInputPattern36 = new int[][] { { 1, 0, 1 }, { 0, 0, 1 } };
		int[][] loadFithOutputPattern36 = new int[][] { { 1, 0, 0 }, { 0, 0, 1 } };
		int[][] loadSixthInputPattern36 = new int[][] { { 0, 1, 0 }, { 1, 1, 1 } };
		int[][] loadSixthOutputPattern36 = new int[][] { { 1, 0, 1 }, { 1, 1, 1 } };

		
		//Adding all inputs/outputs to hashmap to easily iterate
		HashMap<int[][], int[][]> loadPatterns = new HashMap<>();
		//Hashmap of patterns that have been taught to neural net
		HashMap<int[][], int[][]> loadedPatterns = new HashMap<>();
		loadPatterns.put(loadInputPattern36, loadOutputPattern36);
		loadPatterns.put(loadSecondInputPattern36,loadSecondOutputPattern36);
		loadPatterns.put(loadThirdInputPattern36, loadThirdOutputPattern36);
		loadPatterns.put(loadFourthInputPattern36, loadFourthOutputPattern36);
		loadPatterns.put(loadFithInputPattern36, loadFithOutputPattern36);
		loadPatterns.put(loadSixthInputPattern36, loadSixthOutputPattern36);
		
		//Teaching one at a time and then checking to see if output is correct
		for(Entry<int[][], int[][]> entry : loadPatterns.entrySet()) {
		    int[][] key = entry.getKey();
		    int[][] value = entry.getValue();
		    synapseNetwork36LoadTest.Learning(key, value);
		    if(!loadedPatterns.containsKey(key)) {
		    	 loadedPatterns.put(key, value);
		    }
		    for(Entry<int[][], int[][]> entryLoaded : loadedPatterns.entrySet()) {
			    int[][] keyLoaded = entryLoaded.getKey();
			    int[][] valueLoaded = entryLoaded.getValue();
			    output = synapseNetwork36LoadTest.recall(keyLoaded);
//			    MatrixUtility.printArray2D(keyLoaded);
//			    MatrixUtility.printArray2D(valueLoaded);
//			    MatrixUtility.printArray2D(output);
//			    synapseNetwork36LoadTest.printSynapticMatrix();
			    if(MatrixUtility.numberOfDifferenceBetweenArrays(output, valueLoaded)!=0) {
			    	System.out.println("Errors in the values recalled from matrix at: "+loadPatterns.size()+" number of taught patterns");
			    	System.out.println("Load parameter: "+synapseNetwork36LoadTest.calculateLoadParamter());
			    	System.out.println("Strengthened synapases: "+synapseNetwork36LoadTest.calculateStrengthSynapseFraction());
			    	synapseNetwork36LoadTest.printSynapticMatrix();
			    	break;
			    }
		    }
		}
		
		/* QUESTION 7. */
		// Creating neural network to load test on with a threshold of 1
		System.out.println("QUESTION 7");
		synapseNetwork16LoadTest = new NeuralNetwork(2,2);
		synapseNetwork16LoadTest.setThreshold(1);
		
		//Hard coding inputs and outputs as will not need many
		int[][] loadInputPattern16 = new int[][] { { 1, 0 }, { 1, 0 } };
		int[][] loadOutputPattern16 = new int[][] { { 0, 1 }, { 0, 1 } };
		int[][] loadSecondInputPattern16 = new int[][] { { 0, 0 }, { 1, 0 } };
		int[][] loadSecondOutputPattern16 = new int[][] { { 1,  0}, { 0, 1 } };

		
		//Adding all inputs/outputs to hashmap to easily iterate
		HashMap<int[][], int[][]> loadPatterns16 = new HashMap<>();
		//Hashmap of patterns that have been taught to neural net
		HashMap<int[][], int[][]> loadedPatterns16 = new HashMap<>();
		loadPatterns16.put(loadInputPattern16, loadOutputPattern16);
		loadPatterns16.put(loadSecondInputPattern16, loadSecondOutputPattern16);
	
		
		//Teaching one at a time and then checking to see if output is correct
		for(Entry<int[][], int[][]> entry : loadPatterns16.entrySet()) {
		    int[][] key = entry.getKey();
		    int[][] value = entry.getValue();
		    synapseNetwork16LoadTest.Learning(key, value);
		    if(!loadedPatterns16.containsKey(key)) {
		    	 loadedPatterns16.put(key, value);
		    }
		    for(Entry<int[][], int[][]> entryLoaded : loadedPatterns16.entrySet()) {
			    int[][] keyLoaded = entryLoaded.getKey();
			    int[][] valueLoaded = entryLoaded.getValue();
			    output = synapseNetwork16LoadTest.recall(keyLoaded);
//			    MatrixUtility.printArray2D(keyLoaded);
//			    MatrixUtility.printArray2D(valueLoaded);
//			    MatrixUtility.printArray2D(output);
//			    synapseNetwork16LoadTest.printSynapticMatrix();
			    if(MatrixUtility.numberOfDifferenceBetweenArrays(output, valueLoaded)!=0) {
			    	System.out.println("Errors in the values recalled from matrix at: "+loadPatterns16.size()+" number of taught patterns");
			    	System.out.println("Load parameter: "+synapseNetwork16LoadTest.calculateLoadParamter());
			    	System.out.println("Strengthened synapases: "+synapseNetwork16LoadTest.calculateStrengthSynapseFraction());
			    	synapseNetwork16LoadTest.printSynapticMatrix();
			    	break;
			    }
		    }
		}

	}
}
