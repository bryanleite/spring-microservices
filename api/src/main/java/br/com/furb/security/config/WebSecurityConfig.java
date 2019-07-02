package br.com.furb.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.furb.security.authentication.JWTAuthenticationFilter;
import br.com.furb.security.authentication.JWTUtil;
import br.com.furb.security.authorization.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * Rotas públicas que não necessitam de token de autorização
	 */
	private static final String[] PUBLIC_MATCHERS = {
		"/eventsConsult/**",
		"/h2/**",
		"/favicon.ico",
		"/app/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		
		// Aplica a configuração de Cors ( Permitir chamada de qualquer origem para a API)
		http.cors().and().csrf().disable();
		
		// Obriga que qualquer requisição esteja autenticada(a de login não exige por padrão)
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		
		// Inclui o filtro de autenticação, responsável por incluir o Token na resposta e tratar a falha de autenticação.
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		// Inclui o filtro de autorização, responsável por validar o Token na chamada dos EndPoints.
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
}
