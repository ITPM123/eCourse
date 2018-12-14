package com.scut.ecourse.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationSuccessHandler successHandler;
    @Autowired
    AuthenticationFailHandler failHandler;
    @Autowired
    MyLogoutSuccessHandler logoutSuccessHandler;
//    @Autowired
//    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter;
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
                //.antMatchers("/schoolAnnouncement/uploadFile").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().cors().disable();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSucceed")
                .logoutSuccessHandler(logoutSuccessHandler);
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                StringBuffer sb = new StringBuffer();
                sb.append("{\"errCode\":1,\"msg\":\"未登录\",\"data\":null}");
                out.write(sb.toString());
                out.flush();
                out.close();

            }
        });

    }

}
