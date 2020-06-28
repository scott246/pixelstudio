package pixelstudio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class View extends JFrame {
	public static View viewInstance;
	
	protected final int WINDOW_WIDTH = 600;
    protected final int WINDOW_HEIGHT = 600;
    
    protected final Color BACKGROUND_COLOR = new Color(32, 32, 32);
    protected final Color EDITOR_BACKGROUND_COLOR = new Color(64, 64, 64);
    protected final Color TOOLBAR_BACKGROUND_COLOR = new Color(64, 64, 64);
    protected final Color FOREGROUND_COLOR = new Color(255, 255, 255);
    protected final Color ACCENT_COLOR = new Color(255, 128, 0, 255);
    protected final Color SELECTION_COLOR = new Color(255, 128, 0, 64);
    protected final Color TRANSPARENT_COLOR = Color.LIGHT_GRAY;
    protected final Color WARNING_COLOR = new Color(255, 255, 0);
    
    //TODO: make below variables dynamic
    protected int editorPixelCountX = 10;
    protected int editorPixelCountY = 10;
    protected Dimension editorSize = new Dimension(600, 500);
    protected int editorPixelWidth = editorSize.width/editorPixelCountX;
    protected int editorPixelHeight = editorSize.height/editorPixelCountY;

	protected boolean leftMouseDown = false;
	protected boolean rightMouseDown = false;
	protected boolean isPaintMode = true;
    protected Color currentBrushColor = new Color(0, 0, 0);

    protected final Border PIXEL_BORDER = BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(1, 1, 1, 1), 
			BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
    protected final Border SELECTED_PIXEL_BORDER = BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(1, 1, 1, 1), 
			BorderFactory.createMatteBorder(1, 1, 1, 1, ACCENT_COLOR));
    
    protected ArrayList<Pixel> selectedPixels = new ArrayList<Pixel>();
    protected ArrayList<Pixel> allPixels = new ArrayList<Pixel>();

	private JPanel contentPane;
	private JPanel launcherPanel = new JPanel();
	private JPanel editorPanel = new JPanel();
	private JTextField densityHeightInput;
	private JTextField nameInput;
	private JTextField densityWidthInput;
	private JTextField canvasHeightInput;
	private JTextField canvasWidthInput;
	private JTextField openLocationInput;
	private JTextField projectTitleTextField;
	
	protected JLabel paintColorLabel = new JLabel("#XXXXXX");
	protected JLabel helpTextLabel = new JLabel("Help Text");
	protected JLabel mouseLocationLabel = new JLabel("X:Y");
	protected JPanel editorCanvasPanel = new JPanel();
	protected JButton paintColorButton = new JButton(" ");
	protected JButton selectModeButton = new JButton("Select");
	protected JButton paintModeButton = new JButton("Paint");
	protected JButton saveButton = new JButton("Save");

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
		viewInstance = this;
		setBackground(BACKGROUND_COLOR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("Pixel Studio");
		
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		initializeMenuBar();
		initializeLauncherPanel();
		initializeEditorPanel();

		//TODO: change when editing launcher elements
		launcherPanel.setVisible(false);
		editorPanel.setVisible(true);
	}
	
	private void initializeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		
		JMenuItem saveAsMenuItem = new JMenuItem("Save As");
		fileMenu.add(saveAsMenuItem);
		
		JMenuItem exportMenuItem = new JMenuItem("Export");
		fileMenu.add(exportMenuItem);
		
		JSeparator separator = new JSeparator();
		fileMenu.add(separator);
		
		JMenuItem preferencesMenuItem = new JMenuItem("Preferences");
		fileMenu.add(preferencesMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		fileMenu.add(separator_1);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		editMenu.add(undoMenuItem);
		
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		editMenu.add(redoMenuItem);
		
		JMenu actionsMenu = new JMenu("Actions");
		editMenu.add(actionsMenu);
		
		JMenuItem fillMenuItem = new JMenuItem("Fill");
		actionsMenu.add(fillMenuItem);
		
		JMenuItem replaceColorMenuItem = new JMenuItem("Replace Color");
		actionsMenu.add(replaceColorMenuItem);
		
		JMenuItem cloneMenuItem = new JMenuItem("Clone");
		actionsMenu.add(cloneMenuItem);
		
		JMenu flipMenu = new JMenu("Flip");
		actionsMenu.add(flipMenu);
		
		JMenuItem flipHorizontallyMenuItem = new JMenuItem("Flip Horizontally");
		flipMenu.add(flipHorizontallyMenuItem);
		
		JMenuItem flipVerticallyMenuItem = new JMenuItem("Flip Vertically");
		flipMenu.add(flipVerticallyMenuItem);
		
		JMenuItem flipAscendingMenuItem = new JMenuItem("Flip Diagonally (Ascending)");
		flipMenu.add(flipAscendingMenuItem);
		
		JMenuItem flipDescendingMenuItem = new JMenuItem("Flip Diagonally (Descending)");
		flipMenu.add(flipDescendingMenuItem);
		
		JMenu rotateMenu = new JMenu("Rotate");
		actionsMenu.add(rotateMenu);
		
		JMenuItem rotate90MenuItem = new JMenuItem("90");
		rotateMenu.add(rotate90MenuItem);
		
		JMenuItem rotate180MenuItem = new JMenuItem("180");
		rotateMenu.add(rotate180MenuItem);
		
		JMenuItem rotate270MenuItem = new JMenuItem("270");
		rotateMenu.add(rotate270MenuItem);
		
		JMenuItem shiftMenuItem = new JMenuItem("Shift");
		actionsMenu.add(shiftMenuItem);
		
		JMenuItem cropOutMenuItem = new JMenuItem("Crop Out");
		actionsMenu.add(cropOutMenuItem);
		
		JMenuItem clearMenuItem = new JMenuItem("Clear");
		actionsMenu.add(clearMenuItem);
		
		JMenuItem propertiesMenuItem = new JMenuItem("Properties");
		editMenu.add(propertiesMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("Help");
		helpMenu.add(helpMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
	}
	
	private void initializeLauncherPanel() {
		launcherPanel.setBackground(BACKGROUND_COLOR);
		contentPane.add(launcherPanel, "name_858653006200");
		
		JPanel newOpenRadioButtonsPanel = new JPanel();
		newOpenRadioButtonsPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
		
		JPanel newOpenSelectionCardPanel = new JPanel();
		newOpenSelectionCardPanel.setBackground(BACKGROUND_COLOR);
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
		newRadioButton.setForeground(FOREGROUND_COLOR);
		newRadioButton.setSelected(true);
		newRadioButton.setBackground(newOpenRadioButtonsPanel.getBackground());
		newOpenRadioButtonsPanel.add(newRadioButton);
		
		JRadioButton openRadioButton = new JRadioButton("Open");
		openRadioButton.setForeground(FOREGROUND_COLOR);
		openRadioButton.setSelected(false);
		openRadioButton.setBackground(newOpenRadioButtonsPanel.getBackground());
		newOpenRadioButtonsPanel.add(openRadioButton);
		newOpenSelectionCardPanel.setLayout(new CardLayout(0, 0));

		ButtonGroup newOpenButtonsGroup = new ButtonGroup();
		newOpenButtonsGroup.add(newRadioButton);
		newOpenButtonsGroup.add(openRadioButton);
		
		JPanel newSelectionContentPanel = new JPanel();
		newSelectionContentPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
		newOpenSelectionCardPanel.add(newSelectionContentPanel, "name_1335114206700");
		
		JLabel newNameLabel = new JLabel("Name");
		newNameLabel.setForeground(FOREGROUND_COLOR);
		newNameLabel.setToolTipText("Name of project");
		
		JLabel canvasWidthLabel = new JLabel("Canvas (Width)");
		canvasWidthLabel.setForeground(FOREGROUND_COLOR);
		canvasWidthLabel.setToolTipText("Width of the canvas (in pixels)");
		
		JLabel canvasHeightLabel = new JLabel("Canvas (Height)");
		canvasHeightLabel.setForeground(FOREGROUND_COLOR);
		canvasHeightLabel.setToolTipText("Height of the canvas (in pixels)");
		
		JLabel densityWidthLabel = new JLabel("Density (Width)");
		densityWidthLabel.setForeground(FOREGROUND_COLOR);
		densityWidthLabel.setToolTipText("Number of pixels contained horizontally in the picture");
		
		JLabel densityHeightLabel = new JLabel("Density (Height)");
		densityHeightLabel.setForeground(FOREGROUND_COLOR);
		densityHeightLabel.setToolTipText("Number of pixels contained vertically in the picture");
		
		JCheckBox forceSquareCheckBox = new JCheckBox("Square");
		forceSquareCheckBox.setForeground(FOREGROUND_COLOR);
		forceSquareCheckBox.setBackground(newSelectionContentPanel.getBackground());
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
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(forceSquareCheckBox)
						.addComponent(createNewProjectButton))
					.addContainerGap())
		);
		newSelectionContentPanel.setLayout(gl_newSelectionContentPanel);
		
		JPanel openSelectionContentPanel = new JPanel();
		openSelectionContentPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
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
	}
	
	private void initializeEditorPanel() {
		editorPanel.setBackground(BACKGROUND_COLOR);
		contentPane.add(editorPanel, "name_890918696800");
		
		JPanel paintOptionsPanel = new JPanel();
		paintOptionsPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
		
		editorCanvasPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
		editorCanvasPanel.setPreferredSize(editorSize);
    	editorCanvasPanel.setLayout(new GridLayout(editorPixelCountX, editorPixelCountY));
    	for (int i = 0; i < editorPixelCountX; i++) {
    		for (int j = 0; j < editorPixelCountY; j++) {
    			editorCanvasPanel.add(new Pixel(i, j));
    		}
    	}
    	
		GroupLayout gl_editorPanel = new GroupLayout(editorPanel);
		gl_editorPanel.setHorizontalGroup(
			gl_editorPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(paintOptionsPanel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(editorCanvasPanel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_editorPanel.setVerticalGroup(
			gl_editorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editorPanel.createSequentialGroup()
					.addComponent(paintOptionsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editorCanvasPanel, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(5, 0));
		infoPanel.add(horizontalStrut_2);
		
		infoPanel.add(mouseLocationLabel);
		mouseLocationLabel.setForeground(FOREGROUND_COLOR);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(5, 0));
		infoPanel.add(horizontalStrut_1);
		
		helpTextLabel.setForeground(ACCENT_COLOR);
		infoPanel.add(helpTextLabel);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		infoPanel.add(horizontalGlue_1);
		
		projectTitleTextField = new JTextField();
		projectTitleTextField.setMaximumSize(new Dimension(200, 2147483647));
		projectTitleTextField.setPreferredSize(new Dimension(200, 20));
		infoPanel.add(projectTitleTextField);
		projectTitleTextField.setColumns(10);
		
		saveButton.setHorizontalAlignment(SwingConstants.RIGHT);
		infoPanel.add(saveButton);
		paintOptionsPanel.setLayout(new BoxLayout(paintOptionsPanel, BoxLayout.X_AXIS));
		
		selectModeButton.setPreferredSize(new Dimension(80, 23));
		selectModeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Actions.setPaintState(false);
			}
    	});
		paintOptionsPanel.add(selectModeButton);
		
		paintModeButton.setPreferredSize(new Dimension(80, 23));
		paintModeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Actions.setPaintState(true);
			}
        });
		paintOptionsPanel.add(paintModeButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		paintOptionsPanel.add(horizontalGlue);
		
		paintColorLabel.setForeground(FOREGROUND_COLOR);
		paintOptionsPanel.add(paintColorLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		paintOptionsPanel.add(horizontalStrut);
		
		paintColorButton.setPreferredSize(new Dimension(90, 23));
		paintColorButton.setHorizontalAlignment(SwingConstants.RIGHT);
		paintColorButton.setFocusPainted(false);
        paintColorButton.addActionListener(new ColorChoiceAction());
		paintColorButton.setBackground(currentBrushColor);
		paintOptionsPanel.add(paintColorButton);
		editorPanel.setLayout(gl_editorPanel);
	}
}
