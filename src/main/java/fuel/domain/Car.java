package fuel.domain;

public abstract class Car {

    abstract double getDistancePerLiter();

    abstract String getName();

    abstract double getDistance();

    double getChargeQuantity() {
        return getDistance() / getDistancePerLiter();
    }
}
