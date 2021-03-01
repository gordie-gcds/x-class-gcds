package riemannKS;
import java.util.Scanner;

import org.opensourcephysics.frames.PlotFrame;

import org.dalton.polyfun.Polynomial;

public class UserInterface {
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        Scanner scan2 = new Scanner (System.in);


        LeftHandRule LHRule = new LeftHandRule();  //defines LHRule
        RightHandRule RHRule = new RightHandRule();  //defines RHRule
        TrapezoidRule TRule = new TrapezoidRule(); //defines TRule
        MidpointRule MidRule = new MidpointRule(); //defines MidRule
        RandomRule RandomRule = new RandomRule(); //defines RandomRule
        SimpsonsRule SimpsonsRule = new SimpsonsRule(); //defines SimpsonsRule

        //Ask user to enter the polynomial:

        System.out.println("What degree is your polynomial?");
        int degree = scan.nextInt();

        double[] coefficients = new double[degree+1]; //defines an array of length degree+1

        System.out.println("Please enter your last coefficient (enter coefficients from last to first)");
        for (int i = 0; i < coefficients.length; i++) { //for loop that adds all the coefficients into the array
            coefficients[i] = scan.nextDouble();
            if (i==(coefficients.length-1)) {
                System.out.println();
            }
            else {
                System.out.println("Enter your next coefficient");
            }
        }

        Polynomial poly = new Polynomial(coefficients); //constructs polynomial
        System.out.println("Your Polynomial: "); poly.print();

        //Ask user questions:
        System.out.println("Where do you want the area under the curve to start (left side)?");
        double left = scan.nextDouble();
        System.out.println("Where do you want the area under the curve to end (right side)?");
        double right = scan.nextDouble();
        System.out.println("How many subintervals do you want?");
        int intervals = scan.nextInt();
        System.out.println("What do you want the precision to be?");
        double precision = scan.nextDouble();
        System.out.println("What do you want the base of your accumulation function to be?");
        double base = scan.nextDouble();
        System.out.println("Which rule would you like to use to approximate the area under the curve (LHRule, RHRule, TRule, MidRule, RandomRule, MaxRule, MinRule, SimpsonsRule)?");
        String rule = scan2.nextLine();

        PlotFrame p = new PlotFrame("x","y",rule); //declares plotframe that polynomial and slices are going to be displayed on
        p.setPreferredMinMaxX(-5, 5);
        p.setPreferredMinMaxY(-5, 5);
        p.setDefaultCloseOperation(3);
        p.setVisible(true);

        PlotFrame accu = new PlotFrame("x","y","Accumulation Function"); //declares plotframe that accumulation function is going to be displayed on
        accu.setPreferredMinMaxX(-5, 5);
        accu.setPreferredMinMaxY(-5, 5);
        accu.setDefaultCloseOperation(3);
        accu.setVisible(true);

        //if else statements that display based on the users answer about what rule they want to use:
        if (rule.equals("LHRule")) {
            System.out.println(LHRule.rs(poly, left, right, intervals)+"\n"); //prints out the approximated area
            LHRule.rsPlot(p, poly, 1, precision, left, right, intervals); //plots the polynomial and the slices
            LHRule.rsAcc(accu, poly, 2, precision, base); //plots the accumulation function
            System.out.println("Would you like to test another polynomial and rule?");
            String userAnswer=scan2.nextLine();
        }
        else if (rule.equals("RHRule")) {
            System.out.println(RHRule.rs(poly, left, right, intervals)+"\n"); //prints out the approximated area
            RHRule.rsPlot(p, poly, 1, precision, left, right, intervals); //plots the polynomial and the slices
            RHRule.rsAcc(accu, poly, 2, precision, base); //plots the accumulation function
        }

        else if (rule.equals("TRule")) {
            System.out.println(TRule.rs(poly, left, right, intervals)+"\n"); //prints out the approximated area
            TRule.rsPlot(p, poly, 1, precision, left, right, intervals); //plots the polynomial and the slices
            TRule.rsAcc(accu, poly, 2, precision, base); //plots the accumulation function
        }

        else if (rule.equals("SimpsonsRule")) {
            System.out.println(SimpsonsRule.rs(poly, left, right, intervals)+"\n"); //prints out the approximated area
            SimpsonsRule.rsPlot(p, poly, 1, precision, left, right, intervals); //plots the polynomial and the slices
            SimpsonsRule.rsAcc(accu, poly, 2, precision, base); //plots the accumulation function
        }


    }
}
