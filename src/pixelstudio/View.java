package pixelstudio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JList;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField densityHeightInput;
	private JTextField nameInput;
	private JTextField densityWidthInput;
	private JTextField canvasHeightInput;
	private JTextField canvasWidthInput;
	private JTextField openLocationInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel launcherPanel = new JPanel();
		contentPane.add(launcherPanel, "name_858653006200");
		
		JPanel newOpenRadioButtonsPanel = new JPanel();
		
		JPanel newOpenSelectionCardPanel = new JPanel();
		GroupLayout gl_launcherPanel = new GroupLayout(launcherPanel);
		gl_launcherPanel.setHorizontalGroup(
			gl_launcherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_launcherPanel.createSequentialGroup()
					.addGroup(gl_launcherPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_launcherPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(newOpenRadioButtonsPanel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addGroup(gl_launcherPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(newOpenSelectionCardPanel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_launcherPanel.setVerticalGroup(
			gl_launcherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_launcherPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(newOpenRadioButtonsPanel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newOpenSelectionCardPanel, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addContainerGap())
		);
		newOpenRadioButtonsPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JRadioButton newRadioButton = new JRadioButton("New");
		newOpenRadioButtonsPanel.add(newRadioButton);
		
		JRadioButton openRadioButton = new JRadioButton("Open");
		newOpenRadioButtonsPanel.add(openRadioButton);
		newOpenSelectionCardPanel.setLayout(new CardLayout(0, 0));
		
		JPanel newSelectionContentPanel = new JPanel();
		newOpenSelectionCardPanel.add(newSelectionContentPanel, "name_1335114206700");
		
		JLabel newNameLabel = new JLabel("Name");
		newNameLabel.setToolTipText("Name of project");
		
		JLabel canvasWidthLabel = new JLabel("Canvas (Width)");
		canvasWidthLabel.setToolTipText("Width of the canvas (in pixels)");
		
		JLabel canvasHeightLabel = new JLabel("Canvas (Height)");
		canvasHeightLabel.setToolTipText("Height of the canvas (in pixels)");
		
		JLabel densityWidthLabel = new JLabel("Density (Width)");
		densityWidthLabel.setToolTipText("Number of pixels contained horizontally in the picture");
		
		JLabel densityHeightLabel = new JLabel("Density (Height)");
		densityHeightLabel.setToolTipText("Number of pixels contained vertically in the picture");
		
		JCheckBox forceSquareCheckBox = new JCheckBox("Square");
		forceSquareCheckBox.setToolTipText("Forces the width and height of the canvas and pixel density to be equal");
		
		JButton createNewProjectButton = new JButton("Create");
		
		densityHeightInput = new JTextField();
		densityHeightInput.setColumns(10);
		
		nameInput = new JTextField();
		nameInput.setColumns(10);
		
		densityWidthInput = new JTextField();
		densityWidthInput.setColumns(10);
		
		canvasHeightInput = new JTextField();
		canvasHeightInput.setColumns(10);
		
		canvasWidthInput = new JTextField();
		canvasWidthInput.setColumns(10);
		GroupLayout gl_newSelectionContentPanel = new GroupLayout(newSelectionContentPanel);
		gl_newSelectionContentPanel.setHorizontalGroup(
			gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
							.addComponent(canvasHeightLabel)
							.addGap(18)
							.addComponent(canvasHeightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
							.addComponent(forceSquareCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
							.addComponent(createNewProjectButton))
						.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
							.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(densityHeightLabel)
								.addComponent(newNameLabel)
								.addComponent(densityWidthLabel)
								.addComponent(canvasWidthLabel))
							.addGap(18)
							.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(canvasWidthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(densityWidthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(densityHeightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(92)))
					.addContainerGap())
		);
		gl_newSelectionContentPanel.setVerticalGroup(
			gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(newNameLabel)
						.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(canvasWidthLabel)
						.addComponent(canvasWidthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(canvasHeightLabel)
						.addComponent(canvasHeightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(densityWidthLabel)
						.addComponent(densityWidthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(densityHeightLabel)
						.addComponent(densityHeightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(forceSquareCheckBox)
						.addComponent(createNewProjectButton))
					.addContainerGap())
		);
		newSelectionContentPanel.setLayout(gl_newSelectionContentPanel);
		
		JPanel openSelectionContentPanel = new JPanel();
		newOpenSelectionCardPanel.add(openSelectionContentPanel, "name_1413884164500");
		
		JLabel openLabel = new JLabel("Open");
		
		openLocationInput = new JTextField();
		openLocationInput.setColumns(10);
		
		JButton browseButton = new JButton("Browse");
		
		JButton openButton = new JButton("Open");
		
		JList recentlyOpenedList = new JList();
		
		JLabel recentLabel = new JLabel("Recent");
		GroupLayout gl_openSelectionContentPanel = new GroupLayout(openSelectionContentPanel);
		gl_openSelectionContentPanel.setHorizontalGroup(
			gl_openSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_openSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_openSelectionContentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(recentlyOpenedList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_openSelectionContentPanel.createSequentialGroup()
							.addComponent(openLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(openLocationInput, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(browseButton))
						.addComponent(openButton)
						.addComponent(recentLabel, Alignment.LEADING))
					.addContainerGap())
		);
		gl_openSelectionContentPanel.setVerticalGroup(
			gl_openSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_openSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_openSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(openLabel)
						.addComponent(openLocationInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(recentLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(recentlyOpenedList, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(openButton)
					.addContainerGap())
		);
		openSelectionContentPanel.setLayout(gl_openSelectionContentPanel);
		launcherPanel.setLayout(gl_launcherPanel);
		
		JPanel editorPanel = new JPanel();
		contentPane.add(editorPanel, "name_890918696800");
	}
}
