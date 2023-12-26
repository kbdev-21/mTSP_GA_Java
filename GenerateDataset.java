import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Run this file to insert a random dataset into the /datasets folder 
 */
public class GenerateDataset {
    //generate a random dataset and write it into "random_dataset.txt"
    public static void main(String[] args) {
        //customizable value
        int numberOfCities = 50; //currently not support over 500
        int numberOfSalesman = 5; 

        // Generate random numbers and add them to an array
        int[] randomXs = generateRandomNumbersArray(numberOfCities);
        int[] randomYs = generateRandomNumbersArray(numberOfCities);

        StringBuilder dataset = new StringBuilder();
        dataset.append(numberOfSalesman);
        for(int i = 0; i < numberOfCities; i++) {
            dataset.append("\n");
            dataset.append(i + 1).append(" ");
            dataset.append(randomXs[i]).append(" ");
            dataset.append(randomYs[i]);
        }
        System.out.println(dataset.toString());

        FileHelper.writeStringToFile(dataset.toString(), "datasets/random_dataset.txt");
    }

    /**
     * A random numbers function
     * @param n
     * @return
     */
    private static int[] generateRandomNumbersArray(int n) {
        int bound = 500;

        if(n > bound) {
            System.out.println("Out of bound!");
            return null;
        }
        
        Random random = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        int[] differentRandomNumbers = new int[n];

        // Generate different random numbers until the desired count is reached
        for (int i = 0; i < n; ) {
            int randomNumber = random.nextInt(bound); // Adjust the range as needed

            // Check if the generated number is unique
            if (uniqueNumbers.add(randomNumber)) {
                differentRandomNumbers[i] = randomNumber;
                i++;
            }
        }

        return differentRandomNumbers;
    }
}