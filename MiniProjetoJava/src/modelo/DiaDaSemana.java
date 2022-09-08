package modelo;
import java.time.LocalTime;

/**
 * Classe "Data". Armazena a data que um remedio deve ser tomado, bem como o 
 * horario e o dia da semana.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class DiaDaSemana {
	private String diaSemana;
	private LocalTime[] horario = new LocalTime[40];
	
	// Construtores
	/**
	 * Construtor vazio para a instanciacao de uma data sem valores padrao.
	 */
	public DiaDaSemana() {
		
	}
	/**
	 * Construtor para a instanciacao de uma data com valores definidos.
	 */
	public DiaDaSemana(String diaSemana, LocalTime[] horario) {
		this.diaSemana = diaSemana;
		this.horario = horario;
	}
	
	// Gatters e Setters
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public LocalTime[] getHorario() {
		return horario;
	}
	public void setHorario(LocalTime[] horario) {
		this.horario = horario;
	}
}

