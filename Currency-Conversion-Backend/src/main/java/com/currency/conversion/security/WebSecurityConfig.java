package com.currency.conversion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by ksrivas on 5/30/2018.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_COOKIE = "access_token";
    public static final String ACCOUNT_CLAIM = "accountIds";
    public static final String REQUEST_ACCOUNT = "accountId";
    public static final String PATH_ACCOUNT = "account";

    @Autowired
    private RESTAuthenticationEntryPoint restAuthenticationEntryPoint;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

//        http.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
//
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/**/*.woff",
//                        "/**/*.woff2",
//                        "/**/*.ttf",
//                        "/webjars/**"
//                ).permitAll()
//                .antMatchers(HttpMethod.GET, "/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/auth").permitAll()
//                .antMatchers(HttpMethod.GET, "/info").permitAll()
//                .antMatchers(HttpMethod.GET, "/health").permitAll()
//                .antMatchers("/rest/**").permitAll()
//                .antMatchers("/view/**").permitAll()
//                .anyRequest().authenticated();
    }


}
