package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controle.*;
import modelo.Paciente;

public class TelaCadastroEdicaoPaciente implements ActionListener, ListSelectionListener{
	private JFrame frame;
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JLabel labelEmail = new JLabel("Email: ");
	private JLabel labelDoencas = new JLabel("Historico de doencas: ");
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtDoencas;
	private JButton excluir = new JButton("Excluir");
	private JButton salvar = new JButton("Salvar");
	
	
	public void inserirEditarPaciente(int opcao, ControleDados dados, TelaPaciente tela, int indice) {
		
		if(opcao == 1) {
			// sem dados
			txtNome = new JTextField(200);
			txtEmail = new JTextField(200);
			txtDoencas = new JTextField(200);
			txtTelefone = new JTextField(11);
			//salvar.setBounds(245, 175, 115, 30);
		}
		else if(opcao == 2) {
			txtNome = new JTextField(dados.getPacientes()[indice].getNome(), 60);
			txtEmail = new JTextField(dados.getPacientes()[indice].getEmail(), 60);
			txtTelefone = new JTextField(dados.getPacientes()[indice].getTelefone(), 11);
			txtDoencas = new JTextField(dados.getPacientes()[indice].getHistoricoDoencas(), 60);
		}
		
		this.frame = new JFrame();
		this.frame.setSize(600, 600);
		
		txtNome.setSize(100, 20);
		txtEmail.setSize(100, 20);
		txtTelefone.setSize(100, 20);
		txtDoencas.setSize(100, 20);
		
		this.frame.setLayout(new FlowLayout());
		this.frame.add(labelNome);
		this.frame.add(txtNome);
		this.frame.add(labelEmail);
		this.frame.add(txtEmail);
		this.frame.add(labelTelefone);
		this.frame.add(txtTelefone);
		this.frame.add(labelDoencas);
		this.frame.add(txtDoencas);
		this.frame.add(salvar);
		this.frame.add(excluir);
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		this.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		
	}
	public void valueChanged(ListSelectionEvent e) {
		
	}
}
