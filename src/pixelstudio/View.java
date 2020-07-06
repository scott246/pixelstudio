package pixelstudio;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;

@SuppressWarnings("serial")
public class View extends JFrame {
	public static View viewInstance;
    
    protected static final Color BACKGROUND_COLOR = new Color(32, 32, 32);
    protected static final Color EDITOR_BACKGROUND_COLOR = new Color(64, 64, 64);
    protected static final Color TOOLBAR_BACKGROUND_COLOR = new Color(64, 64, 64);
    protected static final Color FOREGROUND_COLOR = new Color(255, 255, 255);
    protected static final Color ACCENT_COLOR = new Color(255, 128, 0);
    protected static final Color SELECTION_COLOR = new Color(255, 255, 255, 64);
    protected static final Color TRANSPARENT_COLOR_1 = new Color(64, 64, 64);
    protected static final Color TRANSPARENT_COLOR_2 = new Color(128, 128, 128);
    protected static final Color WARNING_COLOR = new Color(255, 255, 0);
    
	protected int editorPixelCount;
    protected Dimension editorSize;
    protected int editorPixelSize;
    
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
	private JTextField projectTitleTextField;
	
	protected JLabel paintColorLabel = new JLabel("#XXXXXX");
	protected JLabel helpTextLabel = new JLabel("Help Text");
	protected JLabel mouseLocationLabel = new JLabel("X:Y");
	protected JPanel editorCanvasPanel = new JPanel();
	protected JButton paintColorButton = new JButton();
	protected JButton saveButton = new JButton();
	protected JToggleButton paintSelectModeToggleButton = new JToggleButton();
	
	protected JMenuItem fillMenuItem = new JMenuItem("Fill");
	protected JMenuItem replaceColorMenuItem = new JMenuItem("Replace Color");
	protected JMenuItem cloneMenuItem = new JMenuItem("Clone");
	protected JMenu flipMenu = new JMenu("Flip");
	protected JMenuItem flipHorizontallyMenuItem = new JMenuItem("Flip Horizontally");
	protected JMenuItem flipVerticallyMenuItem = new JMenuItem("Flip Vertically");
	protected JMenu rotateMenu = new JMenu("Rotate");
	protected JMenuItem rotate90MenuItem = new JMenuItem("90");
	protected JMenuItem rotate180MenuItem = new JMenuItem("180");
	protected JMenuItem rotate270MenuItem = new JMenuItem("270");
	protected JMenuItem shiftMenuItem = new JMenuItem("Shift");
	protected JMenuItem cropOutMenuItem = new JMenuItem("Crop Out");
	protected JMenuItem clearMenuItem = new JMenuItem("Clear");

	/**
	 * Create the frame.
	 */
	public View(String name, int canvasSize, int pixelDensity) {
		viewInstance = this;
		projectTitleTextField = new JTextField();
		projectTitleTextField.setText(name);
		editorSize = new Dimension(canvasSize + (4 * pixelDensity), canvasSize + (4 * pixelDensity));
		editorPixelCount = pixelDensity;
		editorPixelSize = canvasSize/editorPixelCount;
		setBackground(BACKGROUND_COLOR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pixel Studio");
		setSize(new Dimension(canvasSize + 100 + (pixelDensity * 4), canvasSize + 300 + (pixelDensity * 4)));
		
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		initializeMenuBar();
		initializeEditorPanel();

		launcherPanel.setVisible(true);
		editorPanel.setVisible(false);
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
		
		JMenuItem exitMenuItem = new JMenuItem(new ExitAction());
		fileMenu.add(exitMenuItem);
		
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		editMenu.add(undoMenuItem);
		
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		editMenu.add(redoMenuItem);
		
		JMenu actionsMenu = new JMenu("Actions");
		editMenu.add(actionsMenu);
		actionsMenu.add(fillMenuItem);
		actionsMenu.add(replaceColorMenuItem);
		actionsMenu.add(cloneMenuItem);
		actionsMenu.add(flipMenu);
		flipMenu.add(flipHorizontallyMenuItem);
		flipMenu.add(flipVerticallyMenuItem);
		actionsMenu.add(rotateMenu);
		rotateMenu.add(rotate90MenuItem);
		rotateMenu.add(rotate180MenuItem);
		rotateMenu.add(rotate270MenuItem);
		actionsMenu.add(shiftMenuItem);
		actionsMenu.add(cropOutMenuItem);
		actionsMenu.add(clearMenuItem);
		JMenuItem propertiesMenuItem = new JMenuItem("Properties");
		editMenu.add(propertiesMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("Help");
		helpMenu.add(helpMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);

		LauncherView.launcherInstance.dispose();
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
    	editorCanvasPanel.setLayout(new GridLayout(editorPixelCount, editorPixelCount));
    	for (int i = 0; i < editorPixelCount; i++) {
    		for (int j = 0; j < editorPixelCount; j++) {
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
		
		projectTitleTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		projectTitleTextField.setBorder(null);
		projectTitleTextField.setMaximumSize(new Dimension(200, 2147483647));
		projectTitleTextField.setPreferredSize(new Dimension(200, 20));
		infoPanel.add(projectTitleTextField);
		projectTitleTextField.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(5, 0));
		infoPanel.add(horizontalStrut_3);
		
		saveButton.setHorizontalAlignment(SwingConstants.RIGHT);
		ViewActions.formatButton(saveButton, "src/pixelstudio/icons/save_26px.png", "Save");
		infoPanel.add(saveButton);
		paintOptionsPanel.setLayout(new BoxLayout(paintOptionsPanel, BoxLayout.X_AXIS));
		
		paintSelectModeToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (paintSelectModeToggleButton.isSelected()) ViewActions.setPaintState(true);
				else ViewActions.setPaintState(false);
			}
		});
		paintSelectModeToggleButton.setSelected(true);
		paintSelectModeToggleButton.setBackground(View.TRANSPARENT_COLOR_1);
		paintSelectModeToggleButton.setUI(new MetalToggleButtonUI() {
			@Override
			protected Color getSelectColor() {
				return View.TRANSPARENT_COLOR_2;
			}
		});
		paintSelectModeToggleButton.setForeground(View.FOREGROUND_COLOR);
		paintSelectModeToggleButton.setFocusPainted(false);
		paintSelectModeToggleButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		ViewActions.setPaintState(true);
		paintSelectModeToggleButton.setPreferredSize(new Dimension(120, 23));
		paintOptionsPanel.add(paintSelectModeToggleButton);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(TOOLBAR_BACKGROUND_COLOR);
		toolBar.setFloatable(false);
		toolBar.setBorder(null);
		paintOptionsPanel.add(toolBar);
		
		JButton undoButton = new JButton();
		ViewActions.formatButton(undoButton, "src/pixelstudio/icons/undo_26px.png", "Undo");
		toolBar.add(undoButton);
		
		JButton redoButton = new JButton();
		ViewActions.formatButton(redoButton, "src/pixelstudio/icons/redo_26px.png", "Redo");
		toolBar.add(redoButton);
		
		toolBar.addSeparator();
		
		JButton fillButton = new JButton();
		ViewActions.formatButton(fillButton, "src/pixelstudio/icons/fill_color_26px.png", "Fill Selection");
		toolBar.add(fillButton);
		
		JButton replaceColorButton = new JButton();
		ViewActions.formatButton(replaceColorButton, "src/pixelstudio/icons/change_theme_26px.png", "Replace Colors");
		toolBar.add(replaceColorButton);
		
		JButton cloneButton = new JButton();
		ViewActions.formatButton(cloneButton, "src/pixelstudio/icons/clone_26px.png", "Clone Selection");
		toolBar.add(cloneButton);
		
		JButton flipHorizontalButton = new JButton();
		ViewActions.formatButton(flipHorizontalButton, "src/pixelstudio/icons/flip_horizontal_26px.png", "Flip Horizontal");
		toolBar.add(flipHorizontalButton);
		
		JButton flipVerticalButton = new JButton();
		ViewActions.formatButton(flipVerticalButton, "src/pixelstudio/icons/flip_vertical_26px.png", "Flip Vertical");
		toolBar.add(flipVerticalButton);
		
		JButton rotateButton = new JButton();
		ViewActions.formatButton(rotateButton, "src/pixelstudio/icons/rotate_right_26px.png", "Rotate 90");
		toolBar.add(rotateButton);
		
		JButton shiftButton = new JButton();
		ViewActions.formatButton(shiftButton, "src/pixelstudio/icons/shift_26px.png", "Shift Selection");
		toolBar.add(shiftButton);
		
		JButton cropButton = new JButton();
		ViewActions.formatButton(cropButton, "src/pixelstudio/icons/crop_26px.png", "Crop Out Selection");
		toolBar.add(cropButton);
		
		JButton clearButton = new JButton();
		ViewActions.formatButton(clearButton, "src/pixelstudio/icons/clear_symbol_26px.png", "Clear Selection");
		toolBar.add(clearButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		paintOptionsPanel.add(horizontalGlue);
		
		paintColorLabel.setForeground(FOREGROUND_COLOR);
		paintOptionsPanel.add(paintColorLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		paintOptionsPanel.add(horizontalStrut);

		ViewActions.formatButton(paintColorButton, "src/pixelstudio/icons/paint_palette_26px.png", "Change Brush Color");
		paintColorButton.setPreferredSize(new Dimension(90, 23));
		paintColorButton.setHorizontalAlignment(SwingConstants.RIGHT);
        paintColorButton.addActionListener(new ColorChoiceAction());
		paintColorButton.setBackground(currentBrushColor);
		paintOptionsPanel.add(paintColorButton);
		editorPanel.setLayout(gl_editorPanel);
	}
}
