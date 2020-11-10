import java.util.*;

public class MakkahCity {

	private static final ArrayList<Campaign> allCampgains = new ArrayList<>();

	private static final ArrayList<Vehicle> vehiclesList = new ArrayList<>();
	private static final Route[] stdRoutes = new Route[9];
	private static final Street[] stdStreet = new Street[8];

	public static void main(String[] args) {

		//Gen Camp
		generateCamps(District.ALAZIZIYA, getRandom(70, 100));
		generateCamps(District.ALMANSOOR, getRandom(110, 160));
		generateCamps(District.ALHIJRA, getRandom(80, 110));
		
		//Make Streets
		makeStreets();
		
		//Make Routes
		makeRoutes();

		while(!PDate.isEnded()) {
			PDate.step(Calendar.MINUTE, 1);
			//TODO: add civil cars in loop itirations.
			//TODO: Move busses and vehicles.
			//TODO: Update streets.
			//TODO: Streets move forward.
			//TODO: update vehicles on street.
		}
	}

	private static int getRandom(int min, int max) {
		return (int)(Math.random() * (max - min) + min);
	}

	private static void generateCamps(District area, int count) {
		for (int i = 0; i < count; i++){
			Campaign camp = new Campaign(area, getRandom(10, 15));
			allCampgains.add(camp);
		}
	}

	private static void makeStreets(){
		stdStreet[StreetNames.KA_STREET] = new Street(22700,3);
		stdStreet[StreetNames.FOURTH_HISHWAY] = new Street(24600,4);
		stdStreet[StreetNames.THIRD_HIGHWAY] = new Street(22000,3);
		stdStreet[StreetNames.STREET1] = new Street(4000,2);
		stdStreet[StreetNames.STREET2] = new Street(7000,2);
		stdStreet[StreetNames.STREET3] = new Street(400,2);
		stdStreet[StreetNames.STREET4] = new Street(8200,2);
		stdStreet[StreetNames.STREET5] = new Street(100,2); //TODO: Change numbers
	}

	private static void makeRoutes() {
	//	stdRoutes [RouteName.mashierToAlMansoor1] = new Route({stdStreet[]}, hotelArea, mashier) 
			
		
	}

	private static void fillBusesToList() {
		//TODO: add all buses from campaigns to vehiclesList
	}
}
