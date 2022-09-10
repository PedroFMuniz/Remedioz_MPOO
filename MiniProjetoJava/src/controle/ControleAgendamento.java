package controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import modelo.*;

public class ControleAgendamento {
	private Agendamento[] agendamentos;
	int qtdAgendamentos;

	public ControleAgendamento(ControleDados dados) {
		this.agendamentos = dados.getAgendamentos();
		this.qtdAgendamentos = dados.getQtdAgendamentos();
	}

	public String[] getInfo() {
		String[] infos = new String[qtdAgendamentos];
		for (int i = 0; i < qtdAgendamentos; i++) {
			infos[i] = Integer.toString(agendamentos[i].getId()) + " - " + agendamentos[i].getRemedio().getNome();
		}
		return infos;
	}

	public String[] getInfo(int idPaciente, String data) {
		int indice = 0;
		String[] infotemp = new String[40];
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", Locale.US);
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String diaSemana = data.split(", ")[0];
		LocalDate ld = LocalDate.parse(transformarDiaSemana(diaSemana, 2) + ", " + data.split(", ")[1], formatterData);
		for (int i = 0; i < qtdAgendamentos; i++) {
			if (agendamentos[i] != null && agendamentos[i].getPaciente().getId() == idPaciente
					&& ld.isAfter(agendamentos[i].getDtInicio()) && ld.isBefore(agendamentos[i].getDtFim())) {
				for (int j = 0; j < 7; j++) {
					if (agendamentos[i].getDiasDaSemana()[j] != null
							&& agendamentos[i].getDiasDaSemana()[j].getDiaSemana().equals(diaSemana)) {
						for (int k = 0; k < agendamentos[i].getDiasDaSemana()[j].getHorario().length; k++) {
							if (agendamentos[i].getDiasDaSemana()[j].getHorario()[k] != null) {
								infotemp[indice] = Integer.toString(agendamentos[i].getId()) + " - "
										+ agendamentos[i].getRemedio().getNome() + " - "
										+ agendamentos[i].getDiasDaSemana()[j].getHorario()[k].format(formatterHora);
								indice++;
							}
						}
					}
				}
			}
		}
		String[] info = new String[indice];
		for (int w = 0; w < indice; w++) {
			info[w] = infotemp[w];
		}
		return info;
	}

	public String[] getInfo(int id) {
		int indice = 0;
		String[] infotemp = new String[40];
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < qtdAgendamentos; i++) {
			if (agendamentos[i].getId() == id) {
				for (int j = 0; j < 7; j++) {
					if (agendamentos[i].getDiasDaSemana()[j] != null) {
						for (int k = 0; k < agendamentos[i].getDiasDaSemana()[j].getHorario().length; k++) {
							if (agendamentos[i].getDiasDaSemana()[j].getHorario()[k] != null) {
								infotemp[indice] = agendamentos[i].getDiasDaSemana()[j].getDiaSemana() + " - "
										+ agendamentos[i].getDiasDaSemana()[j].getHorario()[k].format(formatterHora);
								indice++;
							}
						}
					}
				}
				String[] info = new String[indice];
				for (int j = 0; j < indice; j++) {
					info[j] = infotemp[j];
				}
				return info;
			}
		}
		return null;
	}

	public int getId(int i) {
		return agendamentos[i].getId();
	}

	public LocalDate getDtInicio(int id) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == id) {
				return agendamentos[i].getDtInicio();
			}
		}
		return null;
	}

	public LocalDate getDtFim(int id) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == id) {
				return agendamentos[i].getDtFim();
			}
		}
		return null;
	}

	public Remedio getRemedio(int id) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == id) {
				return agendamentos[i].getRemedio();
			}
		}
		return null;
	}

	public Paciente getPaciente(int id) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == id) {
				return agendamentos[i].getPaciente();
			}
		}
		return null;
	}

	public Medico getMedico(int id) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == id) {
				return agendamentos[i].getMedico();
			}
		}
		return null;
	}

	public DiaDaSemana[] getDiasSemana(int id) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == id) {
				return agendamentos[i].getDiasDaSemana();
			}
		}
		return null;
	}

	public String transformarDiaSemana(String dia, int op) {
		String[] us = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		String[] br = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		if (op == 1) {
			for (int i = 0; i < 7; i++) {
				if (dia.equals(us[i])) {
					return br[i];
				}
			}
		} else {
			for (int i = 0; i < 7; i++) {
				if (dia.equals(br[i])) {
					return us[i];
				}
			}
		}
		return null;
	}

	public String mudarLabel(String data, int op) {
		String dia;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", Locale.US);
		if (op == 3) {
			dia = data;
		} else {
			dia = transformarDiaSemana(data.split(", ")[0], 2) + ", " + data.split(", ")[1];
		}
		LocalDate ld = LocalDate.parse(dia, formatter);
		if (op == 1) {
			ld = ld.plusDays(1);
		} else if (op == 2) {
			ld = ld.minusDays(1);
		}
		dia = ld.format(formatter);
		String retorno = transformarDiaSemana(dia.split(",")[0], 1) + ", " + dia.split(", ")[1];
		return retorno;
	}
}
