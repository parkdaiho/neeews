package me.parkdaiho.project.config;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.oauth2.OAuth2AuthorizationRequestRepositoryBasedOnCookie;
import me.parkdaiho.project.config.properties.CookieProperties;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.service.user.OAuth2UserCustomService;
import me.parkdaiho.project.service.user.TokenService;
import me.parkdaiho.project.service.user.UserDetailCustomService;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserService userService;
    private final TokenService tokenService;

    private final JwtProperties jwtProperties;
    private final CookieProperties cookieProperties;

    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests()
                .requestMatchers("/my-page", "/my-page/**").authenticated()
                .requestMatchers("/api/membership").hasAnyRole(Role.ADMIN.getAuthority(), Role.MANAGER.getAuthority())
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")

                .usernameParameter("username")
                .passwordParameter("password")

                .successHandler(authenticationCustomSuccessHandler())
                .failureHandler(authenticationCustomFailureHandler());

        http.oauth2Login()
                .loginPage("/login")

                .authorizationEndpoint()
                .baseUri("/oauth2/authorization/")
                .authorizationRequestRepository(oAuth2AuthorizationRequestRepositoryBasedOnCookie())
                .and()

                .userInfoEndpoint()
                .userService(oAuth2UserCustomService())
                .and()

                .successHandler(authenticationCustomSuccessHandler())
                .failureHandler(authenticationCustomFailureHandler());

        http.logout()
                .logoutUrl("/logout")
                .addLogoutHandler(logoutCustomHandler())
                .logoutSuccessUrl("/?logout");

        return http.build();
    }

    @Bean
    public OAuth2UserCustomService oAuth2UserCustomService() {
        return new OAuth2UserCustomService(userService);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenService);
    }

    @Bean
    public OAuth2AuthorizationRequestRepositoryBasedOnCookie oAuth2AuthorizationRequestRepositoryBasedOnCookie() {
        return new OAuth2AuthorizationRequestRepositoryBasedOnCookie(jwtProperties);
    }

    @Bean
    public AuthenticationCustomSuccessHandler authenticationCustomSuccessHandler() {
        return new AuthenticationCustomSuccessHandler(oAuth2AuthorizationRequestRepositoryBasedOnCookie(),
                tokenService, cookieProperties);
    }

    @Bean
    public AuthenticationCustomFailureHandler authenticationCustomFailureHandler() {
        return new AuthenticationCustomFailureHandler(userService, authenticationCustomSuccessHandler());
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailCustomService userDetailCustomService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailCustomService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public LogoutCustomHandler logoutCustomHandler() {
        return new LogoutCustomHandler(jwtProperties, tokenService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}