package org.example.moviesystem.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            // If the connection is successful, grant roles based on the username
            String role = "ROLE_" + username.toUpperCase();
            return new UsernamePasswordAuthenticationToken(
                    new User(username, password, Collections.singletonList(new SimpleGrantedAuthority(role))),
                    password,
                    Collections.singletonList(new SimpleGrantedAuthority(role))
            );

        } catch (SQLException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

