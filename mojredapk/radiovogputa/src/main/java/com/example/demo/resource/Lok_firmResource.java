/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Lokacije;
import com.example.demo.model.LokacijeFirmi;
import com.example.demo.repository.Lok_firmRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author korisnik
 */
@RestController
@RequestMapping(value = "/mojred/lokacije_firmi")
public class Lok_firmResource {
    @Autowired
    Lok_firmRepository lok_firmRepository;
    @GetMapping(value = "/all")
    public List<LokacijeFirmi> getAll(){
        return lok_firmRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<LokacijeFirmi> persist(@RequestBody final LokacijeFirmi lokacijeFirmi){
        lok_firmRepository.save(lokacijeFirmi);
        return lok_firmRepository.findAll();
    }
    
    
     @GetMapping(value="/dajLokacije/{fkFirme}" )
    public List<Lokacije> dajFirmu(@PathVariable int fkFirme){
           List<Lokacije> cuvaj=new ArrayList<>();
        List<LokacijeFirmi> lokacijeFirmi = lok_firmRepository.findAll();
        for(LokacijeFirmi lkfr: lokacijeFirmi){
            if(lkfr.getFkFirme().getId()==fkFirme ){
            cuvaj.add(lkfr.getFkLokacije());
        }
        }        return  cuvaj;

    }
    
    
    
    
}
