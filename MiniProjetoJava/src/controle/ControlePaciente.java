package controle;

import modelo.*;

public class ControlePaciente {
	private Paciente[] pacientes;
	private int qtdPacientes;

	public ControlePaciente(ControleDados dados) {
		this.pacientes = dados.getPacientes();
		this.qtdPacientes = dados.getQtdPacientes();
	}

	public String[] getNome() {
		String[] nomes = new String[qtdPacientes];
		for (int i = 0; i < qtdPacientes; i++) {
			nomes[i] = pacientes[i].getNome();
		}
		return nomes;
	}
	
	public int getId(int i) {
		return pacientes[i].getId();
	}

	public String getNome(int i) {
		return pacientes[i].getNome();
	}

	public String getTelefone(int i) {
		return pacientes[i].getTelefone();
	}

	public String getEmail(int i) {
		return pacientes[i].getEmail();
	}
	
	public String getHistoricoDoencas(int i) {
		return pacientes[i].getHistoricoDoencas();
	}
	
	public Remedio[] getAlergias(int i) {
		return pacientes[i].getAlergias();
	}
	
	public void setQtdPacientes(int qtd) {
		this.qtdPacientes = qtd;
	}
}