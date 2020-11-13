import java.util.Date;

public abstract class Vehicle {

    private double vehicleSize;
    private Route route;
    private Street currentStreet;
    private double currentLocation;
    private double totalDistanceTraveled;
    private boolean arrivedToDest;
    private boolean moving;
    private Date timeStartedMoving;
    private Date timeOfArrival;

    public Vehicle(double vehicleSize){
        setVehicleSize(vehicleSize);
    }

    public double getVehicleSize() {
        return vehicleSize;
    }

    public Route getRoute() {
        return route;
    }

    public void arrive() {
        setArrivedToDest(true);
        setMoving(false);
        setTimeOfArrival(MakkahCity.getTimeManager().getCurrentTime());
        getCurrentStreet().getVehicles().remove(this);
    }

    public void move(double distance) {
        if (!isMoving()) {
            setMoving(true);
            setTimeStartedMoving(MakkahCity.getTimeManager().getCurrentTime());
        }
        setCurrentLocation(getCurrentLocation() + distance);
        setTotalDistanceTraveled(getTotalDistanceTraveled() + distance);
    }

    public void setRoute(Route route) {
        this.route = route;
        //this.currentStreet = route.getStreets()[0];
        //this.route.getStreets()[0].addVehicle(this);
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

    /**
     * @return Distance in meters to next car on same street. -1 if Can not calculate.
     */
    public double getDistanceToNextVehicle() {
        if (getCurrentStreet() != null) {
            int indexOfNxt = getCurrentStreet().getVehicles().indexOf(this) - 1; //next
            //Index may be out of bound if this car is the only or first (end if street)
            try {
                Vehicle nextVehicle = getCurrentStreet().getVehicles().get(indexOfNxt);
                return nextVehicle.getCurrentLocation() - this.getCurrentLocation();
            }
            catch (IndexOutOfBoundsException e) {return -1;}
        }
        return -1;//open or unlimited
    }

    public void setCurrentStreet(Street currentStreet) {
        if (this.currentStreet != null) {
            this.currentStreet.getVehicles().remove(this);
        }
        this.currentStreet = currentStreet;
        this.currentStreet.addVehicle(this);
    }

    public void setCurrentLocation(double currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }

    public void setTotalDistanceTraveled(double totalDistanceTraveled) {
        this.totalDistanceTraveled = totalDistanceTraveled;
    }

    public boolean isArrivedToDest() {
        return arrivedToDest;
    }

    public void setArrivedToDest(boolean arrivedToDest) {
        this.arrivedToDest = arrivedToDest;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Date getTimeStartedMoving() {
        return timeStartedMoving;
    }

    public void setTimeStartedMoving(Date timeStartedMoving) {
        this.timeStartedMoving = timeStartedMoving;
    }

    public Date getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Date timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }
}
