
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
public class Patterning {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width;
	private int height;


	public Patterning(BufferedImage parameterImage) {
		inputImage = parameterImage;
		width = inputImage.getWidth();
		height = inputImage.getHeight();
		outputImage = new BufferedImage(width * 3, height * 3, BufferedImage.TYPE_INT_ARGB); 
		// the size of output increases 3x3 = 9 times
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}

	int[][] checkIndex(int index, int[][] mark) {
		if (index == 1) {
			mark[2][2] = 0;
		}
		if (index == 2) {
			mark[0][0] = 0;
		}
		if (index == 3) {
			mark[0][2] = 0;
		}
		if (index == 4) {
			mark[2][0] = 0;
		}
		if (index == 5) {
			mark[2][1] = 0;
		}
		if (index == 6) {
			mark[1][0] = 0;
		}
		if (index == 7) {
			mark[0][1] = 0;
		}
		if (index == 8) {
			mark[1][2] = 0;
		}
		if (index == 9) {
			mark[1][1] = 0;
		}
		return mark;

	}

	int[][] getFont(int index) {
		int[][] mark = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		for (int i = 1; i <= index; i++) {
			mark = checkIndex(i, mark);
		}
		return mark;
	}

	public void putPixel(int i, int j, int colorValue) {
		Color color = new Color(colorValue, colorValue, colorValue);
		int rgb = color.getRGB();
		outputImage.setRGB(i, j, rgb);
	}

	public void processing() {
		int[][] matrixOfColor = new int[height][width];
		int[][] matrixOfIndex = new int[height][width];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = inputImage.getRGB(i, j);
			//	int alpha = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;

				// processing

				int numberOfFont = 10;
				int average = (r + g + b) / 3; // Grayscale
				int index = average / numberOfFont;
				matrixOfColor[j][i] = average;
				matrixOfIndex[j][i] = index;
			//	System.out.print(index+" ");
			}
			//System.out.println();
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				int index = matrixOfIndex[j][i];
				int[][] font = getFont(index);
				// 3x3 matrix
				for (int x = 0; x <= 2; x++) {
					for (int y = 0; y <= 2; y++) {
						if (font[y][x] == 1) {
							putPixel(i *3  + x, j * 3 + y, 0); // black
						} else {
							putPixel(i * 3 + x, j * 3 + y, 255); // white
						
						}
					}
				}
			}
			
		}
	}

}
