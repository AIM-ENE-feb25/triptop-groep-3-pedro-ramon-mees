package triptop.ese.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StripeApplication {

    private final StripeTesting stripeTesting;

    @Autowired
    public StripeApplication(StripeTesting stripeTesting) {
        this.stripeTesting = stripeTesting;
    }

    public static void main(String[] args) {
        SpringApplication.run(StripeApplication.class, args)
            .getBean(StripeApplication.class)
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
