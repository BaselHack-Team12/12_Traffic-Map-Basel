package com.baselhack17.team12;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * //TODO write here something nicer.
 */
@RestController
public class StreetController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "hello";
    }

    @RequestMapping(value = "/streets", method = RequestMethod.GET)
    public List<streets> getStreets() {
        return new HibernateUtils().getStreets();
    }

}
