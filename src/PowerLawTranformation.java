
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PowerLawTranformation {
	BufferedImage inputImage;
	BufferedImage outputImage;
	int width = 0;
	int height = 0;

	public PowerLawTranformation(BufferedImage anh) {
		inputImage = anh;
		outputImage = new BufferedImage(anh.getWidth(), anh.getHeight(), BufferedImage.TYPE_INT_ARGB);
		width = anh.getWidth();
		height = anh.getHeight();

	}

	public void processing(double r, double c) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

			
				int pi = inputImage.getRGB(i, j);
				int alpha = (pi >> 24) & 0xff;
				int red = (pi >> 16) & 0xff;
				int green = (pi >> 8) & 0xff;
				int blue = (pi) & 0xff;
				double hesochuyendoi = (255/Math.pow(255, r))  ; 
				red = 	(int) 	(Math.pow(red, r)  * c * hesochuyendoi );
				green = (int) 	(Math.pow(green,r) * c * hesochuyendoi );
				blue = 	(int) 	(Math.pow(blue, r) * c * hesochuyendoi );

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
