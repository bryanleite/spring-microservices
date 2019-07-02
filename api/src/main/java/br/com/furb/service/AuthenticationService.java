package br.com.furb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furb.domain.User;
import br.com.furb.repository.UserRepository;
import br.com.furb.security.authentication.UserSV;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Busca o usuário através do username para autenticação do Spring Security
	 * @param username
	 * @return
	 */
	public UserSV loadUserByUsername(String username) {
		UserSV userSv = null;
		try {
			Optional<User> user = userRepository.loadUserByLogin(username);
			if(user.isPresent()) {
				userSv = new UserSV(user.get().getLogin(), user.get().getPassword());
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreram falhas ao realizar a autenticação. Causa: " + e.getMessage());
		}
		
		return userSv;
	}

}
