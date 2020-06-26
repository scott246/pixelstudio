package pixelstudio;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class Actions {
	public static void setPaintColor(Color c) {
    	Window.selectedPaintColorLabel.setText(Utils.rgbToHex(c).toUpperCase());
    	Window.currentBrushColor = c;
    	Window.chooseColorButton.setBackground(c);
    }
	
	public static void setPaintState(boolean isPaintState) {
		if (isPaintState) {
			Window.paintModeButton.setBackground(Window.ACCENT_COLOR);
			Window.selectModeButton.setBackground(Window.TRANSPARENT_COLOR);
			Window.isPaintMode = true;
		}
		else {
			Window.selectModeButton.setBackground(Window.ACCENT_COLOR);
			Window.paintModeButton.setBackground(Window.TRANSPARENT_COLOR);
			Window.isPaintMode = false;
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
		JColorChooser.createDialog((Component) Window.windowInstance, "Select Paint Color", true, colorChooser, null, null).setVisible(true);
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
