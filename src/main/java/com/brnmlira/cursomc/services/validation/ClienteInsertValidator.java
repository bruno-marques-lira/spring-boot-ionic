package com.brnmlira.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brnmlira.cursomc.domain.Cliente;
import com.brnmlira.cursomc.domain.enums.TipoCliente;
import com.brnmlira.cursomc.dto.ClienteNewDTO;
import com.brnmlira.cursomc.repositories.ClienteRepository;
import com.brnmlira.cursomc.resources.exceptions.FieldMessage;
import com.brnmlira.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann){
	}

	@Override
	public boolean isValid(ClienteNewDTO objNewDto, ConstraintValidatorContext context){
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objNewDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if(objNewDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objNewDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objNewDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}
		
		// Inclua os testes aqui, insetindo erros na lista
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}