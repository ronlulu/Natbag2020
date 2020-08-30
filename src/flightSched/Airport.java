package flightSched;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import flightSched.Flight.FlightType;

public class Airport {

	public static final String AIRPORT_CODE = "TLV";

	private Flight[] takeOff, arrivels, futureFlights;
	private int numOfArrivels, numOfTakeoff, numOfFutureFlights;

	public Airport(String airportCode) {
		takeOff = new Flight[20];
		arrivels = new Flight[20];
		futureFlights = new Flight[20];
		numOfArrivels = 0;
		numOfTakeoff = 0;
		numOfFutureFlights = 0;
	}


	public boolean addFlight(Flight newFlight) {
		LocalDateTime now = LocalDateTime.now();
		if (newFlight.isTodaysFlight(now)) {
			if (newFlight.getFlightType().equals(FlightType.departure)) {
				takeOff[numOfTakeoff++] = newFlight;
				return true;
			}
			if (newFlight.getFlightType().equals(FlightType.arrivale)) {
				arrivels[numOfArrivels++] = newFlight;
				return true;
			} else
				return false;
		} else {
			futureFlights[numOfFutureFlights++] = newFlight;
			return true;
		}

	}

	public String getTakeOffSched() {
		sortMethodByDate(takeOff, numOfTakeoff);
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < numOfTakeoff; i++) {
			res.append(takeOff[i].toString());
		}
		return res.toString();
	}

	public void updateFlightsScedule() { // this mathod check the date and update the flights arrays from future flights
											// to arrivales/departures.
		LocalDateTime now = LocalDateTime.now();
		for (int i = 0; i < numOfFutureFlights; i++) {
			if (now.getYear() == futureFlights[i].getTimeOfFlight().getYear()) {
				if (now.getDayOfYear() == futureFlights[i].getTimeOfFlight().getYear()
						|| now.getDayOfYear() + 1 == futureFlights[i].getTimeOfFlight().getYear()
						|| now.getDayOfYear() - 1 == futureFlights[i].getTimeOfFlight().getYear()) {
					removeToScedArray(futureFlights[i]);
				}
			}
		}
	}

	private void removeToScedArray(Flight flight) {
		Flight newFlight = new Flight(flight);
		if (flight.getFlightType().equals(FlightType.departure)) {
			takeOff[numOfTakeoff++] = newFlight;
		}
		if (flight.getFlightType().equals(FlightType.arrivale)) {
			arrivels[numOfArrivels++] = newFlight;
			}
		deleteFlight(futureFlights , newFlight);
		}
	
	private void deleteFlight(Flight[] futureFlights, Flight flight) {
		flight = null;
		numOfFutureFlights--;
		Flight[] temp = new Flight[numOfFutureFlights];
		for (int i = 0; i < numOfFutureFlights; i++) {
			if(futureFlights[i] != null ) {
				temp[i] = futureFlights[i];
			}
		}
		futureFlights = temp;
		
	}

	public String getAriivelsSched() {
		sortMethodByDate(arrivels, numOfArrivels);
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < numOfArrivels; i++) {
			res.append(arrivels[i].toString());
		}
		return res.toString();
	}

	public String getFutureFlightsSched() {
		sortMethodByDate(futureFlights, numOfFutureFlights);
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < numOfFutureFlights; i++) {
			res.append(futureFlights[i].toString());
		}
		return res.toString();
	}

	private void sortMethodByDate(Flight[] arr, int numOfFlights) {
		for (int i = 1; i < numOfFlights; i++) {
			for (int j = i; j > 0 && arr[j - 1].getTakeOff().isAfter(arr[j].getTakeOff()); j--) {
				Flight temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}
	}

	public void save(String address) throws FileNotFoundException {
		File file = new File(address);
		PrintWriter printer = new PrintWriter(file);
		printer.println(numOfTakeoff);
		for (int i = 0; i < numOfTakeoff; i++) {
			takeOff[i].save(printer);
		}
		printer.println(numOfArrivels);
		for (int i = 0; i < numOfArrivels; i++) {
			arrivels[i].save(printer);
		}
		printer.println(numOfFutureFlights);
		for (int i = 0; i < numOfFutureFlights; i++) {
			futureFlights[i].save(printer);
		}
		printer.close();
	}

	public void read(Scanner scan) {
		int numberOfTakeofFlightToAdd = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < numberOfTakeofFlightToAdd; i++) {
			String carrierName = scan.nextLine();
			String dest = scan.nextLine();
			String orign = scan.nextLine();
			String carrierCode = scan.nextLine();
			int fltNum = Integer.parseInt(scan.next());
			int year = Integer.parseInt(scan.next());
			int month = Integer.parseInt(scan.next());
			int dayOfMonth = Integer.parseInt(scan.next());
			int hour = Integer.parseInt(scan.next());
			int minute = Integer.parseInt(scan.next());
			LocalDateTime takeOffTime;
			double flightTime = Double.parseDouble(scan.next());
			String cityDest = scan.nextLine();
			String cityOrigen = scan.nextLine();
			String countryDest = scan.nextLine();
			String countryOrigen = scan.nextLine();
			takeOffTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
			Flight flight = new Flight(carrierName, dest, orign, carrierCode, fltNum, takeOffTime, flightTime,
					cityOrigen, countryOrigen, cityDest, countryDest);
			addFlight(flight);
			scan.nextLine();

		}
		int numberOfArivallFlightToAdd = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < numberOfArivallFlightToAdd; i++) {
			String carrierName = scan.next();
			String dest = scan.next();
			String orign = scan.next();
			String carrierCode = scan.next();
			int fltNum = Integer.parseInt(scan.next());
			int year = Integer.parseInt(scan.next());
			int month = Integer.parseInt(scan.next());
			int dayOfMonth = Integer.parseInt(scan.next());
			int hour = Integer.parseInt(scan.next());
			int minute = Integer.parseInt(scan.next());
			LocalDateTime takeOffTime;
			double flightTime = Double.parseDouble(scan.next());
			String cityDest = scan.nextLine();
			String cityOrigen = scan.nextLine();
			String countryDest = scan.nextLine();
			String countryOrigen = scan.nextLine();
			takeOffTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
			Flight flight = new Flight(carrierName, dest, orign, carrierCode, fltNum, takeOffTime, flightTime,
					cityOrigen, countryOrigen, cityDest, countryDest);
			addFlight(flight);
		}
		int numberOfFutureFlightsToAdd = scan.nextInt();
		for (int i = 0; i < numberOfFutureFlightsToAdd; i++) {
			String carrierName = scan.next();
			String dest = scan.next();
			String orign = scan.next();
			String carrierCode = scan.next();
			int fltNum = Integer.parseInt(scan.next());
			int year = Integer.parseInt(scan.next());
			int month = Integer.parseInt(scan.next());
			int dayOfMonth = Integer.parseInt(scan.next());
			int hour = Integer.parseInt(scan.next());
			int minute = Integer.parseInt(scan.next());
			LocalDateTime takeOffTime;
			double flightTime = Double.parseDouble(scan.next());
			String cityDest = scan.nextLine();
			String cityOrigen = scan.nextLine();
			String countryDest = scan.nextLine();
			String countryOrigen = scan.nextLine();
			takeOffTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
			Flight flight = new Flight(carrierName, dest, orign, carrierCode, fltNum, takeOffTime, flightTime,
					cityOrigen, countryOrigen, cityDest, countryDest);
			addFlight(flight);
		}
		scan.nextLine();

	}

	public void searchByCountry(String countryName, boolean arrivalQ) {
		if (arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfArrivels; i++) {
				if (arrivels[i].getCountryOrigen().equalsIgnoreCase(countryName)) {
					resultsCount++;
					System.out.println(arrivels[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else if (!arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfTakeoff; i++) {
				if (takeOff[i].getCountryOrigen().equalsIgnoreCase(countryName)) {
					resultsCount++;
					System.out.println(takeOff[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else {
			System.out.println("system is Empty");
		}

	}

	public void searchByCompany(String companyName, boolean arrivalQ) {
		if (arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfArrivels; i++) {
				if (arrivels[i].getCarrierName().equalsIgnoreCase(companyName)) {
					resultsCount++;
					System.out.println(arrivels[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else if (!arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfTakeoff; i++) {
				if (takeOff[i].getCarrierName().equalsIgnoreCase(companyName)) {
					resultsCount++;
					System.out.println(takeOff[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else {
			System.out.println("system is Empty");
		}
		System.out.println();
	}

	public void searchByFlightNumber(int flightNumber, boolean arrivalQ) {
		if (arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfArrivels; i++) {
				if (arrivels[i].getFltNum() == flightNumber) {
					resultsCount++;
					System.out.println(arrivels[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else if (!arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfTakeoff; i++) {
				if (takeOff[i].getFltNum() == flightNumber) {
					resultsCount++;
					System.out.println(takeOff[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else {
			System.out.println("system is Empty");
		}
		System.out.println();
	}

	public void searchByFlightDate(LocalDate date, boolean arrivalQ) {
		if (arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfArrivels; i++) {
				LocalDate tempDate = arrivels[i].getTakeOff().toLocalDate();
				if (tempDate == date) {
					resultsCount++;
					System.out.println(arrivels[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else if (!arrivalQ) {
			int resultsCount = 0;
			for (int i = 0; i < numOfTakeoff; i++) {
				LocalDate tempDate = arrivels[i].getTakeOff().toLocalDate();
				if (tempDate == date) {
					resultsCount++;
					System.out.println(takeOff[i]);
				}

			}
			System.out.println("the system found " + resultsCount + " results.");
		} else {
			System.out.println("system is Empty");
		}
		System.out.println();
	}
}
