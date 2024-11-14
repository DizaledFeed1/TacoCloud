package com.example.tacocloud.security;

import com.example.tacocloud.User;
import com.example.tacocloud.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/design", "/orders").hasRole("USER") // Защищенные маршруты
                        .requestMatchers("/**", "/login", "/register", "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/orders").hasAuthority("USER")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
//                .oauth2Login(oauth2 -> oauth2
////                        .loginPage("/login")
//                        .defaultSuccessUrl("/design")
//                        .failureUrl("/login?error=true")
//                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/design")
                        .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
//                .oauth2Client(withDefaults()); // Настройка OAuth2-клиента
        return http.build();
    }
}



