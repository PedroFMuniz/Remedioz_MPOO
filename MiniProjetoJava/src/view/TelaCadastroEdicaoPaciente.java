package view;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.event.*;
import java.util.Date;
import controle.*;

public class TelaCadastroEdicaoPaciente implements ActionListener, ListSelectionListener{
	private JFrame frame;
	private JLabel titulo;
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JLabel labelEmail = new JLabel("Email: ");
	private JLabel labelDoencas = new JLabel("Historico de doencas: ");
	private JLabel labelAlergias = new JLabel("Alergias");
	private JLabel labelDatas;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtDoencas;
	private JButton excluir = new JButton("Excluir");
	private JButton salvar = new JButton("Salvar");
	private JButton addAlergia = new JButton("+");
	private JButton removeAlergia = new JButton("-");
	private JButton setaEsq = new JButton("<-");
	private JButton setaDir = new JButton("->");
	private JList<String> listaAlergias;
	private JList<String> listaAgendamentos;
	private static int opcaoCrud;
	private static int posicao;
	private static ControleDados controleTelaEdicaoPaciente;
	private String[] infosRemedios = new String[40];
	private int[] indicesAlergias = new int[40];
	private JPanel panel1 = new JPanel(); 
	private JPanel panel2 = new JPanel(); 
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JButton addAgendamento = new JButton("Novo agendamento");
	private JButton refreshAgendamento = new JButton("Refresh...");
	private LocalDate hoje = LocalDate.now();
	private int diaSemana = hoje.getDayOfWeek().getValue();
	private String diaUs;
	
	public void inserirEditarPaciente(int opcao, ControleDados dados, int id) {
		opcaoCrud = opcao;
		posicao = id;
		controleTelaEdicaoPaciente = dados;
		infosRemedios = new ControleRemedio(dados).getInfo();
		//pra deixar as listas em branco
		listaAlergias = new JList<String>();
		listaAlergias.setVisibleRowCount(5);
		listaAlergias.setPrototypeCellValue(String.format("%60s", ""));
		listaAgendamentos = new JList<String>(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(id, hoje));
		listaAgendamentos.setVisibleRowCount(15);
		
		diaUs = Integer.toString(diaSemana(diaSemana));
		
		scroll1 = new JScrollPane();
		scroll1.setViewportView(listaAlergias);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		
		scroll2 = new JScrollPane();
		scroll2.setViewportView(listaAgendamentos);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		
		frame = new JFrame("Paciente");
		labelDatas = new JLabel(hoje.toString());
		if(opcao == 1) {
			// sem dados
			titulo = new JLabel("Cadastro de paciente");
			txtNome = new JTextField(60);
			txtEmail = new JTextField(60);
			txtDoencas = new JTextField(60);
			txtTelefone = new JTextField(11);
			frame.add(salvar);
			salvar.setBounds(500, 430, 80, 30);
			frame.setSize(600, 600);
		}
		else if(opcao == 2) {
			frame.setSize(800, 600);
			titulo = new JLabel("Edicao de paciente");
			txtNome = new JTextField(dados.getPacientes()[id].getNome(), 60);
			txtEmail = new JTextField(dados.getPacientes()[id].getEmail(), 60);
			txtTelefone = new JTextField(dados.getPacientes()[id].getTelefone(), 11);
			txtDoencas = new JTextField(dados.getPacientes()[id].getHistoricoDoencas(), 60);
			frame.add(salvar);
			frame.add(excluir);
			panel2.add(setaEsq);
			panel2.add(setaDir);
			panel2.add(labelDatas);
			panel2.add(scroll2);
			panel2.add(addAgendamento);
			frame.add(panel2);
			listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(id));
			listaAlergias.updateUI();
			panel2.setBounds(400, 40, 300, 360);
			salvar.setBounds(600, 430, 80, 30);
			excluir.setBounds(500, 430, 80, 30);
			addAgendamento.setBounds(140, 320, 150, 30);
		}
		
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDoencas.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
		setaEsq.setFont(new Font("Arial", Font.BOLD, 16));
		setaDir.setFont(new Font("Arial", Font.BOLD, 16));
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		
		panel1.setBackground(Color.MAGENTA);
		panel1.setLayout(new FlowLayout());
		panel2.setBackground(Color.MAGENTA);
		panel2.setLayout(new FlowLayout());
		
		titulo.setBounds(315, 10, 200, 20);
		labelNome.setBounds(20, 40, 100, 20);
		labelEmail.setBounds(20, 70, 100, 20);
		labelDoencas.setBounds(20, 100, 130, 20);
		labelTelefone.setBounds(20, 130, 100, 20);
		txtNome.setBounds(130, 40, 150, 20);
		txtEmail.setBounds(130, 70, 150, 20);
		txtDoencas.setBounds(160, 100, 150, 20);
		txtTelefone.setBounds(130, 130, 150, 20);
		setaEsq.setBounds(10, 10, 50, 50);
		setaDir.setBounds(240, 10, 50, 50);
		labelDatas.setBounds(70, 35, 170, 15);
		scroll2.setBounds(10, 70, 280, 230);
		
		panel1.setBounds(20, 200, 200, 200);
		
		//listaAlergias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		frame.setLayout(null);
		panel2.setLayout(null);
		panel1.add(labelAlergias);
		panel1.add(scroll1);
		
		frame.add(titulo);
		frame.add(labelNome);
		frame.add(txtNome);
		frame.add(labelEmail);
		frame.add(txtEmail);
		frame.add(labelTelefone);
		frame.add(txtTelefone);
		frame.add(labelDoencas);
		frame.add(txtDoencas);
		frame.add(panel1);
		panel1.add(addAlergia);
		panel1.add(removeAlergia);
		
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		addAlergia.addActionListener(this);
		removeAlergia.addActionListener(this);
		addAgendamento.addActionListener(this);
		refreshAgendamento.addActionListener(this);
		listaAgendamentos.addListSelectionListener(this);
		setaDir.addActionListener(this);
		setaEsq.addActionListener(this);
		this.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergia) {
			new DetalheTelaCadastroPaciente().mostrarLista(controleTelaEdicaoPaciente);
		}
		if(fonte == removeAlergia) {
			
		}
		if(fonte == addAgendamento) {
			new TelaAgendamento().inserirEditarAgendamento(1, controleTelaEdicaoPaciente, controleTelaEdicaoPaciente.getQtdAgendamentos(), posicao);
		}
		if(fonte == refreshAgendamento) {
			
		}
		if(fonte == setaDir) {
			labelDatas.setText(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(labelDatas.getText(), 1));
		}
		if(fonte == setaEsq) {
			labelDatas.setText(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(labelDatas.getText(), 2));
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaAgendamentos) {
			String[] textoSeparado = listaAgendamentos.getSelectedValue().split(" - ");
			new TelaAgendamento().inserirEditarAgendamento(2, controleTelaEdicaoPaciente, Integer.parseInt(textoSeparado[0]), posicao);
		}
	}
	
	public void setIndicesAlergias(int[] indicesAlergias) {
		this.indicesAlergias = indicesAlergias; 
	}
	public String diaSemanaUs(int value) {
		String diaUs;
		switch (diaSemana) {
        	case 1:
        		diaUs = "Mon";
        		break;
	        case 2:
	        	diaUs = "Tue";
	            break;
	        case 3:
	        	diaUs = "Wed";
	            break;
	        case 4:
	        	diaUs = "Thu";
	            break;
	        case 5:
	        	diaUs = "Fri";
	            break;
	        case 6:
	        	diaUs = "Sat";
	            break;
	        case 7:
	        	diaUs = "Sun";
	            break;
	        default:
	            break;
	    }
		return diaUs;
	}
}
