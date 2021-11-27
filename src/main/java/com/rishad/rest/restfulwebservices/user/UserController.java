package com.rishad.rest.restfulwebservices.user;

import com.rishad.rest.restfulwebservices.exception.CustomizeExceptionHandler;
import com.rishad.rest.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController extends CustomizeExceptionHandler {

    @Autowired
    private UserDaoService userDaoService;

    // retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) throws HttpClientErrorException.NotFound {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("nothing ");
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        User user = userDaoService.deleteById(id);


        if (user == null) {
            throw new UserNotFoundException("Not Found");
        }
    }

    // save user
    // input--> user
    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
        //  return new ResponseEntity<>("CREATED", HttpStatus.CREATED);

        //  ServletUriComponentsBuilder --> create links based on the current HttpServletRequest
        // .fromCurrentRequest()--> Prepare a builder from the host, port, scheme, and context path of the given
        // .path() --> Append to the path of this builder
        // buildAndExpand() --> Build a UriComponents instance and replaces URI template variables with the values from an array
    }
}
