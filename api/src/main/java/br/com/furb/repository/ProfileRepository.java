package br.com.furb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.furb.domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
