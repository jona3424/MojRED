/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.resource;

import com.example.demo.model.Brojevi;
import com.example.demo.repository.BrojeviRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author korisnik
 */
@RestController
@RequestMapping(value = "/mojred/brojevi")
public class BrojeviResource {

    @Autowired
    BrojeviRepository brojeviRepository;

//    @PutMapping("/stigaoNaRed/{id}")
//    public String stigaoNaRed(@PathVariable int id){
//         brojeviRepository.stigaoNaRed(id);return "ok";
//    }
    @GetMapping(value = "/all")
    public List<Brojevi> getAll() {
        return brojeviRepository.findAll();
    }

    @PostMapping(value = "/load")
    public Brojevi persist(@RequestBody final Brojevi brojevi) {
        return brojeviRepository.save(brojevi);
//         brojeviRepository.findAll();
    }

//    @GetMapping(value="/dajbroj/{fkLokacije}&{fkKorisnici}&{fkUsluge}")
//    public Brojevi dajBroj(@PathVariable int fkLokacije,@PathVariable int fkKorisnici,@PathVariable int fkUsluge){
//        List<Brojevi> brojevi =brojeviRepository.findAll();
//        for(Brojevi b: brojevi){
//            if(b.getFkLokacije().getId().equals(fkLokacije) && b.getFkKorisnici().getId().equals(fkKorisnici) && b.getFkUsluge().getId().equals(fkUsluge) ){
//            return b;
//            }
//        }
//        return null;
//    }
    @GetMapping("/syncAttempt/{id}")
    public String sync(@PathVariable int id) throws FileNotFoundException {
        File fajl = new File("C:\\Users\\Jona\\Desktop\\filemojred\\" + id + ".txt");
        Scanner sc = new Scanner(fajl);
        return sc.nextLine();
    }

    @GetMapping("/reset/{id}")
    public long reset(@PathVariable int id) throws FileNotFoundException {
        File fajl = new File("C:\\Users\\Jona\\Desktop\\filemojred\\" + id + ".txt");
        PrintStream ps = new PrintStream(fajl);
//        Timer timer = new Timer();
        //      int counter = 0;
        //    TimerTask tt = new TimerTask(){
        //      @Override
        //    public void run() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        ps.println(cal.getTimeInMillis());
        //  }

        //    };
        return cal.getTimeInMillis();
    }

    @GetMapping(value = "/dalokacije/{fkLokacije}")
    public List<Brojevi> dajLokacije(@PathVariable int fkLokacije) {
        List<Brojevi> cuvaj = new ArrayList<>();
        List<Brojevi> brojevi = brojeviRepository.findAll();
        for (Brojevi b : brojevi) {
            if (b.getFkLokacije().getId().equals(fkLokacije) && b.getUredu() == true) {
                cuvaj.add(b);
            }
        }
        return cuvaj;
    }

    @GetMapping(value = "/dajfkLok/{fkLokacije}&{fkKorisnici}")
    public int zaBrojanje(@PathVariable int fkLokacije, @PathVariable int fkKorisnici) {
        int br = 0;
        List<Brojevi> brojevi = brojeviRepository.findAll();
        for (Brojevi b : brojevi) {
            if (b.getUredu() == true) {
                if (b.getId() <= fkKorisnici) {
                    if (b.getFkLokacije().getId().equals(fkLokacije)) {
                        br++;
                    }
                }
            }
        }
        return br;

    }

    @GetMapping(value = "/dajbul/{fkLokacije}&{fkKorisnici}")
    public boolean zatruefalse(@PathVariable int fkLokacije, @PathVariable int fkKorisnici) {
        List<Brojevi> brojevi = brojeviRepository.findAll();
        boolean bul = false;
        for (Brojevi b : brojevi) {
            if (b.getId().equals(fkKorisnici)) {
                if (b.getFkLokacije().getId().equals(fkLokacije)) {
                    bul = b.getUredu();
                }
            }
        }
        return bul;

    }

    @PutMapping(value = "update/{id}")
    public String updateBroj(@PathVariable int id) throws FileNotFoundException {
        List<Brojevi> brojevi1 = brojeviRepository.findAll();
        for (Brojevi b : brojevi1) {
            if (b.getId() == id) {
                b.setUredu(false);
                brojeviRepository.save(b);
                List<Brojevi> test = dajLokacije(b.getFkLokacije().getId());
                System.out.println(test.size());
                       
                if(test.isEmpty()){
                
                    File fajl = new File("C:\\Users\\Jona\\Desktop\\filemojred\\" + b.getFkLokacije().getId() + ".txt");
                    PrintStream ps = new PrintStream(fajl);
                    ps.println("69");
                }
                    
                return "ok";
            }
        }
        return "nieok";
    }

}
