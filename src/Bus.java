
public class Bus extends CivilVehicle {

    private final int TIME_TO_FIX = 20; //in minutes

    public Bus(double vehicleSize) {
        super(vehicleSize);
    }

    @Override
    public int getTimeToFix() {
        return TIME_TO_FIX;
    }
}
