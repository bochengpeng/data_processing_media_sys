package org.example.moviesystem.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(DatabaseAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
            .requestMatchers("/login", "/login?error=true").permitAll()
            .requestMatchers("/popular", "/popular_series", "/home", "/tv_rate", "/popular_series", "/series", "/ratedxml").authenticated()
//                        .requestMatchers("/junior/**").hasRole("JUNIOR")
//                        .requestMatchers("/medior/**").hasRole("MEDIOR")
//                        .requestMatchers("/senior/**").hasRole("SENIOR")
//                        .requestMatchers("/apiuser/**").hasRole("APIUSER")
            .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true) // Invalidate session on logout
                    .clearAuthentication(true)
                    .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1) // Allow only one session per user
                .expiredUrl("/login?error=sessionExpired") // Redirect if session expires
            )
            .sessionManagement(session -> session
                    .sessionFixation().migrateSession() // Protect against session fixation attacks
                    .invalidSessionUrl("/login?error=invalidSession") // Redirect for invalid sessions
            );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .build();
    }
}



