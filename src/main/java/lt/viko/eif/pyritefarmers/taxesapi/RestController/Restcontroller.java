package lt.viko.eif.pyritefarmers.taxesapi.RestController;

import lt.viko.eif.pyritefarmers.taxesapi.APIs.SkyScanner;
import lt.viko.eif.pyritefarmers.taxesapi.models.Place;
import lt.viko.eif.pyritefarmers.taxesapi.models.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Restcontroller {

    @GetMapping("/routes/from/{market}/{query}")
    ResponseEntity<CollectionModel<Place>> ro() throws Exception {
        CollectionModel<Place> model = CollectionModel.of(SkyScanner.getPlaces("LT","Lithuania"));
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-starting-routes"));

        return ResponseEntity.ok(model);
    }


}
