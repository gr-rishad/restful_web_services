package com.rishad.rest.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    // field1, field2
    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value 2", "value 3");
    }

    // field1, field3
    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveSomeBeanList() {
        return Arrays.asList(new SomeBean("value1", "value 2", "value 3"),
                new SomeBean("value1", "value 2", "value 3"));

    }
}
