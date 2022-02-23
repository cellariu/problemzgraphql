package com.cami.udemy.graphql.problemz.problemzgraphql.security;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.repository.UserzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ProblemzSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserzRepository userzRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var authProvider = new ProblemzAutheticationProvider(userzRepository);
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new ProblemzSecurityFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        http.antMatcher("/**")
                .authorizeRequests().anyRequest().authenticated()
                    .and()
                .csrf().disable();
    }
}
