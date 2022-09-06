package controle;

import modelo.*;

public class ControlePaciente {
	private Paciente[] pacientes;
	private int qtdPacientes;

	public ControlePaciente(ControleDados dados) {
		this.pacientes = dados.getPacientes();
		this.qtdPacientes = dados.getQtdPacientes();
	}

	public String[] getInfo() {
		String[] infos = new String[qtdPacientes];
		for (int i = 0; i < qtdPacientes; i++) {
			infos[i] = Integer.toString(pacientes[i].getId()) + "-" + pacientes[i].getNome();
		}
		return infos;
	}
	
	public int getId(int i) {
		return pacientes[i].getId();
	}

	public String getNome(int id) {
		for(int i = 0; i < qtdPacientes; i++) {
			if(pacientes[i].getId() == id) {
				return pacientes[i].getNome();
			}
		}
		return null;
	}

	public String getTelefone(int id) {
		for(int i = 0; i < qtdPacientes; i++) {
			if(pacientes[i].getId() == id) {
				return pacientes[i].getTelefone();
			}
		}
		return null;
	}

	public String getEmail(int id) {
		for(int i = 0; i < qtdPacientes; i++) {
			if(pacientes[i].getId() == id) {
				return pacientes[i].getEmail();
			}
		}
		return null;
	}
	
	public String getHistoricoDoencas(int id) {
		for(int i = 0; i < qtdPacientes; i++) {
			if(pacientes[i].getId() == id) {
				return pacientes[i].getHistoricoDoencas();
			}
		}
		return null;
	}
	
	public Remedio[] getAlergias(int id) {
		for(int i = 0; i < qtdPacientes; i++) {
			if(pacientes[i].getId() == id) {
				return pacientes[i].getAlergias();
			}
		}
		return null;
	}
	
	public void setQtdPacientes(int qtd) {
		this.qtdPacientes = qtd;
	}
}