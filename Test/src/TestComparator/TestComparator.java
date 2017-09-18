package TestComparator;

import java.util.Arrays;

/**
 * <h1>Test Class for the CountryComparator class</h1><br/>
 * @author Braden Hoagland
 * @since September 11, 2017
 */
public class TestComparator {
	
	/**
	 * main method that tests CountryComparator with an array of Country objects and prints the result
	 * @param args
	 */
	public static void main(String[] args) {
		Country[] countries = {
				new Country("United States", 1),
				new Country("Germany", 2),
				new Country("Britain", 3),
				new Country("France", 4)
		};
		
		Arrays.sort(countries, new CountryComparator());
		System.out.println(Arrays.toString(countries));
	}
}