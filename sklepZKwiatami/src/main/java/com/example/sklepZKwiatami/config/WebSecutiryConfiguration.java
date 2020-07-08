package com.example.sklepZKwiatami.config;

import com.example.sklepZKwiatami.security.ApplicationUserRole;
import com.example.sklepZKwiatami.security.JwtAuthenticationEntryPoint;
import com.example.sklepZKwiatami.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecutiryConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter filter;

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/user/login" ,"/user/register", "/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/**").hasRole(ApplicationUserRole.CUSTOMER.name())
                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.SELLER.name())
                .antMatchers(HttpMethod.GET, "/flower/**").hasRole(ApplicationUserRole.CUSTOMER.name())
                .antMatchers(HttpMethod.POST, "/flower").hasRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/flower/**").hasRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/flower/**").hasRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/history/**").hasRole(ApplicationUserRole.CUSTOMER.name())
                .antMatchers(HttpMethod.DELETE, "/history/**").hasRole(ApplicationUserRole.ADMIN.name())
                .antMatchers("/shop").hasRole(ApplicationUserRole.SELLER.name())
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    }
}
