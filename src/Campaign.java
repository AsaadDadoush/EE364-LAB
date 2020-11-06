import java.util.Date;

public class Campaign {

    private String UID;
    //private int housingNumber;
    private String name;
    private District hotelDistrict;

    private Route housingToDestRoute;
    private Route destToHousingRoute;

    private Vehicle[] vehicles;

    //Will be of type PDate after extention
    private Date timeToLeaveToDest;
    private Date timeToLeaveToHousing;

    private static int numeberOfCampains;

    public Campaign(String name, District hotelDistrict) {
        numeberOfCampains++;
        this.name = name;
        this.hotelDistrict = hotelDistrict;
        this.UID = String.format("%4d", numeberOfCampains);
    }

    public Campaign(int numberOfPeople){

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

    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    public void setVehicles(Vehicle[] vehicles){
        /*TODO
        check if vehicles is not null then set vehicles.
         */
    }

    public void generateBusses(int number){
        /*TODO
        generate "number"  of busses and set them to vehicles array.
         */
    }
}
