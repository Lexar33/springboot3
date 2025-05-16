package org.jalcantararivera.mitosales.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Profile(value={"dev","qa","prod"})
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // con esto funciona el @PreAuthorize
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticacionEntryPoint jwtAuthenticacionEntryPoint;
    private final UserDetailsService jwtUserDetailsService;
    private final JwtResquestFilter jwtResquestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        //Desde spring boot 3.1+
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // csrf-> csrf.disable()
                .authorizeHttpRequests(req-> req
                        .requestMatchers(antMatcher("/login")).permitAll()
                        .requestMatchers(antMatcher("/rest/**")).permitAll()
                        .requestMatchers(antMatcher("/categories/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e->e.authenticationEntryPoint(jwtAuthenticacionEntryPoint))
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(Customizer.withDefaults());

        httpSecurity.addFilterBefore(jwtResquestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
