package com.github.jntakpe.pp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jOSS on 27/04/2014.
 */
@RestController
public class UserResource {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
