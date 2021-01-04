package com.pacote.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pacote.domain.Cliente;
import com.pacote.domain.Pedido;
import com.pacote.domain.PedidoProduto;
import com.pacote.domain.Produto;
import com.pacote.domain.enums.StatusCliente;
import com.pacote.domain.enums.StatusEntrega;
import com.pacote.repositories.ClienteRepository;
import com.pacote.repositories.PedidoProdutoRepository;
import com.pacote.repositories.PedidoRepository;
import com.pacote.repositories.ProdutoRepository;



@Service
public class DBService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;
	
	
	public void instantiateTesteDatabase() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		
		Cliente cl1 = new Cliente(0, "Fernando", sdf.parse("14/07/2017 07:10:20"), StatusCliente.ATIVO);
		Cliente cl2 = new Cliente(0, "Teste", sdf.parse("17/05/2019 07:10:20"), StatusCliente.ATIVO);
		Cliente cl3 = new Cliente(0, "Cliente 1", sdf.parse("10/12/2018 07:10:20"), StatusCliente.INATIVO);
		
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2, cl3));
		
		Produto p1 = new Produto(0, 23.00, true, "Produto1");
		Produto p2 = new Produto(0, 21.70, true, "Produto2");
		Produto p3 = new Produto(0, 2.50, true, "Produto3");
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Pedido pd1 = new Pedido(0,25.6, sdf.parse("10/0/2017 07:10:20"), StatusEntrega.ENTREGUE, cl1);
		Pedido pd2 = new Pedido(0,7.1, sdf.parse("06/01/2019 07:10:20"), StatusEntrega.ENTREGUE, cl2);
		Pedido pd3 = new Pedido(0,11.15, sdf.parse("10/10/2017 07:10:20"), StatusEntrega.ENTREGUE, cl1);
		
		pedidoRepository.saveAll(Arrays.asList(pd1, pd2, pd3));
		
		PedidoProduto pp1 = new PedidoProduto(0, p1, 3, pd1);
		PedidoProduto pp2 = new PedidoProduto(0, p2, 1, pd1);
		PedidoProduto pp3 = new PedidoProduto(0, p3, 2, pd1);
		
		pedidoProdutoRepository.saveAll(Arrays.asList(pp1, pp2, pp3));
		
		
		/*Vendedor v1 = new Vendedor(0, "v@1", "","", StatusVendedor.ATIVO, null, "Fernando", "12231054744", pe.encode("111111"), "https://porumclique.s3-sa-east-1.amazonaws.com/vendedor1.jpg", "22659855458");
		Vendedor v2 = new Vendedor(0, "v@2", "","", StatusVendedor.ATIVO, null, "Fernando1", "12231054744", pe.encode("111111"), "https://porumclique.s3-sa-east-1.amazonaws.com/vendedor2.jpg", "22659855458");
		Vendedor v3 = new Vendedor(0, "v@3", "","", StatusVendedor.ATIVO, null, "Fernando2", "12231054744", pe.encode("111111"), "https://porumclique.s3-sa-east-1.amazonaws.com/vendedor3.jpg", "22659855458");
		
		
		
		Categoria c1 = new Categoria(0, "Água",  "https://porumclique.s3-sa-east-1.amazonaws.com/agua.jpg");
		Categoria c2 = new Categoria(0, "Gás", "https://porumclique.s3-sa-east-1.amazonaws.com/gas.png");
		
		ProdutoGeral pg1 = new ProdutoGeral(0, "Ipanema", "https://porumclique.s3-sa-east-1.amazonaws.com/agua.jpg", c1);
		ProdutoGeral pg2 = new ProdutoGeral(0, "Bellaagua", "https://porumclique.s3-sa-east-1.amazonaws.com/agua.jpg", c1);
		ProdutoGeral pg3 = new ProdutoGeral(0, "Eco", "https://porumclique.s3-sa-east-1.amazonaws.com/agua.jpg", c1);
		ProdutoGeral pg4 = new ProdutoGeral(0, "Oasis","https://porumclique.s3-sa-east-1.amazonaws.com/agua.jpg",  c1);
		ProdutoGeral pg5 = new ProdutoGeral(0, "Cascata Azul","https://porumclique.s3-sa-east-1.amazonaws.com/agua.jpg",  c1);
		
		ProdutoGeral pg6 = new ProdutoGeral(0, "Supergasbras","https://porumclique.s3-sa-east-1.amazonaws.com/gas.png",  c2);
		ProdutoGeral pg7 = new ProdutoGeral(0, "Nacional gás","https://porumclique.s3-sa-east-1.amazonaws.com/gas.png",  c2);
		ProdutoGeral pg8 = new ProdutoGeral(0, "Ultragaz","https://porumclique.s3-sa-east-1.amazonaws.com/gas.png",  c2);
		ProdutoGeral pg9 = new ProdutoGeral(0, "Liquigás","https://porumclique.s3-sa-east-1.amazonaws.com/gas.png",  c2);
		
		Cliente cl1 = new Cliente(0, "c@1", "22152554", "José", "1111111111", "111111", "");
		
		Dinheiro d1 = new Dinheiro(0, 25.00, 100.00);	
		
//		
		Avaliacao a1 = new Avaliacao(0, 3, "teste");
		Pedido p1 = new Pedido(0, 23.00,StatusPedido.ENTREGUE, d1, cl1, v1, sdf.parse("10/10/2017 07:10:20"), a1);
		
		
		
		Pedido p2 = new Pedido(0, 23.00,StatusPedido.ENTREGUE, d1, cl1, v1, sdf.parse("10/10/2017 07:10:20"), a1);
		Pedido p3 = new Pedido(0, 23.00,StatusPedido.ENTREGUE, d1, cl1, v2, sdf.parse("10/10/2017 07:10:20"), a1);
		
				
		
		vendedorRepository.saveAll(Arrays.asList(v1, v2, v3));
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoGeralRepository.saveAll(Arrays.asList(pg1,pg2,pg3,pg4,pg5,pg6,pg7,pg8,pg9));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		dinheiroRepository.saveAll(Arrays.asList(d1));
		avaliacaoRepository.saveAll(Arrays.asList(a1));
		pedidoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		*/
		
		
		//--------------------------- inspeção ----------------------------------
	
	}
}
