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

	public int getUltimoIdPacientes() {
		return dados.getUltimoIdPacientes();
	}

	public void setUltimoIdPacientes(int id) {
		dados.setUltimoIdPacientes(id);
	}

	public int getQtdMedicos() {
		return dados.getQtdeMedicos();
	}

	public void setQtdMedicos(int qtd) {
		dados.setQtdeMedicos(qtd);
	}

	public int getUltimoIdMedicos() {
		return dados.getUltimoIdMedicos();
	}

	public void setUltimoIdMedicos(int id) {
		dados.setUltimoIdMedicos(id);
	}

	public int getQtdRemedios() {
		return dados.getQtdeRemedios();
	}

	public void setQtdRemedios(int qtd) {
		dados.setQtdeRemedios(qtd);
	}

	public int getUltimoIdRemedios() {
		return dados.getUltimoIdRemedios();
	}

	public void setUltimoIdRemedios(int id) {
		dados.setUltimoIdRemedios(id);
	}

	public int getQtdAgendamentos() {
		return dados.getQtdeAgendamentos();
	}

	public void setQtdAgendamentos(int qtd) {
		dados.setQtdeAgendamentos(qtd);
	}

	public int getUltimoIdAgendamentos() {
		return dados.getUltimoIdAgendamentos();
	}

	public void setUltimoIdAgendamentos(int id) {
		dados.setUltimoIdAgendamentos(id);
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
	 * @param dadosPaciente Array de strings informando os dados a serem
	 *                      cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */
	public boolean inserirEditarPaciente(String[] dadosPaciente) {
		// Verifica a formatacao de telefone e email
		if (verificarTelEmail(dadosPaciente[2], dadosPaciente[3])) {
			// Inicia o paciente com o vetor de alergias nulo
			Paciente p = new Paciente(Integer.parseInt(dadosPaciente[0]), dadosPaciente[1], dadosPaciente[2],
					dadosPaciente[3], new Remedio[40], dadosPaciente[4]);
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
	 * @param dadosMedico Array de strings informando os dados a serem cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */
	public boolean inserirEditarMedico(String[] dadosMedico) {
		// Verifica a formatacao de telefone e email
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
	 * @param dadosRemedio Array de strings informando os dados a serem
	 *                     cadastrados
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
	 * 
	 * @param dadosAgendamento Array de strings informando os dados a serem
	 *                         cadastrados
	 * @return boolean informando se foi possivel ou nao o cadastro
	 */

	public boolean inserirEditarAgendamento(String[] dadosAgendamento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Medico[] medicos = dados.getMedicos();
		Paciente[] pacientes = dados.getPacientes();
		Remedio[] remedios = dados.getRemedios();
		// Inicia o agendamento com o vetor de dias da semana nulo
		Agendamento a = new Agendamento(Integer.parseInt(dadosAgendamento[0]),
				LocalDate.parse(dadosAgendamento[1], formatter), LocalDate.parse(dadosAgendamento[2], formatter),
				medicos[Integer.parseInt(dadosAgendamento[3])], pacientes[Integer.parseInt(dadosAgendamento[4])],
				remedios[Integer.parseInt(dadosAgendamento[5])], new DiaDaSemana[7]);
		dados.inserirOuEditarAgendamento(a, a.getId());
		return true;

	}

	/**
	 * Metodo responsavel por inserir novos horarios em um determinado agendamento.
	 * Verifica ate que ponto o vetor de horarios de um dia esta preenchido e
	 * adiciona o novo dado na primeira posicao nula. Tambem verifica se o
	 * agendamento ja possui um horario identico. Em caso negativo cadastra o novo
	 * horario.
	 * 
	 * @param idAgendamento int indicando qual agendamento deve ser editado
	 * @param dia String indicando o nome do dia da semana a ser
	 *                      adicionado
	 * @param hora LocalTime indicando o horario a ser adicionado
	 * @return boolean indicando se a operacao foi realizada com sucesso
	 */
	public boolean inserirHorarioAgendamento(int idAgendamento, String dia, int horario, int minutos) {
		LocalTime hora = LocalTime.of(horario, minutos);
		Agendamento[] agendamentos = dados.getAgendamentos();
		DiaDaSemana[] dias = new DiaDaSemana[7];
		for (int i = 0; i < agendamentos.length; i++) {
			// Verifica qual agendamento esta sendo editado
			if (agendamentos[i] != null && agendamentos[i].getId() == idAgendamento) {
				dias = agendamentos[i].getDiasDaSemana();
				int cont = 0;
				// Verifica se existe algum horario cadastrado neste dia da semana
				if (dias[transformarDiaSemana(dia)] != null) {
					// Rotina para verificar se já existe um registro igual e inserir o horario na
					// primeira posicao nula
					while (dias[transformarDiaSemana(dia)].getHorario()[cont] != null) {
						if (dias[transformarDiaSemana(dia)].getHorario()[cont].equals(hora)) {
							return false;
						}
						cont++;
					}
					dias[transformarDiaSemana(dia)].getHorario()[cont] = hora;
				} else {
					// Cria um novo dia da semana caso a posicao desejada seja nula
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

	/**
	 * Metodo responsavel por inserir novas alergias em um determinado paciente.
	 * Verifica se um determinado remedio ja nao esta cadastrado como alergia no
	 * paciente indicado, em caso negativo cadastra a nova alergia
	 * 
	 * 
	 * @param idPaciente int indicando o paciente que deve ser editado
	 * @param remedio int indicando o remedio a ser adicionado como alergia
	 * @return boolean indicando se a operacao teve sucesso
	 */
	public boolean inserirAlergiasPaciente(int idPaciente, int remedio) {
		Remedio alergia = new Remedio();
		for (int i = 0; i < dados.getQtdeRemedios(); i++) {
			// Verifica qual remedio esta sendo adicionado como alergia
			if (dados.getRemedios()[i].getId() == remedio) {
				alergia = dados.getRemedios()[i];
				break;
			}
		}
		for (int i = 0; i < dados.getQtdePacientes(); i++) {
			// Identifica qual paciente esta sendo editado
			if (dados.getPacientes()[i].getId() == idPaciente) {
				// Rotina para verificar se ja existe um registro igual
				for (int j = 0; j < dados.getPacientes()[i].getAlergias().length; j++) {
					if (dados.getPacientes()[i].getAlergias()[j] != null
							&& dados.getPacientes()[i].getAlergias()[j] == alergia) {
						return false;
					}
				}
				int cont = 0;
				// Rotina para incluir a alergia na primeira posicao nula
				while (dados.getPacientes()[i].getAlergias()[cont] != null) {
					cont++;
				}
				dados.getPacientes()[i].getAlergias()[cont] = alergia;
				return true;
			}
		}
		return false;
	}

	// Métodos de exclusão

	/**
	 * Metodo responsavel por retirar um cadastro de agendamento da base de dados.
	 * Se utiliza de uma rotina swap para reorganizar o array e por fim diminui a
	 * quantidade de agendamentos.
	 * 
	 * @param id int informando o id do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerAgendamento(int id) {
		int qtdAgendamentos = dados.getQtdeAgendamentos();
		int cont = 0;
		// Rotina para verificar a posição do registro
		while (dados.getAgendamentos()[cont].getId() != id) {
			cont++;
		}
		// Rotina para reorganizar o vetor
		for (int i = cont; i < qtdAgendamentos; i++) {
			dados.getAgendamentos()[i] = null;
			dados.getAgendamentos()[i] = dados.getAgendamentos()[i + 1];
		}
		dados.getAgendamentos()[qtdAgendamentos] = null;
		dados.setQtdeAgendamentos(qtdAgendamentos - 1);
		return true;
	}

	/**
	 * Metodo responsavel por retirar um cadastro de paciente da base de dados. Se
	 * utiliza de uma rotina swap para reorganizar o array e por fim diminui a
	 * quantidade de pacientes
	 * 
	 * Se utiliza do metodo "verificarRelacao" para verificar se o paciente possui
	 * agendamentos relacionados. Em caso positivo, a exclusao nao sera realizada
	 * 
	 * @param id int informando o id do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerPaciente(int id) {
		int qtdPacientes = dados.getQtdePacientes();
		if (verificarRelacao(id, 1)) {
			return false;
		} else {
			int cont = 0;
			// Rotina para verificar a posição do registro
			while (dados.getPacientes()[cont].getId() != id) {
				cont++;
			}
			// Rotina para reorganizar o vetor
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
	 * Metodo responsavel por retirar um cadastro de remedio da base de dados. Se
	 * utiliza de uma rotina swap para reorganizar o array e por fim diminui a
	 * quantidade de remedios
	 * 
	 * Se utiliza do metodo "verificarRelacao" para verificar se o remedio possui
	 * agendamentos relacionados ou pacientes relacionados. Em caso positivo, a
	 * exclusao nao sera realizada
	 * 
	 * @param id int informando o id do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerRemedio(int id) {
		int qtdRemedios = dados.getQtdeRemedios();
		if (verificarRelacao(id, 4) || verificarRelacao(id, 2)) {
			return false;
		} else {
			int cont = 0;
			// Rotina para verificar a posição do registro
			while (dados.getRemedios()[cont].getId() != id) {
				cont++;
			}
			// Rotina para reorganizar o vetor
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
	 * Metodo responsavel por retirar um cadastro de medico da base de dados. Se
	 * utiliza de uma rotina swap para reorganizar o array e por fim diminui a
	 * quantidade de medicos
	 * 
	 * Se utiliza do metodo "verificarRelacao" para verificar se o medico possui
	 * agendamentos relacionados. Em caso positivo, a exclusao nao sera realizada
	 * 
	 * @param id int informando o id do cadastro a ser deletado
	 * @return boolean informando se foi possivel ou nao a exclusao
	 */
	public boolean removerMedico(int id) {
		int qtdMedicos = dados.getQtdeMedicos();
		if (verificarRelacao(id, 3)) {
			return false;
		} else {
			int cont = 0;
			// Rotina para verificar a posição do registro
			while (dados.getMedicos()[cont].getId() != id) {
				cont++;
			}
			// Rotina para reorganizar o vetor
			for (int i = cont; i < qtdMedicos; i++) {
				dados.getMedicos()[i] = null;
				dados.getMedicos()[i] = dados.getMedicos()[i + 1];
			}
			dados.getMedicos()[qtdMedicos] = null;
			dados.setQtdeMedicos(qtdMedicos - 1);
			return true;
		}
	}

	/**
	 * Metodo responsavel por retirar um horario de um agendamento indicado,
	 * verifica se existe um cadastro com o dia e horario indicados no agendamento
	 * desejado. Em caso positivo, exclui o registro
	 * 
	 * 
	 * @param idAgendamento int indicando o agendamento a ser editado
	 * @param dia String indicando o dia da semana a ser editado
	 * @param hora LocalTime indicando o horario a ser deletado
	 * @return boolean indicando o sucesso da operacao
	 */
	public boolean removerHorarioAgendamento(int idAgendamento, String dia, String horario) {
		LocalTime hora = LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
		Agendamento[] agendamentos = dados.getAgendamentos();
		for (int i = 0; i < dados.getQtdeAgendamentos(); i++) {
			// Verifica qual agendamento sera editado
			if (agendamentos[i].getId() == idAgendamento) {
				DiaDaSemana[] dias = agendamentos[i].getDiasDaSemana();
				for (int j = 0; j < 7; j++) {
					// Verifica o dia que sera editado
					if (dias[j] != null && dias[j].getDiaSemana().equals(dia)) {
						int cont = 0;
						// Rotina para verificar a posição do registro
						while (!dias[j].getHorario()[cont].equals(hora)) {
							cont++;
						}
						// Verifica se o horario a ser deletado é o unico do dia, em caso positivo
						// transforma o dia em null
						if (cont == 1) {
							dados.getAgendamentos()[i].getDiasDaSemana()[j] = null;
						} else {
							// Rotina para reorganizar o vetor
							for (int l = cont; l < dias[j].getHorario().length - 1; l++) {
								dados.getAgendamentos()[i].getDiasDaSemana()[j].getHorario()[l] = null;
								dados.getAgendamentos()[i].getDiasDaSemana()[j].getHorario()[l] = dias[j].getHorario()[l
										+ 1];
							}
							dados.getAgendamentos()[i].getDiasDaSemana()[j]
									.getHorario()[dados.getAgendamentos()[i].getDiasDaSemana()[j].getHorario().length
											- 1] = null;
						}

						return true;

					}
				}
			}
		}
		return false;
	}

	/**
	 * Metodo responsavel por remover um remedio da lista de alergias de um paciente
	 * indicado, verifica se o remedio esta nas alergias deste paciente. Em caso
	 * positovo, deleta o registro
	 * 
	 * 
	 * @param idPaciente int indicando o paciente a ser editado
	 * @param remedio int indicando o remedio a ser excluido
	 * @return boolean indicando o sucesso da operacao
	 */
	public boolean removerAlergiaPaciente(int idPaciente, int remedio) {
		Remedio alergia = new Remedio();
		// Rotina para obter o remedio que sera deletado
		for (int i = 0; i < dados.getQtdeRemedios(); i++) {
			if (dados.getRemedios()[i].getId() == remedio) {
				alergia = dados.getRemedios()[i];
			}
		}
		Paciente paciente = new Paciente();
		// Rotina para identificar o paciente que sera editado
		for (int i = 0; i < dados.getQtdePacientes(); i++) {
			if (dados.getPacientes()[i].getId() == idPaciente) {
				paciente = dados.getPacientes()[i];
			}
		}
		int cont = 0;
		// Rotina para verificar a posição do registro
		while (paciente.getAlergias()[cont].getId() != alergia.getId()) {
			cont++;
			// Caso nao possua alergias
			if (cont == paciente.getAlergias().length) {
				return false;
			}
		}
		// Rotina para reorganizar o vetor
		for (int i = cont; i < paciente.getAlergias().length - 1; i++) {
			paciente.getAlergias()[i] = null;
			paciente.getAlergias()[i] = paciente.getAlergias()[i + 1];
		}
		paciente.getAlergias()[paciente.getAlergias().length - 1] = null;
		return true;
	}

	/**
	 * Metodo responsavel por verificar se um determinado paciente, medico ou
	 * remedio possui alguma relacao com outro paciente ou agendamento
	 * 
	 * @param id int informando a posicao do paciente, medico ou remedio
	 * @param op int informando se deve-se buscar na base de pacientes, medicos ou
	 *           remedios
	 * @return boolean informando se possui ou nao relacoes
	 */
	public boolean verificarRelacao(int id, int op) {
		switch (op) {
		case 1: {
			int qtdAgendamentos = dados.getQtdeAgendamentos();
			// Rotina para verificar a relacao
			for (int i = 0; i < qtdAgendamentos; i++) {
				if (dados.getAgendamentos()[i].getPaciente().getId() == id) {
					return true;
				}
			}
			return false;
		}
		case 2: {
			int qtdPacientes = dados.getQtdePacientes();
			// Rotina para verificar a relacao
			for (int i = 0; i < qtdPacientes; i++) {
				for (int j = 0; j < dados.getPacientes()[i].getAlergias().length; j++) {
					if (dados.getPacientes()[i].getAlergias()[j] != null
							&& dados.getPacientes()[i].getAlergias()[j].getId() == id) {
						return true;
					}
				}
			}
			return false;
		}
		case 3: {
			int qtdAgendamentos = dados.getQtdeAgendamentos();
			// Rotina para verificar a relacao
			for (int i = 0; i < qtdAgendamentos; i++) {
				if (dados.getAgendamentos()[i].getMedico().getId() == id) {
					return true;
				}
			}
			return false;
		}
		case 4 :{
			int qtdAgendamentos = dados.getQtdeAgendamentos();
			// Rotina para verificar a relacao
			for (int i = 0; i < qtdAgendamentos; i++) {
				if (dados.getAgendamentos()[i].getRemedio().getId() == id) {
					return true;
				}
			}
			return false;
		}
		default: {
			return false;
		}
		}
	}

	/**
	 * Metodo responsavel por transformar um dia da semana. Transforma o nome do dia
	 * em um int representando seu indice
	 * 
	 * @param dia String contendo o nome do dia
	 * @return int representando o indice do dia
	 */
	public int transformarDiaSemana(String dia) {
		String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" };
		for (int i = 0; i < dias.length; i++) {
			if (dia.equals(dias[i])) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Metodo responsavel por transformar um dia da semana. Transforma o indice do
	 * dia em seu nome
	 * 
	 * @param dia int representando o indice do dia
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

	/**
	 * Metodo responsavel por validar a formatacao de telefone e email para cadastro
	 * de pessoas
	 * 
	 * @param tel String indicando o telefone a ser validado
	 * @param email String indicando o email a ser validado
	 * @return boolean indicando a validade do telefone e email
	 */
	public boolean verificarTelEmail(String tel, String email) {
		return (tel.matches("\\d{10,11}") && email.matches("^(.+)@(.+)$"));
	}

}
