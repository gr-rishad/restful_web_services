package com.rishad.rest.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    // field1, field2
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {

        SomeBean someBean = new SomeBean("value31", "value32", "value33");
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("someBeanFilter", simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    // field1, field3
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value01", "value 02", "value 03"),
                new SomeBean("value11", "value 12", "value 13"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;

    }
}
