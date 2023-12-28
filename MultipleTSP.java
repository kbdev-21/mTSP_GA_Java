import java.util.ArrayList;
import java.util.List;

public class MultipleTSP {
    /**
     * Split for mTSP
     * @param tspResult: a Tour get after solving TSP problem
     * @param numberOfSalesmans: number of salesman, which defined in the input
     * @return: a List<Tour> with each tour for each salesman
     */
    public static ArrayList<Tour> splitToursForEachSalesman(Tour tspResult, String datasetPath) {
        int numberOfSalesmans = FileHelper.importNumberOfSalesman(datasetPath);

        ArrayList<ArrayList<City>> toursArray = new ArrayList<>();
        for (int i = 0; i < numberOfSalesmans; i++) {
            toursArray.add(new ArrayList<>());
        }

        int currentSalesman = 0;
        int count = 0;

        for (int i = 0; i < tspResult.tourSize(); i++) {
            toursArray.get(currentSalesman).add(tspResult.getCity(i));
            count++;

            if (count == tspResult.tourSize() / numberOfSalesmans) {
                count = 0;
                currentSalesman++;

                if (currentSalesman > numberOfSalesmans - 1) {
                    currentSalesman = numberOfSalesmans - 1;
                }
            }
        }

        City depot = CitiesManager.centerCity();
        for (List<City> tour : toursArray) {
            if (!tour.contains(depot)) {
                tour.add(0, depot);
            }
        }

        ArrayList<Tour> mtspTours = new ArrayList<>();

        for (int i = 0; i < numberOfSalesmans; i++) {
            ArrayList<City> currentCities = toursArray.get(i);
            Tour currentTour = new Tour(currentCities);

            for (int j = 0; j < tspResult.tourSize(); j++) {
                Tour cloneTour = new Tour(currentTour.getTour());
                currentTour.removeIntersections();
                if(currentTour.getDistance() > cloneTour.getDistance()) {
                    currentTour.setTour(cloneTour.getTour());
                }
            }

            mtspTours.add(currentTour);
        }

        return mtspTours;
    }
}
