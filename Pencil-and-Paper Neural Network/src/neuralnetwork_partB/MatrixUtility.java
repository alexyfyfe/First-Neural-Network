package neuralnetwork_partB;

public class MatrixUtility {

	// Transforms a 2Dimentional array to a 1Dimentional array
	public int[] create1DArrayFrom2D(int[][] array2D) {
		int[] array1D = new int[array2D.length * array2D[0].length];
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				// System.out.println("i: "+i+" j: "+j+" length: "+array2D[0].length);
				array1D[i * array2D[0].length + j] = array2D[i][j];
			}
		}
		return array1D;
	}

	// Transforms and 1Dimensional array to a 2Dimensional array based on given
	// height and width
	public int[][] create2DArrayFrom1D(int[] array1D, int height, int width) {
		int[][] array2D = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// System.out.println("i: "+i+" j: "+j+" width: "+width);
				array2D[i][j] = array1D[i * width + j];
			}
		}
		return array2D;
	}

	// Outputs to the console an string version of a 2Dimensional array
	public void printArray2D(int[][] array2D) {
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// Outputs to the console an string version of a 1Dimensional array
	public void printArray1D(int[] array1D) {
		for (int i = 0; i < array1D.length; i++) {
			System.out.print(array1D[i] + ", ");
		}
		System.out.println();
		System.out.println();
	}

	// Outputs to console input, expected output, acutal output
	public void printResult(int[][] input, int[][] expectedOutput, int[][] actualOutput) {
		System.out.println("RESULT");
		System.out.println("------");
		System.out.println("-Input:");
		this.printArray2D(input);
		System.out.println("-Expected Output:");
		this.printArray2D(expectedOutput);
		System.out.println("-Actual Output:");
		this.printArray2D(actualOutput);
	}

	// Compares two arrays and returns an the number of difference values
	public int numberOfDifferenceBetweenArrays(int[][] outputPatternArray, int[][] expectedOutputPatternArray) {
		int difference = 0;
		for (int i = 0; i < outputPatternArray.length; i++) {
			for (int j = 0; j < outputPatternArray[i].length; j++) {
				if (outputPatternArray[i][j] != expectedOutputPatternArray[i][j]) {
					difference++;
				}
			}
		}
		return difference;
	}

}
