package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controle.*;

/**
 * Classe "TelaMenu". Contem o metodo "main" do programa. Descreve uma tela
 * simples, com 5 botoes e um label. Cada botao leva para outra tela com a
 * funcao de realizar o CRUD para cada classe do pacote "modelo".
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaMenu implements ActionListener {
	
	private static JFrame frame = new JFrame("Remedioz");
	private static JLabel titulo = new JLabel("Menu Principal");
	private static JButton btnPaciente = new JButton("Paciente");
	private static JButton btnMedico = new JButton("Medico");
	private static JButton btnRemedio = new JButton("Remedio");
	private static JButton btnHorario = new JButton("Horario");
	private static JButton btnAgendamento = new JButton("Agendamentos");
	
	public static ControleDados dados = new ControleDados();
	/**
	 * Construtor que descreve a tela de menu para renderizacao.
	 */
	public TelaMenu() {
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		frame.setSize(400, 150);
		frame.setLayout(null);
		titulo.setBounds(135, 0, 150 ,50);
		btnPaciente.setBounds(20, 60, 100, 30);
		btnMedico.setBounds(140, 60, 100, 30);
		btnRemedio.setBounds(260, 60, 100, 30);

		frame.add(titulo);
		frame.add(btnMedico);
		frame.add(btnPaciente);
		frame.add(btnRemedio);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		TelaMenu menu = new TelaMenu();
		btnPaciente.addActionListener(menu);
		btnMedico.addActionListener(menu);
		btnRemedio.addActionListener(menu);
		btnHorario.addActionListener(menu);
		btnAgendamento.addActionListener(menu);
	}
	public void actionPerformed(ActionEvent e) {
		Object fonteEvento = e.getSource();
		
		if(fonteEvento == btnPaciente) {
			new TelaPaciente().mostrarDados(dados);
		}
		else if(fonteEvento == btnMedico) {
			new TelaMedico().mostrarDados(dados);
		}
		else if(fonteEvento == btnRemedio) {
			new TelaRemedio().mostrarDados(dados);
		}
	}
}
