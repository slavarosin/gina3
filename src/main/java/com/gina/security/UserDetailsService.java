package com.gina.security;

import com.gina.domain.Authority;
import com.gina.domain.User;
import com.gina.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();

        // User userFromDatabase = userRepository.findOne(lowercaseLogin);
        // if (userFromDatabase == null) {
        // throw new UsernameNotFoundException("User " + lowercaseLogin +
        // " was not found in the database");
        // } else if (!userFromDatabase.getActivated()) {
        // throw new UserNotActivatedException("User " + lowercaseLogin +
        // " was not activated");
        // }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // for (Authority authority : userFromDatabase.getAuthorities()) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        grantedAuthorities.add(grantedAuthority);
        // }

        return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                "b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1", grantedAuthorities);
    }
}
