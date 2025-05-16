package org.jalcantararivera.mitosales.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl {
    public boolean hasAccess(String path){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username= auth.getName();

        log.info(username);
        auth.getAuthorities().forEach(e->log.info(e.getAuthority()));

        Optional<? extends GrantedAuthority> firstAuth = auth.getAuthorities().stream().findFirst();
        String authRole=firstAuth.get().getAuthority();

        log.info(authRole + "-----------"+  path );

        if ( path.equalsIgnoreCase("readAll") && authRole.equalsIgnoreCase("admin")){
            log.info("soy true");
            return true;
        }else  return  false;
    }
}
