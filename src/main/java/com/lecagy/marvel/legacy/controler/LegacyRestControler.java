/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lecagy.marvel.legacy.controler;

import com.lecagy.marvel.legacy.modelo.ComictDTO;
import com.lecagy.marvel.legacy.modelo.PScomicsDTO;
import com.lecagy.marvel.legacy.modelo.PSerieDTO;
import com.lecagy.marvel.legacy.modelo.PStoryDTO;
import com.lecagy.marvel.legacy.modelo.PersonajesDTO;
import com.lecagy.marvel.legacy.modelo.TrackinDTO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author jrlopez
 */
@RestController
@RequestMapping("/legacy")
public class LegacyRestControler {
    
private final ServiceMarvel service;

public LegacyRestControler(ServiceMarvel service) {
        this.service = service;
    }

 @GetMapping("/personajes")
    public Flux<PersonajesDTO> getpersonajes(@RequestParam(value = "name", required = false) String name ) {
        return service.obtenerPersonajes(name);
    }
    
 @GetMapping("/personajeserie")
    public Flux<PSerieDTO> getpersonajes(@RequestParam Integer idpersonaje) {
        return service.personajeserie(idpersonaje);
    }
 
  @GetMapping("/personajehistorietas")
    public Flux<PStoryDTO> getpersonajehistorias(@RequestParam Integer id) {
        return service.personajestorieta(id);
    }
  
 @GetMapping("/personajecomic")
    public Flux<PScomicsDTO> getpersonajecomic(@RequestParam Integer id) {
        return service.getpersonajecomic(id);
    }
 
    @GetMapping("/lstcomicAll")
    public Flux<ComictDTO> getAllComic() {
        return service.getAllComic();
    }

    @GetMapping("/buscarcomicid")
    public Flux<ComictDTO> getcomicxid(@RequestParam Integer id){
        return service.getcomicxid(id);
    }
    
    @GetMapping("/busquedagenusuario")
    public List<TrackinDTO> getusuariobusquedas(@RequestParam String user){
        return service.getbusquedadAlluser(user);
    }
    
    @GetMapping("/busquedauserstorie")
    public List<TrackinDTO> getuserbusquedasstoris(@RequestParam String user){
        return service.getbusquedadstoriuser(user);
    }
    
    
}
