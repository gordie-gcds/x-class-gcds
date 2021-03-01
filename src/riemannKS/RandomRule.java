package riemannKS;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;

import java.awt.Color;
import java.util.Random;

public class RandomRule extends Riemann{

    /**
     * Calculates the area each slice(rectangle). Randomly generates a x-coordinate between and sleft and sright and evaluates the function at that x to find the height.
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     * @return			Returns the area of the rectangle
     */
    public double slice(Polynomial poly, double sleft, double sright) {
        Random gen = new Random(); //calls method random to be able to generate a random numbers
        double random = sleft+(gen.nextDouble()*((sright-sleft))); //randomly generates a number between sleft and sright
        double height=(poly.evaluate(random)).getTerms()[0].getTermDouble(); //evaluates the function at that random number to find the height
        double base=(sright-sleft); //calculates the length of the base of the rectangle
        double area = (height*base);

        return area;
    }

    /**
     * Draws each slice(randomly generates a x value between sleft and sright and then evaluates the function at that x to find the height)
     * @param pframe		Plotframe where drawings appear
     * @param poly		Polynomial that the user inputs
     * @param sleft		The x-coordinate of where the slice starts
     * @param sright		The x-coordinate of where the slice ends
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
        Random gen = new Random(); //calls method that generates random numbers
        double random = sleft + gen.nextDouble()*((sright-sleft)); //generates a random number between sleft and sright
        double base=(sright-sleft);
        double height=(poly.evaluate(random)).getTerms()[0].getTermDouble(); //evaluates function at random number
        double centerX = ((sright+sleft)/2); //finds the x-coordinate of the center of the rectangle
        double centerY=(height/2); //finds the y-coordinate of the center of the rectangle

        height=Math.abs(height); //finds the absolute value of the height so that the slices can be plotted even when the polynomial is negative

        DrawableShape rect = DrawableShape.createRectangle(centerX, centerY, base, height);
        rect.color=Color.gray;
        rect.edgeColor=Color.pink;
        pframe.addDrawable(rect); //draws rectangle on plotframe
        pframe.setVisible(true); //shows plotframe
    }

}