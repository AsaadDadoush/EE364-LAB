import java.util.Date;

public class Campaign {

    private int UID;
    private int numberOfPeople;
    private int housingNumber;
    private String name;
    private boolean local;

    private Route housingToDestRoute;
    private Route destToHousingRoute;

    private Vehicle[] vehicles;

    //Will be of type PDate after extention
    private Date timeToLeaveToDest;
    private Date timeToLeaveToHousing;

    public Campaign(int numberOfPeople, String name, Route housingToDestRoute, Route destToHousingRoute) {
        setNumberOfPeople(numberOfPeople);
        this.name = name;
        this.housingToDestRoute = housingToDestRoute;
        this.destToHousingRoute = destToHousingRoute;
    }

    public Campaign(int numberOfPeople){
        setNumberOfPeople(numberOfPeople);
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople > 0) this.numberOfPeople = numberOfPeople;
        else throw new IllegalArgumentException("Negative number of people in camp");
    }

    public Route getHousingToDestRoute() {
        return housingToDestRoute;
    }

    public void setHousingToDestRoute(Route housingToDestRoute) {
        this.housingToDestRoute = housingToDestRoute;
    }

    public Route getDestToHousingRoute() {
        return destToHousingRoute;
    }

    public void setDestToHousingRoute(Route destToHousingRoute) {
        this.destToHousingRoute = destToHousingRoute;
    }

    public int getNumberOfBusses() {
        int busses = 0;
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof Bus) busses++;
        }
        return busses;
    }
}
