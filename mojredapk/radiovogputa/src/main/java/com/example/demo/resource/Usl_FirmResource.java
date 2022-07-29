/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Lokacije;
import com.example.demo.model.LokacijeFirmi;
import com.example.demo.model.Usluge;
import com.example.demo.model.UslugeFirmi;
import com.example.demo.repository.Usl_FirmRepository;
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
@RequestMapping(value = "/mojred/usluge_firmi")
public class Usl_FirmResource {
    @Autowired
    Usl_FirmRepository usl_FirmRepository;
    @GetMapping(value = "/all")
    public List<UslugeFirmi> getAll(){
        return usl_FirmRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<UslugeFirmi> persist(@RequestBody final UslugeFirmi uslugeFirmi){
        usl_FirmRepository.save(uslugeFirmi);
        return usl_FirmRepository.findAll();
    }
    
    @GetMapping(value="/dajUsluge/{fkFirme}" )
    public List<Usluge> dajFirmu(@PathVariable int fkFirme){
           List<Usluge> cuvaj=new ArrayList<>();
        List<UslugeFirmi> uslugeFirmi = usl_FirmRepository.findAll();
        for(UslugeFirmi usfr: uslugeFirmi){
            if(usfr.getFkFirme().getId()==fkFirme ){
            cuvaj.add(usfr.getFkUsluge());
        }
        }        return  cuvaj;

    }
    
    
     @GetMapping(value="/dajUslugeFirmi/{fkFirme} & {fkUsluge}" )
    public UslugeFirmi dajObjekat(@PathVariable int fkFirme,@PathVariable int fkUsluge){
        List<UslugeFirmi> uslugeFirmi = usl_FirmRepository.findAll();
        for(UslugeFirmi usfr: uslugeFirmi){
                System.out.println(usfr.getFkFirme()+ "||||" + usfr.getFkUsluge()+ "||||" + fkFirme + "||||" + fkUsluge);
            System.out.println(usfr.getFkFirme().getId().equals(fkFirme) && usfr.getFkUsluge().getId().equals(fkUsluge));
            if(usfr.getFkFirme().getId().equals(fkFirme) && usfr.getFkUsluge().getId().equals(fkUsluge) ){
                return usfr;
        }
        }        
           return null;
    }
    
}
