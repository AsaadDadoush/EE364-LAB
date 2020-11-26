import java.util.*;

public class MakkahCity {

	private static final ArrayList<Campaign> listOfCampaigns = new ArrayList<>();
	private static final ArrayList<Vehicle> listOfVehicles = new ArrayList<>();
	private static final ArrayList<Campaign>[] campPerDistrict = new ArrayList[District.values().length];
	private static final Route[] stdRoutes = new Route[RouteName.values().length];
	private static final Street[] stdStreet = new Street[StreetNames.values().length];

	private static final PDate firstDayTimeMan = new PDate(
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 4, 0, 0),
		new GregorianCalendar(2020, Calendar.JANUARY, 1, 18, 0, 0)
	);

	private static final PDate lastDayTimeMan = new PDate(
			new GregorianCalendar(2020, Calendar.JANUARY, 4, 12, 0, 0),
			new GregorianCalendar(2020, Calendar.JANUARY, 4, 22, 0, 0)
	);

	private static PDate currenttimeManager = firstDayTimeMan;

	public static void main(String[] args) {

		//Gen Camp
		campPerDistrict[District.ALMANSOOR.ordinal()] = new ArrayList<>();
		campPerDistrict[District.ALAZIZIYA.ordinal()] = new ArrayList<>();
		campPerDistrict[District.ALHIJRA.ordinal()] = new ArrayList<>();
		generateCamps(District.ALAZIZIYA, (int)getRandom(1200, 1400));
		generateCamps(District.ALMANSOOR, (int)getRandom(1600, 1800));
		generateCamps(District.ALHIJRA, (int)getRandom(1400, 1600));

		fillBusesToList();

		makeStreets();

		addCivilVehicleNoise();

		makeRoutes();

		//Set Routes for Campaigns
		setRoutesForCampaigns(Mashier.ARAFAT);
		System.out.println(preSimulationReport());
		while(!firstDayTimeMan.isEnded()) {
			//Start of Every hour
			if (firstDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) == 0){
				System.out.println("\n\n" + getStreetsReport());
			}
			else System.out.print(".");
			//Start of Every half-hour
			if (firstDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) % 30 == 0){

			}
			//Start of every 10min
			if (firstDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) % 10 == 0){
				addCivilVehicleNoise();
			}

			if (firstDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) == getRandom(0,59)
		 		&& firstDayTimeMan.getCurrentCalendar().get(Calendar.SECOND) == getRandom(0,59)){

			}
			clearDoneCivilVehicles();
			for (Vehicle vehicle : listOfVehicles) {
				Route route = vehicle.getRoute();
				double currentLocation = vehicle.getCurrentLocation();
				if (vehicle.getCurrentStreet() == null &&
				route.getStreets()[0].capcityPoint(0,1000) < 1) {
					vehicle.setCurrentStreet(route.getStreets()[0]);
				}
				 if (vehicle.getCurrentStreet() != null && vehicle.getCurrentStreet().capcityPoint(currentLocation,
						currentLocation+1000) < 1 ) {

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
			//noise based on time of day (From PDate)
			firstDayTimeMan.step(Calendar.MINUTE, 1);
			//for (int i = 0; i < 46; i++) System.out.print("\b");
		}
		//TODO make report
		currenttimeManager = lastDayTimeMan;
		System.out.println("***************FINSHIED ARAFAT DAY***************");
		setRoutesForCampaigns(Mashier.MINA);
		for (Vehicle vehicle : listOfVehicles) {
			vehicle.setCurrentStreet(null);
		}
		System.out.println("***************STARTING LAST DAY***************");
		while(!lastDayTimeMan.isEnded()) {
			//Start of Every hour
			if (lastDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) == 0){
				//TODO: removed break here. now should schedule.
				System.out.println("\n\n" + getStreetsReport());
			}
			else System.out.print(".");
			//Start of Every half-hour
			if (lastDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) % 30 == 0){

			}
			//Start of every 10min
			if (lastDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) % 10 == 0){
				addCivilVehicleNoise();
			}

			if (lastDayTimeMan.getCurrentCalendar().get(Calendar.MINUTE) == getRandom(0,59)
					&& lastDayTimeMan.getCurrentCalendar().get(Calendar.SECOND) == getRandom(0,59)){

			}
			clearDoneCivilVehicles();
			for (Vehicle vehicle : listOfVehicles) {
				Route route = vehicle.getRoute();
				double currentLocation = vehicle.getCurrentLocation();
				if (vehicle.getCurrentStreet() == null &&
						route.getStreets()[0].capcityPoint(0,1000) < 1) {
					vehicle.setCurrentStreet(route.getStreets()[0]);
				}
				if (vehicle.getCurrentStreet() != null && vehicle.getCurrentStreet().capcityPoint(currentLocation,
						currentLocation+1000) < 1 ) {

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
			//noise based on time of day (From PDate)
			lastDayTimeMan.step(Calendar.MINUTE, 1);
			//for (int i = 0; i < 46; i++) System.out.print("\b");
		}
		//TODO: print final report 
	}

	private static void clearDoneCivilVehicles() {
		//Clear civil cars from list
		for (int i = 0; i < listOfVehicles.size();){
			Vehicle vehicle = listOfVehicles.get(i);
			if (!(vehicle instanceof Bus) && vehicle.isArrivedToDest())
				listOfVehicles.remove(vehicle);
			else i++;

		}
	}

	private static void setRoutesForCampaigns(Mashier mashier) {
		for (Campaign camp : listOfCampaigns){
			camp.setRoute(getShortestRoute(camp, mashier));
		}
	}

	/**
	 * Checks if 'now' is within range of coming to arafat and leaving mena.
	 * Arafat day range ends 01/01/2020 05:00PM
	 */
	private static boolean isArafatDayRange() {
		Calendar arafatDayStart = (GregorianCalendar) firstDayTimeMan.getStartCalendar().clone();
		Calendar arafatDayEnd = new GregorianCalendar(2020, Calendar.JANUARY, 1, 17, 0, 0);
		Calendar now = firstDayTimeMan.getCurrentCalendar();
		return now.after(arafatDayStart) && now.before(arafatDayEnd);
	}

	private static double getRandom(double min, double max) {
		return (Math.random() * (max - min) + min);
	}

	private static void generateCamps(District area, int count) {
		for (int i = 0; i < count; i++){
			Campaign camp = new Campaign(area, (int)getRandom(10, 15));
			listOfCampaigns.add(camp);
			campPerDistrict[area.ordinal()].add(camp);
		}
	}

	private static void makeStreets(){
		stdStreet[StreetNames.KA_STREET.ordinal()] = new Street(4700,3, StreetNames.KA_STREET);
		stdStreet[StreetNames.FOURTH_HIGHWAY1.ordinal()] = new Street(4850,4, StreetNames.FOURTH_HIGHWAY1);
		stdStreet[StreetNames.FOURTH_HIGHWAY2.ordinal()] = new Street(4850,4, StreetNames.FOURTH_HIGHWAY2);
		stdStreet[StreetNames.FOURTH_HIGHWAY3.ordinal()] = new Street(4500,4, StreetNames.FOURTH_HIGHWAY3);
		stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()] = new Street(8200,3, StreetNames.THIRD_HIGHWAY);
		stdStreet[StreetNames.STREET1.ordinal()] = new Street(7800,3, StreetNames.STREET1);
		stdStreet[StreetNames.STREET2.ordinal()] = new Street(2400,3,StreetNames.STREET2);
		stdStreet[StreetNames.STREET3.ordinal()] = new Street(4800,2, StreetNames.STREET3);
		stdStreet[StreetNames.JABAL_THAWR_STREET.ordinal()] = new Street(3800,3,StreetNames.JABAL_THAWR_STREET);
		stdStreet[StreetNames.IBRAHIM_ALKHALIL2.ordinal()] = new Street(3200,2, StreetNames.IBRAHIM_ALKHALIL2);
		stdStreet[StreetNames.IBRAHIM_ALKHALIL1.ordinal()] = new Street(4500,3, StreetNames.IBRAHIM_ALKHALIL1);
		stdStreet[StreetNames.KKH_STREET.ordinal()] = new Street(3300,3, StreetNames.KKH_STREET);

	}

	private static void makeRoutes() {

		//******Arafat day
		stdRoutes[RouteName.AlHijraToArafat1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.STREET1.ordinal()]},
				District.ALHIJRA, Mashier.ARAFAT);

		stdRoutes[RouteName.AlHijraToArafat2.ordinal()] = new Route(new Street[]{
						stdStreet[StreetNames.JABAL_THAWR_STREET.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY2.ordinal()],
						stdStreet[StreetNames.STREET1.ordinal()]
		},District.ALHIJRA, Mashier.ARAFAT);

		stdRoutes[RouteName.AlMansoorToArafat1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.STREET3.ordinal()],
						stdStreet[StreetNames.KA_STREET.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.STREET1.ordinal()]
				},District.ALMANSOOR, Mashier.ARAFAT);

		stdRoutes[RouteName.AlMansoorToArafat2.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.IBRAHIM_ALKHALIL2.ordinal()],
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.STREET1.ordinal()]
				},District.ALMANSOOR, Mashier.ARAFAT);

		//Optimal for Almansoor
		stdRoutes[RouteName.AlMansoorToArafat3.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.IBRAHIM_ALKHALIL2.ordinal()],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL1.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY1.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY2.ordinal()],
						stdStreet[StreetNames.STREET1.ordinal()]
				},District.ALMANSOOR, Mashier.ARAFAT);

		stdRoutes[RouteName.AlAziziToArafat1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.KA_STREET.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.STREET1.ordinal()]
		},District.ALAZIZIYA, Mashier.ARAFAT);

		//******Arafat day end

		//******Mina Leave
		stdRoutes[RouteName.MinaToAlMansoor1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.KKH_STREET.ordinal()],
						stdStreet[StreetNames.KA_STREET.ordinal()],
						stdStreet[StreetNames.STREET3.ordinal()]
				},District.ALMANSOOR, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlMansoor2.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.FOURTH_HIGHWAY3.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL2.ordinal()]
				},District.ALMANSOOR, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlMansoor3.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.FOURTH_HIGHWAY3.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY2.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY1.ordinal()],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL1.ordinal()],
						stdStreet[StreetNames.IBRAHIM_ALKHALIL2.ordinal()]
				},District.ALMANSOOR, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlhijra1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.FOURTH_HIGHWAY3.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()]
				},District.ALHIJRA, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlhijra2.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.FOURTH_HIGHWAY3.ordinal()],
						stdStreet[StreetNames.FOURTH_HIGHWAY2.ordinal()],
						stdStreet[StreetNames.JABAL_THAWR_STREET.ordinal()]
				},District.ALHIJRA, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlhijra3.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.KKH_STREET.ordinal()],
						stdStreet[StreetNames.THIRD_HIGHWAY.ordinal()]
				},District.ALHIJRA, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlaziziya1.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.KKH_STREET.ordinal()],
						stdStreet[StreetNames.KA_STREET.ordinal()],
				},District.ALAZIZIYA, Mashier.MINA);

		stdRoutes[RouteName.MinaToAlaziziya2.ordinal()] = new Route(
				new Street[]{
						stdStreet[StreetNames.FOURTH_HIGHWAY3.ordinal()],
						stdStreet[StreetNames.STREET2.ordinal()],
						stdStreet[StreetNames.KA_STREET.ordinal()],
				},District.ALAZIZIYA, Mashier.MINA);
		//******Mina Leave end

	}

	private static void fillBusesToList() {
		for (Campaign camp : listOfCampaigns) {
			listOfVehicles.addAll(camp.getVehicles());
		}
	}

	private static void addCivilVehicleNoise() {
		//TODO: rewrite to avoid factoring (deviding) the values down to zero.

		for (Street street: stdStreet) {
			int numOfSedan = (int)getRandom(40,50);
			int numOfSUV = (int)getRandom(20,30);
			int numOfTruck = (int)getRandom(10,12);
			if (street.getName() == StreetNames.FOURTH_HIGHWAY1) numOfSedan = (int) (numOfSedan * 0.5);
			if (street.getName() == StreetNames.FOURTH_HIGHWAY2) numOfSedan = (int) (numOfSedan * 0.5);
			if (street.getName() == StreetNames.STREET3) numOfSedan = (int) (numOfSedan * 1.5);
			if (street.getName() == StreetNames.IBRAHIM_ALKHALIL2) numOfSedan = (int) (numOfSedan * 1.5);
			for (int x = 0; x < numOfSedan; x++) {
				Sedan car = new Sedan(getRandom(4, 5));
				double pointOfEntry = getRandom(0, street.getLength());//TODO: consider getLength - x
				if (street.capcityPoint(pointOfEntry, pointOfEntry+1500) < 1){
					listOfVehicles.add(car);
					car.setCurrentLocation(pointOfEntry);
					car.setRoute(new Route(street));
					//car.setCurrentStreet(street);
				}

			}

			if (street.getName() == StreetNames.FOURTH_HIGHWAY1) numOfTruck = (int) (numOfTruck * 0.5);
			if (street.getName() == StreetNames.FOURTH_HIGHWAY2) numOfTruck = (int) (numOfTruck * 0.5);
			if (street.getName() == StreetNames.STREET3) numOfTruck = (int) (numOfTruck * 1.5);
			if (street.getName() == StreetNames.IBRAHIM_ALKHALIL2) numOfSedan = (int) (numOfSedan * 1.5);
			for (int x = 0; x < numOfTruck; x++) {
				Truck car = new Truck(getRandom(4, 5));
				double pointOfEntry = getRandom(0, street.getLength());
				if (street.capcityPoint(pointOfEntry, pointOfEntry+1500) < 1){
					listOfVehicles.add(car);
					car.setCurrentLocation(pointOfEntry);
					car.setRoute(new Route(street));
					//car.setCurrentStreet(street);
				}
			}

			if (street.getName() == StreetNames.FOURTH_HIGHWAY1) numOfSUV = (int) (numOfSUV * 0.5);
			if (street.getName() == StreetNames.FOURTH_HIGHWAY2) numOfSUV = (int) (numOfSUV * 0.5);
			if (street.getName() == StreetNames.STREET3) numOfSUV = (int) (numOfSUV * 1.5);
			if (street.getName() == StreetNames.IBRAHIM_ALKHALIL2) numOfSUV = (int) (numOfSedan * 1.5);
			for (int x = 0; x < numOfSUV; x++) {
				SUV car = new SUV(getRandom(4, 5));
				double pointOfEntry = getRandom(0, street.getLength());
				if (street.capcityPoint(pointOfEntry, pointOfEntry+1500) < 1){
					listOfVehicles.add(car);
					car.setCurrentLocation(pointOfEntry);
					car.setRoute(new Route(street));
					//car.setCurrentStreet(street);
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

	public static PDate getTimeMan() {
		return currenttimeManager;
	}

	/**
	 * Find shortest path without respect to traffic
	 * @param campaign
	 * @return
	 */
	private static Route getShortestRoute(Campaign campaign, Mashier mashier) {
		Route[] routes = getRoutesToDistrict(campaign.getHotelDistrict());
		Route route = null;
		double min = Double.MAX_VALUE;
		for (Route r : routes) {
			if (r.getMashier() == mashier){
				if (r.getTotalLength() < min) {
					min = r.getTotalLength();
					route = r;
				}
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
		String status = "";
		if (currenttimeManager == firstDayTimeMan) status = "   Status: Heading to Arafat";
		else status = "   Status: Heading to hotels";
		String headerFormat = "******Streets report*****\n" +
						"Time: %s%s\n" +
						"    Street name    |Street Load| Total | Buses | Local Vehicles |" +
						"*********| District  | Average Arrival | Avg. time\n";
		StringBuilder report = new StringBuilder();
		report.append(String.format(headerFormat, currenttimeManager.getCurrentTime(), status));
		String streetFormat = "%-18s | %%%-8s | %5d | %5d | %14d |";
		String districtForamt = "         | %-9s | %%%-14d | %s";
		for (int i = 0; i < stdStreet.length; i++) {
			int cap = stdStreet[i].getPercentRemainingCapacity();
			report.append(String.format(streetFormat,
					stdStreet[i].getName().name(),
					cap,
					stdStreet[i].getVehicles().size(),
					stdStreet[i].getNumberOfBuses(),
					stdStreet[i].getNumberOfLocalCars()));
			if (i < 3){
				report.append(String.format(districtForamt, District.values()[i], getPercentArrival(District.values()[i]),getAvgTimeOfTrip(District.values()[i])));
			}
			report.append("\n");
		}
		report.append("\n").append(getFinalRep());
		report.append("*************************");
		return report.toString();
	}
	
	private static String getFinalRep() {
		int numberOfBusses = 0;
		int numberOfArrivedBuses = getNumberOfArrivedBusses();
		//Redundant loops slow down execution. find better sol.
		for (Campaign campaign : listOfCampaigns) {
			numberOfBusses += campaign.getNumberOfBusses();
		} //TODO Add max min time, Add  how many arrived. Estimated arrivel if taken street.
		//TODO: And print all routes with their streets.
		//TODO: Print time when all have finished
		String s = String.format("Buses: %d Buses done: %d Average trip in last hour: %s\n",
				numberOfBusses, numberOfArrivedBuses, avgTimeOfTrip());
		return s;
	}

	/**
	 * Calculate average trip time for last 10 minutes
	 * @return "hh:mm"
	 */
	private static String avgTimeOfTrip() {
		//TODO: does output diff value even after all have arrived.
		Calendar now = firstDayTimeMan.getCurrentCalendar();
		Calendar from = (GregorianCalendar)now.clone();
		from.roll(Calendar.HOUR, -1);
		int counter = 1;
		int sum = 0;
		//TODO check last day avg
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
		if (hours == 0 && minutes == 0) return "(No arrivals) in last Hour";
		return String.format("%2d:%02d", hours,minutes);
	}

	private static int getPercentArrival(District district) {
		int sum = 0;
		for (Campaign campaign : campPerDistrict[district.ordinal()]) {
			sum += campaign.getPercentArrived();
		}
		return sum/campPerDistrict[district.ordinal()].size();
	}

	private static String getAvgTimeOfTrip(District district){
		int sum = 0;
		int counter = 1;
		for (Campaign campaign : campPerDistrict[district.ordinal()]) {
			for (Vehicle vehicle : campaign.getVehicles()) {
				if (vehicle.isArrivedToDest()) {
					long minutes = (vehicle.getTimeOfArrival().getTime() - vehicle.getTimeStartedMoving().getTime())/60000;
					sum+= minutes;
					counter++;
				}
			}
		}//Make the following a method since it is the same other method
		sum = sum /counter;
		int hours = sum / 60;
		int minutes = sum % 60;
		if (hours == 0 && minutes == 0) return "No arrivals yet";
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

	private static String getColoredStreetName(Street street, int capacity) {
		String s = "";
		String name = street.getName().name();
		if (capacity > 80) s = ANSI_RED + name;
		else if (capacity > 60) s = ANSI_YELLOW + name;
		else s = ANSI_GREEN + name;
		return s + ANSI_RESET;
	}

	private static String preSimulationReport() {
		StringBuilder report = new StringBuilder();
		report.append("******************************District details******************************\n");
		report.append("District | Campaigns | Busses | Est. time to Arafat | Est. time to District \n");
		for (int i = 0; i < campPerDistrict.length; i++) {
			//Per District, i denotes district index
			report.append(String.format("%-9s|",campPerDistrict[i].get(0).getHotelDistrict().name()));

			report.append(String.format(" %-10d|",campPerDistrict[i].size()));
			//Calc values per dist here.

			report.append("\n");
		}
		return report.toString();
	}

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

}
