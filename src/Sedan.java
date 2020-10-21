import java.util.Date;

public class Sedan extends Vehicle implements Breakable {

    private final int TIME_TO_FIX = 15; //in minutes
    private boolean broken;
    private boolean accident;

    public Sedan(double vehicleSize){
        super(vehicleSize);
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


    public void fix() { this.broken = false; this.accident = false; }

}
