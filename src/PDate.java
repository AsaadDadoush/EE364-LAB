import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class to manage and track timeline of Hajj-Simulation
 */
public class PDate extends Calendar {

    public static final Calendar startCalendar = new GregorianCalendar(2020,Calendar.JANUARY,15,13,0,0);
    public static final Calendar endCalendar= new GregorianCalendar(2020,Calendar.JANUARY,16,20,0,0);
    private static final Calendar currentCalendar = (GregorianCalendar)startCalendar.clone();
    private static boolean ended;

    public static Calendar getStartCalendar() {
        return startCalendar;
    }

    public static Calendar getEndCalendar() {
        return endCalendar;
    }

    public static Calendar getCurrentCalendar() {
        return currentCalendar;
    }

    public static Date getStartTime() {
        return startCalendar.getTime();
    }

    public static Date getEndTime(){
        return endCalendar.getTime();
    }

    public static Date getCurrentTime() {
        return currentCalendar.getTime();
    }

    public static void step(int key, int value){
        ended = false;
        Calendar dummy = (GregorianCalendar)currentCalendar.clone();
        dummy.add(key, value);
        if (dummy.before(endCalendar)){
            currentCalendar.add(key, value);
        }
        else ended = true;
    }

    /**
    Check if time/date provided falls within the simulation timeline
     */
    public static boolean isValidTime(Date timeToLeaveToDest) {
        if (timeToLeaveToDest.after(PDate.endCalendar.getTime()) ||
                timeToLeaveToDest.before(PDate.startCalendar.getTime()))
            return false;
        else
            return true;
    }

    public static boolean isEnded(){
        return ended;
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
