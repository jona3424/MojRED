/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Firme;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author korisnik
 */
public interface FirmeRepository extends JpaRepository<Firme, Integer> {
    
}
