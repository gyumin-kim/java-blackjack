package fuel.domain;

public class K5 extends Car {

    private static final String MODEL_NAME = "K5";
    private static final double DISTANCE_PER_LITER = 13.0;

    private final int distance;

    public K5(final int distance) {
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