package flightSched;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightTest {
	static Flight flight;
	static LocalDateTime time;


	@BeforeEach
	void setUp() throws Exception {
		time = LocalDateTime.of(2020, 8, 25, 9, 51);
		flight = new Flight("ELAL", "JFK", "TLV", "LY", 001, time, 9, "TEL AVIV" ,"ISRAEL" ,"NEW YORK" , "USA");
	}

	@AfterEach
	void tearDown() throws Exception {
		time = null;
		flight = null;
	}

	@Test
	void testIsTodaysFlight() {
		LocalDateTime now = LocalDateTime.now();
		
		boolean expacted = false;
		boolean actual = false;
		
		if(now.getYear() == flight.getTimeOfFlight().getYear() ){
			if(now.getDayOfYear() + 1 == flight.getTimeOfFlight().getDayOfYear() || now.getDayOfYear() == flight.getTimeOfFlight().getDayOfYear() || now.getDayOfYear() - 1 == flight.getTimeOfFlight().getDayOfYear() )
			actual = true;
		}
		assertEquals(expacted, actual );
		
	}

	@Test
	void testGetFlightType() {
		assertEquals(Flight.FlightType.departure, flight.getFlightType());
	}

}
