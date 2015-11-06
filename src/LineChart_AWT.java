import org.jfree.chart.ChartPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	public LineChart_AWT(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public void setDataset(int[] redHistogram, int[] greenHistogram, int[] blueHistogram) {
		for (int i = 0; i < 256; i++) {
			dataset.addValue(redHistogram[i], "red", String.valueOf(i));
			dataset.addValue(blueHistogram[i], "blue", String.valueOf(i));
			dataset.addValue(greenHistogram[i], "green", String.valueOf(i));
		}
	}

	public void showLineChart() {
		JFreeChart lineChart = ChartFactory.createLineChart("Histogram", "Level", "Number of pixels", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		setContentPane(chartPanel);
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}

}
