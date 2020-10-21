public class Route {

    private Street[] streets;

    public Route(Street[] streets) {
        this.streets = streets;
    }

    public Street[] getStreets() {
        return this.streets;
    }

    public double getTotalLength() {
        double totalLength = 0;
        for (Street st : streets){
            totalLength += st.getLength();
        }
        return totalLength;
    }

}
