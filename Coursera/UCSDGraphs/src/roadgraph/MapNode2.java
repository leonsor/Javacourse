package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode2 {
	private GeographicPoint location;
	private List<MapNode2> neighbors;

	
	public MapNode2(GeographicPoint location) {
		this.location = location;
		this.neighbors = new ArrayList<MapNode2>();
	}
	
	public MapNode2() {
		this.location = null;
		this.neighbors =  new ArrayList<MapNode2>();
	}
	
	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	
	public GeographicPoint getLocation() {
		return location;
	}
	
	public void addNeighbor(MapNode2 neighbor) {
		if(!neighbors.contains(neighbor)) {
			neighbors.add(neighbor);
		}
	}
	
	public List<MapNode2> getNeighbors() {
		return neighbors;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
