package com.rishad.rest.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

// controller -> handle http request
@RestController
public class HelloWorldController {

    // GET
    //URI - /hello-world
    // method - "Hello World"
    //@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Hello World");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorld helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorld(String.format("Hello World %s,", name));
    }
}
