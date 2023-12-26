
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    /**
     * Simply write a content and save it to a .txt file
     * @param content
     * @param filePath
     */
    public static void writeStringToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the content to the file
            writer.write(content);
            System.out.println("File has been written!");
        } catch (IOException e) {
            // Handle IOException, e.g., file not found or permission issues
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Reads data from a file, initializes City objects, and updates CitiesManager.
     * @param filePath: The path to the file containing salesmen and cities data.
     * @return: The number of salesmen as specified in the file.
     */
    public static int importCitiesAndNumOfSalesman(String filePath) {
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
}
