package modelo;

/**
 * Classe "Paciente" herda de "Pessoa". Simula o comportamento de um paciente
 * no contexto do programa. Armazena um vetor de remedios que causam reacoes
 * alergicas ao paciente, bem como um historico de doencas. 
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class Paciente extends Pessoa {
	
	private Remedio[] alergias = new Remedio[40];
	private String historicoDoencas;
	
	// Construtores
	
	/**
	 * Construtor vazio para a instanciacao de um paciente sem valores padrao.
	 */
	public Paciente() {
		super();
	}
	/**
	 * Construtor para a instanciacao de um paciente com valores definidos.
	 */
	public Paciente(int id, String nome, String telefone, String email, String historicoDoencas) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.historicoDoencas = historicoDoencas;
	}
	
	// Getters e setters
	
	public Remedio[] getAlergias() {
		return this.alergias;
	}
	public void setAlergias(Remedio[] alergias) {
		this.alergias = alergias;
	}
	public String getHistoricoDoencas() {
		return historicoDoencas;
	}
	public void setHistoricoDoencas(String historicoDoencas) {
		this.historicoDoencas = historicoDoencas;
	}
	
	// Outros métodos (no github da prof diz que faz mais sentido 
	// na camada de controle)
	
	/**
	 * Metodo para adicionar uma alergia a um paciente. Retorna TRUE se houver 
	 * sucesso; se nao, FALSE.
	 * 
	 * @return boolean
	 */
	public boolean adicionarAlergia(Remedio remedio) {
		return false;
	}
	/**
	 * Metodo para remover uma alergia de um paciente. Retorna TRUE se houver 
	 * sucesso; se nao, FALSE.
	 * 
	 * @return boolean
	 */
	public boolean removerAlergia(Remedio remedio) {
		return false;
	}

}

