package com.happytails.springserver.config;

import com.happytails.springserver.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
// класс конфига защиты. если хочешь её отключить, закомменть целиком этот класс, MyUserDetailsService,
// пару строчек в EmployeeService и зависимость в build.gradle (вроде ничего не забыл)
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    // коротко о методах. этот возвращает цепочку фильтров, устанавливающую доступ к маппингам по ролям
    // и прочие настройки http
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry
                            .antMatchers("/api/v1.0/registration/**")
                            .permitAll();
                    registry
                            .antMatchers("/api/v1.0/employee/**")
                                    .hasRole("CLIENT");
                    registry
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic()
                .and()
                .sessionManagement()
                .disable()
//                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//                .and()
                .build();
    }

    @Bean
    // этот возвращает UserDetailsService для доступа к пользователям в БД и их сравнения
    public UserDetailsService userDetailsService(){
        return myUserDetailsService;
    }

    @Bean
    // Предоставляет аутенификацию через заданный UserDetailsService и PasswordEncoder
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    // возвращает энкодер пароля
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
