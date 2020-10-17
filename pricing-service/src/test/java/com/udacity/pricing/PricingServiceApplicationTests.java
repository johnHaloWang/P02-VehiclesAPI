package com.udacity.pricing;

import com.udacity.pricing.domain.price.entity.Price;
import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	private final static String HOST = "http://localhost:";
	private final static String API_URL = "/services/price";
	private final static String _TAG = "PricingServiceApplicationTests";
	private static int counter = 1;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean
	private PricingService pricingService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {

	}


	/**
	 * Creates pre-requisites for testing, such as an example price.
	 */
	@Before
	public void setup() throws PriceException {
		Price price = getPrice();
		given(pricingService.getPrice(anyLong())).willReturn(price);
	}

	/**
	 * Tests for get the car price via id  in the system
	 * Test if the pricingService's getPrice(anyInt()) function is called
	 * Test if the HttpStatus is ok
	 * Test the content of return Price object is as intended
	 * @throws PriceException when id is not found in the system
	 */
	@Test
	public void testSinglePrice() throws PriceException {
		ResponseEntity<Price> response = testRestTemplate.getForEntity( PricingServiceApplicationTests.HOST+ port + PricingServiceApplicationTests.API_URL+ "?vehicleId=1", Price.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().getId(), equalTo(new Long(PricingServiceApplicationTests.counter)));
		verify(pricingService, times(1))
				.getPrice(anyLong());
	}

	/**
	 * Tests for get list of price via in the system
	 * Test if the pricingService's retrievePrice() function is called
	 * Test if the HttpStatus is ok
	 * Test the content of return List<Price> object is as intended
	 * Test if the list has the correct size
	 */
	@Test
	public void testListOfPrice(){
		List<Price> list = new ArrayList<>();
		list.add(getPrice());
		list.add(getPrice());
		given(pricingService.retrievePrice()).willReturn(list);
		ResponseEntity<List<Price>> response = testRestTemplate.exchange(PricingServiceApplicationTests.HOST + port + PricingServiceApplicationTests.API_URL + "/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Price>>() {});
		List<Price> listResult = response.getBody();
		assertThat(listResult.size(), equalTo(list.size()));
		assertThat(listResult.get(0).getId(), equalTo(list.get(0).getId()));
		verify(pricingService, times(1))
				.retrievePrice();

	}
	/**
	 * Creates an examplem Price object for use in testing.
	 * @return an example Price object
	 */
	private Price getPrice() {
		PricingServiceApplicationTests.counter++;
		int number =PricingServiceApplicationTests.counter;
		Price p = new Price();
		p.setCurrency("USD");
		p.setId(new Long(number));
		p.setVehicleId(new Long(number));
		p.setPrice(new BigDecimal(10 + number));
		return p;
	}

}
