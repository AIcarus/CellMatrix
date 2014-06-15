package org.accela.cellmatrix;

import java.awt.event.MouseEvent;

public interface ClickStrategy {
	public int calCellVal(MouseEvent e, int cellVal);
}
