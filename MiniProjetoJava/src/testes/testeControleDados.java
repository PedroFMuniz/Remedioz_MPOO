package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import controle.*;
import modelo.*;


class testeControleDados {

	@Test
	void testeManipularHorarioAgendamento() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.manipularHorarioAgendamento(0, "Terça", LocalTime.of(18, 30)));
	}
	
	@Test
	void testeManipularHorarioAgendamento2() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.manipularHorarioAgendamento(0, "Sexta", LocalTime.of(18, 30), LocalTime.of(19, 30)));
		
		assertFalse(dados.manipularHorarioAgendamento(0, "Terça", LocalTime.of(18, 30), LocalTime.of(19, 30)));
	}
	
	@Test
	void testeValidacaoTelEmail() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.verificarTelEmail("61999999999", "teste123@teste.com"));
		
		assertFalse(dados.verificarTelEmail("6199999-9999", "@teste.com"));
	}
}
