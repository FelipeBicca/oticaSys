package com.felipebicca.otica;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipebicca.otica.domain.Cidade;
import com.felipebicca.otica.domain.Endereco;
import com.felipebicca.otica.domain.Estado;
import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.repositories.CidadeRepository;
import com.felipebicca.otica.repositories.EnderecoRepository;
import com.felipebicca.otica.repositories.EstadoRepository;
import com.felipebicca.otica.repositories.PessoaRepository;



@SpringBootApplication
public class OticaSysApplication implements CommandLineRunner{

	@Autowired
	private PessoaRepository pessoaRepo; 
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OticaSysApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Pessoa p1 = new Pessoa("Felipe Damasceno Bicca", "01724658000", "4096661881", "Masculino", "Casado", sdf.parse("30/09/1988"));

		p1.getEmails().addAll(Arrays.asList("felipedbicca@hotmail.com", "felipebicca@gmail.com"));
		
		Estado est1 = new Estado(null, "Rio Grande do Sul");
		Estado est2 = new Estado(null, "Santa Catarina");
		
		Cidade c1 = new Cidade(null, "Urubici", est2);
		Cidade c2 = new Cidade(null, "Rio Grande", est1);
		Cidade c3 = new Cidade(null, "Gramado", est1);
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Endereco e1 = new Endereco(null, "Andradas", "79", "", "Centro", "96200030", p1, c2);
		Endereco e2 = new Endereco(null, "Av. Flores", "105", "Ap. 800", "Jardim", "98777012", p1, c3);
		
		p1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		pessoaRepo.saveAll(Arrays.asList(p1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
	

}