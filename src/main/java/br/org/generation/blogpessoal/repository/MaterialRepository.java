package br.org.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Material;


@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{
	
	public List <Material> findAllByNomeContainingIgnoreCase(String nome);
}
