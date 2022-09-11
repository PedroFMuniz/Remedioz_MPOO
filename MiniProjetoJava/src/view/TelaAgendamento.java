package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import controle.*;
import modelo.*;

/**
 * Classe "TelaAgendamento". Descreve uma tela para cadastro e edicao de "Agendamentos".
 * Descreve uma tela com JComboboxes, um JList com os horarios cadastrados e botoes para
 * salvar ou excluir um "Agendamento" a depender da opcao do usuario. Implementa a interface 
 * ActionListener.
 *
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaAgendamento implements ActionListener{
	private JLabel titulo = new JLabel("Cadastro de agendamento");
	private JLabel labelDataInicio = new JLabel("Data de início:");
	private JLabel labelDataFim  = new JLabel("Data de fim:");
	private JLabel labelHorarios = new JLabel("Horários");
	private JLabel remedio = new JLabel("Remédio:");
	private JLabel medico = new JLabel("Médico:");
	private JComboBox<Object> cmbRemedios;
	private JComboBox<Object> cmbMedicos;
	private JComboBox<Integer> dataInicioDia;
	private JComboBox<Integer> dataInicioMes;
	private JComboBox<Integer> dataInicioAno;
	private JComboBox<Integer> dataFimDia;
	private JComboBox<Integer> dataFimMes;
	private JComboBox<Integer> dataFimAno;
	private JList<String> listaHorarios;
	private JButton btnHorario = new JButton("Novo horário");
	private JButton salvar = new JButton("Salvar");
	private JButton refreshHorario = new JButton("Atualizar");
	private JButton excluirHorario = new JButton("Excluir horário");
	private JButton excluirAgendamento = new JButton("Excluir agendamento");
	private JFrame frame;
	private JPanel panel = new JPanel();
	private JScrollPane scroll;
	private int idAgendamento;
	private int opc;
	private int idPac;
	private ControleDados controleDadosAgendamento;
	private String[] agendamentoInicial = new String[10];
	/**
	 * Metodo que descreve a tela "TelaAgendamento" para renderizacao. Adiciona
	 * ActionListeners aos botoes de salvar e excluir.
	 * 
	 * @param opcao Define se a tela obtera os dados de um agendamento especifico 
	 * escolhido em "TelaCadastroEdicaoPaciente" ou se nao tera dados anteriores
	 * @see TelaCadastroEdicaoPaciente
	 * @param dados A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @param idAg O atributo "id" do "Agendamento" 
	 * @param idPaciente o atributo "id" do "Paciente" ao qual sera adicionado o agendamento.
	 * @see TelaCadastroEdicaoPaciente
	 * 
	 * @return void
	 */
	public void inserirEditarAgendamento(int opcao, ControleDados dados, int idAg, int idPaciente) {
		// Definicoes para uso em outros metodos
		idAgendamento = idAg;
		idPac = idPaciente;
		opc = opcao;
		controleDadosAgendamento = dados;
		
		// Criacao dos itens da tela
		frame = new JFrame("Agendamentos");
		frame.setSize(780,360);
		cmbRemedios = new JComboBox<Object>(new ControleRemedio(controleDadosAgendamento).getInfo());
		cmbMedicos = new JComboBox<Object>(new ControleMedico(controleDadosAgendamento).getInfo());
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
			titulo.setText("Edição de agendamento");
			
			// Obtencao de dados
			listaHorarios.setListData(new ControleAgendamento(controleDadosAgendamento).getInfo(idAg));
			LocalDate dataInicio = new ControleAgendamento(controleDadosAgendamento).getDtInicio(idAgendamento);
			LocalDate dataFim = new ControleAgendamento(controleDadosAgendamento).getDtFim(idAgendamento);
			Medico medicoAg = new ControleAgendamento(controleDadosAgendamento).getMedico(idAgendamento);
			Remedio remedioAg = new ControleAgendamento(controleDadosAgendamento).getRemedio(idAgendamento);
			
			String[] dataInicioString = dataInicio.toString().split("-");
			String[] dataFimString = dataFim.toString().split("-");
			
			// Definicao de dados a serem mostrados
			dataInicioDia.setSelectedIndex(Integer.parseInt(dataInicioString[2]) - 1);
			dataInicioMes.setSelectedIndex(Integer.parseInt(dataInicioString[1]) - 1);
			dataInicioAno.setSelectedItem(Integer.parseInt(dataInicioString[0]));
			dataFimDia.setSelectedIndex(Integer.parseInt(dataFimString[2]) - 1);
			dataFimMes.setSelectedIndex(Integer.parseInt(dataFimString[1]) - 1);
			dataFimAno.setSelectedItem(Integer.parseInt(dataFimString[0]));
			cmbMedicos.setSelectedIndex(medicoAg.getId());
			cmbRemedios.setSelectedIndex(remedioAg.getId());
			
			listaHorarios.updateUI();
			listaHorarios.setVisibleRowCount(5);
			titulo.setBounds(270, 0, 220, 30);
			salvar.setBounds(10, 250, 80, 30);
			excluirAgendamento.setBounds(100, 250, 180, 30);
			frame.add(salvar);
			frame.add(excluirAgendamento);
			
		}
		
		frame.setLayout(null);
		panel.setLayout(null);
		
		cmbRemedios.setFont(new Font("Arial", Font.BOLD, 14));
		cmbMedicos.setFont(new Font("Arial", Font.BOLD, 14));
		remedio.setFont(new Font("Arial", Font.BOLD, 14));
		medico.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioDia.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioMes.setFont(new Font("Arial", Font.BOLD, 14));
		dataInicioAno.setFont(new Font("Arial", Font.BOLD, 14));
		labelHorarios.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 14));
		labelDataInicio.setFont(new Font("Arial", Font.BOLD, 14));
		labelDataFim.setFont(new Font("Arial", Font.BOLD, 14));
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		
		// Definicoes de ScrollPane
		scroll = new JScrollPane();
		scroll.setViewportView(listaHorarios);
		listaHorarios.setLayoutOrientation(JList.VERTICAL);
		refreshHorario.setEnabled(false);
		
		// Posicionamento dos itens
		labelHorarios.setBounds(70, 0, 100, 30);
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
		cmbRemedios.setBounds(80, 190, 150, 30);
		cmbMedicos.setBounds(70, 140, 220, 30);
		excluirHorario.setBounds(600, 110, 150, 30);
		refreshHorario.setBounds(380, 170, 200, 30);
		scroll.setBounds(0, 30, 200, 100);
		panel.setBounds(380, 30, 200, 140);
		
		// Adicoes ao JPanel e ao JFrame
		panel.add(labelHorarios);
		frame.add(titulo);
		frame.add(labelDataInicio);
		frame.add(labelDataFim);
		frame.add(remedio);
		frame.add(medico);
		frame.add(cmbRemedios);
		frame.add(cmbMedicos);
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
	/**
	 * Metodo que descreve o que deve acontecer a partir dos clique nos botoes. Este 
	 * metodo grava o "Agendamento" em "ControleDados", abre a tela de horarios, atualiza
	 * a lista de horarios, exclui um horario e exclui um "Agendamento" a depender do botao 
	 * pressionado.
	 * 
	 * @see ControleDados
	 * @param e  ActionEvent usado para determinar a fonte da acao em "TelaAgendamento".
	 * @return void
	 */
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
					
					// Obtencao dos itens
					LocalDate ld1 = LocalDate.of(Integer.parseInt(dataInicioAno.getSelectedItem().toString()), Integer.parseInt(dataInicioMes.getSelectedItem().toString()), Integer.parseInt(dataInicioDia.getSelectedItem().toString()));
					LocalDate ld2 = LocalDate.of(Integer.parseInt(dataFimAno.getSelectedItem().toString()), Integer.parseInt(dataFimMes.getSelectedItem().toString()), Integer.parseInt(dataFimDia.getSelectedItem().toString()));
					agendamentoInicial[0] = Integer.toString(idAgendamento);
					agendamentoInicial[1] = ld1.format(formatter);
					agendamentoInicial[2] = ld2.format(formatter);
					agendamentoInicial[3] = Integer.toString(cmbMedicos.getSelectedIndex());
					agendamentoInicial[4] = Integer.toString(idPac);
					agendamentoInicial[5] = Integer.toString(cmbRemedios.getSelectedIndex());
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
			// Obtencao dos itens
			LocalDate ld1 = LocalDate.of(Integer.parseInt(dataInicioAno.getSelectedItem().toString()), Integer.parseInt(dataInicioMes.getSelectedItem().toString()), Integer.parseInt(dataInicioDia.getSelectedItem().toString()));
			LocalDate ld2 = LocalDate.of(Integer.parseInt(dataFimAno.getSelectedItem().toString()), Integer.parseInt(dataFimMes.getSelectedItem().toString()), Integer.parseInt(dataFimDia.getSelectedItem().toString()));
			agendamentoInicial[0] = Integer.toString(idAgendamento);
			agendamentoInicial[1] = ld1.format(formatter);
			agendamentoInicial[2] = ld2.format(formatter);
			agendamentoInicial[3] = Integer.toString(cmbMedicos.getSelectedIndex());
			agendamentoInicial[4] = Integer.toString(idPac);
			agendamentoInicial[5] = Integer.toString(cmbRemedios.getSelectedIndex());
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
	/**
	 * Metodo para preencher as JComboBoxes de dias com os dados necessarios.
	 * 
	 * @return array Integer[] contendo os 31 dias do mes.
	 */
	public Integer[] preencheDias() {
		Integer[] arrayDias = new Integer[31];
		for(int i = 0; i < 31; i++) {
			arrayDias[i] = i + 1;
		}
		return arrayDias; 
	}
	/**
	 * Metodo para preencher as JComboBoxes de meses com os dados necessarios.
	 * 
	 * @return array Integer[] contendo os 12 meses do ano.
	 */
	public Integer[] preencheMeses() {
		Integer[] arrayMeses = new Integer[12];
		for(int i = 0; i < 12; i++) {
			arrayMeses[i] = i + 1;
		}
		return arrayMeses; 
	}
	/**
	 * Metodo para preencher as JComboBoxes de anos com os dados necessarios.
	 * 
	 * @return array Integer[] contendo os anos de 2022 a 2032
	 */
	public Integer[] preencheAnos() {
		int ano = 2022;
		Integer[] arrayAnos = new Integer[10];
		for(int i = 0; i < 10; i++) {
			arrayAnos[i] = ano;
			ano++;
		}
		return arrayAnos; 
	}
	
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro do horario.
	 * 
	 * @return void
	 */
	public void sucessoExcluirHorario() {
		JOptionPane.showMessageDialog(null, "O horário foi excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro na exclusao do horario.
	 * 
	 * @return void
	 */
	public void erroExcluirHorario() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve algum erro ao excluir esse horário. Selecione um item e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro no cadastro do Agendamento.
	 * 
	 * @return void
	 */
	public void mensagemErroAgendamento() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve algum erro ao cadastrar esse agendamento. Cadastre um horário e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro do agendamento.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void mensagemSucessoAgendamento() {
		JOptionPane.showMessageDialog(null, "O agendamento foi cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro na exclusao do agendamento.
	 * 
	 * @return void
	 */
	public void mensagemErroExclusaoAgendamento() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve algum erro ao excluir esse agendamento. Tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso na exclusao do agendamento.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	public void mensagemSucessoExclusaoAgendamento() {
		JOptionPane.showMessageDialog(null, "O agendamento foi excluído com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
}
