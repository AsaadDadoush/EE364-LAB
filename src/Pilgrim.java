public class Pilgrim {

    //keep track of all pilgrim object count.
    private static int totalPilgrims;

    private String passport;
    private boolean local;
    private int age;

    private Gender gender;
    private Priority priority;

    public Pilgrim(String passport, int age, Gender gender, Priority priority, boolean local) throws Exception {
        setAge(age);
        setPassport(passport);
        this.gender = gender;
        this.priority = priority;
        totalPilgrims++; //Added a pilgrim
    }
    public Priority getPriority() { return priority; }

    private void setAge(int age) throws IllegalArgumentException {
        if (age < 7 || age > 85) throw new IllegalArgumentException("Age out of permitted range");
        else this.age = age;
    }

    private void setPassport(String passport) throws IllegalArgumentException{
        if (passport.length() == 8) //assuming standard passport number lengths
            this.passport = passport;
        else throw new IllegalArgumentException("Invalid passport format");
        //TODO:Make exception classes
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        //Decrement the count of total objects when GC cleans unneeded.
        totalPilgrims--;
    }
}
