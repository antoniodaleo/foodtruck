package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;


public class FrmInicializacao extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar; 
	private JLabel lblNumero;
	
	private void loading(){
		new Thread() {
			public void run(){
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(30);
						lblNumero.setText(Integer.toString(i) + "%");
						lblNumero.setVisible(false);
						progressBar.setValue(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}dispose();
				FrmLogin o = new FrmLogin(); 
				o.setVisible(true);
				
			}
		}.start();
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmInicializacao frame = new FrmInicializacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmInicializacao() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 235);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNumero = new JLabel("0 ");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumero.setForeground(SystemColor.inactiveCaptionBorder);
		lblNumero.setBounds(399, 112, 46, 30);
		contentPane.add(lblNumero);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(SystemColor.textHighlight);
		progressBar.setBounds(0, 172, 500, 14);
		contentPane.add(progressBar);
		
		JLabel label = new JLabel("");
		label.setForeground(SystemColor.inactiveCaptionBorder);
		label.setIcon(new ImageIcon(FrmInicializacao.class.getResource("/com/foodtruck/image/SplashBackground.png")));
		label.setBounds(0, 0, 500, 235);
		contentPane.add(label);
		loading();
	}
}