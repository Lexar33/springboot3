package com.pnsu.seguridadws.controller;

import com.pnsu.seguridadws.security.JwtRequest;
import com.pnsu.seguridadws.security.JwtResponse;
import com.pnsu.seguridadws.security.JwtTokenUtil;
import com.pnsu.seguridadws.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @GetMapping("/")
    public String logintest(){
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login (@RequestBody JwtRequest req) throws Exception {
        try{
            authenticate(req.getUsername(),req.getPassword());
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new JwtResponse(e.getMessage()));
        }

        final UserDetails userDetails= jwtUserDetailsService.loadUserByUsername(req.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch(  DisabledException e){
            throw new Exception("USER_DISABLED",e);
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }

}
