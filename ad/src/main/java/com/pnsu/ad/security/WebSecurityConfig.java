package com.pnsu.ad.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryAuthProvider() {
        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider("pnsu.gob.pe", "ldap://10.165.7.6");
        // You can configure additional properties here if needed, e.g.,
        // provider.setSearchFilter("(&(objectClass=user)(sAMAccountName={0}))");
        // provider.setUserDetailsContextMapper(yourUserDetailsContextMapper());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(activeDirectoryAuthProvider());
        return authenticationManagerBuilder.build();
    }





    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((req) -> req
                        .anyRequest().fullyAuthenticated())
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }








}
