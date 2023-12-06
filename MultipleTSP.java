import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MultipleTSP {

    public static List<Tour> splitToursForEachSalesman(Tour tspResult, int numberOfSalesmans) {
        List<List<City>> toursArray = new ArrayList<>();
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

        List<Tour> mtspTours = new ArrayList<>();

        for (int i = 0; i < numberOfSalesmans; i++) {
            List<City> currentCities = toursArray.get(i);
            Tour currentTour = new Tour(currentCities);

            for (int j = 0; j < 2 * tspResult.tourSize() / numberOfSalesmans; j++) {
                currentTour.removeIntersections();
            }

            mtspTours.add(currentTour);
        }

        return mtspTours;
    }

    // public static void plotMultipleTSPTours(List<Tour> mtspTours) {
    //     Plotter plotter = new Plotter();
    //     plotter.plotMultipleTSPTours(mtspTours);
    // }

    // public static class Plotter {
    //     public void plotMultipleTSPTours(List<Tour> mtspTours) {
    //         Plotter plotter = new Plotter();
    //         plotter.plotMultipleTSPTours(mtspTours);
    //     }
    // }
}
