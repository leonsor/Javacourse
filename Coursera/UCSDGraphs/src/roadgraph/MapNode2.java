package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode2 {
	private GeographicPoint location;
	private List<Edge2> neighbors;
	private double actualDist; //actual distance from starting point to current vertex - needed for Dijkstra and A*
	private double predictedDist;  //predicted distance from starting point to goal vertex - needed for A*

	
	public MapNode2(GeographicPoint location) {
		this.location = location;
		this.neighbors = new ArrayList<Edge2>();
		this.actualDist = Double.MAX_VALUE;
		this.predictedDist = Double.MAX_VALUE;
	}
	
	public MapNode2() {
		this.location = null;
		this.neighbors =  new ArrayList<Edge2>();
		this.actualDist = Double.MAX_VALUE;
		this.predictedDist = Double.MAX_VALUE;
	}
	
	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	
	public GeographicPoint getLocation() {
		return location;
	}
	
	public void addNeighbor(Edge2 neighbor) {
		if(!neighbors.contains(neighbor)) {
			neighbors.add(neighbor);
		}
	}
	
	public List<Edge2> getNeighbors() {
		return neighbors;
	}
	
	public double getActualDist() {
		return actualDist;
	}

	public void setActualDist(double actualDist) {
		this.actualDist = actualDist;
	}

	public double getPredictedDist() {
		return predictedDist;
	}

	public void setPredictedDist(double predictedDist) {
		this.predictedDist = predictedDist;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
