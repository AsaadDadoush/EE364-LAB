public class TrafficPoliceCar extends Vehicle implements CanBeGovtCar {

    private final double ADDED_EFFICIENCY = 0.05; // 5%
    private int govtID;

    /**
     * construct instance with random GovtID
     * @param vehicleSize Length of vehicle in meters
     */
    public TrafficPoliceCar(double vehicleSize){
        super(vehicleSize);
        //TODO: Set random govtID
    }

    /**
     * Construct instance with given GovtID
     * @param vehicleSize Length of vehicle in meters
     * @param govtID Provided Govt ID
     */
    public TrafficPoliceCar(double vehicleSize, int govtID) {
        super(vehicleSize);
        this.govtID = govtID;
    }

    @Override
    public int getGovtID() {
        return govtID;
    }
}
