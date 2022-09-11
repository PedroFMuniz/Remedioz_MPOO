package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


import controle.*;

/**
 * Classe "TelaRemedio". Descreve uma tela com um titulo, uma barra de pesquisa,
 * uma lista de "Remedios" e botoes para cadastrar um novo remedio ou atualizar a 
 * lista. Implementa as interfaces ActionListener e ListSelectionListener. Gerencia 
 * a pesquisa e vizualizacao de "Remedios".
 *
 * @author Felipe Mastromauro Correa e Pedro Ferreira Muniz
 * @since 2022
 * @version 1.0
 */
public class TelaRemedio implements ActionListener, ListSelectionListener{
	private String[] infosRemedios = new String[40];
	private String[] pesquisaRemedios = new String[40];
	private JFrame frame;
	private JLabel label;
	private JList<String> listaRemedios;
	private JButton btnPesquisa;
	private JButton cadastrarRemedio;
	private JButton refreshRemedio;
	private JTextField txtPesquisa;
	private JScrollPane scroll;
	private JPanel panel = new JPanel();
	private static ControleDados dadosTelaRemedio;
	
	
	/**
	 * Metodo que descreve a tela "TelaRemedio" para renderizacao. Adiciona
	 * ActionListeners em botoes e um ListSelectionListener na lista de "Remedios".
	 * 
	 * @param dados : A instancia de "ControleDados" feita na classe "TelaMenu".
	 * @see TelaMenu
	 * @return void
	 */
	public void mostrarDados(ControleDados dados) {

		// Definicao para uso em outros metodos
		dadosTelaRemedio = dados;
		
		frame = new JFrame("Remedios");
		panel.setLayout(null);
		frame.setLayout(null);
		frame.setSize(400, 400);
		
		infosRemedios = new ControleRemedio(dados).getInfo();
		
		// Criacao dos itens da tela
		label = new JLabel("Lista de remedios");
		txtPesquisa = new JTextField("");
		btnPesquisa = new JButton("Pesquisar...");
		cadastrarRemedio = new JButton("Cadastrar");
		refreshRemedio = new JButton("Refresh");
		listaRemedios = new JList<String>(infosRemedios);
		
		// Definicao ScrollPane
		scroll = new JScrollPane();
		scroll.setViewportView(listaRemedios);
		listaRemedios.setLayoutOrientation(JList.VERTICAL);
		panel.add(scroll);
		
		// Definicao de fontes
		label.setFont(new Font("Arial", Font.BOLD, 16));
		listaRemedios.setFont(new Font("Arial", Font.BOLD, 24));
		
		// Posicionamento dos itens
		scroll.setBounds(0, 0, 350, 200);
		label.setBounds(135, 10, 150, 30);
		txtPesquisa.setBounds(20, 50, 200, 20);
		btnPesquisa.setBounds(240, 50, 130, 20);
		cadastrarRemedio.setBounds(20, 300, 150, 30);
		panel.setBounds(20, 80, 350, 200);
		refreshRemedio.setBounds(220, 300, 150, 30);
		
		// Adicao ao JFrame
		frame.add(label);
		frame.add(txtPesquisa);
		frame.add(btnPesquisa);
		frame.add(panel);
		frame.add(cadastrarRemedio);
		frame.add(refreshRemedio);
		
		// Adicao de ActionListeners e ListSelectionListener
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cadastrarRemedio.addActionListener(this);
		refreshRemedio.addActionListener(this);
		listaRemedios.addListSelectionListener(this);
		btnPesquisa.addActionListener(this);
		
		frame.setVisible(true);
	}
	/**
	 * Metodo que descreve o que deve acontecer a partir dos cliques em botoes.
	 * Este metodo permite que a lista de "Remedios" seja atualizada, o gerenciamento 
	 * da pesquisa na lista, e a abertura de "TelaCadastroEdicaoRemedio" sem dados 
	 * em seus JTextFields.
	 * 
	 * @see TelaCadastroEdicaoRemedio
	 * @param e : ActionEvent usado para determinar a fonte da acao em "TelaRemedio".
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		Object fonte = e.getSource();
		if(fonte == cadastrarRemedio) {
			new TelaCadastroEdicaoRemedio().inserirEditarRemedio(1, dadosTelaRemedio, 0);
		}
		else if(fonte == refreshRemedio) {
			listaRemedios.setListData(new ControleRemedio(dadosTelaRemedio).getInfo());			
			listaRemedios.updateUI();
		}
		else if(fonte == btnPesquisa) {
			pesquisaRemedios = new ControleRemedio(dadosTelaRemedio).getInfo(txtPesquisa.getText());
			if(txtPesquisa.getText().equals("")) {
				listaRemedios.setListData(new ControleRemedio(dadosTelaRemedio).getInfo());			
				listaRemedios.updateUI();
			}
			else {
				listaRemedios.setListData(pesquisaRemedios);			
				listaRemedios.updateUI();
			}
		}
	}
	
	/**
	 * Metodo que descreve o que deve acontecer a partir do clique em um item da lista
	 * de "Remedios". Abre "TelaCadastroEdicaoRemedio" com os dados correspondentes ao 
	 * item da lista.
	 * 
	 * @see TelaCadastroEdicaoPaciente
	 * @param e : ListSelectonEvent utilizado para determinar a fonte da acao (no caso, 
	 * so ha uma fonte possivel: "listaRemedios").
	 * @return void
	 * */
	public void valueChanged(ListSelectionEvent e) {
		Object fonte = e.getSource();
		if(e.getValueIsAdjusting() && fonte == listaRemedios) {
			String split[] = listaRemedios.getSelectedValue().split(" - ");
			new TelaCadastroEdicaoRemedio().inserirEditarRemedio(2, dadosTelaRemedio, Integer.parseInt(split[0]));
		}
	}
}
