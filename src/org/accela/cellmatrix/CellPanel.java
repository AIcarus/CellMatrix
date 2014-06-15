package org.accela.cellmatrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JPanel;

public class CellPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int PANEL_SIZE = 600;

	private ColorStrategy clrStrategy = null;
	private ClickStrategy clickStrategy = null;
	private CellMatrix model = null;

	private int cellSize = 0;

	public CellPanel(CellMatrix model, ColorStrategy clrStrategy,
			ClickStrategy clickStrategy) {
		if (null == model) {
			throw new NullPointerException("null model");
		}
		if (null == clrStrategy) {
			throw new NullPointerException("null clrStrategy");
		}
		if (null == clickStrategy) {
			throw new NullPointerException("null clickStrategy");
		}

		this.model = model;
		this.clrStrategy = clrStrategy;
		this.clickStrategy = clickStrategy;

		this.calCellSize();
		this.setPreferredSize(this.calPreferredSize());

		CellPanelMouseAdapter mouseAdapter = new CellPanelMouseAdapter();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.addMouseWheelListener(mouseAdapter);
	}

	private void calCellSize() {
		this.cellSize = (int) Math.min(
				Math.ceil(1.0 * PANEL_SIZE / this.model.getWidth()),
				Math.ceil(1.0 * PANEL_SIZE / this.model.getHeight()));
	}

	private Dimension calPreferredSize() {
		return new Dimension(this.cellSize * this.model.getWidth(),
				this.cellSize * this.model.getHeight());
	}

	public CellMatrix getModel() {
		return model;
	}

	public ColorStrategy getClrStrategy() {
		return clrStrategy;
	}

	public ClickStrategy getClickStrategy() {
		return clickStrategy;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < this.model.getHeight(); i++) {
			for (int j = 0; j < this.model.getWidth(); j++) {
				Color color = this.clrStrategy.calCellColor(this.model
						.get(i, j));
				g.setColor(color);
				g.fillRect(j * this.cellSize, i * this.cellSize, this.cellSize,
						this.cellSize);
			}
		}
	}

	private Point calMouseEventPos(MouseEvent e) {
		return new Point(e.getX() / this.cellSize, e.getY() / this.cellSize);
	}

	private void handleMousePress(MouseEvent e) {
		Point p = this.calMouseEventPos(e);
		if (this.model.isInBounds(p.y, p.x)) {
			this.model.set(
					this.clickStrategy.calCellVal(e, this.model.get(p.y, p.x)),
					p.y, p.x);
		}
		this.repaint();
	}

	public void update() {
		this.model.update();
		this.repaint();
	}

	private class CellPanelMouseAdapter extends MouseAdapter {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			handleMousePress(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			handleMousePress(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			handleMousePress(e);
		}

	}

}
