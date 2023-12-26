import java.util.ArrayList;
import java.util.List;

public class CitiesManager {

    // A list to store the cities
    private static List<City> citiesList = new ArrayList<>();

    /**
     * Add a new city to the city pool.
     * @param city(City): The City object to be added to the city pool.
     */
    public static void newCity(City city) {
        citiesList.add(city);
    }

    /**
     * Gets the city at the specified index from the city pool.
     * @param index(int): The index of the city to retrieve.
     * @return City: The City object at the specified index.
     */
    public static City getCity(int index) {
        return citiesList.get(index);
    }

    /**
     * Gets the total number of cities in the city pool.
     * @return int: The number of cities in the city pool.
     */
    public static int numberOfCities() {
        return citiesList.size();
    }

    /**
     * Gets the city that is nearest from the center of the coordinate system.
     * @return City: The city object that is nearest from the center.
     */
    public static City centerCity() {
        // Initialize variables
        City centerCity = citiesList.get(0);
        double distance = Double.POSITIVE_INFINITY;  // Set initial distance to negative infinity
        
        int maxX = 0;
        int maxY = 0;
        for(City city: citiesList) {
            if(city.getX() > maxX) {
                maxX = city.getX();
            }
            if(city.getY() > maxY) {
                maxY = city.getY();
            }
        }
        
        City centerCityCoordinate = new City(maxX / 2, maxY / 2);
        
        // Find the city nearest from the center
        for (City city : citiesList) {
            double distanceToCenter = city.distanceTo(centerCityCoordinate);

            if (distanceToCenter < distance) {
                distance = distanceToCenter;
                centerCity = city;
            }
        }

        //System.out.println("CENTER CITY: " + centerCity.toString());
        return centerCity;
    }
}

