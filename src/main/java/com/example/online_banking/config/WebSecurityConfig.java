package com.example.online_banking.config;

import com.example.online_banking.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    AppConfig appConfig = new AppConfig();

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/data/**",
            "/sound/**",
            "/img/**",
            "/",
            "/login",
            "/logout",
            "/error",
            "/index2",
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(appConfig.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/user/**").hasAnyRole("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/j_spring_security_check") // Submit URL
                .defaultSuccessUrl("/home")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
