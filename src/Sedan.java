import java.util.Date;

public class Sedan extends Vehicle implements Breakable {

    private final int TIME_TO_FIX = 15; //in minutes
    private boolean broken;
    private boolean accident;

    public Sedan(double vehicleSize, boolean govtCar){
        super(vehicleSize, govtCar, 4);
    }

    public boolean isBroken(){ return broken; }
    public boolean isInAccident(){ return accident; }
    public void _break(Date time) { this.broken = true; } //TODO
    public void collide(Breakable car, Date time) { this.accident = true; }//TODO //Maybe add time of accident and other breakable args
    public int getTimeToFix(){ return TIME_TO_FIX; }


    public void fixed() { this.broken = false; this.accident = false; }

}
