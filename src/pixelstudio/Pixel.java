package pixelstudio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pixel extends JPanel {
	private Pixel pixel = this;
	private boolean filled = false;
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
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (Window.isPaintMode) 
						paintPixel(true);
					else 
						selectPixel(true);
					Window.leftMouseDown = true;
				}
				else if (e.getButton() == MouseEvent.BUTTON3) {
					if (Window.isPaintMode) 
						paintPixel(false);
					else 
						selectPixel(false);
					Window.rightMouseDown = true;
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
				if (Window.leftMouseDown) {
					if (Window.isPaintMode) 
						paintPixel(true);
					else 
						selectPixel(true);
				}
				else if (Window.rightMouseDown) {
					if (Window.isPaintMode) 
						paintPixel(false);
					else 
						selectPixel(false);
				}
			}
		});
		Window.allPixels.add(pixel);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!filled) {
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

	private void paintPixel(boolean filled) {
		pixel.filled = filled;
		if (filled) pixel.setBackground(Window.currentBrushColor);
		if (!filled) pixel.setBackground(Window.TRANSPARENT_COLOR);
	}
	
	private void selectPixel(boolean selected) {
		pixel.selected = selected;
		if (selected && !Window.selectedPixels.contains(pixel)) Window.selectedPixels.add(pixel);
		else if (!selected && Window.selectedPixels.contains(pixel)) Window.selectedPixels.remove(pixel);
		printPixelSelection();
		Window.helpTextLabel.setText(Window.selectedPixels.size() + " pixels selected");
		pixel.repaint();
	}
	
	private void printPixelSelection() {
		System.out.println("--SELECTED PIXELS--");
		for (Pixel p : Window.selectedPixels) {
			System.out.println(p.x + "," + p.y);
		}
		System.out.println();
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
		paintPixel(filled);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		selectPixel(selected);
	}
}
