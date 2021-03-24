/**
 * ADSJ - week 3
 * Class to register edges in the package 
 * @author Leon
 * @date 18 Mar 2021
 */
package roadgraph;

import geography.GeographicPoint;

public class Edge {
	//TODO change GeographicPoint to MapNode
	private GeographicPoint start;
	private GeographicPoint end;
	private String roadName;
	private String roadType;
	private double length;
	
	public Edge(GeographicPoint start, GeographicPoint end, String roadName, String roadType, double length) {
		this.start = start;
		this.end = end;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
	
	/**
	 * @return the start point
	 */
	public GeographicPoint getStart() {
		return start;
	}

	/**
	 * @return the end point
	 */
	public GeographicPoint getEnd() {
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
		return ("From " + this.start.toString() + " to " + this.end.toString() + ", name: " + this.roadName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("------------Setting up new Edge3 -----------");
		GeographicPoint start = new GeographicPoint(4,1);
		GeographicPoint end = new GeographicPoint(7,3);
		String roadName = "front";
		String roadType = "CityRoad";
		int length = 4;
		System.out.println("--------------Creating new edge----------");
		Edge edge = new Edge(start, end, roadName, roadType, length);
		System.out.println("--------------printing edge---------------");
		System.out.println(edge.toString());

	}

}
