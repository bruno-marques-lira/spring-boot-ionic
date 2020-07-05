package com.brnmlira.cursomc.services;

import java.text.ParseException;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brnmlira.cursomc.domain.Categoria;
import com.brnmlira.cursomc.domain.Cidade;
import com.brnmlira.cursomc.domain.Cliente;
import com.brnmlira.cursomc.domain.Endereco;
import com.brnmlira.cursomc.domain.Estado;
import com.brnmlira.cursomc.domain.ItemPedido;
import com.brnmlira.cursomc.domain.Pagamento;
import com.brnmlira.cursomc.domain.PagamentoComBoleto;
import com.brnmlira.cursomc.domain.PagamentoComCartao;
import com.brnmlira.cursomc.domain.Pedido;
import com.brnmlira.cursomc.domain.Produto;
import com.brnmlira.cursomc.domain.enums.EstadoPagamento;
import com.brnmlira.cursomc.domain.enums.TipoCliente;
import com.brnmlira.cursomc.repositories.CategoriaRepository;
import com.brnmlira.cursomc.repositories.CidadeRepository;
import com.brnmlira.cursomc.repositories.ClienteRepository;
import com.brnmlira.cursomc.repositories.EnderecoRepository;
import com.brnmlira.cursomc.repositories.EstadoRepository;
import com.brnmlira.cursomc.repositories.ItemPedidoRepository;
import com.brnmlira.cursomc.repositories.PagamentoRepository;
import com.brnmlira.cursomc.repositories.PedidoRepository;
import com.brnmlira.cursomc.repositories.ProdutoRepository;
import com.ibm.icu.text.SimpleDateFormat;

@Transactional
@Service
public class DBService {
	@Autowired(required=true)
	private CategoriaRepository categoriaRepository;
	@Autowired(required=true)
	private ProdutoRepository produtoRepository;
	@Autowired(required=true)
	private EstadoRepository estadoRepository;
	@Autowired(required=true)
	private CidadeRepository cidadeRepository;
	@Autowired(required=true)
	private ClienteRepository clienteRepository;
	@Autowired(required=true)
	private EnderecoRepository enderecoRepository;
	@Autowired(required=true)
	private PedidoRepository pedidoRepository;
	@Autowired(required=true)
	private PagamentoRepository pagamentoRepository;
	@Autowired(required=true)
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException{
		// Instanciando Categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa & banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardingem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Alimentação");
		Categoria cat9 = new Categoria(null, "Pets");
		Categoria cat10 = new Categoria(null, "Moda");

		// Instanciando Produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		// Associando Produtos à Categorias
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		// Associando Categorias à Produtos
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		// Instanciando repositório de categorias para armazenar no banco de dados as
		// Categorias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));

		// Instanciando repositório de produtos para armazenar no banco de dados os
		// Produtos
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		// Instanciando Estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		// Instanciando Cidades
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Jundiaí", est2);

		// Associando Cidades à Estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		// Instanciando repositório de estados para armazenar no banco de dados os
		// Estados
		estadoRepository.saveAll(Arrays.asList(est1, est2));

		// Instanciando repositório de cidades para armazenar no banco de dados as
		// Cidades
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		// Instanciando Clientes
		Cliente cli1 = new Cliente(null, "Maria Silva", "brunomarqueslira@outlook.com", "50032665024", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("36283956", "993129762"));

		Cliente cli2 = new Cliente(null, "Bruno Lira", "blira@gmail.com", "75365423000104", TipoCliente.PESSOAJURIDICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("969192721"));

		Cliente cli3 = new Cliente(null, "Patricia Bellemo", "patriciabellemo@gmail.com", "08385323066", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli3.getTelefones().addAll(Arrays.asList("998719177"));

		// Instanciando Endereços
		Endereco end1 = new Endereco(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "39777012", cli1, c2);
		Endereco end3 = new Endereco(null, "Rua Kitizo Utiyama", "308", "", "Vila Brasilina", "04162050", cli2, c2);
		Endereco end4 = new Endereco(null, "Rua Irmã Alida", "20", "", "Vila Progresso", "13202151", cli3, c3);

		// Associando Endereços a Clientes
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));
		cli3.getEnderecos().addAll(Arrays.asList(end4));

		// Instanciando respositório de Clientes para armazenar no banco de dados os
		// Clientes
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));

		// Instanciando respositório de Endereços para armazenar no banco de dados os
		// Endereços dos Clientes
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));

		// Instanciando objeto de formatação de data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		// Instanciando Pedidos
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		Pedido ped3 = new Pedido(null, sdf.parse("19/06/2017 15:02"), cli2, end3);
		Pedido ped4 = new Pedido(null, sdf.parse("03/01/2017 16:20"), cli3, end4);

		// Instanciando Pagamentos
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);
		Pagamento pagto3 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped3, sdf.parse("20/07/2017 09:11"),
				sdf.parse("20/07/2017 09:11"));
		ped3.setPagamento(pagto3);
		Pagamento pagto4 = new PagamentoComCartao(null, EstadoPagamento.CANCELADO, ped4, 4);
		ped4.setPagamento(pagto4);

		// Atribuindo pedidos aos Clientes
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		cli2.getPedidos().addAll(Arrays.asList(ped3));
		cli3.getPedidos().addAll(Arrays.asList(ped4));

		// Instanciando repositório de Pedidos para armazenar no banco de dados os
		// Pedidos dos Clientes
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4));

		// Instanciando repositório de Pagamentos para armazenar no banco de dados os
		// Pagamentos dos Pedidos dos Clientes
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2, pagto3, pagto4));

		// Instanciando os Itens de Pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		ItemPedido ip4 = new ItemPedido(ped3, p1, 100.00, 1, 2000.00);
		ItemPedido ip5 = new ItemPedido(ped4, p2, 0.00, 1, 800.00);

		// Associando Item de pedido aos pedidos
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		ped3.getItens().addAll(Arrays.asList(ip4));
		ped4.getItens().addAll(Arrays.asList(ip5));

		// Associando Itens de Pedido aos Produtor
		p1.getItens().addAll(Arrays.asList(ip1, ip4));
		p2.getItens().addAll(Arrays.asList(ip3, ip5));
		p3.getItens().addAll(Arrays.asList(ip2));

		// Instanciando repositório de Itens de Pedido para armazenar os Itens de Pedido
		// dos Pedidos dos Clientes
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5));
	}
}
