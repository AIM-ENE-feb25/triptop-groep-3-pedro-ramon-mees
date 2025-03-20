package triptop.ese.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StripeTesting {

    @Value("${stripe.api_key}")
    private String apiKey;

    // Of-course would prefer an enum for currency here
    public void createProduct(String name, String description, String currency, Long price) throws StripeException {
        Stripe.apiKey = apiKey;

        ProductCreateParams productParams =
                ProductCreateParams.builder()
                        .setName(name)
                        .setDescription(description)
                        .build();

        Product productObj = Product.create(productParams);

        System.out.println("Product created: " + productObj.getId());

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setProduct(productObj.getId())
                        .setCurrency(currency)
                        .setUnitAmount(price)
                        .build();

        Price priceObj = Price.create(priceParams);

        System.out.println("Price created: " + priceObj.getId() + " for product: " + priceObj.getProduct());
    }


}
