import java.util.ArrayList;
import java.util.List;

public class Population {

    private List<Tour> tours = new ArrayList<>();
    private int populationSize;

    public Population(int populationSize, boolean initialise) {
        /**
         * Initializes a Population object.
         *
         * Parameters:
         * - populationSize (int): The size of the population.
         * - initialise (boolean): If true, randomly generates tours with {populationSize} cities.
         *                          If false, initializes an empty population.
         */
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

    public void addTour(Tour tour) {
        /**
         * Adds a tour to the population.
         *
         * Parameters:
         * - tour (Tour): The Tour object to be added to the population.
         */
        this.tours.add(tour);
    }

    public void setTour(int index, Tour tour) {
        /**
         * Sets a tour at the specified index in the population.
         *
         * Parameters:
         * - index (int): The index where the tour should be set.
         * - tour (Tour): The Tour object to set in the population.
         */
        this.tours.set(index, tour);
    }

    public Tour getTour(int index) {
        /**
         * Gets the tour at the specified index from the population.
         *
         * Parameters:
         * - index (int): The index of the tour to retrieve.
         *
         * Returns:
         * Tour: The Tour object at the specified index.
         */
        return this.tours.get(index);
    }

    public Tour getFittest() {
        /**
         * Gets the fittest tour in the population.
         *
         * Returns:
         * Tour: The fittest Tour object in the population.
         */
        Tour fittest = this.tours.get(0);
        for (int i = 1; i < this.populationSize; i++) {
            if (fittest.getFitness() <= this.getTour(i).getFitness()) {
                fittest = this.getTour(i);
            }
        }
        return fittest;
    }

    public int getSize() {
        /**
         * Gets the size of the population.
         *
         * Returns:
         * int: The number of tours in the population.
         */
        return this.tours.size();
    }

    public void equals(Population population) {
        /**
         * Sets the tours of the current population to be equal to another population.
         *
         * Parameters:
         * - population (Population): The Population object whose tours will be copied.
         */
        this.tours = new ArrayList<>(population.tours);
    }

    public void sort() {
        /**
         * Sorts the tours in the population based on their fitness using Tim Sort.
         * Time complexity: O(nlogn) in the worst case and O(n) in the best case.
         */
        this.tours.sort((tour1, tour2) -> Double.compare(tour2.getFitness(), tour1.getFitness()));
    }
}
