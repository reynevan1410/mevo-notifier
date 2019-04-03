package pl.gdansk.niedzwiednik.kuba;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Station {

    private final int uid;
    private final int number;
    private final String description;

    public Station(final int uid, final int number) {
        this(uid, number, "");
    }
}
