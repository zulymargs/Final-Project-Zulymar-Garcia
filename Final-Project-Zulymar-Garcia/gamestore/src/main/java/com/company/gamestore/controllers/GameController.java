package com.company.gamestore.controllers;
import com.company.gamestore.models.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    GameRepository repo;

    //Creates a new game
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return repo.save(game);
    }

    //Get game by ID
    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameByID(@PathVariable int id) {
        Optional<Game> value = repo.findById(id);
        if (value.isPresent()) {
            return value.get();
        } else {
            return null;
        }
    }

    //Get games by studio
    @GetMapping("/games/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio) {
        List<Game> gameList= repo.findByStudio(studio);
        if(gameList != null)
        {
            return gameList;
        }
        else
        {
            return null;
        }
    }

    //Get games by ESRB rating
    @GetMapping("/games/ESRB/{esrbRating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByESRB(@PathVariable String esrbRating) {
        List<Game> gameList= repo.findByesrbRating(esrbRating);
        if(gameList != null)
        {
            return gameList;
        }
        else
        {
            return null;
        }
    }

    //Get game by title
    @GetMapping("/games/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameByTitle(@PathVariable String title) {
        Game game= repo.findByTitle(title);
        if(game !=null)
        {
           return game;
        }
        else
        {
            return null;
        }
    }


    //Read All
    @GetMapping("/games")
    public List<Game> getAllAuthors() {
        return repo.findAll();
    }

    //Update
    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExistingAuthor(@RequestBody Game game) {
        repo.save(game);
    }

    //Delete
    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        repo.deleteById(id);
    }
}
