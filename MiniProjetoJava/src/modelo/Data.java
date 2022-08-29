package modelo;
import java.util.Calendar;

/**
 * Classe "Data". Armazena a data que um remedio deve ser tomado, bem como o 
 * horario e o dia da semana.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class Data {
	private int id;
	private Calendar data;
	private String diaSemana;
	private Calendar horario;
	
	// Construtores
	/**
	 * Construtor vazio para a instanciacao de uma data sem valores padrao.
	 */
	public Data() {
		
	}
	/**
	 * Construtor para a instanciacao de uma data com valores definidos.
	 */
	public Data(int id, Calendar data, String diaSemana, Calendar horario) {
		this.id = id;
		this.data = data;
		this.diaSemana = diaSemana;
		this.horario = horario;
	}
	
	// Gatters e Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Calendar getHorario() {
		return horario;
	}
	public void setHorario(Calendar horario) {
		this.horario = horario;
	}
}

