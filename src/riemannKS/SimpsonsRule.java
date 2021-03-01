package riemannKS;
import java.awt.Color;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;

public class SimpsonsRule extends Riemann {
    /**
     * Calculates the area each slice
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     * @return			Returns the area of the slice
     */
    public double slice(Polynomial poly, double sleft, double sright) {

        Polynomial p1 = new Polynomial (new double[] {0, 1}); //declares a new polynomial (y=x) (called p1 because it is a polynomial with degree 1)
        Polynomial poly2=poly.times(p1); //multiplies poly and y=x together (essentially multiplying poly by x)
        double degree = poly2.getDegree(); //determines the degree of the new polynomial (poly times y=x)
        double divide = (1/degree); //divides the degree of the new polynomial by 1
        Polynomial polyNew=poly2.times(divide); //multiplies new polynomial by (1/degree) (essentially dividing polynomial by the degree)


        double evalRight = (polyNew.evaluate(sright)).getTerms()[0].getTermDouble(); //evaluates the polynomial at sright
        double evalLeft = (polyNew.evaluate(sleft)).getTerms()[0].getTermDouble(); //evaluates the polynomial at sleft
        double area = (evalRight-evalLeft); //subtracts the y value at sleft from the y value at sright to find the area

        return area;
    }

    /**
     * Plots the slice (the polynomial from sleft from sright)
     * @param pframe 	Plotframe where drawings appear
     * @param poly		Polynomial that user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        double midpoint = ((sright+sleft)/2); //finds the midpoint of sright and sleft
        double point1 = (poly.evaluate(sleft)).getTerms()[0].getTermDouble(); //evaluates the function at sleft
        double point2 = (poly.evaluate(sright)).getTerms()[0].getTermDouble(); //evaluates the function at sright
        double point3 = (poly.evaluate(midpoint)).getTerms()[0].getTermDouble(); //evaluates the function at the midpoint

        //I am trying to solve for the new quadratic ax^2+bx+c
        //creates variables to plug in so it is easier to solve for a, b, c
        double h = (point1-point2);
        double g = (sright*sright)-(sleft*sleft);
        double q = (sleft-sright);
        double w = (point3-point2);
        double m = ((sright*sright)-(midpoint*midpoint));
        double z = (midpoint-sright);

        //solve for a, b, and c algebraically using the variables I just created
        double a = (((q*z)/((m*q)-(z*g)))*((h/q)-(w/z)));
        double b = ((h+(a*g))/q);
        double c = (point1-(a*(sleft*sleft))-(b*sleft));

        //declares new polynomial based on a, b, and c that I just found
        Polynomial poly2= new Polynomial (new double[] {c,b,a});

        pframe.setVisible(true); //makes the plotframe visible
        Trail t = new Trail(); //declares the trail that will graph the quadratic
        double y=0;
        t.addPoint(sleft,0);  //adds a point to the trail on x-axis as sleft
        for (double i = sleft; i <= sright ; i+=.001) {  //graphs polynomial
            y=(poly2.evaluate(i)).getTerms()[0].getTermDouble(); //evaluates the polynomial at i (finds y-coordinate of the polynomial when x=i)
            t.addPoint(i,y); //adds the new point to the plot frame
        }
        t.addPoint(sright, 0); //add a point to the trail on x-axis at sright
        t.color=Color.blue; //sets the color of the new polynomial to blue so that it can be distinguished from the original polynomial
        pframe.addDrawable(t); //add the trail(graph of polynomial) to the plotframe
    }

}
