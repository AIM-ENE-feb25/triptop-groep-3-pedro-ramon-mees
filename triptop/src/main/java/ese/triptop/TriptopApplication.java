package ese.triptop;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import ese.triptop.features.stripe.StripeTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriptopApplication {

	private final StripeTesting stripeTesting;

	@Autowired
	public TriptopApplication(StripeTesting stripeTesting) {
		this.stripeTesting = stripeTesting;
	}

	public static void main(String[] args) {
		SpringApplication.run(TriptopApplication.class, args)
				.getBean(TriptopApplication.class)
				.runStripeTest();
	}

	private void runStripeTest() {
		try {
			stripeTesting.createProduct("Test Product", "This is a test product", "EUR", 2000L);
			Customer c = stripeTesting.createCustomer("Test Customer", "test@test.com");
			stripeTesting.createInvoice(c.getId());
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}
}
