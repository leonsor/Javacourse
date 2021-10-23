/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private Set<GeographicPoint> vertices;
	private Set<Edge> edges;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		this.vertices = new HashSet<GeographicPoint>();
		this.edges = new HashSet<Edge>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return vertices.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		Set<GeographicPoint> returnVertices = vertices;
		return returnVertices;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return edges.size();
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		if(location != null && !vertices.contains(location)) {
			System.out.println("Element is added to the Set"); //TODO delete after testing
			vertices.add(location);
			return true;
		}
		System.out.println("Element is not added to the Set"); //TODO delete after testing
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		if(from != null && to != null) {
			if(vertices.contains(from) && vertices.contains(to) && length >= 0) {
				Edge newEdge = new Edge(from, to, roadName, roadType, length);
				if(!edges.contains(newEdge)) {
					edges.add(newEdge);
					System.out.println("Edge created and added to the Set");
				}
			}
			else {
				if(length < 0) {
					IllegalArgumentException e =  new IllegalArgumentException("New edge has invalid length");
					System.out.println(e);
				}
				else {
					IllegalArgumentException e =  new IllegalArgumentException("Vertices are not in graph");
					System.out.println(e);
				}
			}
		}
		else {
			IllegalArgumentException e =  new IllegalArgumentException("One or both vertices are null");
			System.out.println(e);
		}
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		HashMap<GeographicPoint, ArrayList<GeographicPoint>> parents = new HashMap<GeographicPoint, ArrayList<GeographicPoint>>(); 
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		Queue<GeographicPoint> queue = new LinkedList<GeographicPoint>();
		GeographicPoint curr = start;
		queue.add(curr); //
		visited.add(curr);
		while(!queue.isEmpty()) {
			curr = queue.remove();
			System.out.println("Current node taken from stack is: " + curr.toString());
			if(curr.equals(goal)) {
				//Must re-create route from finish to start
				ArrayList<GeographicPoint> route = new ArrayList<GeographicPoint>();
				route.add(curr);
				while(curr != start) {
					ArrayList<GeographicPoint> r = parents.get(curr);
					route.add(r.get(0));
					curr = r.get(0);
				}
				Collections.reverse(route);
				return route;
			}
			ArrayList<GeographicPoint> neighbors = getNeighbors(curr); //get all existing neighbors from curr using the edges
			System.out.println("Neighbors of " + curr.toString() + " are: " + neighbors.toString()); 
			//for each of curr's neighbors, n, 
			for(GeographicPoint n : neighbors) {
				if(!visited.contains(n)) { // not in visited set:
					System.out.println(n.toString() + " added to visited");
					visited.add(n);//add n to visited set
					nodeSearched.accept(n);
					ArrayList<GeographicPoint> tempor = parents.get(n); //get current parents from n
					if(tempor == null) {
						tempor = new ArrayList<GeographicPoint>();
						tempor.add(curr);
						parents.put(n, tempor);
						System.out.println("New parents Array created, content is : " + tempor.toString());
					}
					else {
						tempor.add(curr);
						parents.replace(n, tempor);//add curr as n's parent in parent map
						System.out.println("Current parents Array updated, content is : " + tempor.toString());
					}
					queue.add(n);//enqueue n onto the queue
					System.out.println(n.toString() + " added to the queue");
				}
			}
		}
		// if we get here there is no path
		System.out.println("no path found! ");		
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null;
	}
	
	public ArrayList<GeographicPoint> getNeighbors(GeographicPoint curr) {
		ArrayList<GeographicPoint> neighbors = new ArrayList<GeographicPoint>();
		for(Edge e:edges) {
			if(e.getStart().equals(curr)) {
				neighbors.add(e.getEnd());
			}
		}
		return neighbors;
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		//Initialize open and closed lists
		//Make the start vertex current
		//Calculate heuristic distance of start vertex to destination (h)
		//Calculate f value for start vertex (f = g + h, where g = 0)
		//WHILE current vertex is not the destination
		//	FOR each vertex adjacent to current
		//		IF vertex not in closed list and not in open list THEN
		//			Add vertex to open list
		//			Calculate distance from start (g)
		//			calculate heuristic distance to destination (h)
		//			Calculate f value (f = g + h)
		//			IF new f value < existing f value or there is no existing f value THEN
		//				Update f value
		//				Set parent to be the current vertex
		//			END IF
		//		END IF
		//	NEXT adjacent vertex
		//	add current vertex to closed list
		//	Remove vertex with lowest f value from open list and make it current
		//END WHILE
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		ArrayList<GeographicPoint> neighbors = firstMap.getNeighbors(new GeographicPoint(4,1)); //testing own method getNeighbors-passed
		System.out.println(neighbors.toString()); // must result into [4,2] [4,0] [1,1] [5,1] [7,3]
		
		List<GeographicPoint> route = firstMap.bfs(new GeographicPoint(1,1), new GeographicPoint(8,-1));
		System.out.println("Route is : " + route.toString());
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
