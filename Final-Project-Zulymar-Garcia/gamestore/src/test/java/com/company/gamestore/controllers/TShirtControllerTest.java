package com.company.gamestore.controllers;
import com.company.gamestore.models.TShirt;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
//@ExtendWith(SpringExtension.class)
public class TShirtControllerTest {

//    @MockBean
//    private TShirtRepository tShirtRepository;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();


   private List<TShirt> tShirtList;

   @Before
   public void setUp() {
       //tShirtRepository.deleteAll();
   }


    //Testing GET
    @Test
    public void shouldReturnAllTShirtsInCollection() throws Exception {


        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(tShirtList);

        // ACT
        mockMvc.perform(get("/tshirt"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    @Test
    public void shouldReturnTShirtsByID() throws Exception {

      TShirt tshirt = new TShirt();
      tshirt.setColor("orange");
      tshirt.setPrice(BigDecimal.valueOf(15.99));
      tshirt.setQuantity(500);
      tshirt.setDescription("Blank");
      tshirt.setId(12943);
        tshirt.setSize("Medium");



        String outputJson = mapper.writeValueAsString(tShirtList);

        // ACT
        mockMvc.perform(get("/tshirt/12943"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }


    @Test
    public void shouldReturnTShirtsByColor() throws Exception {

        TShirt tshirt = new TShirt();
        tshirt.setColor("orange");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setQuantity(500);
        tshirt.setDescription("Blank");
        tshirt.setId(12943);
        tshirt.setSize("Medium");



        String outputJson = mapper.writeValueAsString(tShirtList);

        // ACT
        mockMvc.perform(get("/tshirt/orange"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));              // ASSERT (status code is 200)

    }

    @Test
    public void shouldReturnTShirtsBySize() throws Exception {

        TShirt tshirt = new TShirt();
        tshirt.setColor("orange");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setQuantity(500);
        tshirt.setDescription("Blank");
        tshirt.setSize("Medium");
        tshirt.setId(12943);


        String outputJson = mapper.writeValueAsString(tShirtList);

        // ACT
        mockMvc.perform(get("/tshirt/Medium"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));              // ASSERT (status code is 200)

    }



    @Test
    public void shouldReturnNewTShirtOnPostRequest() throws Exception {

        // ARRANGE
        TShirt tshirt = new TShirt();
        tshirt.setColor("orange");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setQuantity(500);
        tshirt.setDescription("Blank");
        tshirt.setSize("Medium");
        tshirt.setId(12943);
        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(tshirt);

        TShirt tshirt2 = new TShirt();
        tshirt2.setColor("white");
        tshirt2.setPrice(BigDecimal.valueOf(20.99));
        tshirt2.setSize("Small");
        tshirt2.setQuantity(1000);
        tshirt2.setDescription("Disney Logo on front");


        String outputJson = mapper.writeValueAsString(tshirt2);

        // ACT
        mockMvc.perform(
                        post("/records")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {



        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirt.setId(345);

        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(
                        put("/tshirt")    //shouud therre be another slash here
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /records/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {


        mockMvc.perform(delete("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    //Testing more  POSTS


    @Test
    public void shouldReturn422WhenPostingAnEmptySize() throws Exception {
        TShirt tshirt = new TShirt();
        tshirt.setColor("black");
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        //tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirt.setId(345);

        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(
                        post("/tshirt")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void shouldReturn422WhenPostingAnEmptyColor() throws Exception {
        TShirt tshirt = new TShirt();
        tshirt.setPrice(BigDecimal.valueOf(15.99));
        tshirt.setSize("Medium");
        tshirt.setQuantity(300);
        tshirt.setDescription("Leopard print graphics");
        tshirt.setId(345);

        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(
                        post("/tshirt")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }





}
