package ese.triptop.features.payments.adapters;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.param.InvoiceCreateParams;
import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StripePaymentAdapter implements IPaymentAdapter {

//    @Value("${stripe.apiKey}")
    private String apiKey;

    @Override
    public InvoiceResponse processInvoicePayment(InvoiceRequest request) {
        try {
            Stripe.apiKey = apiKey;

            // Create an invoice item
            Map<String, String> metadata = Map.of(
                    "description", "Payment for Triptop services",
                    "amount", String.valueOf(request.amount()),
                    "currency", request.currency()
            );

            InvoiceCreateParams invoiceParams = InvoiceCreateParams.builder()
                    .setCustomer(request.userId())
                    .setMetadata(metadata)
                    .setCurrency(request.currency())
                    .build();

            Invoice invoice = Invoice.create(invoiceParams);

            return new InvoiceResponse(invoice.getId(), invoice.getCurrency(), invoice.getMetadata().get("amount") , "Stripe");
        } catch (Exception e) {
            System.out.println("Stripe Payment Adapter Error------------------------------------------");
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean isAvailable() {
        // Check if Stripe is available
        try {
            // Attempt to create a test customer or perform a simple API call
            Stripe.apiKey = apiKey;
            InvoiceCreateParams params = InvoiceCreateParams.builder()
                    .setCustomer("cus_S1QQnrgnvSUeRt")
                    .build();
            Invoice.create(params);
            return true;
        } catch (StripeException e) {
            return false;
        }
    }

}
