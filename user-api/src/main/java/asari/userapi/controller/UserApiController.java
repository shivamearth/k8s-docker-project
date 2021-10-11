package asari.userapi.controller;

import asari.userapi.model.User;
import asari.userapi.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApiController {

    private static final Log LOG = LogFactory.getLog(UserApiController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> userList = userRepository.findAll();
            LOG.debug("userList.size()=" + userList.size());
            LOG.debug("userList=" + userList);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            LOG.debug("user=" + user);
            User _user = userRepository.save(user);
            LOG.debug("_user=" + _user);
            return new ResponseEntity<>(_user, HttpStatus.OK);
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
