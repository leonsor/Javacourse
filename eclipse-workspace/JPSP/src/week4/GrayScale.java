/**
 * Class to test and implement week 4 videos concerning image manipulation
 * Adapted for Programming Exercise
 */
package week4;

import java.io.File;

import edu.duke.*;
/**
 * @author Leon
 * @version
 * @
 */
public class GrayScale {

	public ImageResource makeGray(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for(Pixel p : outImage.pixels()) {
			Pixel inPixel = inImage.getPixel(p.getX(), p.getY()); 
			int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
			p.setRed(average);
			p.setGreen(average);
			p.setBlue(average);
		}
		return outImage;
	}
	
/*	public void testMakeGray() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}*/
	
	public void selectAndConvert() {
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			String fileName = inImage.getFileName();
			ImageResource grayFile = makeGray(inImage);
			grayFile.setFileName("grey_" + fileName);
			System.out.println("New file name is: " + grayFile.getFileName());
			grayFile.save();
			grayFile.draw();
		}
	}
	
	public void checkDir() {
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			System.out.println(f);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrayScale grayScale = new GrayScale();
		//grayScale.checkDir();
		//grayScale.testMakeGray();
		grayScale.selectAndConvert();
	}

}
