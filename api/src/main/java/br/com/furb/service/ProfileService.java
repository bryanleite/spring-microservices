package br.com.furb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.furb.domain.Profile;
import br.com.furb.repository.ProfileRepository;

@Service
public class ProfileService extends AbstractService<Profile>{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	protected JpaRepository<Profile, Long> getRepository() {
		return profileRepository;
	}

}
