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
import com.lecagy.marvel.legacy.modelo.entity.Trackinuser;
import com.lecagy.marvel.legacy.repository.TrackinUserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jrlopez
 */
@Service
public class ServiceMarvel {
    private String url="";
    private final WebClient webClient;
    
    @Autowired
    TrackinUserRepository repository;

    
    
    public ServiceMarvel(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/marvel").build();
    }

    public Flux<PersonajesDTO> obtenerPersonajes(String name) {
        if(Objects.nonNull(name)){
            url="/obtenerpersonajes?name="+name;
        }else{
            url="/obtenerpersonajes";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        creartrackinusuario(username, 0, url);
        return webClient.get().uri(url).retrieve().bodyToFlux(PersonajesDTO.class);
    }
    
     public Flux<PSerieDTO> personajeserie(Integer id) {
        if(Objects.nonNull(id)){
            url="/personajeserie?idpersonaje="+id;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        creartrackinusuario(username, 0, url);
        return webClient.get().uri(url).retrieve().bodyToFlux(PSerieDTO.class);
    }
     
    public Flux<PStoryDTO> personajestorieta(Integer id) {
        if(Objects.nonNull(id)){
            url="/personajehistorietas?id="+id;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        creartrackinusuario(username, 1, url);
        return webClient.get().uri(url).retrieve().bodyToFlux(PStoryDTO.class);
    }
    
    public Flux<PScomicsDTO> getpersonajecomic(Integer id) {
        if(Objects.nonNull(id)){
            url="/personajecomic?id="+id;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        creartrackinusuario(username, 0, url);
        return webClient.get().uri(url).retrieve().bodyToFlux(PScomicsDTO.class);
    }
    
    public Flux<ComictDTO> getAllComic() {
            url="/lstcomicAll";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        creartrackinusuario(username, 0, url);
        return webClient.get().uri(url).retrieve().bodyToFlux(ComictDTO.class);
    }
    
    public Flux<ComictDTO> getcomicxid(Integer id) {
        if (Objects.nonNull(id)) {
            url = "/buscarcomicid?id=" + id;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        creartrackinusuario(username, 0, url);
        return webClient.get().uri(url).retrieve().bodyToFlux(ComictDTO.class);
    }
    
    public void creartrackinusuario(String usuario, Integer tipobusqueda, String url){
        Trackinuser trackin = new Trackinuser();
        trackin.setFechapeticion(new Date());
        trackin.setTipobuqueda(tipobusqueda);
        trackin.setUrlpeticion(url);
        trackin.setUser(usuario);
        repository.save(trackin);
    }
    
    public List<TrackinDTO> getbusquedadAlluser(String usuario){
        List<TrackinDTO> lstTrackin = new ArrayList<>();
        List<Trackinuser> lst= repository.lsttrakingeneral(usuario);
        if(!lst.isEmpty()){
            for(Trackinuser t: lst){
                TrackinDTO dto= new TrackinDTO();
                dto.id=t.getId();
                dto.urlpeticion=t.getUrlpeticion();
                dto.user=t.getUser();
                if(t.getTipobuqueda()==1){
                    dto.tipobuqueda="Busqueda Historietas";
                }else{
                    dto.tipobuqueda="Busqueda Generales";
                }
                dto.fechapeticion = t.getFechapeticion().toString();
                lstTrackin.add(dto);
            }
        }
        return lstTrackin;
    }
    
    public List<TrackinDTO> getbusquedadstoriuser(String usuario){
        List<TrackinDTO> lstTrackin = new ArrayList<>();
        List<Trackinuser> lst= repository.lststorieuser(usuario);
        if(!lst.isEmpty()){
            for(Trackinuser t: lst){
                TrackinDTO dto= new TrackinDTO();
                dto.id=t.getId();
                dto.urlpeticion=t.getUrlpeticion();
                dto.user=t.getUser();
                if(t.getTipobuqueda()==1){
                    dto.tipobuqueda="Busqueda Historietas";
                }else{
                    dto.tipobuqueda="Busqueda Generales";
                }
                dto.fechapeticion = t.getFechapeticion().toString();
                lstTrackin.add(dto);
            }
        }
        return lstTrackin;
    }
    
    
}
