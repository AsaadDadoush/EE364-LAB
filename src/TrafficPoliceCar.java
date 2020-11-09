public class TrafficPoliceCar extends Vehicle implements CanBeGovtCar, CanFixAccident {

    private final double ADDED_EFFICIENCY = 0.05; // 5%
    private String TPC_UID;
    private static int numeberOfTPC;

    /**
     * construct instance with random GovtID
     * @param vehicleSize Length of vehicle in meters
     */
    public TrafficPoliceCar(double vehicleSize) {
        super(vehicleSize);
        generateGovtID();
    }

    @Override
    public void generateGovtID(){
    	numeberOfTPC++;
    	this.TPC_UID = String.format("TPC%03d", numeberOfTPC);
    }
    
    @Override
    public String getGovtID() {
    	return this.TPC_UID;
    }
}
