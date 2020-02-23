package com.telenor.rest.greeting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.core.AnyOf.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GreetingApplication.class)
@AutoConfigureMockMvc
class GreetingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private String emptyStr = " ";




	@ParameterizedTest
	@CsvSource({ "5,Business", "5,personal", "13,personal",""+Integer.MAX_VALUE+",business" })
	public void testPathOneWithCorrectInput(int id,String account) throws Exception {

		mockMvc.perform(get("/greeting").param("id",String.valueOf(id)).param("account",account)).
				andExpect(status().isOk())
				.andExpect(content().string("Hi, userid "+id));



	}

	@ParameterizedTest
	@CsvSource({ "-5,Business", "5,"+"  ", "13,null",""+Integer.MAX_VALUE+",hello" })
	public void testPathOneWithWrongInput(int id,String account)  {

		try {
			mockMvc.perform(get("/greeting").param("id", String.valueOf(id)).param("account", account)).
					andExpect(status().isBadRequest());
		}catch (Exception ex){
			assertTrue(ex.getMessage().contains("Request processing failed"));
		}

	}

	@ParameterizedTest
	@CsvSource({ "Big,Business", "small,Personal", "big,personal","SMALL,business" })
	public void testPathTwoWithCorrectInput(String type,String account) throws Exception {

		mockMvc.perform(get("/greeting").param("type",type).param("account",account)).
				andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().string(anyOf(is("Welcome, "+account+" user!#"),is("Error: path is not implemented"))));



	}

	@ParameterizedTest
	@CsvSource({ "Huge,Business", "s,Personal", "big, ","SMALL,B" })
	public void testPathTwoWithWrongInput(String type,String account) throws Exception {

		try {
		mockMvc.perform(get("/greeting").param("type",type).param("account",account)).
				andExpect(status().isBadRequest());




		}catch (Exception ex){
			assertTrue(ex.getMessage().contains("Request processing failed"));
		}

	}




}
