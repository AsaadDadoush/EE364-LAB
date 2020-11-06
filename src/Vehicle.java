public abstract class Vehicle {

    private double vehicleSize;
    private Route route;

    public Vehicle(double vehicleSize){
        setVehicleSize(vehicleSize);
    }
    public double getVehicleSize() {
        return vehicleSize;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private void setVehicleSize(double vehicleSize) {
        if (vehicleSize <= 0) throw new IllegalArgumentException("Vehicle can not be negative in length!");
        else this.vehicleSize = vehicleSize;
    }
}
