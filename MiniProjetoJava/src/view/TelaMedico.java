package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controle.*;


public class TelaMedico implements ActionListener, ListSelectionListener {
	private String[] nomesMedicos = new String[40];
	//private Object[][] matrizTabela = new Object[40][2];
	//private DefaultTableModel modeloTabela = new DefaultTableModel();
	private JList<String> listaMedicos;
	private JFrame frame;
	private JLabel label;
	private JButton cadastrarMedico;
	private JButton refreshMedico;
	private static ControleDados dadosTelaMedico;
	
	public void mostrarDados(ControleDados dados) {
		dadosTelaMedico = dados;
		//tabelaMedicos = new JTable(modeloTabela);
		//modeloTabela.addColumn("Nome");
		//modeloTabela.addColumn("Especialidade");
		//tabelaMedicos.getColumnModel().getColumn(0).setPreferredWidth(150);
		//tabelaMedicos.getColumnModel().getColumn(1).setPreferredWidth(150);
		//preencherMedicos(dados);
		//tabelaMedicos.setEnabled(false);
		nomesMedicos = new ControleMedico(dados).getNome();
		frame = new JFrame("Medicos");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		label = new JLabel("Lista de medicos");
		listaMedicos = new JList<String>(nomesMedicos);
		cadastrarMedico = new JButton("Cadastrar");
		refreshMedico = new JButton("Refresh");
		
		cadastrarMedico.setBounds(20, 60, 100, 30);

		
		frame.add(label);
		//frame.add(tabelaMedicos);
		frame.add(listaMedicos);
		frame.add(cadastrarMedico);
		frame.add(refreshMedico);

		
		frame.setSize(600, 600);
		frame.setVisible(true);
		
		cadastrarMedico.addActionListener(this);
		refreshMedico.addActionListener(this);
		listaMedicos.addListSelectionListener(this);
	}
	// acredito que devo colocar no controle
	/*public void preencherMedicos(ControleDados dados) {
		medicos = dados.getMedicos();
		modeloTabela.setNumRows(0);
		for(int i = 0; i < dados.getQtdMedicos(); i++) {
			modeloTabela.addRow(new Object[] {medicos[i].getNome(), medicos[i].getEspecialidade()});
		}
	}*/
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarMedico) {
			new TelaCadastroEdicaoMedico().inserirEditarMedico(1, dadosTelaMedico, this, 0);
			//new TelaCadastroEdicaoPaciente().inserirEditarPaciente(1, dadosTelaPaciente, this, 0);
		}
		if(fonte == refreshMedico) {
			listaMedicos.setListData(new ControleMedico(dadosTelaMedico).getNome());			
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
