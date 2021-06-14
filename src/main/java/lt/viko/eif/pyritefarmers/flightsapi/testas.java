package lt.viko.eif.pyritefarmers.flightsapi;

import lt.viko.eif.pyritefarmers.flightsapi.APIs.SkyScanner;
import lt.viko.eif.pyritefarmers.flightsapi.models.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class testas {
    public static void main(String[] args) throws IOException, ParseException {
        JSONObject jsonObject = SkyScanner.getBrowseQuotesService(new Options(true));
        SkyScanner.getListPlacesService("LT", "Lithuania");

        List<Place> places = SkyScanner.getPlaces("LT", "Lithuania");
        for (Place place : places){
            System.out.println(place+"Place");
        }

        List<PlaceQuote> placeQuotes = SkyScanner.getPlaceQuotes(jsonObject);
        for (PlaceQuote place : placeQuotes){
            System.out.println(place);
        }

        List<Carrier> carriers = SkyScanner.getCarriers(jsonObject);
        for (Carrier carrier : carriers){
            System.out.println(carrier);
        }

        List<Quote> quotes = SkyScanner.getQuotes(jsonObject);
        for (Quote quote : quotes){
            System.out.println(quote);
        }

        List<QuoteSimplified> quoteSimplifiedList = SkyScanner.getQuotesSimplified(new Options(true));
        System.out.println("Suklydau");
        for (QuoteSimplified quoteSimplified : quoteSimplifiedList){
            System.out.println(quoteSimplified);
        }

    }
}
