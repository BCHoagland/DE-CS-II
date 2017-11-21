package productionLine;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProductionLineTest {
	
	private static PrintWriter output;
	
	public static void main(String[] args) {
		try {
			output = new PrintWriter("output.txt");
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		
		testDisk();
		testTower();
		testProductionLine();
		
		if (output != null) {
			output.close();
		}
	}
	
	public static void testDisk() {
		printTitle("DISK");
		
		Disk d1 = new Disk(1);
		Disk d2 = new Disk(4);
		Disk d3 = new Disk(4);
		
		output.println("disk 1: " + d1);
		output.println("disk 2: " + d2 + "\n");
		
		output.println("disk 1 size: " + d1.getSize());
		output.println("disk 2 size: " + d2.getSize() + "\n");
		
		output.println("disk 1 compared to disk 2 (should be negative): " + d1.compareTo(d2));
		output.println("disk 2 compared to disk 1 (should be positive): " + d2.compareTo(d1));
		output.println("disk 2 compared to an equal disk (should be zero): " + d2.compareTo(d3));
		output.println();
	}
	
	public static void testTower() {
		printTitle("TOWER");
		
		Tower t = new Tower();
		output.println("new tower: " + t + "\n");
		
		Disk d1 = new Disk(4);
		Disk d2 = new Disk(3);
		Disk d3 = new Disk(2);
		Disk d4 = new Disk(1);
		t.add(d1);
		t.add(d2);
		t.add(d3);
		t.add(d4);
		output.println("tower with disks of size 4, 3, 2, and 1 added: " + t + "\n");
				
		t.flip();
		output.println("flipped tower: " + t);

		output.println();
	}
	
	public static void testProductionLine() {
		printTitle("PRODUCTION LINE");
		
		ProductionLine p = new ProductionLine();
		output.println("new production line:\n" + p);
		
		Tower t1 = new Tower();
		t1.push(new Disk(2));
		t1.push(new Disk(4));
		t1.push(new Disk(6));
		t1.push(new Disk(8));
		p.setPyramid(t1);
		output.println("\nproduction line with loaded pyramid: " + p);
		
		p.unloadRobot();
		output.println("\nproduction line after unloadRobot(): " + p);
		
		p = new ProductionLine();
		int[] diskValues = {1, 4, 5, 6, 3, 7, 2, 1, 7, 4, 4, 4, 5, 6, 9, 5};
		for (int r : diskValues) {
			p.addDisk(new Disk(r));
		}
		output.println("\nnew production line with disks added:\n" + p);
		
		p.process();
		output.println("\nproduction line after processing:\n" + p);
		
		Tower t2 = p.removeTower();
		output.println("\ntower removed from output queue: " + t2);
	}
	
	public static void printTitle(String title) {
		String str = "";
		for (int i = 0; i < 20; i++) {
			str += "=";
		}
		str += "TESTING " + title;
		for (int i = 0; i < 20; i++) {
			str += "=";
		}
		output.println(str);
	}
}