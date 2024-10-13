package ir.snappay.walletservice.config;

import ir.snappay.walletservice.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfig {
    private final UserService userService;

    public ApplicationConfig(UserService userService) {
        this.userService = userService;
    }


    @Bean
    UserDetailsService userDetailsService(){
        return username ->
            userService.findByMobileNumber(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authprovicer= new DaoAuthenticationProvider();
        authprovicer.setUserDetailsService(userDetailsService());
        authprovicer.setPasswordEncoder(passwordEncoder());
        return authprovicer;
    }
}
