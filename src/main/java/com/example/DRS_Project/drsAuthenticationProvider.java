package com.example.DRS_Project;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

public class drsAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        if ("drs".equals(username)){
            return UsernamePasswordAuthenticationToken.authenticated("drs",null, AuthorityUtils.createAuthorityList("ROLE_admin"));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
