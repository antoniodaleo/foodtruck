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

import com.foodtruck.view.FrmLoja;
import com.foodtruck.modal.Loja;
import com.foodtruck.modal.Usuario;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmLoja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtEndereco;
	private boolean edicao = false; 
	private JButton btnCancela;
	private JButton btnSalva;
	private JButton btnNovo; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	
	
	
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	
	
	private JFormattedTextField txtCnpj;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtTelefone; 
	private JScrollPane scrollPane;
	private JTable aTable;
	private JLabel lblId;
	private JTextField txtId;
	
	
	// ------------------------------------- Limpa os campos
	private void limpar(){
		txtId.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtEstado.setText("");
		txtCnpj.setText("");
		txtCelular.setText("");
		txtTelefone.setText("");
		
		txtCnpj.requestFocus();
	};
	// ------------------------------------- Estado dos campos
	private void campos(int opcao){
		
		txtCnpj.setEnabled(false);
		txtNome.setEnabled(false);
		txtEndereco.setEnabled(false);
		txtNumero.setEnabled(false);
		txtBairro.setEnabled(false);
		txtEstado.setEnabled(false);
		txtTelefone.setEnabled(false);
		txtCelular.setEnabled(false);
		txtCidade.setEnabled(false);
		
		if (opcao == 1 ) {
			txtCnpj.setEnabled(true);
			txtNome.setEnabled(true);
			txtEndereco.setEnabled(true);
			txtNumero.setEnabled(true);
			txtBairro.setEnabled(true);
			txtEstado.setEnabled(true);
			txtTelefone.setEnabled(true);
			txtCelular.setEnabled(true);
			txtCidade.setEnabled(true);
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
					JOptionPane.showMessageDialog(null, "Preencha o Nome");
					txtNome.setBackground(Color.WHITE);
					txtNome.requestFocus();		

				}else {
					ok = true; 
				}
				return ok;	
				
			}
	
	Loja l = new Loja(); 
	
	public FrmLoja() {
		setBounds(100, 100, 699, 740);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "Deseja excluir essa Loja", "EXCLUIR LOJA", JOptionPane.YES_OPTION); 
				if (r== JOptionPane.YES_OPTION) {
					l.setId(Integer.parseInt(txtId.getText()));
					l.excluir();
					JOptionPane.showMessageDialog(null, "Loja eliminada com sucesso!");
					dispose();
					FrmLoja ls = new FrmLoja(); 
					ls.setVisible(true);
					limpar();
					botoes(0);
					campos(0);
				}
				
			}
			
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnExcluir.png")));
		btnExcluir.setBounds(529, 515, 136, 39);
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
		{
			JLabel lblEtichetta = new JLabel("");
			lblEtichetta.setHorizontalAlignment(SwingConstants.CENTER);
			lblEtichetta.setBounds(483, 40, 201, 23);
			pnlCabecalho.add(lblEtichetta);
			lblEtichetta.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/EtichettaCadastroLoja.png")));
		}
		{
			JPanel pnlDados = new JPanel();
			pnlDados.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados da Loja", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlDados.setBackground(Color.WHITE);
			pnlDados.setBounds(10, 90, 674, 414);
			contentPanel.add(pnlDados);
			pnlDados.setLayout(null);
			
			JLabel lblNome = new JLabel("Nome ");
			lblNome.setHorizontalAlignment(SwingConstants.CENTER);
			lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
			lblNome.setBounds(128, 97, 55, 25);
			pnlDados.add(lblNome);
			
			txtNome = new JTextField();
			txtNome.setEnabled(false);
			txtNome.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNome.setBounds(207, 100, 336, 22);
			pnlDados.add(txtNome);
			txtNome.setColumns(10);
			
			JLabel lblSenha = new JLabel("Endereco");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			lblSenha.setFont(new Font("Arial", Font.PLAIN, 13));
			lblSenha.setBounds(128, 133, 55, 25);
			pnlDados.add(lblSenha);
			
			txtEndereco = new JTextField();
			txtEndereco.setEnabled(false);
			txtEndereco.setFont(new Font("Arial", Font.PLAIN, 13));
			txtEndereco.setColumns(10);
			txtEndereco.setBounds(207, 136, 336, 22);
			pnlDados.add(txtEndereco);
			
			JLabel lblCelular = new JLabel("Numero");
			lblCelular.setHorizontalAlignment(SwingConstants.CENTER);
			lblCelular.setFont(new Font("Arial", Font.PLAIN, 13));
			lblCelular.setBounds(128, 169, 55, 25);
			pnlDados.add(lblCelular);
			
			
			
			JLabel lblCnpj = new JLabel("CNPJ");
			lblCnpj.setHorizontalAlignment(SwingConstants.CENTER);
			lblCnpj.setFont(new Font("Arial", Font.PLAIN, 13));
			lblCnpj.setBounds(128, 61, 55, 25);
			pnlDados.add(lblCnpj);
			
			
			MaskFormatter nCnpj = null; 
			try {
				nCnpj = new MaskFormatter("##.###.###/####-##"); 
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Somente numeros");
				e.printStackTrace();
			}
			
			txtCnpj = new JFormattedTextField(nCnpj);
			txtCnpj.setEnabled(false);
			txtCnpj.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCnpj.setColumns(10);
			txtCnpj.setBounds(207, 64, 336, 22);
			pnlDados.add(txtCnpj);
			
			txtNumero = new JTextField();
			txtNumero.setEnabled(false);
			txtNumero.setFont(new Font("Arial", Font.PLAIN, 13));
			txtNumero.setColumns(10);
			txtNumero.setBounds(207, 169, 336, 22);
			pnlDados.add(txtNumero);
			
			JLabel lblBairro = new JLabel("Bairro");
			lblBairro.setHorizontalAlignment(SwingConstants.CENTER);
			lblBairro.setFont(new Font("Arial", Font.PLAIN, 13));
			lblBairro.setBounds(128, 205, 55, 25);
			pnlDados.add(lblBairro);
			
			txtBairro = new JTextField();
			txtBairro.setEnabled(false);
			txtBairro.setFont(new Font("Arial", Font.PLAIN, 13));
			txtBairro.setColumns(10);
			txtBairro.setBounds(207, 205, 336, 22);
			pnlDados.add(txtBairro);
			
			JLabel lblCidade = new JLabel("Cidade");
			lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
			lblCidade.setFont(new Font("Arial", Font.PLAIN, 13));
			lblCidade.setBounds(128, 241, 55, 25);
			pnlDados.add(lblCidade);
			
			txtCidade = new JTextField();
			txtCidade.setEnabled(false);
			txtCidade.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCidade.setColumns(10);
			txtCidade.setBounds(207, 241, 336, 22);
			pnlDados.add(txtCidade);
			
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstado.setFont(new Font("Arial", Font.PLAIN, 13));
			lblEstado.setBounds(128, 277, 55, 25);
			pnlDados.add(lblEstado);
			
			txtEstado = new JTextField();
			txtEstado.setEnabled(false);
			txtEstado.setFont(new Font("Arial", Font.PLAIN, 13));
			txtEstado.setColumns(10);
			txtEstado.setBounds(207, 279, 336, 22);
			pnlDados.add(txtEstado);
			
			JLabel lblTelefone = new JLabel("Telefone");
			lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefone.setFont(new Font("Arial", Font.PLAIN, 13));
			lblTelefone.setBounds(128, 310, 55, 25);
			pnlDados.add(lblTelefone);
			
			MaskFormatter nTel = null; 
			try {
				nTel = new MaskFormatter("(##)####-####"); 
			} catch (Exception e) {
				
			}
			
			txtTelefone = new JFormattedTextField(nTel);
			txtTelefone.setEnabled(false);
			txtTelefone.setFont(new Font("Arial", Font.PLAIN, 13));
			txtTelefone.setColumns(10);
			txtTelefone.setBounds(207, 310, 336, 22);
			pnlDados.add(txtTelefone);
			
			JLabel lblCelular_1 = new JLabel("Celular");
			lblCelular_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCelular_1.setFont(new Font("Arial", Font.PLAIN, 13));
			lblCelular_1.setBounds(128, 346, 55, 25);
			pnlDados.add(lblCelular_1);
			
			MaskFormatter nCell = null; 
			try {
				nCell = new MaskFormatter("(##)9####-####");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Somente numeros");
				e.printStackTrace();
			}
			
			txtCelular = new JFormattedTextField(nCell); 
			txtCelular.setEnabled(false);
			txtCelular.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCelular.setColumns(10);
			txtCelular.setBounds(207, 346, 336, 22);
			pnlDados.add(txtCelular);
			
			btnNovo = new JButton("");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					limpar();
			 		botoes(1);
			 		campos(1);
			 		txtCnpj.requestFocus();
				}
			});
			btnNovo.setBounds(528, 11, 136, 39);
			pnlDados.add(btnNovo);
			btnNovo.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnNovo.png")));
			
			lblId = new JLabel("ID");
			lblId.setHorizontalAlignment(SwingConstants.CENTER);
			lblId.setFont(new Font("Arial", Font.PLAIN, 13));
			lblId.setBounds(128, 29, 55, 25);
			pnlDados.add(lblId);
			
			txtId = new JTextField();
			txtId.setFont(new Font("Arial", Font.PLAIN, 13));
			txtId.setEnabled(false);
			txtId.setColumns(10);
			txtId.setBounds(207, 31, 76, 22);
			pnlDados.add(txtId);
			
		}
		
		btnSalva = new JButton("");
		btnSalva.setEnabled(false);
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				l.setCnpj(txtCnpj.getText());
				l.setNome(txtNome.getText()); 
				l.setEndereco(txtEndereco.getText()); 
				l.setNumero(txtNumero.getText()); 
				l.setBairro(txtBairro.getText()); 
				l.setCidade(txtCidade.getText()); 
				l.setEstado(txtEstado.getText()); 
				l.setTelefone(txtTelefone.getText()); 
				l.setCelular(txtCelular.getText()); 
				
				
				if (validarCampos()== true) {
					
					if(edicao == false){
						
						l.insertLoja();
						limpar();
						botoes(0);
						campos(0);
						dispose();
						FrmLoja lo = new FrmLoja(); 
						lo.setVisible(true);
					}else{
						
						l.setId(Integer.parseInt(txtId.getText()));
						l.editar();
						JOptionPane.showMessageDialog(null, "Loja atualizada com sucesso!");
						dispose();
						FrmLoja ls = new FrmLoja(); 
						ls.setVisible(true);
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
		btnSalva.setBounds(20, 515, 136, 39);
		contentPanel.add(btnSalva);
		
		btnCancela = new JButton("");
		btnCancela.setEnabled(false);
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				limpar();
			}
		});
		btnCancela.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/btnCancelar.png")));
		btnCancela.setBounds(192, 515, 136, 39);
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
		btnEditar.setBounds(360, 515, 136, 39);
		contentPanel.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 574, 681, 148);
		contentPanel.add(scrollPane);
		
		aTable = new JTable();
		aTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int linha = aTable.getSelectedRow(); 
				txtId.setText(aTable.getValueAt(linha, 0).toString());
				txtCnpj.setText(aTable.getValueAt(linha, 1).toString());
				txtNome.setText(aTable.getValueAt(linha, 2).toString());
				txtEndereco.setText(aTable.getValueAt(linha, 3).toString());
				txtNumero.setText(aTable.getValueAt(linha, 4).toString());
				txtBairro.setText(aTable.getValueAt(linha, 5).toString());
				txtCidade.setText(aTable.getValueAt(linha, 6).toString());
				txtEstado.setText(aTable.getValueAt(linha, 7).toString());
				txtTelefone.setText(aTable.getValueAt(linha, 8).toString());
				txtCelular.setText(aTable.getValueAt(linha, 9).toString());
				
			
				botoes(2);
				campos(0);
		
			}
		});
		aTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha  = aTable.getSelectedRow(); 
				txtId.setText(aTable.getValueAt(linha, 0).toString());
				txtCnpj.setText(aTable.getValueAt(linha, 1).toString());
				txtNome.setText(aTable.getValueAt(linha, 2).toString());
				txtEndereco.setText(aTable.getValueAt(linha, 3).toString());
				txtNumero.setText(aTable.getValueAt(linha, 4).toString());
				txtBairro.setText(aTable.getValueAt(linha, 5).toString());
				txtCidade.setText(aTable.getValueAt(linha, 6).toString());
				txtEstado.setText(aTable.getValueAt(linha, 7).toString());
				txtTelefone.setText(aTable.getValueAt(linha, 8).toString());
				txtCelular.setText(aTable.getValueAt(linha, 9).toString());
				
				botoes(2);
				campos(0);
				
			}
		});
		l.carregarLoja(aTable);
		scrollPane.setViewportView(aTable);
		
		
	}
}
