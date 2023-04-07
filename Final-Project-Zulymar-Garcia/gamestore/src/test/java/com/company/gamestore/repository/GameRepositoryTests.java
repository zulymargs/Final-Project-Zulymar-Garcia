package com.company.gamestore.repository;


import com.company.gamestore.models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTests {

    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws  Exception
    {
        gameRepository.deleteAll();
    }

    @Test
    public void createGame() {
        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.25));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);



        game = gameRepository.save(game);

        //Assert...
        Optional<Game> game1 = gameRepository.findById(game.getId());

        assertEquals(game1.get(), game);
    }

    @Test
    public void updateGame() {

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.25));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);

        //Act...
        game.setTitle("MarioBros");

        game.setQuantity(25);


        gameRepository.save(game);


        //Assert...
        Optional<Game> game1 = gameRepository.findById(game.getId());

        assertEquals(game1.get(), game);
    }

    @Test
    public void deleteGame() {

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.20));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);

        gameRepository.deleteById(game.getId());

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertFalse(game1.isPresent());
    }

    @Test
    public void getGameByID() {

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.25));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);


        Game game2 = new Game();
        game2.setTitle("WowGames");
        game2.setESRB("PG");
        game2.setPrice(BigDecimal.valueOf(45.20));
        game2.setDescription("A game for fun people");
        game2.setStudio("LOL");
        game2.setQuantity(8);

        gameRepository.save(game2);
        Optional<Game> foundGame = gameRepository.findById(game.getId());
        assertEquals(foundGame.get(), game);

    }

    @Test
    public void getAllGames(){

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.20));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);


        Game game2 = new Game();
        game2.setTitle("WowGames");
        game2.setESRB("PG");
        game2.setPrice(BigDecimal.valueOf(45.20));
        game2.setDescription("A game for fun people");
        game2.setStudio("LOL");
        game2.setQuantity(8);

        gameRepository.save(game2);

        List<Game> gameList = gameRepository.findAll();

        assertEquals(gameList.size(), 2);
    }

    @Test
        public void getGameByStudio(){

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.20));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);


        Game game2 = new Game();
        game2.setTitle("WowGames");
        game2.setESRB("PG");
        game2.setPrice(BigDecimal.valueOf(45.20));
        game2.setDescription("A game for fun people");
        game2.setStudio("LOL");
        game2.setQuantity(8);

        gameRepository.save(game2);


        Game game3 = new Game();
        game3.setTitle("Sims");
        game3.setESRB("PG");
        game3.setPrice(BigDecimal.valueOf(45.20));
        game3.setDescription("A game for innovating people");
        game3.setStudio("LOL");
        game3.setQuantity(78);

        gameRepository.save(game3);



        List<Game> gameList = gameRepository.findByStudio("LOL");
        assertEquals(gameList.size(), 2);

        List<Game> gameList2 = gameRepository.findByStudio("XD");
        assertEquals(gameList2.size(), 1);

    }

    @Test
    public void getGameByESRBRating(){

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.20));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);


        Game game2 = new Game();
        game2.setTitle("WowGames");
        game2.setESRB("PG");
        game2.setPrice(BigDecimal.valueOf(45.20));
        game2.setDescription("A game for fun people");
        game2.setStudio("LOL");
        game2.setQuantity(8);

        gameRepository.save(game2);


        Game game3 = new Game();
        game3.setTitle("Sims");
        game3.setESRB("PG");
        game3.setPrice(BigDecimal.valueOf(45.20));
        game3.setDescription("A game for innovating people");
        game3.setStudio("LOL");
        game3.setQuantity(78);

        gameRepository.save(game3);



        List<Game> gameList = gameRepository.findByesrbRating("PG");
        assertEquals(gameList.size(), 2);

        List<Game> gameList2 = gameRepository.findByesrbRating("R");
        assertEquals(gameList2.size(), 1);

    }

    @Test
    public void getGameByTitle(){

        Game game = new Game();
        game.setTitle("NewOne");
        game.setESRB("R");
        game.setPrice(BigDecimal.valueOf(45.20));
        game.setDescription("A game for creative people");
        game.setStudio("XD");
        game.setQuantity(3);

        gameRepository.save(game);


        Game game2 = new Game();
        game2.setTitle("WowGames");
        game2.setESRB("PG");
        game2.setPrice(BigDecimal.valueOf(45.20));
        game2.setDescription("A game for fun people");
        game2.setStudio("LOL");
        game2.setQuantity(8);

        gameRepository.save(game2);


        Game game3 = new Game();
        game3.setTitle("Sims");
        game3.setESRB("PG");
        game3.setPrice(BigDecimal.valueOf(45.25));
        game3.setDescription("A game for innovating people");
        game3.setStudio("LOL");
        game3.setQuantity(78);

        gameRepository.save(game3);



        Game foundGame = gameRepository.findByTitle("Sims");
        assertEquals(foundGame,game3);

    }


}
