import java.util.*;

public class MakkahCity {

	private static final ArrayList<Campaign> listOfCampaigns = new ArrayList<>();

	private static final ArrayList<Vehicle> listOfVehicles = new ArrayList<>();
	private static final Route[] stdRoutes = new Route[9];
	private static final Street[] stdStreet = new Street[8];

	public static void main(String[] args) {

		//Gen Camp
		generateCamps(District.ALAZIZIYA, getRandom(70, 100));
		generateCamps(District.ALMANSOOR, getRandom(110, 160));
		generateCamps(District.ALHIJRA, getRandom(80, 110));

		fillBusesToList();

		//Make Streets
		makeStreets();

		//Make Routes
		makeRoutes();

		//Set Routes for Campaigns
		setRoutesForCampaigns();

		//TODO: Set Schedule for Campaigns

		while(!PDate.isEnded()) {
			PDate.step(Calendar.MINUTE, 1);
			System.out.println(PDate.getCurrentTime());
			//TODO: add civil cars in loop iterations. (noise)

			//TODO: Move busses and vehicles.

			//TODO: Update streets.

			//TODO: Streets move forward.

			//TODO: update vehicles on street.
		}
	}

	private static void setRoutesForCampaigns() {
		for (Campaign camp : listOfCampaigns){
			if (camp.getHotelDistrict() == District.ALAZIZIYA) {
				setUpCampaginRoute(camp, RouteName.mashierToAlAzizi1);
			}
			else if (camp.getHotelDistrict() == District.ALMANSOOR){
				setUpCampaginRoute(camp, RouteName.mashierToAlMansoor2);
			}
			else {
				setUpCampaginRoute(camp, RouteName.mashierToAlHijra1);
			}
		}
	}

	private static void setUpCampaginRoute(Campaign camp, int routeName) {
		Route route = stdRoutes[routeName];
		camp.setDestToHousingRoute(route);
		//For now set all busses to one route
		for(Vehicle vehicle : camp.getVehicles()){
			vehicle.setRoute(route);
		}
	}

	private static int getRandom(int min, int max) {
		return (int)(Math.random() * (max - min) + min);
	}

	private static void generateCamps(District area, int count) {
		for (int i = 0; i < count; i++){
			Campaign camp = new Campaign(area, getRandom(10, 15));
			listOfCampaigns.add(camp);
		}
	}

	private static void makeStreets(){
		stdStreet[StreetNames.KA_STREET] = new Street(22700,3);
		stdStreet[StreetNames.FOURTH_HIGHWAY] = new Street(24600,4);
		stdStreet[StreetNames.KUDAY] = new Street(22000,3);
		stdStreet[StreetNames.STREET1] = new Street(4000,2);
		stdStreet[StreetNames.STREET2] = new Street(7000,2);
		stdStreet[StreetNames.STREET3] = new Street(400,2);
		stdStreet[StreetNames.STREET4] = new Street(8200,2);
		stdStreet[StreetNames.IBRAHIM_ALKHALIL] = new Street(100,2); //TODO: Change numbers
	}

	private static void makeRoutes() {

		stdRoutes[RouteName.mashierToAlHijra1] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1],
						stdStreet[StreetNames.STREET2],
						stdStreet[StreetNames.KUDAY]},
				District.ALHIJRA, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlHijra2] = new Route(new Street[]{
						stdStreet[StreetNames.STREET1],
						stdStreet[StreetNames.FOURTH_HIGHWAY],
						stdStreet[StreetNames.STREET4]
		},District.ALAZIZIYA, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlMansoor1] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1],
						stdStreet[StreetNames.STREET2],
						stdStreet[StreetNames.KA_STREET],
						stdStreet[StreetNames.STREET3]
				},District.ALMANSOOR, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlMansoor2] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1],
						stdStreet[StreetNames.STREET2],
						stdStreet[StreetNames.KUDAY],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL]//TODO: is actually half of ibrahim khalil.
				},District.ALMANSOOR, Mashier.ARAFAT);

		//Optimal for Almansoor
		stdRoutes[RouteName.mashierToAlMansoor3] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1],
						stdStreet[StreetNames.FOURTH_HIGHWAY],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL]
				},District.ALMANSOOR, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlAzizi1] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1],
						stdStreet[StreetNames.STREET2],
						stdStreet[StreetNames.KA_STREET]
		},District.ALAZIZIYA, Mashier.ARAFAT);

	}

	private static void fillBusesToList() {
		for (Campaign camp : listOfCampaigns) {
			listOfVehicles.addAll(camp.getVehicles());
		}
	}

	/**
	 * Generates 'noise' cars to be used in streets
	 * @param numberOfCars
	 * @return Array of 70% Sedans and 30% SUVs
	 */
	private static CivilVehicle[] generateCivilVehicles(int numberOfCars) {
		CivilVehicle[] cars = new CivilVehicle[numberOfCars];
		int sedans = (int)(numberOfCars*0.7);
		int SUVs = numberOfCars - sedans;
		for (int i = 0; i < sedans; i++) {
			cars[i] = new Sedan(getRandom(25,32)/10);
		}

		for (int i = 0; i < SUVs; i++) {
			cars[sedans+i] = new SUV(getRandom(35,45)/10);
		}
		return cars;
	}
}
