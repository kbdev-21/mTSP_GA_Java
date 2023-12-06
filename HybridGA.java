import java.util.Random;

public class HybridGA {

    private static Random random = new Random();

    public static Tour crossover(Tour parent1, Tour parent2) {
        /**
         * Performs crossover between two parent tours to create a child tour.
         *
         * Parameters:
         * - parent1 (Tour): The first parent tour.
         * - parent2 (Tour): The second parent tour.
         *
         * Returns:
         * Tour: The child tour resulting from crossover.
         */
        Tour child = new Tour();

        int startPos, endPos;
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

    public static void educate(Tour tour) {
        /**
         * Educates a child tour, currently using the remove intersections method.
         *
         * Parameters:
         * - tour (Tour): The child tour to be educated.
         */
        tour.removeIntersections();
    }

    public static Tour tournamentSelection(Population population, int tournamentSize) {
        /**
         * Performs tournament selection on the population to select the fittest individual.
         *
         * Parameters:
         * - population (Population): The population from which individuals are selected.
         * - tournamentSize (int): The size of the tournament.
         *
         * Returns:
         * Tour: The fittest individual selected through tournament selection.
         */
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomInt = random.nextInt(population.getSize());
            tournament.addTour(population.getTour(randomInt));
        }
        return tournament.getFittest();
    }

    public static void selectSurvivors(Population population, int size) {
        /**
         * Selects survivors from the population by keeping the best tours and removing the others.
         *
         * Parameters:
         * - population (Population): The population to select survivors from.
         * - size (int): The number of survivors to keep.
         */
        population.sort();
        Population newPopulation = new Population(size, false);
        for (int i = 0; i < size; i++) {
            newPopulation.addTour(population.getTour(i));
        }
        population.equals(newPopulation);
    }

    public static void diversify(Population population, int size, int nbest) {
        /**
         * Diversifies the population by keeping a specified number of the best individuals
         * and replacing the others with new random tours.
         *
         * Parameters:
         * - population (Population): The population to diversify.
         * - size (int): The desired size of the population after diversification.
         * - nbest (int): The number of best individuals to keep.
         */
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

