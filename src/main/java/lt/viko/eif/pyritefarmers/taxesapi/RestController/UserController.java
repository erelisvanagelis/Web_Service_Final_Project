package lt.viko.eif.pyritefarmers.taxesapi.RestController;

import lt.viko.eif.pyritefarmers.taxesapi.Repositories.UserRepo;
import lt.viko.eif.pyritefarmers.taxesapi.models.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepo repository= new UserRepo();

    @GetMapping("/user/register/{name}/{password}/{Email}")
    ResponseEntity<RepresentationModel<?>> register(@PathVariable String name, @PathVariable String password, @PathVariable String Email) throws Exception {
        repository.Register(name,password,Email);
       // System.out.println(repository.GetLoggedInUser());
        RepresentationModel<?> model = CollectionModel.of(repository.GetRegisteredUser());
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-User-Register"));
       // model.add(linkTo(methodOn(UserController.class).ShowUserInfo()).withRel("logged-user"));

        return ResponseEntity.ok(model);
    }
    @GetMapping("/user/login/{name}/{password}")
    ResponseEntity<RepresentationModel<?>> login(@PathVariable String name, @PathVariable String password) throws Exception {
        repository.Login(name,password);
         System.out.println(repository.GetLoggedInUser());
        RepresentationModel<?> model = CollectionModel.of(repository.GetLoggedInUser());
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-User-LoggedIn"));
        // model.add(linkTo(methodOn(UserController.class).ShowUserInfo()).withRel("logged-user"));
        return ResponseEntity.ok(model);
    }
    @GetMapping("/users")
    ResponseEntity<CollectionModel<User>> allusers() throws Exception {
        CollectionModel<User> model = CollectionModel.of(repository.GetUsers());
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "self-users"));
        return ResponseEntity.ok(model);
    }
    @GetMapping("/user/delete/{name}/{password}")
    public ResponseEntity<CollectionModel<List<User>>> delete(@PathVariable String name, @PathVariable String password){
        for (User u : repository.GetUsers()) {
            if(u.getNickname().equals(name)&& u.getPassword().equals(password)){
                System.out.println( repository.GetUsers().remove(u));
                repository.GetUsers().remove(u);
            }
        }
        CollectionModel<List<User>> model = CollectionModel.of(Collections.singleton(repository.GetUsers()));
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        model.add(Link.of(uriString, "delete-User"));
        return ResponseEntity.ok(model);
    }


}
