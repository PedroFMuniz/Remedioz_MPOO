package controle;

import modelo.*;

public class ControleMedico {
	private Medico[] medicos;
	private int qtdMedicos;
	
	public ControleMedico(ControleDados dados) {
		this.medicos = dados.getMedicos();
		this.qtdMedicos = dados.getQtdMedicos();
	}
	
	public String[] getInfo() {
		String[] infos = new String[qtdMedicos];
		for(int i = 0; i < qtdMedicos; i++) {
			infos[i] = Integer.toString(medicos[i].getId()) + " - " + medicos[i].getNome() + " - " + medicos[i].getEspecialidade(); 
		}
		return infos;
	}
	
	public String[] getInfo(String busca) {
		String[] infos = new String[qtdMedicos];
		for(int i = 0; i < qtdMedicos; i++) {
			if(medicos[i].getNome().contains(busca)) {
				infos[i] = Integer.toString(medicos[i].getId()) + " - " + medicos[i].getNome() + " - " + medicos[i].getEspecialidade();
			}
		}
		return infos;
	}
	
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
