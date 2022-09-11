package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import controle.ControleDados;
import controle.ControleRemedio;

/**
 * Classe "TelaAlergias". Descreve uma tela para cadastro de alergias em um "Paciente".
 * Descreve uma tela com uma JComboBox, labels e um botao para salvar a alergia.
 * Implementa a interface ActionListener.
 *
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaAlergias implements ActionListener{
	private JComboBox<String> cmbRemedios;
	private JButton addAlergias;
	private JFrame frame;
	private String[] nomesRemedios = new String[40];
	private JLabel titulo = new JLabel("Cadastro de alergias");
	private JLabel labelAlergias = new JLabel("Alergias");
	private ControleDados controleDadosAlergia;
	private int id;
	
	/**
	 * Metodo que descreve a tela "TelaAlergias" para renderizacao. Adiciona
	 * ActionListener ao botao "addAlergias".
	 * 
	 * @param dados : A instancia de "ControleDados" feita na classe "TelaMenu". 
	 * @param idPaciente: o atributo "id" do "Paciente" ao qual sera adicionada a alergia.
	 * @see TelaCadastroEdicaoPaciente
	 * @return void
	 */
	public void mostrarLista(ControleDados dados, int idPaciente) {
		// Definicoes para uso em outros metodos
		controleDadosAlergia = dados;
		id = idPaciente;
		
		nomesRemedios = new ControleRemedio(dados).getInfo();
		
		addAlergias = new JButton("Adicionar");
		cmbRemedios = new JComboBox<String>(nomesRemedios);
		
		frame = new JFrame("Alergias");
		frame.setSize(240, 200);
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		labelAlergias.setFont(new Font("Arial", Font.BOLD, 14));
		cmbRemedios.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Posicionamento dos itens
		titulo.setBounds(35, 0, 170, 30);
		labelAlergias.setBounds(10, 60, 70, 30);
		cmbRemedios.setBounds(80, 60, 140, 30);
		addAlergias.setBounds(55, 120, 120, 30);
		frame.setLayout(null);

		frame.add(titulo);
		frame.add(labelAlergias);
		frame.add(cmbRemedios);
		frame.add(addAlergias);
		
		addAlergias.addActionListener(this);
		
		frame.setVisible(true);
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir do clique no botao. Este 
	 * metodo grava uma alergia no paciente a partir do clique no botao "addAlergias" com
	 * um metodo da classe "ControleDados". 
	 * 
	 * @see ControleDados
	 * @param e : ActionEvent usado para determinar a fonte da acao em "TelaAlergias".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergias) {
			boolean resultado;
			resultado = controleDadosAlergia.inserirAlergiasPaciente(id, cmbRemedios.getSelectedIndex());
			if(resultado) {
				sucessoAdicionarAlergia();
			}
			else {
				erroAdicionarAlergia();
			}
		}
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro da alergia.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void sucessoAdicionarAlergia() {
		JOptionPane.showMessageDialog(null, "A alergia foi adicionada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro no cadastro da alergia.
	 * 
	 * @return void
	 */
	public void erroAdicionarAlergia() {
		JOptionPane.showMessageDialog(null, "ERRO\n houve algum erro ao adicionar essa alergia. Selecione um item e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
		frame.dispose();
	}
}
