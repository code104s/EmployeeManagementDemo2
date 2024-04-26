package com.offlink.quanlynhanvien.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurity {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails twan = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN")
                .build();

        UserDetails nv = User.withDefaultPasswordEncoder()
                .username("employee")
                .password("123")
                .roles("EMPLOYEE")
                .build();
        // post man sẽ gửi request với username và password

        // nếu username và password trùng với 1 trong 2 user trên thì sẽ được phép truy cập

        return new InMemoryUserDetailsManager(twan,nv);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/accessDenied")
                )
                .authorizeHttpRequests(configuer ->
                        configuer
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                );

        return http.build();
    }
}
