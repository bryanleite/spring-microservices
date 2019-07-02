package br.com.furb.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.furb.service.AuthenticationService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * Implementação específica para busca de usuários para autenticação
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authenticationService.loadUserByUsername(username);
	}

}
