
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author NQThanh
 */
public class Canny {

	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private int width ;
	private int height ;
	
	public void setInputImage(BufferedImage input) {
		inputImage = input;
	}

	public BufferedImage getOutputImage() {
		return outputImage;
	}
	  public Canny(BufferedImage input) {
	        inputImage = input;
	        width = inputImage.getWidth() ; 
	        height = inputImage.getHeight() ; 
	    	outputImage = new BufferedImage(width, height , BufferedImage.TYPE_INT_RGB);
	    }


	public void processing( int low , int high) {

		int sumx, sumy;
		
		int goc[][] = new int[width][height];
		int sum[][] = new int[width][height];
		int sumt ;
		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				sumx = (inputImage.getRGB(i - 1, j - 1) & 0xFF) * -1 + (inputImage.getRGB(i + 1, j - 1) & 0xFF) * 1
						+ (inputImage.getRGB(i - 1, j) & 0xFF) * -2 + (inputImage.getRGB(i + 1, j) & 0xFF) * 2
						+ (inputImage.getRGB(i - 1, j + 1) & 0xFF) * -1 + (inputImage.getRGB(i + 1, j + 1) & 0xFF) * 1;
				sumy = (inputImage.getRGB(i - 1, j - 1) & 0xFF) * -1 + (inputImage.getRGB(i, j - 1) & 0xFF) * -2
						+ (inputImage.getRGB(i + 1, j - 1) & 0xFF) * -1 + (inputImage.getRGB(i - 1, j + 1) & 0xFF) * 1
						+ (inputImage.getRGB(i, j + 1) & 0xFF) * 2 + (inputImage.getRGB(i + 1, j + 1) & 0xFF) * 1;
				sum[i][j] = (int) (Math.sqrt(sumx * sumx + sumy * sumy));
				goc[i][j] = (int) (180 * (Math.atan2(sumx, sumy) / Math.PI));
				sumt = (int) Math.sqrt(sumx * sumx + sumy * sumy);

				if (((goc[i][j] < 22.5) && (goc[i][j] > -22.5)) || (goc[i][j] > 157.5) || (goc[i][j] < -157.5)) {
					goc[i][j] = 0;
				} else if (((goc[i][j] > 22.5) && (goc[i][j] < 67.5))
						|| ((goc[i][j] < -112.5) && (goc[i][j] > -157.5))) {
					goc[i][j] = 45;
				} else if (((goc[i][j] > 67.5) && (goc[i][j] < 112.5))
						|| ((goc[i][j] < -67.5) && (goc[i][j] > -112.5))) {
					goc[i][j] = 90;
				} else if (((goc[i][j] > 112.5) && (goc[i][j] < 157.5))
						|| ((goc[i][j] < -22.5) && (goc[i][j] > -67.5))) {
					goc[i][j] = 135;
				}

			}
		}
		int den = new Color(0, 0, 0).getRGB();
		int trang = new Color(255, 255, 255).getRGB();
		int mn[][] = new int[width][height];
		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				if ((goc[i][j] == 0 && sum[i][j] > sum[i][j + 1] && sum[i][j] > sum[i][j - 1])
						|| (goc[i][j] == 135 && sum[i][j] > sum[i + 1][j - 1] && sum[i][j] > sum[i - 1][j + 1])
						|| (goc[i][j] == 90 && sum[i][j] > sum[i + 1][j] && sum[i][j] > sum[i - 1][j])
						|| (goc[i][j] == 45 && sum[i][j] > sum[i + 1][j + 1] && sum[i][j] > sum[i - 1][j - 1])) {
					mn[i][j] = sum[i][j];
				} else {
					mn[i][j] = 0;
				}
			}
		}
		int k = 0, q = 0, size, lx = 0, ly = 0;
		int kq[][] = new int[width][height];
		Deque<Integer> s1 = new ArrayDeque<>();
		Deque<Integer> s2 = new ArrayDeque<>();
		for (int i = 1; i < width - 2; i++) {
			for (int j = 1; j < height - 2; j++) {
				if (mn[i][j] > high) {
					outputImage.setRGB(i, j, trang);
					lx = 0;
					ly = 0;
					do {
						lx = 0;
						ly = 0;
						if (!s1.isEmpty()) {
							lx = s1.pop();
							ly = s2.pop();
						}
						size = s1.size();
						for (k = -1; k < 2; k++) {
							for (q = -1; q < 2; q++) {
								if ((i + k + lx) < width - 2 && (j + q + ly) < height - 2 && (i + k + lx) >= 0
										&& (j + q + ly) >= 0) {
									if (mn[i + k + lx][j + q + ly] > low && kq[i + k + lx][j + q + ly] == 0) {
										kq[i + k + lx][j + q + ly] = 255;
										s1.push(k + lx);
										s2.push(q + ly);
									}
								}
							}
						}

						if (size - 1 <= 0) {
							break;
						}
					} while (size - 1 > 0);
				}
			}
		}
		for (int i = 2; i < width - 2; i++) {
			for (int j = 2; j < height - 2; j++) {
				if (kq[i][j] == 255) {
					outputImage.setRGB(i, j, trang);
				}
			}
		}
	}

}
