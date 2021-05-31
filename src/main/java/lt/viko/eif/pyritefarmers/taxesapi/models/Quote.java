package lt.viko.eif.pyritefarmers.taxesapi.models;

import java.util.Date;
import java.util.List;

public class Quote {
    private int quoteId;
    private double minPrice;
    private boolean direct;
    private int originId;
    private int destinationId;
    private Date departureDate;
    private List<Carrier> carrierIds;

    public Quote(int quoteId, double minPrice, boolean direct, int originId, int destinationId, Date departureDate,
                 List<Carrier> carrierIds) {
        this.quoteId = quoteId;
        this.minPrice = minPrice;
        this.direct = direct;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureDate = departureDate;
        this.carrierIds = carrierIds;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
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

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public List<Carrier> getCarrierIds() {
        return carrierIds;
    }

    public void setCarrierIds(List<Carrier> carrierIds) {
        this.carrierIds = carrierIds;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", minPrice=" + minPrice +
                ", direct=" + direct +
                ", originId=" + originId +
                ", destinationId=" + destinationId +
                ", departureDate=" + departureDate +
                ", carrierIds=" + carrierIds +
                '}';
    }
}
