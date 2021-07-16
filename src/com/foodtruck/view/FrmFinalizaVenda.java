package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import com.foodtruck.modal.Caixa;
import com.foodtruck.modal.TipoPagamento;
import com.foodtruck.modal.Venda;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JComboBox;

public class FrmFinalizaVenda extends JDialog {

	private final JPanel txtRecebido = new JPanel();
	private JTextField txtTxttotalvenda;
	private JTextField txtValorRecebido;
	private JTextField txtTroco;
	private JCheckBox cbDinheiro;
	private JComboBox cbPagamento;
	private JMenuItem mntmVoltar;
	
	
	private void limpar(){
		txtTxttotalvenda.setText("");
		txtValorRecebido.setText("");
		txtTroco.setText("");
		
	}
	
	
	public FrmFinalizaVenda(double totaleV) {
		
		
		DecimalFormat df = new DecimalFormat(); 	
		
		setBounds(100, 100, 560, 422);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		txtRecebido.setBackground(Color.WHITE);
		txtRecebido.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(txtRecebido, BorderLayout.CENTER);
		txtRecebido.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(0, 60, 543, 45);
		txtRecebido.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblFormaDePagamento = new JLabel("FORMA DE PAGAMENTO");
		lblFormaDePagamento.setBounds(10, 11, 523, 29);
		lblFormaDePagamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormaDePagamento.setForeground(Color.WHITE);
		lblFormaDePagamento.setFont(new Font("Arial", Font.BOLD, 15));
		panel_1.add(lblFormaDePagamento);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 543, 60);
		txtRecebido.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TOTAL VENDA");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(257, 18, 133, 27);
		panel.add(lblNewLabel);
		
		txtTxttotalvenda = new JTextField();
		
		txtTxttotalvenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double totForm = totaleV; 
				df.applyPattern(" #,##0.00");
				txtTxttotalvenda.setText(df.format(totForm));
				
				txtValorRecebido.requestFocus();
				txtValorRecebido.selectAll();
			}
		});
		txtTxttotalvenda.setText(totaleV+ "");
		txtTxttotalvenda.setHorizontalAlignment(SwingConstants.CENTER);
		txtTxttotalvenda.setFont(new Font("Arial", Font.BOLD, 18));
		txtTxttotalvenda.setBounds(400, 11, 133, 38);
		panel.add(txtTxttotalvenda);
		txtTxttotalvenda.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 104, 543, 103);
		txtRecebido.add(panel_2);
		
		JLabel lblDinheiro = new JLabel("PAGAMENTO");
		lblDinheiro.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.ORANGE));
		lblDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinheiro.setForeground(Color.BLACK);
		lblDinheiro.setFont(new Font("Arial", Font.BOLD, 15));
		lblDinheiro.setBounds(23, 42, 133, 27);
		panel_2.add(lblDinheiro);
		
		 
		cbDinheiro = new JCheckBox("");
		cbDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRecebido.requestFocus();
				
			}
		});
		cbDinheiro.setSelected(true);
		cbDinheiro.setBounds(208, 44, 21, 23);
		panel_2.add(cbDinheiro);
		
		 cbPagamento = new JComboBox();
		TipoPagamento tp = new TipoPagamento(); 
		tp.carrPagamento(cbPagamento);
		cbPagamento.setFont(new Font("Arial", Font.BOLD, 14));
		cbPagamento.setBounds(280, 45, 231, 27);
		panel_2.add(cbPagamento);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(0, 209, 543, 18);
		txtRecebido.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(0, 227, 543, 68);
		txtRecebido.add(panel_4);
		
		JLabel label = new JLabel("VALOR RECEBIDO");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 200, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(10, 18, 148, 27);
		panel_4.add(label);
		
		txtValorRecebido = new JTextField();
		txtValorRecebido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double tot = totaleV; 
				double recive = Double.parseDouble(txtValorRecebido.getText()); 
				
				double reciveForm = recive; 
				df.applyPattern(" #,##0.00");
				txtValorRecebido.setText(df.format(reciveForm));
				
				double troco= recive - tot;
				
				
				Double trocoFor = troco;
				df.applyPattern(" #,##0.00");
				txtTroco.setText(df.format(trocoFor));
				
				//Venda v = new Venda(); 
				//v.lowStock(codP, qtde);
				
				
			}
		});

		txtValorRecebido.setHorizontalAlignment(SwingConstants.CENTER);
		txtValorRecebido.setFont(new Font("Arial", Font.BOLD, 18));
		txtValorRecebido.setColumns(10);
		txtValorRecebido.setBounds(168, 10, 95, 38);
		panel_4.add(txtValorRecebido);
		
		JLabel lblTroco = new JLabel("TROCO");
		lblTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroco.setForeground(Color.BLACK);
		lblTroco.setFont(new Font("Arial", Font.BOLD, 15));
		lblTroco.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 200, 0)));
		lblTroco.setBounds(296, 18, 112, 27);
		panel_4.add(lblTroco);
		
		txtTroco = new JTextField();
		txtTroco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		txtTroco.setText("0.00");
		txtTroco.setHorizontalAlignment(SwingConstants.CENTER);
		txtTroco.setFont(new Font("Arial", Font.BOLD, 18));
		txtTroco.setColumns(10);
		txtTroco.setBounds(438, 10, 95, 38);
		panel_4.add(txtTroco);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		panel_5.setBackground(Color.GRAY);
		panel_5.setBounds(0, 295, 543, 90);
		txtRecebido.add(panel_5);
		
		
		mntmVoltar = new JMenuItem("CONFIRMA");
		mntmVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Venda v = new Venda(); 
				v.setTotal_venda(totaleV); 
				TipoPagamento t = new TipoPagamento(); 
				v.setTipo_pagamento_id(t.codigoPagamento(cbPagamento.getSelectedItem().toString()));
				v.inserirPagamento();
				v.inserirTotale();
				
				Caixa c = new Caixa(); 
				c.alimentaCaixa(totaleV);
				
				
				//c.aggiornaTotale();
				
				//stampa.isSelected(); 
				
				
				limpar(); 
				dispose(); 
				
			}
		});
		
		
		mntmVoltar.setIcon(new ImageIcon(FrmFinalizaVenda.class.getResource("/com/foodtruck/image/accept.png")));
		mntmVoltar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVoltar.setForeground(Color.BLACK);
		mntmVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		mntmVoltar.setFont(new Font("Arial", Font.BOLD, 15));
		mntmVoltar.setBorder(new MatteBorder(0, 1, 2, 0, (Color) new Color(255, 255, 255)));
		mntmVoltar.setBounds(35, 11, 206, 49);
		panel_5.add(mntmVoltar);
		
		JMenuItem mntmCancela = new JMenuItem("CANCELA");
		mntmCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		mntmCancela.setIcon(new ImageIcon(FrmFinalizaVenda.class.getResource("/com/foodtruck/image/remove.png")));
		mntmCancela.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmCancela.setHorizontalAlignment(SwingConstants.LEFT);
		mntmCancela.setForeground(Color.BLACK);
		mntmCancela.setFont(new Font("Arial", Font.BOLD, 15));
		mntmCancela.setBorder(new MatteBorder(0, 0, 2, 1, (Color) new Color(255, 255, 255)));
		mntmCancela.setBounds(279, 11, 199, 49);
		panel_5.add(mntmCancela);
	}
}
