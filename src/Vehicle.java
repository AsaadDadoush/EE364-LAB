public abstract class Vehicle {

    private double vehicleSize;

    public Vehicle(double vehicleSize){
        setVehicleSize(vehicleSize);
    }
    public double getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(double vehicleSize) {
        if (vehicleSize <= 0) throw new IllegalArgumentException("Vehicle can not be negative in length!");
        else this.vehicleSize = vehicleSize;
    }
}
