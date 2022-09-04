package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controle.*;
import modelo.Medico;

public class TelaMedico implements ActionListener, ListSelectionListener {
	private Medico[] medicos = new Medico[40];
	//private Object[][] matrizTabela = new Object[40][2];
	private DefaultTableModel modeloTabela = new DefaultTableModel();
	
	private JFrame frame;
	private JLabel label;
	private JTable tabelaMedicos;
	private JButton cadastrarMedico;
	private JButton editarMedico;
	private JButton deletarMedico;
	
	public void mostrarDados(ControleDados dados) {
		
		tabelaMedicos = new JTable(modeloTabela);
		modeloTabela.addColumn("Nome");
		modeloTabela.addColumn("Especialidade");
		tabelaMedicos.getColumnModel().getColumn(0).setPreferredWidth(150);
		tabelaMedicos.getColumnModel().getColumn(1).setPreferredWidth(150);
		preencherMedicos(dados);
		
		frame = new JFrame("Medicos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		label = new JLabel("Lista de medicos");
		cadastrarMedico = new JButton("Cadastrar");
		editarMedico = new JButton("Editar");
		deletarMedico = new JButton("Deletar");
		
		cadastrarMedico.setBounds(20, 60, 100, 30);
		editarMedico.setBounds(20, 60, 100, 30);
		deletarMedico.setBounds(20, 60, 100, 30);
		
		frame.add(label);
		frame.add(tabelaMedicos);
		frame.add(cadastrarMedico);
		frame.add(editarMedico);
		frame.add(deletarMedico);
		
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	// acredito que devo colocar no controle
	public void preencherMedicos(ControleDados dados) {
		medicos = dados.getMedicos();
		modeloTabela.setNumRows(0);
		for(int i = 0; i < dados.getQtdMedicos(); i++) {
			modeloTabela.addRow(new Object[] {medicos[i].getNome(), medicos[i].getEspecialidade()});
		}
	}
	public void actionPerformed(ActionEvent e) {
		
	}
	public void valueChanged(ListSelectionEvent e) {
		
	}
}
