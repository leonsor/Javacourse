package week1;
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        int x = 1;//for printing
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            
        	double currDist = prevPt.distance(currPt);
            System.out.println("afstand "+ x + "= " + currDist);//to check individual distance
            x += 1;
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
    	//Start with totalNum = 0
    	int totalNum = 0;
    	//for each point currPt in the Shape
    	for (@SuppressWarnings("unused") Point currPt : s.getPoints()) {
    		//add 1 to totalNum
    		totalNum += 1;
    	}
    	//return totalNum
        return totalNum;
    }

    public double getAverageLength(Shape s) {
        // Put code here
    	//get total number of Points
    	int totNumberPoints = getNumPoints(s);
    	//get perimeter
    	double perimeter = getPerimeter(s);
    	//return perimeter / total number of ponits
        return perimeter / totNumberPoints;
    }

    public double getLargestSide(Shape s) {
    	// Start with largestSide = 0
    	double largestSide = 0.0;
    	// Start with prevPoint = lastPoint
    	Point prevPoint = s.getLastPoint();
    	//for each point currPoint in the Shape
    	for (Point currPoint : s.getPoints()) {
    		// find distance currDistance from prevPoint to currPoint
    		double currDistance = prevPoint.distance(currPoint);
    		// if largestSide <= currDistance
    		if (largestSide <= currDistance) {
    			//replace largestSide with currDistance
    			largestSide = currDistance;
    		}
    		//replace prevPoint with  currPoint
    		prevPoint = currPoint;
    	}
    	//return largestSide
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
    	// Start with largestX = 0
    	double largestX = 0.0;
    	// for each point currPoint in the Shape
    	for (Point currPoint : s.getPoints()) {
    		//find currX Xvalue in currPoint
    		int currX = currPoint.getX();
    		// compare currX with largestX.if currX >= largestX
    		if (currX >= largestX) {
    			// replace largestX with currX
    			largestX = currX;
    		}
    	}
    	// return largestX
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
    	//create DirectoryResource
    	DirectoryResource dr = new DirectoryResource();
    	//create variable to hold the max lenght and initiate it at o.o
    	double currLength = 0.0;
    	//for each file  f in DirectoryResource
    	for (File f : dr.selectedFiles()) {
    		System.out.println(f); /* Test purposes only */
    		// create new FileResource(f) fr
    		FileResource fr = new FileResource(f);
    		// create new Shape(fr) s
    		Shape s = new Shape(fr);
    		//calculate the parimeter and store in var newLenght
    		double newLenght = getPerimeter(s);
    		// check whether newLength >= currlength
    			if(newLenght >= currLength) {
    				// replace currLenght with newLenght
    				currLength = newLenght;
    			}
    	}
    	// print currLength (= largest)
    	System.out.println("The longest perimeter= "+ currLength);
        return currLength;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        //create DirectoryResource
    	DirectoryResource dr = new DirectoryResource();
    	//create variable to hold the max lenght and initiate it at o.o
    	double currLength = 0.0;
    	//for each file  f in DirectoryResource
    	for (File f : dr.selectedFiles()) {
    		System.out.println(f); /* Test purposes only */
    		// create new FileResource(f) fr
    		FileResource fr = new FileResource(f);
    		// create new Shape(fr) s
    		Shape s = new Shape(fr);
    		//calculate the parimeter and store in var newLenght
    		double newLenght = getPerimeter(s);
    		// check whether newLength >= currlength
    			if(newLenght >= currLength) {
    				// replace currLenght with newLenght
    				currLength = newLenght;
    				// replace temp with  current file name
    				temp = f;
    			}
    	}
    	// print currLength (= largest)
    	System.out.println("The longest perimeter= "+ currLength);
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest4.txt");
        Shape s = new Shape(fr);
        int numberPoints = getNumPoints(s);
        System.out.println("Number of Points: " + numberPoints);;
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Average Lenght of sides = " + getAverageLength(s));
        System.out.println("Longest side in Shape s = " + getLargestSide(s));
        System.out.println("Biggest X-coordinate in Shape = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    	double perimeter = getLargestPerimeterMultipleFiles();
    	System.out.println("The longest perimeter= "+ perimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = this.getFileWithLargestPerimeter();
        System.out.println(fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
