package pixelstudio;

import static pixelstudio.View.viewInstance;

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
	
	public Pixel(int xLoc, int yLoc) {
		super();
		pixel.setBorder(viewInstance.PIXEL_BORDER);
		pixel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (viewInstance.isPaintMode) 
						paintPixel(true);
					else 
						selectPixel(true);
					viewInstance.leftMouseDown = true;
				}
				else if (e.getButton() == MouseEvent.BUTTON3) {
					if (viewInstance.isPaintMode) 
						paintPixel(false);
					else 
						selectPixel(false);
					viewInstance.rightMouseDown = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					viewInstance.leftMouseDown = false;
				if (e.getButton() == MouseEvent.BUTTON3)
					viewInstance.rightMouseDown = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) { 
				viewInstance.mouseLocationLabel.setText(xLoc + ":" + yLoc);
				if (viewInstance.leftMouseDown) {
					if (viewInstance.isPaintMode) 
						paintPixel(true);
					else 
						selectPixel(true);
				}
				else if (viewInstance.rightMouseDown) {
					if (viewInstance.isPaintMode) 
						paintPixel(false);
					else 
						selectPixel(false);
				}
			}
		});
		viewInstance.allPixels.add(pixel);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!filled) {
			pixel.setBackground(View.TRANSPARENT_COLOR_1);
			Color previousColor = g.getColor();
			g.setColor(View.TRANSPARENT_COLOR_2);
			g.drawLine(0, 0, viewInstance.editorPixelSize, viewInstance.editorPixelSize);
			g.drawLine(0, viewInstance.editorPixelSize, viewInstance.editorPixelSize, 0);
			g.setColor(previousColor);
		}
		if (selected) {
			Color previousColor = g.getColor();
			g.setColor(View.FOREGROUND_COLOR);
			g.drawRect(0, 0, viewInstance.editorPixelSize, viewInstance.editorPixelSize);
			g.setColor(View.SELECTION_COLOR);
			g.fillRect(0, 0, viewInstance.editorPixelSize, viewInstance.editorPixelSize);
			g.setColor(previousColor);
		}
	}

	private void paintPixel(boolean filled) {
		pixel.filled = filled;
		if (filled) pixel.setBackground(viewInstance.currentBrushColor);
		if (!filled) pixel.setBackground(View.TRANSPARENT_COLOR_1);
		pixel.repaint();
	}
	
	private void selectPixel(boolean selected) {
		pixel.selected = selected;
		if (selected && !viewInstance.selectedPixels.contains(pixel)) viewInstance.selectedPixels.add(pixel);
		else if (!selected && viewInstance.selectedPixels.contains(pixel)) viewInstance.selectedPixels.remove(pixel);
		viewInstance.helpTextLabel.setText(viewInstance.selectedPixels.size() + " pixels selected");
		pixel.repaint();
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
