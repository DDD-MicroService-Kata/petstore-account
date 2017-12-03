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

import static com.ThoughtWorks.DDD.account.interfaces.assemblers.UserMapper.toUserDTO;

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
    public final String demo(){
        return "demo";
    }

    @PostMapping
    public final ResponseEntity createUser(@RequestBody final ApiForRequest<UserDTO> req){
        UserDTO attributes = req.getAttributes();
        Contacts contacts = new Contacts(attributes.getPhoneNumber(), attributes.getEmailAddress());
        User user = new User(attributes.getFirstName(), attributes.getLastName(), contacts);

        User userAfterSaved = userRepository.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(String.format("/api/users/%d", userAfterSaved.getId())));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final @ResponseBody
    ApiForResponse<UserDTO> findById(@PathVariable("id") final long id) {
        User user = userRepository.findOne(id);
        return new ApiForResponse<>(user.getId(), toUserDTO(user));
    }

    @GetMapping(value = "/sample")
    public final String CallServiceSample() {
        return sampleClient.choose();
    }
}

