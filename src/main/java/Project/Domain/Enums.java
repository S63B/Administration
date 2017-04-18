package Project.Domain;

/**
 * Created by arjan on 28-3-2017.
 */
public class Enums {
    public enum Hardware {
        i3,
        i5,
        i7
    }

    public enum IdentificationMethod {
        gpsV1,
        gpsV2
    }

    public enum Status {
        open,
        closed,
        canceled
    }

    /**
     * https://www.energielabel.nl/autos
     * A is best rating
     */
    public enum EnergyLabel {
        A,
        B,
        C,
        D,
        E,
        F,
        G
    }
}
