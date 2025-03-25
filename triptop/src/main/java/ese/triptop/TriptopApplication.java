package ese.triptop;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import ese.triptop.features.stripe.StripeTesting;
import ese.triptop.features.wiremock.WiremockTesting;
import ese.triptop.onderzoeksvraag.ApiArchitectureRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriptopApplication {

	private final StripeTesting stripeTesting;
	private final WiremockTesting wiremockTesting;
	private final ApiArchitectureRunner apiArchitectureRunner;

	@Autowired
	public TriptopApplication(StripeTesting stripeTesting, WiremockTesting wiremockTesting,
							  ApiArchitectureRunner apiArchitectureRunner) {
		this.stripeTesting = stripeTesting;
		this.wiremockTesting = wiremockTesting;
		this.apiArchitectureRunner = apiArchitectureRunner;
	}

	public static void main(String[] args) {
		SpringApplication.run(TriptopApplication.class, args)
				.getBean(TriptopApplication.class)
//				.runStripeTest()
//				.runWiremockTests()
				.runApiArchitectureDemo();
	}

	private TriptopApplication runStripeTest() {
		try {
			stripeTesting.createProduct("Test Product", "This is a test product", "EUR", 2000L);
			Customer c = stripeTesting.createCustomer("Test Customer", "test@test.com");
			stripeTesting.createInvoice(c.getId());
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	private TriptopApplication runWiremockTests() {
		wiremockTesting.run();
		return this;
	}

	private TriptopApplication runApiArchitectureDemo() {
		System.out.println("Running API Architecture Demonstration...");
		apiArchitectureRunner.run();
		return this;
	}
}
