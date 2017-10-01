package com.ua.cabare.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.cabare.models.Bill;
import com.ua.cabare.services.BillService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BillController.class)
public class BillControllerTest {

  @MockBean
  private BillService billService;
  @Autowired
  private MockMvc mockMvc;

  public String objectToJson(Object obj) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(obj);
  }

  @Test
  public void openBill() throws Exception {

    Bill bill = new Bill();
    Map<String, Object> response = new HashMap<>();
    response.put("bill", bill);
    when(billService.openBill(any(Bill.class))).thenReturn(bill);

    mockMvc.perform(
        put("/bill/open")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(objectToJson(new Bill()))
    ).andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string(objectToJson(response)));
  }

}