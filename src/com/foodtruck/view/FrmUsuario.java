package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.foodtruck.modal.Setor;
import com.foodtruck.modal.Usuario;

public class FrmUsuario extends JFrame {
	
	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtSenha1;
	private boolean edicao = false; 
	private JButton btnCancela;
	private JButton btnSalva;
	private JButton btnNovo; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	
	private JScrollPane scrollPane;
	private JTable aTable;
	private JLabel lblId;
	private JTextField txtId;
	
	
	// ------------------------------------- Limpa os campos
	private void limpar(){
		txtId.setText("");
		txtNome.setText("");
		txtSenha1.setText("");
		
		txtNome.requestFocus();
	};
	// ------------------------------------- Estado dos campos
	private void campos(int opcao){
		
		txtNome.setEnabled(false);
		txtSenha1.setEnabled(false);
		
		if (opcao == 1 ) {
			
			txtNome.setEnabled(true);
			txtSenha1.setEnabled(true);
			
			}
	}
	
	// ------------------------------------- Estado dos botoes
	private void botoes(int estado){
		
		btnNovo.setEnabled(true);
		btnSalva.setEnabled(false);
		btnCancela.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		
		if (estado == 1) {
			btnNovo.setEnabled(false);
			btnSalva.setEnabled(true);
			btnCancela.setEnabled(false);
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);
			
		}else if (estado == 2) {
			btnSalva.setEnabled(true);
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
			
		}
		
	}	
	
	//*****************************VALIDAR CAMPOS******************************/
			public boolean validarCampos(){
				boolean ok = false; 
				if (txtNome.getText().trim().equals("")) {
					txtNome.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Preencha o Campo");
					txtNome.setBackground(Color.WHITE);
					txtNome.requestFocus();		

				}else {
					ok = true; 
				}
				return ok;	
				
			}
	
	Usuario u = new Usuario(); 
	
	
	public FrmUsuario() {
		setBounds(100, 100, 717, 584);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "Deseja excluir esse Usuario", "EXCLUIR USUARIO", JOptionPane.YES_OPTION); 
				if (r== JOptionPane.YES_OPTION) {
					u.setId(Integer.parseInt(txtId.getText()));
					u.excluirU();
					JOptionPane.showMessageDialog(null, "Usuario eliminado com sucesso!");
					dispose();
					FrmUsuario u = new FrmUsuario(); 
					u.setVisible(true);
					limpar();
					botoes(0);
					campos(0);
				}
				
			}
			
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnExcluir.png")));
		btnExcluir.setBounds(541, 316, 136, 39);
		contentPanel.add(btnExcluir);
		
		JPanel pnlCabecalho = new JPanel();
		pnlCabecalho.setBackground(Color.WHITE);
		pnlCabecalho.setBounds(0, 0, 684, 79);
		contentPanel.add(pnlCabecalho);
		pnlCabecalho.setLayout(null);
		
		JLabel lblSeparadorAzul = new JLabel("");
		lblSeparadorAzul.setBounds(0, 64, 684, 10);
		pnlCabecalho.add(lblSeparadorAzul);
		lblSeparadorAzul.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/SeparatoreCeleste.png")));
		
		JLabel lblCadastroDoSetor = new JLabel("CADASTRO DO USUARIO");
		lblCadastroDoSetor.setFont(new Font("Arial", Font.BOLD, 15));
		lblCadastroDoSetor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDoSetor.setBounds(232, 26, 236, 27);
		pnlCabecalho.add(lblCadastroDoSetor);
		{
			JPanel pnlDados = new JPanel();
			pnlDados.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro do Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlDados.setBackground(Color.WHITE);
			pnlDados.setBounds(10, 90, 674, 215);
			contentPanel.add(pnlDados);
			pnlDados.setLayout(null);
			
			JLabel lblNome = new JLabel("Descri\u00E7\u00E3o");
			lblNome.setHorizontalAlignment(SwingConstants.CENTER);
			lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
			lblNome.setBounds(128, 128, 84, 25);
			pnlDados.add(lblNome);
			
			txtNome = new JTextField();
			txtNome.setEnabled(false);
			txtNome.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNome.setBounds(234, 130, 336, 22);
			pnlDados.add(txtNome);
			txtNome.setColumns(10);
			

			
			btnNovo = new JButton("");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpar();
			 		botoes(1);
			 		campos(1);
			 		txtNome.requestFocus();
				}
			});
		
			btnNovo.setBounds(528, 11, 136, 39);
			pnlDados.add(btnNovo);
			btnNovo.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnNovo.png")));
			
			lblId = new JLabel("ID");
			lblId.setHorizontalAlignment(SwingConstants.CENTER);
			lblId.setFont(new Font("Arial", Font.PLAIN, 13));
			lblId.setBounds(128, 92, 55, 25);
			pnlDados.add(lblId);
			
			txtId = new JTextField();
			txtId.setFont(new Font("Arial", Font.PLAIN, 13));
			txtId.setEnabled(false);
			txtId.setColumns(10);
			txtId.setBounds(232, 94, 76, 22);
			pnlDados.add(txtId);
			
			JLabel lblSenha = new JLabel("Senha");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			lblSenha.setFont(new Font("Arial", Font.PLAIN, 13));
			lblSenha.setBounds(128, 164, 84, 25);
			pnlDados.add(lblSenha);
			
			txtSenha1 = new JTextField();
			txtSenha1.setFont(new Font("Arial", Font.PLAIN, 13));
			txtSenha1.setEnabled(false);
			txtSenha1.setColumns(10);
			txtSenha1.setBounds(234, 166, 336, 22);
			pnlDados.add(txtSenha1);
			
		}
		
		btnSalva = new JButton("");
		btnSalva.setEnabled(false);
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				u.setNome(txtNome.getText()); 
				u.setSenha(txtSenha1.getText());
				
				
				if (validarCampos()== true) {
					
					if(edicao == false){
						
						u.salvarUsuario();
						limpar();
						botoes(0);
						campos(0);
						dispose(); 
						FrmUsuario us = new FrmUsuario(); 
						us.setVisible(true);
						
					}else{
						
						u.setId(Integer.parseInt(txtId.getText()));
						u.editarU();
						JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
						dispose();
						FrmUsuario u = new FrmUsuario(); 
						u.setVisible(true);
						edicao = false;
						limpar();
						botoes(0);
						campos(0);
						
					}
					
				}
				
			}		
		});
		getRootPane().setDefaultButton(btnSalva);
		btnSalva.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/btnSalvar.png")));
		btnSalva.setBounds(32, 316, 136, 39);
		contentPanel.add(btnSalva);
		
		btnCancela = new JButton("");
		btnCancela.setEnabled(false);
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				limpar();
			}
		});
		btnCancela.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/btnCancelar.png")));
		btnCancela.setBounds(204, 316, 136, 39);
		contentPanel.add(btnCancela);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botoes(1);
				campos(1);
				edicao = true; 
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnEditar.png")));
		btnEditar.setBounds(372, 316, 136, 39);
		contentPanel.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 366, 681, 148);
		contentPanel.add(scrollPane);
		
		aTable = new JTable();
		aTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int linha = aTable.getSelectedRow(); 
				txtId.setText(aTable.getValueAt(linha, 0).toString());
				txtNome.setText(aTable.getValueAt(linha, 1).toString());
				txtSenha1.setText(aTable.getValueAt(linha, 2).toString());
				botoes(2);
				campos(0);
		
			}
		});
		aTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha  = aTable.getSelectedRow(); 
				txtId.setText(aTable.getValueAt(linha, 0).toString());
				txtNome.setText(aTable.getValueAt(linha, 1).toString());
				txtSenha1.setText(aTable.getValueAt(linha, 2).toString());
				botoes(2);
				campos(0);
				
			}
		});
		u.carregarUsuario(aTable);
		scrollPane.setViewportView(aTable);
		
		
	}
}
