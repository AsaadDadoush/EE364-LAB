import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class to manage and track timeline of Hajj-Simulation
 */
public class PDate extends Calendar {

    /*
    Rewrite this class to use it's own members. Extending calendar is pointless like this.
    - H.B.
     */
    public final Calendar startCalendar;
    public final Calendar endCalendar;
    private final Calendar currentCalendar;
    private boolean ended;

    public PDate(GregorianCalendar start, GregorianCalendar end) {
        this.startCalendar = start;
        this.endCalendar = end;
        this.currentCalendar = (GregorianCalendar)start.clone();
    }

    public Calendar getStartCalendar() {
        return startCalendar;
    }

    public Calendar getEndCalendar() {
        return endCalendar;
    }

    public Calendar getCurrentCalendar() {
        return currentCalendar;
    }

    public Date getStartTime() {
        return startCalendar.getTime();
    }

    public Date getEndTime(){
        return endCalendar.getTime();
    }

    public Date getCurrentTime() {
        return currentCalendar.getTime();
    }

    public void step(int key, int value){
        ended = false;
        Calendar dummy = (GregorianCalendar)currentCalendar.clone();
        dummy.add(key, value);
        if (dummy.before(endCalendar)){
            currentCalendar.add(key, value);
        }
        else ended = true;
    }

    public boolean isEnded(){
        return ended;
    }

    /**
    Check if time/date provided falls within the simulation timeline
     */
    public boolean isWithInTimeline(Date time) {
        if (time.after(this.endCalendar.getTime()) ||
                time.before(this.startCalendar.getTime()))
            return false;
        else
            return true;
    }

    /**
     Check if time/date provided falls within the simulation timeline
     with reference to a PDate instance.
     */
    public static boolean isWithInTimeline(Date time, PDate timeManeger) {
        return time.after(timeManeger.getStartTime()) &&
                time.before(timeManeger.getEndTime());
    }

    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int i, int i1) {

    }

    @Override
    public void roll(int i, boolean b) {

    }

    @Override
    public int getMinimum(int i) {
        return 0;
    }

    @Override
    public int getMaximum(int i) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int i) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int i) {
        return 0;
    }
}

class OutOfSimulationTimeException extends Exception {

}
