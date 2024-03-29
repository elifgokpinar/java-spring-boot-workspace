package com.spring.controller;

import com.spring.entity.Sea;
import com.spring.repository.SeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/sea")
public class SeaController {

    private  final SeaRepository seaRepository;
    @Autowired
    public SeaController(SeaRepository seaRepository) {
        this.seaRepository = seaRepository;
    }

    @PostConstruct
    public void init(){
        Sea sea = new Sea();
        sea.setId("1");
        sea.setName("blacksea");
        sea.setArea("5");
        seaRepository.save(sea);

        Sea sea2 = new Sea();
        sea2.setId("2");
        sea2.setName("whitesea");
        sea2.setArea("4");
        seaRepository.save(sea2);
    }

    //eşleşmesi için ismin tamamının yazılması gerekiyor.
    //http://localhost:8080/sea/custom/blacksea
    @GetMapping("/custom/{search}")
    public ResponseEntity<List<Sea>> customQueryName(@PathVariable String search) {
        List<Sea> seaList = seaRepository.getByCustomQuery(search);
        return ResponseEntity.ok(seaList);
    }

    //eşleşmesi için ismin tamamının yazılması gerekiyor.
    @GetMapping("/name/{search}")
    public ResponseEntity<List<Sea>> searchByName(@PathVariable String search) {
        List<Sea> seaList = seaRepository.findByName(search);
        return ResponseEntity.ok(seaList);
    }

    //eşleşmesi için ismin başlangıcından itibaren eşleşmesi gerek. Ortasından eşleşmiyor.
    //b, bla, black yazılırsa eşleşiyor.
    //ack yazılırsa eşleşmez.
    //büyük küçük fark etmez.
    @GetMapping("/nameOrArea/{search}")
    public ResponseEntity<List<Sea>> searchByNameOrArea(@PathVariable String search) {
        List<Sea> seaList = seaRepository.findByNameLikeOrAreaLike(search, search);
        return ResponseEntity.ok(seaList);
    }

    /*
    @GetMapping("")
    public Iterable<Sea> findAll(){
        return seaRepository.findAll();
    }
    @PostMapping("")
    public Sea save(@RequestBody Sea sea){
        return seaRepository.save(sea);
    }
    @PutMapping("update")
    public Sea update(@RequestBody Sea sea) throws Exception {
        if (sea.getId()!=""){
            return seaRepository.save(sea);
        }
        throw new Exception("Id is required");
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        seaRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }*/
}
