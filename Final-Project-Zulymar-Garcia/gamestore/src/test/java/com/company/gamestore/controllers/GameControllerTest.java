package com.company.gamestore.controllers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@SpringBootTest
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {

    }

    //Create a new Game
    @Test
    public void shouldCreateGame() throws Exception {
        Game game = new Game();
        game.setId(1);
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.20));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);


        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(post("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
//
//
//    // Read by Id
//    @Test
//    public void shouldGetSpecificBookById() throws Exception{
//        Book book = new Book();
//
//        book.setBookId(24);
//        book.setIsbn("123456789");
//        book.setPublishDate(LocalDate.of(2020,1,8));
//        book.setAuthorId(123);
//        book.setTitle("Green Eggs and Ham");
//        book.setPublisherId(321);
//        book.setPrice(new BigDecimal(20.34));
//
//        mockMvc.perform(get("/books/24"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    // Read All
//    @Test
//    public void shouldGetAllBooks() throws Exception{
//        Book book1 = new Book();
//
//        book1.setBookId(24);
//        book1.setIsbn("123456789");
//        book1.setPublishDate(LocalDate.of(2020,1,8));
//        book1.setAuthorId(123);
//        book1.setTitle("Green Eggs and Ham");
//        book1.setPublisherId(321);
//        book1.setPrice(new BigDecimal(20.34));
//
//        Book book2 = new Book();
//
//        book2.setBookId(55);
//        book2.setIsbn("123456789");
//        book2.setPublishDate(LocalDate.of(2020,1,8));
//        book2.setAuthorId(456);
//        book2.setTitle("The Green Book");
//        book2.setPublisherId(321);
//        book2.setPrice(new BigDecimal(20.34));
//
//        Book book3 = new Book();
//
//        book3.setBookId(77);
//        book3.setIsbn("123456789");
//        book3.setPublishDate(LocalDate.of(2020,1,8));
//        book3.setAuthorId(123);
//        book3.setTitle("Harry Potter");
//        book3.setPublisherId(321);
//        book3.setPrice(new BigDecimal(20.34));
//
//        mockMvc.perform(get("/books"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    // Update
//    @Test
//    public void shouldUpdateBook() throws Exception {
//        Book book = new Book();
//
//        book.setBookId(77);
//        book.setIsbn("123456789");
//        book.setAuthorId(123);
//        book.setTitle("Harry Potter");
//        book.setPublisherId(321);
//        book.setPrice(new BigDecimal(20.34));
//
//        String inputJson = mapper.writeValueAsString(book);
//
//
//
//        mockMvc.perform(put("/books")
//                .content(inputJson)
//                .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//    }
//
//
//    //Delete
//    @Test
//    public void shouldDeleteExistingBookByIDAndReturn204StatusCode() throws Exception{
//        mockMvc.perform(delete("/books/432"))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//    }
//
//
//    // Search Book by Author Id
//    @Test
//    public void shouldSearchBookByAuthorId () throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("/books/author/13")).andDo(print()).andExpect(status().isOk());
//    }
//
//
//
//}
