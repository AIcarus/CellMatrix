package org.accela.cellmatrix;

import javax.swing.JFrame;

import org.accela.cellmatrix.strategy.BasicClickStrategy;
import org.accela.cellmatrix.strategy.BasicColorStrategy;
import org.accela.cellmatrix.strategy.BasicEvolveStrategy;

public class Main {
	public static void main(String[] args) {
		final int WIDTH = 64;
		final int HEIGHT = 64;
		final int UPDATE_INTERVAL = 50;

		CellMatrix model = new CellMatrix(WIDTH, HEIGHT,
				new BasicEvolveStrategy());

		CellPanel cellPanel = new CellPanel(model, new BasicColorStrategy(),
				new BasicClickStrategy());
		ButtonPanel btnPanel = new ButtonPanel(cellPanel);
		MainPanel panel = new MainPanel(btnPanel, cellPanel, UPDATE_INTERVAL);

		JFrame frame = new JFrame();
		frame.setTitle("Cell Matrix Game");
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
