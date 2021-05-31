package lt.viko.eif.pyritefarmers.taxesapi;

import lt.viko.eif.pyritefarmers.taxesapi.APIs.SkyScanner;
import lt.viko.eif.pyritefarmers.taxesapi.models.Options;
import lt.viko.eif.pyritefarmers.taxesapi.models.Place;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class testas {
    public static void main(String[] args) throws IOException, ParseException {
        SkyScanner.getBrowseQuotesService(new Options(true));
        SkyScanner.getListPlacesService("LT", "Lithuania");
        List<Place> places = SkyScanner.getPlaces("LT", "Lithuania");
        for (Place place : places){
            System.out.println(place);
        }
    }
}
