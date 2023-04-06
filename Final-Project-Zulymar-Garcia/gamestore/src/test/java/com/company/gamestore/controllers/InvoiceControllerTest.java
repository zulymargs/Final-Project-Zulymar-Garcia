package com.company.gamestore.controllers;

import com.company.gamestore.models.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class InvoiceControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of records for testing purposes
    private List<Invoice> invoiceList;

    @Before
    public void setUp() {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }
    @Test
    public void shouldReturnAllInvoicesInCollection() throws Exception {

        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(invoiceList);

        // ACT
        mockMvc.perform(get("/invoices"))        // Perform the GET request
                .andDo(print())              // Print results to console
                .andExpect(status().isOk());        // ASSERT (status code is 200)
    }

    @Test
    public void shouldReturnInvoiceById() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("TShirt");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);


        String outputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoice/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }


    @Test
    public void shouldReturn404StatusCodeIfInvoiceNotFound() throws Exception {
        mockMvc.perform(get("/consoles/567"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnConsoleByCustomerName() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setItem_type("TShirt");
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);

        String outputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(get("/invoices/John Doe"))   //should there be a space here
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnNewInvoiceOnPostRequest() throws Exception {

        // ARRANGE
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);

        String inputJson = mapper.writeValueAsString(invoice);

        Invoice invoice2 = new Invoice();

        invoice2.setItem_id(12345);
        invoice2.setName("Jane Doe");
        invoice2.setStreet("234 Mack Lane");
        invoice2.setCity("Atlanta");
        invoice2.setState("GA");
        invoice2.setZipcode("45324");
        invoice2.setQuantity(2);
        invoice2.setUnit_price(BigDecimal.valueOf(20.99));
        invoice2.setSubtotal(BigDecimal.valueOf(41.98));
        invoice2.setTax(BigDecimal.valueOf(0.07));
        invoice2.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice2.setTotal(BigDecimal.valueOf(46.90));
        invoice.setId(3);

        String outputJson = mapper.writeValueAsString(invoice2);

        // ACT
        mockMvc.perform(
                        post("/consoles")              // Perform the POST request
                                .content(inputJson)             // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                // Print results to console
                .andExpect(status().isCreated());        // ASSERT (status code is 201)
    }
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);


        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        put("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/invoice/500"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyQuantity() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
       // invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        post("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyCustomerName() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
       // invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        post("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyTotal() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
        // invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(1);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        //invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        post("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldReturn404WhenAttemptingToDeleteAInvoiceThatDoesNotExist() throws Exception {
        mockMvc.perform(delete("/consoles/1000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturn422WhenPostingAQuantityLessThanOne() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setItem_id(54321);
         invoice.setName("John Doe");
        invoice.setStreet("200 Ferry Street");
        invoice.setCity("Newark");
        invoice.setState("NJ");
        invoice.setZipcode("07105");
        invoice.setQuantity(0);
        invoice.setUnit_price(BigDecimal.valueOf(15.99));
        invoice.setSubtotal(BigDecimal.valueOf(15.99));
        invoice.setTax(BigDecimal.valueOf(0.05));
        invoice.setProcessing_fee(BigDecimal.valueOf(1.98));
        //invoice.setTotal(BigDecimal.valueOf(18.77));
        invoice.setId(2);

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        post("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }






}
