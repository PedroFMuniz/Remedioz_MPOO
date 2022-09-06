package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controle.*;
import modelo.Paciente;


public class TelaPaciente implements ActionListener, ListSelectionListener {
	private Paciente[] pacientes = new Paciente[40];
	private JFrame frame;
	private JLabel label;
	private String[] infosPacientes = new String[40];
	private JButton cadastrarPaciente;
	private JButton refreshPaciente;
	private JList<String> listaPacientes;
	private JScrollPane scroll;
	private static ControleDados dadosTelaPaciente;
	
	public void mostrarDados(ControleDados dados) {
		dadosTelaPaciente = dados;
		infosPacientes = new ControlePaciente(dados).getInfo();
		frame = new JFrame("Pacientes");
		label = new JLabel("Lista de pacientes");
		cadastrarPaciente = new JButton("Cadastrar");
		refreshPaciente = new JButton("Refresh");
		listaPacientes = new JList<String>(infosPacientes);
		scroll = new JScrollPane(listaPacientes);
		
		frame.add(label);
		frame.add(cadastrarPaciente);
		frame.add(refreshPaciente);
		frame.add(listaPacientes);
		frame.add(scroll);
		
		
		frame.setSize(600, 600);
		
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		cadastrarPaciente.addActionListener(this);
		refreshPaciente.addActionListener(this);
		listaPacientes.addListSelectionListener(this);
	}
	
	//implementar esses dois
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarPaciente) {
			new TelaCadastroEdicaoPaciente().inserirEditarPaciente(1, dadosTelaPaciente, this, 0);
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaPacientes) {
			new TelaCadastroEdicaoPaciente().inserirEditarPaciente(2, dadosTelaPaciente, this, listaPacientes.getSelectedIndex());
		}
	}
}
