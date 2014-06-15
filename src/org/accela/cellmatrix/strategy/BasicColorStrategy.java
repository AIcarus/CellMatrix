package org.accela.cellmatrix.strategy;

import java.awt.Color;

import org.accela.cellmatrix.ColorStrategy;

public class BasicColorStrategy implements ColorStrategy {
	public static final Color BK_COLOR = new Color(238, 238, 238);
	public static final Color FG_COLOR = Color.BLACK;

	@Override
	public Color calCellColor(int cellVal) {
		return 0 == cellVal ? BK_COLOR : FG_COLOR;
	}

}
