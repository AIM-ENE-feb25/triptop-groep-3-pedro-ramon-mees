package ese.triptop.features.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeTesting {

//    @Value("${stripe.api_key}")
    private String apiKey;

    // Of-course would prefer an enum for currency here
    public Product createProduct(String name, String description, String currency, Long price) throws StripeException {
        Stripe.apiKey = apiKey;

        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName(name)
                .setDescription(description)
                .build();

        Product productObj = Product.create(productParams);

        System.out.println("Product created: " + productObj.getId());

        PriceCreateParams priceParams = PriceCreateParams.builder()
                .setProduct(productObj.getId())
                .setCurrency(currency)
                .setUnitAmount(price)
                .build();

        Price priceObj = Price.create(priceParams);

        System.out.println("Price created: " + priceObj.getId() + " for product: " + priceObj.getProduct());
        return productObj;
    }

    public Customer createCustomer(String name, String email) throws StripeException {
        Stripe.apiKey = apiKey;

        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(name)
                .setEmail(email)
                .build();

        Customer customer = Customer.create(params);

        System.out.println("Customer created: " + customer.getId());
        return customer;
    }

    public Invoice createInvoice(String customerID) throws StripeException {
        Stripe.apiKey = apiKey;

        InvoiceCreateParams invoiceParams = InvoiceCreateParams.builder()
                .setCustomer(customerID)
                .build();

        Invoice invoice = Invoice.create(invoiceParams);

        System.out.println("Invoice created: " + invoice.getId());
        return invoice;
    }

}