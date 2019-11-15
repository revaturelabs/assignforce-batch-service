package com.revature.testing.revaturepro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.revaturepro.RevatureProUserDTO;
import com.revature.assignforce.service.RevatureProRestClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@RestClientTest(RevatureProRestClient.class)
@SpringBootTest(classes=RevatureProRestClient.class)

public class RevatureProRestClientAuthenticateTest {

	@Autowired
	private RevatureProRestClient revatureProRestClient;

	@Autowired
	private MockRestServiceServer server;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		// Create a DTO object
		RevatureProUserDTO authorizationDTO = new RevatureProUserDTO();
		authorizationDTO.setToken("yourToken");

		String userDtoString = objectMapper.writeValueAsString(authorizationDTO);
		// Define
		this.server.expect(requestTo("https://dev3.revature.com/caliber/authentication/login"))
				.andRespond(withSuccess(userDtoString, MediaType.APPLICATION_JSON));
	}

	@Test
	public void restClientAuthenticateResponseTokenTest()
			throws Exception {

			// create a test DTO object
			RevatureProUserDTO test = new RevatureProUserDTO();
			test.setToken("yourToken");

			// Call the authenticate method and check to see if the response includes the token defined above
			ResponseEntity<RevatureProUserDTO> response = this.revatureProRestClient.authenticate();
			RevatureProUserDTO responseStatus = response.getBody();

			assertTrue("Response token is not equal to test token", responseStatus.getToken().equals(test.getToken())); }

	@Test
	public void restClientAuthenticateResponseTypeTest()
			throws Exception {

			// Create a test class
			RevatureProUserDTO test = new RevatureProUserDTO();

			// Call the authenticate method
			ResponseEntity<RevatureProUserDTO> response = this.revatureProRestClient.authenticate();

			Class<? extends RevatureProUserDTO> responseType = response.getBody().getClass();
			System.out.println(responseType);
			assertTrue("Response body type is not equal to RevatureProUserDTO", responseType.equals(test.getClass()));
	}
}
