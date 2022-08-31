package controle;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
			Remedio[] alergias = separarVetorRemedio(dadosPaciente[4]);
			Paciente p = new Paciente(
					Integer.parseInt(dadosPaciente[0]), dadosPaciente[1], dadosPaciente[2], dadosPaciente[3], alergias ,dadosPaciente[5]
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
	
	public Boolean inserirEditarAgendamento(String[] dadosAgendamento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld = LocalDate.now();
		Medico[] medicos = dados.getMedicos();
		Paciente[] pacientes = dados.getPacientes();
		Agendamento a = new Agendamento(
				Integer.parseInt(dadosAgendamento[0]), LocalDate.parse(dadosAgendamento[1], formatter), LocalDate.parse(dadosAgendamento[2], formatter),
				medicos[Integer.parseInt(dadosAgendamento[3])], pacientes[Integer.parseInt(dadosAgendamento[4])], separarVetorRemedio(dadosAgendamento[5]),
				separarVetorDiaDaSemana(dadosAgendamento[6])
		);
		dados.inserirOuEditarAgendamento(a, a.getId());
		return true;
	}
	public DiaDaSemana[] separarVetorDiaDaSemana(String vetor) {
		int indice = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String[] vetorSeparado = vetor.split("_");
		DiaDaSemana[] dias = new DiaDaSemana[7];
		for (int i = 0; i < 7; i++) {
			LocalTime[] horarios = new LocalTime[40];
			for (int j = 0; i < vetorSeparado.length; i++) {
				String[] dadosDia = vetorSeparado[i].split(",");
				if(transformarDiaSemana(dadosDia[0]) == i) {
					horarios[indice] = LocalTime.parse(dadosDia[1], formatter);
					indice++;
				}
			}
			DiaDaSemana d = new DiaDaSemana(i, transformarDiaSemana(i), horarios);
			dias[i] = d;
		}
		return dias;
	}
	
	public Remedio[] separarVetorRemedio(String vetor) {
		String[] vetorSeparado = vetor.split("_");
		Remedio[] remedios = new Remedio[vetorSeparado.length];
		Remedio[] dadosRemedios = dados.getRemedios();
		for(int i = 0; i < vetorSeparado.length; i++) {
			int indiceRemedio = Integer.parseInt(vetorSeparado[i]);
			remedios[i] = dadosRemedios[indiceRemedio];
		}
		return remedios;
	}
	
	public int transformarDiaSemana(String dia) {
		String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
		for(int i = 0; i < dias.length; i++) {
			if(dia == dias[i]) {
				return i;
			}
		}
		return 0;
	}
	
	public String transformarDiaSemana(int dia) {
		String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
		for(int i = 0; i < dias.length; i++) {
			if(dia == i) {
				return dias[i];
			}
		}
		return null;
	}
	
}
