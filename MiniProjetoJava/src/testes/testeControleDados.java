package testes;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import controle.*;


class testeControleDados {

	@Test
	void testeManipularHorarioAgendamento() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.inserirHorarioAgendamento(0, "Terça", LocalTime.of(18, 30)));
	}

	@Test
	void testeValidacaoTelEmail() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.verificarTelEmail("61999999999", "teste123@teste.com"));
		
		assertFalse(dados.verificarTelEmail("6199999-9999", "@teste.com"));
	}
	
	@Test
	void testeTransformarDiaSemana() {
		ControleDados dados = new ControleDados();
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		
		for(int i = 0; i < 7; i++) {
			assertEquals(dias[i], dados.transformarDiaSemana(i));
			assertEquals(i, dados.transformarDiaSemana(dias[i]));
		}
	}
	
	@Test
	void testeInserirAlergiaPaciente() {
		ControleDados dados = new ControleDados();
		
		String[] dadosRemedio = {"10", "Generico", "Placebo", "Comprimido", "Oral"};
		dados.inserirEditarRemedio(dadosRemedio);
	
		
		assertTrue(dados.inserirAlergiasPaciente(0, dados.getRemedios()[10].getId()));
	}
}
