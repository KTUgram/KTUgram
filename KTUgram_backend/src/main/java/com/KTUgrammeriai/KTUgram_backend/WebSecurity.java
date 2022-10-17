package com.KTUgrammeriai.KTUgram_backend;

import com.KTUgrammeriai.KTUgram_backend.authentification.SessionHandlerService;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private SessionHandlerService sessionHandlerService;

    public WebSecurity(final SessionHandlerService sessionHandlerService) {
        this.sessionHandlerService = sessionHandlerService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        System.out.println("WebSecurity configure");
        http.cors().and().csrf().disable().authorizeRequests().antMatchers( "/user/**").permitAll().anyRequest()
                        .authenticated().and().addFilter(new AuthorizationFilter(authenticationManager(), sessionHandlerService))
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
