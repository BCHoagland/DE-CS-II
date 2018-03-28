package hashFunctions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjMap {

	public static HashMap<Obj, Boolean> map = new HashMap<Obj, Boolean>();

	public static void reportOnHashMap() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(map);

		for (Object tb : table) {
			if (tb != null) {
				Object entry = tb;
				while (entry != null) {
					Field keyField = entry.getClass().getDeclaredField("key");
					keyField.setAccessible(true);
					Obj o = (Obj) keyField.get(entry);
					System.out.print(o.name + ", ");

					Field nextField = entry.getClass().getDeclaredField("next");
					nextField.setAccessible(true);

					entry = nextField.get(entry);
				}
				System.out.println();
			}
		}

	}

	public static void main(String[] args) throws java.io.FileNotFoundException, NoSuchFieldException, IllegalAccessException {
		Obj o1 = new Obj(1, "a");
		Obj o2 = new Obj(1, "b");
		Obj o3 = new Obj(2, "c");

		map.put(o1, true);
		map.put(o2, false);
		map.put(o3, true);

		reportOnHashMap();

		//		System.out.println();
		//		System.out.println(map.get(o1));
	}
}
