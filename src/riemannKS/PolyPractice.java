package riemannKS;
import org.dalton.polyfun.Polynomial;
import org.dalton.polyfun.Coef;

public class PolyPractice {
    public double eval(Polynomial p, double x){
        //takes a polynomial and a double and it returns a double.
        //The value of the returned double is the polynomial evaluated at the input double.
        double y=0;
        y = p.evaluate(x).getTerms()[0].getTermDouble();
        return y;
    }


    public void	addXsquared(Polynomial p) {
//	//takes a polynomial and returns void. It prints the sum of the polynomial x2 and the input polynomial,
        //and it graphs this sum of polynomials on a PlotFrame.
        Polynomial poly = new Polynomial(new double[] {0,0,1});
        Polynomial sum = new Polynomial(new double[] {0,0,0});
        sum = p.plus(poly);
        System.out.println(sum);
    }
}
