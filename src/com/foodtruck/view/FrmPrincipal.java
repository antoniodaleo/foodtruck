package com.foodtruck.view;

import java.awt.BorderLayout;
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
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.swing.JMenuItem;
import javax.swing.border.MatteBorder;

import com.foodtruck.modal.Caixa;
import com.foodtruck.modal.Relatorio;
import com.foodtruck.modal.Usuario;
import com.foodtruck.modal.hora;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel pnlLista ;
	
	private Color oldColor = null;
	
	// --------- VAR LABEL
	private JLabel lblExit;
	private JLabel lblUsuario;
	private JLabel lblNomeUsuario;
	private JButton btnFechamentoCaixa;
	private JButton btnAberturaCaixa;
	
	// --------- VAR MENUITEM
	private JMenuItem mntmLoja;
	private JMenuItem mntmPdvCaixa;
	
	
	JFrame FrmPrincipal ; 
	Usuario u = new Usuario(); 
	
	public FrmPrincipal(int id, String nome, JButton btn, JMenuItem menu, JMenuItem menuPdv) {	
		setUndecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1385, 744);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.WHITE);
		pnlCabecalho.setBounds(0, 0, 1354, 91);
		contentPane.add(pnlCabecalho);
		pnlCabecalho.setLayout(null);
		
		lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = JOptionPane.showConfirmDialog(null,"Atenção, saindo do sistema....  " , "SAIDA DO SISTEMA", JOptionPane.YES_NO_OPTION); 
				if (x==JOptionPane.YES_OPTION) {

					System.exit(0);
					
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
		
		JLabel lblMenuPrincipal = new JLabel("Men\u00FA principal");
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuPrincipal.setFont(new Font("Arial", Font.BOLD, 16));
		lblMenuPrincipal.setForeground(Color.WHITE);
		lblMenuPrincipal.setBounds(10, 26, 303, 30);
		pnlCabecalho.add(lblMenuPrincipal);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/cabecalhoMenu.png")));
		label.setBounds(0, 0, 1368, 91);
		pnlCabecalho.add(label);
		
		pnlLista = new JPanel();
		pnlLista.setBackground(Color.LIGHT_GRAY);
		pnlLista.setBounds(0, 97, 318, 669);
		pnlLista.setVisible(true);
		contentPane.add(pnlLista);
		pnlLista.setLayout(null);
		
		JLabel lblExitMenu = new JLabel("");
		lblExitMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pnlLista.setVisible(false);
			}
		});
		lblExitMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblExitMenu.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/exit24bianco.png")));
		lblExitMenu.setBounds(272, 0, 46, 32);
		pnlLista.add(lblExitMenu);
		
		JLabel lblCadastro = new JLabel("CADASTRO");
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Arial", Font.BOLD, 15));
		lblCadastro.setBounds(0, 0, 318, 32);
		pnlLista.add(lblCadastro);
		
		JMenuItem mntmProdutos = new JMenuItem("PRODUTOS");
		mntmProdutos.setEnabled(false);
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmProduto p  = new FrmProduto(); 
				p.setVisible(true);
			}
		});
		mntmProdutos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntmProdutos.getBackground();
				mntmProdutos.setBackground(Color.WHITE);
				mntmProdutos.setForeground(Color.GRAY);
				mntmProdutos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntmProdutos.setBackground(oldColor);
				mntmProdutos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				mntmProdutos.setForeground(Color.BLACK);
			}
		});
		mntmProdutos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmProdutos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/hot-tea.png")));
		mntmProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		mntmProdutos.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmProdutos.setBounds(43, 154, 275, 49);
		pnlLista.add(mntmProdutos);
		
		JMenuItem mntmSetor = new JMenuItem("SETOR");
		mntmSetor.setEnabled(false);
		mntmSetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmSetor s = new FrmSetor() ; 
				s.setVisible(true);
			}
		});
		mntmSetor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmSetor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntmSetor.getBackground();
				mntmSetor.setBackground(Color.WHITE);
				mntmSetor.setForeground(Color.GRAY);
				mntmSetor.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
					
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntmSetor.setBackground(oldColor);
				mntmSetor.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				mntmSetor.setForeground(Color.BLACK);
			}
		});
		mntmSetor.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmSetor.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/delivery-cart.png")));
		mntmSetor.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSetor.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmSetor.setBounds(43, 94, 275, 49);
		pnlLista.add(mntmSetor);
		
		mntmLoja = new JMenuItem("LOJA");
		mntmLoja.setEnabled(false);
		mntmLoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmLoja f = new FrmLoja(); 
				f.setVisible(true);
				
			}
		});
		mntmLoja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmLoja.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntmLoja.getBackground();
				mntmLoja.setBackground(Color.WHITE);
				mntmLoja.setForeground(Color.GRAY);
				mntmLoja.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				
			}		
			@Override
			public void mouseExited(MouseEvent e) {
				mntmLoja.setBackground(oldColor);
				mntmLoja.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				mntmLoja.setForeground(Color.BLACK);
				
			}
		});
		mntmLoja.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmLoja.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/warehouse.png")));
		mntmLoja.setHorizontalAlignment(SwingConstants.LEFT);
		mntmLoja.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmLoja.setBounds(43, 34, 275, 49);
		pnlLista.add(mntmLoja);
		
		JMenuItem mntmUsuarios = new JMenuItem("USUARIOS");
		mntmUsuarios.setEnabled(false);
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUsuario us = new FrmUsuario(); 
				us.setVisible(true);
			}
		});
		mntmUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		mntmUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntmUsuarios.getBackground();
				mntmUsuarios.setBackground(Color.WHITE);
				mntmUsuarios.setForeground(Color.GRAY);
				mntmUsuarios.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntmUsuarios.setBackground(oldColor);
				mntmUsuarios.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				mntmUsuarios.setForeground(Color.BLACK);
			}
		});
		mntmUsuarios.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/u.png")));
		mntmUsuarios.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		mntmUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmUsuarios.setBounds(43, 274, 275, 49);
		pnlLista.add(mntmUsuarios);
		
		JMenuItem mntmPagamento = new JMenuItem("PAGAMENTO");
		mntmPagamento.setEnabled(false);
		mntmPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTipoPagamento f = new FrmTipoPagamento(); 
				f.setVisible(true);
			}
		});
		mntmPagamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		mntmPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntmPagamento.getBackground();
				mntmPagamento.setBackground(Color.WHITE);
				mntmPagamento.setForeground(Color.GRAY);
				mntmPagamento.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntmPagamento.setBackground(oldColor);
				mntmPagamento.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				mntmPagamento.setForeground(Color.BLACK);
			}
		});
		mntmPagamento.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		mntmPagamento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/wallet.png")));
		mntmPagamento.setHorizontalAlignment(SwingConstants.LEFT);
		mntmPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmPagamento.setBounds(43, 214, 275, 49);
		pnlLista.add(mntmPagamento);
		
		JLabel lblPdv = new JLabel("PDV");
		lblPdv.setHorizontalAlignment(SwingConstants.CENTER);
		lblPdv.setFont(new Font("Arial", Font.BOLD, 15));
		lblPdv.setBounds(0, 391, 318, 32);
		pnlLista.add(lblPdv);
		
		mntmPdvCaixa = new JMenuItem("PDV CAIXA");
		mntmPdvCaixa.setEnabled(false);
		mntmPdvCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmPDV pdv = new FrmPDV(id, nome,0);
				pdv.setVisible(true);
				
			}
		});
		mntmPdvCaixa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mntmPdvCaixa.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/cash-register.png")));
		mntmPdvCaixa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntmPdvCaixa.getBackground();
				mntmPdvCaixa.setBackground(Color.WHITE);
				mntmPdvCaixa.setForeground(Color.GRAY);
				mntmPdvCaixa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				mntmPdvCaixa.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntmPdvCaixa.setBackground(oldColor);
				mntmPdvCaixa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				
			}
		});
		mntmPdvCaixa.setHorizontalAlignment(SwingConstants.LEFT);
		mntmPdvCaixa.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmPdvCaixa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		mntmPdvCaixa.setBounds(43, 434, 275, 49);
		pnlLista.add(mntmPdvCaixa);
		
		JMenuItem mntnRelatorio = new JMenuItem("FECHAR CAIXA");
		mntnRelatorio.setEnabled(false);
		mntnRelatorio.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/clipboard.png")));
		mntnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmRelatorio r = new FrmRelatorio(id, nome, btnAberturaCaixa, mntnRelatorio, mntmPdvCaixa); 
				r.setVisible(true);
				
			}
		});
		
		
		mntnRelatorio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		mntnRelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				oldColor = mntnRelatorio.getBackground();
				mntnRelatorio.setBackground(Color.WHITE);
				mntnRelatorio.setForeground(Color.GRAY);
				mntnRelatorio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntnRelatorio.setBackground(oldColor);
				mntnRelatorio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
				mntnRelatorio.setForeground(Color.BLACK);
			}
		});
		mntnRelatorio.setHorizontalAlignment(SwingConstants.LEFT);
		mntnRelatorio.setFont(new Font("Arial", Font.PLAIN, 14));
		mntnRelatorio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		mntnRelatorio.setBounds(43, 494, 275, 49);
		pnlLista.add(mntnRelatorio);
		
		JMenuItem mntmSuporte = new JMenuItem("SUPORTE ");
		mntmSuporte.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/foodtruck/image/internet.png")));
		mntmSuporte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
		mntmSuporte.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSuporte.setFont(new Font("Arial", Font.PLAIN, 14));
		mntmSuporte.setEnabled(false);
		mntmSuporte.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		mntmSuporte.setBounds(43, 577, 275, 49);
		pnlLista.add(mntmSuporte);
		
		JPanel pnlOperador = new JPanel();
		pnlOperador.setBackground(Color.LIGHT_GRAY);
		pnlOperador.setBounds(1039, 97, 318, 131);
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
		pnlOraAtuale.setBounds(1039, 615, 318, 118);
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
		lblHora.setBounds(73, 44, 239, 58);
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setBounds(421, 102, 504, 91);
		contentPane.add(panel);
		
		btnAberturaCaixa = new JButton("ABERTURA CAIXA");
		btnAberturaCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmPdvCaixa.setEnabled(true);
				mntnRelatorio.setEnabled(true);
				btnFechamentoCaixa.setEnabled(true);
				
				FrmAbCaixa f = new FrmAbCaixa(); 
				f.setVisible(true);
				btnAberturaCaixa.setEnabled(false);
				
			}
		});
		btnAberturaCaixa.setBackground(Color.GREEN);
		btnAberturaCaixa.setForeground(Color.DARK_GRAY);
		btnAberturaCaixa.setFont(new Font("Arial", Font.BOLD, 13));
		btnAberturaCaixa.setBounds(10, 11, 213, 69);
		panel.add(btnAberturaCaixa);
		
		btnFechamentoCaixa = new JButton("FECHAMENTO CAIXA");
		btnFechamentoCaixa.setEnabled(false);
		btnFechamentoCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnFechamentoCaixa.setEnabled(true);
				mntnRelatorio.setEnabled(true);
				FrmRelatorio r = new FrmRelatorio(id, nome, btnAberturaCaixa, mntnRelatorio, mntmPdvCaixa); 
				r.setVisible(true);
				
			}
		});
		btnFechamentoCaixa.setBackground(Color.RED);
		btnFechamentoCaixa.setForeground(Color.DARK_GRAY);
		btnFechamentoCaixa.setFont(new Font("Arial", Font.BOLD, 13));
		btnFechamentoCaixa.setBounds(271, 11, 223, 69);
		panel.add(btnFechamentoCaixa);
		
		Date data = new Date(); 
		SimpleDateFormat formattare = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		String dataOggi =  formattare.format(data);
		Relatorio r = new Relatorio();
		
		if (nome.equals("administrador")) {
			
			mntmUsuarios.setEnabled(true);
			mntmSetor.setEnabled(true);
			mntmProdutos.setEnabled(true);
			mntmPagamento.setEnabled(true);
			mntnRelatorio.setEnabled(false);
			mntmPdvCaixa.setEnabled(false);
			mntmLoja.setEnabled(true);
			
		}else{
		
			mntmUsuarios.setEnabled(false);
			mntmSetor.setEnabled(false);
			mntmProdutos.setEnabled(false);
			mntmPagamento.setEnabled(false);
			mntnRelatorio.setEnabled(false);
			mntmPdvCaixa.setEnabled(false);
			mntmLoja.setEnabled(false);
			
		}
	}
}
