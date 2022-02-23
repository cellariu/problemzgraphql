package com.cami.udemy.graphql.problemz.problemzgraphql.security;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Userz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.UserzRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class ProblemzAutheticationProvider implements AuthenticationProvider {

    private UserzRepository userzRepository;

    public ProblemzAutheticationProvider(UserzRepository userzRepository) {
        this.userzRepository = userzRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var userz = userzRepository
                .findUserByToken(authentication.getCredentials().toString()).orElse(new Userz());

        return new UsernamePasswordAuthenticationToken(
                userz, authentication.getCredentials().toString(), getAuthorities(userz.getUserRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getAuthorities(String userRole) {
        var authorities = new ArrayList<GrantedAuthority>();

        if (StringUtils.isNotBlank(userRole)) {
            authorities.add(new SimpleGrantedAuthority(userRole));
        }
        return authorities;
    }
}
