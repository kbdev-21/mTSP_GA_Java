import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Tour> tours = new ArrayList<>();
    private int populationSize;

    /**
     * Initializes a Population object.
     * @param populationSize(int): The size of the population.
     * @param initialise(boolean): If true, randomly generates tours with {populationSize} cities.
     *                             If false, initializes an empty population.
     */
    public Population(int populationSize, boolean initialise) {
        this.populationSize = populationSize;

        // if initialise is true, randomly generate the tours with {populationSize} cities
        // if initialise is false, the initial population will be empty
        if (initialise) {
            for (int i = 0; i < populationSize; i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                this.tours.add(newTour);
            }
        }
    }

    /**
     * Add a tour to the population.
     * @param tour(Tour): The Tour object to be added to the population.
     */
    public void addTour(Tour tour) {
        this.tours.add(tour);
    }

    /**
     * Sets a tour at the specified index in the population.
     * @param index(int): The index where the tour should be set.
     * @param tour(Tour): The Tour object to set in the population.
     */
    public void setTour(int index, Tour tour) {
        this.tours.set(index, tour);
    }

    /**
     * Get the tour at the specified index from the population.
     * @param index(int): The index of the tour to retrieve.
     * @return Tour: The Tour object at the specified index.
     */
    public Tour getTour(int index) {
        return this.tours.get(index);
    }

    /**
     * Get the fittest tour in the population.
     * @return Tour: The fittest Tour object in the population.
     */
    public Tour getFittest() {
        Tour fittest = this.tours.get(0);
        for (int i = 1; i < this.populationSize; i++) {
            if (fittest.getFitness() <= this.getTour(i).getFitness()) {
                fittest = this.getTour(i);
            }
        }
        return fittest;
    }

    /**
     * Get the size of the population.
     * @return int: The number of tours in the population.
     */
    public int getSize() {
        return this.tours.size();
    }

    /**
     * Set the tours of the current population to be equal to another population.
     * @param population(Population): The Population object whose tours will be copied.
     */
    public void equals(Population population) {
        this.tours = new ArrayList<>(population.tours);
    }

    /**
     * Sorts the tours in the population based on their fitness using Tim Sort.
     * Time complexity: O(nlogn) in the worst case and O(n) in the best case.
     */
    public void sort() {
        this.tours.sort((tour1, tour2) -> Double.compare(tour2.getFitness(), tour1.getFitness()));
    }
}
