package com.baselhack17.team12;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * //TODO write here something nicer.
 */
@RestController
public class StreetController {

    @RequestMapping(value = "/streets", method = RequestMethod.GET)
    public List<streets> getStreets() {
        return new DAO().getStreets();
    }

    @RequestMapping(value = "streets/{id}", method = RequestMethod.GET)
    public streets getStreet(@PathVariable("id") int id) {
        return new DAO().getStreet(id);
    }

}
