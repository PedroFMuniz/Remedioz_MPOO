package controle;

import modelo.*;

/**
 * Classe "ControleMedico". Responsavel por retornar informacoes acerca dos medicos
 * cadastrados.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class ControleMedico {
	private Medico[] medicos;
	private int qtdMedicos;
	
	/**
	 * Construtor para obter os medicos cadastrados
	 * 
	 * @param dados : ControleDados que retorna os medicos cadastrados
	 */
	public ControleMedico(ControleDados dados) {
		this.medicos = dados.getMedicos();
		this.qtdMedicos = dados.getQtdMedicos();
	}
	
	/**
	 * Metodo responsavel por retornar os dados necessarios de todos os medicos cadastrados
	 * 
	 * @return Array de Strings no formato: id - nome - especialidade
	 */
	public String[] getInfo() {
		String[] infos = new String[qtdMedicos];
		for(int i = 0; i < qtdMedicos; i++) {
			infos[i] = Integer.toString(medicos[i].getId()) + " - " + medicos[i].getNome() + " - " + medicos[i].getEspecialidade(); 
		}
		return infos;
	}
	
	/**
	 *Metodo responsavel por retornar os dados necessarios de medicos com base em uma String de busca 
	 * 
	 * @param busca : String com o nome do medico a ser pesquisado
	 * @return Array de Strings no formato: id - nome - especialidade
	 */
	public String[] getInfo(String busca) {
		String[] infos = new String[qtdMedicos];
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getNome().contains(busca)) {
				infos[i] = Integer.toString(medicos[i].getId()) + " - " + medicos[i].getNome() + " - " + medicos[i].getEspecialidade();
			}
		}
		return infos;
	}
	
	//gets e sets
	
	public int getId(int i) {
		return medicos[i].getId();
	}
	
	public String getNome(int id) {
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getId() == id) {
				return medicos[i].getNome();
			}
		}
		return null;
	}
	
	public String getTelefone(int id) {
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getId() == id) {
				return medicos[i].getTelefone();
			}
		}
		return null;
	}
	
	public String getEmail(int id) {
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getId() == id) {
				return medicos[i].getEmail();
			}
		}
		return null;
	}
	
	public String getCrm(int id) {
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getId() == id) {
				return medicos[i].getCrm();
			}
		}
		return null;
	}
	
	public String getEspecialidade(int id) {
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getId() == id) {
				return medicos[i].getEspecialidade();
			}
		}
		return null;
	}

	public void setQtdMedico(int qtd) {
		this.qtdMedicos = qtd;
	}
}
