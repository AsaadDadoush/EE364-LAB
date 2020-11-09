public class TrafficPoliceCar extends Vehicle implements CanBeGovtCar, CanFixAccident {

    private final double ADDED_EFFICIENCY = 0.05; // 5%
    private String TPC_UID;
    private static int numeberOfTPC;

    /**
     * construct instance with random GovtID
     * @param vehicleSize Length of vehicle in meters
     */
    public TrafficPoliceCar(double vehicleSize){
        super(vehicleSize);
        getGovtID();
    }

    /**
     * Construct instance with given GovtID
     * @param vehicleSize Length of vehicle in meters
     * @param govtID Provided Govt ID
     */
//    public TrafficPoliceCar(double vehicleSize, int govtID) {
//        super(vehicleSize);
//        this.govtID = govtID;
//    } //TODO „« ÌÕ «Ã «‰‰« ‰‰‘∆ ÌÊ «Ì œÌ »„« «‰Â «·«‰‘«¡ ⁄ «·” « ﬂ 

    @Override
    public void generateGovtID(){
    	numeberOfTPC++;
    	this.TPC_UID = String.format("TPC%03d", numeberOfTPC);
    }
    
    
    public String getGovtID() {
    	return this.TPC_UID;
        
    }
}
