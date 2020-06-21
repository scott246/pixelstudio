package pixelstudio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static Window windowInstance;
	
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BORDER_SIZE = 5;
    private static final int PADDING = BORDER_SIZE * 2;
    private static final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    private static final Dimension TOOLBAR_DIMENSION = new Dimension(WINDOW_WIDTH, 30+PADDING);
    private static final Dimension EDITOR_DIMENSION = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT-(TOOLBAR_DIMENSION.height)*2);
    private static final Dimension BUTTON_DIMENSION = new Dimension(100, 30);
	
    private static final Color BACKGROUND_COLOR = new Color(32, 32, 32, 255);
    private static final Color EDITOR_BACKGROUND_COLOR = new Color(64, 64, 64, 255);
    private static final Color TOOLBAR_BACKGROUND_COLOR = new Color(64, 64, 64, 255);
    private static final Color FOREGROUND_COLOR = new Color(255, 255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(255, 128, 0, 255);
	
    private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 12);
    private static final Font MONO_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	
	private static JFrame window = new JFrame("Pixel Studio");
    private static JPanel windowContentPanel = new JPanel();
    
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu fileMenu = new JMenu("File");
    private static JMenuItem newMenuItem = new JMenuItem("New");
    private static JMenuItem openMenuItem = new JMenuItem("Open");
    private static JMenuItem saveMenuItem = new JMenuItem("Save");
    private static JMenuItem saveAsMenuItem = new JMenuItem("Save As");
    private static JMenuItem preferencesMenuItem = new JMenuItem("Preferences");
    private static JMenuItem exportMenuItem = new JMenuItem("Export");
    private static JMenuItem exitMenuItem = new JMenuItem(new ExitAction());
    
    private static JMenu editMenu = new JMenu("Edit");
    private static JMenuItem undoMenuItem = new JMenuItem("Undo");
    private static JMenuItem redoMenuItem = new JMenuItem("Redo");
    private static JMenuItem propertiesMenuItem = new JMenuItem("Properties");
    private static JMenuItem fillMenuItem = new JMenuItem("Fill");
    private static JMenuItem replaceColorMenuItem = new JMenuItem("Replace Color");
    private static JMenuItem dupeMenuItem = new JMenuItem("Dupe");
    private static JMenu flipMenu = new JMenu("Flip");
    private static JMenuItem flipHorizontalMenuItem = new JMenuItem("Horizontally");
    private static JMenuItem flipVerticalMenuItem = new JMenuItem("Vertically");
    private static JMenuItem flipDiagonalAscMenuItem = new JMenuItem("Diagonally (Ascending)");
    private static JMenuItem flipDiagonalDescMenuItem = new JMenuItem("Diagonally (Descending)");
    private static JMenu rotateMenu = new JMenu("Rotate");
    private static JMenuItem rotate90MenuItem = new JMenuItem("90°");
    private static JMenuItem rotate180MenuItem = new JMenuItem("180°");
    private static JMenuItem rotate270MenuItem = new JMenuItem("270°");
    private static JMenuItem shiftMenuItem = new JMenuItem("Shift");
    private static JMenuItem cropMenuItem = new JMenuItem("Crop");
    private static JMenuItem clearMenuItem = new JMenuItem("Clear");
    
    private static JMenu helpMenu = new JMenu("Help");
    private static JMenuItem helpGuideMenuItem = new JMenuItem("Help Guide");
    private static JMenuItem aboutMenuItem = new JMenuItem("About");
    
    private static JPanel editorPanel = new JPanel();
    private static JPanel editorCanvasPanel = new JPanel();
    private static int editorPixelCountX = 10;
    private static int editorPixelCountY = 10;
    private static Dimension editorSize = new Dimension(300, 300);
    
    private static JPanel upperToolbarPanel = new JPanel();
    private static JLabel helpTextLabel = new JLabel("Help text");
    private static JLabel titleLabel = new JLabel("Title");
    private static JButton saveButton = new JButton("Save");
    public static JLabel mousePositionLabel = new JLabel("X:Y");
    
    private static JPanel lowerToolbarPanel = new JPanel();
    private static JButton paintModeButton = new JButton("Paint");
    private static JButton selectModeButton = new JButton("Select");
    private static JButton chooseColorButton = new JButton(" ");
    private static JLabel selectedPaintColorLabel = new JLabel("#000000");
    public static Color currentBrushColor = new Color(0, 0, 0, 0);
    
    private static Border panelBorder = BorderFactory.createMatteBorder(
    		BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BACKGROUND_COLOR);
    private static Border editorPanelBorder = BorderFactory.createMatteBorder(
    		BORDER_SIZE, 0, BORDER_SIZE, 0, BACKGROUND_COLOR);
    private static Border innerPanelBorder = BorderFactory.createEmptyBorder(
    		BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);

    public Window() {
    	initializeWindowContentPanel();
    }
    
    private static void initializeWindowContentPanel() {
    	initializeMenuBar();
    	initializeEditorPanel();
        initializeUpperToolbarPanel();
        initializeLowerToolbarPanel();
        
    	windowContentPanel.setBackground(BACKGROUND_COLOR);
        windowContentPanel.setLayout(new BorderLayout());
    	windowContentPanel.setBorder(panelBorder);
        windowContentPanel.add(editorPanel, BorderLayout.CENTER);
        windowContentPanel.add(upperToolbarPanel, BorderLayout.NORTH);
        windowContentPanel.add(lowerToolbarPanel, BorderLayout.SOUTH);
        windowContentPanel.setOpaque(true);
    }
    
    private static void initializeMenuBar() {
    	fileMenu.add(newMenuItem);
    	fileMenu.add(openMenuItem);
    	fileMenu.add(saveMenuItem);
    	fileMenu.add(saveAsMenuItem);
    	fileMenu.addSeparator();
    	fileMenu.add(preferencesMenuItem);
    	fileMenu.add(exportMenuItem);
    	fileMenu.addSeparator();
    	fileMenu.add(exitMenuItem);
    	menuBar.add(fileMenu);
    	
    	editMenu.add(undoMenuItem);
    	editMenu.add(redoMenuItem);
    	editMenu.addSeparator();
    	editMenu.add(fillMenuItem);
    	editMenu.add(replaceColorMenuItem);
    	editMenu.add(dupeMenuItem);
    	flipMenu.add(flipHorizontalMenuItem);
    	flipMenu.add(flipVerticalMenuItem);
    	flipMenu.add(flipDiagonalAscMenuItem);
    	flipMenu.add(flipDiagonalDescMenuItem);
    	editMenu.add(flipMenu);
    	rotateMenu.add(rotate90MenuItem);
    	rotateMenu.add(rotate180MenuItem);
    	rotateMenu.add(rotate270MenuItem);
    	editMenu.add(rotateMenu);
    	editMenu.add(shiftMenuItem);
    	editMenu.add(cropMenuItem);
    	editMenu.add(clearMenuItem);
    	editMenu.addSeparator();
    	editMenu.add(propertiesMenuItem);
    	menuBar.add(editMenu);
    	
    	helpMenu.add(helpGuideMenuItem);
    	helpMenu.add(aboutMenuItem);
    	menuBar.add(helpMenu);
    	
    	window.setJMenuBar(menuBar);
    }

	private static void initializeEditorPanel() {
    	editorPanel.setBackground(EDITOR_BACKGROUND_COLOR);
    	editorPanel.setBorder(editorPanelBorder);
    	editorPanel.setPreferredSize(EDITOR_DIMENSION);
    	editorCanvasPanel.setPreferredSize(editorSize);
    	editorCanvasPanel.setLayout(new GridLayout(editorPixelCountX, editorPixelCountY));
    	for (int i = 0; i < editorPixelCountX; i++) {
    		for (int j = 0; j < editorPixelCountY; j++) {
    			editorCanvasPanel.add(new Pixel(i, j));
    		}
    	}
    	editorPanel.add(editorCanvasPanel);
    }
    
    private static void initializeUpperToolbarPanel() {
    	upperToolbarPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
    	upperToolbarPanel.setBorder(innerPanelBorder);
    	upperToolbarPanel.setPreferredSize(TOOLBAR_DIMENSION);
    	upperToolbarPanel.setLayout(new BoxLayout(upperToolbarPanel, BoxLayout.X_AXIS));

    	paintModeButton.setBackground(ACCENT_COLOR);
        paintModeButton.setPreferredSize(BUTTON_DIMENSION);
        paintModeButton.setFocusPainted(false);
        upperToolbarPanel.add(paintModeButton);
    	selectModeButton.setFocusPainted(false);
    	selectModeButton.setPreferredSize(BUTTON_DIMENSION);
    	upperToolbarPanel.add(selectModeButton);
        
    	upperToolbarPanel.add(Box.createHorizontalGlue());

        selectedPaintColorLabel.setFont(MONO_FONT);
        selectedPaintColorLabel.setForeground(FOREGROUND_COLOR);
        selectedPaintColorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        upperToolbarPanel.add(selectedPaintColorLabel);
        upperToolbarPanel.add(Box.createRigidArea(new Dimension(BORDER_SIZE, 0)));
        currentBrushColor = Color.BLACK;
        chooseColorButton.setBackground(currentBrushColor);
        chooseColorButton.setFocusPainted(false);
		chooseColorButton.setPreferredSize(BUTTON_DIMENSION);
        chooseColorButton.addActionListener(new ColorChoiceAction());
        upperToolbarPanel.add(chooseColorButton);
    }
    
    private static void initializeLowerToolbarPanel() {
    	lowerToolbarPanel.setBackground(TOOLBAR_BACKGROUND_COLOR);
    	lowerToolbarPanel.setBorder(innerPanelBorder);
    	lowerToolbarPanel.setPreferredSize(TOOLBAR_DIMENSION);
    	lowerToolbarPanel.setLayout(new BoxLayout(lowerToolbarPanel, BoxLayout.X_AXIS));
    	
    	saveButton.setFocusPainted(false);
    	saveButton.setPreferredSize(BUTTON_DIMENSION);
    	lowerToolbarPanel.add(saveButton);
        lowerToolbarPanel.add(Box.createRigidArea(new Dimension(BORDER_SIZE, 0)));
        titleLabel.setFont(LABEL_FONT);
        titleLabel.setForeground(FOREGROUND_COLOR);
        lowerToolbarPanel.add(titleLabel);
        
        lowerToolbarPanel.add(Box.createHorizontalGlue());
        
        helpTextLabel.setFont(LABEL_FONT);
        helpTextLabel.setForeground(ACCENT_COLOR);
        helpTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lowerToolbarPanel.add(helpTextLabel);
        lowerToolbarPanel.add(Box.createRigidArea(new Dimension(BORDER_SIZE, 0)));
        mousePositionLabel.setFont(LABEL_FONT);
        mousePositionLabel.setForeground(FOREGROUND_COLOR);
        mousePositionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lowerToolbarPanel.add(mousePositionLabel);
    }
    
    private static void initializeWindow() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(WINDOW_DIMENSION);
        window.setContentPane(windowContentPanel);
        window.pack();
        window.setVisible(true);
    }
    
    public static void setPaintColor(Color c) {
    	selectedPaintColorLabel.setText(Utils.rgbToHex(c).toUpperCase());
    	currentBrushColor = c;
    	chooseColorButton.setBackground(c);
    }

    public static void main(String[] args) {
        windowInstance = new Window();
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeWindow();
            }
        });
    }
}

@SuppressWarnings("serial")
class Pixel extends JPanel {
	private Pixel pixel = this;
	private Border pixelBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(1, 1, 1, 1), 
			BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
	
	public Pixel(int xLoc, int yLoc) {
		super();
		this.setBorder(pixelBorder);
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				pixel.setBackground(Window.currentBrushColor);
				Utils.mouseDown = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) { 
				Utils.mouseDown = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) { 
				Window.mousePositionLabel.setText(xLoc + ":" + yLoc);
				if (Utils.mouseDown) {
					pixel.setBackground(Window.currentBrushColor);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { }
		});
	}
}