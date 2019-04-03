package pl.gdansk.niedzwiednik.kuba;

import lombok.Data;

@Data
public class StationInfo {

    private final int stationNumber;
    private final int availableBikesNumber;
    private final String reservedBikes;
    private final int reservedBikesNumber;
}
