package modelo;

/**
 * Classe "Medico" herda de "Pessoa". Simula o comportamento de um medico no 
 * contexto do programa. Possui um CRM e uma especialidade.
 * Sera relacionado ao paciente na classe "Agendamento".
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class Medico extends Pessoa {
	
	private String crm;
	private String especialidade;
	
	/**
	 * Construtor para a instanciacao de um medico com valores definidos.
	 */
	public Medico(int id, String nome, String telefone, String email, String crm, String especialidade) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.crm = crm;
		this.especialidade = especialidade;
	}
	
	// Getters e setters 
	
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
}

