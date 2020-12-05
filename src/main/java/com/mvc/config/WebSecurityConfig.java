package com.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mvc.authentication.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyDBAuthenticationService myDBAuthenticationService;

    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Các User trong Database
        auth.userDetailsService(myDBAuthenticationService).passwordEncoder(encoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/", "/home", "/login", "/logout", "/register").permitAll();

        http.authorizeRequests()
                .antMatchers("/userInfo", "/inGroup/{id}", "/groupInfo/netflix/{id}", "/groups/netflix", "/userGroups")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");


        http.authorizeRequests()
                .antMatchers("/admin/inGroup/{id}", "/admin/groups/netflix",
                        "/admin/groups/netflix/insufficientBalance", "/admin/groups/netflix/sufficientBalance",
                        "/admin/kickUserForm", "/admin/addBalance", "/admin/changeAccountPassword",
                        "/admin/receiveChangeAccountPasswordRequest")
                .access("hasRole('ROLE_ADMIN')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin()//

                .loginProcessingUrl("/j_spring_security_check") //
                .loginPage("/login")//
                .defaultSuccessUrl("/home")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // rememberme
                .and().rememberMe().key("uniqueAndSecret")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID");

    }
}
