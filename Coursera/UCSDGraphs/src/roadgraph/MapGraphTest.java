/**
 * 
 */
package roadgraph;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author Leon
 *
 */
public class MapGraphTest {
	MapGraph mg;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("--------------Setting up test environment---------------");
		mg = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", mg);
/*		mg.addVertex(new GeographicPoint(1, 1));
		mg.addVertex(new GeographicPoint(4, -1));
		mg.addVertex(new GeographicPoint(4, 0));
		mg.addVertex(new GeographicPoint(4, 1));
		mg.addVertex(new GeographicPoint(4, 2));*/
		System.out.println("--------------test environment created---------------");
	}

	/**
	 * Test method for {@link roadgraph.MapGraph#getNumVertices()}.
	 */
	@Test
	public final void testGetNumVertices() {
		System.out.println("----------------------Start testGetNumVertices() ------------------------");
		int test = mg.getNumVertices();
		assertEquals("Testing amount of vertices", 9, test);
		//fail("Did not return the right amount of vertices"); 
		System.out.println("----------------------End testGetNumVertices() ------------------------");
	}

	/**
	 * Test method for {@link roadgraph.MapGraph#getVertices()}.
	 */
	@Test
	public final void testGetVertices() {
		System.out.println("----------------------Start testGetVertices() ------------------------");
		Set<GeographicPoint> vertices = mg.getVertices();
		assertEquals("Testing the existence of point (1,1,)", true, vertices.contains(new GeographicPoint(1,1)));
		assertEquals("Testing the existence of point (5,8,)", false, vertices.contains(new GeographicPoint(5,8)));
		System.out.println("----------------------End testGetVertices() ------------------------");
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link roadgraph.MapGraph#getNumEdges()}.
	 */
	@Test
	public final void testGetNumEdges() {
		System.out.println("----------------------Start testGetNumEdges() ------------------------");
		assertEquals("Testing amount of edges at the start of this test", 22, mg.getNumEdges());
		GeographicPoint point1 = new GeographicPoint(7,3);
		GeographicPoint point2 = new GeographicPoint(4,2);
		mg.addEdge(point1, point2, "main", "townroad", 3);
		assertEquals("Testing amount of edges after adding a new edge to the MapGraph", 23, mg.getNumEdges());
		//	fail("Not yet implemented"); // TODO
		System.out.println("----------------------End testGetNumEdges() ------------------------");
	}

	/**
	 * Test method for {@link roadgraph.MapGraph#addVertex(geography.GeographicPoint)}.
	 */
	@Test
	public final void testAddVertex() {
		System.out.println("----------------------Start testAddVertex() ------------------------");
		assertEquals("Testing adding a new vertex", true, mg.addVertex(new GeographicPoint(9,2))); //should assert true
		assertEquals("Testing adding an existing vertex", false, mg.addVertex(new GeographicPoint(1,1))); //should assert false
		assertEquals("Testing adding a null vertex", false, mg.addVertex(null)); //should assert false
		//fail("Not yet implemented"); // TODO
		System.out.println("----------------------End testAddVertex() ------------------------");
	}

	/**
	 * Test method for {@link roadgraph.MapGraph#addEdge(geography.GeographicPoint, geography.GeographicPoint, java.lang.String, java.lang.String, double)}.
	 */
	@Test
	public final void testAddEdge() {
		System.out.println("----------------------Start testAddEdge() ------------------------");
		assertEquals("Testing amount of edges at the start of this test", 22, mg.getNumEdges()); //should be 22 (simpleTestMap)
		GeographicPoint point1 = new GeographicPoint(7,3);
		GeographicPoint point2 = new GeographicPoint(4,2);
		mg.addEdge(point1, point2, "main", "townroad", 3);
		assertEquals("Testing amount of edges after adding a new edge to the MapGraph", 23, mg.getNumEdges());
		GeographicPoint point3 = new GeographicPoint(8,6); //not in the vertices Set
		mg.addEdge(point1, point3, "main", "townroad", 6); //should throw an exception
		assertEquals("Testing amount of edges after adding a new edge to the MapGraph", 23, mg.getNumEdges()); //should still be only 23 edges
		GeographicPoint point4 = null; //new point as null
		mg.addEdge(point1, point4, "highroad", "countryroad", 4); // should throw an exception for non existing point in new edge
		assertEquals("Testing amount of edges after adding a new edge to the MapGraph",23, mg.getNumEdges()); //should still be only 23 edges
/*		GeographicPoint point5 = new GeographicPoint(4,2);
		mg.addEdge(point2, point5, "state", "townroad", 1);
		assertEquals("Testing amount of edges after adding a new edge to the MapGraph", 2, mg.getNumEdges()); //should be 2 edges*/
		System.out.println("----------------------End testAddEdge() ------------------------");
		
		//fail("Not yet implemented"); // TODO
	}

}
