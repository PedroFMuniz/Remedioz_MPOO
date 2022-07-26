package controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import modelo.*;


/**
 * Classe "ControleAgendamento". Responsavel por retornar informacoes acerca dos agendamentos
 * cadastrados.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class ControleAgendamento {
	private Agendamento[] agendamentos;
	int qtdAgendamentos;
	private ControleRemedio remedios;

	/**
	 * Construtor para obter os agendamentos cadastrados
	 * 
	 * @param dados ControleDados que retorna os agendamentos cadastrados
	 * @see ControleDados
	 */
	public ControleAgendamento(ControleDados dados) {
		this.agendamentos = dados.getAgendamentos();
		this.qtdAgendamentos = dados.getQtdAgendamentos();
		this.remedios = new ControleRemedio(dados);
	}

	/**
	 * Metodo responsavel por retornar as informacoes necessarias de todos os
	 * agendamentos cadastrados
	 * 
	 * @return Array de Strings no formato: id - nome do remedio
	 */
	public String[] getInfo() {
		String[] infos = new String[qtdAgendamentos];
		for (int i = 0; i < qtdAgendamentos; i++) {
			infos[i] = Integer.toString(agendamentos[i].getId()) + " - " + remedios.getNome(agendamentos[i].getRemedio().getId());
		}
		return infos;
	}

	/**
	 * Metodo responsavel por retornar as informacoes necessarias de agendamentos
	 * que possuem um paciente e data informados
	 * 
	 * @param idPaciente int indicando o paciente ao qual os agendamentos devem estar relacionados
	 * @param data String indicando a data na qual o agendamento deve estar incluso
	 * @return Array de Strings no formato: id - nome do remedio - horario
	 */
	public String[] getInfo(int idPaciente, String data) {
		int indice = 0;
		String[] infotemp = new String[40];
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", Locale.US);
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String diaSemana = data.split(", ")[0];
		LocalDate ld = LocalDate.parse(transformarDiaSemana(diaSemana, 2) + ", " + data.split(", ")[1], formatterData);
		//Rotina para identificar agendamentos que possuam o paciente indicado
		for (int i = 0; i < qtdAgendamentos; i++) {
			if (agendamentos[i].getPaciente() != null && agendamentos[i].getPaciente().getId() == idPaciente
					&& ((ld.isAfter(agendamentos[i].getDtInicio()) && ld.isBefore(agendamentos[i].getDtFim())) || ld.equals(agendamentos[i].getDtInicio()) || ld.equals(agendamentos[i].getDtFim()))) {
				//Rotina para obter os dias da semana do agendamento
				for (int j = 0; j < 7; j++) {
					if (agendamentos[i].getDiasDaSemana()[j] != null
							&& agendamentos[i].getDiasDaSemana()[j].getDiaSemana().equals(diaSemana)) {
						//Rotina para obter os horarios do dia da semana e adiciona-los ao retorno
						for (int k = 0; k < agendamentos[i].getDiasDaSemana()[j].getHorario().length; k++) {
							if (agendamentos[i].getDiasDaSemana()[j].getHorario()[k] != null) {
								infotemp[indice] = Integer.toString(agendamentos[i].getId()) + " - "
										+ remedios.getNome(agendamentos[i].getRemedio().getId()) + " - "
										+ agendamentos[i].getDiasDaSemana()[j].getHorario()[k].format(formatterHora);
								indice++;
							}
						}
					}
				}
			}
		}
		//Retorna um array com somente o numero de posicoes prenchidas
		String[] info = new String[indice];
		for (int w = 0; w < indice; w++) {
			info[w] = infotemp[w];
		}
		return info;
	}

	/**
	 * Metodo responsavel por retornar as informacoes necessarias acerca dos dias da semana
	 * de um agendamento informado
	 * 
	 * @param id int indicando o agendamento selecionado
	 * @return Array de Strings no formato: dia da semana - horario
	 */
	public String[] getInfo(int id) {
		int indice = 0;
		String[] infotemp = new String[40];
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		//Rotina para identificar o agendamento
		for (int i = 0; i < qtdAgendamentos; i++) {
			if (agendamentos[i].getId() == id) {
				//Rotina para adicionar os dias da semana
				for (int j = 0; j < 7; j++) {
					if (agendamentos[i].getDiasDaSemana()[j] != null) {
						//Rotina para adicionar os horarios
						for (int k = 0; k < agendamentos[i].getDiasDaSemana()[j].getHorario().length; k++) {
							if (agendamentos[i].getDiasDaSemana()[j].getHorario()[k] != null) {
								infotemp[indice] = agendamentos[i].getDiasDaSemana()[j].getDiaSemana() + " - "
										+ agendamentos[i].getDiasDaSemana()[j].getHorario()[k].format(formatterHora);
								indice++;
							}
						}
					}
				}
				//Retorna um array com somente o numero de posicoes prenchidas
				String[] info = new String[indice];
				for (int j = 0; j < indice; j++) {
					info[j] = infotemp[j];
				}
				return info;
			}
		}
		return null;
	}

	//Gets e sets
	
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

	/**
	 * Metodo responsavel por transformar os dias da semana do ingles
	 * para o portugues e vice versa.
	 * 
	 * 
	 * @param dia String com o nome do dia
	 * @param op int indicando qual traducao deve ser feita
	 * @return String com o nome traduzido do dia
	 */
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

	/**
	 * Metodo responsavel por transformar o dia a ser usado 
	 * como parametro para busca de agendamentos.
	 * 
	 * @param data String informando a data a ser transformada
	 * @param op int indicando que tipo de transformacao deve ocorrer
	 * @return String com a data transformada
	 */
	public String mudarLabel(String data, int op) {
		String dia;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", Locale.US);
		//Mantem a mesma data
		if (op == 3) {
			dia = data;
		//Traduz o nome do dia para ingles
		} else {
			dia = transformarDiaSemana(data.split(", ")[0], 2) + ", " + data.split(", ")[1];
		}
		LocalDate ld = LocalDate.parse(dia, formatter);
		//Adiciona/Subtrai um dia da data
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
