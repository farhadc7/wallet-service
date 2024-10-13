package ir.snappay.walletservice.config;

import ir.snappay.walletservice.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization","Content-type"));

        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return source;
    }
}
