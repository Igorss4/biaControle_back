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

import br.org.generation.blogpessoal.model.Servico;
import br.org.generation.blogpessoal.repository.ServicoRepository;





@RestController
@RequestMapping("/servicos") 
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicoController {
	
	@Autowired 
	private ServicoRepository repository;
	

	@GetMapping
	public ResponseEntity<List<Servico>> getAll (){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> getById(@PathVariable Long id) {
		return repository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
		
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Servico>> getByTipo(@PathVariable String tipo){
		return ResponseEntity.ok(repository.findAllByTipoContainingIgnoreCase(tipo));
	}


	@PostMapping
	public ResponseEntity<Servico> postServico (@Valid @RequestBody Servico servico){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(servico));
	}
	
	
	@PutMapping
	public ResponseEntity<Servico> putServico (@Valid @RequestBody Servico servico){
		return repository.findById(servico.getId())
			.map(resposta -> ResponseEntity.ok().body(repository.save(servico)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}