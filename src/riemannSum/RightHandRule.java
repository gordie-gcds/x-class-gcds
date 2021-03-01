package riemannSum;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.dalton.polyfun.*;
import org.opensourcephysics.frames.*;

/**
 *
 * @author Ethan Hellman -
 * Finds and graphs the area of a rectangle under polynomial poly using the right hand rule which takes the right side of the rectangle/slice to calculate the area of the rectangle
 *
 */
public class RightHandRule extends riemann {



    @Override
    /**
     * Takes the height of the right side of the rectangle and uses it to calculate the area of the rectangle
     */
    public double slice(Polynomial poly, double sleft, double sright) {
        // TODO Auto-generated method stub
        double height = poly.evaluate(sright).getTerms()[0].getTermDouble();//finding the height of the rigth side of the rectangle
        return((sright - sleft)*height); //returns the area of the rectangle using the height of the right side
    }

    @Override
    /**
     * Graphs the rectangle with widthe (sright - sleft) using the right hand rule
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        Color myColor = new Color(255, 255, 255, 127);//customizing color
        DrawableShape rect = DrawableShape.createRectangle((sleft+(sright-sleft)/2), (poly.evaluate(sright).getTerms()[0].getTermDouble()/2), Math.abs(sright-sleft), Math.abs(poly.evaluate(sright).getTerms()[0].getTermDouble()));//creates a rectangle whose center lies halfway between the left and right sides and halfway between 0 and the height of the right side of the rectangle
        rect.setMarkerColor(myColor, Color.blue);//sets the color of the rectangle
        pframe.addDrawable(rect);//adds the rectangle to the display/frame
    }

}
