package modelo;

import java.util.*;
/***/
public class Dados {
	// Vetores de dados
	private Medico[] medicos = new Medico[40];
	private Paciente[] pacientes = new Paciente[40];
	private Remedio[] remedios = new Remedio[40];
	private Data[] datas = new Data[40];
	private Agendamento[] agendamentos = new Agendamento[40];
	
	// variáveis para quantidades
	
	private int qtdeMedicos = 0;
	private int qtdePacientes= 0;
	private int qtdeRemedios = 0;
	private int qtdeDatas = 0;
	private int qtdeAgendamentos = 0;
	
	/*// Objeto Calendar
	
	Date data = new Date();
	private Calendar calendario = new GregorianCalendar();
	calendario.setTime(data);
	
	// Métodos
	
	public String diaSemana()
	*/
	/***/
	public void preencherDados() {
		//Date data = Calendar.getInstance().getTime();
		// Preenchimento de dados via for (10 instâncias)
		for(int i = 0; i < 10; i++) {
			
			medicos[i] = new Medico(i, "Médico " + i, "0123456" + i, "medico" + i + "@email.com", "98765" + i, "Generalista");
			pacientes[i] = new Paciente(i, "Paciente " + i, "0123456" + i, "paciente" + i + "@email.com", "Nenhum");
			remedios[i] = new Remedio(i, "Genérico " + i, "Consulte a bula", "Controlado", "Oral");
			datas[i] = new Data(i, Calendar.getInstance(), "Segunda", Calendar.getInstance());
			agendamentos[i] = new Agendamento(i, Calendar.getInstance(), Calendar.getInstance(), medicos[i], pacientes[i]);
			qtdeMedicos = qtdePacientes = qtdeRemedios = qtdeDatas = qtdeAgendamentos = 10;
		}
	}
	
	// Métodos para vetor de médicos
	
	public Medico[] getMedicos() {
		return medicos;
	}
	public void setMedicos(Medico[] medicos) {
		this.medicos = medicos;
	}
	/***/
	public void inserirOuEditarMedico(Medico medico, int posicao) {
		// Insere um médico na posição do vetor desejada. Se já preenchida, é mudada.
		this.medicos[posicao] = medico;
		// Se a posição desejada é igual a quantidade de médicos, a quantidade de médicos deve aumentar
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
	/***/
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
	/***/
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

	// Métodos para vetor de datas
	
	public Data[] getDatas() {
		return datas;
	}
	public void setDatas(Data[] datas) {
		this.datas = datas;
	}
	/***/
	public void inserirOuEditarData(Data data, int posicao) {
		this.datas[posicao] = data;
		if(posicao == qtdeDatas) {
			qtdeDatas++;
		}
	}
	public int getQtdeDatas() {
		return qtdeDatas;
	}
	public void setQtdeDatas(int qtdeDatas) {
		this.qtdeDatas = qtdeDatas;
	}
	
	// Métodos para vetor de agendamentos

	public Agendamento[] getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(Agendamento[] agendamentos) {
		this.agendamentos = agendamentos;
	}
	/***/
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
