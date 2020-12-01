import java.util.Date;

/**
 * Class to be used in Hajj-sim. Overrides toString()
 * Incomplete.
 */
class HijriDate extends Date {

    public HijriDate(long timeInMillis) {
        super(timeInMillis);
    }

    @Override
    public String toString(){
        return super.toString().replaceAll("Jan","Dhu'l-Hijja")
                .replaceAll("AST", "");
    }
}
