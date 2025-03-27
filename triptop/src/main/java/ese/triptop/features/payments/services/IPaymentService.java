package ese.triptop.features.payments.services;

import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;

public interface IPaymentService {
    InvoiceResponse processInvoice(InvoiceRequest request);
}
