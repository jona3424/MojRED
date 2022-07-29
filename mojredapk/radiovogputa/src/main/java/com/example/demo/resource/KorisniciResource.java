/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Korisnici;
import com.example.demo.repository.KorisniciRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mojred/korisnici" )
public class KorisniciResource {
    @Autowired
    KorisniciRepository korisniciRepository;
    @GetMapping(value = "/all")
    public List<Korisnici> getAll(){
        return korisniciRepository.findAll();
}
    @PostMapping(value = "/load")
    public List<Korisnici> persist(@RequestBody final Korisnici korisnici){
        korisniciRepository.save(korisnici);
        return korisniciRepository.findAll();
    }
    @GetMapping(value="/dajUser/{korisnickoime} & {lozinka}" )
    public Korisnici dajUser(@PathVariable String korisnickoime,@PathVariable String lozinka){
        List<Korisnici> korisnici =korisniciRepository.findAll();
        for(Korisnici k:korisnici){
            System.out.println(k.getKorisnickoime() + "||||" + k.getLozinka() + "||||" + korisnickoime + "||||" + lozinka);
            System.out.println(k.getKorisnickoime().equals(korisnickoime) && k.getLozinka().equals(lozinka));
            if(k.getKorisnickoime().equals(korisnickoime) && k.getLozinka().equals(lozinka) ){
            return k;
        }
        
        }
        return null;
    }
//    @GetMapping(value="/dajUser/{korisnickoime} & {lozinka}" )
//    public int dajUser(@PathVariable String korisnickoime,@PathVariable String lozinka){
//        List<Korisnici> korisnici =korisniciRepository.findAll();
//        for(Korisnici k:korisnici){
//            System.out.println(k.getKorisnickoime() + "||||" + k.getLozinka() + "||||" + korisnickoime + "||||" + lozinka);
//            System.out.println(k.getKorisnickoime().equals(korisnickoime) && k.getLozinka().equals(lozinka));
//            if(k.getKorisnickoime().equals(korisnickoime) && k.getLozinka().equals(lozinka) ){
//            return k.getId();
//        }
//        
//        }
//        return -1;
//    }
    
    
    
    
}
