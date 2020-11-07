import java.util.Date;

public abstract class CivilVehicle extends Vehicle implements Breakable {

    private boolean broken;
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
    public Accident collide(Breakable car, Date time, Street location) {
        if (currentAccident == null) {
            Breakable[] cars = new Breakable[2];
            cars[0] = this;
            cars[1] = car;
            Accident accident = new Accident(time, cars, location);
            this.setCurrentAccident(accident);
            if (car instanceof CivilVehicle)
                ((CivilVehicle)car).setCurrentAccident(accident);
            return accident;
        }
        return null;
    }

    @Override
    public void _break(Date time) {
        broken = true;
        this.breakDownTime = time;
    }

    @Override
    public void fix() {
        broken = false;
        this.currentAccident = null;
    }

    public Date getBreakDownTime() {
        return breakDownTime;
    }

    public Accident getCurrentAccident() {
        return currentAccident;
    }

    public void setCurrentAccident(Accident accident){
        if (accident != null) {
            this.currentAccident = accident;
            this._break(accident.getDate());
        }
    }
}
