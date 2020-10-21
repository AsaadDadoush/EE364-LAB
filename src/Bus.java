import java.util.Date;

public class Bus extends Vehicle implements Breakable {

    private final int TIME_TO_FIX = 20; //in minutes
    private boolean broken;
    private boolean inAccident;
    private Date breakDownTime;//TODO: PDate

    public Bus(double vehicleSize) {
        super(vehicleSize);
    }

    @Override
    public int getTimeToFix() {
        return TIME_TO_FIX;
    }

    @Override
    public boolean isBroken() {
        return broken;
    }

    @Override
    public boolean isInAccident() {
        return inAccident;
    }

    @Override
    public void collide(Breakable car, Date time) {
        //Make Accident here and change return type?
        // or make reference as property
    }

    @Override
    public void _break(Date time) {
        broken = true;
        breakDownTime = time;
    }

    @Override
    public void fix() {
        broken = false;
        inAccident = false;
    }
}
