package org.chime.chime.services;

import lombok.AllArgsConstructor;
import org.chime.chime.entities.User;
import org.chime.chime.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional <User> user;
        if (usernameOrEmail.contains("@")) {
            user = userRepository.findByEmail(usernameOrEmail);
        } else {
            user = userRepository.findByUsername(usernameOrEmail);
        }
        if(user.isPresent()) {
            var userDetails = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userDetails.getUsername())
                    .password(userDetails.getPasswordHash())
                    .build();
        } else  {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
