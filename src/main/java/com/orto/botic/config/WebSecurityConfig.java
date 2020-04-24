package com.orto.botic.config;

import com.orto.botic.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String loginPage = "/login";
        String logoutPage = "/logout";

//        http.
//                authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers(loginPage).permitAll()
//                .antMatchers("/index/**", "/resources/**").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and().csrf().disable()
//                .formLogin()
//                .loginPage(loginPage)
////                .successHandler(customAuthenticationSuccessHandler)
//                .failureUrl("/login?error=true")
//                .defaultSuccessUrl("/index")
//                .usernameParameter("user_name")
//                .passwordParameter("password")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();

//        .authorizeRequests()
//                .antMatchers( "/**").permitAll()
//                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .successHandler(customAuthenticationSuccessHandler)
//                .failureUrl("/login?error=true")
////                .defaultSuccessUrl("/")
//                .usernameParameter("user_name")
//                .passwordParameter("password")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and().csrf().disable();


//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
//                .and().formLogin()
//                .loginPage(loginPage)
//                .defaultSuccessUrl("/")
//                .and().logout().logoutSuccessUrl("/").permitAll()
//                .and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("user_name")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .csrf()
                .disable();
    }


        @Override
        public void configure (WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
        }
    }
