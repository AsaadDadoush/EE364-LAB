public abstract class Vehicle {

    private double vehicleSize;
    private Route route;
    private Street currentStreet;
    private double currentLocation;

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

    public Street getCurrentStreet() {
        return currentStreet;
    }

    public double getCurrentLocation() {
        return currentLocation;
    }

    //TODO: Manage movement and setStreet and location ons street.

    public void moveForward(double distance) {
        //TODO: Check if at end of street move to next street in Route.
        this.currentLocation += distance;
    }

    /**
     * @return Distance in meters to next car on same street. -1 if Can not calculate.
     */
    public double getDistanceToNextVehicle() {
        if (getCurrentStreet() != null) {
            int indexOfNxt = getCurrentStreet().getVehicles().indexOf(this) + 1; //next
            Vehicle nextVehicle = getCurrentStreet().getVehicles().get(indexOfNxt);
            return nextVehicle.getCurrentLocation() - this.getCurrentLocation();
        }
        return -1;//open or unlimited
    }
}
