package TestComparator;

/**
 * <h1>Country class that stores a name and population</h1><br/>
 * @author Braden Hoagland
 * @since September 11, 2017
 */
public class Country {
	
	public String name;
	public int population;
	
	/**
	 * initiates a new Country object with a name and population
	 * @param name
	 * @param population
	 */
	public Country(String name, int population) {
		this.name = name;
		this.population = population;
	}
	
	/**
	 * getter function for the population variable
	 * @return population
	 */
	public int getPopulation() {
		return population;
	}
	
	/**
	 * return a formatted String representation of the object
	 */
	public String toString() {
		return name + ": " + population;
	}
}