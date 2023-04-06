package com.company.gamestore.controllers;

import com.company.gamestore.exceptions.NotFoundException;
import com.company.gamestore.models.TShirt;
import com.company.gamestore.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController

public class TShirtController {

    @Autowired
    TShirtRepository repo;


    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getallTShirts() {
        return repo.findAll();
    }


    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt getTshirtbyID(@PathVariable int id) {

        Optional<TShirt> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/tshirt/{color}") //what should we return the number, a list...?
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTshirtbyColor(@PathVariable String color) {
/*
        List<TShirt> tshirtsListByColor = new ArrayList<>();
        List<TShirt> everyTShirtInRepo = repo.findAll();

        for(TShirt tshirt: everyTShirtInRepo)
            if (tshirt.getColor().equals(color)) {
                tshirtsListByColor.add(tshirt);
            } //else {
               // return null;
                //shouldn't i loop to get all tshirts by colot
            //}
         return tshirtsListByColor;

 */

        List<TShirt> returnVal = repo.findAllTShirtsBySize(color);
        if (returnVal != null) {
            return returnVal;
        }
        throw new NotFoundException("tshirts of that color not found");
    }

    @GetMapping("/tshirt/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTshirtbySize(@PathVariable String size) {
/*
        List<TShirt> tshirtsListBySize = new ArrayList<>();
        List<TShirt> everyTShirtInRepo = repo.findAll();

        for(TShirt tshirt: everyTShirtInRepo)
            if (tshirt.getColor().equals(size)) {
                tshirtsListBySize.add(tshirt);
            } //else {
        // return null;
        //shouldn't i loop to get all tshirts by colot
        //}
        return tshirtsListBySize;
*/
        List<TShirt> returnVal = repo.findAllTShirtsBySize(size);
        if (returnVal != null) {
            return returnVal;
        }
        throw new NotFoundException("tshirts of that size not found");
    }


    @PutMapping("/tshirt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody TShirt tshirt) {
        repo.save(tshirt);
    }

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTshirt(@RequestBody TShirt tshirt) {
        return repo.save(tshirt);
    }

    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id){
        repo.deleteById(id);

    }



}
