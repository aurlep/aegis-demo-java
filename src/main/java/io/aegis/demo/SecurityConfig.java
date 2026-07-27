package io.aegis.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http
            .csrf(c -> c.disable())
            .authorizeHttpRequests(a -> a
                .requestMatchers("/", "/login", "/healthz").permitAll()
                .anyRequest().authenticated())
            .formLogin(f -> f.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll())
            .logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());
        return http.build();
    }

    @Bean
    InMemoryUserDetailsManager users() {
        UserDetails demo = User.withUsername("demo@example.com")
            .password("demo1234")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(demo);
    }

    @Bean
    @SuppressWarnings("deprecation")
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
