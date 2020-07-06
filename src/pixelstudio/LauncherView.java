package pixelstudio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class LauncherView extends JFrame {

	public static LauncherView launcherInstance = new LauncherView();

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField canvasSizeInput;
	private JTextField newNameInput;
	private JTextField pixelDensityInput;
	private JTextField browseInput;

	private final int MAX_TITLE_LENGTH = 50;
	private final int MAX_CANVAS_SIZE = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LauncherView frame = launcherInstance;
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
	public LauncherView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 250);
		setTitle("Pixel Studio Launcher");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel newOpenRadioButtonsPanel = new JPanel();
		newOpenRadioButtonsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		newOpenRadioButtonsPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(newOpenRadioButtonsPanel, BorderLayout.NORTH);
		newOpenRadioButtonsPanel.setLayout(new GridLayout(1, 2, 0, 0));

		
		JPanel newOpenSelectionCardPanel = new JPanel();
		newOpenSelectionCardPanel.setBackground(new Color(32, 32, 32));
		contentPane.add(newOpenSelectionCardPanel, BorderLayout.CENTER);
		newOpenSelectionCardPanel.setLayout(new CardLayout(0, 0));

		JPanel newSelectionContentPanel = new JPanel();
		newSelectionContentPanel.setBackground(Color.DARK_GRAY);
		newOpenSelectionCardPanel.add(newSelectionContentPanel, "name_162050057203400");

		JPanel openSelectionContentPanel = new JPanel();
		openSelectionContentPanel.setBackground(Color.DARK_GRAY);
		newOpenSelectionCardPanel.add(openSelectionContentPanel, "name_162067153370100");

		JRadioButton newRadioButton = new JRadioButton("New");
		newRadioButton.setSelected(true);
		newRadioButton.setForeground(Color.WHITE);
		newRadioButton.setBackground(Color.DARK_GRAY);
		newRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (newRadioButton.isSelected()) {
					newSelectionContentPanel.setVisible(true);
					openSelectionContentPanel.setVisible(false);
				}
			}
			
		});
		newOpenRadioButtonsPanel.add(newRadioButton);
		
		JRadioButton openRadioButton = new JRadioButton("Open");
		openRadioButton.setSelected(false);
		openRadioButton.setForeground(Color.WHITE);
		openRadioButton.setBackground(Color.DARK_GRAY);
		openRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (openRadioButton.isSelected()) {
					openSelectionContentPanel.setVisible(true);
					newSelectionContentPanel.setVisible(false);
				}
			}
			
		});
		newOpenRadioButtonsPanel.add(openRadioButton);

		ButtonGroup newOpenButtonsGroup = new ButtonGroup();
		newOpenButtonsGroup.add(newRadioButton);
		newOpenButtonsGroup.add(openRadioButton);
		
		JButton createNewProjectButton = new JButton("Create");
		createNewProjectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String projectTitle;
				int canvasSize;
				int pixelDensity;
				projectTitle = newNameInput.getText();
				if (projectTitle.length() > MAX_TITLE_LENGTH) {
					JOptionPane.showMessageDialog(launcherInstance, "Project title can be up to " + MAX_TITLE_LENGTH + " characters.");
					return;
				}
				if (projectTitle.length() == 0) {
					JOptionPane.showMessageDialog(launcherInstance, "Project title is required.");
					return;
				}

				try {
					canvasSize = Integer.parseInt(canvasSizeInput.getText());
					if (canvasSize > MAX_CANVAS_SIZE) {
						JOptionPane.showMessageDialog(launcherInstance, "Canvas size can be up to " + MAX_CANVAS_SIZE + "px.");
						return;
					}
					if (canvasSize <= 0) {
						JOptionPane.showMessageDialog(launcherInstance, "Invalid canvas size.");
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(launcherInstance, "Invalid canvas size.");
					return;
				}
				
				try {
					if (pixelDensityInput.getText() == "" || pixelDensityInput.getText() == null){
						JOptionPane.showMessageDialog(launcherInstance, "Pixel density is required.");
						return;
					}
					pixelDensity = Integer.parseInt(pixelDensityInput.getText());
					if (pixelDensity > canvasSize) {
						JOptionPane.showMessageDialog(launcherInstance, "You cannot have a pixel density larger than the size of the canvas.");
						return;
					}
					if (pixelDensity <= 0) {
						JOptionPane.showMessageDialog(launcherInstance, "Invalid pixel density.");
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(launcherInstance, "Invalid pixel density.");
					return;
				}
				launchApplication(projectTitle, canvasSize, pixelDensity);
			}
			
		});
		
		JLabel newNameLabel = new JLabel("Name");
		newNameLabel.setToolTipText("Name of project");
		newNameLabel.setForeground(Color.WHITE);
		
		JLabel canvasSizeLabel = new JLabel("Canvas Size");
		canvasSizeLabel.setToolTipText("Width of the canvas (in pixels)");
		canvasSizeLabel.setForeground(Color.WHITE);
		
		JLabel pixelLabel = new JLabel("Pixel Density");
		pixelLabel.setToolTipText("Number of pixels contained horizontally in the picture");
		pixelLabel.setForeground(Color.WHITE);
		
		canvasSizeInput = new JTextField();
		canvasSizeInput.setColumns(35);
		
		newNameInput = new JTextField();
		newNameInput.setColumns(35);
		
		pixelDensityInput = new JTextField();
		pixelDensityInput.setColumns(35);
		GroupLayout gl_newSelectionContentPanel = new GroupLayout(newSelectionContentPanel);
		gl_newSelectionContentPanel.setHorizontalGroup(
			gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
							.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(newNameLabel)
								.addComponent(canvasSizeLabel)
								.addComponent(pixelLabel))
							.addGap(21)
							.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(newNameInput, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
								.addComponent(canvasSizeInput, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
								.addComponent(pixelDensityInput, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
						.addComponent(createNewProjectButton, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_newSelectionContentPanel.setVerticalGroup(
			gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(newNameLabel)
						.addComponent(newNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(canvasSizeLabel)
						.addComponent(canvasSizeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pixelLabel)
						.addComponent(pixelDensityInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(createNewProjectButton)
					.addContainerGap())
		);
		newSelectionContentPanel.setLayout(gl_newSelectionContentPanel);
		
		JList<String> recentlyOpenedList = new JList<String>();
		
		browseInput = new JTextField();
		browseInput.setColumns(10);
		
		JButton browseButton = new JButton("Browse");
		
		JButton openButton = new JButton("Open");
		GroupLayout gl_openSelectionContentPanel = new GroupLayout(openSelectionContentPanel);
		gl_openSelectionContentPanel.setHorizontalGroup(
			gl_openSelectionContentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_openSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_openSelectionContentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(recentlyOpenedList, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_openSelectionContentPanel.createSequentialGroup()
							.addComponent(browseInput, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(browseButton))
						.addComponent(openButton, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_openSelectionContentPanel.setVerticalGroup(
			gl_openSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_openSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_openSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(browseInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(recentlyOpenedList, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(openButton)
					.addContainerGap())
		);
		openSelectionContentPanel.setLayout(gl_openSelectionContentPanel);
	}

	private void launchApplication(String name, int canvasSize, int pixelDensity) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View(name, canvasSize, pixelDensity);
					frame.setVisible(true);
					launcherInstance.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
