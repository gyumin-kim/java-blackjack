package fuel.domain;

public class Avante extends Car {

    private static final String MODEL_NAME = "Avante";
    private static final double DISTANCE_PER_LITER = 15.0;

    private final int distance;

    public Avante(final int distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public String getName() {
        return MODEL_NAME;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }
}