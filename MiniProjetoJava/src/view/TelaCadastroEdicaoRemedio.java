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


public class TelaCadastroEdicaoRemedio implements ActionListener {
	private JFrame frame;
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
	private static int posicao;
	private static ControleDados controleTelaEdicaoRemedio;
	
	public void inserirEditarRemedio(int opcao, ControleDados dados, TelaRemedio tela, int indice) {
		opcaoCrud = opcao;
		posicao = indice;
		controleTelaEdicaoRemedio = dados;
		frame = new JFrame("Cadastro e edicao de Remedios");
		if(opcao == 1) {
			txtNome = new JTextField(30);
			txtEfeito = new JTextField(30);
			txtTipo = new JTextField(30);
			txtViaUso = new JTextField(30);
			this.frame.add(salvar);
		}
		if(opcao == 2) {
			txtNome = new JTextField(dados.getRemedios()[indice].getNome(), 30);
			txtEfeito = new JTextField(dados.getRemedios()[indice].getEfeito(), 30);
			txtTipo = new JTextField(dados.getRemedios()[indice].getTipo(), 30);
			txtViaUso = new JTextField(dados.getRemedios()[indice].getViaDeUso(), 30);
			this.frame.add(salvar);
			this.frame.add(excluir);
		}
		this.frame.setLayout(new FlowLayout());
		
		this.frame.add(labelNome);
		this.frame.add(txtNome);
		this.frame.add(labelEfeito);
		this.frame.add(txtEfeito);
		this.frame.add(labelTipo);
		this.frame.add(txtTipo);
		this.frame.add(labelViaUso);
		this.frame.add(txtViaUso);
		
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
					// novo remedio
					novoCadastro[0] = Integer.toString(controleTelaEdicaoRemedio.getQtdMedicos());
				}
				else {
					//edicao
					novoCadastro[0] = Integer.toString(posicao);
				}
				novoCadastro[1] =  txtNome.getText();
				novoCadastro[2] =  txtEfeito.getText();
				novoCadastro[3] =  txtTipo.getText();
				novoCadastro[4] =  txtViaUso.getText();
				resultado = controleTelaEdicaoRemedio.inserirEditarRemedio(novoCadastro);
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
			resultado = controleTelaEdicaoRemedio.removerRemedio(posicao);
			if (resultado) mensagemSucessoExclusao(); 
			else mensagemErroExclusaoAluno(); 
		}
	}
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