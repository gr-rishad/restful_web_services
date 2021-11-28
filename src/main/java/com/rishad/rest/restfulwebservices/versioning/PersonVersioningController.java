package com.rishad.rest.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    // Basic Approach with UI--> V1
    @GetMapping("/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    // Basic Approach with UI--> V2
    @GetMapping("/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // params --> V1
    @GetMapping(value = "/person/param", params = "version 1")
    public PersonV1 paramV1() {
        return new PersonV1("Bob Charlie");
    }

    // params --> V2
    @GetMapping(value = "/person/param", params = "version 2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // headers --> V1
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Bob Charlie");
    }

    // headers --> V2
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Bob", " Charlie"));
    }

    // produces/content negotiation/Accept Versioning --> V1
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Bob Charlie");
    }

    // produces/content negotiation/Accept Versioning --> V2
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Bob", " Charlie"));
    }
}
