import java.util.Date;

public abstract class Vehicle {

    private double vehicleSize;
    private Route route;
    private Street currentStreet;
    private double currentLocation;
    private int currentStreetIndex;
    private boolean arrivedToDest;
    private boolean moving;
    private Date timeStartedMoving;

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
        this.currentStreetIndex = 0;
        this.currentStreet = route.getStreets()[0];
        this.route.getStreets()[0].addVehicle(this);//TODO: [9]street might not add if capacity is low
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

    public void moveForward(double distance) {
        if (!moving && !arrivedToDest) moving = true;
        if (!arrivedToDest){
            if (isAtEndOfCurrentStreet()) {
                moveToNextStreet();
                //this.currentLocation += distance;
            }
            else {
                double dToNext = getDistanceToNextVehicle();
                double trafficFactor = getCurrentStreet().getPercentRemainingCapacity() / 100.0;
                if (dToNext == -1 ) {
                    this.currentLocation += distance * trafficFactor;
                } else if (dToNext < distance){//TODO after I wake up
                     this.currentLocation += distance;
                }

            }
        }
    }

    private boolean isAtEndOfCurrentStreet() {
        //At last meter of current street
        return (this.currentStreet.getLength() - this.currentLocation) < 1;
    }

    private void moveToNextStreet() {
        this.currentStreetIndex++;
        Street nextStreet;
        try { nextStreet = this.getRoute().getStreets()[currentStreetIndex]; }
        catch (IndexOutOfBoundsException e) { this.arrivedToDest = true; this.moving = false; return;}
        if (nextStreet.canTakeVehicles(this)){
            this.currentStreet = nextStreet;
            this.currentLocation = 0;
        }
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
}
