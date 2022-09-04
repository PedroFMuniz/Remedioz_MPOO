package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controle.*;
import modelo.Remedio;

public class TelaRemedio {
	private Remedio[] remedios = new Remedio[40];
	private DefaultTableModel modeloTabela = new DefaultTableModel();
	
	private JFrame frame;
	private JLabel label;
	private JTable tabelaRemedios;
	private JButton cadastrarRemedio;
	private JButton editarRemedio;
	private JButton deletarRemedio;
	public void mostrarDados(ControleDados dados) {
		tabelaRemedios = new JTable(modeloTabela);
		modeloTabela.addColumn("Nome");
		modeloTabela.addColumn("Efeito");
		modeloTabela.addColumn("Tipo");
		modeloTabela.addColumn("Via de Uso");
		tabelaRemedios.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabelaRemedios.getColumnModel().getColumn(1).setPreferredWidth(130);
		tabelaRemedios.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabelaRemedios.getColumnModel().getColumn(3).setPreferredWidth(40);
		preencherRemedios(dados);
		
		frame = new JFrame("Remedios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		label = new JLabel("Lista de remedios");
		cadastrarRemedio = new JButton("Cadastrar");
		editarRemedio = new JButton("Editar");
		deletarRemedio = new JButton("Deletar");
		
		cadastrarRemedio.setBounds(20, 60, 100, 30);
		editarRemedio.setBounds(20, 60, 100, 30);
		deletarRemedio.setBounds(20, 60, 100, 30);
		
		frame.add(label);
		frame.add(tabelaRemedios);
		frame.add(cadastrarRemedio);
		frame.add(editarRemedio);
		frame.add(deletarRemedio);
		
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	public void preencherRemedios(ControleDados dados) {
		remedios = dados.getRemedios();
		modeloTabela.setNumRows(0);
		for(int i = 0; i < dados.getQtdRemedios(); i++) {
			modeloTabela.addRow(new Object[] {remedios[i].getNome(), remedios[i].getEfeito(),
					remedios[i].getTipo(), remedios[i].getViaDeUso()});
		}
	}
}
