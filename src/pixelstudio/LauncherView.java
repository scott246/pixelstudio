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
import javax.swing.SpinnerNumberModel;

import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

public class LauncherView extends JFrame {

	public static LauncherView launcherInstance = new LauncherView();

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField newNameInput;
	private static JSpinner pixelSizeSpinner;
	private static JSpinner pixelDensitySpinner;
	private JTextField browseInput;

	private static final int MAX_TITLE_LENGTH = 50;
	private static final int MAX_PIXEL_SIZE = 500;
	private static final int MAX_PIXEL_DENSITY = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					launcherInstance.setVisible(true);
					newNameInput.grabFocus();
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

		JButton createNewProjectButton = new JButton("Create");
		getRootPane().setDefaultButton(createNewProjectButton);
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
					getRootPane().setDefaultButton(createNewProjectButton);
				}
			}
			
		});
		newOpenRadioButtonsPanel.add(newRadioButton);
		
		JButton openButton = new JButton("Open");
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
					getRootPane().setDefaultButton(openButton);
				}
			}
			
		});
		newOpenRadioButtonsPanel.add(openRadioButton);

		ButtonGroup newOpenButtonsGroup = new ButtonGroup();
		newOpenButtonsGroup.add(newRadioButton);
		newOpenButtonsGroup.add(openRadioButton);
		
		createNewProjectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String projectTitle;
				int pixelSize;
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
					pixelSize = (int)pixelSizeSpinner.getValue();
					if (pixelSize > MAX_PIXEL_SIZE) {
						JOptionPane.showMessageDialog(launcherInstance, "Pixel size can be up to " + MAX_PIXEL_SIZE + "px.");
						return;
					}
					if (pixelSize <= 0) {
						JOptionPane.showMessageDialog(launcherInstance, "Invalid pixel size.");
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(launcherInstance, "Invalid pixel size.");
					return;
				}
				
				try {
					pixelDensity = (int)pixelDensitySpinner.getValue();
					if (pixelDensity > MAX_PIXEL_DENSITY){
						JOptionPane.showMessageDialog(launcherInstance, "Pixel density can be up to " + MAX_PIXEL_DENSITY + ".");
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
				launchApplication(projectTitle, pixelSize, pixelDensity);
			}
			
		});
		
		JLabel newNameLabel = new JLabel("Name");
		newNameLabel.setToolTipText("Name of project");
		newNameLabel.setForeground(Color.WHITE);
		
		JLabel pixelSizeLabel = new JLabel("Pixel Size");
		pixelSizeLabel.setToolTipText("Width and height of each pixel");
		pixelSizeLabel.setForeground(Color.WHITE);
		
		JLabel pixelDensityLabel = new JLabel("Pixel Density");
		pixelDensityLabel.setToolTipText("Number of pixels contained horizontally and vertically in the picture");
		pixelDensityLabel.setForeground(Color.WHITE);
		
		newNameInput = new JTextField();
		newNameInput.setColumns(35);
		
		pixelSizeSpinner = new JSpinner(new SpinnerNumberModel(16,1,MAX_PIXEL_SIZE,1));
		pixelDensitySpinner = new JSpinner(new SpinnerNumberModel(16,1,MAX_PIXEL_DENSITY,1));
		
		GroupLayout gl_newSelectionContentPanel = new GroupLayout(newSelectionContentPanel);
		gl_newSelectionContentPanel.setHorizontalGroup(
			gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newSelectionContentPanel.createSequentialGroup()
							.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(newNameLabel)
								.addComponent(pixelSizeLabel)
								.addComponent(pixelDensityLabel))
							.addGap(21)
							.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(pixelDensitySpinner, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(pixelSizeSpinner, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(newNameInput, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
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
						.addComponent(pixelSizeLabel)
						.addComponent(pixelSizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_newSelectionContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pixelDensityLabel)
						.addComponent(pixelDensitySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addComponent(createNewProjectButton)
					.addContainerGap())
		);
		newSelectionContentPanel.setLayout(gl_newSelectionContentPanel);
		
		JList<String> recentlyOpenedList = new JList<String>();
		
		browseInput = new JTextField();
		browseInput.setColumns(10);
		
		JButton browseButton = new JButton("Browse");
		
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

	private void launchApplication(String name, int pixelSize, int pixelDensity) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View(name, pixelSize, pixelDensity);
					frame.setVisible(true);
					launcherInstance.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
