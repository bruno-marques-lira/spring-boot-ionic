package com.brnmlira.cursomc.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.brnmlira.cursomc.domain.PagamentoComBoleto;
import com.ibm.icu.util.Calendar;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
