/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Lokacije;
import com.example.demo.repository.LokacijeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author korisnik
 */
@RestController
@RequestMapping(value = "/mojred/lokacije")
public class LokacijeResource {
    @Autowired
    LokacijeRepository lokacijeRepository;
    @GetMapping(value = "/all")
    public List<Lokacije> getAll(){
        return lokacijeRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<Lokacije> persist(@RequestBody final Lokacije lokacije){
        lokacijeRepository.save(lokacije);
        return lokacijeRepository.findAll();
    }
}
