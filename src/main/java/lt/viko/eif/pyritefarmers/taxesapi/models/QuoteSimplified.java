package lt.viko.eif.pyritefarmers.taxesapi.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class QuoteSimplified {
    private double minPrice;
    private boolean direct;
    private String origin;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime quoteDateTime;
    private List<String> carriers;

    public QuoteSimplified(double minPrice, boolean direct, String origin, String destination,
                           LocalDateTime departureDate, LocalDateTime quoteDateTime, List<String> carriers) {
        this.minPrice = minPrice;
        this.direct = direct;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.quoteDateTime = quoteDateTime;
        this.carriers = carriers;
    }

    public QuoteSimplified() {

    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getQuoteDateTime() {
        return quoteDateTime;
    }

    public void setQuoteDateTime(LocalDateTime quoteDateTime) {
        this.quoteDateTime = quoteDateTime;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public List<String> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<String> carriers) {
        this.carriers = carriers;
    }

    @Override
    public String toString() {
        return "QuoteSimplified{" +
                "minPrice=" + minPrice +
                ", direct=" + direct +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", quoteDateTime=" + quoteDateTime +
                ", carriers=" + carriers +
                '}';
    }
}
