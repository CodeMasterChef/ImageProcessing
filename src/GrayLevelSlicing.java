/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author VanNinh
 */
public class GrayLevelSlicing {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;

	public GrayLevelSlicing(BufferedImage parameterImage) {
		inputImage = parameterImage;
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public void processing(int min, int max) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int alpha = 255;
				int pi = inputImage.getRGB(i, j);
				alpha = (pi >> 24) & 0xff;
				int r = (pi >> 16) & 0xff;
				int g = (pi >> 8) & 0xff;
				int b = (pi) & 0xff;
				
				if (r >= min && r <= max)
					r = 255;
				if (g >= min && g <= max)
					g = 255;
				if (b >= min && b <= max)
					b = 255;
				
				Color cx = new Color(r, g, b, alpha);
				int tx = cx.getRGB();
				outputImage.setRGB(i, j, tx);
			}
		}
	}

}
