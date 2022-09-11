package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import javax.swing.*;
import controle.*;

public class TelaHorario implements ActionListener{
	private String[] strDiasSemana = {"Segunda", "Terça", "Quarta",
			"Quinta", "Sexta", "Sabado", "Domingo"};
	private JComboBox<Integer> hora;
	private JComboBox<Integer> minuto;
	private JComboBox<String> diasSemana;
	private JButton salvar;
	private JLabel titulo = new JLabel("Novo horario");
	private JLabel labelHorario = new JLabel("Horario:");
	private JLabel labelDiaSemana = new JLabel("Dia da semana:");
	private JFrame frame;
	private ControleDados controleHorario;
	private int idAg;
	
	public void mostrarHorarios(ControleDados dados, int idAgendamento) {
		controleHorario = dados;
		idAg = idAgendamento;
		frame = new JFrame("Horario");
		hora = new JComboBox<Integer>(preencheCombo(1));
		minuto = new JComboBox<Integer>(preencheCombo(2));
		diasSemana = new JComboBox<String>(strDiasSemana);
		salvar = new JButton("Salvar");
		
		frame.setSize(260, 200);
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		hora.setFont(new Font("Arial", Font.BOLD, 14));
		minuto.setFont(new Font("Arial", Font.BOLD, 14));
		diasSemana.setFont(new Font("Arial", Font.BOLD, 14));
		labelDiaSemana.setFont(new Font("Arial", Font.BOLD, 14));
		labelHorario.setFont(new Font("Arial", Font.BOLD, 14));
		
		titulo.setBounds(70, 0, 100, 30);
		labelDiaSemana.setBounds(10, 30, 150, 30);
		labelHorario.setBounds(10, 70, 100, 30);
		diasSemana.setBounds(130, 30, 100, 30);
		hora.setBounds(80, 70, 50, 30);
		minuto.setBounds(150, 70, 50, 30);
		salvar.setBounds(70, 120, 100, 30);
		
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(labelDiaSemana);
		frame.add(labelHorario);
		frame.add(titulo);
		frame.add(hora);
		frame.add(minuto);
		frame.add(diasSemana);
		frame.add(salvar);
		salvar.addActionListener(this);
	}
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
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {

			try {
				boolean resultado;
				String diaS = diasSemana.getSelectedItem().toString();
				int horaS = hora.getSelectedIndex();
				int minS = minuto.getSelectedIndex();
				LocalTime lt = LocalTime.of(horaS, minS);
				resultado = controleHorario.inserirHorarioAgendamento(idAg, diaS, lt);
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
			int horaS = hora.getSelectedIndex();
			int minS = minuto.getSelectedIndex();
			LocalTime lt = LocalTime.of(horaS, minS);
			controleHorario.inserirHorarioAgendamento(idAg, diaS, lt);
			frame.dispose();

		}
	}
	private void mensagemErroHorario() {
		JOptionPane.showMessageDialog(null, "ERRO \nHouve um erro ao tentar cadastrar o horario.\nVerifique se o horario e igual a outro ja cadastrado.", null, JOptionPane.ERROR_MESSAGE);
	}
	private void mesagemSucessoHorario() {
		frame.dispose();
	}
}
