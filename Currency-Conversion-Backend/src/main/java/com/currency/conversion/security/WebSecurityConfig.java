package com.currency.conversion.security;

import com.currency.conversion.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

//        httpSecurity
//                .authorizeRequests()
//                .antMatchers(
//                        "/",
//                        "/console/**",
//                        "/registration",
//                        "/js/**",
//                        "/css/**",
//                        "/img/**",
//                        "/webjars/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();

//        http.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userInfoService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
