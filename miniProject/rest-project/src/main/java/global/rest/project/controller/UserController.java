package global.rest.project.controller;

import global.rest.project.model.user.UserDetails;
import global.rest.project.model.user.UserRequest;
import global.rest.project.service.UserService;
import global.rest.project.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<Object> findUsers(@PathVariable(name = "email", value = "email") String email)
            throws IOException {

        UserDetails user = userService.searchUser(email);
        if (user != null) {
            UserRequest userResponse = new UserRequest();
            userResponse.setEmail(user.getEmail());
            userResponse.setName(user.getName());
            userResponse.setPassword(user.getPassword());
            userResponse.setPhones(UserUtil.getListOfPhonesFromString(user.getPhones()));
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        return UserUtil.userNotFound(email);
    }

    @Transactional
    @Consumes(MediaType.APPLICATION_JSON )
    @Produces({ MediaType.APPLICATION_JSON  })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object>  createUser(@RequestBody UserRequest newUser) {
        String inputEmail = newUser.getEmail();
        String inputPass = newUser.getPassword();
        Boolean found = userService.searchUser(inputEmail) != null ? true : false;

        if(UserUtil.isValidToPersist(inputEmail, inputPass, found)) {
            UserDetails userToPersist = new UserDetails();
            userToPersist.setEmail(inputEmail);
            userToPersist.setName(newUser.getName());
            userToPersist.setPassword(inputPass);
            userToPersist.setPhones(newUser.getPhones().toString());
            //TODO: persist
            //userService.saveUser(userToPersist);
            inputEmail = "test@test.com";
            logger.info("\n\nMOCKING SEARCH TO VALIDATE INTEGRATION 'email' rewritten as '<{}>' to test response\n\n", inputEmail);
            //TODO remove above MOCK
            return UserUtil.successResponses(userService.searchUser(inputEmail), inputEmail) ;
        }
        return UserUtil.errorResponses(inputEmail, inputPass, found);
    }

}
