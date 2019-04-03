package pl.gdansk.niedzwiednik.kuba;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final PageParser pageParser = new PageParser();
        final List<StationInfo> stations = pageParser.parse("https://rowermevo.pl/mapa-stacji/");
        final List<Station> myStations = Arrays.asList(
                new Station(11726277, 11342),
                new Station(10737132, 11027),
                new Station(10753426, 11158),
                new Station(10754708, 11207),
                new Station(10751681, 11123),
                new Station(10827350, 11367),
                new Station(10735791, 11001),
                new Station(10754730, 11209),
                new Station(10826889, 11332),
                new Station(10754087, 11201),
                new Station(10827321, 11363),
                new Station(10754720, 11208),
                new Station(10827334, 11365, "Galeria Bałtycka")
        );
        final List<StationInfo> availableBikes = stations.stream()
                //.filter(stationIṇfo -> stationIsOnTheList(stationIṇfo, myStations))
                //.filter(stationInfo -> stationInfo.getAvailableBikesNumber() > 1)
                .collect(Collectors.toList());
        System.out.println("availableBikes = " + availableBikes);
        int totalAvailableBikes = totalAvailableBikes(availableBikes);
        System.out.println("totalAvailableBikes = " + totalAvailableBikes);
        long totalStations = totalStations(availableBikes);
        System.out.println("totalStations = " + totalStations);
        int totalReservedBikes = totalReservedBikes(availableBikes);
        System.out.println("totalReservedBikes = " + totalReservedBikes);
    }

    private static boolean stationIsOnTheList(final StationInfo stationInfo, final List<Station> filteredStations) {
        return filteredStations.stream()
                .anyMatch(station -> station.getNumber() == stationInfo.getStationNumber());
    }

    private static int totalAvailableBikes(final List<StationInfo> availableBikes){
        return availableBikes.stream()
                .mapToInt(StationInfo::getAvailableBikesNumber)
                .sum();
    }

    private static int totalReservedBikes(final List<StationInfo> availableBikes){
        return availableBikes.stream()
                .mapToInt(StationInfo::getReservedBikesNumber)
                .sum();
    }

    private static long totalStations(final List<StationInfo> availableBikes){
        return availableBikes.stream()
                .count();
    }
}
