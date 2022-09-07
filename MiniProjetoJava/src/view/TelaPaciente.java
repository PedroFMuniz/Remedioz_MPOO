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
	private JPanel panel;
	private JTextField txtPesquisa;
	private JButton btnPesquisa;
	
	private static ControleDados dadosTelaPaciente;
	
	public void mostrarDados(ControleDados dados) {
		dadosTelaPaciente = dados;
		infosPacientes = new ControlePaciente(dados).getInfo();
		frame = new JFrame("Pacientes");
		label = new JLabel("Lista de pacientes");
		panel = new JPanel();
		cadastrarPaciente = new JButton("Cadastrar");
		refreshPaciente = new JButton("Refresh");
		listaPacientes = new JList<String>(infosPacientes);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		listaPacientes.setFont(new Font("Arial", Font.BOLD, 24));
		txtPesquisa = new JTextField();
		btnPesquisa = new JButton("Pesquisar...");
		scroll = new JScrollPane();
		scroll.setViewportView(listaPacientes);
		listaPacientes.setLayoutOrientation(JList.VERTICAL);
		frame.setLayout(null);
		
		label.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		listaPacientes.setVisibleRowCount(5);
		cadastrarPaciente.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		panel.setBackground(Color.CYAN);
		refreshPaciente.setBounds(220, 300, 150, 30);

		
		panel.add(scroll);
		frame.add(label);
		frame.add(txtPesquisa);
		frame.add(btnPesquisa);
		frame.add(cadastrarPaciente);
		frame.add(refreshPaciente);
		frame.add(panel);
		
		
		frame.setSize(400, 400);
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
