package ese.triptop;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import ese.triptop.features.stripe.StripeTesting;
import ese.triptop.features.wiremock.WiremockTesting;
import ese.triptop.prototype.PrototypeRunner;
import ese.triptop.prototype.service.facade.FlightFacade;
import ese.triptop.prototype.service.strategy.contracts.IFlightSearchStrategy;
import ese.triptop.prototype.domain.Flight;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriptopApplication {

	private final StripeTesting stripeTesting;
	private final WiremockTesting wiremockTesting;
	private final PrototypeRunner prototypeRunner;
	private final FlightFacade flightFacade;
	private final Map<String, IFlightSearchStrategy> flightStrategies;

	@Autowired
	public TriptopApplication(StripeTesting stripeTesting, 
							WiremockTesting wiremockTesting,
							PrototypeRunner prototypeRunner, 
							FlightFacade flightFacade,
							Map<String, IFlightSearchStrategy> flightStrategies) {
		this.flightStrategies = flightStrategies;
		this.stripeTesting = stripeTesting;
		this.wiremockTesting = wiremockTesting;
		this.prototypeRunner = prototypeRunner;
		this.flightFacade = flightFacade;
	}

	private TriptopApplication runME() {
		flightFacade.setSearchStrategy(flightStrategies.get("cheapestFlightStrategy"));
		Flight bestFlight = flightFacade.findBestFlight("AMS", "LHR", "2024-02-01Z", 2);
		if (bestFlight != null) {
			System.out.println("Best flight found: " + bestFlight);
		} else {
			System.out.println("No flights found.");
		}
		//prototypeRunner.run();
		return this;
	}

	public static void main(String[] args) {
		SpringApplication.run(TriptopApplication.class, args)
 				.getBean(TriptopApplication.class)
				.runME();
	}

	// private TriptopApplication runStripeTest() {
	// 	try {
	// 		stripeTesting.createProduct("Test Product", "This is a test product", "EUR", 2000L);
	// 		Customer c = stripeTesting.createCustomer("Test Customer", "test@test.com");
	// 		stripeTesting.createInvoice(c.getId());
	// 	} catch (StripeException e) {
	// 		throw new RuntimeException(e);
	// 	}
	// 	return this;
	// }

	private TriptopApplication runWiremockTests() {
		wiremockTesting.run();
		return this;
	}




	// private TriptopApplication runFactoryDemo() {
	// 	System.out.println("Running Factory Demonstration...");
	// 	factoryRunner.run();
	// 	return this;
	// }

//   private TriptopApplication runPaymentDemo() {
// 		System.out.println("Running Payment Demo...");
// 		paymentRunner.run();
// 		return this;
// 	}
}
