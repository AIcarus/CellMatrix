package org.accela.cellmatrix.strategy;

import java.awt.event.MouseEvent;

import org.accela.cellmatrix.ClickStrategy;

public class PopuClickStrategy implements ClickStrategy {

	@Override
	public int calCellVal(MouseEvent e, int cellVal) {
		if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
			return cellVal += 500;
		} else if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
			return Math.max(cellVal - 50, 0);
		} else {
			return cellVal;
		}
	}

}
