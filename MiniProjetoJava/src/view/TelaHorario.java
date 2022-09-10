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
			try {
				boolean resultado;
				String diaS = diasSemana.getSelectedItem().toString();
				int horaS = hora.getSelectedIndex();
				int minS = minuto.getSelectedIndex();
				LocalTime lt = LocalTime.of(horaS, minS);
				resultado = controleHorario.manipularHorarioAgendamento(idAg, diaS, lt);
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
		}
	}
	private void mensagemErroHorario() {
		JOptionPane.showMessageDialog(null, "ERRO \nHouve um erro ao tentar cadastrar o horario.\nVerifique se o horario e igual a outro ja cadastrado.", null, JOptionPane.ERROR_MESSAGE);
	}
	private void mesagemSucessoHorario() {
		frame.dispose();
	}
}
