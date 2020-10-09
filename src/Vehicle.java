public abstract class Vehicle {

    private double vehicleSize;
    private boolean govtCar;

    public Vehicle(double vehicleSize, boolean govtCar){
        this.vehicleSize = vehicleSize;
        this.govtCar = govtCar;
    }
    public double getVehicleSize() {
        return vehicleSize;
    }

    public boolean isGovtCar() {
        return govtCar;
    }
}
