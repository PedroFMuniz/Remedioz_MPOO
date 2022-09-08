package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controle.*;
import modelo.*;

public class TelaAgendamento implements ActionListener{
	// sujeito a mudancas
	private JComboBox<Object> remedios;
	private JComboBox<Object> medicos;
	//private JList<String> diasSemana;
	private JComboBox<Object> dataInicioDia;
	private JComboBox dataInicioMes;
	private JComboBox dataInicioAno;
	private JComboBox dataFimDia;
	private JComboBox dataFimMes;
	private JComboBox dataFimAno;
	private JComboBox hora;
	private JComboBox minuto;
	private JList<String> listaHorarios;
	private JButton btnHorario = new JButton("Novo horario");
	private JButton salvar = new JButton("Salvar");
	private JButton refreshHorario = new JButton("Refresh...");
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private int idPaciente;
	private int opc;
	private ControleDados controleDadosAgendamento;
	private String[] novoCadastro = new String[10];
	
	public void inserirEditarAgendamento(int opcao, ControleDados dados, int id) {
		idPaciente = id;
		opc = opcao;
		controleDadosAgendamento = dados;
		frame = new JFrame("Agendamentos");
		frame.setSize(600,600);
		remedios = new JComboBox<Object>(new ControleRemedio(controleDadosAgendamento).getInfo());
		medicos = new JComboBox<Object>(new ControleMedico(controleDadosAgendamento).getInfo());
		dataInicioDia = new JComboBox<Object>(preencheDias());
		dataInicioMes = new JComboBox<Object>(preencheMeses());
		dataInicioAno = new JComboBox<Object>(preencheAnos());
		dataFimDia = new JComboBox<Object>(preencheDias());
		dataFimMes = new JComboBox<Object>(preencheMeses());
		dataFimAno = new JComboBox<Object>(preencheAnos());
		//diasSemana = new JList<String>(strDiasSemana);
		//diasSemana.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		
		listaHorarios = new JList<String>();
		listaHorarios.setVisibleRowCount(5);
		listaHorarios.setPrototypeCellValue(String.format("%60s", ""));
		
		scroll = new JScrollPane();
		scroll.setViewportView(listaHorarios);
		listaHorarios.setLayoutOrientation(JList.VERTICAL);
		
		frame.setLayout(new FlowLayout());
		frame.add(remedios);
		frame.add(medicos);
		frame.add(dataInicioDia);
		frame.add(dataInicioMes);
		frame.add(dataInicioAno);
		frame.add(dataFimDia);
		frame.add(dataFimMes);
		frame.add(dataFimAno);
		//frame.add(diasSemana);
		panel.add(scroll);
		frame.add(panel);
		frame.add(btnHorario);
		frame.add(refreshHorario);
		frame.setVisible(true);
		
		salvar.addActionListener(this);
		btnHorario.addActionListener(this);
		refreshHorario.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {
			try {
				if(opc == 1) {
					novoCadastro[0] = Integer.toString(controleDadosAgendamento.getQtdAgendamentos());
				}
				
				
			}
			catch(NullPointerException exc1) {
				
			}
			catch(NumberFormatException exc1) {
				
			}
		}
		else if(fonte == btnHorario) {
			new TelaHorario().mostrarHorarios(controleDadosAgendamento);
		}
		else if(fonte == refreshHorario) {
			listaHorarios.setSelectedIndex(0);
			for(int i = 0; i < 40; i++) {
				if(listaHorarios.getSelectedValue() != "") {
					listaHorarios.setSelectedIndex(i + 1);
				}
				else {
					listaHorarios.setSelectedValue(e, false);
				}
			}
		}
	}
	public Integer[] preencheDias() {
		Integer[] arrayDias = new Integer[31];
		for(int i = 0; i < 31; i++) {
			arrayDias[i] = i + 1;
		}
		return arrayDias; 
	}
	public Integer[] preencheMeses() {
		Integer[] arrayMeses = new Integer[12];
		for(int i = 0; i < 12; i++) {
			arrayMeses[i] = i + 1;
		}
		return arrayMeses; 
	}
	public Integer[] preencheAnos() {
		int ano = 2022;
		Integer[] arrayAnos = new Integer[10];
		for(int i = 0; i < 10; i++) {
			arrayAnos[i] = ano;
			ano++;
		}
		return arrayAnos; 
	}
	public String updateListaHorarios(String str) {
		return str;
	}
}
