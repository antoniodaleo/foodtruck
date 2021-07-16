package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.nio.channels.ClosedSelectorException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;

import javax.swing.JMenuItem;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import com.foodtruck.modal.ConnectionFactory;
import com.foodtruck.modal.Loja;
import com.foodtruck.modal.Produto;
import com.foodtruck.modal.Usuario;
import com.foodtruck.modal.Venda;
import com.foodtruck.modal.hora;

import net.sf.jasperreports.charts.design.JRDesignAreaPlot;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import javax.swing.JCheckBox;

public class FrmPDV extends JFrame {

	private JPanel contentPane;
	private Color oldColor = null;
	
	// --------- VAR LABEL
	private JLabel lblExit;
	private JLabel lblUsuario;
	private JLabel lblNomeUsuario;
	
	// --------- Variabili JmenuItem
	private JMenuItem mntmFinalizarVenda;
	private JMenuItem mntmAbrirFenda;
	
	JFrame FrmPrincipal ; 
	
	// --------- Variabili JButton
	private JButton btnImprimir;
	
	// --------- Variabili JtextField
	private JTextField txtCodigo;
	private JTextField txtDescricao;
	private JTextField txtPreco;
	private JTextField txtQtd;
	private JTextField txtSubtotal;
	private JTextField txtTotal;
	private JTextField txtPesquisaCodigo;
	
	// --------- Text Areas and Jtable
	private JTable aTabPro;
	private JTextArea txtCupon ;
	
	// --------- Variabili primitive
	private Double totalGeralDaFormattare;
	private int quantita; 
	private int codigoUsuario;
	private int codigoPagamento;
	private int codigoVenda;
	private int item;
	private int codigoProduto; 	
	private int codProdPassato; 
	private int qtdPassato; 
	private double totale =0; 
	private String nomeCliente; 
	
	
	// -----------Metodo para limpar caselle 
	public void clean (){
		txtCodigo.setText("");
		txtDescricao.setText("");
		txtPreco.setText("");
		txtQtd.setText("");
		txtSubtotal.setText("");
		txtTotal.setText("");
		txtCodigo.requestFocus();	
	};
	// -----------Metodo para limpar Cupon area
	public void cleanCupon(){
		txtCupon.setText("");	
		}
	
	
	
	public FrmPDV(int id, String nome, double totaleVendita) {
		// codUs riceve subito l id dell operatore 
		codigoUsuario = id; 
		
		// APRIAMO LA VENDITA . 
		DecimalFormat df = new DecimalFormat(); 		
		Date hoje = new Date(); 	
		String hojeFormatado = DateFormat.getInstance().format(hoje); 	
		Locale.setDefault(new Locale("pt","brazil"));
		
		setUndecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1385, 744);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ------- Pesquisa prodotti caso non si ricorda il codice
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 489, 318, 244);
		contentPane.add(scrollPane_1);
		Produto p = new Produto();
		aTabPro = new JTable();
		p.carregarProdutoV(aTabPro);
		scrollPane_1.setViewportView(aTabPro);
		
		txtPesquisaCodigo = new JTextField();
		txtPesquisaCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Produto p = new Produto(); 
				p.pesquisarPV(aTabPro, txtPesquisaCodigo.getText());
			}
		});
		txtPesquisaCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPesquisaCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPesquisaCodigo.setBounds(12, 451, 296, 27);
		contentPane.add(txtPesquisaCodigo);
		txtPesquisaCodigo.setColumns(10);
		
		JPanel pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.WHITE);
		pnlCabecalho.setBounds(0, 0, 1354, 91);
		contentPane.add(pnlCabecalho);
		pnlCabecalho.setLayout(null);
		
		lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = JOptionPane.showConfirmDialog(null,"Atenção, saindo do PDV....   " , "SAIDA DO PDV", JOptionPane.YES_NO_OPTION); 
				if (x==JOptionPane.YES_OPTION) {
					Venda v = new Venda(); 
					v.cancelaVenda(codigoVenda);
					dispose(); 
					
				}else{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/exit32bianco.png")));
			}
					
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/exit24bianco.png")));
			}
		});
		lblExit.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/exit24bianco.png")));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(1288, 11, 56, 30);
		pnlCabecalho.add(lblExit);
		
		JLabel lblMinimize = new JLabel("");
		lblMinimize.setBounds(1230, 11, 56, 30);
		pnlCabecalho.add(lblMinimize);
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED); 
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMinimize.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/minimiza32bianco.png")));
			}
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblMinimize.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/minimize24bianco.png")));
			}
		});
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/minimize24bianco.png")));
		
		JLabel lblMenuPrincipal = new JLabel("CAIXA FECHADA");
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuPrincipal.setFont(new Font("Arial", Font.BOLD, 20));
		lblMenuPrincipal.setForeground(Color.WHITE);
		lblMenuPrincipal.setBounds(212, 11, 896, 45);
		pnlCabecalho.add(lblMenuPrincipal);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setForeground(Color.WHITE);
		label.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/cabecalhoMenu.png")));
		label.setBounds(0, 0, 1368, 91);
		pnlCabecalho.add(label);
		
		JPanel pnlOperador = new JPanel();
		pnlOperador.setBackground(Color.LIGHT_GRAY);
		pnlOperador.setBounds(0, 97, 318, 128);
		contentPane.add(pnlOperador);
		pnlOperador.setLayout(null);
		
		lblUsuario = new JLabel("OPERADOR");
		lblUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 15));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(0, 0, 318, 33);
		pnlOperador.add(lblUsuario);
		
		lblNomeUsuario = new JLabel("");
		lblNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblNomeUsuario.setFont(new Font("Arial", Font.ITALIC, 13));
		lblNomeUsuario.setText(nome);
		lblNomeUsuario.setBounds(141, 77, 167, 22);
		pnlOperador.add(lblNomeUsuario);
		
		JLabel lblNomeUsuario_1 = new JLabel("Nome Usuario");
		lblNomeUsuario_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblNomeUsuario_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNomeUsuario_1.setBounds(10, 77, 107, 22);
		pnlOperador.add(lblNomeUsuario_1);
		
		JLabel lblCodId = new JLabel("Codigo Id");
		lblCodId.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblCodId.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCodId.setBounds(10, 44, 107, 22);
		pnlOperador.add(lblCodId);
		
		JLabel lblMostraIdUsuario = new JLabel("<dynamic>");
		lblMostraIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostraIdUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblMostraIdUsuario.setText(id+ "");
		lblMostraIdUsuario.setFont(new Font("Arial", Font.ITALIC, 13));
		lblMostraIdUsuario.setBounds(141, 44, 167, 22);
		pnlOperador.add(lblMostraIdUsuario);
		
		Date dataAtual = new Date(); 
		SimpleDateFormat formatDataAtual = new SimpleDateFormat(); 
		String dataFormat = formatDataAtual.format(dataAtual);
		
		JPanel pnlOraAtuale = new JPanel();
		pnlOraAtuale.setBackground(Color.LIGHT_GRAY);
		pnlOraAtuale.setBounds(0, 236, 318, 109);
		contentPane.add(pnlOraAtuale);
		pnlOraAtuale.setLayout(null);
		
		
		JLabel lblHora = new JLabel(""); 
		lblHora.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblHora.setFont(new Font("Arial", Font.BOLD, 17));
		hora horarioUtil = new hora(lblHora);
	    horarioUtil.mostrarData(true);
	    Thread thHora = horarioUtil;
	    thHora.start();
		
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(79, 44, 239, 58);
		pnlOraAtuale.add(lblHora);
		
		JLabel lblRelogio = new JLabel("DATA - HORA");
		lblRelogio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelogio.setFont(new Font("Arial", Font.BOLD, 15));
		lblRelogio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblRelogio.setBounds(0, 0, 318, 33);
		pnlOraAtuale.add(lblRelogio);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/clock.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Arial", Font.BOLD, 17));
		label_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		label_2.setBounds(10, 44, 53, 58);
		pnlOraAtuale.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(328, 192, 397, 541);
		contentPane.add(scrollPane);
		
		txtCupon = new JTextArea();
		txtCupon.setBackground(new Color(250, 250, 210));
		txtCupon.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtCupon.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(txtCupon);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(328, 97, 397, 41);
		contentPane.add(panel);
		
		JLabel lblCupon = new JLabel("CUPON");
		lblCupon.setHorizontalAlignment(SwingConstants.CENTER);
		lblCupon.setFont(new Font("Arial", Font.BOLD, 15));
		lblCupon.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblCupon.setBounds(41, 0, 318, 33);
		panel.add(lblCupon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(759, 97, 287, 41);
		contentPane.add(panel_1);
		
		JLabel lblCodigo = new JLabel("CODIGO");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 15));
		lblCodigo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblCodigo.setBounds(41, 0, 203, 33);
		panel_1.add(lblCodigo);
		
		
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
				Produto produto = new Produto();
				produto.setCodBarras(txtCodigo.getText());
				produto.setDescricao(txtCodigo.getText());
				if (produto.pesqProduto() == true) {
					
					codigoProduto = produto.getId(); 
					txtDescricao.setText(produto.getDescricao());
					txtPreco.setText(produto.getPrecoVenda() + "");
					txtQtd.setText("1");
					txtQtd.requestFocus();		
					txtQtd.selectAll();
				}else if ( produto.pesqProdutoNome()==true) {
					codigoProduto = produto.getId(); 
					txtDescricao.setText(produto.getDescricao());
					txtPreco.setText(produto.getPrecoVenda() + "");
					txtQtd.setText("1");
					txtQtd.requestFocus();		
					txtQtd.selectAll();
				}
				
			}
		});
		txtCodigo.setFont(new Font("Arial", Font.BOLD, 18));
		txtCodigo.setBounds(759, 149, 288, 41);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(760, 207, 287, 35);
		contentPane.add(panel_2);
		
		JLabel lblDescrio = new JLabel("DESCRI\u00C7\u00C3O");
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setFont(new Font("Arial", Font.BOLD, 10));
		lblDescrio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblDescrio.setBounds(41, 0, 203, 30);
		panel_2.add(lblDescrio);
		
		txtDescricao = new JTextField();
		txtDescricao.setEditable(false);
		txtDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescricao.setFont(new Font("Arial", Font.BOLD, 15));
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(760, 253, 288, 35);
		contentPane.add(txtDescricao);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(760, 299, 122, 35);
		contentPane.add(panel_3);
		
		JLabel lblPreo = new JLabel("PRE\u00C7O UNI");
		lblPreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreo.setFont(new Font("Arial", Font.BOLD, 10));
		lblPreo.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblPreo.setBounds(10, 0, 88, 30);
		panel_3.add(lblPreo);
		
		txtPreco = new JTextField();
		txtPreco.setEditable(false);
		txtPreco.setHorizontalAlignment(SwingConstants.CENTER);
		txtPreco.setFont(new Font("Arial", Font.BOLD, 13));
		txtPreco.setColumns(10);
		txtPreco.setBounds(760, 345, 122, 35);
		contentPane.add(txtPreco);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(925, 299, 122, 35);
		contentPane.add(panel_4);
		
		JLabel lblQtd = new JLabel("QTD");
		lblQtd.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtd.setFont(new Font("Arial", Font.BOLD, 10));
		lblQtd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblQtd.setBounds(10, 0, 88, 30);
		panel_4.add(lblQtd);
		
		txtQtd = new JTextField();
		txtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		txtQtd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtQtd.setEditable(true);
				quantita = Integer.parseInt( txtQtd.getText());
				double preco = Double.parseDouble(txtPreco.getText());
				double sub = preco* quantita; 
				
				Double totalSubDaFormattare = sub;
				df.applyPattern(" #,##0.00");
				txtSubtotal.setText(df.format(totalSubDaFormattare));
			
				totale =totale + sub ;
				
				 totalGeralDaFormattare = totale;
				df.applyPattern(" #,##0.00");
				txtTotal.setText(df.format(totalGeralDaFormattare));
				
				
				item++;
				
				if (item>0) {
					Venda v = new Venda(); 
					mntmFinalizarVenda.setEnabled(true);
					
				}
				
				//AQUI VOU MANDAR OS ITEMS PARA O BANCO
				//PARA A TABELA ITENS-VENDA (BANCO)
				Venda v = new Venda();
			
				v.carrinhoDeCompra(codigoVenda, codigoProduto, quantita, sub);
			

				
				
				
				
				
				codProdPassato = codigoProduto; 
				qtdPassato = quantita; 
				
				v.lowStock(codProdPassato, qtdPassato);
				
				          
				txtCupon.append(quantita +" "+  txtDescricao.getText()+ "         R$: " + txtSubtotal.getText()+"\n");
				
				txtSubtotal.requestFocus();
			}
		});
		txtQtd.setFont(new Font("Arial", Font.BOLD, 13));
		txtQtd.setColumns(10);
		txtQtd.setBounds(925, 345, 122, 35);
		contentPane.add(txtQtd);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(760, 391, 122, 35);
		contentPane.add(panel_5);
		
		JLabel lblSubtotal = new JLabel("SUBTOTAL");
		lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtotal.setFont(new Font("Arial", Font.BOLD, 10));
		lblSubtotal.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblSubtotal.setBounds(10, 0, 88, 30);
		panel_5.add(lblSubtotal);
		
		txtSubtotal = new JTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setText("");
				txtDescricao.setText("");
				txtPreco.setText("");
				txtQtd.setText("");
				txtSubtotal.setText("");
				
				// Qui manda nel cupon
				
				
				
				txtCodigo.requestFocus();
				
				
				
			}
		});
		txtSubtotal.setFont(new Font("Arial", Font.BOLD, 13));
		txtSubtotal.setColumns(10);
		txtSubtotal.setBounds(760, 437, 122, 35);
		contentPane.add(txtSubtotal);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setBounds(759, 617, 287, 41);
		contentPane.add(panel_6);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
		lblTotal.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblTotal.setBounds(41, 0, 203, 33);
		panel_6.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double totForm = totale; 
				df.applyPattern(" #,##0.00");
				txtTotal.setText(df.format(totForm));
			}
		});
		txtTotal.setFont(new Font("Arial", Font.BOLD, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(758, 672, 288, 61);
		contentPane.add(txtTotal);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(1067, 97, 287, 636);
		contentPane.add(panel_7);
		
		JLabel label_1 = new JLabel("CODIGO");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		label_1.setBounds(41, 0, 203, 33);
		panel_7.add(label_1);
		
		mntmFinalizarVenda = new JMenuItem("FINALIZAR VENDA");
		mntmFinalizarVenda.setEnabled(false);
		mntmFinalizarVenda.setIcon(new ImageIcon(FrmPDV.class.getResource("/com/foodtruck/image/money.png")));
		mntmFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Venda v = new Venda(); 
				v.inserirCliente(nomeCliente);
				
				txtCodigo.setEditable(false);
				mntmFinalizarVenda.setEnabled(false);
				txtCupon.append(" \n"+
								" \n"+
								" \n"+
						"Total :   R$ "+  totalGeralDaFormattare +" \n"+
						"Mesa Cliente :"+   nomeCliente +" \n"
						
						);
				
				
				btnImprimir.setEnabled(true);
				btnImprimir.requestFocusInWindow();
				
				
				double parametroTotal =  totale; 
				FrmFinalizaVenda fv = new FrmFinalizaVenda(parametroTotal); 
				fv.setVisible(true);
				
			}
		});
		mntmFinalizarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmFinalizarVenda.setHorizontalAlignment(SwingConstants.LEFT);
		mntmFinalizarVenda.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmFinalizarVenda.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmFinalizarVenda.setBounds(10, 169, 275, 49);
		panel_7.add(mntmFinalizarVenda);
		
		JMenuItem mntmCancelarVenda = new JMenuItem("CANCELAR VENDA");
		mntmCancelarVenda.setEnabled(false);
		mntmCancelarVenda.setIcon(new ImageIcon(FrmPDV.class.getResource("/com/foodtruck/image/delete.png")));
		mntmCancelarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int x = JOptionPane.showConfirmDialog(null,"Atenção, quer cancelar a venda?   " , "CANCELA VENDA", JOptionPane.YES_NO_OPTION); 
				if (x==JOptionPane.YES_OPTION) {

					Venda v = new Venda(); 
					v.cancelaVenda(codigoVenda);
					JOptionPane.showMessageDialog(null, "Venda Cancelada com sucesso!!");
					
					clean();
					cleanCupon();
					
					
					totale = 0; 
					//codigoUsuario = 0 ;
					codigoProduto=0;
					codigoVenda=0; 
					mntmAbrirFenda.setEnabled(true);
					mntmFinalizarVenda.setEnabled(false);
					mntmCancelarVenda.setEnabled(false);
					
					
				}else{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
				
				
			}
		});
		mntmCancelarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmCancelarVenda.setHorizontalAlignment(SwingConstants.LEFT);
		mntmCancelarVenda.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmCancelarVenda.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmCancelarVenda.setBounds(10, 232, 275, 49);
		panel_7.add(mntmCancelarVenda);
		
		JMenuItem mntmHome = new JMenuItem("HOME");
		mntmHome.setBounds(10, 44, 275, 49);
		panel_7.add(mntmHome);
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null,"Atenção, saindo do PDV....   " , "SAIDA DO PDV", JOptionPane.YES_NO_OPTION); 
				if (x==JOptionPane.YES_OPTION) {
					
					
					dispose(); 
				}else{
					
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
				 
			}
		});
		mntmHome.setIcon(new ImageIcon(FrmPDV.class.getResource("/com/foodtruck/image/warehouse.png")));
		mntmHome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmHome.setHorizontalAlignment(SwingConstants.LEFT);
		mntmHome.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmHome.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		
		mntmAbrirFenda = new JMenuItem("ABRIR VENDA");
		mntmAbrirFenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 nomeCliente= JOptionPane.showInputDialog("Digite o nome do cliente..");
				
				txtCodigo.setEditable(true);
				txtCodigo.setEnabled(true);
				txtCodigo.requestFocus();
				
				mntmAbrirFenda.setEnabled(false);
				lblMenuPrincipal.setText("CAIXA ABERTA");
				
				mntmFinalizarVenda.setEnabled(true);
				mntmCancelarVenda.setEnabled(true);
				
				
				if (codigoUsuario > 0 ) {

					Venda v = new Venda();
					codigoVenda =v.abrirVenda(codigoUsuario, codigoPagamento);
					
				}
				
				txtCupon.append("---------------------------------------------\n"+
					    		"                 CUPON NÃO FISCAL\n\n"+
					    		"Restaurante: Laziz Arabic Food\n\n"+
								"Qtd    Item                 Preço\n");
			}
		});
		mntmAbrirFenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmAbrirFenda.setIcon(new ImageIcon(FrmPDV.class.getResource("/com/foodtruck/image/cash-register.png")));
		mntmAbrirFenda.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAbrirFenda.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmAbrirFenda.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmAbrirFenda.setBounds(10, 109, 275, 49);
		panel_7.add(mntmAbrirFenda);
		
		JLabel lblItem = new JLabel("Item   |  Descri\u00E7\u00E3o  |  Pre\u00E7o   ");
		lblItem.setFont(new Font("Arial", Font.PLAIN, 13));
		lblItem.setBounds(328, 160, 397, 21);
		contentPane.add(lblItem);
		
		
		
		btnImprimir = new JButton("IMPRIMIR CUPON");
		getRootPane().setDefaultButton(btnImprimir);
			btnImprimir.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent arg0) {
				
			System.out.println(codigoVenda);
				try {
					
					Produto p = new Produto() ; 
					try {
						p.imprimirVenda(codigoVenda);
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
									
					Venda v = new Venda(); 
					
					//txtCupon.print();
					
					btnImprimir.setEnabled(false);
					clean();
					cleanCupon();
					mntmAbrirFenda.setEnabled(true);
					totale = 0; 
					//codigoUsuario = 0 ;
					codigoProduto=0;
					codigoVenda=0; 
					mntmFinalizarVenda.setEnabled(false);
					mntmCancelarVenda.setEnabled(false);
					
					
				} catch (Exception e) {
					
				}
			}
			
			
			
		});
		btnImprimir.setEnabled(false);
		btnImprimir.setFont(new Font("Arial", Font.PLAIN, 13));
		btnImprimir.setBounds(759, 556, 287, 41);
		contentPane.add(btnImprimir);
		
		JLabel lblNewLabel = new JLabel("  PESQUISA CODIGO PRODUTO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setIcon(new ImageIcon(FrmPDV.class.getResource("/com/foodtruck/image/barcode.png")));
		lblNewLabel.setBounds(12, 413, 296, 27);
		contentPane.add(lblNewLabel);
		//jpanelProduto.add(scrollPane_1);
		//jpanelProduto.add();
		
		
		
		
	
	}
}
