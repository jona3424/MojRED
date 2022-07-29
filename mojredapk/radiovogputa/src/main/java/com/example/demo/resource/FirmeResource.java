/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Firme;
import com.example.demo.repository.FirmeRepository;
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
@RequestMapping(value = "/mojred/firme")
public class FirmeResource {
    @Autowired
    FirmeRepository firmeRepository;
    @GetMapping(value = "/all")
    public List<Firme> getAll(){
        return firmeRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<Firme> persist(@RequestBody final Firme firme){
        firmeRepository.save(firme);
        return firmeRepository.findAll();
    }
}
