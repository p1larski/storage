package storage.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import storage.services.EmployeeService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final EmployeeService EmployeeService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.antMatchers("/login").permitAll();
                    auth.antMatchers("/employee/new").permitAll();
                    auth.antMatchers("/admin").hasAuthority("ADMIN");
                    auth.antMatchers("/*/*","/*").hasAuthority("USER");
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(EmployeeService);
        return provider;
    }
}