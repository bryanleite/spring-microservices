package br.com.furb.security.authorization;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.furb.security.authentication.JWTUtil;

/**
 * Classe responsável por realizar a autorização de acesso aos EndPoints da API: Intercepta e analisa o Token JWT 
 * @author Bryan.Leite
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	/**
	 * Recupera do Header o Token e realiza validação
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		
		UsernamePasswordAuthenticationToken auth = null;
		if(StringUtils.isNotBlank(header) && StringUtils.startsWith(header, "Bearer ")) {
			auth = getAuthentication(header.substring(7));
		}
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

	/**
	 * Verifica a validade do token (integridade algorítmica e data de expiração) e verifica se o usuário presente no mesmo existe na aplicação. 
	 * @param token
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		UsernamePasswordAuthenticationToken auth = null;
		if(jwtUtil.validToken(token)) {
			String username = jwtUtil.getUsername(token);
			try {
				UserDetails user = userDetailsService.loadUserByUsername(username);
				auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			} catch (UsernameNotFoundException e) {
				//Não retorna exceção pois, caso o usuário seja inválido apenas irá retornar não autorizado.
			}
		}
		
		return auth;
	}

}
