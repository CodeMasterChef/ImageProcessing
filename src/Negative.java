/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Negative {
	BufferedImage inputImage;
	BufferedImage outputImage;
	int width = 0;
	int height = 0;

	public Negative(BufferedImage parameterImage) {
		inputImage = parameterImage;
		outputImage = new BufferedImage(parameterImage.getWidth(), parameterImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		width = parameterImage.getWidth();
		height = parameterImage.getHeight();

	}

	public void processing() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int alpha = 255;
				int pi = inputImage.getRGB(i, j);
				alpha = (pi >> 24) & 0xff;
				int r = (pi >> 16) & 0xff;
				int g = (pi >> 8) & 0xff;
				int b = (pi) & 0xff;
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				Color cx = new Color(r, g, b, alpha);
				int tx = cx.getRGB();
				outputImage.setRGB(i, j, tx);
			}
		}
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}
}
