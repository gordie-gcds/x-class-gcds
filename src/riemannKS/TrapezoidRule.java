package riemannKS;
import java.awt.Color;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;

public class TrapezoidRule extends Riemann{
    /**
     * Calculates the area each slice(trapezoid)
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     * @return			Returns the area of the trapezoid
     */
    public double slice(Polynomial poly, double sleft, double sright) {
        double base1=(poly.evaluate(sleft)).getTerms()[0].getTermDouble(); //calculates length of one base
        double base2=(poly.evaluate(sright)).getTerms()[0].getTermDouble(); //calculates length of other base
        double base=((base1+base2)/2); //averages the lengths of the bases
        double height=(sright-sleft);

        double trapezoidArea=(height*base);

        return (trapezoidArea);
    }

    /**
     * Draws each slice
     * @param pframe		Plotframe where drawings appear
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        double base1=(poly.evaluate(sleft)).getTerms()[0].getTermDouble(); //calculates the height of the trapezoid when x=sleft
        double base2=(poly.evaluate(sright)).getTerms()[0].getTermDouble(); //calculates the height of the trapezoid when x=sright

        Trail t = new Trail();
        t.addPoint(sleft,0); //point at bottom left corner of the trapezoid
        t.addPoint(sleft, base1); //point at top left corner of the trapezoid
        t.addPoint(sright, base2); //point at top right corner of the trapezoid
        t.addPoint(sright, 0); //point at bottom right corner of the trapezoid
        t.color=Color.green; //establishes color of trail so that it is easily ideatifiable from the other polynomial
        pframe.addDrawable(t);
        pframe.setVisible(true);
    }

}

