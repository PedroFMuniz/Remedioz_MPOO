package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controle.*;


public class TelaMedico implements ActionListener, ListSelectionListener {
	private String[] infosMedicos = new String[40];
	//private String nomeMedico;
	//private Object[][] matrizTabela = new Object[40][2];
	//private DefaultTableModel modeloTabela = new DefaultTableModel();
	private JList<String> listaMedicos;
	private String[] pesquisaMedicos = new String[40];
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
		panel.setLayout(null);
		listaMedicos.setSize(400, 400);
		scroll = new JScrollPane();
		scroll.setViewportView(listaMedicos);
		listaMedicos.setLayoutOrientation(JList.VERTICAL);
		scroll.setBounds(0, 0, 350, 200);
		//listaMedicos.setPrototypeCellValue(String.format("%40s", ""));
		panel.add(scroll);
		
		label.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		listaMedicos.setVisibleRowCount(5);
		cadastrarMedico.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		refreshMedico.setBounds(220, 300, 150, 30);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		btnPesquisa.addActionListener(this);
		listaMedicos.addListSelectionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarMedico) {
			new TelaCadastroEdicaoMedico().inserirEditarMedico(1, dadosTelaMedico, 0);
		}
		if(fonte == refreshMedico) {
			listaMedicos.setListData(new ControleMedico(dadosTelaMedico).getInfo());			
			listaMedicos.updateUI();
		}
		if(fonte == btnPesquisa) {
			pesquisaMedicos = new ControleMedico(dadosTelaMedico).getInfo(txtPesquisa.getText());
			if(txtPesquisa.getText().equals("")) {
				listaMedicos.setListData(new ControleMedico(dadosTelaMedico).getInfo());			
				listaMedicos.updateUI();
			}
			else {
				listaMedicos.setListData(pesquisaMedicos);			
				listaMedicos.updateUI();
			}
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaMedicos) {
			String split[] = listaMedicos.getSelectedValue().split(" - ");
			new TelaCadastroEdicaoMedico().inserirEditarMedico(2, dadosTelaMedico, Integer.parseInt(split[0]));
			//new TelaCadastroEdicaoPaciente().inserirEditarPaciente(2, dadosTelaPaciente, this, listaPacientes.getSelectedIndex());
		}
	}
}
