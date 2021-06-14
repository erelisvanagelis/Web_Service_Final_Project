package lt.viko.eif.pyritefarmers.flightsapi.models;

public class Place {
    private String placeId;
    private String placeName;
    private String countryId;
    private String regionId;
    private String cityId;
    private String countryName;

    public Place(String placeId, String placeName, String countryId, String regionId, String cityId, String countryName) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.countryId = countryId;
        this.regionId = regionId;
        this.cityId = cityId;
        this.countryName = countryName;
    }

    public Place(boolean grybas) {
        this.placeId = "PLQ-sky";
        this.placeName = "Palanga International";
        this.countryId = "LT-sky";
        this.regionId = "";
        this.cityId = "PALA-sky";
        this.countryName = "Lithuania";
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
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
        return "Place{" +
                "placeId='" + placeId + '\'' +
                ", placeName='" + placeName + '\'' +
                ", countryId='" + countryId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
