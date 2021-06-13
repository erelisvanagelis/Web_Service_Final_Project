package lt.viko.eif.pyritefarmers.taxesapi.RestController;

import lt.viko.eif.pyritefarmers.taxesapi.APIs.SkyScanner;
import lt.viko.eif.pyritefarmers.taxesapi.models.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Restcontroller {
    JSONObject jsonObject = SkyScanner.getBrowseQuotesService(new Options(true));

    public Restcontroller() throws IOException, ParseException {
    }

    @GetMapping("/routes/starting/{market}/{query}")
    ResponseEntity<CollectionModel<Place>> startingplace(@PathVariable String market, @PathVariable String query) throws Exception {
        CollectionModel<Place> model = CollectionModel.of(SkyScanner.getPlaces(market,query));
       // List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }
    @GetMapping("/routes/destination/{market}/{query}")
    ResponseEntity<CollectionModel<Place>> destiantionplace(@PathVariable String market, @PathVariable String query) throws Exception {
        CollectionModel<Place> model = CollectionModel.of(SkyScanner.getPlaces(market,query));
     //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/routes/placeQoute")
    ResponseEntity<CollectionModel<PlaceQuote>> placeQuotes() throws Exception {
        CollectionModel<PlaceQuote> model = CollectionModel.of(SkyScanner.getPlaceQuotes(jsonObject));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }
    @GetMapping("/routes/qoute")
    ResponseEntity<CollectionModel<Quote>> quotes() throws Exception {
        CollectionModel<Quote> model = CollectionModel.of(SkyScanner.getQuotes(jsonObject));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }
    //cia tipo toks ala gautusi jaigu kaupti duomenys
    Options options = new Options(0,0,"UK","LT-sky","SE-sky",50,500,"EUR",false,java.time.LocalDate.now(),java.time.LocalDate.now().plusDays(7));

    @GetMapping("/routes/simpleqoute")
    ResponseEntity<CollectionModel<QuoteSimplified>> simplifiedquotes() throws Exception {
        CollectionModel<QuoteSimplified> model = CollectionModel.of(SkyScanner.getQuotesSimplified(options));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }


}
