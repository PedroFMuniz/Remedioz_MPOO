package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controle.*;
import modelo.Paciente;

public class TelaCadastroEdicaoPaciente implements ActionListener, ListSelectionListener{
	private JFrame frame;
	private JLabel titulo;
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JLabel labelEmail = new JLabel("Email: ");
	private JLabel labelDoencas = new JLabel("Historico de doencas: ");
	private JLabel labelAlergias = new JLabel("Alergias");
	private JLabel labelDatas = new JLabel("placeholder data");
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
	
	public void inserirEditarPaciente(int opcao, ControleDados dados, TelaPaciente tela, int indice) {
		opcaoCrud = opcao;
		posicao = indice;
		controleTelaEdicaoPaciente = dados;
		infosRemedios = new ControleRemedio(dados).getInfo();
		//pra deixar as listas em branco
		listaAlergias = new JList<String>();
		listaAlergias.setVisibleRowCount(5);
		listaAlergias.setPrototypeCellValue(String.format("%60s", ""));
		listaAgendamentos = new JList<String>();
		listaAgendamentos.setVisibleRowCount(15);
		listaAlergias.setPrototypeCellValue(String.format("%60s", ""));
		
		
		this.frame = new JFrame();
		this.frame.setSize(800, 600);
		if(opcao == 1) {
			// sem dados
			titulo = new JLabel("Cadastro de paciente");
			txtNome = new JTextField(60);
			txtEmail = new JTextField(60);
			txtDoencas = new JTextField(60);
			txtTelefone = new JTextField(11);
			this.frame.add(salvar);
			salvar.setBounds(600, 430, 80, 30);
		}
		else if(opcao == 2) {
			titulo = new JLabel("Edicao de paciente");
			txtNome = new JTextField(dados.getPacientes()[indice].getNome(), 60);
			txtEmail = new JTextField(dados.getPacientes()[indice].getEmail(), 60);
			txtTelefone = new JTextField(dados.getPacientes()[indice].getTelefone(), 11);
			txtDoencas = new JTextField(dados.getPacientes()[indice].getHistoricoDoencas(), 60);
			this.frame.add(salvar);
			this.frame.add(excluir);
			salvar.setBounds(600, 430, 80, 30);
			excluir.setBounds(500, 430, 80, 30);
		}
		
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDoencas.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
		setaEsq.setFont(new Font("Arial", Font.BOLD, 16));
		setaDir.setFont(new Font("Arial", Font.BOLD, 16));
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		
		scroll1 = new JScrollPane();
		scroll1.setViewportView(listaAlergias);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		
		scroll2 = new JScrollPane();
		scroll2.setViewportView(listaAgendamentos);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		
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
		scroll2.setBounds(10, 70, 280, 270);
		
		panel1.setBounds(20, 200, 200, 200);
		panel2.setBounds(400, 40, 300, 360);
		
		//listaAlergias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		this.frame.setLayout(null);
		panel2.setLayout(null);
		panel1.add(labelAlergias);
		panel1.add(scroll1);
		panel2.add(setaEsq);
		panel2.add(setaDir);
		panel2.add(labelDatas);
		panel2.add(scroll2);
		this.frame.add(titulo);
		this.frame.add(labelNome);
		this.frame.add(txtNome);
		this.frame.add(labelEmail);
		this.frame.add(txtEmail);
		this.frame.add(labelTelefone);
		this.frame.add(txtTelefone);
		this.frame.add(labelDoencas);
		this.frame.add(txtDoencas);
		this.frame.add(panel1);
		this.frame.add(panel2);
		panel1.add(addAlergia);
		panel1.add(removeAlergia);
		
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		addAlergia.addActionListener(this);
		removeAlergia.addActionListener(this);
		this.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergia) {
			new DetalheTelaCadastroPaciente().mostrarLista(controleTelaEdicaoPaciente);
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		
	}
	
	public void setIndicesAlergias(int[] indicesAlergias) {
		this.indicesAlergias = indicesAlergias; 
	}
}
