import java.util.Date;

public class Sedan extends CivilVehicle {

    private final int TIME_TO_FIX = 15; //in minutes

    public Sedan(double vehicleSize){
        super(vehicleSize);
    }

    public int getTimeToFix(){ return TIME_TO_FIX; }


}
