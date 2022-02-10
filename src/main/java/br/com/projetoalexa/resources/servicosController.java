package br.com.projetoalexa.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class servicosController {
	
	@GetMapping()
	public ResponseEntity<?> get()  {
		return ResponseEntity.badRequest().body("Sistema em construção, servidor no ar!");
	}
	
	@PostMapping()
	public ResponseEntity<?> post()  {
		return ResponseEntity.badRequest().body("Sistema em construção, servidor no ar!");
	}
	
	
	@PutMapping()
	public ResponseEntity<?> put()  {
		return ResponseEntity.badRequest().body("Sistema em construção, servidor no ar!");
	}
	
	
	@DeleteMapping()
	public ResponseEntity<?> delete()  {
		return ResponseEntity.badRequest().body("Sistema em construção, servidor no ar!");
	}
	

}