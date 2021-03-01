package riemannKS;
import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;

public class MidpointRule extends Riemann{
    /**
     * Calculates the area each slice(rectangle). The height of the rectangle is the y-coordinate of the polynomial when x equals the midpoint of the slice.
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     * @return			Returns the area of the rectangle
     */
    public double slice(Polynomial poly, double sleft, double sright) {
        double midpoint = ((sright+sleft)/2); //calculates the midpoint of the slice (midpoint of sleft and sright)
        double height=(poly.evaluate(midpoint)).getTerms()[0].getTermDouble(); //finds the height by evaluating the polynomial at the midpoint
        double base=(sright-sleft); //calculates the length of the base
        double area=(base*height);

        return area;
    }

    /**
     * Draws each slice
     * @param pframe		Plotframe where drawings appear
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        double base=(sright-sleft); //calculates the length of the base
        double midpoint = ((sright+sleft)/2); //calculates the midpoint
        double height=(poly.evaluate(midpoint)).getTerms()[0].getTermDouble(); //finds the height
        double centerY=(height/2); //finds the y-coordinate of the center of the rectangle

        height=Math.abs(height); //finds the absolute value of the height so that the slices can be plotted even when the polynomial is negative

        DrawableShape rect = DrawableShape.createRectangle(midpoint, centerY, base, height);
        rect.color=Color.yellow;
        rect.edgeColor=Color.pink;
        pframe.addDrawable(rect); //adds rectangle to the plotframe
        pframe.setVisible(true); //makes the plotframe visible
    }

}

