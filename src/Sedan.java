
public class Sedan extends CivilVehicle {

	private String UID;
	private static int numeberOfSedan;
    private final int TIME_TO_FIX = 15; //in minutes
    public static final int MAX_FORWARD = 1500; // Meter/Min

    public Sedan(double vehicleSize){
        super(vehicleSize);
        generateUID();
    }
    @Override
    public int getTimeToFix(){ return TIME_TO_FIX; 
    
    }
    
    private void generateUID() {
    	numeberOfSedan++;
    	this.UID = String.format("Sedan%04d", numeberOfSedan);
    	
    }
    
    public String getUID(){
        return this.UID;
    }
    


}
