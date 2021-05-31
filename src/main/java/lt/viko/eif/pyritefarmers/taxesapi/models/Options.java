package lt.viko.eif.pyritefarmers.taxesapi.models;

import java.time.LocalDate;
import java.util.Date;

public class Options {
    private int id;
    private int userId;
    private String market;
    private String originPlace;
    private String destinationPlace;
    private int distance;
    private int price;
    private String currency;
    private boolean direct;
    private LocalDate start;
    private LocalDate end;

    public Options(int id, int userId, String market, String originPlace, String destinationPlace, int distance,
                   int price, String currency, boolean direct, LocalDate start, LocalDate end) {
        this.id = id;
        this.userId = userId;
        this.market = market;
        this.originPlace = originPlace;
        this.destinationPlace = destinationPlace;
        this.distance = distance;
        this.price = price;
        this.currency = currency;
        this.direct = direct;
        this.start = start;
        this.end = end;
    }

    public Options(int userId, String market, String originPlace, String destinationPlace, int distance, int price,
                   String currency, boolean direct, LocalDate start, LocalDate end) {
        this.userId = userId;
        this.market = market;
        this.originPlace = originPlace;
        this.destinationPlace = destinationPlace;
        this.distance = distance;
        this.price = price;
        this.currency = currency;
        this.direct = direct;
        this.start = start;
        this.end = end;
    }

    public Options() {
        this.id = 0;
        this.userId = 0;
        this.market = "LT";
        this.originPlace = "LT-sky";
        this.destinationPlace = "PR-sky";
        this.distance = 50;
        this.price = 500;
        this.currency = "EUR";
        this.direct = true;
        this.start = java.time.LocalDate.now();
        this.end = java.time.LocalDate.now().plusDays(7);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Options{" +
                "id=" + id +
                ", userId=" + userId +
                ", originPlace='" + originPlace + '\'' +
                ", destinationPlace='" + destinationPlace + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", currentcy='" + currency + '\'' +
                ", direct=" + direct +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
