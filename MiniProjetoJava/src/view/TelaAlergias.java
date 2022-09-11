package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import controle.ControleDados;
import controle.ControleRemedio;

public class TelaAlergias implements ActionListener{
	private JComboBox<String> cmbRemedios;
	private JButton addAlergias;
	private JFrame frame;
	private String[] nomesRemedios = new String[40];
	private JLabel titulo = new JLabel("Cadastro de alergias");
	private JLabel labelAlergias = new JLabel("Alergias");
	private ControleDados controleDadosAlergia;
	private int id;
	
	public void mostrarLista(ControleDados dados, int idPaciente) {
		controleDadosAlergia = dados;
		id = idPaciente;
		nomesRemedios = new ControleRemedio(dados).getInfo();
		addAlergias = new JButton("Adicionar");
		cmbRemedios = new JComboBox<String>(nomesRemedios);
		frame = new JFrame("Alergias");
		frame.setSize(240, 200);
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		labelAlergias.setFont(new Font("Arial", Font.BOLD, 14));
		cmbRemedios.setFont(new Font("Arial", Font.BOLD, 14));
		
		titulo.setBounds(35, 0, 170, 30);
		labelAlergias.setBounds(10, 60, 70, 30);
		cmbRemedios.setBounds(80, 60, 140, 30);
		addAlergias.setBounds(55, 120, 120, 30);
		frame.setLayout(null);

		frame.add(titulo);
		frame.add(labelAlergias);
		frame.add(cmbRemedios);
		frame.add(addAlergias);
		frame.setVisible(true);
		addAlergias.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergias) {
			boolean resultado;
			resultado = controleDadosAlergia.inserirAlergiasPaciente(id, cmbRemedios.getSelectedIndex());
			if(resultado) {
				sucessoAdicionarAlergia();
			}
			else {
				erroAdicionarAlergia();
			}
		}
	}
	public void sucessoAdicionarAlergia() {
		JOptionPane.showMessageDialog(null, "A alergia foi adicionada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	public void erroAdicionarAlergia() {
		JOptionPane.showMessageDialog(null, "ERRO\n houve algum erro ao adicionar essa alergia. Selecione um item e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
		frame.dispose();
	}
}
