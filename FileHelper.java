
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
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
}
