package neuralnetwork_partE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	// Creating neural network objects
	private static NeuralNetwork synapseNetwork81;
	private static NeuralNetwork synapseNetwork36;

	private static double[][] output;
	private static MatrixUtility MatrixUtility = new MatrixUtility();

	public static void main(String[] args) {
		
		/* PART E */
		
		// Creating Input pattern and Output pattern
		double[][] inputPattern36 = new double[][] { { 0.8, 0.2, 0.9 }, { 0.2, 0.7, 0.4 } };
		double[][] outputPattern36 = new double[][] { { 0.6, 0.4, 0.3 }, { 0.2, 0.3, 0.8 } };

		// Initialise a 36 synapse Neural Network with initial input and output pattern
		synapseNetwork36 = new NeuralNetwork(2, 3);

		// Creating second Input pattern and Output pattern
		double[][] secondInputPattern36 = new double[][] { { 0.7, 0.3, 0.2 }, { 0.1, 0.9, 0.2 } };
		double[][] secondOutputPattern36 = new double[][] { { 0.5, 0.5, 0.5 }, { 0.3, 0.4, 0.9 } };

		// Teaching neural network second pattern
		System.out.println("Training the neural net...");
		for (int i = 0; i < 100000; i++) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			synapseNetwork36.Learning(inputPattern36, outputPattern36);
			synapseNetwork36.Learning(secondInputPattern36, secondOutputPattern36);
		}
		System.out.println("Finished training");
//		System.out.println("SYNAPTIC MATRIXS");
//		synapseNetwork36.printSynapticMatrix();

		// Recalling both input patterns
		System.out.println("Part E");
		output = synapseNetwork36.recall(inputPattern36);
		MatrixUtility.printResult(inputPattern36, outputPattern36, output);
		output = synapseNetwork36.recall(secondInputPattern36);
		MatrixUtility.printResult(secondInputPattern36, secondOutputPattern36, output);

	}
}
