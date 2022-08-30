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
	
	public Boolean inserirEditarMedico(String[] dadosMedico) {
		if(!dadosMedico[2].matches("\\d{10,11}") || !dadosMedico[2].matches("\\.+\\@\\w+\\.\\w(2,3)\\.\\w(2,3)")) {
			return false;
		}
		else {
			Medico m = new Medico(
					Integer.parseInt(dadosMedico[0]), dadosMedico[1], dadosMedico[2], dadosMedico[3], dadosMedico[4], dadosMedico[5]
			);
			dados.inserirOuEditarMedico(m, m.getId());
			return true;
		}
	}
	
	public Boolean inserirEditarRemedio(String[] dadosRemedio) {
		Remedio r = new Remedio(
				Integer.parseInt(dadosRemedio[0]), dadosRemedio[1], dadosRemedio[2], dadosRemedio[3], dadosRemedio[4]
		);
		dados.inserirOuEditarRemedio(r, r.getId());
		return true;
	}
	
	public Boolean inserirAgendamento
	
}
