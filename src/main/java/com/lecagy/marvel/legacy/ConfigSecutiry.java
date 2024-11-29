/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lecagy.marvel.legacy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



/**
 *
 * @author jrlopez
 */
@Configuration
@EnableWebSecurity
public class ConfigSecutiry {
        
    
    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().
                anyRequest()
                .authenticated()
                .and().httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }


    
}
