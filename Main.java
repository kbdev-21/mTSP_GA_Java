import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        //choose a dataset
        String datasetPath = "datasets/random_dataset.txt";

        //start recording time
        long startTime = System.currentTimeMillis();

        //run TSP algorithm
        Tour tspTour = HybridGA.solveTSP(datasetPath);

        //run split for mTSP algorithm
        ArrayList<Tour> mtspTours = MultipleTSP.splitToursForEachSalesman(tspTour, datasetPath); 

        //end recording time and show running time of the algorithm:
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("Finish! Running time: " + runningTime + " milliseconds");

        //save results to .txt files for visualization
        FileHelper.saveResults(tspTour, mtspTours);
    }
}
