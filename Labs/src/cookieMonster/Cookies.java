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
		setScanner();
		int lineCount = 0;
		while (input.hasNextLine()) {
			lineCount++;
			input.nextLine();
		}
		grid = new int[lineCount][lineCount];
		
		//fill the new array with the proper ints
		setScanner();
		int rowCount = 0;
		int colCount = 0;
		while (input.hasNextInt()) {
			int num = input.nextInt();
			grid[rowCount][colCount] = num;
			colCount++;
			if (colCount % lineCount == 0) {
				rowCount++;
				colCount = 0;
			}
		}
	}

	public static void main(String[] args) {
		make2DArray();

		/*for (int[] row : grid) {
			for (int col : row) {
				System.out.print(col + ", ");
			}
			System.out.println();
		}*/

		System.out.println("original: " + getOptimalPath());
		System.out.println("recursive: " + getOptimalPath(11, 11));
	}

	//NON-RECURSIVE OPTIMAL PATH
	public static int getOptimalPath() {
		updateCurrentTotal();
		while (true) {
			//if two moves exist, move down and save this position for later
			if (canMoveRight() && canMoveDown()) {
				unusedMoves.push(new Point(currentPos.getX(), currentPos.getY(), currentTotal));
				moveDown();
				//if cookie monster can only move right, move right
			} else if (canMoveRight()) {
				moveRight();
				//if cookie monster can only move down, move down
			} else if (canMoveDown()) {
				moveDown();
				//no moves available
			} else {
				//if cookie monster is at the bottom corner of the grid, check to see if max needs updating
				if (isAtBottomRight()) {
					updateMax();
				}
				//move cookie monster back to the last position where he had to choose a direction to move
				//print the max value if there are no positions left on the stack
				if (resetFromStack()) {
					moveRight();
				} else {
					return max;
				}
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

	public static boolean resetFromStack() {
		if (!unusedMoves.isEmpty()) {
			Point lastPoint = unusedMoves.pop();
			currentPos = lastPoint;
			currentTotal = lastPoint.getValSoFar();
			return true;
		} else {
			return false;
		}
	}
	
	//RECURSIVE OPTIMAL PATH
	public static int getOptimalPath(int row, int col) {
		if (canMoveUp(row, col) && canMoveLeft(row, col)) {
			return grid[row][col] + Math.max(getOptimalPath(row - 1, col), getOptimalPath(row, col - 1));
		} else if (canMoveUp(row, col)) {
			return grid[row][col] + getOptimalPath(row - 1, col);
		} else if (canMoveLeft(row, col)) {
			return grid[row][col] + getOptimalPath(row, col - 1);
		} else {
			return grid[row][col];
		}
	}
	
	public static boolean canMoveUp(int row, int col) {
		return row - 1 >= 0 && grid[row - 1][col] != -1;
	}

	public static boolean canMoveLeft(int row, int col) {
		return col - 1 >= 0 && grid[row][col - 1] != -1;
	}
}