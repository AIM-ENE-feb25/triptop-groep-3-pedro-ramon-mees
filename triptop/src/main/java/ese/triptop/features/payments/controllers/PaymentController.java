package ese.triptop.features.payments.controllers;

import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;
import ese.triptop.features.payments.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Endpoint to create an invoice.
     *
     * @param invoiceRequest The request body containing invoice details.
     * @return invoiceResponse The response containing the invoice details.
     */
    @PostMapping("/invoice")
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return paymentService.processInvoice(invoiceRequest);
    }

}
