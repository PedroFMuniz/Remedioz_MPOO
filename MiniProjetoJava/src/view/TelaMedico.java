package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controle.*;


public class TelaMedico implements ActionListener, ListSelectionListener {
	private String[] infosMedicos = new String[40];
	//private Object[][] matrizTabela = new Object[40][2];
	//private DefaultTableModel modeloTabela = new DefaultTableModel();
	private JList<String> listaMedicos;
	private JFrame frame;
	private JLabel label;
	private JButton cadastrarMedico;
	private JButton refreshMedico;
	private JButton btnPesquisa;
	private JTextField txtPesquisa;
	private static ControleDados dadosTelaMedico;
	private JScrollPane scroll;
	private JPanel panel;
	
	public void mostrarDados(ControleDados dados) {
		dadosTelaMedico = dados;
		//tabelaMedicos = new JTable(modeloTabela);
		//modeloTabela.addColumn("Nome");
		//modeloTabela.addColumn("Especialidade");
		//tabelaMedicos.getColumnModel().getColumn(0).setPreferredWidth(150);
		//tabelaMedicos.getColumnModel().getColumn(1).setPreferredWidth(150);
		//preencherMedicos(dados);
		//tabelaMedicos.setEnabled(false);
		infosMedicos = new ControleMedico(dados).getInfo();
		frame = new JFrame("Medicos");
		label = new JLabel("Lista de medicos");
		listaMedicos = new JList<String>(infosMedicos);
		cadastrarMedico = new JButton("Cadastrar");
		frame.setLayout(null);
		txtPesquisa = new JTextField();
		btnPesquisa = new JButton("Pesquisar...");
		cadastrarMedico  = new JButton("Cadastrar");
		refreshMedico = new JButton("Refresh");
		listaMedicos = new JList<String>(infosMedicos);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		listaMedicos.setFont(new Font("Arial", Font.BOLD, 24));
		panel = new JPanel();
		listaMedicos.setSize(400, 400);
		scroll = new JScrollPane();
		scroll.setViewportView(listaMedicos);
		listaMedicos.setLayoutOrientation(JList.VERTICAL);
		panel.add(scroll);
		
		label.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		listaMedicos.setVisibleRowCount(5);
		cadastrarMedico.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		panel.setBackground(Color.CYAN);
		refreshMedico.setBounds(220, 300, 150, 30);

		
		
		panel.add(scroll);
		frame.add(label);
		frame.add(txtPesquisa);
		frame.add(btnPesquisa);
		frame.add(panel);
		frame.add(cadastrarMedico);
		frame.add(refreshMedico);

		
		frame.setSize(400, 400);
		frame.setVisible(true);
		
		cadastrarMedico.addActionListener(this);
		refreshMedico.addActionListener(this);
		listaMedicos.addListSelectionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarMedico) {
			new TelaCadastroEdicaoMedico().inserirEditarMedico(1, dadosTelaMedico, this, 0);
			//new TelaCadastroEdicaoPaciente().inserirEditarPaciente(1, dadosTelaPaciente, this, 0);
		}
		if(fonte == refreshMedico) {
			listaMedicos.setListData(new ControleMedico(dadosTelaMedico).getInfo());			
			listaMedicos.updateUI();
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaMedicos) {
			new TelaCadastroEdicaoMedico().inserirEditarMedico(2, dadosTelaMedico, this, listaMedicos.getSelectedIndex());
			//new TelaCadastroEdicaoPaciente().inserirEditarPaciente(2, dadosTelaPaciente, this, listaPacientes.getSelectedIndex());
		}
	}
}
