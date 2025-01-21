package com.archetype.app.cmd.api.controllers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.archetype.app.cmd.api.command.OpenPrototypeCommand;
import com.archetype.app.cmd.api.vo.PrototypeResponse;
import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.app.common.vo.PrototypeType;
import com.archetype.cqrsev.core.infrastructure.CommandDispatcher;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ActiveProfiles("test")
//@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(MockitoExtension.class)
public class OpenPrototypeControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CommandDispatcher commandDispatcher; 
	
	@BeforeEach
	public void setup () {
		MockitoAnnotations.openMocks(this);
		this.mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
//	@Test
	public void openPrototypeTest() throws Exception {
		OpenPrototypeCommand command = OpenPrototypeCommand.builder()
				.id("1234abc")
				.prototypeCode("cod1")
				.prototypeDay("24/05/1995")
				.prototypeNumbers(
						List.of(PrototypeNumberVo.builder().type(PrototypeType.E).value(1).build()))
				.build();
		PrototypeResponse expectedResponse=new PrototypeResponse("The prototype has been saved successfully",command.getPrototypeCode());
		
		doNothing().when(commandDispatcher).send(command);
//	final ResultActions result=	
			mockMvc.perform(
				post("/v1/prototype/openprototype")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(command))
				)
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)))
				.andReturn();
//	result.andExpect(status().isCreated());
//	result.andExpect(jsonPath("$[*].name", containsInAnyOrder(expectedResponse)));
	
		
	}
}
