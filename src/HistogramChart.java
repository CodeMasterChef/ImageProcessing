
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

public class HistogramChart  {
	BufferedImage inputImage;
	BufferedImage outputImage;
	int width = 0;
	int height = 0;

	public HistogramChart(BufferedImage parameterImage) {
		inputImage = parameterImage;
		outputImage = new BufferedImage(parameterImage.getWidth(), parameterImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		width = parameterImage.getWidth();
		height = parameterImage.getHeight();

	}

	public void processing() {
		int[][] histogram = new int[3][256]; // int[0][i] = red ; int[1][i] =
												// greem ; int[2][i] = blue

		for (int channel = 0; channel < 3; channel++) {
			for (int i = 0; i < 256; i++) {
				histogram[channel][i] = 0;
			}
		}

		// Step 1: calculate histogram
	
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				int pi = inputImage.getRGB(i, j);
			//	int alpha = (pi >> 24) & 0xff;
				int red = (pi >> 16) & 0xff;
				int green = (pi >> 8) & 0xff;
				int blue = (pi) & 0xff;

				histogram[0][red]++;
				histogram[1][green]++;
				histogram[2][blue]++;

			}
		}
		// show chart 
		LineChart_AWT lineChart_AWT = new LineChart_AWT("Histogram") ; 
		lineChart_AWT.setDataset(histogram[0], histogram[1], histogram[2]);
		lineChart_AWT.showLineChart(); 
		
	}

}
