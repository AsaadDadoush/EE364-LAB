
public class Truck extends CivilVehicle {

    private final int TIME_TO_FIX = 20; //in minutes

    public Truck(double vehicleSize){
        super(vehicleSize);
    }

    public int getTimeToFix(){
    	return TIME_TO_FIX; 
    }


}
