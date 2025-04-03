package ese.triptop.prototype.factory;

import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlightAdapterFactory {

    private final List<IFlightAdapter> flightAdapters;

    // Sadly made w/ help of GiPiTi, sorry ~M
    private final Map<Class<? extends IFlightAdapter>, CircuitBreakerState> circuitStates = new HashMap<>();

    private static final int MAX_FAILURES = 5;
    private static final long RESET_TIMEOUT_MS = 30000; // 30 seconds

    @Autowired
    public FlightAdapterFactory(List<IFlightAdapter> flightAdapters) {
        this.flightAdapters = flightAdapters;
        // Initialize circuit breaker state for each adapter
        for (IFlightAdapter adapter : flightAdapters) {
            circuitStates.put(adapter.getClass(), new CircuitBreakerState());
        }
    }

    public List<IFlightAdapter> getFlightAdapters() {
        return flightAdapters.stream()
                .filter(adapter -> {
                    CircuitBreakerState state = circuitStates.get(adapter.getClass());
                    if (isCircuitClosed(state)) {
                        try {
                            boolean available = adapter.isAvailable();
                            if (available) {
                                resetCircuit(adapter.getClass());
                                return true;
                            } else {
                                recordFailure(adapter.getClass());
                                return false;
                            }
                        } catch (Exception e) {
                            recordFailure(adapter.getClass());
                            return false;
                        }
                    }
                    return false;
                })
                .toList();
    }

    // Sadly made w/ help of GiPiTi, sorry ~M
    public <T extends IFlightAdapter> IFlightAdapter getFlightAdapter(Class<T> adapterClass) {
        CircuitBreakerState state = circuitStates.get(adapterClass);
        if (state != null && isCircuitClosed(state)) {
            for (IFlightAdapter adapter : flightAdapters) {
                if (adapterClass.isInstance(adapter)) {
                    try {
                        boolean available = adapter.isAvailable();
                        if (available) {
                            resetCircuit(adapterClass);
                            return adapter;
                        } else {
                            recordFailure(adapterClass);
                        }
                    } catch (Exception e) {
                        recordFailure(adapterClass);
                    }
                }
            }
        }

        return getAvailableAdapter();
    }

    public IFlightAdapter getAvailableAdapter() {
        // Try to find any adapter that's available
        for (IFlightAdapter adapter : flightAdapters) {
            CircuitBreakerState state = circuitStates.get(adapter.getClass());
            if (isCircuitClosed(state)) {
                try {
                    boolean available = adapter.isAvailable();
                    if (available) {
                        resetCircuit(adapter.getClass());
                        return adapter;
                    } else {
                        recordFailure(adapter.getClass());
                    }
                } catch (Exception e) {
                    recordFailure(adapter.getClass());
                }
            }
        }

        if (!flightAdapters.isEmpty()) {
            System.out.println("No available adapters - forcing first adapter");
            return flightAdapters.get(0);
        }

        throw new IllegalStateException("No flight adapters available");
    }

    private boolean isCircuitClosed(CircuitBreakerState state) {
        if (state.failureCount >= MAX_FAILURES) {
            if (System.currentTimeMillis() - state.lastFailureTime > RESET_TIMEOUT_MS) {
                return true; // Half-open state - try again
            }
            return false; // Circuit open
        }
        return true; // Circuit closed
    }

    private void recordFailure(Class<? extends IFlightAdapter> adapterClass) {
        CircuitBreakerState state = circuitStates.get(adapterClass);
        if (state != null) {
            state.failureCount++;
            state.lastFailureTime = System.currentTimeMillis();
        }
    }

    private void resetCircuit(Class<? extends IFlightAdapter> adapterClass) {
        CircuitBreakerState state = circuitStates.get(adapterClass);
        if (state != null) {
            state.failureCount = 0;
        }
    }

    private static class CircuitBreakerState {
        int failureCount = 0;
        long lastFailureTime = 0;
    }
}