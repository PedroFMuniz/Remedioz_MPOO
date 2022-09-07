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
	private LocalTime[] horarios = {LocalTime.now(), LocalTime.of(19, 00)};
	
	// variáveis para quantidades
	
	private int qtdeMedicos = 0;
	private int qtdePacientes= 0;
	private int qtdeRemedios = 0;
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
			medicos[i] = new Medico(i, "Medico " + i, "0123456" + i, "medico" + i + "@email.com", "98765" + i, "Generalista");
			pacientes[i] = new Paciente(i, "Paciente " + i, "0123456" + i, "paciente" + i + "@email.com", remedios, "Nenhum");
			remedios[i] = new Remedio(i, "Generico " + i, "Consulte a bula", "Controlado", "Oral");
			agendamentos[i] = new Agendamento(i, LocalDate.of(2022, 9, i+1), LocalDate.of(2022, 10, i+1), medicos[i], pacientes[i], remedios[i], gerarDias(i));
			qtdeMedicos = qtdePacientes = qtdeRemedios = qtdeAgendamentos = 10;
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
		if(id == qtdeMedicos) {
			qtdeMedicos++;
			this.medicos[id] = medico;
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
		if(id == qtdePacientes) {
			qtdePacientes++;
			this.pacientes[id] = paciente;
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
		if(id == qtdeRemedios) {
			qtdeRemedios++;
			this.remedios[id] = remedio;
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
		if(id == qtdeAgendamentos) {
			qtdeAgendamentos++;
			this.agendamentos[id] = agendamento;
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
	
	public DiaDaSemana[] gerarDias(int i) {
		DiaDaSemana dia = new DiaDaSemana(i, "Segunda", horarios);
		DiaDaSemana[] dias = {dia};
		return dias;
	}
}
