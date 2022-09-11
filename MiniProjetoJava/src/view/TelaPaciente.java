package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controle.*;

/**
 * Classe "TelaPaciente". Descreve uma tela com um titulo, uma barra de pesquisa,
 * uma lista de "Pacientes" e botoes para cadastrar um novo paciente ou atualizar
 * a lista. Implementa as interfaces ActionListener e ListSelectionListener. Gerencia 
 * a pesquisa e vizualizacao de "Pacientes".
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaPaciente implements ActionListener, ListSelectionListener {
	private JFrame frame;
	private JLabel label;
	private String[] infosPacientes = new String[40];
	private String[] pesquisaPacientes = new String[40];
	private JButton cadastrarPaciente;
	private JButton refreshPaciente;
	private JList<String> listaPacientes;
	private JScrollPane scroll;
	private JPanel panel;
	private JTextField txtPesquisa;
	private JButton btnPesquisa;
	private static ControleDados dadosTelaPaciente;
	
	/**
	 * Metodo que descreve a tela "TelaPaciente" para renderizacao. Adiciona 
	 * ActionListeners em botoes e um ListSelectionListener na lista de
	 * "Pacientes".
	 * 
	 * @param dados A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @return void
	 */
	public void mostrarDados(ControleDados dados) {
		
		// Definicao para uso em outros metodos
		dadosTelaPaciente = dados;
		
		infosPacientes = new ControlePaciente(dados).getInfo();
		
		panel = new JPanel();
		frame = new JFrame("Pacientes");
		panel.setLayout(null);
		frame.setLayout(null);
		frame.setSize(400, 400);
		
		// Criacao de itens da tela
		label = new JLabel("Lista de pacientes");
		cadastrarPaciente = new JButton("Cadastrar");
		refreshPaciente = new JButton("Atualizar");
		listaPacientes = new JList<String>(infosPacientes);
		txtPesquisa = new JTextField();
		btnPesquisa = new JButton("Pesquisar...");
		
		//Definicoes de JScrollPane
		scroll = new JScrollPane();
		scroll.setViewportView(listaPacientes);
		listaPacientes.setLayoutOrientation(JList.VERTICAL);
		panel.add(scroll);
		
		label.setFont(new Font("Arial", Font.BOLD, 16));
		listaPacientes.setFont(new Font("Arial", Font.BOLD, 24));
		
		// Posicionamento dos itens
		label.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		listaPacientes.setVisibleRowCount(5);
		cadastrarPaciente.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		refreshPaciente.setBounds(220, 300, 150, 30);
		scroll.setBounds(0, 0, 350, 200);
		
		// Adicao ao JFrame
		frame.add(label);
		frame.add(txtPesquisa);
		frame.add(btnPesquisa);
		frame.add(cadastrarPaciente);
		frame.add(refreshPaciente);
		frame.add(panel);
		
		// Adicao de ActionListeners e ListSelectionListener
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cadastrarPaciente.addActionListener(this);
		refreshPaciente.addActionListener(this);
		listaPacientes.addListSelectionListener(this);
		btnPesquisa.addActionListener(this);
		
		frame.setVisible(true);
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir dos cliques em botoes.
	 * Este metodo permite que a lista de "Pacientes" seja atualizada, o gerenciamento 
	 * da pesquisa na lista, e a abertura de "TelaCadastroEdicaoPaciente" sem dados 
	 * em seus campos.
	 * 
	 * @see TelaCadastroEdicaoPaciente
	 * @param e ActionEvent usado para determinar a fonte da acao em "TelaPaciente".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarPaciente) {
			new TelaCadastroEdicaoPaciente().inserirEditarPaciente(1, dadosTelaPaciente, dadosTelaPaciente.getUltimoIdPacientes() + 1);
		}
		if(fonte == refreshPaciente) {
			listaPacientes.setListData(new ControlePaciente(dadosTelaPaciente).getInfo());			
			listaPacientes.updateUI();
		}
		if(fonte == btnPesquisa) {
			pesquisaPacientes = new ControlePaciente(dadosTelaPaciente).getInfo(txtPesquisa.getText());
			if(txtPesquisa.getText().equals("")) {
				listaPacientes.setListData(new ControlePaciente(dadosTelaPaciente).getInfo());			
				listaPacientes.updateUI();
			}
			else {
				listaPacientes.setListData(pesquisaPacientes);			
				listaPacientes.updateUI();
			}
		}
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir do clique em um item da lista
	 * de "Pacientes". Abre "TelaCadastroEdicaoPaciente" com os dados correspondentes ao 
	 * item da lista.
	 * 
	 * @see TelaCadastroEdicaoPaciente
	 * @param e ListSelectonEvent utilizado para determinar a fonte da acao (no caso, 
	 * so ha uma fonte possivel: "listaPacientes").
	 * @return void
	 * */
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaPacientes) {
			String[] textoSeparado = listaPacientes.getSelectedValue().split(" - ");
			new TelaCadastroEdicaoPaciente().inserirEditarPaciente(2, dadosTelaPaciente, Integer.parseInt(textoSeparado[0]));
		}
	}
}
