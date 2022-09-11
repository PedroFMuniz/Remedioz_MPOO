package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import controle.*;

class testeGetInfo {

	@Test
	void getInfo() {
		ControleDados controle = new ControleDados();
		ControleAgendamento agendamentos = new ControleAgendamento(controle);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		//Rotinas para obter as informacoes do agendamento 0
		int cont = 0;
		String[] temp = new String[40];
		for(int i = 0; i < controle.getQtdAgendamentos(); i++) {
			if(controle.getAgendamentos()[i] != null && controle.getAgendamentos()[i].getId() == 0) {
				for(int j = 0; j < 7; j++) {
					if(controle.getAgendamentos()[i].getDiasDaSemana()[j] != null) {
						for(int k = 0; k < 40; k++) {
								if(controle.getAgendamentos()[i].getDiasDaSemana()[j].getHorario()[k] != null) {
								temp[cont] = controle.getAgendamentos()[i].getDiasDaSemana()[j].getDiaSemana() + " - " + controle.getAgendamentos()[i].getDiasDaSemana()[j].getHorario()[k].format(formatter);
								cont++;
							}
						}
					}
				}
			}
		}
		
		String[] retorno = new String[cont];
		for (int i = 0; i < cont; i++) {
			retorno[i] = temp[i];
		}
		
		//Chama o metodo getInfo do controle agendamento com o parametro de id igual a 0
		String[] getInfo = agendamentos.getInfo(0);
		
		//Verifica se o esperado e o retorno possuem o mesmo tamanho
		assertEquals(retorno.length, getInfo.length);
		
		//Verifica se o esperado e o retorno possuem o mesmo conteudo
		for(int i = 0; i < cont; i++) {
			assertEquals(retorno[i], getInfo[i]);
		}
	}

}
