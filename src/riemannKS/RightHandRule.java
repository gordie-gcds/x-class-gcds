package riemannKS;
import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;

public class RightHandRule extends Riemann{

    /**
     * Calculates the area of each rectangle(slice)
     * @param poly 		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     * @return			returns the area of the slice
     */
    public double slice(Polynomial poly, double sleft, double sright) {
        double height=(poly.evaluate(sright)).getTerms()[0].getTermDouble(); //finds the height of rectangle based on y-coordinate when x=sright(because right hand rule)
        double base=(sright-sleft); //calculates the length of the base
        double sliceArea =(height*base);
        return sliceArea;
    }

    /**
     * Draws each slice
     * @param pframe		Plotframe where drawings appear
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {

        double base=(sright-sleft);
        double height=(poly.evaluate(sright)).getTerms()[0].getTermDouble(); //finds height of rectangle based on y-coordinate when x=sright
        double centerX=((sright+sleft)/2); //the x-coordinate of the center of the rectangle
        double centerY=(height/2); //the y-coordinate of the center of the rectangle

        height = Math.abs(height); //finds the absolute value of the height so that the slices can be plotted even when the polynomial is negative

        DrawableShape rect = DrawableShape.createRectangle(centerX, centerY, base, height);
        rect.color = Color.blue;
        rect.edgeColor = Color.pink;
        pframe.addDrawable(rect);
        pframe.setVisible(true);
    }

}
