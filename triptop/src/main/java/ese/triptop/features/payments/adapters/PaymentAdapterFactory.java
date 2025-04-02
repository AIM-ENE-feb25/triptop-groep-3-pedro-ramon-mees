package ese.triptop.features.payments.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentAdapterFactory {

    private final StripePaymentAdapter stripePaymentAdapter;
    private final PaypalPaymentAdapter paypalPaymentAdapter;

    private int failureCount = 0;
    private long lastFailureTime = 0;
    private static final int MAX_FAILURES = 5;
    private static final long RESET_TIMEOUT_MS = 30000; // 30 seconds

    @Autowired
    public PaymentAdapterFactory(StripePaymentAdapter stripePaymentAdapter,
                                 PaypalPaymentAdapter paypalPaymentAdapter) {
        this.stripePaymentAdapter = stripePaymentAdapter;
        this.paypalPaymentAdapter = paypalPaymentAdapter;
    }

    public IPaymentAdapter getPaymentAdapter() {
        // If Stripe adapter is requested, apply circuit breaker pattern
        if (isCircuitClosed()) {
            try {
                if (stripePaymentAdapter.isAvailable()) {
                    resetCircuit();
                    return stripePaymentAdapter;
                } else {
                    recordFailure();
                    // Recursive call to getPaymentAdapter to try again
                    return getPaymentAdapter();
                }
            } catch (Exception e) {
                recordFailure();
                // Fallback to PayPal
                return paypalPaymentAdapter;
            }
        } else {
            // Circuit is open, fallback to PayPal
            return paypalPaymentAdapter;
        }
    }

    private boolean isCircuitClosed() {
        if (failureCount >= MAX_FAILURES) {
            // Check if enough time has passed to try again
            if (System.currentTimeMillis() - lastFailureTime > RESET_TIMEOUT_MS) {
                // Try half-open state
                return true;
            }
            return false; // Circuit open
        }
        return true; // Circuit closed
    }

    private void recordFailure() {
        failureCount++;
        lastFailureTime = System.currentTimeMillis();
    }

    private void resetCircuit() {
        failureCount = 0;
    }
}