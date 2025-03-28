package ese.triptop.features.payments.adapters;

import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;

public interface IPaymentAdapter {
    InvoiceResponse processInvoicePayment(InvoiceRequest request);
    boolean isAvailable();
}
