package controle;

import modelo.*;

public class controleDados {
	
	private Dados dados = new Dados();
	
	//Construtor
	public controleDados() {
		dados.preencherDados();	
	}
	
	//Gets e sets
	
	public Dados getDados() {
		return dados;
	}
	
	public void setDados(Dados dados) {
		this.dados = dados;
	}
	
	public Paciente[] getPacientes() {
		return dados.getPacientes();
	}
	
	public void setPacientes(Paciente[] pacientes) {
		dados.setPacientes(pacientes);
	}
	
	public Medico[] getMedicos() {
		return dados.getMedicos();
	}
	
	public void setMedicos(Medico[] medicos) {
		dados.setMedicos(medicos);
	}
	
	public Remedio[] getRemedios() {
		return dados.getRemedios();
	}
	
	public void setRemedios(Remedio[] remedios) {
		dados.setRemedios(remedios);
	}
	
	public Agendamento[] getAgendamentos() {
		return dados.getAgendamentos();
	}
	
	public void setAgendamentos(Agendamento[] agendamentos) {
		dados.setAgendamentos(agendamentos);
	}
	
	public Boolean inserirEditarPaciente(String[] dadosPaciente) {
		if(!dadosPaciente[2].matches("\\d{10,11}") || !dadosPaciente[2].matches("\\.+\\@\\w+\\.\\w(2,3)\\.\\w(2,3)")) {
			return false;
		}
		else {
			Paciente p = new Paciente(
					Integer.parseInt(dadosPaciente[0]), dadosPaciente[1], dadosPaciente[2], dadosPaciente[3], dadosPaciente[4]
					);
			dados.inserirOuEditarPaciente(p, p.getId());
			return true;
		}
	}
	
	
}
