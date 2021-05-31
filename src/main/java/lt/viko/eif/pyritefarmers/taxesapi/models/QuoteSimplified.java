package lt.viko.eif.pyritefarmers.taxesapi.models;

import java.util.Date;
import java.util.List;

public class QuoteSimplified {
    private double minPrice;
    private boolean direct;
    private String origin;
    private String destination;
    private Date departureDate;
    private List<String> carriers;

    public QuoteSimplified(
            double minPrice, boolean direct, String origin, String destination, Date departureDate, List<String> carriers) {
        this.minPrice = minPrice;
        this.direct = direct;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.carriers = carriers;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
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
                ", carriers=" + carriers +
                '}';
    }
}
