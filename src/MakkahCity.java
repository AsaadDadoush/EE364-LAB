import java.util.*;

public class MakkahCity {

	private static final ArrayList<Campaign> listOfCampaigns = new ArrayList<>();

	private static final ArrayList<Vehicle> listOfVehicles = new ArrayList<>();
	private static final Route[] stdRoutes = new Route[6];
	private static final Street[] stdStreet = new Street[8];

	private static final PDate timeManager = new PDate(
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 8, 0, 0),
		new GregorianCalendar(2020, Calendar.JANUARY, 2, 8, 0, 0)
	);

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

		//TODO: [1]Set Schedule for Campaigns

		while(!timeManager.isEnded()) {
			timeManager.step(Calendar.MINUTE, 1);
			System.out.println(timeManager.getCurrentTime());
			//TODO: [2]add civil cars in loop iterations. (noise)
			//noise based on time of day (From PDate)
			//TODO: [3]Move busses and vehicles.

			//TODO: [4]Update streets.

			//TODO: [5]Streets move forward.

			//TODO: [6]update vehicles on street.
		}
	}

	private static void setRoutesForCampaigns() {
		for (Campaign camp : listOfCampaigns){
			camp.setDestToHousingRoute(getShortestRoute(camp));
		}
	}

	/*
	This is not used. The campaign object sets the routes for the busses
	 */
	@Deprecated
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
		stdStreet[StreetNames.IBRAHIM_ALKHALIL] = new Street(100,2); //TODO: [7]Change numbers
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
		},District.ALHIJRA, Mashier.ARAFAT);

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
						stdStreet[StreetNames.IBRAHIM_ALKHALIL]//TODO: [8]is actually half of ibrahim khalil.
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
			try {
				cars[sedans+i] = new SUV(getRandom(35,45)/10);
			}
			catch (IndexOutOfBoundsException ex) {
				break;
			}
		}
		return cars;
	}

	public static PDate getTimeManager() {
		return timeManager;
	}

	/**
	 * Find shortest path without respect to traffic
	 * @param campaign
	 * @return
	 */
	private static Route getShortestRoute(Campaign campaign) {
		Route[] routes = getRoutesToDistrict(campaign.getHotelDistrict());
		Route route = null;
		double min = Double.MAX_VALUE;
		for (Route r : routes) {
			if (r.getTotalLength() < min) {
				min = r.getTotalLength();
				route = r;
			}
		}
		return route;
	}

	/**
	 * Find routes that connect to a certain district.
	 * @param district
	 * @return Array of routes that connect to 'district'
	 */
	private static Route[] getRoutesToDistrict(District district) {
		ArrayList<Route> routes = new ArrayList<>();
		for (Route route : stdRoutes) {
			if (route.getHotelArea() == district) {
				routes.add(route);
			}
		}
		Route[] routesArray = new Route[routes.size()];
		return routes.toArray(routesArray);
	}

}
