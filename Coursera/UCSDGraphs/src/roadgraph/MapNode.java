package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
	private GeographicPoint location;
	private List<MapNode> neighbors;

	
	public MapNode(GeographicPoint location) {
		this.location = location;
		this.neighbors = new ArrayList<MapNode>();
	}
	
	public MapNode() {
		this.location = null;
		this.neighbors =  new ArrayList<MapNode>();
	}
	
	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	
	public GeographicPoint getLocation() {
		return location;
	}
	
	public void addNeighbor(MapNode neighbor) {
		if(!neighbors.contains(neighbor)) {
			neighbors.add(neighbor);
		}
	}
	
	public List<MapNode> getNeighbors() {
		return neighbors;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
