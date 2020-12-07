import java.util.Date;

public interface Breakable {

    int getTimeToFix();
    boolean isBroken();
    Accident collide(Breakable car, Date time, Street location);
    void _break(Date time);
    void fix();

}
