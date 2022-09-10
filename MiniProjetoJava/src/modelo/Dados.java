package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Classe "Dados". Relaciona-se com todas as classes do pacote "modelo", pois tem 
 * como funcao principal a criacao de dados padrao no inicio da execucao do programa.
 * Tambem possui metodos para inserir ou editar uma instancia de cada classe.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class Dados {
	// Vetores de dados
	private Medico[] medicos = new Medico[40];
	private Paciente[] pacientes = new Paciente[40];
	private Remedio[] remedios = new Remedio[40];
	private Agendamento[] agendamentos = new Agendamento[40];
	
	
	// variáveis para quantidades
	
	private int qtdeMedicos = 0;
	private int qtdePacientes= 0;
	private int qtdeRemedios = 0;
	private int qtdeAgendamentos = 0;
	private int ultimoIdMedicos = 0;
	private int ultimoIdPacientes= 0;
	private int ultimoIdRemedios = 0;
	private int ultimoIdAgendamentos = 0;
	
	/**
	 * Construtor para a instanciacao da classe. Esta classe nao necessita de um 
	 * construtor elaborado.
	 * */
	public Dados() {
		
	}

	/**
	 * Metodo para preencher os vetores com dados padrao. Usa de um loop "for" para
	 * criar 10 instancias de todas as classes do projeto e salva-as em seus
	 * respectivos vetores. Tambem iguala todas as variaveis de quantidades a 10. 
	 * 
	 * @return void
	 * */
	public void preencherDados() {
		for(int i = 0; i < 10; i++) {

			Medico md = new Medico(i, "Dr. teste", "61999994444", "doutor@teste.com", "999999999999", "Diagnóstico");
			Remedio rd1 = new Remedio(i, "Generico", "Placebo", "Comprimido", "Oral");
			Remedio[] alergias = new Remedio[40];
			alergias[0] = rd1;
			Paciente pa = new Paciente(i, "Teste", "61999994444", "paciente@teste.com", alergias, "Saudável");
			LocalTime[] horarios = new LocalTime[40];
			horarios[0] = LocalTime.of(18, 30);
			DiaDaSemana d1 = new DiaDaSemana("Sexta", horarios);
			DiaDaSemana[] dias = {null, null, null, null, d1, null, null};
			Agendamento ag = new Agendamento(0, LocalDate.of(2022, 2, 1), LocalDate.of(2022, 12, 1), md, pa, rd1, dias);
			medicos[i] = md;
			pacientes[i] = pa;
			remedios[i] = rd1;
			agendamentos[i] = ag;
			qtdeMedicos = qtdePacientes = qtdeRemedios = qtdeAgendamentos = 10;
			ultimoIdMedicos = ultimoIdPacientes = ultimoIdRemedios = ultimoIdAgendamentos = 9;
		}
	}
	
	// Métodos para vetor de médicos
	
	public Medico[] getMedicos() {
		return medicos;
	}
	public void setMedicos(Medico[] medicos) {
		this.medicos = medicos;
	}
	/**
	 * Metodo para inserir uma instancia de "Medico" na posicao desejada. Se ja 
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia, se o numero de
	 * instancias for igual a posicao, acresce um ao numero de instancias.
	 * 
	 * @return void
	 * */
	public void inserirOuEditarMedico(Medico medico, int id) {
		if(id == ultimoIdMedicos + 1) {
			this.medicos[qtdeMedicos] = medico;
			qtdeMedicos++;
			ultimoIdMedicos++;
		}
		else {
			for(int i = 0; i < qtdeMedicos; i++) {
				if(medicos[i].getId() == id) {
					medicos[i] = medico;
					break;
				}
			}
		}
	}
	public int getQtdeMedicos() {
		return qtdeMedicos;
	}
	public void setQtdeMedicos(int qtdeMedicos) {
		this.qtdeMedicos = qtdeMedicos;
	}
	
	public int getUltimoIdMedicos() {
		return ultimoIdMedicos;
	}
	public void setUltimoIdMedicos(int ultimoIdMedicos) {
		this.ultimoIdMedicos = ultimoIdMedicos;
	}
	
	// Métodos para vetor de paciente
	
	public Paciente[] getPacientes() {
		return pacientes;
	}
	public void setPacientes(Paciente[] pacientes) {
		this.pacientes = pacientes;
	}
	/**
	 * Metodo para inserir uma instancia de "Paciente" na posicao desejada. Se ja 
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia, se o numero de
	 * instancias for igual a posicao, acresce um ao numero de instancias.
	 * 
	 * @return void
	 * */
	public void inserirOuEditarPaciente(Paciente paciente, int id) {
		if(id == ultimoIdPacientes + 1) {
			this.pacientes[qtdePacientes] = paciente;
			ultimoIdPacientes++;
			qtdePacientes++;
		}
		else {
			for(int i = 0; i < qtdePacientes; i++) {
				if(pacientes[i].getId() == id) {
					pacientes[i] = paciente;
					break;
				}
			}
		}
	}
	public int getQtdePacientes() {
		return qtdePacientes;
	}
	public void setQtdePacientes(int qtdePacientes) {
		this.qtdePacientes = qtdePacientes;
	}
	
	public int getUltimoIdPacientes() {
		return ultimoIdPacientes;
	}
	public void setUltimoIdPacientes(int ultimoIdPacientes) {
		this.ultimoIdPacientes = ultimoIdPacientes;
	}
	
	// Métodos para vetor de remédios
	
	public Remedio[] getRemedios() {
		return remedios;
	}
	public void setRemedios(Remedio[] remedios) {
		this.remedios = remedios;
	}
	/**
	 * Metodo para inserir uma instancia de "Remedio" na posicao desejada. Se ja 
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia, se o numero de
	 * instancias for igual a posicao, acresce um ao numero de instancias.
	 * 
	 * @return void
	 * */
	public void inserirOuEditarRemedio(Remedio remedio, int id) {
		if(id == ultimoIdRemedios + 1) {
			this.remedios[qtdeRemedios] = remedio;
			ultimoIdRemedios++;
			qtdeRemedios++;
		}
		else {
			for(int i = 0; i < qtdeRemedios; i++) {
				if(remedios[i].getId() == id) {
					remedios[i] = remedio;
					break;
				}
			}
		}
	}
	public int getQtdeRemedios() {
		return qtdeRemedios;
	}
	public void setQtdeRemedios(int qtdeRemedios) {
		this.qtdeRemedios = qtdeRemedios;
	}
	
	public int getUltimoIdRemedios() {
		return ultimoIdRemedios;
	}
	public void setUltimoIdRemedios(int ultimoIdRemedios) {
		this.ultimoIdRemedios = ultimoIdRemedios;
	}
	
	// Métodos para vetor de agendamentos

	public Agendamento[] getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(Agendamento[] agendamentos) {
		this.agendamentos = agendamentos;
	}
	/**
	 * Metodo para inserir uma instancia de "Agendamento" na posicao desejada. Se ja 
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia, se o numero de
	 * instancias for igual a posicao, acresce um ao numero de instancias.
	 * 
	 * @return void
	 * */
	public void inserirOuEditarAgendamento(Agendamento agendamento, int id) {
		if(id == ultimoIdAgendamentos + 1) {
			this.agendamentos[qtdeAgendamentos] = agendamento;
			ultimoIdAgendamentos++;
			qtdeAgendamentos++;
		}
		else {
			for(int i = 0; i < qtdeAgendamentos; i++) {
				if(agendamentos[i].getId() == id) {
					agendamentos[i] = agendamento;
					break;
				}
			}
		}
	}
	
	public int getQtdeAgendamentos() {
		return qtdeAgendamentos;
	}
	public void setQtdeAgendamentos(int qtdeAgendamentos) {
		this.qtdeAgendamentos = qtdeAgendamentos;
	}
	
	public int getUltimoIdAgendamentos() {
		return ultimoIdAgendamentos;
	}
	public void setUltimoIdAgendamentos(int ultimoIdAgendamentos) {
		this.ultimoIdAgendamentos = ultimoIdAgendamentos;
	}
}
