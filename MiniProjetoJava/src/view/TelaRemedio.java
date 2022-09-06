package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


import controle.*;

public class TelaRemedio implements ActionListener, ListSelectionListener{
	private String[] nomesRemedios = new String[40];
	
	private JFrame frame;
	private JLabel label;
	private JList<String> listaRemedios;
	private JButton cadastrarRemedio;
	private JButton refreshRemedio;
	private static ControleDados dadosTelaRemedio;
	public void mostrarDados(ControleDados dados) {

		dadosTelaRemedio = dados;
		
		frame = new JFrame("Remedios");
		nomesRemedios = new ControleRemedio(dados).getInfo();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		label = new JLabel("Lista de remedios");
		cadastrarRemedio = new JButton("Cadastrar");
		refreshRemedio = new JButton("Refresh");
		listaRemedios = new JList<String>(nomesRemedios);
		
		
		
		frame.add(label);
		frame.add(listaRemedios);
		frame.add(cadastrarRemedio);
		frame.add(refreshRemedio);
		
		cadastrarRemedio.addActionListener(this);
		refreshRemedio.addActionListener(this);
		listaRemedios.addListSelectionListener(this);
		
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarRemedio) {
			new TelaCadastroEdicaoRemedio().inserirEditarRemedio(1, dadosTelaRemedio, this, 0);
		}
		else if(fonte == refreshRemedio) {
			listaRemedios.setListData(new ControleRemedio(dadosTelaRemedio).getInfo());			
			listaRemedios.updateUI();
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaRemedios) {
			new TelaCadastroEdicaoRemedio().inserirEditarRemedio(2, dadosTelaRemedio, this, listaRemedios.getSelectedIndex());
		}
	}
	/*public void preencherRemedios(ControleDados dados) {
		remedios = dados.getRemedios();
		modeloTabela.setNumRows(0);
		for(int i = 0; i < dados.getQtdRemedios(); i++) {
			modeloTabela.addRow(new Object[] {remedios[i].getNome(), remedios[i].getEfeito(),
					remedios[i].getTipo(), remedios[i].getViaDeUso()});
		}
	}*/
}
