package modelo;

/**
 * Classe abstrata "Pessoa". Modela o "esqueleto" de uma pessoa no contexto do 
 * aplicativo de gestão de remedios tomados diariamente. "Pessoa" nao tem metodos 
 * padrao exceto getters e setters, mas deve conter id, nome, telefone e email.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */

public abstract class Pessoa {
	
	// Atributos 
	
	protected int id;
	protected String nome;
	protected String telefone;
	protected String email;
	
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

