package controle;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import modelo.*;

/**
 * Classe "ControleDados". Manipula os arrays de dados armazenados na classe
 * "Dados" do pacote "modelo". Possui metodos de insercao e exclusao de dados,
 * alem de metodos auxiliares.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */

public class ControleDados {

	// Declaração da instância de dados

	private Dados dados = new Dados();

	// Construtor
	/**
	 * Construtor responsavel por preencher os dados que serao manipulados
	 */
	public ControleDados() {
		dados.preencherDados();
	}

	// Gets e sets

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

	public int getQtdPacientes() {
		return dados.getQtdePacientes();
	}

	public void setQtdPacientes(int qtd) {
		dados.setQtdePacientes(qtd);
	}

	public int getQtdMedicos() {
		return dados.getQtdeMedicos();
	}

	public void setQtdMedicos(int qtd) {
		dados.setQtdeMedicos(qtd);
	}

	public int getQtdRemedios() {
		return dados.getQtdeRemedios();
	}

	public void setQtdRemedios(int qtd) {
		dados.setQtdeRemedios(qtd);
	}

	public int getQtdAgendamentos() {
		return dados.getQtdeAgendamentos();
	}

	public void setQtdAgendamentos(int qtd) {
		dados.setQtdeAgendamentos(qtd);
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

	// Métodos de inserção/edição

	/**
	 * Metodo responsavel por inserir ou editar um paciente. Verifica se os dados de
	 * telefone e email estao na formatacao correta e cadastra os novos dados na
	 * base de dados
	 * 
	 * Se utiliza do metodo "separarVetorRemedio" para obter o array de alergias do
	 * paciente
	 * 
	 * @param Array de strings informando os dados a serem cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */
	public boolean inserirEditarPaciente(String[] dadosPaciente) {
		if (verificarTelEmail(dadosPaciente[2],dadosPaciente[3])) {
			Remedio[] alergias = separarVetorRemedio(dadosPaciente[4]);
			Paciente p = new Paciente(Integer.parseInt(dadosPaciente[0]), dadosPaciente[1], dadosPaciente[2],
					dadosPaciente[3], alergias, dadosPaciente[5]);
			dados.inserirOuEditarPaciente(p, p.getId());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Metodo responsavel por inserir ou editar um medico. Verifica se os dados de
	 * telefone e email estao na formatacao correta e cadastra os novos dados na
	 * base de dados
	 * 
	 * @param Array de strings informando os dados a serem cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */
	public boolean inserirEditarMedico(String[] dadosMedico) {
		if (verificarTelEmail(dadosMedico[2], dadosMedico[3])) {
			Medico m = new Medico(Integer.parseInt(dadosMedico[0]), dadosMedico[1], dadosMedico[2], dadosMedico[3],
					dadosMedico[4], dadosMedico[5]);
			dados.inserirOuEditarMedico(m, m.getId());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo responsavel por inserir ou editar um remedio. Cadastra os novos dados
	 * na base de dados
	 * 
	 * @param Array de strings informando os dados a serem cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */
	public boolean inserirEditarRemedio(String[] dadosRemedio) {
		Remedio r = new Remedio(Integer.parseInt(dadosRemedio[0]), dadosRemedio[1], dadosRemedio[2], dadosRemedio[3],
				dadosRemedio[4]);
		dados.inserirOuEditarRemedio(r, r.getId());
		return true;
	}

	/**
	 * Metodo responsavel por inserir ou editar um agendamento. Cadastra os novos
	 * dados na base de dados
	 * 
	 * Se utiliza dos metodos "separarVetorRemedio" e "separarVetorDiaDaSemana" para
	 * obter os vetores de remedios e dias da semana respectivamente
	 * 
	 * @param Array de strings informando os dados a serem cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */

	public boolean inserirEditarAgendamento(String[] dadosAgendamento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Medico[] medicos = dados.getMedicos();
		Paciente[] pacientes = dados.getPacientes();
		Remedio[] remedios = dados.getRemedios();
		Agendamento a = new Agendamento(Integer.parseInt(dadosAgendamento[0]),
				LocalDate.parse(dadosAgendamento[1], formatter), LocalDate.parse(dadosAgendamento[2], formatter),
				medicos[Integer.parseInt(dadosAgendamento[3])], pacientes[Integer.parseInt(dadosAgendamento[4])],
				remedios[Integer.parseInt(dadosAgendamento[5])], separarVetorDiaDaSemana(dadosAgendamento[6]));
		dados.inserirOuEditarAgendamento(a, a.getId());
		return true;
	}

	public boolean manipularHorarioAgendamento(int idAgendamento, String dia, LocalTime hora) {
		Agendamento[] agendamentos = dados.getAgendamentos();
		DiaDaSemana[] dias = new DiaDaSemana[7];
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i] != null && agendamentos[i].getId() == idAgendamento) {
				dias = agendamentos[i].getDiasDaSemana();
				int cont = 0;
				if (dias[transformarDiaSemana(dia)] != null) {
					while (dias[transformarDiaSemana(dia)].getHorario()[cont] != null) {
						cont++;
					}
					dias[transformarDiaSemana(dia)].getHorario()[cont] = hora;
				} else {
					LocalTime[] ld = new LocalTime[40];
					ld[0] = hora;
					DiaDaSemana d = new DiaDaSemana(dia, ld);
					dias[transformarDiaSemana(dia)] = d;
				}

				dados.getAgendamentos()[i].setDiasDaSemana(dias);
				return true;
			}
		}
		return false;
	}

	public boolean manipularHorarioAgendamento(int idAgendamento, String dia, LocalTime horaAntiga,
			LocalTime horaNova) {
		Agendamento[] agendamentos = dados.getAgendamentos();
		DiaDaSemana[] dias = new DiaDaSemana[7];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i] != null && agendamentos[i].getId() == idAgendamento) {
				dias = agendamentos[i].getDiasDaSemana();
				for (int j = 0; j < dias.length; j++) {
					if (dias[j] != null && dias[j].getDiaSemana() == dia) {
						for (int k = 0; k < dias[j].getHorario().length; k++) {
							if (dias[j].getHorario()[k] != null && dias[j].getHorario()[k].equals(horaAntiga)) {
								dias[j].getHorario()[k] = horaNova;
								dados.getAgendamentos()[i].setDiasDaSemana(dias);
								return true;
							}
						}
					} else if (j == dias.length - 1) {
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean inserirAlergiasPaciente(int idPaciente, Remedio remedio) {
		for (int i = 0; i < dados.getQtdePacientes(); i++) {
			if (dados.getPacientes()[i] != null && dados.getPacientes()[i].getId() == idPaciente) {
				int cont = 0;
				while (dados.getPacientes()[i].getAlergias()[cont] != null) {
					cont++;
				}
				dados.getPacientes()[i].getAlergias()[cont] = remedio;
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// Métodos de exlusão

	/**
	 * Metodo responsavel por retirar um cadastro de agendamento da base de dados.
	 * Verifica se o mesmo se encontra na ultima posicao do array de dados. Em caso
	 * positivo, apenas retira o cadastro e diminui a quantidade de agendamentos. Em
	 * caso negativo, se utiliza de uma rotina swap para reorganizar o array e por
	 * fim diminui a quantidade de agendamentos
	 * 
	 * @param int informando a posicao do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerAgendamento(int id) {
		int qtdAgendamentos = dados.getQtdeAgendamentos();
		int cont = 0;
		while (dados.getAgendamentos()[cont].getId() != id) {
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

	/**
	 * Metodo responsavel por retirar um cadastro de paciente da base de dados.
	 * Verifica se o mesmo se encontra na ultima posicao do array de dados. Em caso
	 * positivo, apenas retira o cadastro e diminui a quantidade de pacientes. Em
	 * caso negativo, se utiliza de uma rotina swap para reorganizar o array e por
	 * fim diminui a quantidade de pacientes
	 * 
	 * Se utiliza do metodo "verificarRelacao" para verificar se o paciente possui
	 * agendamentos relacionados. Em caso positivo, a exclusao nao sera realizada
	 * 
	 * @param int informando a posicao do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerPaciente(int id) {
		int qtdPacientes = dados.getQtdePacientes();
		if (verificarRelacao(id, 1)) {
			return false;
		} else {
			int cont = 0;
			while (dados.getPacientes()[cont].getId() != id) {
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

	/**
	 * Metodo responsavel por retirar um cadastro de remedio da base de dados.
	 * Verifica se o mesmo se encontra na ultima posicao do array de dados. Em caso
	 * positivo, apenas retira o cadastro e diminui a quantidade de remedios. Em
	 * caso negativo, se utiliza de uma rotina swap para reorganizar o array e por
	 * fim diminui a quantidade de remedios
	 * 
	 * Se utiliza do metodo "verificarRelacao" para verificar se o remedio possui
	 * agendamentos relacionados ou pacientes relacionados. Em caso positivo, a
	 * exclusao nao sera realizada
	 * 
	 * @param int informando a posicao do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerRemedio(int id) {
		int qtdRemedios = dados.getQtdeRemedios();
		if (verificarRelacao(id, 1) && verificarRelacao(id, 2)) {
			return false;
		} else {
			int cont = 0;
			while (dados.getRemedios()[cont].getId() != id) {
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

	/**
	 * Metodo responsavel por retirar um cadastro de medico da base de dados.
	 * Verifica se o mesmo se encontra na ultima posicao do array de dados. Em caso
	 * positivo, apenas retira o cadastro e diminui a quantidade de medicos. Em caso
	 * negativo, se utiliza de uma rotina swap para reorganizar o array e por fim
	 * diminui a quantidade de medicos
	 * 
	 * Se utiliza do metodo "verificarRelacao" para verificar se o medico possui
	 * agendamentos relacionados. Em caso positivo, a exclusao nao sera realizada
	 * 
	 * @param int informando a posicao do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerMedico(int id) {
		int qtdMedicos = dados.getQtdeMedicos();
		if (verificarRelacao(id, 3)) {
			return false;
		} else {
			int cont = 0;
			while (dados.getMedicos()[cont].getId() != id) {
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


	public boolean removerHorarioAgendamento(int idAgendamento, String dia, LocalTime hora) {
		Agendamento[] agendamentos = dados.getAgendamentos();
		DiaDaSemana[] dias = new DiaDaSemana[7];
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i].getId() == idAgendamento) {
				dias = agendamentos[i].getDiasDaSemana();
				for (int j = 0; j < dias.length; j++) {
					if (dias[j].getDiaSemana() == dia) {
						for (int k = 0; k < dias[j].getHorario().length; k++) {
							if (dias[j].getHorario()[k] == hora) {
								if (k == dias[j].getHorario().length - 1) {
									dias[j].getHorario()[k] = null;
									return true;
								} else {
									for (int l = k; l < dias[j].getHorario().length; l++) {
										dias[j].getHorario()[l] = null;
										dias[j].getHorario()[l] = dias[j].getHorario()[l + 1];
									}
									dados.getPacientes()[dias[j].getHorario().length - 1] = null;
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean removerAlergiaPaciente(int idPaciente, Remedio remedio) {
		Paciente paciente = new Paciente();
		for (int i = 0; i < dados.getQtdePacientes(); i++) {
			if (dados.getPacientes()[i].getId() == idPaciente) {
				paciente = dados.getPacientes()[i];
			}
		}
		int cont = 0;
		while (paciente.getAlergias()[cont] != remedio) {
			cont++;
		}
		for (int i = cont; i < paciente.getAlergias().length; i++) {
			paciente.getAlergias()[i] = null;
			paciente.getAlergias()[i] = paciente.getAlergias()[i + 1];
		}
		paciente.getAlergias()[paciente.getAlergias().length] = null;
		return true;
	}

	/**
	 * Metodo responsavel por verificar se um determinado paciente, medico ou
	 * remedio possui alguma relacao com outro paciente ou agendamento
	 * 
	 * @param int informando a posicao do paciente, medico ou remedio
	 * @param int informando se deve-se buscar na base de pacientes, medicos ou
	 *            remedios
	 * @return boolean informando se possui ou nao relacoes
	 */
	public boolean verificarRelacao(int id, int op) {
		boolean verificador = false;
		switch (op) {
		case 1: {
			int qtdAgendamentos = dados.getQtdeAgendamentos();
			for (int i = 0; i < qtdAgendamentos; i++) {
				if (dados.getAgendamentos()[i].getPaciente().getId() == id) {
					verificador = true;
				}
			}
		}
		case 2: {
			int qtdPacientes = dados.getQtdePacientes();
			for (int i = 0; i < qtdPacientes; i++) {
				for (int j = 0; j < dados.getPacientes()[i].getAlergias().length; j++) {
					if (dados.getPacientes()[i].getAlergias()[j] != null && dados.getPacientes()[i].getAlergias()[j].getId() == id) {
						verificador = true;
					}
				}
			}
		}
		case 3: {
			int qtdAgendamentos = dados.getQtdeAgendamentos();
			for (int i = 0; i < qtdAgendamentos; i++) {
				if (dados.getAgendamentos()[i].getMedico().getId() == id) {
					verificador = true;
				}
			}
		}
		default: {
			verificador = false;
		}
		}
		return verificador;
	}

	/**
	 * Metodo responsavel por separar os dados referentes aos dias e horarios
	 * correspondentes a um agendamento. Formata os horarios e os separa nos sete
	 * dias da semana de acordo com os dados fornecidos
	 * 
	 * Se utiliza do metodo "transformarDiaSemana" para obter o indice ou a
	 * nomenclatura de uma dia da semana
	 * 
	 * @param String contendo os dados dos dias da semana com seus horarios
	 * @return Array de dias da semana provenientes dos dados informados
	 */
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
			DiaDaSemana d = new DiaDaSemana(transformarDiaSemana(i), horarios);
			dias[i] = d;
		}
		return dias;
	}

	/**
	 * Metodo responsavel por separar os dados referentes aos remedios de um
	 * agendamento ou alergias de um paciente. Separa o vetor de strings de dados em
	 * remedios em um vetor de retorno.
	 * 
	 * @param String contendo os ids dos remedios
	 * @return Array de remedios a partir dos dados fornecidos
	 */
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

	/**
	 * Metodo responsavel por transformar um dia da semana. Transforma o nome do dia
	 * em um int representando seu indice
	 * 
	 * @param String contendo o nome do dia
	 * @return int representando o indice do dia
	 */
	public int transformarDiaSemana(String dia) {
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		for (int i = 0; i < dias.length; i++) {
			if (dia == dias[i]) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Metodo responsavel por transformar um dia da semana. Transforma o indice do
	 * dia em seu nome
	 * 
	 * @param int representando o indice do dia
	 * @return String contendo o nome do dia
	 */
	public String transformarDiaSemana(int dia) {
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		for (int i = 0; i < dias.length; i++) {
			if (dia == i) {
				return dias[i];
			}
		}
		return null;
	}
	
	public boolean verificarTelEmail(String tel, String email) {
		return (tel.matches("\\d{10,11}") && email.matches("^(.+)@(.+)$"));
	}

}
