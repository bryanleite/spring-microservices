package br.com.furb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.furb.domain.User;
import br.com.furb.repository.UserRepository;

@Service
public class UserService extends AbstractService<User>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected JpaRepository<User, Long> getRepository() {
		return userRepository;
	}

}
