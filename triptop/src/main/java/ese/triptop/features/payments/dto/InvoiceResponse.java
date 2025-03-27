package ese.triptop.features.payments.dto;

public record InvoiceResponse(String invoiceId, String currency, String amount, String source) {

    @Override
    public String toString() {
        return "InvoiceResponse{" +
                "invoiceId='" + invoiceId + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                '}';
    }
}
