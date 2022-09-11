package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controle.ControleDados;

class testeTransformarDiaSemana {

	@Test
	void transformarDiaSemana() {
		ControleDados dados = new ControleDados();
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		
		for(int i = 0; i < 7; i++) {
			assertEquals(dias[i], dados.transformarDiaSemana(i));
			assertEquals(i, dados.transformarDiaSemana(dias[i]));
		}
	}

}
