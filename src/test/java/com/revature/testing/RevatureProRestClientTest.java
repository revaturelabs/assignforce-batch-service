package com.revature.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.RevatureProUserDTO;
import com.revature.assignforce.service.RevatureProRestClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@RestClientTest(RevatureProRestClient.class)
@SpringBootTest(classes=RevatureProRestClient.class)

public class RevatureProRestClientTest {

	@Autowired
	private RevatureProRestClient revatureProRestClient;

	@Autowired
	private MockRestServiceServer server;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		RevatureProUserDTO userDTO = new RevatureProUserDTO();
		userDTO.setToken("yourToken");
		String userDtoString =
				objectMapper.writeValueAsString(userDTO);
		this.server.expect(requestTo("https://dev3.revature.com/caliber/authentication/login"))
				.andRespond(withSuccess(userDtoString, MediaType.APPLICATION_JSON));
	}

	@Test
	public void restClientTest()
			throws Exception {

		RevatureProRestClient testResponse = this.revatureProRestClient;
		String result = testResponse.authenticate().getBody().getToken().toString();
		System.out.println("Result is: "+ result);
		assertTrue("Token isn't equal", result.equals("yourToken"));
	}
}
