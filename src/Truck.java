
public class Truck extends CivilVehicle {
	private String UID;
	private static int numeberOfTruck;
    private final int TIME_TO_FIX = 20; //in minutes
    public Truck(double vehicleSize){
        super(vehicleSize);
        generateUID();
    }

    public int getTimeToFix(){
    	return TIME_TO_FIX; 
    }
    private void generateUID() {
    	numeberOfTruck++;
    	this.UID = String.format("Truck%04d", numeberOfTruck);
    }
    		  
    	    public String getUID(){
    	        return this.UID;
    	    }
    	}


