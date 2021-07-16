package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.foodtruck.modal.Caixa;
import com.foodtruck.modal.Relatorio;
import com.foodtruck.modal.Venda;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrmRelatorio extends JFrame {

	private JPanel contentPane;
	private JTable aTableVista;
	Relatorio c = new Relatorio();
	private JTable aTableCartao;
	private JTable aTableTotal;
	private JTable aTableTotDia;
	private JTable table;
	private JTextField txtSaca;
	private JTextField txtCaixa;
    private	JButton btnRefresh;
    private JButton btnSacar;
    private JButton btnFecha;
    JDateChooser dtData2;
    
    
    public FrmRelatorio(int id, String nome, JButton btn, JMenuItem menu, JMenuItem menuPdv) {
    	setResizable(false);
    	setTitle("Operador: \r\n" + nome);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 1028, 664);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 478, 305);
		contentPane.add(scrollPane);
		
		aTableVista = new JTable();
		c.fecharCassa(aTableVista, nome);
		scrollPane.setViewportView(aTableVista);
		
		JLabel lblTotalAVista = new JLabel("TOTAL A VISTA");
		lblTotalAVista.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblTotalAVista.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotalAVista.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAVista.setBounds(10, 84, 478, 47);
		contentPane.add(lblTotalAVista);
		
		JLabel lblTotalCartoDe = new JLabel("TOTAL CART\u00C3O DE CREDITO");
		lblTotalCartoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCartoDe.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotalCartoDe.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblTotalCartoDe.setBounds(524, 84, 478, 47);
		contentPane.add(lblTotalCartoDe);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(524, 140, 478, 305);
		contentPane.add(scrollPane_1);
		
		aTableCartao = new JTable();
		c.fecharCassaCartao(aTableCartao, nome);
		scrollPane_1.setViewportView(aTableCartao);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(267, 469, 215, 37);
		contentPane.add(scrollPane_2);
		
		aTableTotal = new JTable();
		c.totalCaixa(aTableTotal, nome);  // aTableTotal
		scrollPane_2.setViewportView(aTableTotal);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(524, 469, 215, 37);
		contentPane.add(scrollPane_3);
		
		table = new JTable();
		c.totalCaixaCarta(table, nome);
		scrollPane_3.setViewportView(table);
		
		JLabel label_1 = new JLabel("TOTAL CART\u00C3O DE CREDITO");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		label_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		label_1.setBounds(777, 469, 225, 37);
		contentPane.add(label_1);
		
		JLabel lblTotalAVista_1 = new JLabel("TOTAL A VISTA");
		lblTotalAVista_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAVista_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotalAVista_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblTotalAVista_1.setBounds(10, 470, 225, 37);
		contentPane.add(lblTotalAVista_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(392, 542, 215, 37);
		contentPane.add(scrollPane_4);
		
		aTableTotDia = new JTable();
		c.totalGenerale(aTableTotDia, nome);
		scrollPane_4.setViewportView(aTableTotDia);
		
		JLabel lblTotalDoDia = new JLabel("TOTAL DO DIA");
		lblTotalDoDia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalDoDia.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotalDoDia.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblTotalDoDia.setBounds(127, 542, 225, 37);
		contentPane.add(lblTotalDoDia);
		
		
		JLabel lblData = new JLabel("DATA ");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Arial", Font.BOLD, 13));
		lblData.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblData.setBounds(10, 11, 125, 37);
		contentPane.add(lblData);
		
		JLabel lblSacar = new JLabel("SACAR");
		lblSacar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSacar.setFont(new Font("Arial", Font.BOLD, 13));
		lblSacar.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblSacar.setBounds(344, 11, 120, 37);
		contentPane.add(lblSacar);
		
		txtSaca = new JTextField();
		txtSaca.setText("0,00");
		txtSaca.setFont(new Font("Arial", Font.BOLD, 15));
		txtSaca.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaca.setBounds(474, 11, 103, 37);
		contentPane.add(txtSaca);
		txtSaca.setColumns(10);
		
		JLabel lblCaixa = new JLabel("CAIXA");
		lblCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaixa.setFont(new Font("Arial", Font.BOLD, 13));
		lblCaixa.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		lblCaixa.setBounds(650, 11, 189, 37);
		contentPane.add(lblCaixa);
		
		txtCaixa = new JTextField();
		txtCaixa.setText("0,00");
		txtCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		txtCaixa.setFont(new Font("Arial", Font.BOLD, 15));
		txtCaixa.setColumns(10);
		txtCaixa.setBounds(849, 11, 97, 37);
		contentPane.add(txtCaixa);
		
		btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSaca.requestFocus();
				txtCaixa.selectAll();
				
				Caixa c = new Caixa(); 
			    c.mostraGiacenza();
			    txtCaixa.setText(c.getGiacenza()+ "");
			    
			}
		});
		btnRefresh.setIcon(new ImageIcon(FrmRelatorio.class.getResource("/com/foodtruck/image/reload.png")));
		btnRefresh.setBounds(958, 12, 44, 37);
		contentPane.add(btnRefresh);
		
		
		btnFecha = new JButton("FECHAR CAIXA");
		btnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int x = JOptionPane.showConfirmDialog(null,"Atenção, quer fechar a caixa?   " , "FECHAR CAIXA", JOptionPane.YES_NO_OPTION); 
				if (x==JOptionPane.YES_OPTION) {
					Caixa c = new Caixa(); 
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
					//String data = sdf.format(dtData.getDate()); 
					//SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
					String data2 = sdf.format(dtData2.getDate()); 
					
					//System.out.println(data);
					System.out.println(data2);
					
					c.setData_fe(data2);
					c.setSaco(Double.parseDouble(txtSaca.getText()));
					c.fechaturaCaixa();
					txtSaca.setText("");
					txtCaixa.setText("");
					dispose(); 
					//btn.setEnabled(true);
					menu.setEnabled(false);
					menuPdv.setEnabled(false);
					JOptionPane.showMessageDialog(null, "PARA ABRIR CAIXA COM OUTRO OPERADOR, SAIR DO SISTEMA...");	
				}else{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		btnFecha.setForeground(new Color(255, 255, 224));
		btnFecha.setFont(new Font("Arial", Font.BOLD, 17));
		btnFecha.setBackground(new Color(255, 0, 0));
		btnFecha.setBounds(774, 539, 228, 75);
		contentPane.add(btnFecha);
		
		btnSacar = new JButton("");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Caixa c = new Caixa(); 
				c.setSaco(Double.parseDouble(txtSaca.getText()));
				c.sacaCaixa();
				c.mostraGiacenza();
				txtSaca.setText("0,00");
				System.out.println(c.getGiacenza()+"");
				txtCaixa.setText( c.getGiacenza()+""); 
				
			}
		});
		btnSacar.setIcon(new ImageIcon(FrmRelatorio.class.getResource("/com/foodtruck/image/dollar-coins.png")));
		btnSacar.setBounds(587, 11, 44, 37);
		contentPane.add(btnSacar);
		
		dtData2 = new JDateChooser();
		dtData2.setLocale(new Locale("pt"));
		dtData2.setDateFormatString("dd/MM/yyyy ");
		dtData2.setBounds(163, 24, 143, 23);
		contentPane.add(dtData2);
		
		
		
		
	}
}
