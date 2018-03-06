package neuralnetwork_partD;

public class MatrixUtility {
	
	//Transforms a 2Dimentional array to a 1Dimentional array
	public int[] create1DArrayFrom2D(int[][] array2D) {
		//printArray2D(array2D);
		int[] array1D = new int[array2D.length * array2D[0].length];
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				//System.out.println("i: "+i+" j: "+j+" length: "+array2D[0].length);
				array1D[i * array2D[0].length + j] = array2D[i][j];
			}
		}
		//printArray1D(array1D);
		return array1D;
	}

	//Transforms and 1Dimensional array to a 2Dimensional array based on given height and width
	public int[][] create2DArrayFrom1D(int[] array1D, int height, int width) {
		//printArray1D(array1D);
		int[][] array2D = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				//System.out.println("i: "+i+" j: "+j+" width: "+width);
				array2D[i][j] = array1D[i * width + j];
			}
		}
		//printArray2D(array2D);
		return array2D;
	}
	
	//Outputs to the console an string version of a 2Dimensional array
	public void printArray2D(int[][] array2D) {
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//Outputs to the console an string version of a 1Dimensional array
	public void printArray1D(int[] array1D) {
		for (int i = 0; i < array1D.length; i++) {
			System.out.print(array1D[i] + ", ");
		}
		System.out.println();
		System.out.println();
	}
	
	//Outputs to console input, expected output, acutal output
	public void printResult(int[][] input, int[][] expectedOutput, int[][] actualOutput) {
		System.out.println();
		System.out.println("RESULT");
		System.out.println("------");
		System.out.println("-Input:");
		this.printArray2D(input);
		System.out.println("-Expected Output:");
		this.printArray2D(expectedOutput);
		System.out.println("-Actual Output:");
		this.printArray2D(actualOutput);
	}
	
}
