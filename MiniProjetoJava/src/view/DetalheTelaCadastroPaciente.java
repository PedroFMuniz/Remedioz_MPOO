package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controle.ControleDados;
import controle.ControleRemedio;

public class DetalheTelaCadastroPaciente implements ActionListener{
	private JList<String> listaRemedios;
	private JButton addAlergias;
	private JFrame frame;
	private String[] nomesRemedios = new String[40];
	
	public void mostrarLista(ControleDados dados) {
		nomesRemedios = new ControleRemedio(dados).getNome();
		addAlergias = new JButton("Adicionar");
		listaRemedios = new JList<String>(nomesRemedios);
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		listaRemedios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		frame.setVisible(true);
		addAlergias.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergias) {
			
		}
		
	}
}
