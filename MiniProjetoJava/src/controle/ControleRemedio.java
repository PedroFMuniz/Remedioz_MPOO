package controle;

import modelo.*;

public class ControleRemedio {
	Remedio[] remedios;
	int qtdRemedios;
	
	public ControleRemedio(ControleDados dados) {
		this.remedios = dados.getRemedios();
		this.qtdRemedios = dados.getQtdRemedios();
	}
	
	public String[] getNome() {
		String[] nomes = new String[qtdRemedios];
		for(int i = 0; i < qtdRemedios; i++) {
			nomes[i] = remedios[i].getNome();
		}
		return nomes;
	}
	
	public int getId(int i) {
		return remedios[i].getId();
	}
	
	public String getNome(int i) {
		return remedios[i].getNome();
	}
	
	public String getEfeito(int i) {
		return remedios[i].getEfeito();
	}
	
	public String getTipo(int i) {
		return remedios[i].getTipo();
	}
	
	public String getViaDeUso(int i) {
		return remedios[i].getViaDeUso();
	}
	
	public void setQtdRemedios(int qtd) {
		this.qtdRemedios = qtd;
	}
}
