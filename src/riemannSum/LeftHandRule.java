package riemannSum;
import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.*;
import org.dalton.polyfun.Polynomial;
/**
 *
 * @author Ethan Hellman -
 * This class calculates the area of slices under a curve using the Left Hand Rule. This means that the calculation of the riemann sum under
 * a curve begins at the left-most point given and then increases by the calculated width. The area of each rectangle is calculated using the height
 * obtained from using the left side of the given rectangle/slice
 */
public class LeftHandRule extends riemann {

    /**
     * Calculates the area of slice with bounds of sleft, sright in accordance with polynomial poly
     */
    public double slice(Polynomial poly, double sleft, double sright) {
        double height = poly.evaluate(sleft).getTerms()[0].getTermDouble();//takes the height of the left side of the rectangle and uses this as the height of the rectangle
        return((sright - sleft)*height); //returns the area of the rectangle
    }

    /**
     * Plots the rectangle/slice with bounds sleft, sright in using polynomial poly on pframe
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        Color myColor = new Color(255, 255, 255, 127);//customizing color
        DrawableShape rect = DrawableShape.createRectangle((sleft + (sright-sleft)/2), (poly.evaluate(sleft).getTerms()[0].getTermDouble())/2, Math.abs(sright-sleft), Math.abs(poly.evaluate(sleft).getTerms()[0].getTermDouble()));//creates a rectangle whose center lies halfway between the left and right coordinates, and halfway between the height of the left side of the rectangle
        rect.setMarkerColor(myColor, Color.red);//sets color of the rectangle
        pframe.addDrawable(rect);//adds the shape to the frame
    }

}
