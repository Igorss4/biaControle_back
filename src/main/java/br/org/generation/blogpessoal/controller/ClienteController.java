package br.org.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Cliente;
import br.org.generation.blogpessoal.repository.ClienteRepository;



@RestController
@RequestMapping("/clientes") 
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired 
	private ClienteRepository repository;
	

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll (){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) {
		return repository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
		
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}


	@PostMapping
	public ResponseEntity<Cliente> postCliente (@Valid @RequestBody Cliente cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
	}
	
	
	@PutMapping
	public ResponseEntity<Cliente> putCliente (@Valid @RequestBody Cliente cliente){
		return repository.findById(cliente.getId())
			.map(resposta -> ResponseEntity.ok().body(repository.save(cliente)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
