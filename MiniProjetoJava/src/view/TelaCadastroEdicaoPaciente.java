package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Locale;

import controle.*;

/**
 * Classe "TelaCadastroEdicaoPaciente". Descreve uma tela com um titulo, JTextFields, uma
 * lista de alergias e, no caso de edicao de paciente, uma lista de agendamentos por dia, bem 
 * como botoes para manipular ambas as listas, salvar e excluir um paciente.
 * Implementa as interfaces ActionListener e ListSelectionListener 
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaCadastroEdicaoPaciente implements ActionListener, ListSelectionListener{
	private JFrame frame;
	private JLabel titulo;
	private JLabel labelNome = new JLabel("Nome: ");
	private JLabel labelTelefone = new JLabel("Telefone: ");
	private JLabel labelEmail = new JLabel("Email: ");
	private JLabel labelDoencas = new JLabel("Histórico de doencas: ");
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
	private JButton refreshAlergia = new JButton("Atualizar");
	private JButton setaEsq = new JButton("<-");
	private JButton setaDir = new JButton("->");
	private JList<String> listaAlergias;
	private JList<String> listaAgendamentos;
	private JPanel panel1 = new JPanel(); 
	private JPanel panel2 = new JPanel(); 
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private JButton addAgendamento = new JButton("Novo agendamento");
	private JButton refreshAgendamento = new JButton("Atualizar");
	private LocalDate hoje = LocalDate.now();
	private String[] novoCadastro = new String[10];
	private static int idPaciente;
	private static ControleDados controleTelaEdicaoPaciente;
	
	/**
	 * Metodo que descreve a tela "TelaCadastroEdicaoPaciente" para renderizacao. Adiciona 
	 * dados aos JTextFields e às listas a depender da situacao. Adiciona ActionListeners e 
	 * ListSelectionListeners aos itens necessarios.
	 * 
	 * @param opcao Define se a tela obtera os dados de um paciente especifico 
	 * escolhido em "TelaPaciente" ou se nao tera dados anteriores 
	 * @see TelaPaciente
	 * @param dados A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @param id o atributo "id" do paciente em questao
	 * @see TelaPaciente
	 * 
	 * @return void
	 */
	public void inserirEditarPaciente(int opcao, ControleDados dados, int id) {
		
		// Definicoes para uso em outros metodos
		idPaciente = id;
		controleTelaEdicaoPaciente = dados;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy", Locale.US);
		
		labelDatas = new JLabel(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(hoje.format(formatter), 3));
		
		// Para deixar a lista em branco
		listaAlergias = new JList<String>();
		listaAlergias.setPrototypeCellValue(String.format("%60s", ""));
		
		// Preenchimento da lista de agendamentos (so aparece na opcao 2)
		listaAgendamentos = new JList<String>(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(id, labelDatas.getText()));
		
		frame = new JFrame("Paciente");
		
		
		// Defincoes de ScrollPanes
		scroll1 = new JScrollPane();
		scroll1.setViewportView(listaAlergias);
		listaAlergias.setLayoutOrientation(JList.VERTICAL);
		scroll2 = new JScrollPane();
		scroll2.setViewportView(listaAgendamentos);
		listaAgendamentos.setLayoutOrientation(JList.VERTICAL);
		
		
		if(opcao == 1) {// sem dados
			titulo = new JLabel("Cadastro de paciente");
			txtNome = new JTextField(60);
			txtEmail = new JTextField(60);
			txtDoencas = new JTextField(60);
			txtTelefone = new JTextField(11);
			frame.add(salvar);
			salvar.setBounds(20, 200, 80, 30);
			frame.setSize(550, 280);
			panel1.setBounds(330, 30, 200, 200);
			titulo.setBounds(200, 0, 200, 30);
			refreshAlergia.setEnabled(false);
		}
		else if(opcao == 2) { // com dados	
			frame.setSize(660, 440);
			titulo = new JLabel("Edição de paciente");
			// Obtencao de dados
			txtNome = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getNome(id), 60);
			txtEmail = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getEmail(id), 60);
			txtTelefone = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getTelefone(id), 11);
			txtDoencas = new JTextField(new ControlePaciente(controleTelaEdicaoPaciente).getHistoricoDoencas(id), 60);
			
			// Adicoes ao JFrame e ao panel2
			frame.add(salvar);
			frame.add(excluir);
			panel2.add(setaEsq);
			panel2.add(setaDir);
			panel2.add(labelDatas);
			panel2.add(scroll2);
			panel2.add(addAgendamento);
			panel2.add(refreshAgendamento);
			frame.add(panel2);
			
			// Obtencao de dados
			listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(id));
			listaAlergias.updateUI();
			
			panel2.setBounds(340, 30, 300, 360);
			salvar.setBounds(20, 360, 80, 30);
			excluir.setBounds(120, 360, 80, 30);
			addAgendamento.setBounds(140, 320, 150, 30);
			panel1.setBounds(10, 150, 200, 200);
			refreshAgendamento.setBounds(10, 320, 100, 30);
			titulo.setBounds(230, 0, 200, 30);
			refreshAlergia.setEnabled(true);
		}
		
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDoencas.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNome.setFont(new Font("Arial", Font.BOLD, 14));
		labelEmail.setFont(new Font("Arial", Font.BOLD, 14));
		labelDoencas.setFont(new Font("Arial", Font.BOLD, 14));
		labelTelefone.setFont(new Font("Arial", Font.BOLD, 14));
		labelAlergias.setFont(new Font("Arial", Font.ITALIC|Font.BOLD, 14));
		labelDatas.setFont(new Font("Arial", Font.BOLD, 16));
		setaEsq.setFont(new Font("Arial", Font.BOLD, 16));
		setaDir.setFont(new Font("Arial", Font.BOLD, 16));
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(null);
		
		// Posicionamento dos itens
		labelNome.setBounds(20, 40, 100, 20);
		labelEmail.setBounds(20, 70, 100, 20);
		labelDoencas.setBounds(20, 100, 160, 20);
		labelTelefone.setBounds(20, 130, 100, 20);
		txtNome.setBounds(70, 40, 150, 20);
		txtEmail.setBounds(65, 70, 150, 20);
		txtDoencas.setBounds(180, 100, 150, 20);
		txtTelefone.setBounds(90, 130, 150, 20);
		setaEsq.setBounds(10, 10, 50, 50);
		setaDir.setBounds(240, 10, 50, 50);
		labelDatas.setBounds(70, 30, 170, 15);
		scroll2.setBounds(10, 70, 280, 230);
		
		
		frame.setLayout(null);
		panel2.setLayout(null);
		panel1.add(labelAlergias);
		panel1.add(scroll1);
		
		// Adicoes ao JFrame e ao panel1
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
		
		// ActionListeners e ListSelectionListeners
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
	
	/**
	 * Metodo que descreve o que deve acontecer a partir dos cliques nos botoes. Este 
	 * metodo grava um "Paciente" em "ControleDados", remove uma alergia de "Paciente",
	 * atualiza a lista de alergias, abre a tela "TelaAgendamento", atualiza a lista de 
	 * agendamentos, muda o dia da lista de agendamentos (para frente ou para tras), salva
	 * um paciente ou o exclui, tudo a depender do botao pressionado. 
	 * 
	 * @see ControleDados
	 * @param e ActionEvent usado para determinar a fonte da acao em "TelaCadastroEdicaoPaciente".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == addAlergia) {
			if(txtNome.getText().equals("") || txtEmail.getText().equals("") || txtTelefone.getText().equals("") ||
					txtDoencas.getText().equals("")) {
				mensagemPreencherDados();
			}
			else {
				boolean resultado;
				// Obtencao de dados
				novoCadastro[0] = Integer.toString(idPaciente);
				novoCadastro[1] = txtNome.getText();
				novoCadastro[2] = txtTelefone.getText();
				novoCadastro[3] = txtEmail.getText();
				novoCadastro[4] = txtDoencas.getText();
				resultado = controleTelaEdicaoPaciente.inserirEditarPaciente(novoCadastro);
				if(!resultado) {
					erroCadastroPaciente();
				}
				new TelaAlergias().mostrarLista(controleTelaEdicaoPaciente, idPaciente);
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
				resultado = controleTelaEdicaoPaciente.removerAlergiaPaciente(idPaciente, Integer.parseInt(itemLista[0]));
				listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(idPaciente));
				listaAlergias.updateUI();
				if(resultado) 
					sucessoExcluirAlergia();
				else
					erroExcluirAlergia();
			}
		}
		if(fonte == refreshAlergia) {
			listaAlergias.setListData(new ControlePaciente(controleTelaEdicaoPaciente).getInfoAlergias(idPaciente));
			listaAlergias.updateUI();
		}
		if(fonte == addAgendamento) {
			new TelaAgendamento().inserirEditarAgendamento(1, controleTelaEdicaoPaciente, controleTelaEdicaoPaciente.getUltimoIdAgendamentos() + 1, idPaciente);
		}
		if(fonte == refreshAgendamento) {
			listaAgendamentos.setListData(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(idPaciente, labelDatas.getText()));
			listaAgendamentos.updateUI();
		}
		if(fonte == setaDir) {
			// Avanca um dia
			labelDatas.setText(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(labelDatas.getText(), 1));
			listaAgendamentos.setListData(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(idPaciente, labelDatas.getText()));
			listaAgendamentos.updateUI();
		}
		if(fonte == setaEsq) {
			// Retorna um dia
			labelDatas.setText(new ControleAgendamento(controleTelaEdicaoPaciente).mudarLabel(labelDatas.getText(), 2));
			listaAgendamentos.setListData(new ControleAgendamento(controleTelaEdicaoPaciente).getInfo(idPaciente, labelDatas.getText()));
			listaAgendamentos.updateUI();
		}
		if(fonte == salvar) {
			try {
				// Obtencao  de dados
				novoCadastro[0] = Integer.toString(idPaciente);
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
			resultado = controleTelaEdicaoPaciente.removerPaciente(idPaciente);
			if(resultado) {
				sucessoExcluirPaciente();
			}
			else {
				erroExcluirPaciente();
			}
		}
	}
	/**
	 * Metodo que descreve o que deve acontecer a partir do clique em um item da lista
	 * de "Agendamentos". Abre "TelaAgendamento" com os dados correspondentes ao 
	 * item da lista.
	 * 
	 * @see TelaAgendamento
	 * @param e ListSelectonEvent utilizado para determinar a fonte da acao (no caso, 
	 * so ha uma fonte possivel: "listaAgendamentos").
	 * @return void
	 * */
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaAgendamentos) {
			String[] textoSeparado = listaAgendamentos.getSelectedValue().split(" - ");
			new TelaAgendamento().inserirEditarAgendamento(2, controleTelaEdicaoPaciente, Integer.parseInt(textoSeparado[0]), idPaciente);
		}
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso na exclusao da alergia.
	 * 
	 * @return void
	 */
	public void sucessoExcluirAlergia() {
		JOptionPane.showMessageDialog(null, "A alergia foi excluída com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro na exclusao da alergia.
	 * 
	 * @return void
	 */
	public void erroExcluirAlergia() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve algum erro ao excluir essa alergia. Selecione um item e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso dos dados nao estarem devidamente
	 * preenchidos no cadastro de um novo paciente.
	 * 
	 * @return void
	 */
	private void mensagemPreencherDados() {
		JOptionPane.showMessageDialog(null, "Preencha os outros campos antes de adicionar uma alergia.", null, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro no cadastro do paciente.
	 * 
	 * @return void
	 */
	private void erroCadastroPaciente() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve um erro ao tentar cadastrar o paciente. Verifique os dados e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso no cadastro do paciente.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	private void sucessoCadastroPaciente() {
		JOptionPane.showMessageDialog(null, "O paciente foi cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	/**
	 * Metodo que mostra um dialogo de erro no caso de erro na exclusao do paciente.
	 * 
	 * @return void
	 */
	private void erroExcluirPaciente() {
		JOptionPane.showMessageDialog(null, "ERRO\nHouve um erro ao tentar excluir o paciente. Verifique os dados e tente novamente.", null, JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Metodo que mostra um dialogo de informacao no caso de sucesso na exclusao do paciente.
	 * Fecha a tela.
	 * 
	 * @return void
	 */
	private void sucessoExcluirPaciente() {
		JOptionPane.showMessageDialog(null, "O paciente foi excluído com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}

}
