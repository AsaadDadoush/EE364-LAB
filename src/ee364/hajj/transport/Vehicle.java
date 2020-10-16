package ee364.hajj.transport;

public abstract class Vehicle {

    private double vehicleSize;
    private boolean govtCar;
    private int capacity;

    public Vehicle(double vehicleSize, boolean govtCar, int capacity){
        this.vehicleSize = vehicleSize;
        this.govtCar = govtCar;
        setCapacity(capacity);
    }
    public double getVehicleSize() {
        return vehicleSize;
    }

    public boolean isGovtCar() {
        return govtCar;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) throws IllegalArgumentException {
        if (capacity > 0 && capacity < 50){
            this.capacity = capacity;
        }
        else throw new IllegalArgumentException(String.format("Invalid vehicle capacity %d", capacity));
    }
}
