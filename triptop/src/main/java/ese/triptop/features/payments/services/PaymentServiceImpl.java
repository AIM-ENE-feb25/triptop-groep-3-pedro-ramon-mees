package ese.triptop.features.payments.services;

import ese.triptop.features.payments.adapters.IPaymentAdapter;
import ese.triptop.features.payments.adapters.PaymentAdapterFactory;
import ese.triptop.features.payments.dto.InvoiceRequest;
import ese.triptop.features.payments.dto.InvoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService{

    private final IPaymentAdapter paymentAdapter;

    @Autowired
    public PaymentServiceImpl(PaymentAdapterFactory paymentAdapterFactory) {
        paymentAdapter = paymentAdapterFactory.getPaymentAdapter();
    }

    @Override
    public InvoiceResponse processInvoice(InvoiceRequest request) {
        return paymentAdapter.processInvoicePayment(request);
    }


}
