package modelo;

import java.time.LocalDate;
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
	private LocalDate dtInicio;
	private LocalDate dtFim;
	private Medico medico;
	private Paciente paciente;
	private Remedio remedio;
	private DiaDaSemana[] diasDaSemana = new DiaDaSemana[7];
	
	/**
	 * Construtor para a instanciacao de um paciente com valores definidos.
	 */
	public Agendamento(int id, LocalDate dtInicio, LocalDate dtFim, Medico medico, Paciente paciente, Remedio remedio, DiaDaSemana[] diasSemana) {
		this.id = id;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.medico = medico;
		this.paciente = paciente;
		this.remedio = remedio;
		this.diasDaSemana = diasSemana;
	}

	// Getters e Setters
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}
	public LocalDate getDtFim() {
		return dtFim;
	}
	public void setDtFim(LocalDate dtFim) {
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
	public Remedio getRemedio() {
		return remedio;
	}
	public void setRemedio(Remedio remedio) {
		this.remedio = remedio;
	}
	public DiaDaSemana[] getDiasDaSemana() {
		return diasDaSemana;
	}
	public void setDiasDaSemana(DiaDaSemana[] diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}
}

