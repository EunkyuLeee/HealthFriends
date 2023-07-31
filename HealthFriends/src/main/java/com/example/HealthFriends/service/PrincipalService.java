package com.example.HealthFriends.service;

import com.example.HealthFriends.entity.User;
import com.example.HealthFriends.oauth2.PrincipalDetails;
import com.example.HealthFriends.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService {

    private final JPAUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }
}
