package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

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
	
	public void inserirEditarMedico(int opcao, ControleDados dados, TelaMedico tela, int indice) {
		opcaoCrud = opcao;
		posicao = indice;
		controleTelaEdicaoMedico = dados;
		if(opcao == 1) {
			// sem dados
			txtNome = new JTextField(200);
			txtEmail = new JTextField(200);
			txtCrm = new JTextField(200);
			txtEspecialidade = new JTextField(200);
			txtTelefone = new JTextField(11);
			//salvar.setBounds(245, 175, 115, 30);
		}
		else if(opcao == 2) {
			txtNome = new JTextField(dados.getMedicos()[indice].getNome(), 30);
			txtEmail = new JTextField(dados.getMedicos()[indice].getEmail(), 30);
			txtTelefone = new JTextField(dados.getMedicos()[indice].getTelefone(), 11);
			txtCrm = new JTextField(dados.getMedicos()[indice].getCrm(), 30);
			txtEspecialidade = new JTextField(dados.getMedicos()[indice].getEspecialidade(), 30);
		}
		
		this.frame = new JFrame();
		this.frame.setSize(600, 600);
		
		txtNome.setSize(60, 20);
		txtEmail.setSize(60, 20);
		txtTelefone.setSize(60, 20);
		txtCrm.setSize(60, 20);
		txtEspecialidade.setSize(60, 20);
		
		this.frame.setLayout(new FlowLayout());
		this.frame.add(labelNome);
		this.frame.add(txtNome);
		this.frame.add(labelEmail);
		this.frame.add(txtEmail);
		this.frame.add(labelTelefone);
		this.frame.add(txtTelefone);
		this.frame.add(labelCrm);
		this.frame.add(txtCrm);
		this.frame.add(labelEspecialidade);
		this.frame.add(txtEspecialidade);
		this.frame.add(salvar);
		this.frame.add(excluir);
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		this.frame.setVisible(true);
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
					mensagemSucessoCadastro();
				}
				else{
					mensagemErroCadastro();
				}
			
			}
			catch(NullPointerException exc1) {
				mensagemErroCadastro();
			}
			catch(NumberFormatException exc2) {
				mensagemErroCadastro();
			}
		}
		if(fonte == excluir) {
			boolean resultado = false;
			resultado = controleTelaEdicaoMedico.removerMedico(posicao);
			if (resultado) mensagemSucessoExclusao(); 
			else mensagemErroExclusaoAluno(); 
		}
	}
	
	// Mudar esses metodos aqui lembra disso cabeca oca
	
	public void mensagemSucessoExclusao() {
		JOptionPane.showMessageDialog(null, "Os dados foram excluidos com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

	public void mensagemSucessoCadastro() {
		JOptionPane.showMessageDialog(null, "Os dados foram salvos com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

	public void mensagemErroCadastro() {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Nem todos os campos foram preenchidos \n"
				+ "2. CPF, identidade, DDD e telefone não contém apenas números", null, 
				JOptionPane.ERROR_MESSAGE);
	}

	public void mensagemErroExclusaoAluno() {
		JOptionPane.showMessageDialog(null,"Ocorreu um erro ao excluir o dado.\n "
				+ "Verifique se o aluno está matriculado\n"
				+ "em alguma disciplina. Se sim, cancele\n "
				+ "a matricula e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void mensagemErroExclusaoProf() {
		JOptionPane.showMessageDialog(null,"Ocorreu um erro ao excluir o dado.\n "
				+ "Verifique se o professor está responsável\n"
				+ "por alguma disciplina. Se sim, substitua\n "
				+ "o professor e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
	}
}
