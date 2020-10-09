public class Pilgrim {

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
    }
    public Priority getPriority() {
        return priority;
    }

    private void setAge(int age) throws Exception {
        if (age < 7 || age > 85) throw new Exception("Age out of permitted range");
        else this.age = age;
    }

    private void setPassport(String passport) throws Exception {
        if (passport.length() == 8) //assuming standard passport number lengths
            this.passport = passport;
        else throw new Exception("Invalid passport format");
        //TODO:Make exception classes
    }
}
