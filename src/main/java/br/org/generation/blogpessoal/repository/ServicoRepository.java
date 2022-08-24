package br.org.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Servico;


@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
	
	public List <Servico> findAllByTipoContainingIgnoreCase(String tipo);


}
