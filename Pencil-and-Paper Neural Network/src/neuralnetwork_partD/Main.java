package neuralnetwork_partD;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	// Creating neural network objects
	private static NeuralNetwork synapseNetwork81;
	
	private static int[][] output;
	private static MatrixUtility MatrixUtility = new MatrixUtility();

	public static void main(String[] args) {

		/* PART D */

		// Initialise a 81 synapse Neural Network with initial input and output pattern
		synapseNetwork81 = new NeuralNetwork(3, 3);

		// Creating Input patternS and Output pattern
		int[][] inputPattern81 = new int[][] { { 1, 0, 1 }, { 1, 1, 0 }, { 0, 0, 1 } };
		int[][] secondInputPattern81 = new int[][] { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } };
		int[][] thirdInputPattern81 = new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
		int[][] outputPattern81 = new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 1, 1 } };

		//Setting threshold to 5
		synapseNetwork81.setThreshold(thirdInputPattern81);
		
		// Teaching neural network second pattern
		System.out.println("Training the neural net...");
		synapseNetwork81.LearningThreeInputs(inputPattern81, secondInputPattern81, thirdInputPattern81, outputPattern81);
		System.out.println("Finished training");

		//synapseNetwork81.printSynapticMatrix();

		// Recalling both input patterns
		System.out.println("Part D");
		output = synapseNetwork81.recall(inputPattern81);
		MatrixUtility.printResult(inputPattern81, outputPattern81, output);
		output = synapseNetwork81.recall(secondInputPattern81);
		MatrixUtility.printResult(secondInputPattern81, outputPattern81, output);
		output = synapseNetwork81.recall(thirdInputPattern81);
		MatrixUtility.printResult(thirdInputPattern81, outputPattern81, output);
		
	}
}
