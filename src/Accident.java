import java.util.Date;

public class Accident {

    private Date date;
    private Breakable[] involvedCars;
    //private Street street;

    public Accident(Date date, Breakable[] involvedCars) {
        setDate(date);
        setInvovledCars(involvedCars);
    }

    private void setDate(Date date){
        Date startOfSimDate = new Date(15000000);
        //TODO: make a static final class with needed values
        if (date.before(startOfSimDate)){
            throw new IllegalArgumentException("Date of Accident before t zero");
        }
        else this.date = date;
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

    public int getTimeToFix(){
        int max = 0;
        for (Breakable car : involvedCars){
            if (car.getTimeToFix() > max) max = car.getTimeToFix();
        }
        return max;
    }
}
