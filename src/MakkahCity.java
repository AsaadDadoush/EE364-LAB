import java.util.*;

public class MakkahCity {

	private static final ArrayList<Campaign> listOfCampaigns = new ArrayList<>();

	private static final ArrayList<Vehicle> listOfVehicles = new ArrayList<>();
	private static final Route[] stdRoutes = new Route[6];
	private static final Street[] stdStreet = new Street[8];

	private static final PDate timeManager = new PDate(
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 4, 0, 0),
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 17, 0, 0)
	);

	public static void main(String[] args) {

		//Gen Camp
		generateCamps(District.ALAZIZIYA, (int)getRandom(70, 100));
		generateCamps(District.ALMANSOOR, (int)getRandom(110, 160));
		generateCamps(District.ALHIJRA, (int)getRandom(80, 110));

		fillBusesToList();

		//Make Streets
		makeStreets();

		//Make Routes
		makeRoutes();

		//Set Routes for Campaigns
		setRoutesForCampaigns();

		//TODO: use Queues or Wating area for each street?

		while(!timeManager.isEnded()) {
			timeManager.step(Calendar.MINUTE, 1);
			//Start of Every hour
			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) == 0){

			}
			//Start of Every half-hour
			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) % 30 == 0){

			}

			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) == getRandom(0,59)
		 		&& timeManager.getCurrentCalendar().get(Calendar.SECOND) == getRandom(0,59)){

			}

			for (Vehicle vehicle : listOfVehicles) {
				Route route = vehicle.getRoute();
				double currentLocation = vehicle.getCurrentLocation();
				if (vehicle.getCurrentStreet() == null &&
				route.getStreets()[0].capcityPoint(0,1500) < 1) {
					vehicle.setCurrentStreet(route.getStreets()[0]);
				}
				else if (vehicle.getCurrentStreet() != null && vehicle.getCurrentStreet().capcityPoint(currentLocation+1500,
						currentLocation+1500*2) < 1 ) { //May test diff values.

					if (currentLocation >= vehicle.getCurrentStreet().getLength()) {
						//Move to next street
						vehicle.setCurrentLocation(0);
						int nxtIndex = route.indexOf(vehicle.getCurrentStreet()) + 1;
						if (nxtIndex <= route.getStreets().length - 1)
							vehicle.setCurrentStreet(route.getStreets()[nxtIndex]);
						else vehicle.arrive();
					}
					if (!vehicle.isArrivedToDest()) {
						if (vehicle instanceof Bus) vehicle.move(Bus.MAX_FORWARD);
						else if (vehicle instanceof Sedan) vehicle.move(Sedan.MAX_FORWARD);
						else if (vehicle instanceof SUV) vehicle.move(SUV.MAX_FORWARD);
						else if (vehicle instanceof Truck) vehicle.move(Bus.MAX_FORWARD);
					}
				}
			}
			Vehicle v = listOfVehicles.get(320);
			if (v.getCurrentStreet() != null) {
				System.out.printf("St: %s distance: %f total: %f %s\n",
						v.getCurrentStreet().getName(),
						v.getCurrentLocation(),
						v.getTotalDistanceTraveled(),
						timeManager.getCurrentTime());
			}


			//System.out.println(v.getTimeStartedMoving());
			//TODO: [2]add civil cars in loop iterations. (noise)
			//noise based on time of day (From PDate)

			//TODO: [5]Streets move forward.

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

	private static double getRandom(int min, int max) {
		return (Math.random() * (max - min) + min);
	}

	private static void generateCamps(District area, int count) {
		for (int i = 0; i < count; i++){
			Campaign camp = new Campaign(area, (int)getRandom(10, 15));
			listOfCampaigns.add(camp);
		}
	}

	private static void makeStreets(){
		stdStreet[StreetNames.KA_STREET.ordinal()] = new Street(22700,3, StreetNames.KA_STREET);
		stdStreet[StreetNames.FOURTH_HIGHWAY.ordinal()] = new Street(24600,4, StreetNames.FOURTH_HIGHWAY);
		stdStreet[StreetNames.KUDAY.ordinal()] = new Street(22000,3, StreetNames.KUDAY);
		stdStreet[StreetNames.STREET1.ordinal()] = new Street(4000,2, StreetNames.STREET1);
		stdStreet[StreetNames.STREET2.ordinal()] = new Street(7000,2,StreetNames.STREET2);
		stdStreet[StreetNames.STREET3.ordinal()] = new Street(400,2, StreetNames.STREET3);
		stdStreet[StreetNames.STREET4.ordinal()] = new Street(8200,2,StreetNames.STREET4);
		stdStreet[StreetNames.IBRAHIM_ALKHALIL.ordinal()] = new Street(100,2, StreetNames.IBRAHIM_ALKHALIL); //TODO: [7]Change numbers
	}

	private static void makeRoutes() {

		stdRoutes[RouteName.mashierToAlHijra1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.KUDAY.ordinal()]},
				District.ALHIJRA, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlHijra2.ordinal()] = new Route(new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY.ordinal()],
						stdStreet[StreetNames.STREET4.ordinal()]
		},District.ALHIJRA, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlMansoor1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.KA_STREET.ordinal()],
						stdStreet[StreetNames.STREET3.ordinal()]
				},District.ALMANSOOR, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlMansoor2.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.KUDAY.ordinal()],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL.ordinal()]//TODO: [8]is actually half of ibrahim khalil.
				},District.ALMANSOOR, Mashier.ARAFAT);

		//Optimal for Almansoor
		stdRoutes[RouteName.mashierToAlMansoor3.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY.ordinal()],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL.ordinal()]
				},District.ALMANSOOR, Mashier.ARAFAT);

		stdRoutes[RouteName.mashierToAlAzizi1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.KA_STREET.ordinal()]
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
