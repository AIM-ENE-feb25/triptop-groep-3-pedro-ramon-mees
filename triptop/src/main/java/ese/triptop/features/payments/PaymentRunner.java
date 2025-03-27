package ese.triptop.features.payments;

import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;
import ese.triptop.features.payments.services.IPaymentService;
import ese.triptop.features.payments.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentRunner {

    private final IPaymentService paymentService;

    @Autowired
    public PaymentRunner(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    public void run() {
        InvoiceRequest invoiceRequest = new InvoiceRequest("cus_S1MthklVNcSUB6", 1000L, "EUR");

        InvoiceResponse response = paymentService.processInvoice(invoiceRequest);

        System.out.println("Payment processed: " + response);
    }
}
