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

import br.org.generation.blogpessoal.model.Material;
import br.org.generation.blogpessoal.repository.MaterialRepository;


@RestController
@RequestMapping("/materiais") 
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MaterialController {
	
	@Autowired 
	private MaterialRepository repository;
	

	@GetMapping
	public ResponseEntity<List<Material>> getAll (){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Material> getById(@PathVariable Long id) {
		return repository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
		
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Material>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}


	@PostMapping
	public ResponseEntity<Material> postMaterial (@Valid @RequestBody Material material){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(material));
	}
	
	
	@PutMapping
	public ResponseEntity<Material> putMaterial (@Valid @RequestBody Material material){
		return repository.findById(material.getId())
			.map(resposta -> ResponseEntity.ok().body(repository.save(material)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
