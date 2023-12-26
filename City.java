import java.util.Random;
/**
 * Represents a city with coordinates on a 2D plane.
 */
public class City {
    // Coordinates of the city
    private int x;
    private int y;

    /**
     * Parameterized constructor that allows specifying the x and y coordinates for the city.
     * @param x: The x-coordinate of the city.
     * @param y: The y-coordinate of the city.
     */
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A simple getter
     * @return int: The x-coordinate of the city.
     */
    public int getX() {
        return this.x;
    }

    /**
     * A simple getter
     * @return int: The y-coordinate of the city.
     */
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

    /**
     * return a string representation of the city in the format "(x, y)".
     */
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    /**
     * Checks if the city has a special condition (null check).
     * @return True if the city has a special condition; otherwise, false.
     */
    public boolean checkNull() {
        return this.x == -1;
    }
}