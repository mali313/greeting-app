package com.telenor.rest.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@SpringBootApplication

public class GreetingApplication {


	public static void main(String[] args) {

		SpringApplication.run(GreetingApplication.class, args);
	}

}
