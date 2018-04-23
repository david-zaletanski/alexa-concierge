package net.dzale.alexa.concierge.controller;

import net.dzale.alexa.concierge.service.AlexaConciergeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The main controller for the alexa-concierge backend REST API.
 *
 * @author dzale
 */
@RestController
public class AlexaConciergeController {
    private static final Logger log = LoggerFactory.getLogger(AlexaConciergeController.class);

    @Autowired
    AlexaConciergeService alexaConciergeService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        String responseBody = alexaConciergeService.test();

        return new ResponseEntity<String>(responseBody, HttpStatus.OK);
    }

}
