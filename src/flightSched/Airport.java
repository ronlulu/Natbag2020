package flightSched;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Airport {
	private Flight[] takeOff;
	private Flight[] arrivels;
	private String airportCode;
	private int numOfArrivels, numOfTakeoff;

	public Airport(String airportCode) {
		this.airportCode = airportCode;
		takeOff = new Flight[20];
		arrivels = new Flight[20];
	}

	public boolean addFlight(Flight newFlight) {
		if (newFlight.getOrign().equalsIgnoreCase(airportCode)) {
			takeOff[numOfTakeoff++] = newFlight;
			return true;
		}
		if (newFlight.getDest().equalsIgnoreCase(airportCode)) {
			arrivels[numOfArrivels++] = newFlight;
			return true;
		} else
			return false;

	}

	public String getTakeOffSched() {
		sortMethodByDate(takeOff, numOfTakeoff);
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < numOfTakeoff; i++) {
			res.append(takeOff[i].toString());
		}
		return res.toString();
	}

	public String getAriivelsSched() {
		sortMethodByDate(arrivels, numOfArrivels);
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < numOfArrivels; i++) {
			res.append(arrivels[i].toString());
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
			Flight flight = new Flight(carrierName, dest, orign, carrierCode, fltNum, takeOffTime, flightTime , cityOrigen ,countryOrigen ,cityDest , countryDest );
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
			Flight flight = new Flight(carrierName, dest, orign, carrierCode, fltNum, takeOffTime, flightTime, cityOrigen ,countryOrigen ,cityDest , countryDest);
			addFlight(flight);
			scan.nextLine();
		}
	}
}
