package pixelstudio;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import static pixelstudio.View.viewInstance;

@SuppressWarnings("serial")
public class Pixel extends JPanel {
	private Pixel pixel = this;
	private boolean filled = false;
	private boolean selected = false;
	
	public Pixel(int xLoc, int yLoc) {
		super();
		/*
		 * int onmask = KeyEvent.SHIFT_DOWN_MASK | KeyEvent.BUTTON1_DOWN_MASK; int
		 * offmask = KeyEvent.CTRL_DOWN_MASK; if ((event.getModifiersEx() & (onmask |
		 * offmask)) == onmask) { ... }
		 */
		pixel.setBorder(viewInstance.PIXEL_BORDER);
		pixel.addMouseListener(new MouseListener() {
			int ctrlClickMask = KeyEvent.CTRL_DOWN_MASK | KeyEvent.BUTTON1_DOWN_MASK;
			@Override
			public void mouseClicked(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getModifiersEx() == ctrlClickMask) {
					if (viewInstance.isPaintMode) 
						paintPixel(false);
					else 
						selectPixel(false);
				}
				else if (e.getButton() == MouseEvent.BUTTON1) {
					if (viewInstance.isPaintMode) 
						paintPixel(true);
					else 
						selectPixel(true);
					viewInstance.leftMouseDown = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					viewInstance.leftMouseDown = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) { 
				viewInstance.mouseLocationLabel.setText(xLoc + ":" + yLoc);
				if (e.getModifiersEx() == ctrlClickMask) {
					if (viewInstance.isPaintMode) 
						paintPixel(false);
					else 
						selectPixel(false);
				}
				else if (viewInstance.leftMouseDown) {
					if (viewInstance.isPaintMode) 
						paintPixel(true);
					else 
						selectPixel(true);
				}
				
			}
		});
		viewInstance.allPixels.add(pixel);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!filled) {
			pixel.setBackground(viewInstance.TRANSPARENT_COLOR);
			g.drawLine(0, 0, viewInstance.editorPixelWidth, viewInstance.editorPixelHeight);
			g.drawLine(0, viewInstance.editorPixelHeight, viewInstance.editorPixelWidth, 0);
		}
		if (selected) {
			Color previousColor = g.getColor();
			g.setColor(viewInstance.ACCENT_COLOR);
			g.drawRect(0, 0, viewInstance.editorPixelWidth, viewInstance.editorPixelHeight);
			g.setColor(viewInstance.SELECTION_COLOR);
			g.fillRect(0, 0, viewInstance.editorPixelWidth, viewInstance.editorPixelHeight);
			g.setColor(previousColor);
		}
	}

	private void paintPixel(boolean filled) {
		pixel.filled = filled;
		if (filled) pixel.setBackground(viewInstance.currentBrushColor);
		if (!filled) pixel.setBackground(viewInstance.TRANSPARENT_COLOR);
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
