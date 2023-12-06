import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {

    private List<City> tour = new ArrayList<>();

    public Tour() {
        /**
         * Initializes a Tour object.
         * 
         * Returns: 
         * creating an empty tour.
         */
        for (int i = 0; i < CitiesManager.numberOfCities(); i++) {
            this.tour.add(new City(-1, -1));
        }
    }

    public Tour(List<City> tour) {
        /**
         * Initializes a Tour object.
         *
         * Parameters:
         * - tour (List<City>): A list of City objects representing the tour.
         */

        this.tour = new ArrayList<>(tour);
    }

    public void generateIndividual() {
        /**
         * Generates a random tour by shuffling the cities in the tour.
         */
        // foreach city in the city pool, set it to the tour
        for (int dindex = 0; dindex < CitiesManager.numberOfCities(); dindex++) {
            setCity(dindex, CitiesManager.getCity(dindex));
        }

        // then shuffle it to create a random tour
        Collections.shuffle(tour);

        // Connect the last city to the first city
        tour.add(tour.get(0));
    }

    public void addCity(City city) {
        /**
         * Adds a city to the tour.
         *
         * Parameters:
         * - city (City): The City object to add to the tour.
         */
        tour.add(city);
    }

    public void setCity(int index, City city) {
        /**
         * Sets the city at the specified index in the tour.
         *
         * Parameters:
         * - index (int): The index where the city should be set.
         * - city (City): The City object to set in the tour.
         */
        tour.set(index, city);
    }

    public void swapCities(int i, int j) {
        /**
         * Swaps the positions of two cities in the tour.
         *
         * Parameters:
         * - i (int): Index of the first city.
         * - j (int): Index of the second city.
         */
        Collections.swap(tour, i, j);
    }

    public City getCity(int index) {
        /**
         * Gets the city at the specified index in the tour.
         *
         * Parameters:
         * - index (int): The index of the city to retrieve.
         *
         * Returns:
         * City: The City object at the specified index.
         */
        return tour.get(index);
    }

    public double getFitness() {
        /**
         * Calculates the fitness of the tour.
         *
         * Returns:
         * double: The fitness of the tour (inverse of the total distance).
         */
        return 1 / getDistance();
    }

    public double getDistance() {
        /**
         * Calculates the total distance of the tour.
         *
         * Returns:
         * double: The total distance of the tour.
         */
        double distance = 0;
        for (int i = 0; i < tourSize(); i++) {
            City currentCity = getCity(i);
            City nextCity = (i + 1 < tourSize()) ? getCity(i + 1) : getCity(0);
            distance += currentCity.distanceTo(nextCity);
        }
        return distance;
    }

    public double getDistanceFromAToB(int aIndex, int bIndex) {
        /**
         * Calculates the distance from city at index aIndex to city at index bIndex in the tour.
         *
         * Parameters:
         * - aIndex (int): Index of the starting city.
         * - bIndex (int): Index of the destination city.
         *
         * Returns:
         * double: The distance from city at index aIndex to city at index bIndex.
         */
        double distance = 0;
        if (bIndex == 0) {
            distance += getCity(tourSize() - 1).distanceTo(getCity(0));
            bIndex = tourSize() - 1;
        }
        for (int i = aIndex; i < bIndex; i++) {
            City currentCity = getCity(i);
            City nextCity = getCity(i + 1);
            distance += currentCity.distanceTo(nextCity);
        }
        return distance;
    }

    public int tourSize() {
        /**
         * Gets the number of cities in the tour.
         *
         * Returns:
         * int: The number of cities in the tour.
         */
        return tour.size();
    }

    public boolean containsCity(City city) {
        /**
         * Checks if the tour contains a specific city.
         *
         * Parameters:
         * - city (City): The City object to check for in the tour.
         *
         * Returns:
         * boolean: True if the city is in the tour, False otherwise.
         */
        return tour.contains(city);
    }

    public String toString() {
        /**
         * Generates a string representation of the tour.
         *
         * Returns:
         * String: A string representation of the tour.
         */
        StringBuilder result = new StringBuilder("->");
        for (int i = 0; i < tourSize(); i++) {
            result.append(getCity(i).toString()).append("->");
        }
        return result.toString();
    }

    public void removeIntersections() {
        /**
         * Removes intersections in the tour by swapping cities.
         */
        tour.add(tour.get(0));
        int tourSize = tour.size();

        for (int i = 0; i < tourSize - 1; i++) {
            for (int j = i + 2; j < tourSize - 1; j++) {
                City city1 = getCity(i);
                City city2 = getCity(i + 1);
                City city3 = getCity(j);
                City city4 = getCity(j + 1);

                if (doIntersect(city1, city2, city3, city4)) {
                    swapCities(i + 1, j);
                }
            }
        }

        tour.remove(tour.size() - 1);
    }

    private boolean doIntersect(City city1, City city2, City city3, City city4) {
        /**
         * Checks if two line segments intersect.
         *
         * Parameters:
         * - city1, city2, city3, city4 (City): City objects representing the coordinates of the line segments.
         *
         * Returns:
         * boolean: True if the line segments intersect, False otherwise.
         */
        int o1 = orientation(city1, city2, city3);
        int o2 = orientation(city1, city2, city4);
        int o3 = orientation(city3, city4, city1);
        int o4 = orientation(city3, city4, city2);

        return o1 != o2 && o3 != o4;
    }

    private int orientation(City p, City q, City r) {
        /**
         * Calculates the orientation of three points (p, q, r).
         *
         * Returns:
         * int: 0 if collinear, 1 if clockwise, 2 if counterclockwise.
         */
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) {
            return 0;
        } else if (val > 0) {
            return 1;
        } else {
            return 2;
        }
    }
}

