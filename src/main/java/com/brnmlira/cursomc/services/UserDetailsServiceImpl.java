package com.brnmlira.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.brnmlira.cursomc.domain.Cliente;
import com.brnmlira.cursomc.repositories.ClienteRepository;
import com.brnmlira.cursomc.security.SSUser;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new SSUser(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
