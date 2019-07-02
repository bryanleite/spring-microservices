package br.com.furb.routes;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.furb.domain.User;
import br.com.furb.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserRoute {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> save(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}
	
	@PutMapping(path="/:id")
	public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	} 
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PostMapping(path="/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.ok(String.format("Usuário de id %d removido com sucesso!", id));
	}
}
