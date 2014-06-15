package org.accela.cellmatrix.strategy;

import org.accela.cellmatrix.EvolveStrategy;
import org.accela.cellmatrix.CellMatrix.NineCells;

public class PopuEvolveStrategy implements EvolveStrategy {

	@Override
	public int nextState(NineCells cells) {

		double val = cells.cells[1][1];
		double avgVal = 0;
		for (int i = 0; i < cells.cells.length; i++) {
			for (int j = 0; j < cells.cells[i].length; j++) {
				avgVal += cells.cells[i][j];
			}
		}
		avgVal /= 9;

		if (val <= 0 && cells.countNeighborPos > 2) {
			val = avgVal * 2;
		}

		double b = 0.6;
		double a = 1 + Math.random() * b - b / 2;
		a = 1;

		double deathRate = (100.0 / (avgVal + 0.01) + 1.0 / 100) / 100;
		deathRate *= a;
		double birthRate = 10.0 / 100;
		birthRate *= a;
		double pressureRate = avgVal / 200000;
		pressureRate *= a;

		double death = deathRate * val;
		double birth = birthRate * val;
		double pressure = pressureRate * val;

		double punish = val >= 10000 ? val : 0;

		int ret = (int) (val - death + birth - pressure - punish);
		ret = ret >= 0 ? ret : 0;

		return ret;
	}

}
