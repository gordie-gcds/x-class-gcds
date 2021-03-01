package riemannKS;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import org.dalton.polyfun.Polynomial;

/**
 * Riemann Sums:
 * Create an abstract class with three methods and two abstract methods
 * Methods:
 * rs (calculates the riemann sum by adding up all the slices)
 * rsPlot (plots the polynomial and all the rectangles(or other shapes)
 * rsAcc (graphs the accumulation function)
 * Abstract Methods:
 *slice (calculates the area of one slice)
 *slicePlot (plots just one slice)s
 * Rules Used:
 * Left hand rule
 * Right hand rule
 * Trapezoid rule
 * Random rule
 * Midpoint rule
 * Maximum rule
 * Minimum rule
 * Simpson's rule
 * Answer question: Which Riemann Sum rule is the most effective for approximating area?
 * MostEffectiveRule
 *
 *
 */

public abstract class Riemann {
    /**
     * This method calculates the Riemann sum by using the slice method from one of the rules. It adds all the slices together to find the final area under the curve.
     * @param poly 				Polynomial that the user entered
     * @param left				This is the x-coordinate of where we want the area to start
     * @param right				This is the x-coordinate of the we want the area to end
     * @param subintervals		This is the number of rectangles/trapezoids that we separate the area under the curve into
     * @return					Returns the area under the curve calculated by the Riemann sum
     */
    public double rs(Polynomial poly, double left, double right, int subintervals) { //calculates a Riemann sum

        double area=0; //declaring the variable area that is going to be the total area under the curve
        double base=(right-left)/(subintervals); //length of the base of each rectangle(or other shape)

        for (double i = 0; i < subintervals; i++) { //for loop that runs slice function for each rule and then add them all together to get the total area
            area+=slice(poly, left, left+base); //adding the return value of each slice together
            left+=base; //adds the value of base to left so that next time I run the slice function it starts at the next rectangle
        }

        return area; //returns the total area under the curve
    }

    /**
     * This method plots the polynomial with the rectangles(and other shapes) whose areas are being added together to find the total area under the curve
     * @param pframe		The frame where the graph will be displayed
     * @param poly		The polynomial that the user inputs
     * @param index
     * @param precision	Tells the computer how many points to plot in the graph of the polynomial
     * @param left		The x-coordinate of where the area starts
     * @param right		The x-coordinate of where the area ends
     * @param subintervals	Number of sub rectangles(or other shapes) that are drawn in graph
     */
    public void rsPlot(PlotFrame pframe, Polynomial poly, int index, double precision, double left, double right, int subintervals) {
        //graphs the polynomial whose Riemann sum is being calculated and also draws the rectangles (or other shapes) whose areas are summed in the calculation of the Riemann sum.
        pframe.setVisible(true); //tells the computer to display the plotFrame
        Trail t = new Trail();
        double y=0; //y is the y-coordinate of the point that I am going to plot in the for loop
        double base=(right-left)/subintervals; //length of the base of each subinterval

        for (double i = -100; i < 100 ; i+=precision) {  //graphs polynomial
            y=(poly.evaluate(i)).getTerms()[0].getTermDouble(); //evaluates the polynomial at i (finds y-coordinate of the polynomial when x=i)
            t.addPoint(i,y); //adds the new point to the plot frame
        }
        for (double i=0; i < subintervals+1; i++) { //displays rectangles (or other shapes) whose areas are being summed
            slicePlot(pframe, poly, left, left+base); //runs slicePlot function
            left+=base;
        }
        pframe.addDrawable(t); //add the trail(graph of polynomial) to the plotframe
    }

    /**
     * This methods plots the accumulation function
     * @param pframe		The frame the graph will be displayed on
     * @param poly		The polynomial the user inputs
     * @param index
     * @param precision	Tells the computer how many points to plot in the function
     * @param base		The base point of the accumulation function (where it starts)
     */
    public void rsAcc(PlotFrame pframe, Polynomial poly, int index, double precision, double base) {
        //graphs the accumulation function
        pframe.setVisible(true); //displays the plotframe
        Trail t = new Trail();
        double y=0;
        for (double i=-100; i<100; i+=precision) { //plots each point in the accumulation function
            y=rs(poly, base, i, 1000); //the y-coordinate of each point is the area under the curve from base to i
            t.addPoint(i,y);
        }
        pframe.addDrawable(t);
    }

    /**
     * This method finds the area of each slice
     * @param poly		The polynomial the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     * @return			Returns the area of one slice
     */
    public abstract double slice(Polynomial poly, double sleft, double sright); //abstract method
    //uses a particular Riemann sum rule to calculate an approximation of the area under the graph of the given polynomial and over one interval on the x-axis.

    /**
     * This method graphs each slice
     * @param pframe		The plotframe that the slice will be displayed on
     * @param poly		The polynomial the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     */
    public abstract void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright); //abstract method
    //draws the region whose area is calculated by slice

}
