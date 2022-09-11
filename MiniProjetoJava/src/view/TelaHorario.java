package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controle.*;

/**
 * Classe "TelaHorario". Descreve uma tela com um titulo, duas ComboBoxes 
 * para o horario e uma para os dias da semana, alem de labels e um botao 
 * para salvar. Implementa a interface ActionListener. Gerencia o cadastro 
 * de horarios para um determinado "Agendamento".
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaHorario implements ActionListener{
	private String[] strDiasSemana = {"Segunda", "Terça", "Quarta",
			"Quinta", "Sexta", "Sabado", "Domingo"};
	private JComboBox<Integer> cmbHora;
	private JComboBox<Integer> cmbMinuto;
	private JComboBox<String> diasSemana;
	private JButton salvar;
	private JLabel titulo = new JLabel("Novo horario");
	private JLabel labelHorario = new JLabel("Horario:");
	private JLabel labelDiaSemana = new JLabel("Dia da semana:");
	private JFrame frame;
	private ControleDados controleHorario;
	private int idAg;
	/**
	 * Metodo que descreve a tela "TelaHorario" para renderizacao. Adiciona
	 * um ActionListener no botao "salvar".
	 * 
	 * @param dados : A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @param idAgendamento : O atributo "id" do agendamento ao qual sera adicionado
	 * o horario
	 * @see TelaAgendamento
	 * @return void
	 */
	public void mostrarHorarios(ControleDados dados, int idAgendamento) {
		
		// Definicoes para uso em outros metodos
		controleHorario = dados;
		idAg = idAgendamento;
		
		// Criacao dos elementos da tela
		frame = new JFrame("Horario");
		frame.setLayout(null);
		cmbHora = new JComboBox<Integer>(preencheCombo(1));
		cmbMinuto = new JComboBox<Integer>(preencheCombo(2));
		diasSemana = new JComboBox<String>(strDiasSemana);
		salvar = new JButton("Salvar");
		
		frame.setSize(260, 200);
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		cmbHora.setFont(new Font("Arial", Font.BOLD, 14));
		cmbMinuto.setFont(new Font("Arial", Font.BOLD, 14));
		diasSemana.setFont(new Font("Arial", Font.BOLD, 14));
		labelDiaSemana.setFont(new Font("Arial", Font.BOLD, 14));
		labelHorario.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Posicionamento dos itens
		titulo.setBounds(70, 0, 100, 30);
		labelDiaSemana.setBounds(10, 30, 150, 30);
		labelHorario.setBounds(10, 70, 100, 30);
		diasSemana.setBounds(130, 30, 100, 30);
		cmbHora.setBounds(80, 70, 50, 30);
		cmbMinuto.setBounds(150, 70, 50, 30);
		salvar.setBounds(70, 120, 100, 30);
		
		// Adicao ao JFrame
		frame.add(labelDiaSemana);
		frame.add(labelHorario);
		frame.add(titulo);
		frame.add(cmbHora);
		frame.add(cmbMinuto);
		frame.add(diasSemana);
		frame.add(salvar);
		
		salvar.addActionListener(this);
		
		frame.setVisible(true);
	}
	/**
	 * Metodo para preencher as JComboBoxes "hora" e "minuto" com os dados 
	 * necessarios. A depender da opcao, retorna uma lista de horas ou de minutos.
	 * 
	 * @param opcao : Determina qual das listas e retornada. Se igual a 1, a lista
	 * de horas e retornada. Se nao, a lista de minutos.
	 * @return Integer[]
	 */
	public Integer[] preencheCombo(int opcao) {
		if(opcao == 1) {
			Integer[] listaHoras = new Integer[24];
			for(int i = 0; i < 24; i++) {
				listaHoras[i] = i;
			}
			return listaHoras;
		}
		else {
			Integer[] listaMin = new Integer[60];
			for(int i = 0; i < 60; i++) {
				listaMin[i] = i;
			}
			return listaMin;
		}
	}
	/**
	 * Metodo que descreve o que deve acontecer a partir do clique no botao "salvar".
	 * Este metodo grava o horario no "Agendamento" em questao a partir de metodos de
	 * "ControleDados" e dos dados selecionados nas JComboBoxes.
	 * 
	 * @see TelaAgendamento
	 * @param e : ActionEvent usado para determinar a fonte da acao em "TelaHorario".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {

			try {
				boolean resultado;
				String diaS = diasSemana.getSelectedItem().toString();

				int horaS = cmbHora.getSelectedIndex();
				int minS = cmbMinuto.getSelectedIndex();
				resultado = controleHorario.inserirHorarioAgendamento(idAg, diaS, horaS, minS);
				if(resultado) {
					mesagemSucessoHorario();
				}
				else {
					mensagemErroHorario();
				}	
			}
			catch(NullPointerException exc1) {
				mensagemErroHorario();
			}
			catch(NumberFormatException exc1) {
				mensagemErroHorario();
			}
			String diaS = diasSemana.getSelectedItem().toString();
			int horaS = cmbHora.getSelectedIndex();
			int minS = cmbMinuto.getSelectedIndex();
			controleHorario.inserirHorarioAgendamento(idAg, diaS, horaS, minS);
			frame.dispose();

		}
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro no cadastro do horario.
	 * 
	 * @return void
	 */
	private void mensagemErroHorario() {
		JOptionPane.showMessageDialog(null, "ERRO \nHouve um erro ao tentar cadastrar o horario.\nVerifique se o horario e igual a outro ja cadastrado.", null, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro do horario.
	 * Fecha a janela.
	 * 
	 * @return void
	 */
	private void mesagemSucessoHorario() {
		JOptionPane.showMessageDialog(null, "O horario foi cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
}
