
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class LogarithmicTransformation {
	BufferedImage inputImage;
	BufferedImage outputImage;
	int width = 0;
	int height = 0;

	public LogarithmicTransformation(BufferedImage anh) {
		inputImage = anh;
		outputImage = new BufferedImage(anh.getWidth(), anh.getHeight(), BufferedImage.TYPE_INT_ARGB);
		width = anh.getWidth();
		height = anh.getHeight();

	}

	public void processing(double c) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				

				int pi = inputImage.getRGB(i, j);
				int alpha = (pi >> 24) & 0xff;
				int red = (pi >> 16) & 0xff;
				int green = (pi >> 8) & 0xff;
				int blue = (pi) & 0xff;

				double heSoChuyenDoi = (255.0 / Math.log(1 + 255)); // The range
																	// [0, 2.41]
																	// should be
																	// mapped to
																	// [0, L-1]
				red = (int) 	(c * Math.log(1 + red) 	 * heSoChuyenDoi);
				green = (int) 	(c * Math.log(1 + green) * heSoChuyenDoi);
				blue = (int) 	(c * Math.log(1 + blue)	 * heSoChuyenDoi);

				red = (red > 255) ? 255 : red;
				green = (green > 255) ? 255 : green;
				blue = (blue > 255) ? 255 : blue;

				Color cx = new Color(red, green, blue, alpha);
				int tx = cx.getRGB();
				outputImage.setRGB(i, j, tx);
			}
		}
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}
}
