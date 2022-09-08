package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import controle.ControleDados;

public class TelaCadastroEdicaoMedico implements ActionListener{
	private JFrame frame;
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JLabel labelEmail = new JLabel("Email: ");
	private JLabel labelCrm = new JLabel("CRM: ");
	private JLabel labelEspecialidade = new JLabel("Especialidade: ");
	private String[] novoCadastro = new String[9];
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtCrm;
	private JTextField txtEspecialidade;
	private JButton excluir = new JButton("Excluir");
	private JButton salvar = new JButton("Salvar");
	private static int opcaoCrud;
	private static int posicao;
	private static ControleDados controleTelaEdicaoMedico;
	
	public void inserirEditarMedico(int opcao, ControleDados dados, int indice) {
		opcaoCrud = opcao;
		posicao = indice;
		controleTelaEdicaoMedico = dados;
		this.frame = new JFrame("Cadastro e edicao de medicos");
		this.frame.setSize(600, 600);
		if(opcao == 1) {
			// sem dados
			txtNome = new JTextField(30);
			txtEmail = new JTextField(30);
			txtCrm = new JTextField(30);
			txtEspecialidade = new JTextField(30);
			txtTelefone = new JTextField(11);
			frame.add(salvar);
			//salvar.setBounds(245, 175, 115, 30);
		}
		else if(opcao == 2) {
			txtNome = new JTextField(dados.getMedicos()[indice].getNome(), 30);
			txtEmail = new JTextField(dados.getMedicos()[indice].getEmail(), 30);
			txtTelefone = new JTextField(dados.getMedicos()[indice].getTelefone(), 11);
			txtCrm = new JTextField(dados.getMedicos()[indice].getCrm(), 30);
			txtEspecialidade = new JTextField(dados.getMedicos()[indice].getEspecialidade(), 30);
			frame.add(salvar);
			frame.add(excluir);
		}
		
		
		
		txtNome.setSize(60, 20);
		txtEmail.setSize(60, 20);
		txtTelefone.setSize(60, 20);
		txtCrm.setSize(60, 20);
		txtEspecialidade.setSize(60, 20);
		
		frame.setLayout(new FlowLayout());
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
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {
			try {
				boolean resultado;
				if(opcaoCrud == 1) {
					// novo medico
					novoCadastro[0] = Integer.toString(controleTelaEdicaoMedico.getQtdMedicos());
				}
				else {
					//edicao
					novoCadastro[0] = Integer.toString(posicao);
				}
				novoCadastro[1] =  txtNome.getText();
				novoCadastro[2] =  txtTelefone.getText();
				novoCadastro[3] =  txtEmail.getText();
				novoCadastro[4] =  txtEspecialidade.getText();
				novoCadastro[5] =  txtCrm.getText();
				resultado = controleTelaEdicaoMedico.inserirEditarMedico(novoCadastro);
				System.out.println(resultado);
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
			resultado = controleTelaEdicaoMedico.removerMedico(posicao);
			if (resultado) sucessoExcluir(); 
			else erroExcluir(); 
		}
	}
		
	public void sucessoExcluir() {
		JOptionPane.showMessageDialog(null, "O medico foi excluido com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

	public void sucessoCadastrar() {
		JOptionPane.showMessageDialog(null, "O medico foi salvo com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

	public void erroCadastrar() {
		JOptionPane.showMessageDialog(null,"ERRO\n "
				+ "Verifique os dados inseridos e tente novamente.  \n", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void erroExcluir() {
		JOptionPane.showMessageDialog(null,"ERRO\n "
				+ "Houve um erro desconhecido ao tetnar excluir o medico.  \n", null, 
				JOptionPane.ERROR_MESSAGE);
	}
}
