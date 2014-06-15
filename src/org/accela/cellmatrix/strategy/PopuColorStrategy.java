package org.accela.cellmatrix.strategy;

import java.awt.Color;

import org.accela.cellmatrix.ColorStrategy;

public class PopuColorStrategy implements ColorStrategy {

	@Override
	public Color calCellColor(int cellVal) {
		double val = Math.min(Math.max(0, cellVal), 10000);
		int clrVal = (int) ((1 - val / 10000) * 238);
		return new Color(clrVal, clrVal, clrVal);
	}

}
