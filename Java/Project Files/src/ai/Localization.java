package ai;

import java.util.Scanner;

public class Localization {
	static double [][] grid = new double [3][3];
	static double epsilon = -1;
	static boolean north, south, east, west, obstN, obstS, obstE, obstW;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean loop = true;
		grid[0][2] = -1;
		grid[1][1] = -1;
		printGrid(true);
		while(loop) {
			System.out.println("Enter the noise factor [0 - 1]: ");
			System.out.print(">");
			String temp = s.nextLine();
			try {
				epsilon = Double.parseDouble(temp);
				loop = false;
			} catch (Exception e) {
				System.out.println("Error: invalid input, try again. " + e.getMessage());
			}
		}
		loop = true;
		while(loop) {
			System.out.println("Enter an observation: ");
			System.out.print(">");
			try {
				getObs(s.nextLine());
				loop = false;
			}
			catch (Exception e) {
				north = false;
				south = false;
				east = false;
				west = false;
				System.out.println("Error: invalid input, try again.");
			}
		}
		debug();
		setObstacles(0, 0);
		debug();
		s.close();
	}
	public static void debug() {
		System.out.println("Epsilon = " + epsilon);
		System.out.println("North = " + north);
		System.out.println("South = " + south);
		System.out.println("East = " + east);
		System.out.println("West = " + west);
		System.out.println("North Obstacle= " + obstN);
		System.out.println("South Obstacle = " + obstS);
		System.out.println("East Obstacle = " + obstE);
		System.out.println("West Obstacle = " + obstW);
		printGrid(false);
		printGrid(true);
	}
	public static void setObstacles(int x, int y){
		if(north) {
			obstN = false;
			if(y == 0) {
				obstN = true;
			}
			else if(grid[x][y-1] < 0) {
				obstN = true;
			}
		}
		if(south) {
			obstS = false;
			if(y == 2) {
				obstS = true;
			}
			else if(grid[x][y+1] < 0) {
				obstS = true;
			}
		}
		if(east) {
			obstE = false;
			if(x == 2) {
				obstE = true;
			}
			else if(grid[x+1][y] < 0) {
				obstE = true;
			}
		}
		if(west) {
			obstW = false;
			if(x == 0) {
				obstW = true;
			}
			else if(grid[x-1][y] < 0) {
				obstW = true;
			}
		}
	}
	public static void getObs(String input) throws Exception {
		for(int i = 0; i < input.length(); i++) {
			switch(input.substring(i, i+1)) {
			case "N":
			case "n":
				north = true;
				break;
			case "S":
			case "s":
				south = true;
				break;
			case "W":
			case "w":
				west = true;
				break;
			case "E":
			case "e":
				east = true;
				break;
			default: throw new Exception();
			}
		}
	}
	public static double normalize(double a, double b) {
		return (a/(a+b));
	}
	public static void printGrid(boolean wValues) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				System.out.print("[");
				if(grid[i][j] < 0) {
					System.out.print("X]\t\t");
				}
				else if(wValues) {
					System.out.print("<" + grid[i][j] + ", " + grid[i][j] + ">]\t");
				}
				else if(!wValues) {
					System.out.print(" ]\t\t");
				}
			}
			System.out.println();
		}
	}
}
