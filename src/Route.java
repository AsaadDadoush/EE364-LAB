public class Route {

    private Street[] streets;
    private District hotelArea;
    private Mashier mashier;

    public Route(Street[] streets, District hotelArea, Mashier mashier) {
        setStreets(streets);
        setHotelArea(hotelArea);
        setMashier(mashier);
    }

    public Route(Street street){
        this.streets = new Street[1];
        this.streets[0] = street;
    }

    public int indexOf(Street street){
        for (int i = 0; i < streets.length; i++) {
            if (street == streets[i]) return i;
        }
        return -1;
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

    public District getHotelArea() {
        return hotelArea;
    }

    public Mashier getMashier() {
        return mashier;
    }

    private void setStreets(Street[] streets) {
        if (streets != null) this.streets = streets;
        else throwIllegal();
    }

    private void setHotelArea(District hotelArea) {
        if (hotelArea != null) this.hotelArea = hotelArea;
        else throwIllegal();
    }

    private void setMashier(Mashier mashier) {
        if (mashier != null) this.mashier = mashier;
        else throwIllegal();
    }

    private void throwIllegal() {
        throw new IllegalArgumentException();
    }
    
}
