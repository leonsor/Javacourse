import edu.duke.URLResource;

/**
 * Coursera - Java programming: solving problems with software
 * Week 2: Part4 - Finding web links
 */

/**
 * @author Leon
 *
 */
public class Part4 {
	
	public void readPage() {
		URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		String searchString = "youtube.com";
		for(String s : url.words()) {
			String sLowerCase = s.toLowerCase();
//			System.out.println(sLowerCase);
			int indexY = sLowerCase.indexOf(searchString);
			if(indexY != -1) {
				System.out.println("Gevonden: " + s); //Test purposes
				int indexStart = s.lastIndexOf("\"", indexY);
				int indexEnd = s.indexOf("\"", indexY);
				System.out.println("index eerste \" = " + indexStart);
				System.out.println("index laatste \" = " + indexEnd);
				String antwoord = s.substring(indexStart, indexEnd +1);
				System.out.println("URL = " + antwoord);
			}
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Part4 p4 = new Part4();
		p4.readPage();
	}

}
