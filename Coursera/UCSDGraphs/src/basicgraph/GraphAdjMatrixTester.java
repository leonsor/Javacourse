package basicgraph;

import java.util.ArrayList;
import java.util.List;

import util.GraphLoader;

/*
 * ADSJ Week 2 
 * Tester file for the method getDistance2 in GraphAdjMatrix
 * 
 * @author Leon
 */

public class GraphAdjMatrixTester {

	public static void main(String[] args) {
		System.out.println("Loading graph based on real data...");		
		System.out.println("****");
		GraphAdjMatrix graphFromFile = new GraphAdjMatrix();
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphFromFile); //original
		GraphLoader.loadRoadMap("data/maps/ucsd.map", graphFromFile); //different file
		System.out.println(graphFromFile);
		List<Integer> neighborV = new ArrayList<Integer>();
		neighborV = graphFromFile.getDistance2(10); 
		System.out.println("amount of vertices 2 steps away is: " + neighborV.size());
		System.out.println("vertices zijn: "  + neighborV.toString());
	}

}
