import java.util.ArrayList;

public class Street {

    private double length;
    private int numberOfLanes;
    private ArrayList<Vehicle> vehicles;
  

    public Street(double length, int numberOfLanes) {
        setLength(length);
        setNumberOfLanes(numberOfLanes);
    }

    public Street(double length, int numberOfLanes, ArrayList<Vehicle> vehicles) {
        this(length, numberOfLanes);
        this.vehicles = vehicles;
    }

    private void setLength(double length) {
        if (length >= 0) this.length = length;
        else throw new IllegalArgumentException("Can not make a negative length street");
    }

    private void setNumberOfLanes(int numberOfLanes) {
        if (numberOfLanes >= 1) this.numberOfLanes = numberOfLanes;
        else throw new IllegalArgumentException("Street can not have less then 1 lane");
    }

    public double getLength() {
        return length;
    }

    public int getNumberOfLanes() {
        return numberOfLanes;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public double capcity() {
        double totalLength =  length * numberOfLanes;
        //TODO Ammar return (total length - (length of cars + padding))
        double totalLenthofCar=0;
        for(int i=0;i<vehicles.size();i++) {
            totalLenthofCar+=vehicles.get(i).getVehicleSize();
        }
        return totalLength -(totalLenthofCar + 0.5);
    }
    
    public boolean canTakeVehicles( Vehicle vehicle ) {
    	if ( vehicle.getVehicleSize() > capcity() )
    		return false;
    	else
    		return true;
    }

    public void addVehicle( Vehicle vehicle ) {
        if(capcity()>0) {
            for(int i=0;i<getVehicles().size();i++) {
                addVehicle.set(i, getVehicles().get(i));
            }
        }
        //TODO Ammar i hope that
    }
  
}
