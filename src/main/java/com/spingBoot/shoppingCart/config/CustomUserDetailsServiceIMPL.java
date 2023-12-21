package com.spingBoot.shoppingCart.config;

import com.spingBoot.shoppingCart.Entity.UserEntity;
import com.spingBoot.shoppingCart.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class CustomUserDetailsServiceIMPL implements UserDetailsService {
   @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserEntity> user=userRepository.findByEmail(username);
        return user.map(CustomUserDetailsIMPL::new).orElseThrow(()->new UsernameNotFoundException("user name does not exist"));
    }
}
