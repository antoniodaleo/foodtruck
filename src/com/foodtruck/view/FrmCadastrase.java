package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.foodtruck.modal.Usuario;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCadastrase extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField textSenha;
	
	private JButton btnCancela;
	private JButton btnSalva;
	
	private void limpar(){
		txtNome.setText("");
		textSenha.setText("");
		txtNome.requestFocus();
	};
	
	public FrmCadastrase() {
		setUndecorated(true);
		setBounds(100, 100, 684, 381);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.WHITE);
		pnlCabecalho.setBounds(0, 0, 684, 103);
		contentPanel.add(pnlCabecalho);
		pnlCabecalho.setLayout(null);
		
		JLabel lblSeparadorAzul = new JLabel("");
		lblSeparadorAzul.setBounds(0, 82, 684, 10);
		pnlCabecalho.add(lblSeparadorAzul);
		lblSeparadorAzul.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/SeparatoreCeleste.png")));
		{
			JLabel lblEtichetta = new JLabel("");
			lblEtichetta.setHorizontalAlignment(SwingConstants.CENTER);
			lblEtichetta.setBounds(483, 58, 201, 23);
			pnlCabecalho.add(lblEtichetta);
			lblEtichetta.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/EtichettaUsuario.png")));
		}
		
		JLabel lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose(); 
			}
		});
		lblExit.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/enter (24).png")));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(643, 11, 31, 29);
		pnlCabecalho.add(lblExit);
		{
			JPanel pnlDados = new JPanel();
			pnlDados.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlDados.setBackground(Color.WHITE);
			pnlDados.setBounds(109, 137, 474, 160);
			contentPanel.add(pnlDados);
			pnlDados.setLayout(null);
			
			JLabel lblNome = new JLabel("Nome ");
			lblNome.setHorizontalAlignment(SwingConstants.CENTER);
			lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
			lblNome.setBounds(24, 28, 55, 25);
			pnlDados.add(lblNome);
			
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNome.setBounds(103, 31, 336, 22);
			pnlDados.add(txtNome);
			txtNome.setColumns(10);
			
			JLabel lblSenha = new JLabel("Senha");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			lblSenha.setFont(new Font("Arial", Font.PLAIN, 13));
			lblSenha.setBounds(24, 64, 55, 25);
			pnlDados.add(lblSenha);
			
			textSenha = new JTextField();
			textSenha.setFont(new Font("Arial", Font.PLAIN, 13));
			textSenha.setColumns(10);
			textSenha.setBounds(103, 67, 336, 22);
			pnlDados.add(textSenha);
			
			MaskFormatter nCell = null; 
			try {
				nCell = new MaskFormatter("(##)9####-####");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Somente numeros");
				e.printStackTrace();
			}
		}
		
		btnSalva = new JButton("");
		getRootPane().setDefaultButton(btnSalva);
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario u = new Usuario(); 
				
			    
			    if (textSenha.getText().trim().equals("")) {
					txtNome.setBackground(Color.RED);
					textSenha.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Preencha Nome e Senha.");
					limpar();
					txtNome.setBackground(Color.WHITE);
					textSenha.setBackground(Color.WHITE);
					
			    
			    }else{
			    	u.setNome(txtNome.getText());
					u.setSenha(textSenha.getText()); 
				    u.salvarUsuario();
				    limpar();
				    dispose();
			    }
			    
				
			}
		});
		btnSalva.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/btnSalvar.png")));
		btnSalva.setBounds(211, 308, 136, 39);
		contentPanel.add(btnSalva);
		
		btnCancela = new JButton("");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				limpar();
			}
		});
		btnCancela.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/btnCancelar.png")));
		btnCancela.setBounds(357, 308, 136, 39);
		contentPanel.add(btnCancela);
	}
}
