package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.models.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;


//    Get all Games
    @QueryMapping
    public List<Game> games(){return gameRepository.findAll(); }

//    Get a Game by ID
    @QueryMapping
    public Game findGameByID(@Argument int game_id){
        Optional<Game> game = gameRepository.findById(game_id);
        if(game.isPresent()){
            return game.get();
        }
        else{
            return null;
        }
    }


//    Get a Game by Title
@QueryMapping
public Game findGameByTitle(@Argument String title){
    Game game = gameRepository.findByTitle(title);
    if(game != null){
        return game;
    }
    else{
        return null;
    }
}

//    Get a Game by ESRB rating
@QueryMapping
public List<Game> findGameByESRB(@Argument String esrbRating){
    List<Game> gameList= gameRepository.findByesrbRating(esrbRating);
    if(gameList != null)
    {
        return gameList;
    }
    else
    {
        return null;
    }
}

//    Get a Game by Studio
@QueryMapping
public List<Game> findGameByStudio(@Argument String studio){
    List<Game> gameList= gameRepository.findByStudio(studio);
    if(gameList != null)
    {
        return gameList;
    }
    else
    {
        return null;
    }
}

//    Get all Consoles
@QueryMapping
public List<Console> consoles(){return consoleRepository.findAll(); }


//    Get a Console by ID
@QueryMapping
public Console findConsoleByID(@Argument int console_id){
    Optional<Console> console = consoleRepository.findById(console_id);
    if(console.isPresent()){
        return console.get();
    }
    else{
        return null;
    }
}

//    Get a Console by Manufacturer
@QueryMapping
public List<Console> findConsoleByManufacturer(@Argument String manufacturer){
    List<Console> console = consoleRepository.findByManufacturer(manufacturer);
    if(console !=null){
        return console;
    }
    else{
        return null;
    }
}
}
