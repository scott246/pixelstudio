package pixelstudio;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import static pixelstudio.View.viewInstance;

public class Actions {
	public static void setPaintColor(Color c) {
    	viewInstance.paintColorLabel.setText(Utils.rgbToHex(c).toUpperCase());
    	viewInstance.currentBrushColor = c;
    	viewInstance.paintColorButton.setBackground(c);
    }
	
	public static void setPaintState(boolean isPaintState) {
		if (isPaintState) {
			viewInstance.paintSelectModeToggleButton.setText("Paint Mode");
			deselectAllPixels();
			viewInstance.helpTextLabel.setText("");
			viewInstance.isPaintMode = true;
		}
		else {
			viewInstance.paintSelectModeToggleButton.setText("Select Mode");
			viewInstance.helpTextLabel.setText("0 pixels selected");
			viewInstance.isPaintMode = false;
		}
	}
	
	public static void deselectAllPixels() {
		for (Pixel p : viewInstance.allPixels) {
			p.setSelected(false);
		}
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
		if (c != null) Actions.setPaintColor(c);
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
