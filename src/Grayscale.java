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
public class Grayscale {

    private BufferedImage inputImage;
    private BufferedImage outputImage;

    public Grayscale(BufferedImage input) {
        inputImage = input;
    }

    public void setInputImage(BufferedImage input) {
        inputImage = input;
    }

    public BufferedImage getOutputImage() {
        return outputImage;
    }

    public void processing() {
        outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int i, j;
        for (i = 0; i < inputImage.getWidth(); i++) {
            for (j = 0; j < inputImage.getHeight(); j++) {
                int pixel = inputImage.getRGB(i, j);
                int alpha = (pixel >> 24) & 0xff;
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel) & 0xff;
                int average = (r + g + b) / 3;
                Color c = new Color(average, average, average, alpha);
                int k = c.getRGB();
                outputImage.setRGB(i, j, k);
            }
        }
    }

}
