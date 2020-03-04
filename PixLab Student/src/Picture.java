import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	// /////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	// //////////////////// methods ///////////////////////////////////////
	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height "
				+ getHeight() + " width " + getWidth();
		return output;
	}

	public void zeroBlue() {
		// grab pixel represenation of image
		Pixel[][] pixels = this.getPixels2D();

		// generate all possible row indeces
		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel p = pixels[r][c];
				p.setBlue(0);
			}
		}
	}

	/*
	 * every function has a return type name
	 */
	public void keepOnlyRed() {
		// grab pixel represenation of image
		Pixel[][] pixels = this.getPixels2D();

		// generate all possible row indeces
		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel p = pixels[r][c];
				p.setBlue(0);
				p.setGreen(0);
			}
		}
	}

	/*
	 * Apply Sepia Filter onto your picture outputRed = (inputRed * .393) +
	 * (inputGreen *.769) + (inputBlue * .189) outputGreen = (inputRed * .349) +
	 * (inputGreen *.686) + (inputBlue * .168) outputBlue = (inputRed * .272) +
	 * (inputGreen *.534) + (inputBlue * .131)
	 */
	public void sepia() {

		Pixel[][] pixels = this.getPixels2D();

		// generate all possible row indeces
		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel p = pixels[r][c];

				int newRed = (int) (p.getRed() * .393)
						+ (int) (p.getGreen() * .769)
						+ (int) (p.getBlue() * .189);
				p.setRed(newRed);
				int newBlue = (int) (p.getRed() * .272)
						+ (int) (p.getGreen() * .534)
						+ (int) (p.getBlue() * .131);
				p.setBlue(newBlue);
				int newGreen = (int) (p.getRed() * .349)
						+ (int) (p.getGreen() * .686)
						+ (int) (p.getBlue() * .168);
				p.setGreen(newGreen);

			}
		}

	}

	/*
	 * turn a picture black and white (grayscale) read up on how to do this from
	 * here
	 * https://www.johndcook.com/blog/2009/08/24/algorithms-convert-color-grayscale
	 * /
	 */
	public void bAndW() {
		Pixel[][] pixels = this.getPixels2D();

		// generate all possible row indeces
		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel p = pixels[r][c];

				int newCol = (int) (p.getRed() * .21)
						+ (int) (p.getGreen() * .72)
						+ (int) (p.getBlue() * .07);
				p.setRed(newCol);
				p.setBlue(newCol);
				p.setGreen(newCol);

			}
		}
	}

	/*
	 * Formula red = 255-currentRed blue = 255-currentBlue green =
	 * 255-currentGreen
	 */
	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				pixels[r][c].setBlue(255 - pixels[r][c].getBlue());
				pixels[r][c].setRed(255 - pixels[r][c].getRed());
				pixels[r][c].setGreen(255 - pixels[r][c].getGreen());
			}

		}
	}

	public void encrypt(Picture background) {

		Pixel[][] bg = this.getPixels2D(); // picture you want to add a new
											// background
		Pixel[][] msg = background.getPixels2D();

		// visit every pixel in main
		for (int r = 0; r < bg.length; r++) {
			for (int c = 0; c < bg[r].length; c++) {

				// kill the last bit of ever pixel's blue channel
				bg[r][c].setBlue((bg[r][c].getBlue() >> 1) << 1);

				// whenever we see black in our "secret message"
				// we're gonna flip a bit in the blue channel of the pixel
				if (msg[r][c].getBlue() < 40) {
					// turn on the last big by adding !
					bg[r][c].setBlue(bg[r][c].getBlue() + 1);
				}

			}
		}
	}

	public void decrypt(Picture background) {
		Pixel[][] bg = this.getPixels2D();

		Pixel[][] msg = background.getPixels2D();

		// setup the loops to visit every pixel

		// visit every pixel in main
		for (int r = 0; r < bg.length; r++) {
			for (int c = 0; c < bg[r].length; c++) {

				// kill the last bit of ever pixel's blue channel
				bg[r][c].setBlue((bg[r][c].getBlue() << 3) >> 1);

				// whenever we see black in our "secret message"
				// we're gonna flip a bit in the blue channel of the pixel
				if (bg[r][c].getBlue() % 2 == 1) {
					// turn on the last big by adding !
					msg[r][c].setColor(new Color(255, 255, 255));

				} else {
					msg[r][c].setColor(new Color(0, 0, 0));
				}

			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorHorizontal() {

		Pixel[][] pixels = this.getPixels2D();
		int height = pixels.length;
		Pixel topPixel = null;
		Pixel bottomPixel = null;

		// generate all possible row indeces
		for (int r = 0; r < height / 2; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				topPixel = pixels[r][c];
				bottomPixel = pixels[height - 1 - r][c];
				topPixel.setColor(bottomPixel.getColor());
			}
		}

	}

	/*
	 * copy right half pixels to left half which would mirror the right to the
	 * left of the pic
	 */
	public void mirrorRightToLeft() {
		Pixel[][] pixels = this.getPixels2D();
		int width = pixels[0].length;
		Pixel leftPixel = null;
		Pixel rightPixel = null;

		// generate all possible row indeces
		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < width / 2; c++) {
				leftPixel = pixels[r][c];
				rightPixel = pixels[r][width - 1 - c];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {

	}

	public void filterOne() {
		Pixel[][] pixels = this.getPixels2D();

		// generate all possible row indeces
		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				int newBlue = pixels[r][c].getBlue() - 55;

				pixels[r][c].setBlue(newBlue);

			}
		}
	}

	public void filterThree(Picture yes) {
		// generate all possible row indeces
		Pixel[][] pixels = yes.getPixels2D();
		int line1 = pixels.length / 3;
		int line2 = line1 * 2;
		Pixel topPixel = null;
		Pixel bottomPixel = null;

		for (int r = 0; r < line1; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel p = pixels[r][c];

				int newCol = (int) (p.getRed() * .21)
						+ (int) (p.getGreen() * .72)
						+ (int) (p.getBlue() * .07);
				p.setRed(newCol);
				p.setBlue(newCol);
				p.setGreen(newCol);

			}
		}

		for (int r = line2; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel p = pixels[r][c];

				int newCol = (int) (p.getRed() * .21)
						+ (int) (p.getGreen() * .72)
						+ (int) (p.getBlue() * .07);
				p.setRed(newCol);
				p.setBlue(newCol);
				p.setGreen(newCol);

			}
		}

	}

	public void filterFour() {
		Pixel[][] pixels = this.getPixels2D();

		for (int r = 0; r < pixels.length; r++) {
			// generate all column indeces
			for (int c = 0; c < pixels[0].length; c++) {
				if (pixels[r][c].getBlue() != 0) {
					Pixel p = pixels[r][c];

					int newCol = (int) (p.getRed() * .21)
							+ (int) (p.getGreen() * .72)
							+ (int) (p.getBlue() * .07);
					p.setRed(newCol);
					p.setBlue(newCol);
					p.setGreen(newCol);
				}
			}
		}

	}

	public void sharpen(Picture edges) {
		Pixel[][] og = this.getPixels2D();
		Pixel[][] e = edges.getPixels2D();

		for (int r = 0; r < og.length; r++) {
			// generate all column indeces
			for (int c = 0; c < og[0].length; c++) {
				if (e[r][c].getRed() <= 50) {
					og[r][c].setRed(og[r][c].getRed() + 20);
					og[r][c].setGreen(og[r][c].getGreen() + 20);
					og[r][c].setBlue(og[r][c].getBlue() + 20);

				}
			}
		}

	}

	public void blur() {

		int newRed = 0;
		int newBlue = 0;
		int newGreen = 0;
		Pixel[][] pixels = this.getPixels2D();
		for (int r = 1; r < pixels.length - 1; r++) {
			for (int c = 1; c < pixels[0].length - 1; c++) {

				newRed = pixels[r - 1][c - 1].getRed()
						+ pixels[r][c - 1].getRed()
						+ pixels[r + 1][c - 1].getRed()
						+ pixels[r - 1][c].getRed() + pixels[r][c].getRed()
						+ pixels[r + 1][c].getRed()
						+ pixels[r - 1][c + 1].getRed()
						+ pixels[r][c + 1].getRed()
						+ pixels[r + 1][c + 1].getRed();
				pixels[r][c].setRed(newRed / 9);

				newBlue = pixels[r - 1][c - 1].getBlue()
						+ pixels[r][c - 1].getBlue()
						+ pixels[r + 1][c - 1].getBlue()
						+ pixels[r - 1][c].getBlue() + pixels[r][c].getBlue()
						+ pixels[r + 1][c].getBlue()
						+ pixels[r - 1][c + 1].getBlue()
						+ pixels[r][c + 1].getBlue()
						+ pixels[r + 1][c + 1].getBlue();
				pixels[r][c].setBlue(newBlue / 9);

				newGreen = pixels[r - 1][c - 1].getGreen()
						+ pixels[r][c - 1].getGreen()
						+ pixels[r + 1][c - 1].getGreen()
						+ pixels[r - 1][c].getGreen() + pixels[r][c].getGreen()
						+ pixels[r + 1][c].getGreen()
						+ pixels[r - 1][c + 1].getGreen()
						+ pixels[r][c + 1].getGreen()
						+ pixels[r + 1][c + 1].getGreen();
				pixels[r][c].setGreen(newGreen / 9);

			}
		}

	}

	public void blueScreen(Picture background) {
		Pixel[][] blue = this.getPixels2D();
		Pixel[][] bg = background.getPixels2D();

		for (int r = 0; r < blue.length; r++) {
			// generate all column indeces
			for (int c = 0; c < blue[0].length; c++) {

				if (blue[r][c].getRed() < 35
						&& blue[r][c].getGreen() <= 45 && blue[r][c].getBlue() > 25) {

					blue[r][c].setRed(bg[r][c].getRed());
					blue[r][c].setBlue(bg[r][c].getBlue());
					blue[r][c].setGreen(bg[r][c].getGreen());
				}
				if (r <= 168 || c <= 50 || c >= 590 || (c > 378 && r < 291) || (c < 145 && r > 368 )) {
					blue[r][c].setRed(bg[r][c].getRed());
					blue[r][c].setBlue(bg[r][c].getBlue());
					blue[r][c].setGreen(bg[r][c].getGreen());
				}

			}
		}

	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();

	}

	/** Method to create a collage of several pictures */
	public void createCollage() {

		this.write("collage.jpg");
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param parameterObject
	 *            TODO
	 */
	public void edgeDetection(int edgeDist) {

		Pixel left = null;
		Pixel right = null;
		Pixel top = null;
		Pixel bottom = null;

		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				left = pixels[row][col];
				right = pixels[row][col + 1];
				top = pixels[row][col];
				bottom = pixels[row + 1][col];
				if (left.colorDistance(right.getColor()) > edgeDist
						|| top.colorDistance(bottom.getColor()) > edgeDist) {

					left.setColor(Color.black);
				} else {
					left.setColor(Color.white);
				}
			}
		}

	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture blue = new Picture("images/blue-mark.jpg");
		Picture bg = new Picture("images/beach.jpg");

		blue.explore();
		blue.blueScreen(bg);
		blue.explore();

	}

} // this } is the end of class Picture, put all new methods before this
