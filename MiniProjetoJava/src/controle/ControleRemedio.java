package controle;

import modelo.*;

public class ControleRemedio {
	Remedio[] remedios;
	int qtdRemedios;
	
	public ControleRemedio(ControleDados dados) {
		this.remedios = dados.getRemedios();
		this.qtdRemedios = dados.getQtdRemedios();
	}
	
	public String[] getInfo() {
		String[] infos = new String[qtdRemedios];
		for(int i = 0; i < qtdRemedios; i++) {
			infos[i] = Integer.toString(remedios[i].getId()) + " - " + remedios[i].getNome();
		}
		return infos;
	}
	
	public String[] getInfo(String busca) {
		String[] infos = new String[qtdRemedios];
		for(int i = 0; i < qtdRemedios; i++) {
			if(remedios[i].getNome().contains(busca)) {
				infos[i] = Integer.toString(remedios[i].getId()) + " - " + remedios[i].getNome();
			}
		}
		return infos;
	}
	
	public int getId(int i) {
		return remedios[i].getId();
	}
	
	public String getNome(int id) {
		for(int i = 0; i < qtdRemedios; i++) {
			if(remedios[i].getId() == id) {
				return remedios[i].getNome();
			}
		}
		return null;
	}
	
	public String getEfeito(int id) {
		for(int i = 0; i < qtdRemedios; i++) {
			if(remedios[i].getId() == id) {
				return remedios[i].getEfeito();
			}
		}
		return null;
	}
	
	public String getTipo(int id) {
		for(int i = 0; i < qtdRemedios; i++) {
			if(remedios[i].getId() == id) {
				return remedios[i].getTipo();
			}
		}
		return null;
	}
	
	public String getViaDeUso(int id) {
		for(int i = 0; i < qtdRemedios; i++) {
			if(remedios[i].getId() == id) {
				return remedios[i].getViaDeUso();
			}
		}
		return null;
	}
	
	public void setQtdRemedios(int qtd) {
		this.qtdRemedios = qtd;
	}
}
