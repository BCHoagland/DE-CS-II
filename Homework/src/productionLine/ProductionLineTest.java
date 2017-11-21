package productionLine;

public class ProductionLineTest {
	
	public static void main(String[] args) {
		testDisk();
		testTower();
		testProductionLine();
	}
	
	public static void testDisk() {
		printTitle("DISK");
		
		Disk d1 = new Disk(1);
		Disk d2 = new Disk(4);
		Disk d3 = new Disk(4);
		
		System.out.println("disk 1: " + d1);
		System.out.println("disk 2: " + d2 + "\n");
		
		System.out.println("disk 1 size: " + d1.getSize());
		System.out.println("disk 2 size: " + d2.getSize() + "\n");
		
		System.out.println("disk 1 compared to disk 2: " + d1.compareTo(d2));
		System.out.println("disk 2 compared to an equal disk: " + d2.compareTo(d3) + "\n");
	}
	
	public static void testTower() {
		printTitle("TOWER");
		
		Tower t = new Tower();
		System.out.println("new tower: " + t + ", inverted: " + t.isInverted() + "\n");
		
		Disk d1 = new Disk(4);
		Disk d2 = new Disk(3);
		Disk d3 = new Disk(2);
		Disk d4 = new Disk(1);
		t.add(d1);
		t.add(d2);
		t.add(d3);
		t.add(d4);
		System.out.println("tower with disks of size 4, 3, 2, and 1 added: " + t);
		
		System.out.println("should still not be regular -> isRegular: " + t.isRegular());
		System.out.println("should still be inverted -> isInverted: " + t.isInverted() + "\n");
		
		t.flip();
		System.out.println("flipped tower: " + t);
		System.out.println("should be regular now -> isRegular: " + t.isRegular());
		System.out.println("should not be inverted now -> isInverted: " + t.isInverted() + "\n");
	}
	
	public static void testProductionLine() {
		printTitle("PRODUCTION LINE");
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
		System.out.println(str);
	}
}