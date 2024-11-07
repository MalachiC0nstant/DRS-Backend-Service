package com.example.DRS_Project;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/")
    public String publicPage(){
        return "Bruh moment: public page";
    }
    @GetMapping("/private")
    public String privatePage(Authentication authentication) {

        return "Welcome to the private page ~[ " + getName(authentication) + " ]~";
    }
    private static String getName(Authentication authentication) {
        return Optional.of(authentication.getPrincipal())
                // Trying OidcUser if possible
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(oidcUser -> (String) oidcUser.getAttributes().get("email"))
                // Trying casting to OAuth2User if not OidcUser
                .or(() -> Optional.of(authentication.getPrincipal())
                        .filter(OAuth2User.class::isInstance)
                        .map(OAuth2User.class::cast)
                        .map(oauth2User -> (String) oauth2User.getAttributes().get("email")))
                // If neither, use username
                .orElseGet(authentication::getName);
    }
}

