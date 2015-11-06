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

	private JPanel contentPane;
	public static JTextField txtGx11;
	private JTextField txtRadiusOfOilPainting;
	private JTextField txtItensityLevelOfOilPainting;

	public int getGxMatrixInSobel() {
		return Integer.parseInt(txtGx11.getText());
	}

	public int geRadiusOfOilPainting() {
		return Integer.parseInt(txtRadiusOfOilPainting.getText());
	}

	public int geItensityLevelOfOilPainting() {
		return Integer.parseInt(txtItensityLevelOfOilPainting.getText());
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

		JPanel panel = new JPanel();
		tabbedPane.addTab("Spatial Filtering", null, panel, null);

		JLabel lblSobel = new JLabel("Sobel:");

		txtGx11 = new JTextField();
		txtGx11.setText("-1");
		txtGx11.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblSobel).addContainerGap(488,
						Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addGap(38).addComponent(txtGx11, 0, 0, Short.MAX_VALUE)
						.addGap(460)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblSobel)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtGx11, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(249, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Artistic", null, panel_1, null);

		JLabel lblOilPainting = new JLabel("Oil Painting:");
		lblOilPainting.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblRadius = new JLabel("Radius:");

		JLabel lblIndetityLevel = new JLabel("Intensity Level:");

		txtRadiusOfOilPainting = new JTextField();
		txtRadiusOfOilPainting.setText("2");
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
