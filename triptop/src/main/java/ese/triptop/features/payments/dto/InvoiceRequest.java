package ese.triptop.features.payments.dto;

public record InvoiceRequest(String userId, Long amount, String currency) {

    @Override
    public String toString() {
        return "InvoiceRequest{" +
                "userId='" + userId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
