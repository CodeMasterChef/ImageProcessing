import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class OptionForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField txtGxInSobel00;
	private JTextField txtRadiusOfOilPainting;
	private JTextField txtItensityLevelOfOilPainting;
	private JTextField txtPercentOfSaltOfAddSaltClass;
	private JTextField txtSizeOfFilterInMinFillterClass;
	private JTextField txtSizeOfFilterInMaxFillterClass;
	private JTextField txtSizeOfFilterInMedianFillterClass;
	private JTextField txtSizeOfFilterInEverageFillterClass;
	private JTextField txtMinPostionOfGrayLevelSlicingClass;
	private JTextField txtMaxPostionOfGrayLevelSlicingClass;
	private JTextField txtKernelSizeInErosonClass;
	private JTextField txtKernelSizeOfDilasionClass;
	private JTextField txtGxInSobel01;
	private JTextField txtGxInSobel02;
	private JTextField txtGxInSobel10;
	private JTextField txtGxInSobel11;
	private JTextField txtGxInSobel12;
	private JTextField txtGxInSobel20;
	private JTextField txtGxInSobel21;
	private JTextField txtGxInSobel22;
	private JTextField txtGyInSobel00;
	private JTextField txtGyInSobel01;
	private JTextField txtGyInSobel02;
	private JTextField txtGyInSobel12;
	private JTextField txtGyInSobel11;
	private JTextField txtGyInSobel10;
	private JTextField txtGyInSobel20;
	private JTextField txtGyInSobel21;
	private JTextField txtGyInSobel22;
	private JLabel lblCanny;
	private JLabel lblLow;
	private JTextField txtLowValueFromCannyClass;
	private JLabel lblHigh;
	private JTextField txtHighValueFromCannyClass;
	private JLabel lblGaussian;
	private JLabel label_5;
	private JTextField txtSizeOfFitterInGaussian;
	private JLabel lblSigma;
	private JTextField txtSigmaInGaussian;

	public int getGxMatrixInSobel() {
		return Integer.parseInt(txtGxInSobel00.getText());
	}

	public int geRadiusOfOilPainting() {
		return Integer.parseInt(txtRadiusOfOilPainting.getText());
	}

	public int geItensityLevelOfOilPainting() {
		return Integer.parseInt(txtItensityLevelOfOilPainting.getText());
	}
	public double getPercentOfSaltOfAddSaltClass() {
		return Double.parseDouble(txtPercentOfSaltOfAddSaltClass.getText());
	}
	public int getSizeOfFilterInMinFillterClass() {
		return Integer.parseInt( txtSizeOfFilterInMinFillterClass.getText() );
	}
	public int getSizeOfFilterInMaxFillterClass() {
		return Integer.parseInt( txtSizeOfFilterInMaxFillterClass.getText() );
	}
	public int getSizeOfFilterInMedianFillterClass() {
		return Integer.parseInt( txtSizeOfFilterInMedianFillterClass.getText() );
	}
	public int getSizeOfFilterInEverageFillterClass() {
		return Integer.parseInt( txtSizeOfFilterInEverageFillterClass.getText() );
	}
	public int getMinPostionOfGrayLevelSlicingClass() {
		return Integer.parseInt( txtMinPostionOfGrayLevelSlicingClass.getText() );
	}
	public int getMaxPostionOfGrayLevelSlicingClass() {
		return Integer.parseInt( txtMaxPostionOfGrayLevelSlicingClass.getText() );
	}

	public int getKernelSizeInErosonClass() { 
		return Integer.parseInt( txtKernelSizeInErosonClass.getText() ) ; 
	}
	public int getKernelSizeOfDilasionClass() { 
		return Integer.parseInt( txtKernelSizeOfDilasionClass.getText() ) ; 
	}
	public int[][] getGxFromSobelClass() { 
		int[][] gX = new int[3][3] ; 
		gX[0][0] = Integer.parseInt(  txtGxInSobel00.getText()) ; 
		gX[0][1] = Integer.parseInt(  txtGxInSobel01.getText()) ; 
		gX[0][2] = Integer.parseInt(  txtGxInSobel02.getText()) ; 
		
		gX[1][0] = Integer.parseInt(  txtGxInSobel10.getText()) ; 
		gX[1][1] = Integer.parseInt(  txtGxInSobel11.getText()) ; 
		gX[1][2] = Integer.parseInt(  txtGxInSobel12.getText()) ; 
		
		gX[2][0] = Integer.parseInt(  txtGxInSobel20.getText()) ; 
		gX[2][1] = Integer.parseInt(  txtGxInSobel21.getText()) ; 
		gX[2][2] = Integer.parseInt(  txtGxInSobel22.getText()) ; 
		return gX; 
	}
	public int[][] getGyFromSobelClass() { 
		int[][] gY = new int[3][3] ; 
		gY[0][0] = Integer.parseInt(  txtGyInSobel00.getText()) ; 
		gY[0][1] = Integer.parseInt(  txtGyInSobel01.getText()) ; 
		gY[0][2] = Integer.parseInt(  txtGyInSobel02.getText()) ; 

		gY[1][0] = Integer.parseInt(  txtGyInSobel10.getText()) ; 
		gY[1][1] = Integer.parseInt(  txtGyInSobel11.getText()) ; 
		gY[1][2] = Integer.parseInt(  txtGyInSobel12.getText()) ; 

		gY[2][0] = Integer.parseInt(  txtGyInSobel20.getText()) ; 
		gY[2][1] = Integer.parseInt(  txtGyInSobel21.getText()) ; 
		gY[2][2] = Integer.parseInt(  txtGyInSobel22.getText()) ; 
		return gY; 
	}
	public int getLowValueFromCannyClass() { 
		return Integer.parseInt( txtLowValueFromCannyClass.getText() ) ; 
	}
	public int getHighValueFromCannyClass() { 
		return Integer.parseInt( txtHighValueFromCannyClass.getText() ) ; 
	}
	
	public int getSizeOfFilterInGaussian() { 
		return Integer.parseInt( txtSizeOfFitterInGaussian.getText() ) ; 
	}
	public double getSigmaInGaussian() { 
		return Double.parseDouble( txtSigmaInGaussian.getText() ) ; 
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionForm frame = new OptionForm();
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
	public OptionForm() {
		// load 'look and feel' packet
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception ex) {
		}
		setBounds(100, 100, 559, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE));
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Point Processing", null, panel_3, null);
		
		JLabel label_3 = new JLabel("");
		
		JLabel lblGrayLevelSlicing = new JLabel("Gray Level Slicing:");
		lblGrayLevelSlicing.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblMinPostion = new JLabel("Min postion:");
		
		JLabel lblMaxPosition = new JLabel("Max Position:");
		
		txtMinPostionOfGrayLevelSlicingClass = new JTextField();
		txtMinPostionOfGrayLevelSlicingClass.setText("100");
		txtMinPostionOfGrayLevelSlicingClass.setColumns(10);
		
		txtMaxPostionOfGrayLevelSlicingClass = new JTextField();
		txtMaxPostionOfGrayLevelSlicingClass.setText("150");
		txtMaxPostionOfGrayLevelSlicingClass.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGrayLevelSlicing)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(lblMaxPosition, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtMaxPostionOfGrayLevelSlicingClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(lblMinPostion, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtMinPostionOfGrayLevelSlicingClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
					.addContainerGap(334, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGrayLevelSlicing, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMinPostion)
						.addComponent(txtMinPostionOfGrayLevelSlicingClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaxPosition)
						.addComponent(txtMaxPostionOfGrayLevelSlicingClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(217, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Spatial Filtter", null, panel_2, null);
		
		JLabel lblAddSalt = new JLabel("Add Salt:");
		lblAddSalt.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblPercentOfSalt = new JLabel("Percent of salt:");
		
		txtPercentOfSaltOfAddSaltClass = new JTextField();
		txtPercentOfSaltOfAddSaltClass.setText("0.03");
		txtPercentOfSaltOfAddSaltClass.setColumns(10);
		
		JLabel lblMinFillter = new JLabel("Min Fillter:");
		lblMinFillter.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblSizeOfFillter = new JLabel("Size of Fillter:");
		
		txtSizeOfFilterInMinFillterClass = new JTextField();
		txtSizeOfFilterInMinFillterClass.setText("3");
		txtSizeOfFilterInMinFillterClass.setColumns(10);
		
		JLabel lblMaxFillter = new JLabel("Max Fillter:");
		lblMaxFillter.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel label = new JLabel("Size of Fillter:");
		
		txtSizeOfFilterInMaxFillterClass = new JTextField();
		txtSizeOfFilterInMaxFillterClass.setText("3");
		txtSizeOfFilterInMaxFillterClass.setColumns(10);
		
		JLabel lblMedianFillter = new JLabel("Median Fillter:");
		lblMedianFillter.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("Size of Fillter:");
		
		txtSizeOfFilterInMedianFillterClass = new JTextField();
		txtSizeOfFilterInMedianFillterClass.setText("3");
		txtSizeOfFilterInMedianFillterClass.setColumns(10);
		
		JLabel lblEverageFillter = new JLabel("Everage Fillter:");
		lblEverageFillter.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("Size of Fillter:");
		
		txtSizeOfFilterInEverageFillterClass = new JTextField();
		txtSizeOfFilterInEverageFillterClass.setText("3");
		txtSizeOfFilterInEverageFillterClass.setColumns(10);
		
		lblGaussian = new JLabel("Gaussian:");
		lblGaussian.setFont(new Font("Arial", Font.PLAIN, 14));
		
		label_5 = new JLabel("Size of Fillter:");
		
		txtSizeOfFitterInGaussian = new JTextField();
		txtSizeOfFitterInGaussian.setText("10");
		txtSizeOfFitterInGaussian.setColumns(10);
		
		lblSigma = new JLabel("Sigma:");
		
		txtSigmaInGaussian = new JTextField();
		txtSigmaInGaussian.setText("1.5");
		txtSigmaInGaussian.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAddSalt))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMinFillter, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(28)
							.addComponent(lblPercentOfSalt)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPercentOfSaltOfAddSaltClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(28)
							.addComponent(lblSizeOfFillter)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSizeOfFilterInMinFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaxFillter, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(txtSizeOfFilterInMaxFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMedianFillter, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(29)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSizeOfFilterInMedianFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblEverageFillter, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(29)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtSizeOfFilterInEverageFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(48)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtSizeOfFitterInGaussian, 0, 0, Short.MAX_VALUE))
								.addComponent(lblGaussian, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblSigma)
							.addGap(4)
							.addComponent(txtSigmaInGaussian, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
					.addGap(378))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(19)
					.addComponent(lblAddSalt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPercentOfSalt)
						.addComponent(txtPercentOfSaltOfAddSaltClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblMinFillter, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSizeOfFillter)
						.addComponent(txtSizeOfFilterInMinFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMaxFillter, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(txtSizeOfFilterInMaxFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMedianFillter, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(txtSizeOfFilterInMedianFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEverageFillter, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGaussian, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(txtSizeOfFilterInEverageFillterClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(txtSizeOfFitterInGaussian, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSigma)
						.addComponent(txtSigmaInGaussian, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
				JPanel panel = new JPanel();
				tabbedPane.addTab("Edge Detected", null, panel, null);
				
						JLabel lblSobel = new JLabel("Sobel:");
						lblSobel.setFont(new Font("Arial", Font.PLAIN, 14));
						
								txtGxInSobel00 = new JTextField();
								txtGxInSobel00.setText("-1");
								txtGxInSobel00.setColumns(10);
								
								txtGxInSobel01 = new JTextField();
								txtGxInSobel01.setText("-2");
								txtGxInSobel01.setColumns(10);
								
								txtGxInSobel02 = new JTextField();
								txtGxInSobel02.setText("-1");
								txtGxInSobel02.setColumns(10);
								
								txtGxInSobel10 = new JTextField();
								txtGxInSobel10.setText("0");
								txtGxInSobel10.setColumns(10);
								
								txtGxInSobel11 = new JTextField();
								txtGxInSobel11.setText("0");
								txtGxInSobel11.setColumns(10);
								
								txtGxInSobel12 = new JTextField();
								txtGxInSobel12.setText("0");
								txtGxInSobel12.setColumns(10);
								
								txtGxInSobel20 = new JTextField();
								txtGxInSobel20.setText("1");
								txtGxInSobel20.setColumns(10);
								
								txtGxInSobel21 = new JTextField();
								txtGxInSobel21.setText("2");
								txtGxInSobel21.setColumns(10);
								
								txtGxInSobel22 = new JTextField();
								txtGxInSobel22.setText("1");
								txtGxInSobel22.setColumns(10);
								
								JLabel lblGx = new JLabel("G[x]:");
								
								JLabel lblGy = new JLabel("G[y]:");
								
								txtGyInSobel00 = new JTextField();
								txtGyInSobel00.setText("-1");
								txtGyInSobel00.setColumns(10);
								
								txtGyInSobel01 = new JTextField();
								txtGyInSobel01.setText("0");
								txtGyInSobel01.setColumns(10);
								
								txtGyInSobel02 = new JTextField();
								txtGyInSobel02.setText("1");
								txtGyInSobel02.setColumns(10);
								
								txtGyInSobel12 = new JTextField();
								txtGyInSobel12.setText("2");
								txtGyInSobel12.setColumns(10);
								
								txtGyInSobel11 = new JTextField();
								txtGyInSobel11.setText("0");
								txtGyInSobel11.setColumns(10);
								
								txtGyInSobel10 = new JTextField();
								txtGyInSobel10.setText("-2");
								txtGyInSobel10.setColumns(10);
								
								txtGyInSobel20 = new JTextField();
								txtGyInSobel20.setText("-1");
								txtGyInSobel20.setColumns(10);
								
								txtGyInSobel21 = new JTextField();
								txtGyInSobel21.setText("0");
								txtGyInSobel21.setColumns(10);
								
								txtGyInSobel22 = new JTextField();
								txtGyInSobel22.setText("1");
								txtGyInSobel22.setColumns(10);
								
								lblCanny = new JLabel("Canny:");
								lblCanny.setFont(new Font("Arial", Font.PLAIN, 14));
								
								lblLow = new JLabel("Low:");
								
								txtLowValueFromCannyClass = new JTextField();
								txtLowValueFromCannyClass.setText("5");
								txtLowValueFromCannyClass.setColumns(10);
								
								lblHigh = new JLabel("High:");
								
								txtHighValueFromCannyClass = new JTextField();
								txtHighValueFromCannyClass.setText("10");
								txtHighValueFromCannyClass.setColumns(10);
								GroupLayout gl_panel = new GroupLayout(panel);
								gl_panel.setHorizontalGroup(
									gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addContainerGap()
													.addComponent(lblSobel))
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(16)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblCanny, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_panel.createSequentialGroup()
															.addComponent(lblGx)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup()
																	.addComponent(txtGxInSobel20, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(txtGxInSobel21, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(txtGxInSobel22, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_panel.createSequentialGroup()
																	.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(txtGxInSobel00, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
																		.addComponent(txtGxInSobel10, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_panel.createSequentialGroup()
																			.addComponent(txtGxInSobel11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(txtGxInSobel12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_panel.createSequentialGroup()
																			.addComponent(txtGxInSobel01, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(txtGxInSobel02, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))))
														.addGroup(gl_panel.createSequentialGroup()
															.addGap(10)
															.addComponent(lblLow, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(txtLowValueFromCannyClass, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
															.addGap(18)
															.addComponent(lblHigh, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(txtHighValueFromCannyClass, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))))
											.addGap(9)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblGy, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
													.addGap(10)
													.addComponent(txtGyInSobel00, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(txtGyInSobel01, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(txtGyInSobel02, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(35)
													.addComponent(txtGyInSobel10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(txtGyInSobel11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(txtGyInSobel12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(35)
													.addComponent(txtGyInSobel20, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(txtGyInSobel21, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(txtGyInSobel22, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
											.addContainerGap(220, Short.MAX_VALUE))
								);
								gl_panel.setVerticalGroup(
									gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addContainerGap()
											.addComponent(lblSobel)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addGap(3)
															.addComponent(lblGy))
														.addComponent(txtGyInSobel00, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGyInSobel01, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGyInSobel02, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addGap(11)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(txtGyInSobel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGyInSobel11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGyInSobel12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addGap(11)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(txtGyInSobel20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGyInSobel21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGyInSobel22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_panel.createSequentialGroup()
													.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtGxInSobel00, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblGx)
														.addComponent(txtGxInSobel01, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGxInSobel02, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtGxInSobel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGxInSobel11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGxInSobel12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtGxInSobel20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGxInSobel21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtGxInSobel22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
											.addGap(18)
											.addComponent(lblCanny, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblLow)
												.addComponent(txtLowValueFromCannyClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHigh)
												.addComponent(txtHighValueFromCannyClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addContainerGap(131, Short.MAX_VALUE))
								);
								panel.setLayout(gl_panel);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Morphology", null, panel_4, null);
		
		JLabel lblEroson = new JLabel("Eroson:");
		lblEroson.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblFilterSize = new JLabel("Kernel size:");
		
		txtKernelSizeInErosonClass = new JTextField();
		txtKernelSizeInErosonClass.setText("3");
		txtKernelSizeInErosonClass.setColumns(10);
		
		JLabel Dilation = new JLabel("Dilation");
		Dilation.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel label_4 = new JLabel("Kernel size:");
		
		txtKernelSizeOfDilasionClass = new JTextField();
		txtKernelSizeOfDilasionClass.setText("3");
		txtKernelSizeOfDilasionClass.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(10)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtKernelSizeOfDilasionClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(10)
							.addComponent(lblFilterSize, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtKernelSizeInErosonClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblEroson, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(Dilation, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(344, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEroson, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilterSize)
						.addComponent(txtKernelSizeInErosonClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(Dilation, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(txtKernelSizeOfDilasionClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(191, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Artistic", null, panel_1, null);

		JLabel lblOilPainting = new JLabel("Oil Painting:");
		lblOilPainting.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblRadius = new JLabel("Radius:");

		JLabel lblIndetityLevel = new JLabel("Intensity Level:");

		txtRadiusOfOilPainting = new JTextField();
		txtRadiusOfOilPainting.setText("3");
		txtRadiusOfOilPainting.setColumns(10);

		txtItensityLevelOfOilPainting = new JTextField();
		txtItensityLevelOfOilPainting.setText("256");
		txtItensityLevelOfOilPainting.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(lblOilPainting))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(28)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblRadius).addGap(43))
										.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblIndetityLevel)
												.addPreferredGap(ComponentPlacement.UNRELATED)))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(txtRadiusOfOilPainting, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtItensityLevelOfOilPainting, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(329, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap().addComponent(lblOilPainting)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblRadius).addComponent(
						txtRadiusOfOilPainting, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtItensityLevelOfOilPainting, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIndetityLevel))
				.addContainerGap(235, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}
}
