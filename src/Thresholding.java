/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Thresholding {

	BufferedImage inputImage = null;
	BufferedImage outputImage = null;
	int width = 0;
	int height = 0;

	public Thresholding(BufferedImage parameterImage) {
		inputImage = parameterImage;
		outputImage = new BufferedImage(parameterImage.getWidth(), parameterImage.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		width = parameterImage.getWidth();
		height = parameterImage.getHeight();

	}

	public void processing(int threshold) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				int pi = inputImage.getRGB(i, j);
				int alpha = (pi >> 24) & 0xff;
				int r = (pi >> 16) & 0xff;

				if (r < threshold) {
					r = 0;
				} else {
					r = 255;
				}
				Color cx = new Color(r, r, r, alpha);
				int tx = cx.getRGB();
				outputImage.setRGB(i, j, tx);
			}
		}
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}
}
