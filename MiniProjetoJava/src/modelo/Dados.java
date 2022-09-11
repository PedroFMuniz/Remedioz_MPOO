package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
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
	 * respectivos vetores. Tambem iguala todas as variaveis de quantidades a 10 e o ultimo id para 9. 
	 * 
	 * @return void
	 * */
	public void preencherDados() {
		Random gerador = new Random();
		String[] nomes = {"Jorge", "Cláudio", "Davi", "Miguel", "Helena", "Ana", "Laura", "Alice"};
		String[] sobrenomes = {"Silva", "Ferraz", "Correia", "Fernandes"};
		String[] nomeRemedios = {"Paracetamol", "Ibuprofeno", "Rivotril", "Systane", "Metila", "Polaramine"};
		String[] viaDeUso = {"Oral", "Ocular", "Superfície", "Nasal"};
		String[] tipo = {"Comprimido", "Gotas", "Spray", "Líquido"};
		String[] efeitos = {"Alívio da dor", "Alívio da febre", "Antialérgico", "Limpeza"};
		String[] especialidades = {"Nutricionista", "Oftalmologista", "Otorrinolaringologista", "Geral", "Diagnóstico"};
		String[] historicos = {"Saudável", "Diabético", "Hipertenso", "Imunosuprimido"};
		String[] diasSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
		for(int i = 0; i < 10; i++) {
			medicos[i] = new Medico(i, nomes[gerador.nextInt(7)] + " " +  sobrenomes[gerador.nextInt(3)], "619" + Integer.toString(gerador.nextInt(10000000, 99999999)), sobrenomes[gerador.nextInt(3)] + "@email.com", Integer.toString(gerador.nextInt(1000000, 9999999)), especialidades[gerador.nextInt(4)]);
			remedios[i] = new Remedio(i, nomeRemedios[gerador.nextInt(5)], efeitos[gerador.nextInt(3)], tipo[gerador.nextInt(3)], viaDeUso[gerador.nextInt(3)]);
			Remedio[] alergias = new Remedio[40];
			alergias[0] = remedios[i==0 ? 0 : gerador.nextInt(i)];
			pacientes[i] = new Paciente(i, nomes[gerador.nextInt(7)] + " " +  sobrenomes[gerador.nextInt(3)], "619" + Integer.toString(gerador.nextInt(10000000, 99999999)), sobrenomes[gerador.nextInt(3)] + "@email.com", alergias, historicos[gerador.nextInt(3)]);
			LocalTime[] horarios = new LocalTime[40];
			horarios[0] = LocalTime.of(gerador.nextInt(23), gerador.nextInt(59));
			int dia = gerador.nextInt(6);
			DiaDaSemana d = new DiaDaSemana(diasSemana[dia], horarios);
			DiaDaSemana[] dias = new DiaDaSemana[7];
			dias[dia] = d;
			agendamentos[i] = new Agendamento(i, LocalDate.of(2022, 2, 1), LocalDate.of(2022, 12, 1), medicos[i==0 ? 0 : gerador.nextInt(i)], pacientes[i==0 ? 0 : gerador.nextInt(i)], remedios[i==0 ? 0 : gerador.nextInt(i)], dias);
		}
		qtdeMedicos = qtdePacientes = qtdeRemedios = qtdeAgendamentos = 10;
		ultimoIdMedicos = ultimoIdPacientes = ultimoIdRemedios = ultimoIdAgendamentos = 9;
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
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia,
	 * acresce um ao numero de instancias e incrementa o ultimo id cadastrado.
	 * 
	 * @param medico : Medico que deve ser cadastrado ou editado
	 * @param id : int indicando o id do medico
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
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia,
	 * acresce um ao numero de instancias e incrementa o ultimo id cadastrado.
	 * 
	 * @param paciente : Paciente que deve ser cadastrado ou editado
	 * @param id : int indicando o id do paciente
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
					paciente.setAlergias(pacientes[i].getAlergias());
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
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia,
	 * acresce um ao numero de instancias e incrementa o ultimo id cadastrado.
	 * 
	 * @param remedio : Remedio que deve ser cadastrado ou editado
	 * @param id : int indicando o id do remedio
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
	 * preenchida, apenas sobrescreve. No caso de uma posicao vazia,
	 * acresce um ao numero de instancias e incrementa o ultimo id cadastrado.
	 * 
	 * @param agendamento : Agendamento que deve ser cadastrado ou editado
	 * @param id : int indicando o id do agendamento
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
					agendamento.setDiasDaSemana(agendamentos[i].getDiasDaSemana());
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
