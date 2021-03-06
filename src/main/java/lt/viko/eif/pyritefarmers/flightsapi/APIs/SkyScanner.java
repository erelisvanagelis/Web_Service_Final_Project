package lt.viko.eif.pyritefarmers.flightsapi.APIs;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lt.viko.eif.pyritefarmers.flightsapi.models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with SkyScanner API and parses the data received
 */
public class SkyScanner {
    private static String key = "c0dd1b869fmsh6b647aa42d660f4p1c85b7jsn5d757cf79a06";

    /**
     * Calls browsequotes service, with parameters provided in the options object
     * @param options by which specified quote is returned
     * @return JSONObject of the response body
     * @throws IOException
     * @throws ParseException
     */
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
                .addHeader("x-rapidapi-key", key)
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject)parse.parse(responseString);
        System.out.println(responseString);

        return jsonObject;
    }

    /**
     * Calls autosuggest service, which returns places based on market and query
     * @param market example - "LT"
     * @param query example - "Lithuania"
     * @return JSONObject of the response body.
     * @throws IOException
     * @throws ParseException
     */
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
                .addHeader("x-rapidapi-key", key)
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject)parse.parse(responseString);
        System.out.println(responseString);

        return jsonObject;
    }

    /**
     * Calls autosuggest service, which returns places based on market and query and parses it to Place list
     * @param market example - "LT"
     * @param query example - "Lithuania"
     * @return List of places that fit the search results
     * @throws IOException
     * @throws ParseException
     */
    public static List<Place> getPlaces(String market, String query) throws IOException, ParseException {
        JSONObject jsonObject = getListPlacesService(market, query);
        JSONArray jsonArray = (JSONArray) jsonObject.get("Places");

        List<Place> places = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject placeJson = (JSONObject) o;
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

    /**
     * Parses JSONObject data that is returned from getBrowseQuotesService to PlaceQuote objects
     * @param jsonObject data that is returned from getBrowseQuotesService
     * @return a List of PlaceQuotes
     * @throws IOException
     * @throws ParseException
     */
    public static List<PlaceQuote> getPlaceQuotes(JSONObject jsonObject) throws IOException, ParseException {
        JSONArray jsonArray = (JSONArray) jsonObject.get("Places");

        List<PlaceQuote> places = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject placeJson = (JSONObject) o;
            PlaceQuote placeQuote = new PlaceQuote();
            placeQuote.setName(placeJson.get("Name").toString());
            placeQuote.setType(placeJson.get("Type").toString());
            placeQuote.setPlaceId(Integer.parseInt(placeJson.get("PlaceId").toString()));
            placeQuote.setSkyscannerCode(placeJson.get("SkyscannerCode").toString());

            if (placeQuote.getType().equals("Station")){
                placeQuote.setIataCode(placeJson.get("IataCode").toString());
                placeQuote.setCityName(placeJson.get("CityName").toString());
                placeQuote.setCityId(placeJson.get("CityId").toString());
                placeQuote.setCountryName(placeJson.get("CountryName").toString());
            }
            places.add(placeQuote);
        }
        return places;
    }

    /**
     * Parses JSONObject data that is returned from getBrowseQuotesService to Carrier objects
     * @param jsonObject data that is returned from getBrowseQuotesService
     * @return a List of PlaceQuotes
     * @throws IOException
     * @throws ParseException
     */
    public static List<Carrier> getCarriers(JSONObject jsonObject) throws IOException, ParseException {
        JSONArray jsonArray = (JSONArray) jsonObject.get("Carriers");

        List<Carrier> carriers = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject carrierJson = (JSONObject) o;

            carriers.add(new Carrier(
                    Integer.parseInt(carrierJson.get("CarrierId").toString()),
                    carrierJson.get("Name").toString()
            ));
        }
        return carriers;
    }

    /**
     * Parses JSONObject data that is returned from getBrowseQuotesService to Quote objects
     * @param jsonObject data that is returned from getBrowseQuotesService
     * @return a List of PlaceQuotes
     * @throws IOException
     * @throws ParseException
     */
    public static List<Quote> getQuotes(JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("Quotes");

        List<Quote> quotes = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject quoteJson = (JSONObject) o;

            JSONObject outboundLeg = (JSONObject) ((JSONObject) o).get("OutboundLeg");
            JSONArray carriersJson = (JSONArray) outboundLeg.get("CarrierIds");
            List<Integer> carriers = new ArrayList<>();
            for (Object carrier : carriersJson) {
                carriers.add(Integer.parseInt(carrier.toString()));
                System.out.println(carrier.toString());
            }

            quotes.add(new Quote(
                    Integer.parseInt(quoteJson.get("QuoteId").toString()),
                    Double.parseDouble(quoteJson.get("MinPrice").toString()),
                    Boolean.parseBoolean(quoteJson.get("Direct").toString()),
                    Integer.parseInt(outboundLeg.get("OriginId").toString()),
                    Integer.parseInt(outboundLeg.get("DestinationId").toString()),
                    LocalDateTime.parse(outboundLeg.get("DepartureDate").toString()),
                    LocalDateTime.parse(quoteJson.get("QuoteDateTime").toString()),
                    carriers
            ));
        }
        return quotes;
    }

    /**
     * Calls browsequotes service, with parameters provided in the options object, then parses the data using:
     * getPlaceQuotes, getQuotes and getCarriers methods and combines the data into a list of QuoteSimplified objects
     * @param options by which specified quote is returned
     * @return a List of QuoteSimplified objects
     * @throws IOException
     * @throws ParseException
     */
    public static List<QuoteSimplified> getQuotesSimplified(Options options) throws IOException, ParseException {
        JSONObject jsonObject = getBrowseQuotesService(options);
        List<PlaceQuote> placeQuotes = SkyScanner.getPlaceQuotes(jsonObject);
        List<Quote> quotes = SkyScanner.getQuotes(jsonObject);
        List<Carrier> carriers = SkyScanner.getCarriers(jsonObject);

        List<QuoteSimplified> quoteSimplifiedList = new ArrayList<>();
        for(Quote quote : quotes){
            QuoteSimplified quoteSimplified = new QuoteSimplified();
            quoteSimplified.setMinPrice(quote.getMinPrice());
            quoteSimplified.setDirect(quote.isDirect());
            quoteSimplified.setDepartureDate(quote.getDepartureDate());
            quoteSimplified.setQuoteDateTime(quote.getQuoteDateTime());

            for (PlaceQuote place : placeQuotes){
                if(place.getType().equals("Country")){
                    continue;
                }

                if (place.getPlaceId() == quote.getDestinationId()){
                    quoteSimplified.setDestination(
                                    " " + place.getIataCode() +
                                    " " + place.getCityName() +
                                    " " + place.getCountryName()
                    );
                    continue;
                }

                if (place.getPlaceId() == quote.getOriginId()){
                    quoteSimplified.setOrigin(
                                    " " + place.getIataCode() +
                                    " " + place.getCityName() +
                                    " " + place.getCountryName()
                    );
                }
            }
            List<String> carrierNames = new ArrayList<>();

            for (Integer carrierId : quote.getCarrierIds()){
                for (Carrier carrier : carriers){
                    if(carrier.getCarrierId() == carrierId.intValue()){
                        carrierNames.add(carrier.getName());
                        break;
                    }
                }
            }
            quoteSimplified.setCarriers(carrierNames);
            quoteSimplifiedList.add(quoteSimplified);
        }

        return quoteSimplifiedList;
    }

    /**
     * Gets quotes that correspond to the requirements in the Options object
     * @param options an object with requirements for the quotes
     * @return a List of Quote objects
     * @throws IOException
     * @throws ParseException
     */
    public static List<QuoteSimplified> getCorrespondingQuotes(Options options) throws IOException, ParseException {
        List<QuoteSimplified> quoteSimplifiedList = getQuotesSimplified(options);
        List<QuoteSimplified> correspondingQuotes = new ArrayList<>();
        for (QuoteSimplified quote: quoteSimplifiedList) {
            if(quote.getMinPrice() > options.getPrice()){
                continue;
            }

            if (options.isDirect() && !quote.isDirect()){
                continue;
            }

            correspondingQuotes.add(quote);
        }
        return correspondingQuotes;
    }

}
