/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lecagy.marvel.legacy.repository;

import com.lecagy.marvel.legacy.modelo.entity.Trackinuser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jrlopez
 */
public interface TrackinUserRepository extends JpaRepository<Trackinuser, Integer>{
     @Query("SELECT c FROM Trackinuser c WHERE c.user = ?1")
     public List<Trackinuser> lsttrakingeneral(String user);
     
     @Query("SELECT c FROM Trackinuser c WHERE c.user = ?1 and tipobuqueda=1")
     public List<Trackinuser> lststorieuser(String user);
    
}
