package cookieMonster;

public class Point {
	
	private int x;
	private int y;
	private int valSoFar;
	
	public Point() {
		x = 0;
		y = 0;
		valSoFar = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		valSoFar = 0;
	}
	
	public Point(int x, int y, int val) {
		this.x = x;
		this.y = y;
		valSoFar = val;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getValSoFar() {
		return valSoFar;
	}
	
	public void incrementX() {
		x++;
	}
	
	public void incrementY() {
		y++;
	}
}