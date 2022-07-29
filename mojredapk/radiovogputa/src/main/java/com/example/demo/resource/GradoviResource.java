/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Gradovi;
import com.example.demo.repository.GradoviRepository;
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
@RequestMapping(value = "/mojred/gradovi")
public class GradoviResource {
    @Autowired
    GradoviRepository gradoviRepository;
    @GetMapping(value = "/all")
    public List<Gradovi> getAll(){
        return gradoviRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<Gradovi> persist(@RequestBody final Gradovi gradovi){
        gradoviRepository.save(gradovi);
        return gradoviRepository.findAll();
    }
}
