package lt.viko.eif.pyritefarmers.taxesapi.APIs;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lt.viko.eif.pyritefarmers.taxesapi.models.Options;
import lt.viko.eif.pyritefarmers.taxesapi.models.Place;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SkyScanner {
    public static JSONObject getBrowseQuotesService(Options options) throws IOException, ParseException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0" +
                        "/" + options.getMarket() +
                        "/" + options.getCurrency() +
                        "/en-US" +
                        "/" + options.getOriginPlace() +
                        "/" + options.getDestinationPlace() +
                        "/anytime?inboundpartialdate=anytime")
                .get()
                .addHeader("x-rapidapi-key", "c0dd1b869fmsh6b647aa42d660f4p1c85b7jsn5d757cf79a06")
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject)parse.parse(responseString);
        System.out.println(responseString);

        return jsonObject;
    }

    public static JSONObject getListPlacesService(String market, String query) throws IOException, ParseException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0" +
                        "/" + market +
                        "/EUR" +
                        "/en-GB" +
                        "/?query=" + query
                        )
                .get()
                .addHeader("x-rapidapi-key", "c0dd1b869fmsh6b647aa42d660f4p1c85b7jsn5d757cf79a06")
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject)parse.parse(responseString);
        System.out.println(responseString);

        return jsonObject;
    }

    public static List<Place> getPlaces(String market, String query) throws IOException, ParseException {
        JSONObject jsonObject = getListPlacesService(market, query);
        JSONArray jsonArray = (JSONArray) jsonObject.get("Places");

        List<Place> places = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject placeJson = (JSONObject) jsonArray.get(i);
            places.add(new Place(
                    placeJson.get("PlaceId").toString(),
                    placeJson.get("PlaceName").toString(),
                    placeJson.get("CountryId").toString(),
                    placeJson.get("RegionId").toString(),
                    placeJson.get("CityId").toString(),
                    placeJson.get("CountryName").toString()
            ));
        }
        return places;
    }
}
