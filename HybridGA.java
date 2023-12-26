import java.util.Random;

public class HybridGA {
    /**
     * 
     * @param parent1(Tour): The first parent tour.
     * @param parent2(Tour): The second parent tour
     * @return a Tour: The child tour resulting from crossover.
     */
    public static Tour crossover(Tour parent1, Tour parent2) {
        Tour child = new Tour();

        int startPos, endPos;
        Random random = new Random();
        do {
            startPos = random.nextInt(parent1.tourSize());
            endPos = random.nextInt(parent2.tourSize());
        } while (startPos >= endPos);

        for (int i = startPos; i < endPos; i++) {
            child.setCity(i, parent1.getCity(i));
        }

        for (int i = 0; i < parent2.tourSize(); i++) {
            if (!child.containsCity(parent2.getCity(i))) {
                for (int i1 = 0; i1 < child.tourSize(); i1++) {
                    if (child.getCity(i1).checkNull()) {
                        child.setCity(i1, parent2.getCity(i));
                        break;
                    }
                }
            }
        }

        return child;
    }

    /**
     * Educates a child tour, currently using the remove intersections method.
     * @param tour(Tour): The child tour to be educated.
     */
    public static void educate(Tour tour) {
        tour.removeIntersections();
    }

    /**
     * Performs tournament selection on the population to select the fittest individual.
     * @param population(Population): The population from which individuals are selected.
     * @param tournamentSize(int): The size of the tournament.
     * @return a Tour: The fittest individual selected through tournament selection.
     */
    public static Tour tournamentSelection(Population population, int tournamentSize) {
        Population tournament = new Population(tournamentSize, false);
        Random random = new Random();
        for (int i = 0; i < tournamentSize; i++) {
            int randomInt = random.nextInt(population.getSize());
            tournament.addTour(population.getTour(randomInt));
        }
        return tournament.getFittest();
    }

    /**
     * Selects survivors from the population by keeping the best tours and removing the others.
     * @param population(Population): The population to select survivors from.
     * @param size(int): The number of survivors to keep.
     */
    public static void selectSurvivors(Population population, int size) {
        population.sort();
        Population newPopulation = new Population(size, false);
        for (int i = 0; i < size; i++) {
            newPopulation.addTour(population.getTour(i));
        }
        population.equals(newPopulation);
    }

    /**
     * Diversifies the population by keeping a specified number of the best individuals 
     * and replacing the others with new random tours.
     * @param population(Population): The population to diversify.
     * @param size(int): The desired size of the population after diversification.
     * @param nbest(int): The number of best individuals to keep.
     */
    public static void diversify(Population population, int size, int nbest) {
        population.sort();
        Population newPopulation = new Population(0, false);
        for (int i = 0; i < size; i++) {
            if (i < nbest) {
                newPopulation.addTour(population.getTour(i));
            } else {
                Tour randomTour = new Tour();
                randomTour.generateIndividual();
                newPopulation.addTour(randomTour);
            }
        }
        population.equals(newPopulation);
    }
}

