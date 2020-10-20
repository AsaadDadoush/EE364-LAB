import java.util.Date;

public interface Breakable {

    int getTimeToFix();
    boolean isBroken();
    boolean isInAccident();
    void collide(Breakable car, Date time);
    void _break(Date time);

}
