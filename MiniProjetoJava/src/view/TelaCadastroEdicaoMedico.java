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
import controle.ControleMedico;

/**
 * Classe "TelaCadastroEdicaoMedico". Descreve uma tela com um titulo e JTextFields
 * para o cadastro de um novo "Medico" ou edicao de um "Medico" existente. Implementa a 
 * interface ActionListener. 
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaCadastroEdicaoMedico implements ActionListener{
	private JFrame frame;
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JLabel labelEmail = new JLabel("Email: ");
	private JLabel labelCrm = new JLabel("CRM: ");
	private JLabel labelEspecialidade = new JLabel("Especialidade: ");
	private JLabel titulo = new JLabel("Cadastro de médico");
	private String[] novoCadastro = new String[9];
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtCrm;
	private JTextField txtEspecialidade;
	private JButton excluir = new JButton("Excluir");
	private JButton salvar = new JButton("Salvar");
	private static int opcaoCrud;
	private static int idMedico;
	private static ControleDados controleTelaEdicaoMedico;
	
	/**
	 * Metodo que descreve a tela "TelaCadastroEdicaoMedico" para renderizacao. Adiciona 
	 * dados aos JTextFields a depender da situacao. Adiciona ActionListeners aos botoes 
	 * de "salvar" e "excluir".
	 * 
	 * @param opcao Define se a tela obtera os dados de um medico especifico 
	 * escolhido em "TelaMedico" ou se nao tera dados anteriores 
	 * @see TelaMedico
	 * @param dados A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @param id o atributo "id" do medico em questao
	 * @see TelaMedico
	 * 
	 * @return void
	 */
	public void inserirEditarMedico(int opcao, ControleDados dados, int id){
		// Definicoes para uso em outros metodos
		opcaoCrud = opcao;
		idMedico = id;
		controleTelaEdicaoMedico = dados;
		frame = new JFrame("Cadastro e edicao de medicos");
		frame.setSize(350, 320);
		if(opcao == 1) {
			// sem dados
			txtNome = new JTextField(30);
			txtEmail = new JTextField(30);
			txtCrm = new JTextField(30);
			txtEspecialidade = new JTextField(30);
			txtTelefone = new JTextField(11);
			
			titulo.setBounds(95, 0, 200, 30);
			salvar.setBounds(250, 240, 70, 30);
			frame.add(salvar);
		}
		else if(opcao == 2) {
			// Obtencao de dados
			txtNome = new JTextField(new ControleMedico(controleTelaEdicaoMedico).getNome(id), 30);
			txtEmail = new JTextField(new ControleMedico(controleTelaEdicaoMedico).getEmail(id), 30);
			txtTelefone = new JTextField(new ControleMedico(controleTelaEdicaoMedico).getTelefone(id), 11);
			txtCrm = new JTextField(new ControleMedico(controleTelaEdicaoMedico).getCrm(id), 30);
			txtEspecialidade = new JTextField(new ControleMedico(controleTelaEdicaoMedico).getEspecialidade(id), 30);
			
			titulo.setText("Edição de medico");
			titulo.setBounds(110, 0, 200, 30);
			salvar.setBounds(250, 240, 70, 30);
			excluir.setBounds(130, 240, 100, 30);
			frame.add(salvar);
			frame.add(excluir);
		}
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		labelNome.setFont(new Font("Arial", Font.BOLD, 14));
		labelEmail.setFont(new Font("Arial", Font.BOLD, 14));
		labelTelefone.setFont(new Font("Arial", Font.BOLD, 14));
		labelCrm.setFont(new Font("Arial", Font.BOLD, 14));
		labelEspecialidade.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Posicionamento dos itens
		labelNome.setBounds(10, 35, 80, 30);
		labelEmail.setBounds(10, 75, 80, 30);
		labelTelefone.setBounds(10, 115, 100, 30);
		labelCrm.setBounds(10, 155, 80, 30);
		labelEspecialidade.setBounds(10, 195, 110, 30);
		txtNome.setBounds(60, 40, 200, 20);
		txtEmail.setBounds(55, 80, 200, 20);
		txtTelefone.setBounds(80, 120, 200, 20);
		txtCrm.setBounds(50, 160, 200, 20);
		txtEspecialidade.setBounds(120, 200, 200, 20);
		
		frame.setLayout(null);
		
		// Adicoes ao JFrame
		frame.add(titulo);
		frame.add(labelNome);
		frame.add(txtNome);
		frame.add(labelEmail);
		frame.add(txtEmail);
		frame.add(labelTelefone);
		frame.add(txtTelefone);
		frame.add(labelCrm);
		frame.add(txtCrm);
		frame.add(labelEspecialidade);
		frame.add(txtEspecialidade);
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		
		frame.setVisible(true);
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir dos cliques nos botoes. Este 
	 * metodo grava um "Medico" em "ControleDados" ou o exclui, a depender do botao 
	 * pressionado.
	 * 
	 * @see ControleDados
	 * @param e ActionEvent usado para determinar a fonte da acao em "TelaCadastroEdicaoMedico".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {
			try {
				boolean resultado;
				if(opcaoCrud == 1) {
					// novo medico
					novoCadastro[0] = Integer.toString(controleTelaEdicaoMedico.getUltimoIdMedicos() + 1);
				}
				else {
					//edicao
					novoCadastro[0] = Integer.toString(idMedico);
				}
				// Obtencao de dados
				novoCadastro[1] =  txtNome.getText();
				novoCadastro[2] =  txtTelefone.getText();
				novoCadastro[3] =  txtEmail.getText();
				novoCadastro[4] =  txtCrm.getText();	
				novoCadastro[5] =  txtEspecialidade.getText();
				
				resultado = controleTelaEdicaoMedico.inserirEditarMedico(novoCadastro);
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
			resultado = controleTelaEdicaoMedico.removerMedico(idMedico);
			if (resultado) sucessoExcluir(); 
			else erroExcluir(); 
		}
	}
	
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso na exclusao do medico.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void sucessoExcluir() {
		JOptionPane.showMessageDialog(null, "O médico foi excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro do medico.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void sucessoCadastrar() {
		JOptionPane.showMessageDialog(null, "O médico foi salvo com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro no cadastro do medico.
	 * 
	 * @return void
	 */
	public void erroCadastrar() {
		JOptionPane.showMessageDialog(null,"ERRO\nVerifique os dados inseridos e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro na exclusao do medico.
	 * 
	 * @return void
	 */
	public void erroExcluir() {
		JOptionPane.showMessageDialog(null,"ERRO\nHouve um erro ao tentar excluir o médico.\nVerifique se o médico está incluso em um agendamento.", null, JOptionPane.ERROR_MESSAGE);
	}
}
