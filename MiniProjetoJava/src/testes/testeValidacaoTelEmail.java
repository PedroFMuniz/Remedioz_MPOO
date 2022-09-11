package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controle.ControleDados;

class testeValidacaoTelEmail {

	@Test
	void validacaoTelEmail() {
		ControleDados dados = new ControleDados();
		
		assertTrue(dados.verificarTelEmail("61999999999", "teste123@teste.com"));
		
		assertFalse(dados.verificarTelEmail("6199999-9999", "@teste.com"));
	}
}
