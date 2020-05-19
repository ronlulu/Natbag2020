package flightSched;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		Airport airPort = new Airport("TLV");
		hardCoded(airPort);
		int choice;
		do {
			choice = allChoices(scan);
			switch (choice) {
			case 1:
				String carrierName , carrierCode , dest , orign ;
				int fltNum , year , month , dayOfMonth , hour , minute  ;
				double flightTime;
				LocalDateTime takeOff ;
				System.out.println("add carrier name ");
				carrierName = scan.next();
				System.out.println("add air port destanation ");
				dest = scan.next();
				System.out.println("add city destanation");
				String cityDest = scan.next();
				System.out.println("add country destanation");
				String countryDest = scan.next();
				System.out.println("add air port orign ");
				orign = scan.next();
				System.out.println("add city Origen");
				String cityOrigen = scan.next();
				System.out.println("add country Origen");
				String countryOrigen = scan.next();
				System.out.println("add carrier code ");
				carrierCode = scan.next();
				System.out.println("add flight number ");
				fltNum = scan.nextInt();
				System.out.println("enter the date : ");
				System.out.println("year ? : ");
				year = scan.nextInt();
				System.out.println( "month ?");
				month = scan.nextInt();
				System.out.println("day ? ");
				dayOfMonth = scan.nextInt();
				System.out.println("hour ? ");
				hour = scan.nextInt();
				System.out.println("minute ?");
				minute = scan.nextInt();
				
				
				
				
				takeOff = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
				System.out.println("how long the flight ? ");
				flightTime = scan.nextDouble();
				Flight newFlight = new Flight(carrierName, dest, orign, carrierCode, fltNum, takeOff, flightTime , cityOrigen ,countryOrigen ,cityDest , countryDest);
				if (airPort.addFlight(newFlight)) {
					System.out.println("flight added ");
				}
				else {
					System.out.println("failed to add ");
				}
				break;
			case 2:
				System.out.println(airPort.getTakeOffSched());
				break;
			case 3:
				System.out.println(airPort.getAriivelsSched());
				break;

			case 4: {
				scan.nextLine();
				System.out.println("enter the path of the file ");
				String path = scan.nextLine();
				airPort.save(path);
				System.out.println("information saved ");
				break;
			}
			case 5: {
				scan.nextLine();
				System.out.println("enter the path of the file ");
				String path = scan.nextLine();
				File file = new File(path);
				Scanner sc = new Scanner(file);
				airPort.read(sc);
				sc.close();
				break;
			}
			case 6: {
				int chs1;
				System.out.println("1. Search by Country ");
				System.out.println("2. Search by Company");
				System.out.println("3. Search by Flight Number");
				System.out.println("Enter your choise:");
				
				do {
					chs1 = scan.nextInt();
					switch(chs1) {
					case 1: {
						System.out.println("Please write the orign airport name:");
						String countryName = scan.nextLine();
					//	airPort.searchByCountry(countryName);
					}
					case 2: {
						System.out.println("Please write the Company name:");
						String companyName = scan.nextLine();
					//	airPort.searchByCompany(companyName);
					}
					case 3: {
						System.out.println("Please write the required Flight Number:");
						String countryName = scan.nextLine();
					//	airPort.searchByFlightNumber(countryName);
					}
						
					}
				}while (chs1!=0);
			}
			default:
				break;
			}
		} while (choice != 0);
	}

	public static int allChoices(Scanner scan) {
		
		System.out.println("Enter Your Choise:");
		System.out.println("1. ---> add flight ");
		System.out.println("2. ---> get the take off schedule");
		System.out.println("3. ---> get the arrivels schedule");
		System.out.println("4. ---> save the information on a file ");
		System.out.println("5. ---> read information from file ");
		System.out.println("6. ---> Search for Arrivel Flight");
		System.out.println("7. ---> Search for Departing Flight");
		System.out.println("8. ---> Search for future Flight");
		System.out.println("0. ---> to Exit");
		return scan.nextInt();
	}

	public static void hardCoded(Airport airport) {
		LocalDateTime time1 = LocalDateTime.of(2020, 05, 20, 00, 45);
		Flight flight1 = new Flight("ELAL", "JFK", "TLV", "LY", 001, time1, 9, "TEL AVIV" ,"ISRAEL" ,"NEW YORK" , "USA");
		LocalDateTime time2 = LocalDateTime.of(2020, 05, 20, 10, 10);
		Flight flight2 = new Flight("ELAL", "LHR", "TLV", "LY", 315, time2, 9, "TEL AVIV" ,"ISRAEL" ,"LONDON" , "ENGLAND");
		airport.addFlight(flight2);
		airport.addFlight(flight1);
		
		System.out.println("flights loaded from hard code ");
	}

}
