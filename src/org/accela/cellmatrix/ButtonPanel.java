package org.accela.cellmatrix;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton pauseBtn = new JButton();
	private JButton clearBtn = new JButton("Clear");
	private JButton importBtn = new JButton("Import");
	private boolean paused = true;

	private CellPanel cellPanel = null;

	public ButtonPanel(final CellPanel cellPanel) {
		if (null == cellPanel) {
			throw new NullPointerException("cellPanel should not be null");
		}

		this.cellPanel = cellPanel;

		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(clearBtn);
		this.add(importBtn);
		this.add(pauseBtn);

		this.pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPaused(!paused);
			}
		});
		importBtn.addActionListener(new ImportActionListener());

		this.clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < cellPanel.getModel().getHeight(); i++) {
					for (int j = 0; j < cellPanel.getModel().getWidth(); j++) {
						cellPanel.getModel().set(0, i, j);
					}
				}
				cellPanel.repaint();
			}
		});

		this.setBorder(BorderFactory.createEtchedBorder());
		syncPause();
	}

	private void syncPause() {
		if (paused) {
			pauseBtn.setText("Go");
		} else {
			pauseBtn.setText("Pause");
		}
	}

	public JButton getPauseBtn() {
		return pauseBtn;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
		syncPause();
	}

	private class ImportActionListener implements ActionListener {
		private final JFileChooser fc = new JFileChooser();

		public ImportActionListener() {
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(new FileFilter() {

				public String getExtension(File f) {
					String ext = null;
					String s = f.getName();
					int i = s.lastIndexOf('.');

					if (i > 0 && i < s.length() - 1) {
						ext = s.substring(i + 1).toLowerCase();
					}
					return ext;
				}

				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}

					String extension = getExtension(f);
					if (extension != null) {
						if (extension.equals("tiff") || extension.equals("tif")
								|| extension.equals("gif")
								|| extension.equals("jpeg")
								|| extension.equals("jpg")
								|| extension.equals("png")) {
							return true;
						} else {
							return false;
						}
					}

					return false;
				}

				@Override
				public String getDescription() {
					return "Image Files";
				}

			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component place = (e.getSource() instanceof Component) ? (Component) e
					.getSource() : ButtonPanel.this;
			File file = null;
			int retVal = fc.showOpenDialog(place);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
			}
			if (null == file) {
				return;
			} else if (!file.exists() || file.isDirectory()) {
				return;
			} else {
				// do nothing
			}

			Image img = new ImageIcon(file.getAbsolutePath()).getImage();
			BufferedImage bimg = new BufferedImage(cellPanel.getModel()
					.getWidth(), cellPanel.getModel().getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bimg.createGraphics();
			g.drawImage(img, 0, 0, bimg.getWidth(), bimg.getHeight(),
					ButtonPanel.this);
			g.dispose();

			int[] imgData = new int[cellPanel.getModel().getWidth()
					* cellPanel.getModel().getHeight()];
			bimg.getData().getDataElements(0, 0, bimg.getWidth(),
					bimg.getHeight(), imgData);

			// long avgGClr = 0;
			// for (int i = 0; i < cellPanel.getModel().getHeight(); i++) {
			// for (int j = 0; j < cellPanel.getModel().getWidth(); j++) {
			// Color clr = new Color(imgData[i
			// * cellPanel.getModel().getWidth() + j]);
			// int gClr = (clr.getRed() + clr.getGreen() + clr.getBlue())
			// * 255 / (255 + 255 + 255);
			// avgGClr += gClr;
			// }
			// }
			// avgGClr /= cellPanel.getModel().getHeight()
			// * cellPanel.getModel().getWidth();

			for (int i = 0; i < cellPanel.getModel().getHeight(); i++) {
				for (int j = 0; j < cellPanel.getModel().getWidth(); j++) {
					Color clr = new Color(imgData[i
							* cellPanel.getModel().getWidth() + j]);
					int gClr = (clr.getRed() + clr.getGreen() + clr.getBlue())
							* 255 / (255 + 255 + 255);
					// cellPanel.getModel().set(gClr > avgGClr ? 0 : 10000, i,
					// j);
					cellPanel.getModel().set(gClr * 10000 / 255, i, j);
				}
			}

			cellPanel.repaint();
		}
	}
}
