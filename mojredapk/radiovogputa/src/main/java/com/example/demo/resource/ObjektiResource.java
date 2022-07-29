/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Objekti;
import com.example.demo.repository.ObjektiRepository;
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
@RequestMapping(value = "/mojred/objekti")
public class ObjektiResource {
    @Autowired
    ObjektiRepository objektiRepository;
    @GetMapping(value = "/all")
    public List<Objekti> getAll(){
        return objektiRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<Objekti> persist(@RequestBody final Objekti objekti){
        objektiRepository.save(objekti);
        return objektiRepository.findAll();
    }
}
