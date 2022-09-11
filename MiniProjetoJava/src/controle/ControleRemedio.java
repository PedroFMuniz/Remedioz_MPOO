package controle;

import modelo.*;

/**
 * Classe "ControleRemedio". Responsavel por retornar informacoes acerca dos
 * remedios cadastrados.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class ControleRemedio {
	Remedio[] remedios;
	int qtdRemedios;

	/**
	 * Construtor para obter os remedios cadastrados
	 * 
	 * @param dados ControleDados que retorna os remedios cadastrados
	 * @see ControleDados
	 */
	public ControleRemedio(ControleDados dados) {
		this.remedios = dados.getRemedios();
		this.qtdRemedios = dados.getQtdRemedios();
	}

	/**
	 * Metodo responsavel por retornar os dados necessarios de todos os remedios
	 * cadastrados
	 * 
	 * @return Array de Strings no formato: id - nome
	 */
	public String[] getInfo() {
		String[] infos = new String[qtdRemedios];
		for (int i = 0; i < qtdRemedios; i++) {
			infos[i] = Integer.toString(remedios[i].getId()) + " - " + remedios[i].getNome();
		}
		return infos;
	}

	/**
	 * Metodo responsavel por retornar os dados necessarios de remedios com base em
	 * uma String de busca
	 * 
	 * @param busca : String com o nome do remedio a ser pesquisado no registro
	 * @return Array de Strings no formato: id - nome
	 */
	public String[] getInfo(String busca) {
		int cont = 0;
		String[] infos = new String[0];
		String[] infostemp = new String[qtdRemedios];
		//Rotina para adicionar apenas os registros pesquisas
		for (int i = 0; i < qtdRemedios; i++) {
			if (remedios[i].getNome().toUpperCase().contains(busca.toUpperCase())) {

				infostemp[i] = Integer.toString(remedios[i].getId()) + " - " + remedios[i].getNome();
				cont++;
			}
		}
		infos = new String[cont];
		//Rotina para retornar um vetor com o numero exato de posicoes
		for (int i = 0; i < cont; i++) {
			for (int j = 0; j < qtdRemedios; j++) {
				if (infos[i] == null && infostemp[j] != null) {
					infos[i] = infostemp[j];
					infostemp[j] = null;
					break;
				}
			}
		}
		return infos;
	}

	// Gets e sets

	public int getId(int i) {
		return remedios[i].getId();
	}

	public String getNome(int id) {
		for (int i = 0; i < qtdRemedios; i++) {
			if (remedios[i].getId() == id) {
				return remedios[i].getNome();
			}
		}
		return null;
	}

	public String getEfeito(int id) {
		for (int i = 0; i < qtdRemedios; i++) {
			if (remedios[i].getId() == id) {
				return remedios[i].getEfeito();
			}
		}
		return null;
	}

	public String getTipo(int id) {
		for (int i = 0; i < qtdRemedios; i++) {
			if (remedios[i].getId() == id) {
				return remedios[i].getTipo();
			}
		}
		return null;
	}

	public String getViaDeUso(int id) {
		for (int i = 0; i < qtdRemedios; i++) {
			if (remedios[i].getId() == id) {
				return remedios[i].getViaDeUso();
			}
		}
		return null;
	}

	public void setQtdRemedios(int qtd) {
		this.qtdRemedios = qtd;
	}
}
