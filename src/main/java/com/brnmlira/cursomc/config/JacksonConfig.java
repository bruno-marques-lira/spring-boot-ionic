package com.brnmlira.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.brnmlira.cursomc.domain.PagamentoComBoleto;
import com.brnmlira.cursomc.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig{
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-istance-of-interfaceclass-without-hiting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder(){
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
			public void configure(ObjectMapper objectMapper){
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}