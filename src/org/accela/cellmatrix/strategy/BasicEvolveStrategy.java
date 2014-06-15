package org.accela.cellmatrix.strategy;

import org.accela.cellmatrix.CellMatrix.NineCells;
import org.accela.cellmatrix.EvolveStrategy;

public class BasicEvolveStrategy implements EvolveStrategy {

	@Override
	public int nextState(NineCells cells) {
		int ret = 0;
		if (3 == cells.countNeighborPos) {
			ret = 1;
		} else if (2 == cells.countNeighborPos) {
			ret = cells.cells[1][1];
		} else {
			ret = 0;
		}

		// if(0==ret){
		// if(Math.random()<0.005){
		// ret=1;
		// }
		// }

		return ret;
	}

}
