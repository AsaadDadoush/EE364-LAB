import java.util.Date;
import java.util.HashMap;

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
    private Date timeStartedOnCurrentStreet;

    //Map Street to Minutes
    private HashMap<Street, Integer> routeTimeHistory = new HashMap<>();

    public abstract int getMaxSpeed();

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
        setTimeOfArrival(MakkahCity.getTimeMan().getCurrentTime());
        getCurrentStreet().getVehicles().remove(this);
        this.currentLocation = 0;
    }

    public void move(double distance) {
        if (!isMoving()) {
            setMoving(true);
            setTimeStartedMoving(MakkahCity.getTimeMan().getCurrentTime());
        }
        setCurrentLocation(getCurrentLocation() + distance);
        setTotalDistanceTraveled(getTotalDistanceTraveled() + distance);
    }

    public void setRoute(Route route) {
        this.route = route;
        this.arrivedToDest = false;
        this.routeTimeHistory.clear();
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
        if (this.currentStreet != null)
            this.currentStreet.addVehicle(this);
        this.timeStartedOnCurrentStreet = MakkahCity.getTimeMan().getCurrentTime();
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
    
    public void moveToNextStreet() {
        this.routeTimeHistory.put(this.currentStreet,
                (int) (MakkahCity.getTimeMan().getCurrentTime().getTime() - timeStartedOnCurrentStreet.getTime())/60000);
    	int nxtIndex = route.indexOf(this.getCurrentStreet()) + 1;
		if (nxtIndex <= route.getStreets().length - 1) {
			if (this.getRoute().getStreets()[nxtIndex].capcityPoint(0, 1000) < 1) {
			this.setCurrentStreet(route.getStreets()[nxtIndex]);
			this.setCurrentLocation(0);
			}
		}
		else
			this.arrive();
    }

    /**
     * Get value in minutes of time spent travling on street. If did not, return 0.
     * @param street
     * @return Time in minutes
     */
    public int getTimeOnStreet(Street street) {
        if (routeTimeHistory.containsKey(street)) {
            return routeTimeHistory.get(street);
        }
        return 0;
    }

    public boolean hasCrossedStreet(Street street) {
        return routeTimeHistory.containsKey(street);
    }

    public String toString() {
        return String.format("%s\nStreet: %s Location: %.1f\n" +
                        "Arrived: %s Starting time: %s Arrive Time: %s\n",
                super.toString(), this.getCurrentStreet().getName().name(),
                this.getCurrentLocation(), this.isArrivedToDest(),
                this.getTimeStartedMoving(), this.getTimeOfArrival());
    }
}


