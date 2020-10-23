import java.util.Calendar;
import java.util.Date;

/**
 * Class to manage and track timeline of Hajj-Simulation
 */
public class PDate extends Calendar {

    private final Calendar startCalendar = Calendar.getInstance();
    private final Calendar endCalendar= Calendar.getInstance();
    private final Calendar currentCalendar = Calendar.getInstance();
    private boolean ended;

    public PDate(){
        startCalendar.set(2020,0,15,13,0,0);
        endCalendar.set(2020,0,15,20,0,0);
        currentCalendar.setTime(startCalendar.getTime());
    }

    public PDate(Calendar startCalendar, Calendar endCalendar){
        this.startCalendar.setTime(startCalendar.getTime());
        this.endCalendar.setTime(endCalendar.getTime());
        currentCalendar.setTime(startCalendar.getTime());
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
        if (currentCalendar.before(endCalendar)){
            currentCalendar.add(key, value);
        }
        else if (currentCalendar.after(endCalendar) || currentCalendar.equals(endCalendar)) {
            ended = true;
        }

    }

    public boolean isEnded(){
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
