import java.util.Random;
/**
 * Represents a city with coordinates on a 2D plane.
 */
public class City {
    // Maximum values for x and y coordinates
    private static final int xMax = 200;
    private static final int yMax = 200;

    // Coordinates of the city
    private int x;
    private int y;

    /**
     * Default constructor that initializes a city with random coordinates within the specified maximum values.
     */
    public City() {
        this.x = new Random().nextInt(xMax + 1);
        this.y = new Random().nextInt(yMax + 1);
    }

    public City(int x, int y) {
         /**
         * Parameterized constructor that allows specifying the x and y coordinates for the city.
         *
         * Parameters:
         *  x The x-coordinate of the city.
         *  y The y-coordinate of the city.
         */
        this.x = x;
        this.y = y;
    }

    public int getX() {
        /**
         * Gets the x-coordinate of the city.
         *
         * Returns:
         * The x-coordinate of the city.
         */
        return this.x;
    }

    public int getY() {
        return this.y;
        
    }

    public double distanceTo(City otherCity) {
         /**
         * Calculates the Euclidean distance between the current city and another city.
         *
         * Parameters:
         * otherCity The other city to calculate the distance to.
         * 
         * Returns:
         * The distance between the two cities.
         */
        int xDis = Math.abs(this.getX() - otherCity.getX());
        int yDis = Math.abs(this.getY() - otherCity.getY());
        return Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
    }

    public String toString() {
        /**
         * Provides a string representation of the city in the format "(x, y)".
         *
         * Returns: 
         * The string representation of the city.
         */
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    public boolean checkNull() {
         /**
         * Checks if the city has a special condition (null check).
         *
         * Returns: 
         *  True if the city has a special condition; otherwise, false.
         */
        return this.x == -1;
    }
}