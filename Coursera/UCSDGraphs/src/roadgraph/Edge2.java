/**
 * ADSJ - week 3
 * Class to register edges in the package 
 * @author Leon
 * @date 18 Mar 2021
 */
package roadgraph;

import geography.GeographicPoint;

public class Edge2 { //changed attributes from GraphicPoint to MapNode
	//TODO change GeographicPoint to MapNode
	private MapNode start;
	private MapNode end;
	private String roadName;
	private String roadType;
	private double length;
	
	public Edge2(MapNode start, MapNode end, String roadName, String roadType, double length) {
		this.start = start;
		this.end = end;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
	
	/**
	 * @return the start point
	 */
	public MapNode getStart() {
		return start;
	}

	/**
	 * @return the end point
	 */
	public MapNode getEnd() {
		return end;
	}

	/**
	 * @return the roadName
	 */
	public String getRoadName() {
		return roadName;
	}

	/**
	 * @return the roadType
	 */
	public String getRoadType() {
		return roadType;
	}

	/**
	 * @return the length of the edge
	 */
	public double getLength() {
		return length;
	}
	
	public String toString() {
		//TODO change method after implementing mapNode instead of GraphicalPoint
		return ("From " + this.start.getLocation() + " to " + this.end.getLocation() + ", name: " + this.roadName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("------------Setting up new Edge3 -----------");
		MapNode start = new MapNode(new GeographicPoint(4,1));
		MapNode end = new MapNode(new GeographicPoint(7,3));
		String roadName = "front";
		String roadType = "CityRoad";
		int length = 4;
		System.out.println("--------------Creating new edge----------");
		Edge2 edge2 = new Edge2(start, end, roadName, roadType, length);
		System.out.println("--------------printing edge---------------");
		System.out.println(edge2.toString());

	}

}
