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
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private JLabel lblMain = new JLabel("");
	private JPanel contentPane;
	private JCheckBox checkboxOldLoading; // // thực hiện load chồng các xử lí
											// đã áp dụng trước đó
	private BufferedImage oldLoadingImage;
	private JTextField txtThreshold;

	private OptionForm optionForm = new OptionForm();
	private JTextField txtPowerParameter = new JTextField();
	private JTextField txtCParameterInPowerLaw;
	private JSlider sliderOfGammaInPowerLaw = new JSlider();

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
		Image image = outputImage.getScaledInstance(outputImage.getWidth(), outputImage.getHeight(),
				Image.SCALE_SMOOTH);
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

					// reset oldLoadingImage
					oldLoadingImage = null;

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

		JMenuItem mntmMoreOption = new JMenuItem("More Option");
		mntmMoreOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionForm.setVisible(true);
			}
		});
		mnOption.add(mntmMoreOption);
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
				Histogram histogram = new Histogram(getInputImage());
				histogram.processing();
				outputImage = histogram.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnLineChartOfHistogram = new JButton("Line Chart");
		btnLineChartOfHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HistogramChart histogramChart = new HistogramChart(getInputImage());
				histogramChart.processing();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addComponent(btnHistogramEqualization)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnLineChartOfHistogram)
						.addContainerGap(1171, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHistogramEqualization, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
						.addComponent(btnLineChartOfHistogram, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)));
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
					int threshold = Integer.parseInt(txtThreshold.getText());
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

		JButton btnPowerLawTranformation = new JButton("Power law");
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

		txtThreshold = new JTextField();
		txtThreshold.setText("128");
		txtThreshold.setHorizontalAlignment(SwingConstants.TRAILING);
		txtThreshold.setColumns(10);

		JLabel lblThreshold = new JLabel("Threshold:");

		JButton btnGrayLevelSlicing = new JButton("Gray level slicing");
		btnGrayLevelSlicing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GrayLevelSlicing grayLevelSlicing = new GrayLevelSlicing(getInputImage());
				int minPosition = optionForm.getMinPostionOfGrayLevelSlicingClass();
				int maxPosition = optionForm.getMaxPostionOfGrayLevelSlicingClass();
				grayLevelSlicing.processing(minPosition, maxPosition);
				outputImage = grayLevelSlicing.getOutputImage();
				loadImageToLabel();
			}
		});

		JLabel label = new JLabel("Gamma:");

		JLabel label_1 = new JLabel("C:");

		sliderOfGammaInPowerLaw = new JSlider();
		sliderOfGammaInPowerLaw.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Double value = sliderOfGammaInPowerLaw.getValue() / 100.0;
				txtPowerParameter.setText(String.valueOf(value));
			}
		});

		sliderOfGammaInPowerLaw.setBackground(Color.WHITE);
		sliderOfGammaInPowerLaw.setForeground(Color.WHITE);
		sliderOfGammaInPowerLaw.setMaximum(1000);

		txtPowerParameter = new JTextField();
		txtPowerParameter.setText("5");
		txtPowerParameter.setColumns(10);

		txtCParameterInPowerLaw = new JTextField();
		txtCParameterInPowerLaw.setText("1");
		txtCParameterInPowerLaw.setColumns(10);
		final JCheckBox checkBoxBitPlace0 = new JCheckBox("0");
		checkBoxBitPlace0.setSelected(true);
		final JCheckBox checkBoxBitPlace1 = new JCheckBox("1");
		final JCheckBox checkBoxBitPlace2 = new JCheckBox("2");
		final JCheckBox checkBoxBitPlace3 = new JCheckBox("3");
		final JCheckBox checkBoxBitPlace4 = new JCheckBox("4");
		final JCheckBox checkBoxBitPlace5 = new JCheckBox("5");
		final JCheckBox checkBoxBitPlace6 = new JCheckBox("6");
		final JCheckBox checkBoxBitPlace7 = new JCheckBox("7");

		JButton btnBitPlaceSlicing = new JButton("Bit Place Slicing");
		btnBitPlaceSlicing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int[] choosedBitArray = new int[8];
				choosedBitArray[0] = (checkBoxBitPlace0.isSelected() == true) ? 1 : 0;
				choosedBitArray[1] = (checkBoxBitPlace1.isSelected() == true) ? 1 : 0;
				choosedBitArray[2] = (checkBoxBitPlace2.isSelected() == true) ? 1 : 0;
				choosedBitArray[3] = (checkBoxBitPlace3.isSelected() == true) ? 1 : 0;
				choosedBitArray[4] = (checkBoxBitPlace4.isSelected() == true) ? 1 : 0;
				choosedBitArray[5] = (checkBoxBitPlace5.isSelected() == true) ? 1 : 0;
				choosedBitArray[6] = (checkBoxBitPlace6.isSelected() == true) ? 1 : 0;
				choosedBitArray[7] = (checkBoxBitPlace7.isSelected() == true) ? 1 : 0;
			
				BitPlaneSlicing bitPlaneSlicing = new BitPlaneSlicing(getInputImage());
				bitPlaneSlicing.processing(choosedBitArray);
				outputImage = bitPlaneSlicing.getOutputImage();
				loadImageToLabel();
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(btnGrayscale, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblThreshold)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnThresholding, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNegative, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogarithmic, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGrayLevelSlicing)
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(checkBoxBitPlace0)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkBoxBitPlace1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkBoxBitPlace2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkBoxBitPlace3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnBitPlaceSlicing, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(281)
							.addComponent(btnPowerLawTranformation, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(checkBoxBitPlace4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBoxBitPlace5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBoxBitPlace6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBoxBitPlace7, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(25)
							.addComponent(sliderOfGammaInPowerLaw, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPowerParameter, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtCParameterInPowerLaw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnGrayscale, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(btnThresholding, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblThreshold))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPowerParameter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(label)
										.addComponent(sliderOfGammaInPowerLaw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCParameterInPowerLaw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(btnPowerLawTranformation, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBitPlaceSlicing)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkBoxBitPlace0)
										.addComponent(checkBoxBitPlace1)
										.addComponent(checkBoxBitPlace2)
										.addComponent(checkBoxBitPlace3))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(checkBoxBitPlace4)
										.addComponent(checkBoxBitPlace5)
										.addComponent(checkBoxBitPlace6)
										.addComponent(checkBoxBitPlace7)))))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnNegative, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnLogarithmic, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
							.addComponent(btnGrayLevelSlicing, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(17))
		);
		panel_1.setLayout(gl_panel_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Spatial Filtering", null, panel_4, null);

		JButton btnMinFillter = new JButton("Min Fillter");
		btnMinFillter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MinFilter minFilter = new MinFilter(getInputImage());
				int sizeOfFillter = optionForm.getSizeOfFilterInMinFillterClass();
				minFilter.processing(sizeOfFillter);
				outputImage = minFilter.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnAddSalt = new JButton("Add Salt");
		btnAddSalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddSalt addSalt = new AddSalt(getInputImage());
				Double percentOfSalt = optionForm.getPercentOfSaltOfAddSaltClass();
				addSalt.processing(percentOfSalt);
				outputImage = addSalt.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnMaxFillter = new JButton("Max Fillter");
		btnMaxFillter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MaxFilter maxFilter = new MaxFilter(getInputImage());
				int sizeOfFillter = optionForm.getSizeOfFilterInMinFillterClass();
				maxFilter.processing(sizeOfFillter);
				outputImage = maxFilter.getOutputImage();
				loadImageToLabel();

			}
		});

		JButton btnMedianFillter = new JButton("Median Fillter");
		btnMedianFillter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedianFilter medianFilter = new MedianFilter(getInputImage());
				int sizeOfFillter = optionForm.getSizeOfFilterInMedianFillterClass();
				medianFilter.processing(sizeOfFillter);
				outputImage = medianFilter.getOutputImage();
				loadImageToLabel();

			}
		});

		JButton btnEverageFillter = new JButton("Everage Fillter");
		btnEverageFillter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EverageFilter everageFilter = new EverageFilter(getInputImage());
				int sizeOfFillter = optionForm.getSizeOfFilterInEverageFillterClass();
				everageFilter.processing(sizeOfFillter);
				outputImage = everageFilter.getOutputImage();
				loadImageToLabel();

			}
		});
		
		JButton btnGaussian = new JButton("Gaussian");
		btnGaussian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gaussian gaussian = new Gaussian(getInputImage()) ; 
				int filterSize = optionForm.getSizeOfFilterInGaussian() ; 
				double sigma = optionForm.getSigmaInGaussian() ; 
				gaussian.processing(filterSize, sigma);
				outputImage = gaussian.getOutputImage() ; 
				loadImageToLabel();
				
			}
		});
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(btnMinFillter, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMaxFillter, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMedianFillter)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEverageFillter, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGaussian)
					.addPreferredGap(ComponentPlacement.RELATED, 766, Short.MAX_VALUE)
					.addComponent(btnAddSalt)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAddSalt)
					.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnMinFillter, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addComponent(btnMaxFillter, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addComponent(btnMedianFillter, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnEverageFillter, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnGaussian, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
		);
		panel_4.setLayout(gl_panel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Edge Detected", null, panel_2, null);

		JButton btnCanny = new JButton("Canny");
		btnCanny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Canny canny = new Canny(getInputImage());
				int low = optionForm.getLowValueFromCannyClass() ; 
				int high = optionForm.getHighValueFromCannyClass() ; 
				canny.processing(low, high);
				outputImage = canny.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnSobel = new JButton("Sobel");
		btnSobel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Sobel sobel = new Sobel(getInputImage());
				int[][] gX = optionForm.getGxFromSobelClass() ; 
				int[][] gY = optionForm.getGxFromSobelClass() ; 
				sobel.processing(gX,gY);
				outputImage = sobel.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnLaplaceOfGaussian = new JButton("LoG");
		btnLaplaceOfGaussian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LaplaceOfGaussian log = new LaplaceOfGaussian(getInputImage());
				outputImage = log.processing(7, 0.7);
				loadImageToLabel();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnCanny, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSobel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLaplaceOfGaussian, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1117, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnCanny, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addComponent(btnSobel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnLaplaceOfGaussian, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE).addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1352, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
										.addContainerGap()));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Morphology", null, panel_5, null);
		
		JButton btnEroson = new JButton("Eroson");
		btnEroson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eroson eroson = new Eroson(getInputImage()) ; 
				int kernelSize = optionForm.getKernelSizeInErosonClass() ; 
				eroson.processing(kernelSize);
				outputImage = eroson.getOutputImage() ; 
				loadImageToLabel();
			}
		});
		
		JButton btnDilation = new JButton("Dilation");
		btnDilation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dilation dilation = new Dilation(getInputImage()) ; 
				int kernelSize = optionForm.getKernelSizeOfDilasionClass() ; 
				dilation.processing(kernelSize);
				outputImage = dilation.getOutputImage() ; 
				loadImageToLabel();
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(btnEroson, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDilation, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1171, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEroson, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
						.addComponent(btnDilation, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_5.setLayout(gl_panel_5);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Artistic", null, panel_3, null);

		JButton btnOilPainting = new JButton("Oil Painting");
		btnOilPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OilPainting oilPainting = new OilPainting(getInputImage());
				int radius = optionForm.geRadiusOfOilPainting();
				int intensityLevels = optionForm.geItensityLevelOfOilPainting();
				oilPainting.processing(radius, intensityLevels);
				outputImage = oilPainting.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnHalftoning = new JButton("Halftoning");
		btnHalftoning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Halftoning halftoning = new Halftoning(getInputImage());
				halftoning.processing();
				outputImage = halftoning.getOutputImage();
				loadImageToLabel();
			}
		});

		JButton btnPatterning = new JButton("Patterning");
		btnPatterning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Patterning patterning = new Patterning(getInputImage());
				patterning.processing();
				outputImage = patterning.getOutputImage();
				loadImageToLabel();

			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3
				.setHorizontalGroup(
						gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addComponent(btnOilPainting)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnHalftoning, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPatterning)
										.addContainerGap(1086, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(btnOilPainting, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
				.addComponent(btnHalftoning, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
				.addComponent(btnPatterning, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE));
		panel_3.setLayout(gl_panel_3);
		lblMain.setForeground(Color.WHITE);
		lblMain.setBackground(Color.WHITE);

		lblMain.setIcon(null);
		scrollPane.setViewportView(lblMain);
		contentPane.setLayout(gl_contentPane);
	}
}
