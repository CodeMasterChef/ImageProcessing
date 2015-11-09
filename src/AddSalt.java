
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;


public class AddSalt {

	private BufferedImage input;
	private BufferedImage output;
	private int width;
	private int height;

	public AddSalt(BufferedImage input) {
		this.input = input;
		width = input.getWidth();
		height = input.getHeight();
		output = new BufferedImage( width , height , BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getOutputImage() {
		return output;
	}

	public void setInputImage(BufferedImage input) {
		this.input = input;
	}


	public void processing(double percent) {

		output = new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				Color c = new Color(input.getRGB(y, x));
				output.setRGB(y, x, c.getRGB());
			}
		}
		double amount = width * height * percent; // Ti le phai o dang thap phan:
												// 0.0xxx
		for (int i = 0; i < amount; i++) { // lay random vi tri tren anh va them
											// muoi vao.
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			Color c = new Color(output.getRGB(x, y));
			Color ca = new Color(0, 0, 0, c.getAlpha()); // tao mot diem mau den
			output.setRGB(x, y, ca.getRGB());
		}

	}
}