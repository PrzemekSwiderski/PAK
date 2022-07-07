package fun.epak.pak.config;

import fun.epak.pak.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebServiceConfig{

    @Bean
    protected static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
                    try {
                        auth
                                .antMatchers("/auth", "/resources/**", "/webjars/**", "/register").permitAll()
                                .antMatchers("/**").hasAuthority("USER")
                                .and()
                                .csrf().disable()
                                .headers().frameOptions().disable()
                                .and()
                                .formLogin()
                                .loginPage("/auth")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/auth")
                                .failureForwardUrl("/auth?error")
                                .defaultSuccessUrl("/")
                                .and()
                                .logout().deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/")
                                .and()
                                .rememberMe().tokenValiditySeconds(7 * 24 * 60 * 60)
                                .key("AbcqwerasdgfrdefghiJk5yz");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        ).httpBasic(withDefaults());
        return http.build();
    }
}
