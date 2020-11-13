
public class Sedan extends CivilVehicle {

    private final int TIME_TO_FIX = 15; //in minutes
    public static final int MAX_FORWARD = 1500; // Meter/Min

    public Sedan(double vehicleSize){
        super(vehicleSize);
    }

    public int getTimeToFix(){ return TIME_TO_FIX; }


}
