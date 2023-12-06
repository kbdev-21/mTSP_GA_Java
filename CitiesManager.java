import java.util.ArrayList;
import java.util.List;

public class CitiesManager {

    // A list to store the cities
    private static List<City> citiesList = new ArrayList<>();

    public static void newCity(City city) {
        /**
         * Adds a new city to the city pool.
         *
         * Parameters:
         * - city (City): The City object to be added to the city pool.
         */
        citiesList.add(city);
    }

    public static City getCity(int index) {
        /**
         * Gets the city at the specified index from the city pool.
         *
         * Parameters:
         * - index (int): The index of the city to retrieve.
         *
         * Returns:
         * City: The City object at the specified index.
         */
        return citiesList.get(index);
    }

    public static int numberOfCities() {
        /**
         * Gets the total number of cities in the city pool.
         *
         * Returns:
         * int: The number of cities in the city pool.
         */
        return citiesList.size();
    }

    public static City centerCity() {
        /**
         * Gets the city that is nearest from the center of the coordinate system.
         *
         * Returns:
         * City: The city object that is nearest from the center.
         */
        // Initialize variables
        City centerCity = citiesList.get(0);
        double distance = Double.NEGATIVE_INFINITY;  // Set initial distance to negative infinity
        
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

        return centerCity;
    }
}

