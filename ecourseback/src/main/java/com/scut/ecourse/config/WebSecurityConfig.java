package com.scut.ecourse.config;

import com.scut.ecourse.config.AuthenticationFailHandler;
import com.scut.ecourse.config.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationSuccessHandler successHandler;
    @Autowired
    AuthenticationFailHandler failHandler;
    @Autowired
    MyLogoutSuccessHandler logoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSucceed")
                .logoutSuccessHandler(logoutSuccessHandler);
    }
}
