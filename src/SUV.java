
public class SUV extends CivilVehicle {
	private String UID;
	private static int numeberOfSUV;
    private final int TIME_TO_FIX = 15; //in minutes
    public static final int MAX_FORWARD = 1300;

    public SUV(double vehicleSize){
        super(vehicleSize);
        generateUID();
    }

    public int getTimeToFix(){
    	return TIME_TO_FIX; 
    }
    
    private void generateUID() {
    	numeberOfSUV++;
    	this.UID = String.format("SUV%04d", numeberOfSUV);
    	
    }
    
    public String getUID(){
        return this.UID;
    }
    


}
