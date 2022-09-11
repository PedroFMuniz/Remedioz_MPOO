package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controle.ControleDados;

class testeInserirHorarioAgendamento {

	@Test
	void inserirHorarioAgendamento() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.inserirHorarioAgendamento(0, "Terça", 18, 30));
	}

}
