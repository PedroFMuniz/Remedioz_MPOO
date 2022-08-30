package modelo;

import java.util.Calendar;
/**
 * Classe "Agendamento". Armazena listas de remedios e datas, bem como o medico e o 
 * paciente a que se refere a receita. Tambem possui metodos para manipular as listas 
 * de remedios e datas. 
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */

public class Agendamento {
	private int id;
	private Calendar dtInicio;
	private Calendar dtFim;
	private Medico medico;
	private Paciente paciente;
	private Remedio[] remedios = new Remedio[40];
	private Data[] datas = new Data[40];
	
	// Construtores
	/**
	 * Construtor vazio para a instanciacao de um agendamento sem valores padrao.
	 */
	public Agendamento() {
		
	}
	/**
	 * Construtor  para a instanciacao de um paciente com valores definidos.
	 */
	public Agendamento(int id, Calendar dtInicio, Calendar dtFim, Medico medico, Paciente paciente) {
		this.id = id;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.medico = medico;
		this.paciente = paciente;
	}

	// Getters e Setters
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Calendar dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Calendar getDtFim() {
		return dtFim;
	}
	public void setDtFim(Calendar dtFim) {
		this.dtFim = dtFim;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Remedio[] getRemedios() {
		return remedios;
	}
	public void setRemedios(Remedio[] remedios) {
		this.remedios = remedios;
	}
	public Data[] getDatas() {
		return datas;
	}
	public void setDatas(Data[] datas) {
		this.datas = datas;
	}
	
	// Outros métodos (mais sentido na camada de controle)
	/**
	 * Adiciona um remedio ao array de remedios. Retorna TRUE se a operacao tiver
	 * sucesso; se nao, FALSE.
	 * 
	 * @return boolean
	 */
	public boolean adicionarRemedio(Remedio remedio) {
		return false;
	}
	/**
	 * Remove um remedio do array da instancia da classe. Retorna TRUE se houver 
	 * sucesso; se nao, FALSE.
	 * 
	 * @return boolean
	 */
	public boolean removerRemedio(Remedio remedio) {
		return false;
	}
	/**
	 * Adiciona uma data ao array de datas. Retorna TRUE se a operacao tiver
	 * sucesso; se nao, FALSE. 
	 * 
	 * @return boolean
	 */
	public boolean adicionarData(Data data) {
		return false;
	}
	/**
	 * Remove uma data do array da instancia da classe. Retorna TRUE se houver 
	 * sucesso; se nao, FALSE.
	 * 
	 * @return boolean
	 */
	public boolean removerData(Data data) {
		return false;
	}

}

