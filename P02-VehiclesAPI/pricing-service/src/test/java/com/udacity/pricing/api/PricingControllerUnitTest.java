package com.udacity.pricing.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.pricing.domain.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
public class PricingControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getPriceTest() throws Exception {

    Long vehicleId = 1L;

    MvcResult result = mockMvc.perform(get("/services/price?vehicleId=" + vehicleId))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String priceJsonString = result.getResponse().getContentAsString();

    Price price = new ObjectMapper().readValue(priceJsonString, Price.class);

    assertThat(price.getVehicleId(), equalTo(vehicleId));
  }
}
