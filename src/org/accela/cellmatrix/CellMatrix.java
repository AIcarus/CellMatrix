package org.accela.cellmatrix;

public class CellMatrix {
	private int[][] matrix = null;

	private EvolveStrategy strategy = null;

	public CellMatrix(int width, int height, EvolveStrategy strategy) {
		if (null == strategy) {
			throw new NullPointerException("strategy should not be null");
		}
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("width or height illegal");
		}

		this.matrix = new int[height][width];
		this.strategy = strategy;
	}

	public int get(int i, int j) {
		return matrix[i][j];
	}

	public void set(int val, int i, int j) {
		this.matrix[i][j] = val;
	}

	public int getWidth() {
		return this.matrix[0].length;
	}

	public int getHeight() {
		return this.matrix.length;
	}

	public boolean isInBounds(int i, int j) {
		return Common.isInBounds(matrix, i, j);
	}

	public EvolveStrategy getStrategy() {
		return strategy;
	}

	public void update() {
		NineCells nineCells = new NineCells();
		int[][] newMatrix = new int[this.matrix.length][this.matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				nineCells.calNineCells(this.matrix, i, j);
				newMatrix[i][j] = this.strategy.nextState(nineCells);
			}
		}

		this.matrix = newMatrix;
	}

	public static class NineCells {
		public int[][] cells = new int[3][3];

		public int countZero = 0;
		public int countPos = 0;
		public int countNeg = 0;

		public int countNeighborZero = 0;
		public int countNeighborPos = 0;
		public int countNeighborNeg = 0;

		public void clear() {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					cells[i][j] = 0;
				}
			}

			countZero = 0;
			countPos = 0;
			countNeg = 0;

			countNeighborZero = 0;
			countNeighborPos = 0;
			countNeighborNeg = 0;
		}

		public void calNineCells(int[][] matrix, int i, int j) {
			clear();

			for (int ptrX = -1; ptrX <= 1; ptrX++) {
				for (int ptrY = -1; ptrY <= 1; ptrY++) {
					int val = Common.isInBounds(matrix, i + ptrY, j + ptrX) ? matrix[i
							+ ptrY][j + ptrX]
							: 0;

					cells[ptrY + 1][ptrX + 1] = val;
					if (0 == val) {
						countZero++;
					} else if (val < 0) {
						countNeg++;
					} else {
						countPos++;
					}

					countNeighborZero = countZero;
					countNeighborPos = countPos;
					countNeighborNeg = countNeg;
					if (cells[1][1] == 0) {
						countNeighborZero--;
					} else if (cells[1][1] < 0) {
						countNeighborNeg--;
					} else {
						countNeighborPos--;
					}
				}
			}// end of for
		}
	}

}
