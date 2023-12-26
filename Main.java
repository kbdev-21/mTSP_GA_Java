import java.util.List;

public class Main {
    /**
     * The algorithm will run here
     */
    public static void main(String[] args) {
        // Setup the dataset  
        String cityDatasetPath = "datasets/random_dataset.txt";
        int numberOfSalesmans = FileHelper.importCitiesAndNumOfSalesman(cityDatasetPath);

        // Customizable parameters using in the algorithm:
        int populationSize = 10;
        int populationRange = 20;
        int maxUnchangedGeneration = 100;
        int nbest = 2;
        int tournamentSize = 2;
        int stopCondition = 4000;

        // Hybrid Genetic Algorithm
        // initialize a random population
        Population population = new Population(populationSize, true);

        // show first generation
        //population.getFittest().plotTour("Best tour in the initial population");

        // start recording time
        long startTime = System.currentTimeMillis();

        // start the genetic loop
        int count = 0;
        int unchangedCount = 0;
        double lastBest = 0;

        while (count < stopCondition) {
            // sort the population
            population.sort();

            // get 2 parents using tournamentSelection
            Tour parent1 = HybridGA.tournamentSelection(population, tournamentSize);
            Tour parent2 = HybridGA.tournamentSelection(population, tournamentSize);

            // crossover to get a child
            Tour child = HybridGA.crossover(parent1, parent2);

            // educate the child
            HybridGA.educate(child);

            // add child to population
            population.addTour(child);

            // if population size reach the customizable range, select survivors
            // (means sort and get best tours, remove all the others)
            if (population.getSize() == populationRange + populationSize) {
                HybridGA.selectSurvivors(population, populationSize);
            }

            // diversify if the best individual is not improved after maxUnchangedGeneration
            if (unchangedCount == maxUnchangedGeneration) {
                HybridGA.diversify(population, populationSize, nbest);
                unchangedCount = 0;
            }

            // print the best tour in the current generation to check progress
            System.out.println("The best fitness tour of generation " + (count + 1) + ": "
                    + population.getFittest().getDistance());

            // count the unchangedCount if the best individual is not improved compare to the last generation
            if (population.getFittest().getFitness() == lastBest) {
                unchangedCount++;
            } else {
                unchangedCount = 0;
            }

            // save the new best individual
            lastBest = population.getFittest().getFitness();

            // count the loop
            count++;
        }

        // print the algorithm's result
        Tour tspResult = population.getFittest();
        System.out.println("Final TSP result: " + tspResult.toString());

        //split for mTSP
        List<Tour> salesmanTours = MultipleTSP.splitToursForEachSalesman(tspResult, numberOfSalesmans);

        // print the mTSP result
        for (int i = 0; i < salesmanTours.size(); i++) {
            System.out.println("Salesman " + (i + 1) + "'s tour: " + salesmanTours.get(i).toString());
        }

        // end recording time and show running time of the dataset:
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("Running time of " + cityDatasetPath + " dataset: " + runningTime + " milliseconds");

        //write TSP result to txt file
        FileHelper.writeStringToFile(tspResult.toString(), "results/tspResult.txt");

        //write mTSP result to txt file
        StringBuilder mtspResultString = new StringBuilder();
        for(int i = 0; i < salesmanTours.size(); i++) {
            if(i != 0) {
                mtspResultString.append("\n");
            }
            mtspResultString.append(salesmanTours.get(i).toString());
        }
        FileHelper.writeStringToFile(mtspResultString.toString(), "results/mtspResult.txt");
    }

    
}