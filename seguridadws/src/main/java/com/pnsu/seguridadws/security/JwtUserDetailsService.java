package com.pnsu.seguridadws.security;

import com.pnsu.seguridadws.model.User;
import com.pnsu.seguridadws.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

   private final IUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOneByUsername(username);

        if (user==null){
            throw new UsernameNotFoundException("user not found:" +username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        String rol1 = "ADMIN";
        String rol2= "USER";
        roles.add(new SimpleGrantedAuthority(rol1));
        roles.add(new SimpleGrantedAuthority(rol2));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);

    }
}
