
public class Bus extends CivilVehicle {
	
	private String UID;
	private static int numeberOfBuses;
    private final int TIME_TO_FIX = 20; //in minutes

    public Bus(double vehicleSize) {
        super(vehicleSize);
        generateUID();
    }

    @Override
    public int getTimeToFix() {
        return TIME_TO_FIX;
    }
    
    private void generateUID() {
        numeberOfBuses++;
        this.UID = String.format("BUS%04d", numeberOfBuses);
    }

    public String getUID(){
        return this.UID;
    }
}
