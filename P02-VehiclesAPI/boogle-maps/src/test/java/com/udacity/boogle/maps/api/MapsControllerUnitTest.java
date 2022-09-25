package com.udacity.boogle.maps.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.boogle.maps.Address;
import com.udacity.boogle.maps.MapsController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@WebMvcTest(MapsController.class)
public class MapsControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getAddressTest() throws Exception {
    MvcResult result = mockMvc.perform(get("/maps?lat=20.0&lon=30.0"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    String addressJsonString = result.getResponse().getContentAsString();

    Address address = new ObjectMapper().readValue(addressJsonString, Address.class);

    Assert.assertNotNull(address.getAddress());

  }

}
