package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.foodtruck.modal.Caixa;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmAbCaixa extends JFrame {

	private JPanel contentPane;
	private JTextField txtGiacenza;
	
	private JButton btnExecutaAbertura;
	private JTextField txtDataHoje;
	
	public FrmAbCaixa() {
		setTitle("ABERTURA CAIXA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 213);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		Date data = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		System.out.println(data);
		String dataForm = sdf.format(data);
		
		btnExecutaAbertura = new JButton("EXECUTA ABERTURA");
		getRootPane().setDefaultButton(btnExecutaAbertura);
		btnExecutaAbertura.setFont(new Font("Arial", Font.BOLD, 12));
		btnExecutaAbertura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Caixa c = new Caixa(); 
				c.setGiacenza(Double.parseDouble(txtGiacenza.getText()));
				c.aberturaCaixa();
				dispose(); 
			}
		});
		btnExecutaAbertura.setBounds(10, 96, 163, 32);
		contentPane.add(btnExecutaAbertura);
		
		txtGiacenza = new JTextField();
		txtGiacenza.setText("0.00");
		txtGiacenza.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiacenza.setFont(new Font("Arial", Font.BOLD, 15));
		txtGiacenza.setBounds(10, 44, 163, 32);
		contentPane.add(txtGiacenza);
		txtGiacenza.setColumns(10);
		
		JLabel lblInformaDinheiroNa = new JLabel("VALOR EM CAIXA");
		lblInformaDinheiroNa.setForeground(Color.RED);
		lblInformaDinheiroNa.setFont(new Font("Arial", Font.BOLD, 15));
		lblInformaDinheiroNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformaDinheiroNa.setBounds(10, 11, 151, 22);
		contentPane.add(lblInformaDinheiroNa);
		
		txtDataHoje = new JTextField();
		txtDataHoje.setForeground(new Color(255, 255, 224));
		txtDataHoje.setBackground(new Color(0, 128, 0));
		txtDataHoje.setEditable(false);
		txtDataHoje.setFont(new Font("Arial", Font.BOLD, 13));
		txtDataHoje.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataHoje.setText(dataForm);
		txtDataHoje.setBounds(273, 45, 151, 32);
		contentPane.add(txtDataHoje);
		txtDataHoje.setColumns(10);
		
		JLabel lblHoraDeAbertura = new JLabel("HORA\r\n");
		lblHoraDeAbertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraDeAbertura.setForeground(Color.RED);
		lblHoraDeAbertura.setFont(new Font("Arial", Font.BOLD, 15));
		lblHoraDeAbertura.setBounds(273, 11, 151, 22);
		contentPane.add(lblHoraDeAbertura);
		
		txtGiacenza.requestFocus();
		txtGiacenza.selectAll();
		
	}
}
