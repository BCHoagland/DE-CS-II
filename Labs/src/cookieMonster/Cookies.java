package cookieMonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Cookies {
	
	private static int currentTotal = 0;
	private static int max;
	private static File inputFile = new File("cookies.txt");
	private static Scanner input;
	private static int[][] grid;
	private static Point currentPos = new Point();
	private static Stack<Point> unusedMoves = new Stack<Point>();
	
	public static void setScanner() {
		try {
			input = new Scanner(inputFile);
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public static void make2DArray() {
		//find the proper dimensions for the new array
		boolean newNum = true;
		int count = 0;
		for (char ch : input.nextLine().toCharArray()) {
			if (ch == ' ') {
				newNum = true;
			} else if (newNum) {
				count++;
				newNum = false;
			}
		}
		grid = new int[count][count];						//IS IT ALWAYS A SQUARE????????
		setScanner();
		
		
		//fill the new array with the proper ints
		int yIndex = 0;
		while (input.hasNextLine()) {
			int xIndex = 0;
			newNum = true;
			for (char ch : input.nextLine().toCharArray()) {
				//figure out why this works with negative numbers
				if (ch == ' ') {
					newNum = true;
				} else if (newNum) {
					grid[yIndex][xIndex] = Character.getNumericValue(ch);
					xIndex++;
					newNum = false;
				}
			}
			yIndex++;
		}
	}
	
	public static void main(String[] args) {
		setScanner();
		make2DArray();
		updateCurrentTotal();
		while (true) {
			if (canMoveRight() && canMoveDown()) {
				unusedMoves.push(new Point(currentPos.getX(), currentPos.getY(), currentTotal));
				moveDown();
			} else if (canMoveRight()) {
				moveRight();
			} else if (canMoveDown()) {
				moveDown();
			} else {
				if (isAtBottomRight()) {
					updateMax();
				}
				resetFromStack();
				moveRight();
			}
		}
	}
	
	public static boolean canMoveRight() {
		return currentPos.getX() + 1 < grid[0].length && grid[currentPos.getY()][currentPos.getX() + 1] != -1;
	}
	
	public static boolean canMoveDown() {
		return currentPos.getY() + 1 < grid.length && grid[currentPos.getY() + 1][currentPos.getX()] != -1;
	}
	
	public static boolean isAtBottomRight() {
		return currentPos.getX() == grid[0].length - 1 && currentPos.getY() == grid.length - 1;
	}
	
	public static void moveRight() {
		currentPos.incrementX();
		updateCurrentTotal();
	}
	
	public static void moveDown() {
		currentPos.incrementY();
		updateCurrentTotal();
	}
	
	public static void updateCurrentTotal() {
		currentTotal += grid[currentPos.getY()][currentPos.getX()];
	}
	
	public static void updateMax() {
		if (currentTotal > max) {
			max = currentTotal;
		}
	}
	
	public static void resetFromStack() {
		if (!unusedMoves.isEmpty()) {
			currentTotal = 0;
			currentPos = unusedMoves.pop();
		} else {
			System.out.println("max cookies: " + max);
			System.exit(0);
		}
	}
}