//package riemannSum;
import java.lang.Object;
import org.dalton.polyfun.*;
import org.opensourcephysics.frames.*;


/**
 *
 * @author Ethan Hellman -
 *
 * This class allows you certain functions all guided by the basic principles of riemann sums. A riemann sum is the total area under a curve from points a to b on the x axis and a given number of subintervals.
 * To calculate the area under a given curve using the basic principles of algebra, one uses the area of simple shapes such as rectangles and trapezoids.
 * To do this, you calculate the width of your subintervals by taking the total distance of the bounds (represented by b-a) and divide by your total number of subintervals,
 * thus giving the width of an individual subinterval. Then, you start from point a (if you are using the Left Hand Rule) and add your calculated width. Thus you have the coordinates
 * for the two bottom corners of your slice. Then you can plug in those x values into your polynomial to calculate the coordinates of your top corners. The result are the coordinates
 * of the vertices of your rectangle or slice. You continue this process by moving to the next rectangle one calculated width away from your last rectangle. By adding up the area of these
 * rectangles, one is able to get the approximation of the area under a curve for a given interval. You can repeat this process using different "rules," - such as the Left Hand, Right Hand, and Trapezoid Rules - to yeild better/different results.
 */
public abstract class riemann extends java.lang.Object {

    /**
     * Calls on slice within the selected child class to get the area of an individual slice and adds it to the total sum of all of the other slices added up so far...does this for the given number of subintervals or rather number of slices
     * @param poly the given polynomial for which riemann sum is being calculated
     * @param left the x coordinate of the left-most point in desired riemann sum
     * @param right the x coordinate of the right-most point in the desired riemann sum
     * @param subintervals the desired amount of slices/rectangles/trapezoids for area to be calculated
     * @return the total sum of all of the subintervals after program has called on slice for each slice
     */
    public double rs(Polynomial poly, double left, double right, int subintervals) {
        double rSum = 0; //respresents the total sum of all of the slices
        double width = (right - left)/subintervals; //finding the width of each subinterval/slice
        for(double i = 0; i < subintervals; i++) {
            rSum += slice(poly,left + (i*width),left + ((i+1)*width));//for each slice, calculate its area and add it to the total area under the curve, comprised of all of the slices
            System.out.println("Current RSum = " + rSum);

        }
        //System.out.println("Done");
        return rSum;
    }
    /**
     * Graphs the accumulation function for Polynomial poly - runs rs continually with gradually increasing bounds. x coordinate = size of bounds, y coordinate = riemann sum for that bound - generates a graph of the accumulation function
     * @param pframe desired frame to graph accumulation function on
     * @param poly the given polynomial for which accumulation function will be calculated
     * @param index will change the color of your points/graph depending on index
     * @param precision minimum distance each point has to have between one another - this is how I calculate the number of subintervals = total area/the precision
     * @param base the base point from which you begin calculating your accumulation function
     */
    public void rsAcc(PlotFrame pframe, Polynomial poly, int index, double precision, double base) {
        double left = 0;
        double right = 0;
        int subintervals = 0;
        for(double i = base-50; i < base+50; i +=.1) { //graphs 50 above and below the base - just to give a good display of the accumulation function
            if(i<base) {
                left = i; //sets the left-most bound equal to i
                right = base; //sets the right-most bound equal to base
                subintervals = (int) Math.round(((right-left)/precision)-.5); //rounds down for the number of subintervals
                pframe.append(index, i, (-1*rs(poly, left, right, subintervals))); //constantly increases i and graphs the corresponding sum so you get the accumulation function
            }
            else {
                left = base; //sets the left-most bound equal to base
                right = i; //sets the right most bound equal to i
                subintervals = (int) Math.round(((right-left)/precision)-.5); //again, rounding the number of subintervals down
                pframe.append(index, i, rs(poly, left, right, subintervals)); //does the same thing as above
            }

        }
    }


    /**
     * Calls on slicePlot for every slice to graph all slices under/within the riemann sum
     * @param pframe given frame on which slices and graph of function will appear
     * @param poly the given polynomial for which you are calculating the riemann sum
     * @param index will change your graph/color of your graph - distinguishes graphs
     * @param precision the minimum distance between two points - the x distance - unused in this method
     * @param left the x coordinate for the left-most bound of the riemann sum under poly
     * @param right the x coordinate for the right-most bound of the riemann sum under poly
     * @param subintervals the number of slices/rectangles/trapezoids under poly/within the riemann sum
     */
    public void rsPlot(PlotFrame pframe, Polynomial poly, int index, double precision, double left, double right, int subintervals) {
        double width = (right - left)/subintervals; //calculating the width of each subinterval
        for(double i = 0; i < subintervals; i++) {
            slicePlot(pframe, poly, (left+(width*i)), (left+(width*(i+1)))); //for each of the subintervals, calls on slicePlot and graphs the individual slice - all in all graphs the entire riemann sum
        }

        for(double i = (left-3);i <= (right+3);i+=precision ) { //graphs a little more than the polynomial's domain
            pframe.append(index, i, poly.evaluate(i).getTerms()[0].getTermDouble()); //evaluates the graph and graphs the polynomial
        }
    }
    /**
     * Abstract method implemented in each child class which calculates the area of a given rectangle with bounds sleft, sright under a given polynomial "poly"
     * @param poly the given polynomial with which the dimensions of the slice/rectangle will be calculated
     * @param sleft the x coordinate of the left side of the rectangle/slice - plug this into poly to get the y coordinates of rectangle
     * @param sright the x coordinate of the right side of the rectangle/slice - plug this into poly to get the y coordinates of rectangle
     * @return The area of the slice/rectangle
     */
    abstract double slice(Polynomial poly, double sleft, double sright);

    /**
     * Abstract methods implemented in each child class which plots the rectangle/slice on pframe
     * @param pframe the frame on which the slice/rectangle is plotted
     * @param poly the given polynomial which is used to find the dimensions/corners of the slice/rectangle
     * @param sleft the x coordinate for the left side of the desired rectangle - plug this into poly to get the y coordinates of rectangle
     * @param sright the x coordinate for the right side of the desired rectangle - plug this into poly to get the y coordinates of rectangle
     */
    abstract void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright);
    public double rs() {
        // TODO Auto-generated method stub
        return 0;
    }

}

