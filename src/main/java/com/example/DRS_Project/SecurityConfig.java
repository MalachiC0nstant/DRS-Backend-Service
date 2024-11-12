package com.example.DRS_Project;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationEventPublisher publisher
    ) throws Exception {
        http.getSharedObject(AuthenticationManagerBuilder.class).authenticationEventPublisher(publisher); // Can supposedly ignore this?

        var authManager = new ProviderManager(new RobotAuthenticationProvider(List.of("beep-boop", "boop-beep")));
        authManager.setAuthenticationEventPublisher(publisher);

        return http
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("error").permitAll();
                    authorizeConfig.requestMatchers("favicon.ico").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin(withDefaults())
                .oauth2Login(withDefaults())
                .addFilterBefore(new RobotFilter(authManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(new drsAuthenticationProvider())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{noop}password")
                        .authorities("ROLE_user")
                        .build()
        );
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> successListener(){
        return event -> {
            System.out.println(String.format("Success [%s] %s", event.getAuthentication().getClass().getSimpleName(), event.getAuthentication().getName()));
        };
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

}
