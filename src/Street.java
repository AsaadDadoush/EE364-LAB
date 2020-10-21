import java.util.ArrayList;

public class Street {

    private double length;
    private int numberOfLanes;
    private ArrayList<Vehicle> vehicles;

    public Street(double length, int numberOfLanes) {
        setLength(length);
        setNumberOfLanes(numberOfLanes);
    }

    public Street(double length, int numberOfLanes, ArrayList<Vehicle> vehicles) {
        this(length, numberOfLanes);
        this.vehicles = vehicles;
    }

    private void setLength(double length) {
        if (length >= 0) this.length = length;
        else throw new IllegalArgumentException("Can not make a negative length street");
    }

    private void setNumberOfLanes(int numberOfLanes) {
        if (numberOfLanes >= 1) this.numberOfLanes = numberOfLanes;
        else throw new IllegalArgumentException("Street can not have less then 1 lane");
    }

    public double getLength() {
        return length;
    }

    public int getNumberOfLanes() {
        return numberOfLanes;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
