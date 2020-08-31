package flightSched;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class htmlMain {
	public static void main(String[] args) {

		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
		boolean isDepartures = args.length > 1 && args[1].equalsIgnoreCase("departures");
		Airport airport = new Airport("TLV");
		Main.hardCoded(airport);
		if (isDepartures) {
			airport.searchByCountry(args[3], false);
			if (isHtml)
				System.out.println("<br>");
			airport.searchByFlightNumber(Integer.parseInt(args[5]), false);
			if (isHtml)
				System.out.println("<br>");
			airport.searchByCompany(args[2], false);
			if (isHtml)
				System.out.println("<br>");
			LocalDate temp = LocalDate.of(Integer.parseInt(args[8]), Integer.parseInt(args[7]), Integer.parseInt(args[6]));
			airport.searchByFlightDate(temp, false);
			if (isHtml)
				System.out.println("<br>");
		}
		else {
			airport.searchByCountry(args[3], true);
			if (isHtml)
				System.out.println("<br>");
			airport.searchByFlightNumber(Integer.parseInt(args[5]), true);
			if (isHtml)
				System.out.println("<br>");
			airport.searchByCompany(args[2], true);
			if (isHtml)
				System.out.println("<br>");
			LocalDate temp = LocalDate.of(Integer.parseInt(args[8]), Integer.parseInt(args[7]), Integer.parseInt(args[6]));
			airport.searchByFlightDate(temp, true);
			if (isHtml)
				System.out.println("<br>");
		}
		
	}
}
