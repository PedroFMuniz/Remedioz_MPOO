package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Locale;

import controle.*;

public class TelaCadastroEdicaoPaciente implements ActionListener, ListSelectionListener{
	private int opc;
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
	private JButton refreshAlergia = new JButton("Refresh...");
	private JButton setaEsq = new JButton("<-");
	private JButton setaDir = new JButton("->");
	private JList<String> listaAlergias;
	private JList<String> listaAgendamentos;
	private static int posicao;
	private static ControleDados controleTelaEdicaoPaciente;
	private JPanel panel1 = new JPanel(); 
	private JPanel panel2 = new JPanel(); 
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JButton addAgendamento = new JButton("Novo agendamento");
	private JButton refreshAgendamento = new JButton("Refresh...");
	private LocalDate hoje = LocalDate.now();
	private String[] novoCadastro = new String[10];
	
	public void inserirEditarPaciente(int opcao, ControleDados dados, int id) {
		posicao = id;
		controleTelaEdicaoPaciente = dados;
		opc = opcao;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", Locale.US);
		frame = new JFrame("Paciente");
		labelDatas = new JLabel(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(hoje.format(formatter), 3));
		//pra deixar as listas em branco
		listaAlergias = new JList<String>();
		//listaAlergias.setVisibleRowCount(5);
		listaAlergias.setPrototypeCellValue(String.format("%60s", ""));
		listaAgendamentos = new JList<String>(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(id, labelDatas.getText()));
		//listaAgendamentos.setVisibleRowCount(15);
				
		scroll1 = new JScrollPane();
		scroll1.setViewportView(listaAlergias);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		
		scroll2 = new JScrollPane();
		scroll2.setViewportView(listaAgendamentos);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		
		
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
			refreshAlergia.setEnabled(false);
		}
		else if(opcao == 2) {
			frame.setSize(800, 600);
			titulo = new JLabel("Edicao de paciente");
			txtNome = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getNome(id), 60);
			txtEmail = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getEmail(id), 60);
			txtTelefone = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getTelefone(id), 11);
			txtDoencas = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getHistoricoDoencas(id), 60);
			frame.add(salvar);
			frame.add(excluir);
			panel2.add(setaEsq);
			panel2.add(setaDir);
			panel2.add(labelDatas);
			panel2.add(scroll2);
			panel2.add(addAgendamento);
			panel2.add(refreshAgendamento);
			frame.add(panel2);
			listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(id));
			listaAlergias.updateUI();
			panel2.setBounds(400, 40, 300, 360);
			salvar.setBounds(600, 430, 80, 30);
			excluir.setBounds(500, 430, 80, 30);
			addAgendamento.setBounds(140, 320, 150, 30);
			refreshAgendamento.setBounds(10, 320, 100, 30);
			refreshAlergia.setEnabled(true);
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
		panel1.add(refreshAlergia);
		
		
		salvar.addActionListener(this);
		excluir.addActionListener(this);
		addAlergia.addActionListener(this);
		removeAlergia.addActionListener(this);
		addAgendamento.addActionListener(this);
		refreshAgendamento.addActionListener(this);
		refreshAlergia.addActionListener(this);
		listaAgendamentos.addListSelectionListener(this);
		setaDir.addActionListener(this);
		setaEsq.addActionListener(this);
		this.frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergia) {
			if(txtNome.getText().equals("") || txtEmail.getText().equals("") || txtTelefone.getText().equals("") ||
					txtDoencas.getText().equals("")) {
				mensagemPreencherDados();
			}
			else {
				boolean resultado;
				novoCadastro[0] = Integer.toString(posicao);
				novoCadastro[1] = txtNome.getText();
				novoCadastro[2] = txtTelefone.getText();
				novoCadastro[3] = txtEmail.getText();
				novoCadastro[4] = txtDoencas.getText();
				resultado = controleTelaEdicaoPaciente.inserirEditarPaciente(novoCadastro);
				if(!resultado) {
					erroCadastroPaciente();
				}
				new TelaAlergias().mostrarLista(controleTelaEdicaoPaciente, posicao);
				if(refreshAlergia.isEnabled() == false) {
					refreshAlergia.setEnabled(true);
				}
			}
			
		}
		if(fonte == removeAlergia) {
			if(listaAlergias.getSelectedIndex() == -1) {
				erroExcluirAlergia();
			}
			else {
				boolean resultado;
				String[] itemLista = listaAlergias.getSelectedValue().split(" - ");
				resultado = controleTelaEdicaoPaciente.removerAlergiaPaciente(posicao, Integer.parseInt(itemLista[0]));
				listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(posicao));
				listaAlergias.updateUI();
				if(resultado) 
					sucessoExcluirAlergia();
				else
					erroExcluirAlergia();
			}
		}
		/*if(fonte == removeAlergia) {
			controleTelaEdicaoPaciente.removerAlergiaPaciente(posicao, listaAlergias.getSelectedIndex());
			listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(posicao));
			listaAlergias.updateUI();
		}*/
		if(fonte == refreshAlergia) {
			listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(posicao));
			listaAlergias.updateUI();
		}
		if(fonte == addAgendamento) {
			new TelaAgendamento().inserirEditarAgendamento(1, controleTelaEdicaoPaciente, controleTelaEdicaoPaciente.getUltimoIdAgendamentos() + 1, posicao);
		}
		if(fonte == refreshAgendamento) {
			listaAgendamentos.setListData(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(posicao, labelDatas.getText()));
			listaAgendamentos.updateUI();
		}
		if(fonte == setaDir) {
			labelDatas.setText(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(labelDatas.getText(), 1));
			listaAgendamentos.setListData(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(posicao, labelDatas.getText()));
			listaAgendamentos.updateUI();
		}
		if(fonte == setaEsq) {
			labelDatas.setText(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(labelDatas.getText(), 2));
			listaAgendamentos.setListData(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(posicao, labelDatas.getText()));
			listaAgendamentos.updateUI();
		}
		if(fonte == salvar) {
			try {
				novoCadastro[0] = Integer.toString(posicao);
				novoCadastro[1] = txtNome.getText();
				novoCadastro[2] = txtTelefone.getText();
				novoCadastro[3] = txtEmail.getText();
				novoCadastro[4] = txtDoencas.getText();
				boolean resultado = controleTelaEdicaoPaciente.inserirEditarPaciente(novoCadastro);
				if(resultado) {
					sucessoCadastroPaciente();
				}
				else {
					erroCadastroPaciente();
				}
				
			}
			catch(NullPointerException exc1) {
				erroCadastroPaciente();
			}
			catch(NumberFormatException exc1) {
				erroCadastroPaciente();
			}
		}
		if(fonte == excluir) {
			boolean resultado;
			resultado = controleTelaEdicaoPaciente.removerPaciente(posicao);
			if(resultado) {
				sucessoExcluirPaciente();
			}
			else {
				erroExcluirPaciente();
			}
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaAgendamentos) {
			String[] textoSeparado = listaAgendamentos.getSelectedValue().split(" - ");
			new TelaAgendamento().inserirEditarAgendamento(2, controleTelaEdicaoPaciente, Integer.parseInt(textoSeparado[0]), posicao);
		}
	}
	public void sucessoExcluirAlergia() {
		JOptionPane.showMessageDialog(null, "A alergia foi excluida com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
	}
	public void erroExcluirAlergia() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve algum erro ao excluir essa alergia. Selecione um item e tente novamente.", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	private void mensagemPreencherDados() {
		JOptionPane.showMessageDialog(null, "Preencha os outros campos antes de adicionar uma alergia.", null, JOptionPane.INFORMATION_MESSAGE);
	}
	private void erroCadastroPaciente() {
		JOptionPane.showMessageDialog(null, "ERRO \nHouve um erro ao tentar cadastrar o paciente. Verifique os dados e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	private void sucessoCadastroPaciente() {
		JOptionPane.showMessageDialog(null, "O paciente foi cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	private void erroExcluirPaciente() {
		JOptionPane.showMessageDialog(null, "ERRO \nHouve um erro ao tentar excluir o paciente. Verifique os dados e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	private void sucessoExcluirPaciente() {
		JOptionPane.showMessageDialog(null, "O paciente foi exluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

}
