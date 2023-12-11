package co.com.cesde.arkham.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(withDefaults())
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(publicEndPoints()).permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(l ->
                        l.addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(
                                        (request, response, authentication) ->
                                                SecurityContextHolder.clearContext()
                                ).permitAll()
                ).cors(httpSecurityCors -> {
                    httpSecurityCors.configurationSource(corsConfigurationSource());
                });

        return httpSecurity.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Configura las opciones CORS según tus necesidades
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        // Añade otras configuraciones CORS necesarias

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private RequestMatcher publicEndPoints() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/auth/register/**"),
                new AntPathRequestMatcher("/auth/login/**"),
                new AntPathRequestMatcher("/token/**"),
                new AntPathRequestMatcher("/appointment/export/**"),
                new AntPathRequestMatcher("/property/all/**"),
                new AntPathRequestMatcher("/property/location/**"),
                new AntPathRequestMatcher("/property/offer/**"),
                new AntPathRequestMatcher("/appointment/save/**"),
                new AntPathRequestMatcher("/appointment/**"),
                new AntPathRequestMatcher("/appointment/update/**"),
                new AntPathRequestMatcher("/appointment/date/**"),
                new AntPathRequestMatcher("/appointment/dateusername/**"),
                new AntPathRequestMatcher("/appointment/client/**"),
                new AntPathRequestMatcher("/appointment/delete/**"),
                new AntPathRequestMatcher("/client/**"),
                new AntPathRequestMatcher("/client/save/**"),
                new AntPathRequestMatcher("/client/update/**"),
                new AntPathRequestMatcher("/client/all/**"),
                new AntPathRequestMatcher("/auth/user/all/**"),
                new AntPathRequestMatcher("/auth/user/email/**")
        );
    }
}
