/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Usluge;
import com.example.demo.repository.UslugeRepository;
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
@RequestMapping(value = "/mojred/usluge")
public class UslugeResource {
    @Autowired
    UslugeRepository uslugeRepository;
    @GetMapping(value = "/all")
    public List<Usluge> getAll(){
        return uslugeRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<Usluge> persist(@RequestBody final Usluge usluge){
        uslugeRepository.save(usluge);
        return uslugeRepository.findAll();
    }
}
