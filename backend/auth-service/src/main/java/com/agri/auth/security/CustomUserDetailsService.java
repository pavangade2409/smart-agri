package com.agri.auth.security;

import com.agri.auth.entity.User;
import com.agri.auth.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetailsService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        User user =
                userRepository
                        .findByEmail(
                                email.trim()
                                        .toLowerCase()
                        )
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found: "
                                                + email
                                )
                        );


        return new org.springframework.security
                .core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                !user.isAccountLocked(),
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_"
                                        + user.getRole().name()
                        )
                )
        );
    }
}