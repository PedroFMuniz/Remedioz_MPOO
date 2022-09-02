package controle;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import modelo.*;

public class controleDados {

	private Dados dados = new Dados();

	// Construtor
	public controleDados() {
		dados.preencherDados();
	}

	// Gets e sets

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
		if (!dadosPaciente[2].matches("\\d{10,11}") || !dadosPaciente[2].matches("\\.+\\@\\w+\\.\\w(2,3)\\.\\w(2,3)")) {
			return false;
		} else {
			Remedio[] alergias = separarVetorRemedio(dadosPaciente[4]);
			Paciente p = new Paciente(Integer.parseInt(dadosPaciente[0]), dadosPaciente[1], dadosPaciente[2],
					dadosPaciente[3], alergias, dadosPaciente[5]);
			dados.inserirOuEditarPaciente(p, p.getId());
			return true;
		}
	}

	public Boolean inserirEditarMedico(String[] dadosMedico) {
		if (!dadosMedico[2].matches("\\d{10,11}") || !dadosMedico[2].matches("\\.+\\@\\w+\\.\\w(2,3)\\.\\w(2,3)")) {
			return false;
		} else {
			Medico m = new Medico(Integer.parseInt(dadosMedico[0]), dadosMedico[1], dadosMedico[2], dadosMedico[3],
					dadosMedico[4], dadosMedico[5]);
			dados.inserirOuEditarMedico(m, m.getId());
			return true;
		}
	}

	public Boolean inserirEditarRemedio(String[] dadosRemedio) {
		Remedio r = new Remedio(Integer.parseInt(dadosRemedio[0]), dadosRemedio[1], dadosRemedio[2], dadosRemedio[3],
				dadosRemedio[4]);
		dados.inserirOuEditarRemedio(r, r.getId());
		return true;
	}

	public Boolean inserirEditarAgendamento(String[] dadosAgendamento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld = LocalDate.now();
		Medico[] medicos = dados.getMedicos();
		Paciente[] pacientes = dados.getPacientes();
		Agendamento a = new Agendamento(Integer.parseInt(dadosAgendamento[0]),
				LocalDate.parse(dadosAgendamento[1], formatter), LocalDate.parse(dadosAgendamento[2], formatter),
				medicos[Integer.parseInt(dadosAgendamento[3])], pacientes[Integer.parseInt(dadosAgendamento[4])],
				separarVetorRemedio(dadosAgendamento[5]), separarVetorDiaDaSemana(dadosAgendamento[6]));
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
			for (int j = 0; j < vetorSeparado.length; j++) {
				String[] dadosDia = vetorSeparado[j].split(",");
				if (transformarDiaSemana(dadosDia[0]) == i) {
					horarios[indice] = LocalTime.parse(dadosDia[1], formatter);
					indice++;
				}
			}
			DiaDaSemana d = new DiaDaSemana(i, transformarDiaSemana(i), horarios);
			dias[i] = d;
		}
		return dias;
	}

	public Boolean removerAgendamento(int posicao) {
		int qtdAgendamentos = dados.getQtdeAgendamentos();
		if (posicao == qtdAgendamentos - 1) {
			dados.getAgendamentos()[posicao] = null;
			dados.setQtdeAgendamentos(qtdAgendamentos - 1);
			return true;
		} else {
			int cont = 0;
			while (dados.getAgendamentos()[cont].getId() != dados.getAgendamentos()[posicao].getId()) {
				cont++;
			}
			for (int i = cont; i < qtdAgendamentos; i++) {
				dados.getAgendamentos()[i] = null;
				dados.getAgendamentos()[i] = dados.getAgendamentos()[i + 1];
			}
			dados.getAgendamentos()[qtdAgendamentos] = null;
			dados.setQtdeAgendamentos(qtdAgendamentos - 1);
			return true;
		}
	}

	public Boolean removerPaciente(int posicao) {
		int qtdPacientes = dados.getQtdePacientes();
		if (verificarRelacao(posicao, 1)) {
			return false;
		} else {
			if (posicao == qtdPacientes - 1) {
				dados.getPacientes()[posicao] = null;
				dados.setQtdePacientes(qtdPacientes - 1);
				return true;
			} else {
				int cont = 0;
				while (dados.getPacientes()[cont].getId() != dados.getPacientes()[posicao].getId()) {
					cont++;
				}
				for (int i = cont; i < qtdPacientes; i++) {
					dados.getPacientes()[i] = null;
					dados.getPacientes()[i] = dados.getPacientes()[i + 1];
				}
				dados.getPacientes()[qtdPacientes] = null;
				dados.setQtdePacientes(qtdPacientes - 1);
				return true;
			}
		}
	}

	public Boolean removerRemedio(int posicao) {
		int qtdRemedios = dados.getQtdeRemedios();
		if (verificarRelacao(posicao, 1) && verificarRelacao(posicao, 2)) {
			return false;
		} else {
			if (posicao == qtdRemedios - 1) {
				dados.getRemedios()[posicao] = null;
				dados.setQtdeRemedios(qtdRemedios - 1);
				return true;
			} else {
				int cont = 0;
				while (dados.getRemedios()[cont].getId() != dados.getRemedios()[posicao].getId()) {
					cont++;
				}
				for (int i = cont; i < qtdRemedios; i++) {
					dados.getRemedios()[i] = null;
					dados.getRemedios()[i] = dados.getRemedios()[i + 1];
				}
				dados.getRemedios()[qtdRemedios] = null;
				dados.setQtdeRemedios(qtdRemedios - 1);
				return true;
			}
		}
	}

	public Boolean removerMedico(int posicao) {
		int qtdMedicos = dados.getQtdeMedicos();
		if (verificarRelacao(posicao, 1)) {
			return false;
		} else {
			if (posicao == qtdMedicos - 1) {
				dados.getMedicos()[posicao] = null;
				dados.setQtdeMedicos(qtdMedicos - 1);
				return true;
			} else {
				int cont = 0;
				while (dados.getMedicos()[cont].getId() != dados.getMedicos()[posicao].getId()) {
					cont++;
				}
				for (int i = cont; i < qtdMedicos; i++) {
					dados.getMedicos()[i] = null;
					dados.getMedicos()[i] = dados.getMedicos()[i + 1];
				}
				dados.getMedicos()[qtdMedicos] = null;
				dados.setQtdeMedicos(qtdMedicos - 1);
				return true;
			}
		}
	}
	
	public Boolean verificarRelacao(int posicao, int op) {
		Boolean verificador = false;
		switch (op) {
		case 1: {
			int qtdAgendamentos = dados.getQtdeAgendamentos();
			for (int i = 0; i < qtdAgendamentos; i++) {
				if (dados.getAgendamentos()[i].getPaciente() == dados.getPacientes()[posicao]) {
					verificador = true;
				}
			}
		}
		case 2: {
			int qtdPacientes = dados.getQtdePacientes();
			for (int i = 0; i < qtdPacientes; i++) {
				for (int j = 0; j < dados.getPacientes()[i].getAlergias().length; j++) {
					if (dados.getPacientes()[i].getAlergias()[j] == dados.getRemedios()[posicao]) {
						verificador = true;
					}
				}
			}
		}
		default: {
			verificador = false;
		}
		}
		return verificador;
	}

	public Remedio[] separarVetorRemedio(String vetor) {
		String[] vetorSeparado = vetor.split("_");
		Remedio[] remedios = new Remedio[vetorSeparado.length];
		Remedio[] dadosRemedios = dados.getRemedios();
		for (int i = 0; i < vetorSeparado.length; i++) {
			int indiceRemedio = Integer.parseInt(vetorSeparado[i]);
			remedios[i] = dadosRemedios[indiceRemedio];
		}
		return remedios;
	}

	public int transformarDiaSemana(String dia) {
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		for (int i = 0; i < dias.length; i++) {
			if (dia == dias[i]) {
				return i;
			}
		}
		return 0;
	}

	public String transformarDiaSemana(int dia) {
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		for (int i = 0; i < dias.length; i++) {
			if (dia == i) {
				return dias[i];
			}
		}
		return null;
	}

}
