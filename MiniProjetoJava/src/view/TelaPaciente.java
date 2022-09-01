package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controle.*;
import modelo.Paciente;


public class TelaPaciente implements ActionListener, ListSelectionListener {
	private String[] listaNomes = new String[40];
	private Paciente[] intermedio = new Paciente[40];
	private JFrame frame;
	private JLabel label;
	private JList<String> pacientesCadastrados;
	private JButton cadastrarPaciente;
	private JButton editarPaciente;
	private JButton deletarPaciente;
	
	// preciso poder pegar os nomes sem fazer esse for (isso tem que estar em modelo)
	public void mostrarDados(controleDados dados) {
		for(int i = 0; i < 10; i++) {
			intermedio = dados.getPacientes();
			listaNomes[i] = intermedio[i].getNome();
		}
		frame = new JFrame("Pacientes");
		label = new JLabel("Lista de pacientes");
		pacientesCadastrados = new JList<String>(listaNomes);
		cadastrarPaciente = new JButton("Cadastrar");
		editarPaciente = new JButton("Editar");
		deletarPaciente = new JButton("Deletar");
		
		frame.add(label);
		frame.add(pacientesCadastrados);
		frame.add(cadastrarPaciente);
		frame.add(editarPaciente);
		frame.add(deletarPaciente);
		
		frame.setSize(600, 600);
		
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//implementar esses dois
	public void actionPerformed(ActionEvent e) {
		
	}
	public void valueChanged(ListSelectionEvent e) {
		
	}
}
