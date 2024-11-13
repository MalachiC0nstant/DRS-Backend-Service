package com.example.DRS_Project;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.ObjectPostProcessor;
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


        var configurer = new RobotLoginConfigurer()
                .password("beep-boop")
                .password("boop-beap");
        return http
                .csrf().disable() // TODO: Get rid of this soon
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("/register").permitAll(); // TODO: Get rid of this soon
                    authorizeConfig.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // TODO: fix this too
                    authorizeConfig.requestMatchers("error").permitAll();
                    authorizeConfig.requestMatchers("favicon.ico").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .oauth2Login(
                        oauth2Configurer -> {
                            oauth2Configurer.withObjectPostProcessor(
                                    new ObjectPostProcessor<AuthenticationProvider>() {
                                        @Override
                                        public <T extends AuthenticationProvider> T postProcess(T object) {
                                            return (T) new RateLimitedAuthenticationProvider(object);
                                        }
                                    }
                            );
                        }
                )
                .apply(configurer).and() // or just create the new RobotLoginConfigurer here and set the passwords
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
