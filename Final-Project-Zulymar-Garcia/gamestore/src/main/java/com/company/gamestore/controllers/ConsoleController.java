package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository repo;

    // GET route that gets all consoles
    @GetMapping("/consoles")
    public List<Console> getConsoles() {
        return repo.findAll();
    }

    // GET route that reads consoles by ID
    @GetMapping("/consoles/{id}")
    public Console getConsoleById(@PathVariable Integer id) {
        Optional<Console> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            throw new NotFoundException("Consoles with that ID not found.");
        }
    }

    // GET route that reads consoles by manufacturer
    @GetMapping("/consoles/{manufacturer}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        List<Console> returnVal = repo.findByManufacturer(manufacturer);
        if (returnVal != null) {
            return returnVal;
        }
        throw new NotFoundException("Consoles by that manufacturer not found.");
    }

    // POST route that creates a console
    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return repo.save(console);
    }

    // PUT route that updates console
    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        repo.save(console);
    }

    // DELETE route that deletes a console
    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        repo.deleteById(id);
    }

}
