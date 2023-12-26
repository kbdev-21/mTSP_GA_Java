import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {
    private List<City> tour = new ArrayList<>();

    /**
     * Initializes a Tour object.
     * Returns: creating an empty tour.
     */
    public Tour() {
        for (int i = 0; i < CitiesManager.numberOfCities(); i++) {
            this.tour.add(new City(-1, -1));
        }
    }

    /**
     * Initializes a Tour object.
     * @param tour(List<City>): A list of City objects representing the tour.
     */
    public Tour(List<City> tour) {
        this.tour = new ArrayList<>(tour);
    }

    /**
     * Generates a random tour by shuffling the cities in the tour.
     */
    public void generateIndividual() {
        // foreach city in the city pool, set it to the tour
        for (int dindex = 0; dindex < CitiesManager.numberOfCities(); dindex++) {
            setCity(dindex, CitiesManager.getCity(dindex));
        }

        // then shuffle it to create a random tour
        Collections.shuffle(tour);

        // Connect the last city to the first city
        tour.add(tour.get(0));
    }

    /**
     * A simple getter
     * @return List<City> tour
     */
    public List<City> getTour() {
        return this.tour;
    }

    /**
     * A simple setter
     * @return
     */
    public void setTour(List<City> tour) {
        this.tour = tour;
    }

    /**
     * Add a city to the end of the tour
     * @param city(City): The City object to add to the tour.
     */
    public void addCity(City city) {
        tour.add(city);
    }

    /**
     * Sets the city at the specified index in the tour.
     * @param index(int): The index where the city should be set.
     * @param city(City): The City object to set in the tour.
     */
    public void setCity(int index, City city) {
        tour.set(index, city);
    }

    /**
     * Swaps the positions of two cities in the tour.
     * @param i(int): Index of the first city.
     * @param j(int): Index of the second city.
     */
    public void swapCities(int i, int j) {
        Collections.swap(tour, i, j);
    }

    /**
     * Gets the city at the specified index in the tour.
     * @param index(int): The index of the city to retrieve.
     * @return City: The City object at the specified index.
     */
    public City getCity(int index) {
        return tour.get(index);
    }

    /**
     * Calculates the fitness of the tour.
     * @return double: The fitness of the tour (inverse of the total distance).
     */
    public double getFitness() {
        return 1 / getDistance();
    }

    /**
     * Calculates the total distance of the tour.
     * @return double: The total distance of the tour.
     */
    public double getDistance() {
        double distance = 0;
        for (int i = 0; i < tourSize(); i++) {
            City currentCity = getCity(i);
            City nextCity = (i + 1 < tourSize()) ? getCity(i + 1) : getCity(0);
            distance += currentCity.distanceTo(nextCity);
        }
        return distance;
    }

    /**
     * Calculates the distance from city at index aIndex to city at index bIndex in the tour.
     * @param aIndex(int): Index of the starting city.
     * @param bIndex(int): Index of the destination city.
     * @return double: The distance from city at index aIndex to city at index bIndex.
     */
    public double getDistanceFromAToB(int aIndex, int bIndex) {
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

    /**
     * Gets the number of cities in the tour.
     * @return int: The number of cities in the tour.
     */
    public int tourSize() {
        return tour.size();
    }

    /**
     * Checks if the tour contains a specific city.
     * @param city(City): The City object to check for in the tour.
     * @return boolean: True if the city is in the tour, False otherwise.
     */
    public boolean containsCity(City city) {
        return tour.contains(city);
    }

    /**
     * return: String: A string representation of the tour.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("->");
        for (int i = 0; i < tourSize(); i++) {
            result.append(getCity(i).toString()).append("->");
        }
        return result.toString();
    }

    /**
     * Removes intersections in the tour by swapping cities.
     */
    public void removeIntersections() {
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

    /**
     * 
     * @param city1(City): City object representing the coordinates of the line segments.
     * @param city2(City): City object representing the coordinates of the line segments.
     * @param city3(City): City object representing the coordinates of the line segments.
     * @param city4(City): City object representing the coordinates of the line segments.
     * @return boolean: True if the line segments intersect, False otherwise.
     */
    private boolean doIntersect(City city1, City city2, City city3, City city4) {
        int o1 = orientation(city1, city2, city3);
        int o2 = orientation(city1, city2, city4);
        int o3 = orientation(city3, city4, city1);
        int o4 = orientation(city3, city4, city2);

        return o1 != o2 && o3 != o4;
    }

    /**
     * Calculates the orientation of three points(cities) p, q, r.
     * @param p
     * @param q 
     * @param r
     * @return int: 0 if collinear, 1 if clockwise, 2 if counterclockwise.
     */
    private int orientation(City p, City q, City r) {
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

