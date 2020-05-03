package week3;
/**
 * Week 3
 * Belongs to video on Export countries
 */
import org.apache.commons.csv.*;
import edu.duke.*;


/**
 * @author Leon
 *
 */
public class WhichCountriesExport {

	public void listExporters(CSVParser parser, String exportOfInterest) {
		for(CSVRecord record : parser) {
			String export = record.get("Exports");
			if(export.contains(exportOfInterest)) {
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}
	
	public void whoExportsCoffee() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(true);
		listExporters(parser, "coffee");		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test listExporters
		WhichCountriesExport test1 = new WhichCountriesExport();
		test1.whoExportsCoffee();
	}

}
