package com.elis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration(classes = ProgettoEcommerceApplication.class) //collego il context all'applicazione principale
@TestMethodOrder(OrderAnnotation.class)
class ProgettoEcommerceApplicationTests {
	
	// oggetto chee mi permetterà di testare i metodi
	private MockMvc mock;
	
	@Autowired
	private WebApplicationContext context;
	
	//prima di ogni metodo esegui questo metodo
//	@BeforeEach
//	public void setting() {
//		mock = MockMvcBuilder
//	}
	

	@Test
	void contextLoads() {
	}

}
