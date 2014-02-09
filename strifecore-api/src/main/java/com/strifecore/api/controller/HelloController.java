package com.strifecore.api.controller;

import com.strifecore.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public @ResponseBody String hello(@PathVariable String name){
        return testService.hello(name);
    }

    @RequestMapping(value = "/{name}/secured", method = RequestMethod.GET)
    public @ResponseBody String securedHello(@PathVariable String name){
        return testService.securedHello(name);
    }

}
