package riemannSum;

import java.util.Scanner;

import riemannSum.riemann.*;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.*;

public class RiemannOperationClass {

    public static void main(String[] args) {
        boolean operating = true;
        Scanner scan = new Scanner(System.in);
        Polynomial poly = new Polynomial();
        while(operating) {
            System.out.println("What is the degree of your polynomial?");
            int terms = scan.nextInt();
            double[] coeffs = new double[terms+1];

            for(int degree = 0; degree <= terms; degree++) {
                System.out.println("What is the coefficient for your " + degree + " degree term?");
                double coeff = scan.nextDouble();
                coeffs[degree] = coeff;
            }
            Polynomial poly1 = new Polynomial(coeffs);
            operating = false;

            poly = poly1;
        }
        poly.print();
        System.out.println("What is the rule you would like to use to calculate the area under your polynomial? (use 'LHR' for Left Hand Rule, 'RHR' for Right Hand Rule, 'TPR' for Trapezoid Rule, 'MPR' for Midpoint Rule, 'RDR' for Random Rule, 'MXR' for Maximum Rule, 'MNR' for Minimum Rule, 'ETR' for Ethan Rule");
        String rule = scan.next();


        //LeftHandRule LHR = new LeftHandRule();
        RightHandRule RHR = new RightHandRule();
        //TrapezoidRule TPR = new TrapezoidRule();
        //MidpointRule MPR = new MidpointRule();
        //MaximumRule MXR = new MaximumRule();
        //MinimumRule MNR = new MinimumRule();
        //RandomRule RDR = new RandomRule();
        //EthanRuleExtension ETR = new EthanRuleExtension();
		/*if(!rule.equals("LHR")||!rule.equals("RHR")||!rule.equals("TPR")||!rule.equals("MPR")||!rule.equals("MXR")||!rule.equals("MNR")||!rule.equals("RDR")||!rule.equals("ETR")) {
			System.out.println("'" + rule + "'" + " was not an option...restarting");
			main(args);
		}*/



        System.out.println("If you would like to see the Riemann Sum, type rs, if you would like to see the Accumulation Function, type acc, if you would like to plot the function, type plot");
        String function = scan.next();

        if(function.equals("rs")) {
            System.out.println("What is the right-hand value for the interval you would like to calculate for?");
            double right = scan.nextDouble();
            System.out.println("What is the left-hand value for interval you would like to calculate for?");
            double left = scan.nextDouble();
            System.out.println("How many subintervals would you like to have?");
            int subs = scan.nextInt();
            //if(rule.equals("LHR"))System.out.println(LHR.rs(poly, right, left, subs));
            if(rule.equals("RHR"))System.out.println(RHR.rs(poly, right, left, subs));

            /*
            else if(rule.equals("TPR"))System.out.println(TPR.rs(poly, right, left, subs));
            else if(rule.equals("MPR"))System.out.println(MPR.rs(poly, right, left, subs));
            else if(rule.equals("MXR"))System.out.println(MXR.rs(poly, right, left, subs));
            else if(rule.equals("MNR"))System.out.println(MNR.rs(poly, right, left, subs));
            else if(rule.equals("RDR"))System.out.println(RDR.rs(poly, right, left, subs));
            else if(rule.equals("ETR"))System.out.println(ETR.rs(poly, right, left, subs));

             */
        }

        else if(function.equals("acc")) {
            System.out.println("From where you like to see?");
            double base = scan.nextDouble();
            PlotFrame n = new PlotFrame("x","y","Accumulation Graph");
            //if(rule.equals("LHR"))LHR.rsAcc(n, poly, 1, .01, base);
            if(rule.equals("RHR"))RHR.rsAcc(n, poly, 1, .01, base);
            /*else if(rule.equals("TPR"))TPR.rsAcc(n, poly, 1, .01, base);
            else if(rule.equals("MPR"))MPR.rsAcc(n, poly, 1, .01, base);
            else if(rule.equals("MXR"))MXR.rsAcc(n, poly, 1, .01, base);
            else if(rule.equals("MNR"))MNR.rsAcc(n, poly, 1, .01, base);
            else if(rule.equals("RDR"))RDR.rsAcc(n, poly, 1, .01, base);
            else if(rule.equals("ETR"))ETR.rsAcc(n, poly, 1, .01, base);

             */
            n.setVisible(true);

        }

        else if(function.equals("plot")){
            System.out.println("What is the right-hand value for the interval you would like to plot?");
            double right = scan.nextDouble();
            System.out.println("What is the left-hand value for interval you would like to plot?");
            double left = scan.nextDouble();
            System.out.println("How many subintervals would you like to plot?");
            int subs = scan.nextInt();
            PlotFrame n = new PlotFrame("x","y","Riemann Graph");
            //if(rule.equals("LHR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
           /*
            if(rule.equals("RHR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
            else if(rule.equals("TPR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
            else if(rule.equals("MPR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
            else if(rule.equals("MXR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
            else if(rule.equals("MNR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
            else if(rule.equals("RDR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);
            else if(rule.equals("ETR"))LHR.rsPlot(n, poly, 1, .01, right, left, subs);

            */
            n.setVisible(true);
        }


        System.out.println("Would you like to preform more operations? y/n");
        String answer = scan.next();
        if(answer.equals("y"))main(args);
        else {
            System.out.println("Thank you, goodbye");
            return;
        }
        scan.close();
    }
}

