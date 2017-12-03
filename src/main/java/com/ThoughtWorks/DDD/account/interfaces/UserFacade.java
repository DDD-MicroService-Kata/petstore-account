package com.ThoughtWorks.DDD.account.interfaces;

import com.ThoughtWorks.DDD.account.domain.Contacts;
import com.ThoughtWorks.DDD.account.domain.User;
import com.ThoughtWorks.DDD.account.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.ThoughtWorks.DDD.account.interfaces.assemblers.UserMapper.toDTO;
import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/users")
public class UserFacade {

    private final UserRepository userRepository;

    private final SampleClient sampleClient;

    @Autowired
    public UserFacade(UserRepository userRepository, SampleClient sampleClient) {
        this.userRepository = userRepository;
        this.sampleClient = sampleClient;
    }

    @GetMapping
    public final String demo() {
        return "demo";
    }

    @PostMapping
    public final ResponseEntity createUser(@RequestBody final ApiForRequest<UserDTO> req) {
        UserDTO attributes = req.getAttributes();
        Contacts contacts = new Contacts(attributes.getPhoneNumber(), attributes.getEmailAddress());
        User user = new User(attributes.getFirstName(), attributes.getLastName(), contacts);

        User userAfterSaved = userRepository.save(user);
        return buildResponseEntity(create(format("/api/users/%d", userAfterSaved.getId())), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public final ApiForResponse<UserDTO> findById(@PathVariable("id") final long id) {
        User user = userRepository.findOne(id);
        return new ApiForResponse<>(user.getId(), toDTO(user));
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public final ResponseEntity changeContacts(@PathVariable("id") final long id, @RequestBody final ApiForRequest<UserDTO> req) {
        User user = userRepository.findOne(id);
        user.changeEmailAddressTo(req.getAttributes().getEmailAddress());
        user.changePhoneNumberTo(req.getAttributes().getPhoneNumber());
        userRepository.save(user);

        return buildResponseEntity(create(format("/api/users/%d", user.getId())), HttpStatus.NO_CONTENT);
    }

    private ResponseEntity buildResponseEntity(URI location, HttpStatus noContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, noContent);
    }

    @GetMapping(value = "/sample")
    public final String CallServiceSample() {
        return sampleClient.choose();
    }
}

