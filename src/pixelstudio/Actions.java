package pixelstudio;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Actions {

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
