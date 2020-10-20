import java.util.Date;

public class Sedan extends Vehicle implements Breakable {

    private final int TIME_TO_FIX = 15; //in minutes
    private boolean broken;
    private boolean accident;
    private int capacity;

    public Sedan(double vehicleSize){
        super(vehicleSize);
        capacity = 4; //Should make this attr. in vehicle.
    }

    public boolean isBroken(){ return broken; }
    public boolean isInAccident(){ return accident; }

    @Override
    public void collide(Breakable car, Date time) {

    }

    @Override
    public void _break(Date time) {

    }

    public int getTimeToFix(){ return TIME_TO_FIX; }
    public int getCapacity() { return capacity; }


    public void fixed() { this.broken = false; this.accident = false; }

}
