import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private JLabel lblMain = new JLabel("");
	private JPanel contentPane;
	private JCheckBox checkboxOldLoading; // // thực hiện load chồng các xử lí
											// đã áp dụng trước đó
	private BufferedImage oldLoadingImage;
	private JTextField txtPowerParameter;
	private JSlider sliderPowerParameter;
	private JTextField txtCParameterInPowerLaw;
	private JTextField txtThreshold;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void loadImageToLabel() {
		if (checkboxOldLoading.isSelected()) {
			oldLoadingImage = outputImage;
		}
		Image image = outputImage.getScaledInstance(inputImage.getWidth(), inputImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(image);
		lblMain.setIcon(imageIcon);
	}

	private BufferedImage getInputImage() {
		// thực hiện load chồng các xử lí đã áp dụng trước đó
		if (checkboxOldLoading.isSelected() && oldLoadingImage != null) {
			return oldLoadingImage;
		} else {
			return inputImage;
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		// load 'look and feel' packet
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception ex) {
		}
		// ------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		this.setExtendedState(Frame.MAXIMIZED_BOTH); // set full size of screen

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("FILE");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Open Image:");
				// TypeFillter là lớp mình tạo ra để lọc các file ảnh
				chooser.setFileFilter(new TypeFillter());
				chooser.showOpenDialog(null);
				String location;
				if (chooser.getSelectedFile() != null) {
					File f = chooser.getSelectedFile();
					location = f.getAbsolutePath();

					try {
						inputImage = ImageIO.read(new File(location));
					} catch (IOException ex) {
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
					}

					Image i = inputImage.getScaledInstance(inputImage.getWidth(), inputImage.getHeight(),
							Image.SCALE_AREA_AVERAGING);
					ImageIcon icon = new ImageIcon(i);
					lblMain.setIcon(icon);

				}

			}
		});

		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lblMain == null) {
					JOptionPane.showMessageDialog(null, "Chưa có thông tin để lưu ảnh!", "Lỗi",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JFileChooser save = new JFileChooser();
					save.setFileFilter(new TypeFillter());
					save.setDialogTitle("Lưu ảnh");

					int result = save.showSaveDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = save.getSelectedFile();

						String fileName = file.getName();
						// nếu không có phần mở rộng thì thêm vào đuôi png
						if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".bmp"))) {
							file = new File(file.getAbsolutePath() + ".png");
						}
						try {
							ImageIO.write(outputImage, "png", file);
						} catch (IOException ex) {
							System.out.println("ERROR: " + ex.getMessage());
						}
					}
				}
			}
		});
		mnFile.add(mntmSave);

		JMenu mnOption = new JMenu("OPTION");
		menuBar.add(mnOption);

		checkboxOldLoading = new JCheckBox("Old Loading");
		mnOption.add(checkboxOldLoading);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Histogram", null, panel, null);
		
		JButton btnHistogramEqualization = new JButton("Equalization");
		btnHistogramEqualization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Histogram histogram = new Histogram(getInputImage()) ; 
				histogram.processing(); 
				outputImage = histogram.getOutputImage() ; 
				loadImageToLabel(); 
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnHistogramEqualization)
					.addContainerGap(1258, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(btnHistogramEqualization, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Point Processing", null, panel_1, null);

		JButton btnGrayscale = new JButton("Grayscale");
		btnGrayscale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblMain.getIcon() == null) {
					JOptionPane.showMessageDialog(null, "Chưa có ảnh để xử lí!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				} else {
					Grayscale grayscale = new Grayscale(getInputImage());
					grayscale.processing();
					outputImage = grayscale.getOutputImage();
					loadImageToLabel();

				}

			}
		});

		JButton btnThresholding = new JButton("Thresholding");
		btnThresholding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblMain.getIcon() == null) {
					JOptionPane.showMessageDialog(null, "Chưa có ảnh để xử lí!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				} else {
					Thresholding thresholding = new Thresholding(getInputImage());
					int threshold = Integer.parseInt( txtThreshold.getText() ) ; 
					thresholding.processing(threshold);
					outputImage = thresholding.getOutputImage();
					loadImageToLabel();
				}
			}
		});

		JButton btnNegative = new JButton("Negative ");
		btnNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblMain.getIcon() == null) {
					JOptionPane.showMessageDialog(null, "Chưa có ảnh để xử lí!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				} else {
					Negative negative = new Negative(getInputImage());
					negative.processing();
					outputImage = negative.getOutputImage();
					loadImageToLabel();
				}

			}
		});

		JButton btnPowerLawTranformation = new JButton("Power-law");
		btnPowerLawTranformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PowerLawTranformation powerLawTranformation = new PowerLawTranformation(getInputImage());
				double power = Double.parseDouble(txtPowerParameter.getText());
				double c = Double.parseDouble(txtCParameterInPowerLaw.getText());
				powerLawTranformation.processing(power, c);
				outputImage = powerLawTranformation.getOutputImage();
				loadImageToLabel();
			}
		});

		txtPowerParameter = new JTextField();
		txtPowerParameter.setText("5");
		txtPowerParameter.setColumns(10);

		sliderPowerParameter = new JSlider();
		sliderPowerParameter.setValue(500);
		sliderPowerParameter.setMaximum(1000);
		sliderPowerParameter.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// tham số hàm số mũ
				txtPowerParameter.setText(String.valueOf(sliderPowerParameter.getValue() / 100.0));
			}
		});

		JLabel lblNewLabel = new JLabel("Power Law Tranformation Parameter:");

		txtCParameterInPowerLaw = new JTextField();
		txtCParameterInPowerLaw.setText("1");
		txtCParameterInPowerLaw.setColumns(10);

		JButton btnLogarithmic = new JButton("Logarithmic");
		btnLogarithmic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogarithmicTransformation logarithmic = new LogarithmicTransformation(getInputImage());
				double c = Double.parseDouble(txtCParameterInPowerLaw.getText());
				logarithmic.processing(c);
				outputImage = logarithmic.getOutputImage();
				loadImageToLabel();
			}
		});
		
		JLabel lblGamma = new JLabel("Gamma:");
		
		JLabel lblC = new JLabel("C:");
		
		txtThreshold = new JTextField();
		txtThreshold.setText("128");
		txtThreshold.setHorizontalAlignment(SwingConstants.TRAILING);
		txtThreshold.setColumns(10);
		
		JLabel lblThreshold = new JLabel("Threshold:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnGrayscale)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnThresholding)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNegative, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogarithmic, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(btnPowerLawTranformation, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(489)
					.addComponent(lblThreshold)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGamma)
								.addComponent(lblC))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCParameterInPowerLaw, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(sliderPowerParameter, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPowerParameter, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
							.addGap(1065))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addContainerGap())))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnGrayscale, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addComponent(btnThresholding, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addComponent(btnNegative, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addComponent(btnLogarithmic, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnPowerLawTranformation, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(3)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGamma)
								.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblThreshold))
							.addGap(18)
							.addComponent(lblC))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtPowerParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sliderPowerParameter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCParameterInPowerLaw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("New tab", null, panel_2, null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE).addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1352, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
										.addContainerGap()));

		lblMain.setIcon(new ImageIcon("C:\\Users\\VanNinh\\OneDrive\\Pictures\\Stay-Hungry-Stay-Foolish.jpg"));
		scrollPane.setViewportView(lblMain);
		contentPane.setLayout(gl_contentPane);
	}
}
