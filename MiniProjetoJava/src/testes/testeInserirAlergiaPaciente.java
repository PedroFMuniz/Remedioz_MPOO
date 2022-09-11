package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controle.ControleDados;

class testeInserirAlergiaPaciente {

	@Test
	void inserirAlergiaPaciente() {
		ControleDados dados = new ControleDados();
		
		String[] dadosRemedio = {"10", "Generico", "Placebo", "Comprimido", "Oral"};
		dados.inserirEditarRemedio(dadosRemedio);
	
		
		assertTrue(dados.inserirAlergiasPaciente(0, dados.getRemedios()[10].getId()));
	}
}
