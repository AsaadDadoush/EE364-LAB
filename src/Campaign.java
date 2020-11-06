import java.util.Date;
..
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

    public Campaign(District hotelDistrict, int numberofBusses) {
        this.hotelDistrict = hotelDistrict;
        generateBusses(numberofBusses);
        generateUID();
    }

    public Campaign(District hotelDistrict, Vehicle[] vehicles) {
        this.hotelDistrict = hotelDistrict;
        setVehicles(vehicles);
        generateUID();
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

    public District getHotelDistrict(){ return this.hotelDistrict; }

    public Date getTimeToLeaveToDest() {
        return timeToLeaveToDest;
    }

    public void setTimeToLeaveToDest(Date timeToLeaveToDest) {
        //TODO: check if date is before or after Project Date
        this.timeToLeaveToDest = timeToLeaveToDest;
    }

    public Date getTimeToLeaveToHousing() {
        return timeToLeaveToHousing;
    }

    public void setTimeToLeaveToHousing(Date timeToLeaveToHousing) {
        //TODO: check if date is before or after Project Date
        this.timeToLeaveToHousing = timeToLeaveToHousing;
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
        /*TODO: check if vehicles is not null then set vehicles. */
    }

    public void generateBusses(int number){
        /*TODO: generate "number"  of busses and set them to vehicles array. */
    }

    private void generateUID() {
        numeberOfCampains++;
        this.UID = String.format("%04d", numeberOfCampains);
    }

    public String getUID(){
        return this.UID;
    }
}
