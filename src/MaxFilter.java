
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author VanNinh
 */
public class MaxFilter {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;

	public MaxFilter(BufferedImage parameterImage) {
		inputImage = parameterImage;
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	public int[] peripheryValue(int x, int y, BufferedImage img) {
		int[] value = new int[2];
		if (x < 0) {
			value[0] = 0;
		}
		if (x > img.getWidth() - 1) {
			value[0] = img.getWidth() - 1;
		}
		if (x > 0 && x < img.getWidth())
			value[0] = x;

		if (y < 0) {
			value[1] = 0;
		}
		if (y > img.getHeight() - 1) {
			value[1] = img.getHeight() - 1;
		}
		if (y > 0 && y < img.getHeight())
			value[1] = y;
		return value;
	}

	public void processing(int sizeFillter) {

		int s = sizeFillter * sizeFillter;
		int[] r = new int[s];
		int[] g = new int[s];
		int[] b = new int[s];

		int row = (sizeFillter - 1) / 2;
		int col = (sizeFillter - 1) / 2;
		int m, n;

		for (int x = 0; x < inputImage.getWidth(); x++) {
			for (int y = 0; y < inputImage.getHeight(); y++) {
				int i = 0;
				for (int fx = x - row; fx < x + row + 1; fx++) {
					for (int fy = y - col; fy < y + col + 1; fy++) {
						if (fx < 0 || fy < 0 || fx >= outputImage.getWidth() || fy >= outputImage.getHeight()) {
							int[] v = new int[2];
							v = peripheryValue(fx, fy, inputImage);
							m = v[0];
							n = v[1];
						} else {
							m = fx;
							n = fy;
						}

						int pixel = inputImage.getRGB(m, n);

						r[i] = (pixel >> 16) & 0xff;
						g[i] = (pixel >> 8) & 0xff;
						b[i] = (pixel) & 0xff;

						i++;

					}
				}
				Arrays.sort(r);
				Arrays.sort(g);
				Arrays.sort(b);// sap xep

				Color cm = new Color(r[s - 1], g[s - 1], b[s - 1], 255); // chon
																					// gia
																					// tri
																					// lon
																					// nhat
				outputImage.setRGB(x, y, cm.getRGB());
			}
		}

	}

}
