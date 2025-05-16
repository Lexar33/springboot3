package org.jalcantararivera.mitosales.security;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.model.User;
import org.jalcantararivera.mitosales.repo.IUserRepo;
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
        //FROM User u WHERE u.username=: username
        User user = repo.findOneByUsername(username);
        if (user ==null){
            throw new UsernameNotFoundException("user not found: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        String rol = user.getRole().getName();
        roles.add(new SimpleGrantedAuthority(rol));


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }
}
