package org.accela.cellmatrix;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ButtonPanel btnPanel = null;
	private CellPanel cellPanel = null;

	private Timer timer = null;

	public MainPanel(ButtonPanel btnPanel, CellPanel cellPanel,
			int updateInterval) {
		if (null == btnPanel) {
			throw new NullPointerException("null btnPanel");
		}
		if (null == cellPanel) {
			throw new NullPointerException("null cellPanel");
		}

		this.btnPanel = btnPanel;
		this.cellPanel = cellPanel;

		this.setLayout(new BorderLayout());

		JPanel borderCellPanel = new JPanel();
		borderCellPanel.setLayout(new BorderLayout());
		borderCellPanel.add(this.cellPanel, BorderLayout.CENTER);
		borderCellPanel.setBorder(BorderFactory.createEtchedBorder());

		this.add(borderCellPanel, BorderLayout.CENTER);
		this.add(this.btnPanel, BorderLayout.PAGE_END);

		this.timer = new Timer(updateInterval, new MainPanelActionListener());
		timer.start();
	}

	public ButtonPanel getBtnPanel() {
		return btnPanel;
	}

	public CellPanel getCellPanel() {
		return cellPanel;
	}

	private class MainPanelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!btnPanel.isPaused()) {
				cellPanel.update();
			}
		}
	}
}
