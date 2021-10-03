package com.rizalpurnama.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // query untuk mendapatkan password dari username yang diinputkan
    private static final String SQL_LOGIN
            = "select u.username, up.password, u.active " +
            "from s_users_passwords up " +
            "inner join s_users u on u.id = up.id_user " +
            "where u.username = ?";

    // query untuk mendapatkan permission dari username yang diinputkan
    private static final String SQL_PERMISSION =
            "select u.username, p.value as authority " +
                    "from s_users u " +
                    "inner join s_roles r on r.id = u.id_role " +
                    "inner join s_roles_permissions rp on rp.id_role = r.id " +
                    "inner join s_permissions p on rp.id_permission = p.id " +
                    "where u.username = ?";

    // karena mengakses ke database, maka butuh datasource
    @Autowired
    private DataSource dataSource;

    // method untuk mendapatkan algoritma yang digunakan untuk hashing password
    // bisa pakai BCrypt, SCtypt, dll
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //

    //method untuk mendapatkan objek yang berisi username, password, beserta permission dari database.
    //menggunakan instance dari JdbcUserDetailManager
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(this.dataSource);
        manager.setUsersByUsernameQuery(SQL_LOGIN);
        manager.setAuthoritiesByUsernameQuery(SQL_PERMISSION);
        return manager;
    }
//
//    //Mengkonfigurasi userdetail servis dan encoder yang sudah diatas,
//    //sehingga spring dapat mengerjakan pengamanan sesuai dengan yang kita inginkan
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean @Order(1)
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .authorizeRequests(authorizeRequests -> {
                            authorizeRequests.anyRequest().authenticated();
                        }
                ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    @Bean @Order(2)
    SecurityFilterChain htmlSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/password/**").permitAll()
                                .antMatchers("/css/**", "/img/**", "/js/**").permitAll()
                                .antMatchers("/register/**").permitAll()
                                .anyRequest().authenticated()
                )
//                .formLogin(Customizer.withDefaults());
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll();
        return http.build();
    }

    // Membuat multiple security configuration
    //
    //

    /**
     * Membuat security configuration untuk API
     *  -> tidak perlu mengaktifkan csrf filter, karena ini tidak diakses lewat browser.
     *  -> letakkan url di antMatcher().
     */
//    @Configuration @Order(1)
//    static class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.antMatcher("/api/**")
//                    .authorizeRequests()
//                    .anyRequest()
//                    .permitAll()
//                    .and().csrf().disable();
//        }
//    }
//
//    // Membuat security configuration untuk url yang diakses dengan browser
//    // harus menentukan konfigurasi form login dan logout.
//    @Configuration @Order(2)
//    static class HtmlSecurityConfig extends WebSecurityConfigurerAdapter{
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .anyRequest()
//                    .fullyAuthenticated()
//                    .and().formLogin(Customizer.withDefaults())
//                    .logout(Customizer.withDefaults());
//        }
//    }


}
