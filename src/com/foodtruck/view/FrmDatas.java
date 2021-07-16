package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.foodtruck.modal.datateste;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.components.JSpinField;
import java.util.Locale;

public class FrmDatas extends JFrame {

	private JPanel contentPane;
	private JDateChooser dtDataFine;
	private JButton btnNewButton;
	
	public FrmDatas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dtDataFine = new JDateChooser();
		dtDataFine.setLocale(new Locale("pt"));
		dtDataFine.setBounds(10, 115, 159, 20);
		contentPane.add(dtDataFine);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datateste d = new datateste(); 
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
				String data = sdf.format(dtDataFine.getDate()); 
				d.setData_fine(data);
				d.fechaturaCaixa();
				System.out.println(data);
			}
		});
		btnNewButton.setBounds(236, 115, 89, 23);
		contentPane.add(btnNewButton);
		
		JSpinField spinField = new JSpinField();
		spinField.setBounds(54, 53, 29, 20);
		contentPane.add(spinField);
	
	
	}
}
