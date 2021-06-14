package lt.viko.eif.pyritefarmers.flightsapi.models;

public class Carrier {
    private int carrierId;
    private String name;

    public Carrier(int carrierId, String name) {
        this.carrierId = carrierId;
        this.name = name;
    }

    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "carrierId=" + carrierId +
                ", name='" + name + '\'' +
                '}';
    }
}
