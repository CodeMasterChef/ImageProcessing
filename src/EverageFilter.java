

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 */
public class EverageFilter {

    private BufferedImage input;
    private BufferedImage output;
    private int width;
    private int height;

    public EverageFilter(BufferedImage input) {
        this.input = input;
        this.width = input.getWidth();
        this.height = input.getHeight();
        output=new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
    }

    public BufferedImage getOutputImage() {
        return output;
    }

    public int[] peripheryValue(int x, int y) {
        int[] value = new int[2];
        if (x < 0) {
            value[0] = 0;
        }
        if (x > input.getWidth() - 1) {
            value[0] = input.getWidth() - 1;
        }
        if (x > 0 && x < input.getWidth()) {
            value[0] = x;
        }

        if (y < 0) {
            value[1] = 0;
        }
        if (y > input.getHeight() - 1) {
            value[1] = input.getHeight() - 1;
        }
        if (y > 0 && y < input.getHeight()) {
            value[1] = y;
        }
        return value;
    }

    public void processing(int sizeOfFilter) {
        int s = sizeOfFilter * sizeOfFilter;
        int[] r = new int[s];
        int[] g = new int[s];
        int[] b = new int[s];

        int row = (sizeOfFilter - 1) / 2;
        int col = (sizeOfFilter - 1) / 2;
        int m, n;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int i = 0;
                for (int fx = x - row; fx < x + row + 1; fx++) {
                    for (int fy = y - col; fy < y + col + 1; fy++) {
                        if (fx < 0 || fy < 0 || fx >= width || fy >= height) {
                            int[] v = new int[2];
                            v = peripheryValue(fx, fy);
                            m = v[0];
                            n = v[1];
                        } else {
                            m = fx;
                            n = fy;
                        }
                        int pi = input.getRGB(m, n);
                    //    int alpha = (pi >> 24) & 0xff;
                        int ri = (pi >> 16) & 0xff;
                        int gi = (pi >> 8) & 0xff;
                        int bi = (pi) & 0xff;

                        r[i]=ri;
                        g[i]=gi;
                        b[i]=bi;
                        i++;
                    }
                }
                int tongR = 0, tongG = 0, tongB = 0;
                for (int j = 0; j < s; j++) {
                    tongR += r[j];
                    tongG += g[j];
                    tongB += b[j];
                }
                int tbR = tongR / s;
                int tbG = tongG / s;
                int tbB = tongB / s;
                Color cm = new Color(tbR, tbG, tbB, 255);//chon gia tri trung binh
                output.setRGB(x, y, cm.getRGB());
            }
        }
    }
   
}
