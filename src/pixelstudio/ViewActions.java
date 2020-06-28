package pixelstudio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import static pixelstudio.View.viewInstance;

public class ViewActions {
	public static void setPaintColor(Color c) {
    	viewInstance.paintColorLabel.setText(rgbToHex(c).toUpperCase());
    	viewInstance.currentBrushColor = c;
    	viewInstance.paintColorButton.setBackground(c);
    }
	
	public static void setPaintState(boolean isPaintState) {
		if (isPaintState) {
			viewInstance.paintSelectModeToggleButton.setText("Paint Mode");
			deselectAllPixels();
			viewInstance.helpTextLabel.setText("Switched to paint mode");
			viewInstance.isPaintMode = true;
		}
		else {
			viewInstance.paintSelectModeToggleButton.setText("Select Mode");
			viewInstance.helpTextLabel.setText("Switched to select mode");
			viewInstance.isPaintMode = false;
		}
	}
	
	public static void deselectAllPixels() {
		for (Pixel p : viewInstance.allPixels) {
			p.setSelected(false);
		}
	}
	
	public static String rgbToHex(Color rgb) {
		String hex = "#";
		int red = rgb.getRed();
		int green = rgb.getGreen();
		int blue = rgb.getBlue();
		if ((red >= 0 && red < 256) && (green >= 0 && green < 256) && (blue >= 0 && blue < 256)) {
			hex += twoDigitHex(red);
			hex += twoDigitHex(green);
			hex += twoDigitHex(blue);
			return hex;
		}
		return null;
	}
	
	private static String twoDigitHex(int integer) {
		String formatted = Integer.toString(integer, 16);
		return formatted.length() == 2 ? formatted : "0" + formatted;
	}
	
	public static void formatButton(JButton button, String iconPath, String toolTipText) {
		button.setBackground(View.TOOLBAR_BACKGROUND_COLOR);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(25, 25));
		button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		ImageIcon icon = new ImageIcon(iconPath);
		icon.setImage(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		button.setIcon(icon);
		button.setToolTipText(toolTipText);
	}
}

@SuppressWarnings("serial")
class ColorChoiceAction extends AbstractAction {
	
	public ColorChoiceAction() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JColorChooser colorChooser = new JColorChooser(Color.BLACK);
		for (AbstractColorChooserPanel a : colorChooser.getChooserPanels()) {
			if (!a.getDisplayName().equalsIgnoreCase("RGB") && !a.getDisplayName().equalsIgnoreCase("Swatches")) {
				colorChooser.removeChooserPanel(a);
			}
		}
		JColorChooser.createDialog((Component) viewInstance, "Select Paint Color", true, colorChooser, null, null).setVisible(true);
		Color c = colorChooser.getColor();
		if (c != null) ViewActions.setPaintColor(c);
	}
	
}

@SuppressWarnings("serial")
class ExitAction extends AbstractAction {
	
	public ExitAction() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(1);
	}
	
}
