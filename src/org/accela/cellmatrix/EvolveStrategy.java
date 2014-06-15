package org.accela.cellmatrix;

import org.accela.cellmatrix.CellMatrix.NineCells;

public interface EvolveStrategy {
	public int nextState(NineCells cells);
}
