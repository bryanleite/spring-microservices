package br.com.furb.security.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe responsável por interceptar o envio da autenticação para o Response.
 * Aqui é capturado o usuário, gerado um Token com expiração e enviado no Header 'Authorization'.
 * Também é capturado exceção de falha de autenticação, tratado e enviado no Body do response.
 * 
 * Utilizado o padrão JWT na inclusão do Token
 * 
 * @author Bryan.Leite
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private JWTUtil jwtUtil;
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}
	
	/**
	 * Realiza a tentativa da autenticação.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String username = request.getHeader("username");
		String password = request.getHeader("password");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        
        Authentication auth = authenticationManager.authenticate(authToken);
        return auth;
	}


	/**
	 * Realiza o tratamento da resposta de sucesso, adicionando um Token válido com um determinado tempo de expiração.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String username = ((UserSV) authResult.getPrincipal()).getUsername();
		if(StringUtils.isNotBlank(username)) {
			String token = jwtUtil.generateToken(username);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(jsonSuccess(token, username));
		}
	}
	
    private String jsonSuccess(String token, String username) {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
            + "\"status\": " + HttpServletResponse.SC_OK + ", "
            + "\"token\": \"" + token + "\", "
            + "\"username\": \"" + username + "\", "
            + "\"message\": \"Autenticação realizada com sucesso\", "
            + "\"path\": \"/login\"}";
    }

	/**
	 * Classe privada utilizada para tratar erro de falha de autenticação, pois no Spring Boot 2, quando é lançado erro de autenticação ele envia o status 403 (Forbidden), 
	 * porém, o correto é 401 (Unauthorized).
	 * Foi também alterado para enviar no body alguns detalhes da falha de autenticação.
	 * @author Bryan.Leite
	 *
	 */
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
	    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
	        response.getWriter().append(jsonError("Usuário/Senha inválido", HttpServletResponse.SC_UNAUTHORIZED));
	    }

	    private String jsonError(String msg, Integer status) {
	        long date = new Date().getTime();
	        return "{\"timestamp\": " + date + ", "
	            + "\"status\": " + status + ", "
	            + "\"error\": \"Não autorizado\", "
	            + "\"message\": \"" + msg + "\", "
	            + "\"path\": \"/login\"}";
	    }
	}
}
