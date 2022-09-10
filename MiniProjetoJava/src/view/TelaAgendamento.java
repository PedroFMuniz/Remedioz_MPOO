package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	private int idAgendamento;
	private int opc;
	private int idPac;
	private ControleDados controleDadosAgendamento;
	private String[] novoCadastro = new String[10];
	private Agendamento agendamentoTemp;
	private Medico medico = new Medico();
	private Remedio remedio = new Remedio();
	private DiaDaSemana diaSem;
	private LocalDate data = LocalDate.now();
	private String[] agendamentoInicial = new String[10];
	
	public void inserirEditarAgendamento(int opcao, ControleDados dados, int idAg, int idPaciente) {
		idAgendamento = idAg;
		idPac = idPaciente;
		opc = opcao;
		controleDadosAgendamento = dados;
		
		// Agendamento temporario pra poder preencher
		//agendamentoTemp = new Agendamento(idAgendamento, data, data, medico, controleDadosAgendamento.getPacientes()[idPac], remedio, diaSem);
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
		listaHorarios = new JList<String>();
		if(opc == 1) {
			//sem dados
			// criar um agendamento sem nada
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//agendamentoInicial[0] = Integer.toString(idAgendamento);
			//agendamentoInicial[1] = LocalDate.now().format(formatter);
			//agendamentoInicial[2] = LocalDate.now().format(formatter);
			// perigoso
			//tome jeito rapaz
			//agendamentoInicial[3] = Integer.toString(39);
			//agendamentoInicial[4] = Integer.toString(39);
			//agendamentoInicial[5] = Integer.toString(39);
			//agendamentoInicial[6] = null;
			
			//controleDadosAgendamento.inserirEditarAgendamento(agendamentoInicial);
			listaHorarios.setVisibleRowCount(5);
			listaHorarios.setPrototypeCellValue(String.format("%60s", ""));
		}
		else if(opc == 2) {
			// preenchidos
			listaHorarios.setListData(new ControleAgendamento(controleDadosAgendamento).getInfo(idAg));
			LocalDate dataInicio = new ControleAgendamento(controleDadosAgendamento).getDtInicio(idAgendamento);
			LocalDate dataFim = new ControleAgendamento(controleDadosAgendamento).getDtFim(idAgendamento);
			Medico medicoAg = new ControleAgendamento(controleDadosAgendamento).getMedico(idAgendamento);
			Remedio remedioAg = new ControleAgendamento(controleDadosAgendamento).getRemedio(idAgendamento);
			String[] dataInicioString = dataInicio.toString().split("-");
			String[] dataFimString = dataFim.toString().split("-");
			dataInicioDia.setSelectedIndex(Integer.parseInt(dataInicioString[1]) - 1);
			dataInicioMes.setSelectedIndex(Integer.parseInt(dataInicioString[2]) - 1);
			dataInicioAno.setSelectedItem(Integer.parseInt(dataInicioString[0]));
			dataFimDia.setSelectedIndex(Integer.parseInt(dataFimString[1]) - 1);
			dataFimMes.setSelectedIndex(Integer.parseInt(dataFimString[2]) - 1);
			dataFimAno.setSelectedItem(Integer.parseInt(dataFimString[0]));
			medicos.setSelectedIndex(medicoAg.getId());
			remedios.setSelectedIndex(remedioAg.getId());
			System.out.println(dataInicio.toString() + " " + dataFim.toString());
			//dataInicioDia.setSelectedIndex();
			listaHorarios.updateUI();
			listaHorarios.setVisibleRowCount(5);
			listaHorarios.setPrototypeCellValue(String.format("%60s", ""));
		}
		
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
				/*if(opc == 1) {
					novoCadastro[0] = Integer.toString(controleDadosAgendamento.getQtdAgendamentos());
				}
				else {
					novoCadastro[0] = Integer.toString(idAgendamento);
				}
				LocalDate ld1 = LocalDate.of(Integer.parseInt(dataInicioAno.getSelectedItem().toString()), dataInicioMes.getSelectedIndex(), 
										dataInicioDia.getSelectedIndex());
				LocalDate ld2 = LocalDate.of(Integer.parseInt(dataFimAno.getSelectedItem().toString()), dataFimMes.getSelectedIndex(), 
						dataFimDia.getSelectedIndex());
				novoCadastro[1] = ld1.toString();
				novoCadastro[2] = ld2.toString();
				String[] separa = medicos.getSelectedItem().toString().split(" - ");
				novoCadastro[3] = separa[0];
				novoCadastro[4] = String.valueOf(idPac);
				separa = remedios.getSelectedItem().toString().split(" - ");
				novoCadastro[5] = separa[0];*/
				
			}
			catch(NullPointerException exc1) {
				
			}
			catch(NumberFormatException exc1) {
				
			}
		}
		else if(fonte == btnHorario) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate ld1 = LocalDate.of(Integer.parseInt(dataInicioAno.getSelectedItem().toString()), Integer.parseInt(dataInicioMes.getSelectedItem().toString()), Integer.parseInt(dataInicioDia.getSelectedItem().toString()));
			LocalDate ld2 = LocalDate.of(Integer.parseInt(dataFimAno.getSelectedItem().toString()), Integer.parseInt(dataFimMes.getSelectedItem().toString()), Integer.parseInt(dataFimDia.getSelectedItem().toString()));
			agendamentoInicial[0] = Integer.toString(idAgendamento);
			agendamentoInicial[1] = ld1.format(formatter);
			agendamentoInicial[2] = ld2.format(formatter);
			agendamentoInicial[3] = Integer.toString(medicos.getSelectedIndex());
			agendamentoInicial[4] = Integer.toString(idPac);
			agendamentoInicial[5] = Integer.toString(remedios.getSelectedIndex());
			listaHorarios.setSelectedIndex(0);
			if(opc == 1) {
				agendamentoInicial[6] = "";
			}
			else if(opc == 2) {
				/*String[] ca = new ControleAgendamento(controleDadosAgendamento).getInfo(idAgendamento);
				agendamentoInicial[6] = "";
				for(int i = 0; i < ca.length; i++) {
					if(ca[i] != null) {
						agendamentoInicial[6] += ca[i].split(" - ")[0] + "," + ca[i].split(" - ")[1] + "_";
					}
				}
				System.out.println(agendamentoInicial[6]);*/
			}
			opc = 2;
			controleDadosAgendamento.inserirEditarAgendamento(agendamentoInicial);
			new TelaHorario().mostrarHorarios(controleDadosAgendamento, idAgendamento);
		}
		else if(fonte == refreshHorario) {
			listaHorarios.setListData(new ControleAgendamento(controleDadosAgendamento).getInfo(idAgendamento));
			listaHorarios.updateUI();
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
