/**
 * Assignment to convert images to a negative version and save them
 */
package week4;

import java.io.File;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class Invert {
	
	public ImageResource invertImage(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for(Pixel p : outImage.pixels()) {
			Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
			int inRed = inPixel.getRed();
			int inGreen = inPixel.getGreen();
			int inBlue = inPixel.getBlue();
			p.setRed(255-inRed);
			p.setGreen(255-inGreen);
			p.setBlue(255-inBlue);			
		}
		//outImage.draw();
		return outImage;
	}
	
	public void selectAndConvert() {
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource invertImage = invertImage(inImage);
			inImage.draw();
			invertImage.draw();
			String oldName = inImage.getFileName();
			System.out.println(oldName);
			oldName = f.getAbsoluteFile().getName();
			System.out.println(oldName);
			//invertImage.setFileName("inverted-" + oldName);
			//System.out.println(invertImage.getFileName());
			//invertImage.save();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Invert invert = new Invert();
		invert.selectAndConvert();

	}

}
