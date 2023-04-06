package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of records for testing purposes
    private List<Console> consoleList;

    @Before
    public void setUp() {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }

    // Testing GET /records
    @Test
    public void shouldReturnAllConsolesInCollection() throws Exception {

        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(consoleList);

        // ACT
        mockMvc.perform(get("/consoles"))        // Perform the GET request
                .andDo(print())              // Print results to console
                .andExpect(status().isOk());        // ASSERT (status code is 200)
    }

    // Testing GET console/{id}
    @Test
    public void shouldReturnConsoleById() throws Exception {
        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));
        console.setQuantity(123);
        console.setId(2);

        String outputJson = mapper.writeValueAsString(console);

        mockMvc.perform(get("/consoles/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturn404StatusCodeIfConsoleNotFound() throws Exception {
        mockMvc.perform(get("/consoles/4678"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // Testing GET console/{manufacturer}
    @Test
    public void shouldReturnConsoleByManufacturer() throws Exception {
        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));
        console.setQuantity(123);
        console.setId(2);

        String outputJson = mapper.writeValueAsString(console);

        mockMvc.perform(get("/consoles/Microsoft"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing POST /records
    @Test
    public void shouldReturnNewConsoleOnPostRequest() throws Exception {

        // ARRANGE
        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));
        console.setQuantity(123);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(console);

        Console console2 = new Console();
        console2.setModel("2022");
        console2.setManufacturer("Google");
        console2.setMemoryAmount("64GB");
        console2.setProcessor("Intel");
        console2.setPrice(BigDecimal.valueOf(1111));
        console2.setQuantity(15);

        String outputJson = mapper.writeValueAsString(console2);

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
    public void shouldReturn422WhenPostingAnEmptyModel() throws Exception {
        Console console = new Console();
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));
        console.setQuantity(123);

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyManufacturer() throws Exception {
        Console console = new Console();
        console.setModel("2023");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));
        console.setQuantity(123);

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyPrice() throws Exception {
        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setQuantity(123);

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyQuantity() throws Exception {
        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    // Testing PUT /consoles/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Console console = new Console();
        console.setModel("2023");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("64GB");
        console.setProcessor("Intel");
        console.setPrice(BigDecimal.valueOf(1000.11));
        console.setQuantity(123);
        console.setId(2);

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(
                        put("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /consoles/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/consoles/5"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void shouldReturn404WhenAttemptingToDeleteAConsoleThatDoesNotExist() throws Exception {
        mockMvc.perform(delete("/consoles/586"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}
