package TestComparator;

import java.util.Comparator;

/**
 * <h1>Comparator class for Country objects</h1><br/>
 * @author Braden Hoagland
 * @since September 11, 2017
 */
public class CountryComparator implements Comparator<Country> {
	
	/**
	 * method that compares two given Country objects' populations
	 * @param country1
	 * @param country2
	 */
	public int compare(Country country1, Country country2) {
		return country1.getPopulation() - country2.getPopulation();
	}
}