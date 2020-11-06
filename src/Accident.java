import java.util.Date;

public class Accident {

    private Date date;
    private Breakable[] involvedCars;
    private Street location;

    public Accident(Date date, Breakable[] involvedCars, Street location) {
        setDate(date);
        setInvovledCars(involvedCars);
        this.location = location;
    }

    private void setDate(Date date){
        //TODO: maybe change to Calendar type
        this.date = date;
    }

    private void setInvovledCars(Breakable[] cars){
        if (cars.length > 1) this.involvedCars = cars;
        else throw new IllegalArgumentException("Can not make an accident with one car");
    }

    public Date getDate() {
        return date;
    }

    public Breakable[] getInvolvedCars() {
        return involvedCars;
    }

    public Street getLocation(){
        return this.location;
    }

    public int getTimeToFix(){
        int max = 0;
        for (Breakable car : involvedCars){
            if (car.getTimeToFix() > max) max = car.getTimeToFix();
        }
        return max;
    }
}
