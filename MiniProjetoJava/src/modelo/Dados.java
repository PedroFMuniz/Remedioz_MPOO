package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
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
	private DiaDaSemana[] diasSemana = new DiaDaSemana[40];
	private Agendamento[] agendamentos = new Agendamento[40];
	private LocalTime[] horarios = {LocalTime.now(), LocalTime.of(19, 00)};
	
	// variáveis para quantidades
	
	private int qtdeMedicos = 0;
	private int qtdePacientes= 0;
	private int qtdeRemedios = 0;
	private int qtdeDiasSemana = 0;
	private int qtdeAgendamentos = 0;
	
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
			medicos[i] = new Medico(i, "Médico " + i, "0123456" + i, "medico" + i + "@email.com", "98765" + i, "Generalista");
			//passivel de mudanças
			pacientes[i] = new Paciente(i, "Paciente " + i, "0123456" + i, "paciente" + i + "@email.com", remedios, "Nenhum");
			remedios[i] = new Remedio(i, "Genérico " + i, "Consulte a bula", "Controlado", "Oral");
			//datas[i] = new Data(i, Calendar.getInstance(), "Segunda", Calendar.getInstance());
			diasSemana[i] = new DiaDaSemana(i, "Segunda-feira", horarios);
			agendamentos[i] = new Agendamento(i, LocalDate.now(), LocalDate.now(), medicos[i], pacientes[i], remedios, diasSemana);
			qtdeMedicos = qtdePacientes = qtdeRemedios = qtdeDiasSemana = qtdeAgendamentos = 10;
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
	public void inserirOuEditarMedico(Medico medico, int posicao) {
		this.medicos[posicao] = medico;
		if(posicao == qtdeMedicos) {
			qtdeMedicos++;
		}
	}
	public int getQtdeMedicos() {
		return qtdeMedicos;
	}
	public void setQtdeMedicos(int qtdeMedicos) {
		this.qtdeMedicos = qtdeMedicos;
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
	public void inserirOuEditarPaciente(Paciente paciente, int posicao) {
		this.pacientes[posicao] = paciente;
		if(posicao == qtdePacientes) {
			qtdePacientes++;
		}
	}
	public int getQtdePacientes() {
		return qtdePacientes;
	}
	public void setQtdePacientes(int qtdePacientes) {
		this.qtdePacientes = qtdePacientes;
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
	public void inserirOuEditarRemedio(Remedio remedio, int posicao) {
		this.remedios[posicao] = remedio;
		if(posicao == qtdeRemedios) {
			qtdeRemedios++;
		}
	}
	public int getQtdeRemedios() {
		return qtdeRemedios;
	}
	public void setQtdeRemedios(int qtdeRemedios) {
		this.qtdeRemedios = qtdeRemedios;
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
	public void inserirOuEditarAgendamento(Agendamento agendamento, int posicao) {
		this.agendamentos[posicao] = agendamento;
		if(posicao == qtdeAgendamentos) {
			qtdeAgendamentos++;
		}
	}
	public int getQtdeAgendamentos() {
		return qtdeAgendamentos;
	}
	public void setQtdeAgendamentos(int qtdeAgendamentos) {
		this.qtdeAgendamentos = qtdeAgendamentos;
	}
}
