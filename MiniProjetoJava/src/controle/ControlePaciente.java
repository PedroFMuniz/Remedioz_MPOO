package controle;

import modelo.*;

/**
 * Classe "ControlePaciente". Responsavel por retornar informacoes acerca dos
 * pacientes cadastrados.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class ControlePaciente {
	private Paciente[] pacientes;
	private int qtdPacientes;

	/**
	 * Construtor para obter os pacientes cadastrados
	 * 
	 * @param dados ControleDados que retorna os pacientes cadastrados
	 * @see ControleDados
	 */
	public ControlePaciente(ControleDados dados) {
		this.pacientes = dados.getPacientes();
		this.qtdPacientes = dados.getQtdPacientes();
	}

	/**
	 * Metodo responsavel por retornar os dados necessarios de todos os pacientes
	 * cadastrados
	 * 
	 * @return Array de Strings no formato: id - nome
	 */
	public String[] getInfo() {
		String[] infos = new String[qtdPacientes];
		for (int i = 0; i < qtdPacientes; i++) {
			infos[i] = Integer.toString(pacientes[i].getId()) + " - " + pacientes[i].getNome().split(" ")[0];
		}
		return infos;
	}

	/**
	 * Metodo responsavel por retornar os dados necessarios de pacientes com base em
	 * uma String de busca
	 * 
	 * @param busca String com o nome do paciente a ser pesquisado no registro
	 * @return Array de Strings no formato: id - nome
	 */
	public String[] getInfo(String busca) {
		String[] infostemp = new String[qtdPacientes];
		String[] infos = new String[0];
		int cont = 0;
		//Rotina para adicionar apenas os registros pesquisas
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getNome().split(" ")[0].toUpperCase().contains(busca.toUpperCase())) {
				infostemp[i] = Integer.toString(pacientes[i].getId()) + " - " + pacientes[i].getNome().split(" ")[0];
				cont++;
			}
		}
		infos = new String[cont];
		//Rotina para retornar um vetor com o numero exato de posicoes
		for (int i = 0; i < cont; i++) {
			for (int j = 0; j < qtdPacientes; j++) {
				if (infos[i] == null && infostemp[j] != null) {
					infos[i] = infostemp[j];
					infostemp[j] = null;
					break;
				}
			}
		}
		return infos;
	}

	/**
	 * Metodo responsavel por retornar as alergias de um paciente especifico
	 * 
	 * @param id int indicando o paciente
	 * @return Array de Strings no formato: id do remedio - nome do remedio
	 */
	public String[] getInfoAlergias(int id) {
		int cont = 0;
		String[] nomesRemedios = null;
		//Rotina para identificar o paciente
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getId() == id) {
				//Rotina para retornar um vetor com o numero exato de posicoes
				while (pacientes[i].getAlergias()[cont] != null) {
					cont++;
				}
				nomesRemedios = new String[cont];
				for (int j = 0; j < cont; j++) {
					nomesRemedios[j] = Integer.toString(pacientes[i].getAlergias()[j].getId()) + " - "
							+ pacientes[i].getAlergias()[j].getNome();
				}
			}
		}
		return nomesRemedios;
	}

	//Gets e sets
	
	public int getId(int i) {
		return pacientes[i].getId();
	}

	public String getNome(int id) {
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getId() == id) {
				return pacientes[i].getNome();
			}
		}
		return null;
	}

	public String getTelefone(int id) {
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getId() == id) {
				return pacientes[i].getTelefone();
			}
		}
		return null;
	}

	public String getEmail(int id) {
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getId() == id) {
				return pacientes[i].getEmail();
			}
		}
		return null;
	}

	public String getHistoricoDoencas(int id) {
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getId() == id) {
				return pacientes[i].getHistoricoDoencas();
			}
		}
		return null;
	}

	public Remedio[] getAlergias(int id) {
		for (int i = 0; i < qtdPacientes; i++) {
			if (pacientes[i].getId() == id) {
				return pacientes[i].getAlergias();
			}
		}
		return null;
	}

	public void setQtdPacientes(int qtd) {
		this.qtdPacientes = qtd;
	}
}