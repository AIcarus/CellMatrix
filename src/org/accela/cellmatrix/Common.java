package org.accela.cellmatrix;

public class Common {
	public static boolean isInBounds(int[][] matrix, int i, int j) {
		if (matrix.length <= 0) {
			throw new IllegalArgumentException(
					"matrix.length is zero, illegal!");
		}

		if (i < 0 || j < 0) {
			return false;
		}
		return i < matrix.length && j < matrix[0].length;
	}
}
