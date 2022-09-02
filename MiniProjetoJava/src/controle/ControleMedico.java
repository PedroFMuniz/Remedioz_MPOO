package controle;

import modelo.*;

public class ControleMedico {
	private Medico[] medicos;
	private int qtdMedicos;
	
	public ControleMedico(ControleDados dados) {
		this.medicos = dados.getMedicos();
		this.qtdMedicos = dados.getQtdMedicos();
	}
	
	public String[] getNome() {
		String[] nomes = new String[qtdMedicos];
		for(int i = 0; i < qtdMedicos; i++) {
			nomes[i] = medicos[i].getNome(); 
		}
		return nomes;
	}
	
	public String[] getEspecialidade() {
		String[] especialidades = new String[qtdMedicos];
		for(int i = 0; i < qtdMedicos; i++) {
			especialidades[i] = medicos[i].getEspecialidade(); 
		}
		return especialidades;
	}
	
	public int getId(int i) {
		return medicos[i].getId();
	}
	
	public String getNome(int i) {
		return medicos[i].getNome();
	}
	
	public String getTelefone(int i) {
		return medicos[i].getTelefone();
	}
	
	public String getEmail(int i) {
		return medicos[i].getEmail();
	}
	
	public String getCrm(int i) {
		return medicos[i].getCrm();
	}
	
	public String getEspecialidade(int i) {
		return medicos[i].getEspecialidade();
	}

	public void setQtdMedico(int qtd) {
		this.qtdMedicos = qtd;
	}
}
