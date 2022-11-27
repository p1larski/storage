package storage.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import storage.services.EmployeeService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final EmployeeService EmployeeService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.antMatchers("/employee/add").permitAll();
                    auth.antMatchers("/article/*").hasAuthority("ADMIN");
                    auth.antMatchers("/product/*").hasAnyAuthority("USER", "ADMIN");

                })
                .httpBasic(withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/product/all", true)
                )
                 .exceptionHandling()
                 .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/product/all"))
                 .and()
                .logout();
         return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(EmployeeService);
        return provider;
    }
}