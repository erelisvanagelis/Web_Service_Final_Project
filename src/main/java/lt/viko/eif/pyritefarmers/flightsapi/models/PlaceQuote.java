package lt.viko.eif.pyritefarmers.flightsapi.models;

public class PlaceQuote {
    private String name;
    private String type;
    private int placeId;
    private String iataCode;
    private String skyscannerCode;
    private String cityName;
    private String cityId;
    private String countryName;

    public PlaceQuote(String name, String type, int placeId, String iataCode, String skyscannerCode, String cityName
            , String cityId, String countryName) {
        this.name = name;
        this.type = type;
        this.placeId = placeId;
        this.iataCode = iataCode;
        this.skyscannerCode = skyscannerCode;
        this.cityName = cityName;
        this.cityId = cityId;
        this.countryName = countryName;
    }

    public PlaceQuote() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getSkyscannerCode() {
        return skyscannerCode;
    }

    public void setSkyscannerCode(String skyscannerCode) {
        this.skyscannerCode = skyscannerCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "PlaceQuote{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", placeId=" + placeId +
                ", iataCode='" + iataCode + '\'' +
                ", skyscannerCode='" + skyscannerCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
