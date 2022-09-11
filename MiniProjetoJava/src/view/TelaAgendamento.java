package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import controle.*;
import modelo.*;

public class TelaAgendamento implements ActionListener{
	private JLabel titulo = new JLabel("Cadastro de agendamento");
	private JLabel labelDataInicio = new JLabel("Data de inicio:");
	private JLabel labelDataFim  = new JLabel("Data de fim:");
	private JLabel labelHorarios = new JLabel("Horarios");
	private JLabel remedio = new JLabel("Remedio:");
	private JLabel medico = new JLabel("Medico:");
	private JComboBox<Object> remedios;
	private JComboBox<Object> medicos;
	private JComboBox<Integer> dataInicioDia;
	private JComboBox<Integer> dataInicioMes;
	private JComboBox<Integer> dataInicioAno;
	private JComboBox<Integer> dataFimDia;
	private JComboBox<Integer> dataFimMes;
	private JComboBox<Integer> dataFimAno;
	private JList<String> listaHorarios;
	private JButton btnHorario = new JButton("Novo horario");
	private JButton salvar = new JButton("Salvar");
	private JButton refreshHorario = new JButton("Refresh...");
	private JButton excluirHorario = new JButton("Excluir horario");
	private JButton excluirAgendamento = new JButton("Excluir agendamento");
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private int idAgendamento;
	private int opc;
	private int idPac;
	private ControleDados controleDadosAgendamento;
	private String[] agendamentoInicial = new String[10];
	
	public void inserirEditarAgendamento(int opcao, ControleDados dados, int idAg, int idPaciente) {
		idAgendamento = idAg;
		idPac = idPaciente;
		opc = opcao;
		controleDadosAgendamento = dados;
		frame = new JFrame("Agendamentos");
		frame.setSize(780,360);
		remedios = new JComboBox<Object>(new ControleRemedio(controleDadosAgendamento).getInfo());
		medicos = new JComboBox<Object>(new ControleMedico(controleDadosAgendamento).getInfo());
		dataInicioDia = new JComboBox<Integer>(preencheDias());
		dataInicioMes = new JComboBox<Integer>(preencheMeses());
		dataInicioAno = new JComboBox<Integer>(preencheAnos());
		dataFimDia = new JComboBox<Integer>(preencheDias());
		dataFimMes = new JComboBox<Integer>(preencheMeses());
		dataFimAno = new JComboBox<Integer>(preencheAnos());
		listaHorarios = new JList<String>();
		if(opc == 1) {
			//sem dados
			listaHorarios.setVisibleRowCount(5);
			listaHorarios.setPrototypeCellValue(String.format("%60s", ""));
			titulo.setBounds(260, 0, 220, 30);
			salvar.setBounds(10, 250, 80, 30);
			frame.add(salvar);
		}
		else if(opc == 2) {
			// preenchidos
			titulo.setText("Edicao de agendamento");
			
			listaHorarios.setListData(new ControleAgendamento(controleDadosAgendamento).getInfo(idAg));
			LocalDate dataInicio = new ControleAgendamento(controleDadosAgendamento).getDtInicio(idAgendamento);
			LocalDate dataFim = new ControleAgendamento(controleDadosAgendamento).getDtFim(idAgendamento);
			Medico medicoAg = new ControleAgendamento(controleDadosAgendamento).getMedico(idAgendamento);
			Remedio remedioAg = new ControleAgendamento(controleDadosAgendamento).getRemedio(idAgendamento);
			
			String[] dataInicioString = dataInicio.toString().split("-");
			String[] dataFimString = dataFim.toString().split("-");
			
			dataInicioDia.setSelectedIndex(Integer.parseInt(dataInicioString[2]) - 1);
			dataInicioMes.setSelectedIndex(Integer.parseInt(dataInicioString[1]) - 1);
			dataInicioAno.setSelectedItem(Integer.parseInt(dataInicioString[0]));
			dataFimDia.setSelectedIndex(Integer.parseInt(dataFimString[2]) - 1);
			dataFimMes.setSelectedIndex(Integer.parseInt(dataFimString[1]) - 1);
			dataFimAno.setSelectedItem(Integer.parseInt(dataFimString[0]));
			medicos.setSelectedIndex(medicoAg.getId());
			remedios.setSelectedIndex(remedioAg.getId());
			
			listaHorarios.updateUI();
			listaHorarios.setVisibleRowCount(5);
			titulo.setBounds(270, 0, 220, 30);
			salvar.setBounds(10, 250, 80, 30);
			excluirAgendamento.setBounds(100, 250, 180, 30);
			frame.add(salvar);
			frame.add(excluirAgendamento);
			
		}
		frame.setLayout(null);
		remedios.setFont(new Font("Arial", Font.BOLD, 14));
		medicos.setFont(new Font("Arial", Font.BOLD, 14));
		remedio.setFont(new Font("Arial", Font.BOLD, 14));
		medico.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioDia.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioMes.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioAno.setFont(new Font("Arial", Font.BOLD, 14));
		labelHorarios.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 14));
		labelDataInicio.setFont(new Font("Arial", Font.BOLD, 14));
		labelDataFim.setFont(new Font("Arial", Font.BOLD, 14));
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		
		labelHorarios.setBounds(50, 0, 100, 30);
		panel.add(labelHorarios);
		refreshHorario.setEnabled(false);
		scroll = new JScrollPane();
		scroll.setViewportView(listaHorarios);
		listaHorarios.setLayoutOrientation(JList.VERTICAL);
		labelDataInicio.setBounds(10, 40, 120, 30);
		labelDataFim.setBounds(10, 90, 100, 30);
		medico.setBounds(10, 140, 100, 30);
		remedio.setBounds(10, 190, 100, 30);
		dataInicioDia.setBounds(120, 40, 50, 30);
		dataInicioMes.setBounds(180, 40, 50, 30);
		dataInicioAno.setBounds(240, 40, 80, 30);
		dataFimDia.setBounds(100, 90, 50, 30);
		dataFimMes.setBounds(160, 90, 50, 30);
		dataFimAno.setBounds(220, 90, 80, 30);
		btnHorario.setBounds(600, 60, 150, 30);
		remedios.setBounds(80, 190, 150, 30);
		medicos.setBounds(70, 140, 220, 30);
		excluirHorario.setBounds(600, 110, 150, 30);
		refreshHorario.setBounds(380, 170, 200, 30);
		panel.setBounds(380, 30, 200, 140);
		
		frame.add(titulo);
		frame.add(labelDataInicio);
		frame.add(labelDataFim);
		frame.add(remedio);
		frame.add(medico);
		frame.add(remedios);
		frame.add(medicos);
		frame.add(dataInicioDia);
		frame.add(dataInicioMes);
		frame.add(dataInicioAno);
		frame.add(dataFimMes);
		frame.add(dataFimDia);
		frame.add(dataFimAno);
		panel.add(scroll);
		frame.add(panel);
		frame.add(btnHorario);
		frame.add(refreshHorario);
		frame.add(excluirHorario);
		frame.setVisible(true);
		
		salvar.addActionListener(this);
		btnHorario.addActionListener(this);
		refreshHorario.addActionListener(this);
		excluirHorario.addActionListener(this);
		excluirAgendamento.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == salvar) {
			if(listaHorarios.getModel().getSize() == 0) {
				mensagemErroAgendamento();
			}
			else {
				try {
					boolean resultado;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate ld1 = LocalDate.of(Integer.parseInt(dataInicioAno.getSelectedItem().toString()), Integer.parseInt(dataInicioMes.getSelectedItem().toString()), Integer.parseInt(dataInicioDia.getSelectedItem().toString()));
					LocalDate ld2 = LocalDate.of(Integer.parseInt(dataFimAno.getSelectedItem().toString()), Integer.parseInt(dataFimMes.getSelectedItem().toString()), Integer.parseInt(dataFimDia.getSelectedItem().toString()));
					agendamentoInicial[0] = Integer.toString(idAgendamento);
					agendamentoInicial[1] = ld1.format(formatter);
					agendamentoInicial[2] = ld2.format(formatter);
					agendamentoInicial[3] = Integer.toString(medicos.getSelectedIndex());
					agendamentoInicial[4] = Integer.toString(idPac);
					agendamentoInicial[5] = Integer.toString(remedios.getSelectedIndex());
					resultado = controleDadosAgendamento.inserirEditarAgendamento(agendamentoInicial);
					if(resultado)
						mensagemSucessoAgendamento();
					else
						mensagemErroAgendamento();
					
				}
				catch(NullPointerException exc1) {
					mensagemErroAgendamento();
				}
				catch(NumberFormatException exc1) {
					mensagemErroAgendamento();
				}
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
			controleDadosAgendamento.inserirEditarAgendamento(agendamentoInicial);
			new TelaHorario().mostrarHorarios(controleDadosAgendamento, idAgendamento);
			refreshHorario.setEnabled(true);
		}
		else if(fonte == refreshHorario) {
			listaHorarios.setListData(new ControleAgendamento(controleDadosAgendamento).getInfo(idAgendamento));
			listaHorarios.updateUI();
		}
		else if(fonte == excluirHorario) {
			if(listaHorarios.getSelectedIndex() == -1) {
				erroExcluirHorario();
			}
			else {
				boolean resultado;
				String[] itemLista = listaHorarios.getSelectedValue().split(" - ");
				resultado = controleDadosAgendamento.removerHorarioAgendamento(idAgendamento, itemLista[0], itemLista[1]);
				listaHorarios.setListData(new ControleAgendamento(controleDadosAgendamento).getInfo(idAgendamento));
				listaHorarios.updateUI();
				if(resultado) {
					sucessoExcluirHorario();
					refreshHorario.setEnabled(true);
				}
				else {
					erroExcluirHorario();
				}
			}
		}
		else if(fonte == excluirAgendamento) {
			boolean resultado;
			resultado = controleDadosAgendamento.removerAgendamento(idAgendamento);
			if(resultado) {
				mensagemSucessoExclusaoAgendamento();
			}
			else {
				mensagemErroExclusaoAgendamento();
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
	public void sucessoExcluirHorario() {
		JOptionPane.showMessageDialog(null, "O horario foi excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
	}
	public void erroExcluirHorario() {
		JOptionPane.showMessageDialog(null, "ERRO\n Houve algum erro ao excluir esse horario. Selecione um item e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	public void mensagemErroAgendamento() {
		JOptionPane.showMessageDialog(null, "ERRO\n Houve algum erro ao cadastrar esse agendamento. Cadastre um horario e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	public void mensagemSucessoAgendamento() {
		JOptionPane.showMessageDialog(null, "O agendamento foi cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	public void mensagemErroExclusaoAgendamento() {
		JOptionPane.showMessageDialog(null, "ERRO\n Houve algum erro ao excluir esse agendamento. Tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	public void mensagemSucessoExclusaoAgendamento() {
		JOptionPane.showMessageDialog(null, "O agendamento foi excluído com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
	}
}
