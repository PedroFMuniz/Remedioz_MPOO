package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


import controle.*;

public class TelaRemedio implements ActionListener, ListSelectionListener{
	private String[] infosRemedios = new String[40];
	
	private JFrame frame;
	private JLabel label;
	private JList<String> listaRemedios;
	private JButton btnPesquisa;
	private JButton cadastrarRemedio;
	private JButton refreshRemedio;
	private JTextField txtPesquisa;
	private JScrollPane scroll;
	private JPanel panel = new JPanel();
	private static ControleDados dadosTelaRemedio;
	
	public void mostrarDados(ControleDados dados) {

		dadosTelaRemedio = dados;
		
		frame = new JFrame("Remedios");
		infosRemedios = new ControleRemedio(dados).getInfo();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		label = new JLabel("Lista de remedios");
		txtPesquisa = new JTextField("");
		btnPesquisa = new JButton("Pesquisar...");
<<<<<<< HEAD
=======
		infosRemedios = new ControleRemedio(dados).getInfo();
>>>>>>> branch 'main' of git@github.com:PedroFMuniz/Remedioz_MPOO.git
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		label = new JLabel("Lista de remédios");
		cadastrarRemedio = new JButton("Cadastrar");
		refreshRemedio = new JButton("Refresh");
		listaRemedios = new JList<String>(infosRemedios);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		listaRemedios.setFont(new Font("Arial", Font.BOLD, 24));
		scroll = new JScrollPane();
		scroll.setViewportView(listaRemedios);
		listaRemedios.setLayoutOrientation(JList.VERTICAL);
		panel.add(scroll);
		
		label.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		//listaRemedios.setBounds(20, 80, 350, 200);
		//scroll.setBounds(0, 0, 350, 200);
		listaRemedios.setVisibleRowCount(5);
		cadastrarRemedio.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		panel.setBackground(Color.CYAN);
		refreshRemedio.setBounds(220, 300, 150, 30);
		
		frame.add(label);
		frame.add(txtPesquisa);
		frame.add(btnPesquisa);
		frame.add(panel);
		frame.add(cadastrarRemedio);
		frame.add(refreshRemedio);
		
		cadastrarRemedio.addActionListener(this);
		refreshRemedio.addActionListener(this);
		listaRemedios.addListSelectionListener(this);
		
		frame.setSize(400, 400);
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
