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
	private static int posicao;
	private static ControleDados controleTelaEdicaoRemedio;
	
	public void inserirEditarRemedio(int opcao, ControleDados dados, int indice) {
		opcaoCrud = opcao;
		posicao = indice;
		controleTelaEdicaoRemedio = dados;
		frame = new JFrame("Cadastro e edicao de Remedios");
		frame.setSize(320, 300);
		if(opcao == 1) {
			txtNome = new JTextField(30);
			txtEfeito = new JTextField(30);
			txtTipo = new JTextField(30);
			txtViaUso = new JTextField(15);
			titulo.setBounds(70, 0, 200, 30);
			salvar.setBounds(225, 220, 70, 30);
			frame.add(salvar);
		}
		if(opcao == 2) {
			titulo.setText("Edicao de remedio");
			txtNome = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getNome(indice), 30);
			txtEfeito = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getEfeito(indice), 30);
			txtTipo = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getTipo(indice), 30);
			txtViaUso = new JTextField(new ControleRemedio(controleTelaEdicaoRemedio).getViaDeUso(indice), 15);
			titulo.setBounds(50, 0, 200, 30);
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
		
		labelNome.setBounds(10, 35, 80, 30);
		labelEfeito.setBounds(10, 75, 80, 30);
		labelTipo.setBounds(10, 115, 80, 30);
		labelViaUso.setBounds(10, 155, 100, 30);
		txtNome.setBounds(60, 40, 200, 20);
		txtEfeito.setBounds(65, 80, 200, 20);
		txtTipo.setBounds(50, 120, 200, 20);
		txtViaUso.setBounds(95, 160, 200, 20);
		
		
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
					novoCadastro[0] = Integer.toString(posicao);
				}
				novoCadastro[1] =  txtNome.getText();
				novoCadastro[2] =  txtEfeito.getText();
				novoCadastro[3] =  txtTipo.getText();
				novoCadastro[4] =  txtViaUso.getText();
				resultado = controleTelaEdicaoRemedio.inserirEditarRemedio(novoCadastro);
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
			resultado = controleTelaEdicaoRemedio.removerRemedio(posicao);
			if (resultado) sucessoExcluir(); 
			else erroExcluir(); 
		}
	}
	public void sucessoExcluir() {
		JOptionPane.showMessageDialog(null, "O remedio foi excluido com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

	public void sucessoCadastrar() {
		JOptionPane.showMessageDialog(null, "O remedio foi salvo com sucesso!", null, 
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
