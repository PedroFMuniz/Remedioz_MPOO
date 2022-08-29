package modelo;

/**
 * Classe "Remedio". Simula o comportamento de um remedio no contexto do programa.
 * Armazena um id, nome, efeito, tipo e a via de uso de um determinado medicamento.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class Remedio {
	private int id;
	private String nome;
	private String efeito;
	private String tipo;
	private String viaDeUso;
	
	// Construtores
	
	/**
	 * Construtor vazio para a instanciacao de um remedio sem valores padrao.
	 */
	public Remedio() {
		
	}
	/**
	 * Construtor para a instanciacao de um remedio com valores definidos.
	 */
	public Remedio(int id, String nome, String efeito, String tipo, String viaDeUso) {
		this.id = id;
		this.setNome(nome);
		this.efeito = efeito;
		this.tipo = tipo;
		this.viaDeUso = viaDeUso;
	}
	
	// Getters e setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEfeito() {
		return efeito;
	}
	public void setEfeito(String efeito) {
		this.efeito = efeito;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getViaDeUso() {
		return viaDeUso;
	}
	public void setViaDeUso(String viaDeUso) {
		this.viaDeUso = viaDeUso;
	}
}