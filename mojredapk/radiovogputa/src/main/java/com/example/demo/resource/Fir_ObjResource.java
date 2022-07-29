/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Firme;
import com.example.demo.model.FirmeObjekata;
import com.example.demo.model.Objekti;
import com.example.demo.repository.Fir_ObjRepository;
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
@RequestMapping(value = "/mojred/firme_objekata")
public class Fir_ObjResource {
    @Autowired
    Fir_ObjRepository fir_ObjRepository;
    @GetMapping(value = "/all")
    public List<FirmeObjekata> getAll(){
        return fir_ObjRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<FirmeObjekata> persist(@RequestBody final FirmeObjekata firmeObjekata){
        fir_ObjRepository.save(firmeObjekata);
        return fir_ObjRepository.findAll();
    }
     @GetMapping(value="/dajFirme/{fkObjekta}" )
    public List<Firme> dajFirmu(@PathVariable int fkObjekta){
           List<Firme> cuvaj=new ArrayList<>();
        List<FirmeObjekata> firmeObjekata =fir_ObjRepository.findAll();
        for(FirmeObjekata fobj: firmeObjekata){
            if(fobj.getFkObjekta().getId()==fkObjekta ){
            cuvaj.add(fobj.getFkFirme());
        }
        }        return  cuvaj;

    }
    
}
