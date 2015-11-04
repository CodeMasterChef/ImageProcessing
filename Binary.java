/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author leduc
 */
public class Binary {

    BufferedImage anhGoc = null;
    BufferedImage anhMoi = null;
    int Width = 0;
    int Height = 0;
    int proMax = 0;

    public Binary(BufferedImage anh) {
        anhGoc = anh;
        anhMoi = new BufferedImage(anh.getWidth(), anh.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Width = anh.getWidth();
        Height = anh.getHeight();
        proMax = Width * Height;
    }

    public void processing() {
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Height; j++) {

                int pi = anhGoc.getRGB(i, j);
                int alpha = (pi >> 24) & 0xff;
                int r = (pi >> 16) & 0xff;
                int g = (pi >> 8) & 0xff;
                int b = (pi) & 0xff;

                if (r < 128) {
                    r = 0;
                } else {
                    r = 255;
                }
                if (b < 128) {
                    b = 0;
                } else {
                    b = 255;
                }
                if (g < 128) {
                    g = 0;
                } else {
                    g = 255;
                }
                Color cx = new Color(r, g, b, alpha);
                int tx = cx.getRGB();
                anhMoi.setRGB(i, j, tx);
            }
        }
    }

    public BufferedImage getOutputImage() {
        return anhMoi;
    }
}
