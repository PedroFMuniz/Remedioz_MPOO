package controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.*;

public class ControleAgendamento {
	private Agendamento[] agendamentos;
	int qtdAgendamentos;
	
	public ControleAgendamento(ControleDados dados) {
		this.agendamentos = dados.getAgendamentos();
		this.qtdAgendamentos = dados.getQtdAgendamentos();
	}
	
	public String[] getInfo() {
		String[] infos = new String[40];
		for(int i = 0; i < qtdAgendamentos; i++) {
			infos[i] = Integer.toString(agendamentos[i].getId()) + "-" + agendamentos[i].getRemedio().getNome(); 
		}
		return infos;
	}
	
	public String[] getInfo(int idPaciente, LocalDate data) {
		int indice = 0;
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("E, dd/MM/yyyy");
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String diaSemana = transformarDiaSemana(data.format(formatterData).split(",")[0]);
		String[] infos = new String[40];
		for(int i = 0; i < qtdAgendamentos; i++) {
			if(agendamentos[i].getPaciente().getId() == idPaciente && data.isAfter(agendamentos[i].getDtInicio()) && data.isBefore(agendamentos[i].getDtFim())) {
				for(int j = 0; j < agendamentos[i].getDiasDaSemana().length; j++) {
					if(agendamentos[i].getDiasDaSemana()[j].getDiaSemana() == diaSemana) {
						for(int k = 0; k < agendamentos[i].getDiasDaSemana()[j].getHorario().length; k++) {
							infos[indice] = Integer.toString(agendamentos[i].getId()) + "-" + agendamentos[i].getRemedio().getNome() + "-" + agendamentos[i].getDiasDaSemana()[j].getHorario()[k].format(formatterHora);
							indice++;
						}
					}
				}
			}
		}
		return infos;
	}
	
	public String[] getInfo(int id) {
		int indice = 0;
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String[] infos = new String[40];
		for(int i = 0; i < qtdAgendamentos; i++) {
			if(agendamentos[i].getId() == id) {
				for(int j = 0; j < agendamentos[i].getDiasDaSemana().length; j++) {
						for(int k = 0; k < agendamentos[i].getDiasDaSemana()[j].getHorario().length; k++) {
							infos[indice] = Integer.toString(agendamentos[i].getId()) + "-" + agendamentos[i].getDiasDaSemana()[j].getDiaSemana() +"-" + agendamentos[i].getDiasDaSemana()[j].getHorario()[k].format(formatterHora);
							indice++;
						}
					}
				}
			}
		return infos;
	}
	
	public int getId(int i) {
		return agendamentos[i].getId();
	}
	
	public LocalDate getDtInicio(int id) {
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i].getId() == id) {
				return agendamentos[i].getDtInicio();
			}
		}
		return null;
	}
	
	public LocalDate getDtFim(int id) {
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i].getId() == id) {
				return agendamentos[i].getDtFim();
			}
		}
		return null;
	}
	
	public Remedio getRemedio(int id) {
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i].getId() == id) {
				return agendamentos[i].getRemedio();
			}
		}
		return null;
	}
	
	public Paciente getPaciente(int id) {
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i].getId() == id) {
				return agendamentos[i].getPaciente();
			}
		}
		return null;
	}
	
	public Medico getMedico(int id) {
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i].getId() == id) {
				return agendamentos[i].getMedico();
			}
		}
		return null;
	}
	
	public DiaDaSemana[] getDiasSemana(int id) {
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i].getId() == id) {
				return agendamentos[i].getDiasDaSemana();
			}
		}
		return null;
	}
	
	public String transformarDiaSemana(String dia) {
		String[] us = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
		String[] br = {"Segunda","Terça","Quarta","Quinta","Sexta","Sábado","Domingo"};
		for(int i = 0; i < 7; i++) {
			if(us[i] == dia) {
				return br[i];
			}
		}
		return null;
	}
}