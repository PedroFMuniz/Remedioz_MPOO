package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controle.ControleDados;
import controle.ControleRemedio;

/**
 * Classe "TelaCadastroEdicaoRemedio". Descreve uma tela com um titulo e JTextFields
 * para o cadastro de um novo "Remedio" ou edicao de um "Remedio" existente. Implementa a 
 * interface ActionListener. 
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaCadastroEdicaoRemedio implements ActionListener {
	private JFrame frame;
	private JLabel titulo = new JLabel("Cadastro de remedio");
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelEfeito = new JLabel("Efeito: ");
	private JLabel labelTipo = new JLabel("Tipo: ");
	private JLabel labelViaUso = new JLabel("Via de uso: ");
	private String[] novoCadastro = new String[9];
	private JTextField txtNome;
	private JTextField txtEfeito;
	private JTextField txtTipo;
	private JTextField txtViaUso;
	private JButton excluir = new JButton("Excluir");
	private JButton salvar = new JButton("Salvar");
	private static int opcaoCrud;
	private static int idRemedio;
	private static ControleDados controleTelaEdicaoRemedio;
	
	/**
	 * Metodo que descreve a tela "TelaCadastroEdicaoRemedio" para renderizacao. Adiciona 
	 * dados aos JTextFields a depender da situacao. Adiciona ActionListeners aos botoes 
	 * de "salvar" e "excluir".
	 * 
	 * @param opcao : Define se a tela obtera os dados de um remedio especifico 
	 * escolhido em "TelaRemedio" ou se nao tera dados anteriores 
	 * @see TelaRemedio
	 * @param dados : A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @param id : o atributo "id" do remedio em questao
	 * @see TelaRemedio
	 * 
	 * @return void
	 */
	public void inserirEditarRemedio(int opcao, ControleDados dados, int id) {
		
		// Definicoes para uso em outros metodos
		opcaoCrud = opcao;
		idRemedio = id;
		controleTelaEdicaoRemedio = dados;
		
		frame = new JFrame("Cadastro e edicao de Remedios");
		frame.setSize(320, 300);
		//Se a opcao for cadastrar um novo remedio
		if(opcao == 1) {
			txtNome = new JTextField(30);
			txtEfeito = new JTextField(30);
			txtTipo = new JTextField(30);
			txtViaUso = new JTextField(15);
			
			titulo.setBounds(70, 0, 200, 30);
			salvar.setBounds(225, 220, 70, 30);
			
			frame.add(salvar);
		}
		//Se a opcao for editar um remedio existente
		if(opcao == 2) {
			titulo.setText("Edicao de remedio");
			
			// Obtencao de dados
			txtNome = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getNome(id), 30);
			txtEfeito = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getEfeito(id), 30);
			txtTipo = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getTipo(id), 30);
			txtViaUso = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getViaDeUso(id), 15);
			
			titulo.setBounds(85, 0, 200, 30);
			salvar.setBounds(225, 220, 70, 30);
			excluir.setBounds(115, 220, 100, 30);
			
			frame.add(salvar);
			frame.add(excluir);
		}
		
		frame.setLayout(null);
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		labelNome.setFont(new Font("Arial", Font.BOLD, 14));
		labelEfeito.setFont(new Font("Arial", Font.BOLD, 14));
		labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
		labelViaUso.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Definicao de localizacoes
		labelNome.setBounds(10, 35, 80, 30);
		labelEfeito.setBounds(10, 75, 80, 30);
		labelTipo.setBounds(10, 115, 80, 30);
		labelViaUso.setBounds(10, 155, 100, 30);
		txtNome.setBounds(60, 40, 200, 20);
		txtEfeito.setBounds(65, 80, 200, 20);
		txtTipo.setBounds(50, 120, 200, 20);
		txtViaUso.setBounds(95, 160, 200, 20);
		
		// Adicoes ao frame
		frame.add(titulo);
		frame.add(labelNome);
		frame.add(txtNome);
		frame.add(labelEfeito);
		frame.add(txtEfeito);
		frame.add(labelTipo);
		frame.add(txtTipo);
		frame.add(labelViaUso);
		frame.add(txtViaUso);
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		
		frame.setVisible(true);
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir dos cliques nos botoes. Este 
	 * metodo grava um "Remedio" em "ControleDados" ou o exclui, a depender do botao 
	 * pressionado.
	 * 
	 * @see ControleDados
	 * @param e : ActionEvent usado para determinar a fonte da acao em "TelaCadastroEdicaoRemedio".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {
			try {
				boolean resultado;
				if(opcaoCrud == 1) {
					// novo remedio
					novoCadastro[0] = Integer.toString(controleTelaEdicaoRemedio.getUltimoIdRemedios() + 1);
				}
				else {
					//edicao
					novoCadastro[0] = Integer.toString(idRemedio);
				}
				// Obtencao de dados
				novoCadastro[1] =  txtNome.getText();
				novoCadastro[2] =  txtEfeito.getText();
				novoCadastro[3] =  txtTipo.getText();
				novoCadastro[4] =  txtViaUso.getText();
				
				resultado = controleTelaEdicaoRemedio.inserirEditarRemedio(novoCadastro);
				if(resultado) {
					sucessoCadastrar();
				}
				else{
					erroCadastrar();
				}
			
			}
			catch(NullPointerException exc1) {
				erroCadastrar();
			}
			catch(NumberFormatException exc2) {
				erroCadastrar();
			}
		}
		if(fonte == excluir) {
			boolean resultado = false;
			resultado = controleTelaEdicaoRemedio.removerRemedio(idRemedio);
			if (resultado) sucessoExcluir(); 
			else erroExcluir(); 
		}
	}
	
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso na exclusao do remedio.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void sucessoExcluir() {
		JOptionPane.showMessageDialog(null, "O remedio foi excluido com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro do remedio.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void sucessoCadastrar() {
		JOptionPane.showMessageDialog(null, "O remedio foi salvo com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro no cadastro do remedio.
	 * 
	 * @return void
	 */
	public void erroCadastrar() {
		JOptionPane.showMessageDialog(null,"ERRO\nVerifique os dados inseridos e tente novamente.  \n", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro na exclusao do remedio.
	 * 
	 * @return void
	 */
	public void erroExcluir() {
		JOptionPane.showMessageDialog(null,"ERRO\nHouve um erro desconhecido ao tetnar excluir o medico.\n", null, 
				JOptionPane.ERROR_MESSAGE);
	}

}
