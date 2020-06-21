package pixelstudio;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

public class Actions {

}

@SuppressWarnings("serial")
class ColorChoiceAction extends AbstractAction {
	
	public ColorChoiceAction() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Color c = JColorChooser.showDialog((Component) Window.windowInstance, "Select Paint Color", Color.BLACK);
		if (c != null) Window.setPaintColor(c);
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
