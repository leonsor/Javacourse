package module6;

import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(11);
		pg.ellipse(x, y, 5, 5);
		
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		
		HashMap<String, Object> city = this.getProperties(); //creates a HashMap with values for this Airportmarker [country, altitude, code, City, name]
		//defines the variables
		String country = (String) city.get("country");
		String cityName = (String) city.get("city");
		String code = (String) city.get("code");
		if(code.equals ("\\N") ) {
			code = "no code";
		}
		String name = (String) city.get("name");
		 // show rectangle with output
		String output = "airport name: " + name + ", " + cityName + ", " + "Country: " + country + ", " + code + ".";
		System.out.println(output);
		pg.pushStyle();
		pg.rectMode(PConstants.CORNER);
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y + 15, pg.textWidth(output) +6, 18, 5);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(output, x + 3 , y +18);
		pg.popStyle();
		
		// show routes
		
		
	}
	
}
