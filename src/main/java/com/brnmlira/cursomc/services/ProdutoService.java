package com.brnmlira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brnmlira.cursomc.domain.Categoria;
import com.brnmlira.cursomc.domain.Produto;
import com.brnmlira.cursomc.dto.ProdutoDTO;
import com.brnmlira.cursomc.repositories.ProdutoRepository;
import com.brnmlira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository ProdutoRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = ProdutoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Produto fromDto(ProdutoDTO objDto) {
		return new Produto(objDto.getId(), objDto.getNome(), objDto.getPreco());
	}
}
