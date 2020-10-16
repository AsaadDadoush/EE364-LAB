public class TrafficPoliceCar extends Vehicle implements CanFixAccident {

    public final double ADDED_EFFICIENCY = 0.05; // 5%
    //private Street str_location or name
    private static int totalTrafficCars;

    public TrafficPoliceCar(double vehicleSize, boolean govtCar){
        super(vehicleSize, govtCar, 2); //Capacity is irrelevant.
        totalTrafficCars++;
    }

    public static int getTotalTrafficCars() {
        return totalTrafficCars;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        totalTrafficCars--;
    }
}
