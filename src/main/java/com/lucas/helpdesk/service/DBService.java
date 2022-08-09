package com.lucas.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.helpdesk.domain.Chamado;
import com.lucas.helpdesk.domain.Cliente;
import com.lucas.helpdesk.domain.Tecnico;
import com.lucas.helpdesk.domain.enums.Perfil;
import com.lucas.helpdesk.domain.enums.Prioridade;
import com.lucas.helpdesk.domain.enums.Status;
import com.lucas.helpdesk.repositories.ChamadoRepository;
import com.lucas.helpdesk.repositories.ClienteRepository;
import com.lucas.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public void instanciaDB() {
		

		Tecnico tec1 = new Tecnico(null, "Lucas Kavetzki","43994373085", "lucaskavetzki@outlook.com","1234");
		tec1.addPerfil(Perfil.ADMIN);

		Tecnico tec2 = new Tecnico(null, "Fernanda Devs","78913771039", "ferdevs@outlook.com","12345");

		Tecnico tec3 = new Tecnico(null, "Pedro Silveira","00611553066", "pesilva@me.com","123456");

		Tecnico tec4 = new Tecnico(null, "Nicole Tarstuk","32637041099", "tasturk@gmail.com","1234567");

		Tecnico tec5 = new Tecnico(null, "David Pentreski","07221133077", "davistraski@outlook.com","12345678");

		
		Cliente cli1 = new Cliente(null, "Durval Silva", "80855016000","durvasilva_78@gmail.com", "123");
		Cliente cli2 = new Cliente(null, "Regina Santos", "12438220074","santosregi_02@hotmail.com", "1234");
		Cliente cli3 = new Cliente(null, "Fernando Turco", "41335260072","turco@outlook.com", "12345");
		Cliente cli4 = new Cliente(null, "Jesica damião", "17448751034","miao8@me.com", "123456");
		Cliente cli5 = new Cliente(null, "Zaquiel Jesco", "22694307000","jesco@outlook.com", "1234567");

		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA,Status.ANDAMENTO, " Chamado 01", "Primeiro Chamado",tec1,cli1 );
		Chamado c2 = new Chamado(null, Prioridade.BAIXA,Status.ABERTO, " Chamado 02", "Segundo Chamado",tec5,cli3 );
		Chamado c3 = new Chamado(null, Prioridade.ALTA,Status.ANDAMENTO, " Chamado 03", "Terceiro Chamado",tec3,cli5 );
		Chamado c4 = new Chamado(null, Prioridade.BAIXA,Status.ENCERRADO, " Chamado 04", "Quarto Chamado",tec2,cli4 );

		
		tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3,tec4,tec5));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4,cli5));
		
		chamadoRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
	}

}
