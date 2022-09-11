package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controle.*;

/**
 * Classe "TelaMedico". Descreve uma tela com um titulo, uma barra de pesquisa,
 * uma lista de "Medicos" e botoes para cadastrar um novo medico ou atualizar
 * a lista. Implementa as interfaces ActionListener e ListSelectionListener. Gerencia 
 * a pesquisa e vizualizacao de "Medicos".
 * 
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaMedico implements ActionListener, ListSelectionListener {
	private String[] infosMedicos = new String[40];
	private String[] pesquisaMedicos = new String[40];
	private JList<String> listaMedicos;
	private JFrame frame;
	private JLabel titulo;
	private JButton cadastrarMedico;
	private JButton refreshMedico;
	private JButton btnPesquisa;
	private JTextField txtPesquisa;
	private static ControleDados dadosTelaMedico;
	private JScrollPane scroll;
	private JPanel panel;
	
	/**
	 * Metodo que descreve a tela "TelaMedico" para renderizacao. Adiciona 
	 * ActionListeners em botoes e um ListSelectionListener na lista de
	 * "Medicos".
	 * 
	 * @param dados A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @return void
	 */
	public void mostrarDados(ControleDados dados) {
		// Definicao para uso em outros metodos
		dadosTelaMedico = dados;
		
		infosMedicos = new ControleMedico(dados).getInfo();
		
		frame = new JFrame("Médicos");
		panel = new JPanel();
		panel.setLayout(null);
		frame.setLayout(null);
		frame.setSize(400, 400);
		
		// Criacao de itens da tela
		titulo = new JLabel("Lista de médicos");
		listaMedicos = new JList<String>(infosMedicos);
		txtPesquisa = new JTextField();
		cadastrarMedico = new JButton("Cadastrar");
		btnPesquisa = new JButton("Pesquisar...");
		cadastrarMedico  = new JButton("Cadastrar");
		refreshMedico = new JButton("Atualizar");
		
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		listaMedicos.setFont(new Font("Arial", Font.BOLD, 24));
		
		// Definicoes de JScrollPane
		scroll = new JScrollPane();
		scroll.setViewportView(listaMedicos);
		listaMedicos.setLayoutOrientation(JList.VERTICAL);
		panel.add(scroll);
		
		// Posicionamento dos itens
		scroll.setBounds(0, 0, 350, 200);
		titulo.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		cadastrarMedico.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		refreshMedico.setBounds(220, 300, 150, 30);

		// Adicao ao JFrame
		panel.add(scroll);
		frame.add(titulo);
		frame.add(txtPesquisa);
		frame.add(btnPesquisa);
		frame.add(panel);
		frame.add(cadastrarMedico);
		frame.add(refreshMedico);

		//Adicao de ActionListeners e ListSelectionListener
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cadastrarMedico.addActionListener(this);
		refreshMedico.addActionListener(this);
		btnPesquisa.addActionListener(this);
		listaMedicos.addListSelectionListener(this);
		
		frame.setVisible(true);
	}

	/**
	 * Metodo que descreve o que deve acontecer a partir dos cliques em botoes.
	 * Este metodo permite que a lista de "Medicos" seja atualizada, o gerenciamento 
	 * da pesquisa na lista, e a abertura de "TelaCadastroEdicaoMedico" sem dados 
	 * em seus JTextFields.
	 * 
	 * @see TelaCadastroEdicaoMedico
	 * @param e ActionEvent usado para determinar a fonte da acao em "TelaMedico".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarMedico) {
			new TelaCadastroEdicaoMedico().inserirEditarMedico(1, dadosTelaMedico, 0);
		}
		if(fonte == refreshMedico) {
			listaMedicos.setListData(new ControleMedico(dadosTelaMedico).getInfo());			
			listaMedicos.updateUI();
		}
		if(fonte == btnPesquisa) {
			pesquisaMedicos = new ControleMedico(dadosTelaMedico).getInfo(txtPesquisa.getText());
			if(txtPesquisa.getText().equals("")) {
				listaMedicos.setListData(new ControleMedico(dadosTelaMedico).getInfo());			
				listaMedicos.updateUI();
			}
			else {
				listaMedicos.setListData(pesquisaMedicos);			
				listaMedicos.updateUI();
			}
		}
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir do clique em um item da lista
	 * de "Medicos". Abre "TelaCadastroEdicaoMedico" com os dados correspondentes ao 
	 * item da lista.
	 * 
	 * @see TelaCadastroEdicaoMedico
	 * @param e ListSelectonEvent utilizado para determinar a fonte da acao (no caso, 
	 * so ha uma fonte possivel: "listaMedicos").
	 * @return void
	 * */
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaMedicos) {
			String split[] = listaMedicos.getSelectedValue().split(" - ");
			new TelaCadastroEdicaoMedico().inserirEditarMedico(2, dadosTelaMedico, Integer.parseInt(split[0]));
		}
	}
}
