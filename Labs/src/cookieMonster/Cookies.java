package cookieMonster;

import java.util.Scanner;
import java.util.Stack;

public class Cookies {
	
	private static int currentTotal = 0;
	private static int max;
	private static Scanner input = new Scanner("cookies.txt");
	private static int[][] grid = {{1, 3, 0, 5, -1, 7, -1, -1, 0, 4, 2, 1},
	        {-1, 3, 2, 1, -1, 4, -1, 5, 3, -1, 1, 0},
	        {5, 4, 8, -1, 3, 2, 2, -1, 4, -1, 0, 0},
	        {2, 1, 0, 4, 1, -1, 8, 0, 2, -1, 2, 5},
	        {1, 4, 0, 1,-1, 0, 3, 2, 2, 4, 1, 4},
	        {0, 1, 4, 1, 1, 6, 1, 4, 5, 2, 1, 0},
	        {3, 2, 5, 2, 0, 7,-1, 2, 1, 0,-1, 3},
	        {0, -1,  4, -1, -1,  3,  5,  1, 4, 2, 1, 2},
	        {5, 4, 8, -1, 3, 2, 2, -1, 4, -1, 0, 0},
	        {2, 1, 0, 4, 1, -1, 8, 0, 2,-1, 2, 5},
	        {1, 3, 0, 5, -1, 7, -1, -1, 0, 4, 2, 1},
	        {0,  0,  3,  1,  5,  2,  1,  5,  4,  1,  3,  3}};			//CHANGE THIS SO IT ISN'T HARDCODED
	private static Point currentPos = new Point();
	private static Stack<Point> unusedMoves = new Stack<Point>();
	
	public void make2DArray() {
	}
	
	public static void main(String[] args) {
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
//		System.out.println(currentTotal);
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