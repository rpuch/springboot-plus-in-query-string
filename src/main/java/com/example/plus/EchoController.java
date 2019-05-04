package com.example.plus;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rpuch
 */
@RestController
public class EchoController {
    @GetMapping(path = "/param", produces = MediaType.TEXT_PLAIN_VALUE)
    String echoParam(@RequestParam("p") String paramValue) {
        return paramValue;
    }

    @GetMapping(path = "/path-variable/{val}", produces = MediaType.TEXT_PLAIN_VALUE)
    String echoPathVariable(@PathVariable("val") String val) {
        return val;
    }
}
