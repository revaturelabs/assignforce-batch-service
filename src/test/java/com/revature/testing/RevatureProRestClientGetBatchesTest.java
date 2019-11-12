package com.revature.testing;

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

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RevatureProRestClient.class)
@SpringBootTest(classes=RevatureProRestClient.class)

public class RevatureProRestClientGetBatchesTest {

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
	public void restClientAuthenticateResponseTokenTest()
			throws Exception {

		// Call the authenticate method and check to see if the response includes the token defined above
		ResponseEntity<RevatureProUserDTO> response = this.revatureProRestClient.authenticate();
		String token = response.getBody().getToken();

		System.out.println("Response is: "+ response);
		assertTrue("Token isn't equal", token.equals("yourToken"));
	}

	@Test
	public void restClientAuthenticateResponseTypeTest()
			throws Exception {

		RevatureProUserDTO test = new RevatureProUserDTO();

		// Call the authenticate method and check to see if the response includes the token defined above
		ResponseEntity<RevatureProUserDTO> response = this.revatureProRestClient.authenticate();
		Class<? extends RevatureProUserDTO> responseType = response.getBody().getClass();

		assertTrue("Response body type is not equal to RevatureProUserDTO", responseType.equals(test.getClass()));
	}
}
