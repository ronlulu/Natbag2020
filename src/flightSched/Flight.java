package flightSched;


import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
	private String carrierName;
	private String airPortDest ;
	private String airPortOrigen ; 
	private int fltNum;
	private LocalDateTime takeOffDateTime;
	private double flightTime;
	private String carrierCode;
	private String countryOrigen;
	private String cityOrigen;
	private String cityDest;
	private String countryDest;
	 

	public Flight(String carrierName, String dest, String orign,String carrierCode ,int fltNum, LocalDateTime takeOff, double flightTime ,String cityOrigen , String countryOrigen , String cityDest , String countryDest) {
		this.carrierName = carrierName;
		this.airPortDest = dest;
		this.airPortOrigen = orign;
		this.carrierCode = carrierCode;
		this.fltNum = fltNum;
		this.takeOffDateTime = takeOff;
		this.flightTime = flightTime;
		this.cityDest = cityDest;
		this.cityOrigen = cityOrigen ; 
		this.countryDest = countryDest ; 
		this.countryOrigen = countryOrigen ;
	}

	public LocalDateTime getTakeOff() {
		return takeOffDateTime;
	}
	
	public String FormatTime() {
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	    String formattedDate = takeOffDateTime.format(myFormatObj);
		return formattedDate;
	}

	public String getDest() {
		return airPortDest;
	}

	public void setDest(String dest) {
		this.airPortDest = dest;
	}

	public String getOrign() {
		return airPortOrigen;
	}

	public void setOrign(String orign) {
		this.airPortOrigen = orign;
	}
	
	public String getCountryOrigen() {
		return countryOrigen;
	}

	public String getCountryDest() {
		return countryDest;
	}


	public int getFltNum() {
		return fltNum;
	}

	public void setFltNum(int fltNum) {
		this.fltNum = fltNum;
	}

	public String getCarrierName() {
		return carrierName;
	}

	@Override
	public String toString() {
		
		return "|| Carrier Name : " + carrierName + " || destantion : " + cityDest +  " || flight number : " +carrierCode + fltNum
				+ " || Departure : "+ FormatTime() + " || flight duration : "
				+ flightTime +" || \n";
	}

	public void save(PrintWriter printer) {
		printer.println(carrierName);
		printer.println(airPortDest);
		printer.println(airPortOrigen);
		printer.println(carrierCode);
		printer.println(fltNum);
		saveDate(printer);
		printer.println(flightTime);
		printer.println(cityDest);
		printer.println(cityOrigen);
		printer.println(countryDest);
		printer.println(countryOrigen);
	}

	private void saveDate(PrintWriter printer) {
		printer.println(takeOffDateTime.getYear());
		printer.println(takeOffDateTime.getMonthValue());
		printer.println(takeOffDateTime.getDayOfMonth());
		printer.println(takeOffDateTime.getHour());
		printer.println(takeOffDateTime.getMinute());
	}
	


}
