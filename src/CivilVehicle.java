import java.util.Date;

public abstract class  CivilVehicle extends Vehicle implements Breakable {

    private boolean broken;
    private boolean inAccident;
    private Accident currentAccident;
    private Date breakDownTime;

    public CivilVehicle(double vehicleSize) {
        super(vehicleSize);
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
    public Accident collide(Breakable car, Date time) {
        if (currentAccident == null) {
            Breakable[] cars = new Breakable[2];
            cars[0] = this;
            cars[1] = car;
            Accident accident = new Accident(time, cars);
            this.currentAccident = accident;
            this.inAccident = true;
            return accident;
        }
        return null;
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
        breakDownTime = null;
    }

    public Accident getCurrentAccident() {
        return currentAccident;
    }

    public Date getBreakDownTime() {
        return breakDownTime;
    }
}
