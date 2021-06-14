package lt.viko.eif.pyritefarmers.taxesapi.RestController;

import lt.viko.eif.pyritefarmers.taxesapi.APIs.SkyScanner;
import lt.viko.eif.pyritefarmers.taxesapi.Repositories.UserRepo;
import lt.viko.eif.pyritefarmers.taxesapi.models.*;
import org.apache.tomcat.jni.Local;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RestController that controls services used for Options
 */
@RestController
public class Restcontroller {
    private final UserRepo repository = new UserRepo();
    private Connection Conn;
    JSONObject jsonObject = SkyScanner.getBrowseQuotesService(new Options(true));


    /**
     * Constructor that connects controller to db
     * @throws IOException
     * @throws ParseException
     */
    public Restcontroller() throws IOException, ParseException {
        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject", "root", "root");
            System.out.println("All good connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong.");
        }
    }

    /**
     * Gets all the places from the specified market
     * @param market example - "LT"
     * @param query example - "Lithuania"
     * @return ResponseEntity that is comprised of Place collection model
     * @throws Exception
     */
    @GetMapping("/routes/starting/{market}/{query}")
    ResponseEntity<CollectionModel<Place>> startingplace(@PathVariable String market, @PathVariable String query) throws Exception {
        CollectionModel<Place> model = CollectionModel.of(SkyScanner.getPlaces(market,query));
       // List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }
    /**
     * Gets all the places from the specified market
     * @param market example - "LT"
     * @param query example - "Lithuania"
     * @return ResponseEntity that is comprised of Place collection model
     * @throws Exception
     */
    @GetMapping("/routes/destination/{market}/{query}")
    ResponseEntity<CollectionModel<Place>> destiantionplace(@PathVariable String market, @PathVariable String query) throws Exception {
        CollectionModel<Place> model = CollectionModel.of(SkyScanner.getPlaces(market,query));
     //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-destination-routes"));
        return ResponseEntity.ok(model);
    }

    /**
     * what is de purpose of dis
     * Gets an example of
     * @return
     * @throws Exception
     */
    @GetMapping("/routes/placeQuote")
    ResponseEntity<CollectionModel<PlaceQuote>> placeQuotes() throws Exception {
        CollectionModel<PlaceQuote> model = CollectionModel.of(SkyScanner.getPlaceQuotes(jsonObject));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));
        return ResponseEntity.ok(model);
    }
    @GetMapping("/routes/quote")
    ResponseEntity<CollectionModel<Quote>> quotes() throws Exception {
        CollectionModel<Quote> model = CollectionModel.of(SkyScanner.getQuotes(jsonObject));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-route-qoute"));
        return ResponseEntity.ok(model);
    }
    //cia tipo toks ala gautusi jaigu kaupti duomenys
    Options options = new Options(0,0,"UK","LT-sky","SE-sky",50,500,"EUR",false,java.time.LocalDate.now(),java.time.LocalDate.now().plusDays(7));

    @GetMapping("/routes/simpleqoute")
    ResponseEntity<CollectionModel<QuoteSimplified>> simplifiedquotes() throws Exception {
        CollectionModel<QuoteSimplified> model = CollectionModel.of(SkyScanner.getQuotesSimplified(options));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-route-simplified_quote"));
        return ResponseEntity.ok(model);
    }
    @GetMapping("/routes/simplequote/check")
    ResponseEntity<RepresentationModel<?>> simplifiedquotescheck() throws Exception {
        Options options2 = null;
        try {
            new Restcontroller();
            Statement stmt;
            ResultSet rs;
            stmt = Conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM finalproject.options where userid= '"+ repository.GetLoggedInUser().getId() +"'");
            while (rs.next()) {
                int id=(rs.getInt("id"));
                int userid= (rs.getInt("userid"));
                String market= String.valueOf(rs.getString("market"));
                String originplace= rs.getString("originplace");
                String destination= rs.getString("destinationplace");
                int distance= rs.getInt("distance");
                int price= rs.getInt("price");
                String currency= rs.getString("currency");
                boolean direct = rs.getBoolean("direct");
                LocalDate datefrom= LocalDate.parse(String.valueOf(rs.getString("datefrom")));
                LocalDate dateto= LocalDate.parse(rs.getString("dateto"));



                options2= new Options(id,userid,market,originplace,destination,distance,price,currency,direct,datefrom,dateto);

            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        RepresentationModel<?> model = CollectionModel.of(SkyScanner.getQuotesSimplified(options2));
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-route-simplified_quote"));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/routes/simplequote/add")
    ResponseEntity<RepresentationModel<?>> simplifiedquotesadd() throws Exception {
        new Restcontroller();
        Statement statement=Conn.createStatement();
        String query = "INSERT INTO finalproject.options (userid, market, originplace, destinationplace, distance,price, currency, direct, datefrom,dateto)"+ " values (?, ?, ?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = Conn.prepareStatement(query);
        preparedStmt.setInt(1, repository.GetLoggedInUser().getId());
        preparedStmt.setString(2, options.getMarket());
        preparedStmt.setString(3, options.getOriginPlace());
        preparedStmt.setString(4, options.getDestinationPlace());
        preparedStmt.setDouble(5, options.getDistance());
        preparedStmt.setDouble(6, options.getPrice());
        preparedStmt.setString(7, options.getCurrency());
        preparedStmt.setBoolean(8, options.isDirect());
        preparedStmt.setString(9, String.valueOf(options.getStart()));
        preparedStmt.setString(10, String.valueOf(options.getEnd()));
        preparedStmt.execute();
        List <Options> optionsList= Collections.singletonList(options);
        repository.GetLoggedInUser().setOptionsList(optionsList);
        RepresentationModel<?> model = CollectionModel.of(repository.GetLoggedInUser());
        //   List<Place> places=SkyScanner.getPlaces(market,query);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-route-simplified_quote-add"));
        return ResponseEntity.ok(model);
    }


}
