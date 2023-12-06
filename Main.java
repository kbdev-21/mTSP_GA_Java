import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String cityDatasetPath = "datasets/50cities_5mans.txt";
        int numberOfSalesmans = importCitiesAndNumOfSalesman(cityDatasetPath);

        // Customizable parameters using in the algorithm:
        int populationSize = 10;
        int populationRange = 20;
        int maxUnchangedGeneration = 100;
        int nbest = 2;
        int tournamentSize = 2;
        int stopCondition = 2000;

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
        writeStringToFile(tspResult.toString(), "results/tspResult.txt");

        //write mTSP result to txt file
        StringBuilder mtspResultString = new StringBuilder();
        for(int i = 0; i < salesmanTours.size(); i++) {
            if(i != 0) {
                mtspResultString.append("\n");
            }
            mtspResultString.append(salesmanTours.get(i).toString());
        }
        writeStringToFile(mtspResultString.toString(), "results/mtspResult.txt");
    }

    private static int importCitiesAndNumOfSalesman(String filePath) {
        /**
         * Reads data from a file, initializes City objects, and updates CitiesManager.
         *
         * Parameters:
         * filePath The path to the file containing salesmen and cities data.
         * 
         * Returns:
         * The number of salesmen as specified in the file.
         */
        int numberOfSalesmans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    // Parse the first line to get the number of salesmen
                    numberOfSalesmans = Integer.parseInt(line.trim());
                    firstLine = false;
                } else {
                    // Parse city data from subsequent lines
                    String[] data = line.trim().split("\\s+");
                    int xPos = Integer.parseInt(data[1]);
                    int yPos = Integer.parseInt(data[2]);

                    // Create a new City object with parsed coordinates
                    City city = new City(xPos, yPos);

                    // Update CitiesManager with the new City
                    CitiesManager.newCity(city);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the number of salesmen specified in the file
        return numberOfSalesmans;
    }

    private static void writeStringToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the content to the file
            writer.write(content);
            System.out.println("Content has been written to the file successfully.");
        } catch (IOException e) {
            // Handle IOException, e.g., file not found or permission issues
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}