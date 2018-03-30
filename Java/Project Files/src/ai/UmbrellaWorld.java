package ai;

import java.text.DecimalFormat;
import java.util.Scanner;

public class UmbrellaWorld {
	//The values are as follows: 
	//Probability that it is raining
	//Probability that it is NOT raining
	//Transition model true probability
	//Transition model false probability
	//Sensor model true probability
	//Sensor model false probability
	static double probRainT, probRainF, tranT, tranF, sensT, sensF;
	//boolean that represents whether umbrella is true or false
	//exit represents if the user wishes to exit the loop
	static boolean umbrella, exit;
	//input for user telling if umbrella (T, F, M) is true, false, or to end the loop, respectively
	static char inChar;
	//integer for counting the days, starting at 0, or initial state
	static int day = 0;
	 //Decimal format to keep numbers within 1,000ths
	static DecimalFormat df = new DecimalFormat("#.###");
	public static void main(String[] args) {
		//asks for initial state values
		System.out.println("Enter the prior beliefs (Probalities at day 0)");
		System.out.print("true: ");
		probRainT = getValueInput();
		System.out.print("false: ");
		probRainF = getValueInput();
		System.out.println();
		//asks for CPT of transition model
		System.out.println("Enter the CPT for the Transition model");
		System.out.print("true: ");
		tranT = getValueInput();
		System.out.print("false: ");
		tranF = getValueInput();
		System.out.println();
		//asks for CPT of sensor model
		System.out.println("Enter the CPT for Sensor model");
		System.out.print("true: ");
		sensT = getValueInput();
		System.out.print("false: ");
		sensF = getValueInput();
		System.out.println();
		//loop for input
		while(!exit) {
			//shows transition model on every interation
			transitionModel();
			direct();
		}
		System.out.println("Exiting loop...");
		System.out.println("Process terminated.");
	}
	private static void direct() {
		System.out.println("Does the boss have an umbrella on day " + day + "?");
		System.out.println("(Enter 'T' for true, 'F' for false, or 'M' to end the loop):");
		inChar = getCharInput();
		//determines if the boss comes in with an umbrella, given user input
		switch(inChar) {
		case 'T': 
		case 't':
			umbrella = true;
			sensorModel();
		break;
		case 'F': 
		case 'f':
			umbrella = false;
			sensorModel();
		break;
		case 'M':
		case 'm':
			exit = true;
		break;
		default: System.out.println("Error: Invalid input, try again.");
			direct();
		break;
		}
	}
	private static void sensorModel() {
		double tempT = 0 + probRainT;
		double tempF = 0 + probRainF;
		System.out.println("Sensor model for day " + day + " given umbrella is " + (umbrella));
		//displays equation
		System.out.println("P(R" + day + "|u" + day + ") \t = ALPHA{P(u" + day + "|R"+ day +")P(R"+ day + ")}");
		double a, b;
		//determines which values are being multiplied, given the boss comes in with an umbrella or not
		if(umbrella) {
			a = sensT;
			b = sensF;
		}
		else {
			a = sensF;
			b = sensT;
		}
		//the ALPHA{} signifies what needs to be normalized still
		System.out.println("\t = ALPHA{<" + df.format(a) + " * " + df.format(probRainT) + ", " + df.format(b) + " * " + df.format(probRainF) + ">}");
		probRainT = tempT * a;
		probRainF = tempF * b;
		//values before normalization
		System.out.println("\t = ALPHA{<" + df.format(probRainT) + ", " + df.format(probRainF) + ">}");
		//values are overwritten with the formula
		probRainT = normalize(probRainT, probRainF);
		probRainF = 1.0 - probRainT;
		System.out.println("\t = <" + df.format(probRainT) + ", " + df.format(probRainF) + ">");
		System.out.println();
	}
	private static void transitionModel() {
		day++;
		double tempT = 0 + probRainT;
		double tempF = 0 + probRainF;
		System.out.println("Transition model for day " + day + " given day " + (day-1));
		System.out.println("P(R" + day + ") \t = P(R" + day + "|r"+ (day-1) +"=true)P(r"+ (day-1) +"=true) + P(R" + day + "|r"+ (day-1) +"=false)P(r"+ (day-1) +"=false)");
		System.out.println("\t = <" + df.format(tranT) + ", " + df.format(tranF) + "> * " + df.format(probRainT) + " + " + " <" + df.format(tranF) 
			+ ", " + df.format(tranT) + "> * " + df.format(probRainF));
		
		probRainT = (tranT * tempT) + (tranF * tempF);
		probRainF = (tranF * tempT) + (tranT * tempF);
		probRainT = normalize(probRainT, probRainF);
		probRainF = 1.0 - probRainT;
		System.out.println("\t = <" + df.format(probRainT) + ", " + df.format(probRainF) + ">");
		System.out.println();
	}
	//methods that normalizes a vector and returns 1 value, the other is found by taking 1 - normalized a
	public static double normalize(double a, double b) {
		return (a/(a+b));
	}
	public static char getCharInput() {
		//System input for user
		Scanner s = new Scanner(System.in);
		char val = '-';
		try {
			System.out.print(">");
			val = (s.next()).charAt(0);
		}
		catch(Exception e) {
			System.out.println("Input error, try agian.");
			//not a valid char, run it again
			val = getCharInput();
		}
		//returns input that is a valid char
		return val;
	}
	public static double getValueInput() {
		//System input for user
		Scanner s = new Scanner(System.in);
		double val = 0.0;
		try {
			System.out.print(">");
			val = Double.parseDouble(s.next());
		}
		catch(Exception e) {
			System.out.println("Input error, try agian.");
			//not a number, run it again
			val = getValueInput();
		}
		//returns value if the value is a number
		return val;
	}
}
