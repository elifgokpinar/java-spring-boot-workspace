package org.springbootmongo.controller;


import org.springbootmongo.entity.Flower;
import org.springbootmongo.repository.FlowerRepository;
import org.springbootmongo.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlowerController {
    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private FlowerService flowerService;

    //GET ALL INFO
    @GetMapping("/flowers")
    public ResponseEntity<List<Flower>>  getAllFlowers(){
        List<Flower> flowers = flowerService.getAllFlowers();
        return new ResponseEntity<>(flowers,(flowers.size() > 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //GET ONLY SPECIFIC INFO BY ID
    @GetMapping("/flowers/{id}")
    public ResponseEntity<?> getFlowerById(@PathVariable("id") String id){
        try{
            Flower flower = flowerService.getFlowerById(id);
            return new ResponseEntity<>(flower, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //ADD
    @PostMapping("flowers")
    public ResponseEntity<?> addFlower(@RequestBody Flower flower){
        try{
            Flower flowerResponse = flowerService.createFlower(flower);
            return new ResponseEntity<>(flowerResponse,HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE
    @PutMapping("flowers/{id}")
    public ResponseEntity<?> updateFlower(@PathVariable("id") String id, @RequestBody Flower flower){
        try{
            flowerService.updateFlower(id,flower);
            return new ResponseEntity<>(flower,HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //DELETE*
    @DeleteMapping("flowers/{id}")
    public ResponseEntity<?> deleteFlowerById(@PathVariable("id") String id){
        try
        {
            flowerService.deleteFlower(id);
            return new ResponseEntity<>("Deleted succesfully id:" + id, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
