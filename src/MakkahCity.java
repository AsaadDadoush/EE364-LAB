import java.util.*;

public class MakkahCity {

	private static final ArrayList<Campaign> listOfCampaigns = new ArrayList<>();
	private static final ArrayList<Vehicle> listOfVehicles = new ArrayList<>();
	private static final Route[] stdRoutes = new Route[6];
	private static final Street[] stdStreet = new Street[9];

	private static final PDate timeManager = new PDate(
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 4, 0, 0),
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 17, 0, 0)
	);

	public static void main(String[] args) {

		//Gen Camp
		generateCamps(District.ALAZIZIYA, (int)getRandom(1200, 1400));
		generateCamps(District.ALMANSOOR, (int)getRandom(1600, 1800));
		generateCamps(District.ALHIJRA, (int)getRandom(1400, 1600));

		fillBusesToList();

		//Make Streets
		makeStreets();

		addCivilVehicleNoise();

		//Make Routes
		makeRoutes();

		//Set Routes for Campaigns
		setRoutesForCampaigns();

		//TODO: use Queues or Wating area for each street?
		while(!timeManager.isEnded()) {
			//Start of Every hour
			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) == 0){
				if (isAllArrived()) {
					System.out.println("\nAll campaigns have arrived before " + timeManager.getCurrentTime());
					break;
				}
			}
			//Start of Every half-hour
			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) % 30 == 0){

			}
			//Start of every 10min
			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) % 10 == 0){
				addCivilVehicleNoise();
				System.out.println("\n\n" + getStreetsReport());
				printFinalRep();
			}

			if (timeManager.getCurrentCalendar().get(Calendar.MINUTE) == getRandom(0,59)
		 		&& timeManager.getCurrentCalendar().get(Calendar.SECOND) == getRandom(0,59)){

			}

			for (Vehicle vehicle : listOfVehicles) {
				Route route = vehicle.getRoute();
				double currentLocation = vehicle.getCurrentLocation();
				if (vehicle.getCurrentStreet() == null &&
				route.getStreets()[0].capcityPoint(0,1000) < 1) {
					vehicle.setCurrentStreet(route.getStreets()[0]);
				}
				 if (vehicle.getCurrentStreet() != null && vehicle.getCurrentStreet().capcityPoint(currentLocation,
						currentLocation+1000) < 1 ) {
				 	//TODO: Possible bug. Checks 1Km ahead. Street may not be that long(checks as ok).

					if (currentLocation >= vehicle.getCurrentStreet().getLength()) {
						//Move to next street
						vehicle.moveToNextStreet();
					}
					if (!vehicle.isArrivedToDest()) {
						double factor = 1-(vehicle.getCurrentStreet().capcityPoint(vehicle.getCurrentLocation(),
										vehicle.getCurrentLocation()+1000,vehicle)) ;
						if (vehicle instanceof Bus) vehicle.move(Bus.MAX_FORWARD * factor );
						else if (vehicle instanceof Sedan) vehicle.move(Sedan.MAX_FORWARD * factor );
						else if (vehicle instanceof SUV) vehicle.move(SUV.MAX_FORWARD * factor );
						else if (vehicle instanceof Truck) vehicle.move(Bus.MAX_FORWARD * factor );
					}
				}
			}
//			Vehicle v = listOfVehicles.get(150);
//			if (v.getCurrentStreet() != null) {
//				System.out.printf("St: %s distance: %f total: %f %s\n",
//						v.getCurrentStreet().getName(),
//						v.getCurrentLocation(),
//						v.getTotalDistanceTraveled(),
//						timeManager.getCurrentTime());
			//}
			
			//noise based on time of day (From PDate)
		

			/*
			Output:
			Street stats
			Campaigns avg (at end)
			 */
			timeManager.step(Calendar.MINUTE, 1);
		}
		//TODO: print final report 
	}

	private static void setRoutesForCampaigns() {
		for (Campaign camp : listOfCampaigns){
			camp.setDestToHousingRoute(getShortestRoute(camp));
		}
	}


	private static double getRandom(double min, double max) {
		return (Math.random() * (max - min) + min);
	}

	private static void generateCamps(District area, int count) {
		for (int i = 0; i < count; i++){
			Campaign camp = new Campaign(area, (int)getRandom(10, 15));
			listOfCampaigns.add(camp);
		}
	}

	private static void makeStreets(){
		stdStreet[StreetNames.KA_STREET.ordinal()] = new Street(4700,3, StreetNames.KA_STREET);
		stdStreet[StreetNames.FOURTH_HIGHWAY.ordinal()] = new Street(9700,4, StreetNames.FOURTH_HIGHWAY);
		stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()] = new Street(8200,3, StreetNames.THIRD_HIGHWAY);
		stdStreet[StreetNames.STREET1.ordinal()] = new Street(7800,3, StreetNames.STREET1);
		stdStreet[StreetNames.STREET2.ordinal()] = new Street(2400,3,StreetNames.STREET2);
		stdStreet[StreetNames.STREET3.ordinal()] = new Street(4800,2, StreetNames.STREET3);
		stdStreet[StreetNames.STREET4.ordinal()] = new Street(3800,3,StreetNames.STREET4);
		stdStreet[StreetNames.STREET5.ordinal()] = new Street(3200,2, StreetNames.STREET5);
		stdStreet[StreetNames.IBRAHIM_ALKHALIL.ordinal()] = new Street(4500,3, StreetNames.IBRAHIM_ALKHALIL);
	}

	private static void makeRoutes() {

		stdRoutes[RouteName.mashierToAlHijra1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET1.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()]},
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
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()],
						stdStreet[StreetNames.STREET5.ordinal()]//TODO: [8]is actually half of ibrahim khalil.
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

	private static void addCivilVehicleNoise() {

		for (Street street: stdStreet) {
			int numOfSedan = (int)getRandom(40,50);
			int numOfSUV = (int)getRandom(20,30);
			int numOfTruck = (int)getRandom(10,12);

			if (street.getName() == StreetNames.FOURTH_HIGHWAY) numOfSedan = (int) (numOfSedan * 0.5);
			if (street.getName() == StreetNames.STREET3) numOfSedan = (int) (numOfSedan * 1.5);
			if (street.getName() == StreetNames.STREET5) numOfSedan = (int) (numOfSedan * 1.5);
			for (int x = 0; x < numOfSedan; x++) {
				Sedan car = new Sedan(getRandom(4, 5));
				double pointOfEntry = getRandom(0, street.getLength());
				if (street.capcityPoint(pointOfEntry, pointOfEntry+1500) < 1){
					listOfVehicles.add(car);
					car.setCurrentLocation(pointOfEntry);
					car.setRoute(new Route(street));
					car.setCurrentStreet(street);
				}

			}

			if (street.getName() == StreetNames.FOURTH_HIGHWAY) numOfTruck = (int) (numOfTruck * 0.5);
			if (street.getName() == StreetNames.STREET3) numOfTruck = (int) (numOfTruck * 1.5);
			if (street.getName() == StreetNames.STREET5) numOfSedan = (int) (numOfSedan * 1.5);
			for (int x = 0; x < numOfTruck; x++) {
				Truck car = new Truck(getRandom(4, 5));
				double pointOfEntry = getRandom(0, street.getLength());
				if (street.capcityPoint(pointOfEntry, pointOfEntry+1500) < 1){
					listOfVehicles.add(car);
					car.setCurrentLocation(pointOfEntry);
					car.setRoute(new Route(street));
					car.setCurrentStreet(street);
				}
			}

			if (street.getName() == StreetNames.FOURTH_HIGHWAY) numOfSUV = (int) (numOfSUV * 0.5);
			if (street.getName() == StreetNames.STREET3) numOfSUV = (int) (numOfSUV * 1.5);
			if (street.getName() == StreetNames.STREET5) numOfSedan = (int) (numOfSedan * 1.5);
			for (int x = 0; x < numOfSUV; x++) {
				SUV car = new SUV(getRandom(4, 5));
				double pointOfEntry = getRandom(0, street.getLength());
				if (street.capcityPoint(pointOfEntry, pointOfEntry+1500) < 1){
					listOfVehicles.add(car);
					car.setCurrentLocation(pointOfEntry);
					car.setRoute(new Route(street));
					car.setCurrentStreet(street);
				}
			}

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
	
	
	
	private static void printReport() {
		for(Street street : stdStreet) {
			System.out.printf("StreetName: %s NumberOfVheciles : %d  Capcity: %f\n",
					street.getName().name(),street.getVehicles().size(), street.capcity());
			int qurter = (int) street.getLength()/4;
			double capcity = 0;
				for(int i = 0; i < 4; i++) {
					capcity = street.capcityPoint(i * qurter, qurter * (i+1));
					System.out.printf("qurter%d: %.2f ", (i+1) , capcity );
					}
				System.out.println("\n");	
		}	
	}
	
	
	private static String getStreetsReport() {
		String headerFormat = "******Streets report*****\n" +
						"Time: %s\n" +
						"   Street name   |remaining capacity| Total | Buses | Local Vehicles |\n";
		String report = "";
		report = report + String.format(headerFormat, timeManager.getCurrentTime());
		String entryFormat = "%-16s | %%%-15s | %5d | %5d | %14d |\n";
		for (Street street : stdStreet) {
			int cap = street.getPercentRemainingCapacity();
			report = report + String.format(entryFormat,
					street.getName().name(),
					cap,
					street.getVehicles().size(),
					getNumberOfBuses(street),
					getNumberOfLocalCars(street));
		}
		return report;
	}
	
	private static void printFinalRep() {
		int numberOfBusses = 0;
		int numberOfArrivedBuses = getNumberOfArrivedBusses();
		//Redundant loops slow down execution. find better sol.
		for (Campaign campaign : listOfCampaigns) {
			numberOfBusses += campaign.getNumberOfBusses();
		}
		System.out.printf("Buses: %d Buses done: %d Average trip time: %s\n",
				numberOfBusses, numberOfArrivedBuses, avgTimeOfTrip());
		avgTimeOfTrip();
	}

	/**
	 * Calculate average trip time for last 10 minutes
	 * @return "hh:mm"
	 */
	private static String avgTimeOfTrip() {
		Calendar now = timeManager.getCurrentCalendar();
		Calendar from = (GregorianCalendar)now.clone();
		from.roll(Calendar.MINUTE, -10);
		int counter = 1;
		int sum = 0;
		for (Campaign campaign : listOfCampaigns){
			for (Vehicle bus : campaign.getVehicles()){
				if (bus.isArrivedToDest() && bus.getTimeOfArrival().before(now.getTime())
				&& bus.getTimeOfArrival().after(from.getTime())) {
					long minutes = (bus.getTimeOfArrival().getTime() - bus.getTimeStartedMoving().getTime())/60000;
					sum+= minutes;
					counter++;
				}
			}
		}
		sum = sum /counter;
		int hours = sum / 60;
		int minutes = sum % 60;
		return String.format("%2d:%02d", hours,minutes);
	}

	private static int getNumberOfArrivedBusses() {
		int num = 0;
		for (Campaign campaign : listOfCampaigns) {
			for (Vehicle vehicle : campaign.getVehicles()){
				if (vehicle instanceof  Bus &&
				vehicle.isArrivedToDest()) num++;
			}
		}
		return num;
	}
	
	private static boolean isAllArrived() {
		for (Campaign campaign : listOfCampaigns)
			if (!campaign.isDone())
				return false;
		
		return true;
	}

	private static int getNumberOfBuses(Street street) {
		int number = 0;
		for (Vehicle vehicle : street.getVehicles()) {
			if (vehicle instanceof Bus) number++;
		}
		return number;
	}

	private static int getNumberOfLocalCars(Street street) {
		int number = 0;
		for (Vehicle vehicle : street.getVehicles()) {
			if (vehicle instanceof CivilVehicle && !(vehicle instanceof Bus)) number++;
		}
		return number;
	}

}
