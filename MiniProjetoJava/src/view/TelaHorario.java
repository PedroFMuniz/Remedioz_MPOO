package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controle.*;

public class TelaHorario implements ActionListener{
	private String[] strDiasSemana = {"Segunda", "Terca", "Quarta",
			"Quinta", "Sexta", "Sabado", "Domingo"};
	private JComboBox<Integer> hora;
	private JComboBox<Integer> minuto;
	private JComboBox<String> diasSemana;
	private JButton salvar;
	private JFrame frame;
	
	public void mostrarHorarios(ControleDados dados) {
		frame = new JFrame("Horario");
		hora = new JComboBox<Integer>(preencheCombo(1));
		minuto = new JComboBox<Integer>(preencheCombo(2));
		diasSemana = new JComboBox<String>(strDiasSemana);
		salvar = new JButton("Salvar");
		
		frame.setSize(200, 200);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
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
			String diaS = diasSemana.getSelectedItem().toString();
			String horaS = Integer.toString(hora.getSelectedIndex());
			String minS = Integer.toString(minuto.getSelectedIndex());
			System.out.println(horaS + " " + minS);
			TelaAgendamento ag = new TelaAgendamento();
			ag.updateListaHorarios(diaS + ", " + horaS + ":" + minS);
		}
	}
}
