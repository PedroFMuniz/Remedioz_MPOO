package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controle.*;

/**
 * Classe "TelaMenu". Contem o metodo "main" do programa. Descreve uma tela
 * simples, com 3 botoes e um label. Cada botao leva para outra tela com  a
 * funcao de gerenciar os cadastrod de "Paciente", "Medico" e "Remedio".
 * Instancia a classe "ControleDados" pela unica vez no programa para armazenar
 * e manipular dados, bem como preencher as classes com informacoes padrao.
 * Implementa a interface ActionListener.
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaMenu implements ActionListener {
	
	// Criacao dos itens datela e instanciacao de ControleDados
	private static JFrame frame = new JFrame("Menu");
	private static JLabel titulo = new JLabel("Menu Principal");
	private static JButton btnPaciente = new JButton("Paciente");
	private static JButton btnMedico = new JButton("Médico");
	private static JButton btnRemedio = new JButton("Remédio");
	public static ControleDados dados = new ControleDados();
	
	/**
	 * Construtor que descreve a tela de menu para renderizacao. Posiciona os
	 * botoes e o label e adiciona-os ao JFrame.
	 */
	public TelaMenu() {
		
		// Definicao de tamanho e anulacao de layout
		frame.setSize(400, 150);
		frame.setLayout(null);
		
		// Posicionamento dos itens
		titulo.setBounds(135, 0, 150 ,50);
		btnPaciente.setBounds(20, 60, 100, 30);
		btnMedico.setBounds(140, 60, 100, 30);
		btnRemedio.setBounds(260, 60, 100, 30);

		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		
		// Adicao ao JFrame
		frame.add(titulo);
		frame.add(btnMedico);
		frame.add(btnPaciente);
		frame.add(btnRemedio);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	/**
	 * Metodo main do programa. Cria a tela "TelaMenu" e adiciona ActionListeners
	 * aos botoes para que 
	 * 
	 * @param args array de Strings passado quando o programa e iniciado por
	 * linha de comando.
	 * @return void
	 */
	public static void main(String[] args) {
		TelaMenu menu = new TelaMenu();
		btnPaciente.addActionListener(menu);
		btnMedico.addActionListener(menu);
		btnRemedio.addActionListener(menu);
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir das acoes registradas 
	 * em "TelaMenu" (no caso, cliques em botoes). Abre as telas de "Paciente", 
	 * "Medico" e "Remedio".
	 * 
	 * @param e ActionEvent usado para determinar a fonte da acao em "TelaMenu".
	 * @return void
	 */
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
