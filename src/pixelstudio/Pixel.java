package pixelstudio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pixel extends JPanel {
	private Pixel pixel = this;
	private boolean blank = true;
	private boolean selected = false;
	private int x;
	private int y;
	
	public Pixel(int xLoc, int yLoc) {
		super();
		x = xLoc;
		y = yLoc;
		pixel.setBorder(Window.PIXEL_BORDER);
		pixel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && Window.isPaintMode) {
					blank = false;
					pixel.setBackground(Window.currentBrushColor);
					Window.leftMouseDown = true;
				}
				else if (e.getButton() == MouseEvent.BUTTON3 && Window.isPaintMode) {
					blank = true;
					pixel.setBackground(Window.TRANSPARENT_COLOR);
					Window.rightMouseDown = true;
				}
				else if (e.getButton() == MouseEvent.BUTTON1 && !Window.isPaintMode) {
					selected = !selected;
					if (selected) {
						Window.selectedPixels.add(pixel);
						printPixelSelection();
						pixel.repaint();
					}
					else if (!selected) {
						Window.selectedPixels.remove(pixel);
						printPixelSelection();
						pixel.repaint();
					}
					Window.leftMouseDown = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					Window.leftMouseDown = false;
				else if (e.getButton() == MouseEvent.BUTTON3)
					Window.rightMouseDown = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) { 
				Window.mousePositionLabel.setText(xLoc + ":" + yLoc);
				if (Window.leftMouseDown && Window.isPaintMode) {
					blank = false;
					pixel.setBackground(Window.currentBrushColor);
				}
				else if (Window.rightMouseDown && Window.isPaintMode) {
					blank = true;
					pixel.setBackground(Window.TRANSPARENT_COLOR);
				}
				else if (Window.leftMouseDown && !Window.isPaintMode) {
					selected = !selected;
					if (selected) {
						Window.selectedPixels.add(pixel);
						printPixelSelection();
						pixel.repaint();
					}
					else if (!selected) {
						Window.selectedPixels.remove(pixel);
						printPixelSelection();
						pixel.repaint();
					}
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (blank) {
			pixel.setBackground(Window.TRANSPARENT_COLOR);
			g.drawLine(0, 0, Window.editorPixelWidth, Window.editorPixelHeight);
			g.drawLine(0, Window.editorPixelHeight, Window.editorPixelWidth, 0);
		}
		if (selected) {
			Color previousColor = g.getColor();
			g.setColor(Window.ACCENT_COLOR);
			g.drawRect(0, 0, Window.editorPixelWidth-1, Window.editorPixelHeight-1);
			g.setColor(Window.SELECTION_COLOR);
			g.fillRect(0, 0, Window.editorPixelWidth, Window.editorPixelHeight);
			g.setColor(previousColor);
		}
	}
	
	private void printPixelSelection() {
		System.out.println("--SELECTED PIXELS--");
		for (Pixel p : Window.selectedPixels) {
			System.out.println(p.x + "," + p.y);
		}
		System.out.println();
	}
}
