import java.util.Random;

public class City {

    private static final int xMax = 200;
    private static final int yMax = 200;

    private int x;
    private int y;

    public City() {
        this.x = new Random().nextInt(xMax + 1);
        this.y = new Random().nextInt(yMax + 1);
    }

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double distanceTo(City otherCity) {
        int xDis = Math.abs(this.getX() - otherCity.getX());
        int yDis = Math.abs(this.getY() - otherCity.getY());
        return Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
    }

    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    public boolean checkNull() {
        return this.x == -1;
    }
}