package com.telenor.rest.greeting;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Validated
@RestController

public class GreetingController {
    @RequestMapping(value="/greeting",method = RequestMethod.GET, params = {"account","id"})
    public String pathOne(@RequestParam @Pattern(regexp = "personal|business", flags = Pattern.Flag.CASE_INSENSITIVE)
                                  String account, @RequestParam @Min(1) @Max(Integer.MAX_VALUE) Integer id){
        return "Hi, userid "+id;
    }

    @RequestMapping(value="/greeting",method = RequestMethod.GET, params = {"account","type"})
    public String pathTwo(@RequestParam @Pattern(regexp = "personal|business", flags = Pattern.Flag.CASE_INSENSITIVE)
                                  String account,@RequestParam @Pattern(regexp = "small|big",
                                  flags = Pattern.Flag.CASE_INSENSITIVE) String type) {

        if(type.equalsIgnoreCase("big")){
            return "Welcome, "+account+" user!#";
        }
        return "Error: path is not implemented";
    }
}
