package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.foodtruck.modal.Usuario;



public class FrmLogin extends JFrame {
	private JPanel contentPane;
	private JButton btnCadastrar; 
	private JButton btnAcessar; 
	
	private JTextField txtNomeUsuario;
	private JPasswordField txtSenha;
	private JLabel lblExit;
	
	public FrmLogin() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 455);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		lblExit.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/foodtruck/image/exit24bianco.png")));
		lblExit.setBounds(353, 11, 37, 30);
		contentPane.add(lblExit);
		
		JLabel lblIconPass = new JLabel("");
		lblIconPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPass.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/foodtruck/image/key.png")));
		lblIconPass.setBounds(40, 202, 46, 39);
		contentPane.add(lblIconPass);
		
		JLabel lblIconUsuario = new JLabel("");
		lblIconUsuario.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/foodtruck/image/users.png")));
		lblIconUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUsuario.setBounds(40, 140, 46, 30);
		contentPane.add(lblIconUsuario);
		
		JLabel lblTitolo = new JLabel("Login");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitolo.setBounds(122, 97, 142, 32);
		contentPane.add(lblTitolo);
		
		//getRootPane().setDefaultButton(btnLogar);
		
		
		
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(Color.WHITE);
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setFont(new Font("Arial", Font.ITALIC, 15));
		txtSenha.setBackground(Color.GRAY);
		txtSenha.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtSenha.setBounds(106, 202, 241, 30);
		contentPane.add(txtSenha);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setFont(new Font("Arial", Font.ITALIC, 15));
		txtNomeUsuario.setForeground(Color.WHITE);
		txtNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeUsuario.setBackground(Color.GRAY);
		txtNomeUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		txtNomeUsuario.setBounds(106, 140, 241, 30);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		txtNomeUsuario.requestFocus();
		
		btnCadastrar = new JButton("");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCadastrase cd = new FrmCadastrase(); 
				cd.setVisible(true);
			}
		});
		btnCadastrar.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/foodtruck/image/btnCadastrar.png")));
		btnCadastrar.setBounds(205, 331, 142, 39);
		contentPane.add(btnCadastrar);
		
		
		
		btnAcessar = new JButton("");
		getRootPane().setDefaultButton(btnAcessar);
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario user = new Usuario(); 
				String us = txtNomeUsuario.getText(); 
				String pass = txtSenha.getText(); 
				

				
				if (pass.trim().equals("")) {
					txtSenha.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Preencha a Senha");
					txtSenha.setBackground(Color.GRAY);
					txtSenha.setText("");
					txtSenha.requestFocus();
				}else{
					boolean status = user.logar(us, pass); 
					if (status) {
						dispose();
						FrmPrincipal p = new FrmPrincipal(user.getId(), user.getNome(), null,null,null); 
						p.setVisible(true);
						
					}else{
						txtNomeUsuario.setBackground(Color.RED);
						txtNomeUsuario.setText("");
						
						txtSenha.setBackground(Color.RED);
						txtSenha.setText("");
						JOptionPane.showMessageDialog(null, "Nome e Senha errados....");
						txtNomeUsuario.requestFocus();
						
						txtNomeUsuario.setBackground(Color.GRAY);
						txtSenha.setBackground(Color.GRAY);
						
					}									
				}			
			}
		});
		btnAcessar.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/foodtruck/image/btnLogar.png")));
		btnAcessar.setBounds(40, 331, 136, 39);
		contentPane.add(btnAcessar);
		
		JLabel lblSfondoLogin = new JLabel("");
		lblSfondoLogin.setFont(new Font("Arial", Font.BOLD, 14));
		lblSfondoLogin.setForeground(Color.WHITE);
		lblSfondoLogin.setIcon(new ImageIcon(FrmLogin.class.getResource("/com/foodtruck/image/SfondoLogin.png")));
		lblSfondoLogin.setBounds(0, 0, 401, 455);
		contentPane.add(lblSfondoLogin);
		
		
	}
}
