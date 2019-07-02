package br.com.furb.routes;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.furb.domain.Profile;
import br.com.furb.service.ProfileService;

@RestController
@RequestMapping(value="/profiles")
public class ProfileRoute {
	
	@Autowired
	private ProfileService profileService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> save(@RequestBody Profile profile) {
		return ResponseEntity.ok(profileService.save(profile));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(profileService.findAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(profileService.findById(id));
	}
	
	@PostMapping(path="/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		profileService.deleteById(id);
		return ResponseEntity.ok(String.format("Perfil de id %d removido com sucesso!", id));
	}
}
