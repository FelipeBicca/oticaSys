package com.felipebicca.otica;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipebicca.otica.domain.Cidade;
import com.felipebicca.otica.domain.Consulta;
import com.felipebicca.otica.domain.Endereco;
import com.felipebicca.otica.domain.Estado;
import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.domain.Telefone;
import com.felipebicca.otica.repositories.CidadeRepository;
import com.felipebicca.otica.repositories.ConsultaRepository;
import com.felipebicca.otica.repositories.EnderecoRepository;
import com.felipebicca.otica.repositories.EstadoRepository;
import com.felipebicca.otica.repositories.PessoaRepository;
import com.felipebicca.otica.repositories.TelefoneRepository;



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
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OticaSysApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Pessoa p1 = new Pessoa(null, "Felipe Damasceno Bicca", "01724658000", "4096661881", "Masculino", "Casado", sdf.parse("30/09/1988"));
		Pessoa p2 = new Pessoa(null, "Raline Simões Saraiva", "05259773098", "1127632006", "Feminino", "Solteira", sdf.parse("03/04/2002"));

		p1.getEmails().addAll(Arrays.asList("felipedbicca@hotmail.com", "felipebicca@gmail.com"));
		p2.getEmails().add("ralinesimoess2@gmail.com");
		
		Estado est1 = new Estado(null, "Rio Grande do Sul");
		Estado est2 = new Estado(null, "Santa Catarina");
		
		Cidade c1 = new Cidade(null, "Urubici", est2);
		Cidade c2 = new Cidade(null, "Rio Grande", est1);
		Cidade c3 = new Cidade(null, "Gramado", est1);
		Cidade c4 = new Cidade(null, "Lavras do Sul", est1);
		
		Endereco e1 = new Endereco(null, "Andradas", "79", "", "Centro", "96200030", p1, c2);
		Endereco e2 = new Endereco(null, "Av. Flores", "105", "Ap. 800", "Jardim", "98777012", p1, c3);
		Endereco e3 = new Endereco(null, "Senador Alberto Pasqualine", "391", "Ap. 1", "CEDRO", "96201050", p2, c2);
		Endereco e4 = new Endereco(null, "Antenor Moreira de Castro", "21", "", "Vila Dr. Bucão", "97390000", p2, c4);
		
		p1.getEnderecos().addAll(Arrays.asList(e1, e2));
		p2.getEnderecos().addAll(Arrays.asList(e3, e4));
		
		Consulta cons1 = new Consulta(null, "Não foi necessário fazer receita para o cliente", p1);
		Consulta cons2 = new Consulta(null, "Cliente tem miopia", p1);
		Consulta cons3 = new Consulta(null, "Cliente é cega, tem uma miopia e astigmatismo", p2);
		
		Telefone t1 = new Telefone(null, 53, "991254000", true, p1);
		Telefone t2 = new Telefone(null, 53, "32335025", false, p1);
		Telefone t3 = new Telefone(null, 53, "991423211", true, p2);
		Telefone t4 = new Telefone(null, 55, "32822080", false, p2);
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		pessoaRepo.saveAll(Arrays.asList(p1, p2));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		telefoneRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
		consultaRepository.saveAll(Arrays.asList(cons1, cons2, cons3));
	}
}