package net.dzale.alexa.concierge.controller;

import com.fasterxml.jackson.annotation.JsonView;
import net.dzale.alexa.concierge.exceptions.AlexaConciergeDataException;
import net.dzale.alexa.concierge.exceptions.AlexaConciergeException;
import net.dzale.alexa.concierge.model.AlexaConciergeUser;
import net.dzale.alexa.concierge.model.AlexaConciergeViews;
import net.dzale.alexa.concierge.service.AlexaConciergeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The main Spring Controller for this application.
 *
 * @author dzale
 */
@RestController
@RequestMapping( value = "/crypto" )
public class AlexaConciergeController {

    private static final Logger log = LoggerFactory.getLogger(AlexaConciergeController.class);

    @Autowired
    AlexaConciergeService alexaConciergeService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addUser(AlexaConciergeUser user) throws AlexaConciergeException {
        boolean success = alexaConciergeService.addUser(user);
        if (!success)
            throw new AlexaConciergeException("Unabled to add user '"+user.getUsername()+"'.");
        return new ResponseEntity<Boolean>(success, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<AlexaConciergeUser>> getUsers() throws AlexaConciergeException {
        List<AlexaConciergeUser> users = alexaConciergeService.getUsers();

        return new ResponseEntity<List<AlexaConciergeUser>>(users, HttpStatus.OK);
    }

    @JsonView( value = { AlexaConciergeViews.IncludeInResponse.class } )
    @RequestMapping(value = "/users/{userId}",
            method = RequestMethod.GET,
            produces = { "application/json" } )
    public ResponseEntity<AlexaConciergeUser> getUser(@RequestParam Long userId) throws AlexaConciergeException {
        AlexaConciergeUser user = alexaConciergeService.getUser(userId);
        if (user == null)
            throw new AlexaConciergeDataException("Unable to find user with userId '"+userId+"'.");
        return new ResponseEntity<AlexaConciergeUser>(user, HttpStatus.OK);
    }

    @JsonView( value = { AlexaConciergeViews.IncludeInResponse.class } )
    @RequestMapping(
            value = "/users?username=",
            method = RequestMethod.GET,
            produces = { "application/json" } )
    public ResponseEntity<AlexaConciergeUser> getUserByUsername(String username) throws AlexaConciergeException {
        AlexaConciergeUser user = alexaConciergeService.getUserByUsername(username);
        if (user == null)
            throw new AlexaConciergeDataException("Unable to find user with username '"+username+"'.");
        return new ResponseEntity<AlexaConciergeUser>(user, HttpStatus.OK);
    }

}
