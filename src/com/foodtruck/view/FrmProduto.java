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

import net.sf.jasperreports.engine.JRException;

import com.foodtruck.modal.Loja;
import com.foodtruck.modal.Produto;
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
import javax.swing.JComboBox;

public class FrmProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescricao;
	private boolean edicao = false; 
	private JButton btnCancela;
	private JButton btnSalva;
	private JButton btnNovo; 
	private JButton btnEditar; 
	private JButton btnExcluir; 
	
	
	private int codigoInserido; 
	
	private JComboBox cbSetor;
	private JTextField txtCod;
	private JTextField txtPrecoVenda;
	private JScrollPane scrollPane;
	private JTable aTable;
	private JLabel lblId;
	private JTextField txtId;
	
	
	// ------------------------------------- Limpa os campos
	private void limpar(){
		txtId.setText("");
		cbSetor.setSelectedIndex(-1);
		txtDescricao.setText("");
		txtCod.setText("");
		txtQtde.setText("");
		txtPrecoVenda.setText("");
		txtPrecoCompra.setText("");
		txtAddQtde.setText("0");
		txtCod.requestFocus();
	};
	// ------------------------------------- Estado dos campos
	private void campos(int opcao){
		
		cbSetor.setEnabled(false);
		txtDescricao.setEnabled(false);
		txtCod.setEnabled(false);
		txtPrecoVenda.setEnabled(false);
		txtQtde.setEnabled(false);
		txtPrecoCompra.setEnabled(false);
		txtAddQtde.setEnabled(false);
		
		if (opcao == 1 ) {
			cbSetor.setEnabled(true);
			txtDescricao.setEnabled(true);
			txtCod.setEnabled(true);
			txtQtde.setEnabled(true);
			txtAddQtde.setEnabled(true);	
			txtPrecoVenda.setEnabled(true);
			txtPrecoCompra.setEnabled(true);
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
				if (txtDescricao.getText().trim().equals("")) {
					txtDescricao.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Preencha a descrição do produto");
					txtDescricao.setBackground(Color.WHITE);
					txtCod.requestFocus();		

				}else {
					ok = true; 
				}
				return ok;	
				
			}
	
	Produto p = new Produto(); 
	private JTextField txtPrecoCompra;
	private JTextField txtQtde;
	private JTextField txtAddQtde;
	private JTextField txtPesquisa;
	private int codigoProduto = 2; 
	
	
	public FrmProduto() {
		setBounds(100, 100, 701, 780);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "Deseja excluir esse produto?", "EXCLUIR PRODUTO", JOptionPane.YES_OPTION); 
				if (r== JOptionPane.YES_OPTION) {
					p.setId(Integer.parseInt(txtId.getText()));
					p.excluirP();
					JOptionPane.showMessageDialog(null, "Produto eliminado com sucesso!");
					dispose();
					FrmProduto ls = new FrmProduto(); 
					ls.setVisible(true);
					limpar();
					botoes(0);
					campos(0);
				}
				
			}
			
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnExcluir.png")));
		btnExcluir.setBounds(529, 418, 136, 39);
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
		
		JLabel lblCadastroProduto = new JLabel("CADASTRO PRODUTO");
		lblCadastroProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProduto.setFont(new Font("Arial", Font.BOLD, 15));
		lblCadastroProduto.setBounds(184, 24, 364, 29);
		pnlCabecalho.add(lblCadastroProduto);
		{
			JPanel pnlDados = new JPanel();
			pnlDados.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlDados.setBackground(Color.WHITE);
			pnlDados.setBounds(10, 90, 674, 317);
			contentPanel.add(pnlDados);
			pnlDados.setLayout(null);
			
			JLabel lblNome = new JLabel("Descri\u00E7\u00E3o");
			lblNome.setHorizontalAlignment(SwingConstants.LEFT);
			lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
			lblNome.setBounds(88, 187, 85, 25);
			pnlDados.add(lblNome);
			
			txtDescricao = new JTextField();
			txtDescricao.setEnabled(false);
			txtDescricao.setFont(new Font("Arial", Font.PLAIN, 13));
			txtDescricao.setBounds(197, 190, 375, 22);
			pnlDados.add(txtDescricao);
			txtDescricao.setColumns(10);
			
			JLabel lblSenha = new JLabel("Setor");
			lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
			lblSenha.setFont(new Font("Arial", Font.PLAIN, 13));
			lblSenha.setBounds(88, 115, 85, 25);
			pnlDados.add(lblSenha);
			
			JLabel lblCelular = new JLabel("Codigo Barra");
			lblCelular.setHorizontalAlignment(SwingConstants.LEFT);
			lblCelular.setFont(new Font("Arial", Font.PLAIN, 13));
			lblCelular.setBounds(88, 151, 85, 25);
			pnlDados.add(lblCelular);
			
			
			txtCod = new JTextField();
			txtCod.setEnabled(false);
			txtCod.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCod.setColumns(10);
			txtCod.setBounds(197, 151, 375, 22);
			pnlDados.add(txtCod);
			
			JLabel lblCidade = new JLabel("Pre\u00E7o de Venda");
			lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
			lblCidade.setFont(new Font("Arial", Font.PLAIN, 13));
			lblCidade.setBounds(88, 259, 115, 25);
			pnlDados.add(lblCidade);
			
			txtPrecoVenda = new JTextField();
			txtPrecoVenda.setEnabled(false);
			txtPrecoVenda.setFont(new Font("Arial", Font.PLAIN, 13));
			txtPrecoVenda.setColumns(10);
			txtPrecoVenda.setBounds(197, 261, 107, 22);
			pnlDados.add(txtPrecoVenda);
			
			
			btnNovo = new JButton("");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					limpar();
			 		botoes(1);
			 		campos(1);
			 		cbSetor.requestFocus();
				}
			});
			btnNovo.setBounds(528, 11, 136, 39);
			pnlDados.add(btnNovo);
			btnNovo.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnNovo.png")));
			
			lblId = new JLabel("ID");
			lblId.setHorizontalAlignment(SwingConstants.LEFT);
			lblId.setFont(new Font("Arial", Font.PLAIN, 13));
			lblId.setBounds(88, 66, 83, 25);
			pnlDados.add(lblId);
			
			txtId = new JTextField();
			txtId.setFont(new Font("Arial", Font.PLAIN, 13));
			txtId.setEnabled(false);
			txtId.setColumns(10);
			txtId.setBounds(195, 68, 76, 22);
			pnlDados.add(txtId);
			
			cbSetor = new JComboBox();
			p.carrSetor(cbSetor);
			cbSetor.setEnabled(false);
			cbSetor.setBounds(197, 118, 375, 20);
			pnlDados.add(cbSetor);
			
			JLabel lblPreoDeCompra = new JLabel("Pre\u00E7o de Compra");
			lblPreoDeCompra.setHorizontalAlignment(SwingConstants.LEFT);
			lblPreoDeCompra.setFont(new Font("Arial", Font.PLAIN, 13));
			lblPreoDeCompra.setBounds(356, 259, 115, 25);
			pnlDados.add(lblPreoDeCompra);
			
			txtPrecoCompra = new JTextField();
			txtPrecoCompra.setFont(new Font("Arial", Font.PLAIN, 13));
			txtPrecoCompra.setEnabled(false);
			txtPrecoCompra.setColumns(10);
			txtPrecoCompra.setBounds(465, 261, 107, 22);
			pnlDados.add(txtPrecoCompra);
			
			JLabel lblEstoqueDeProdutos = new JLabel("Estoque de Produtos");
			lblEstoqueDeProdutos.setHorizontalAlignment(SwingConstants.LEFT);
			lblEstoqueDeProdutos.setFont(new Font("Arial", Font.PLAIN, 13));
			lblEstoqueDeProdutos.setBounds(88, 223, 161, 25);
			pnlDados.add(lblEstoqueDeProdutos);
			
			txtQtde = new JTextField();
			txtQtde.setEnabled(false);
			txtQtde.setHorizontalAlignment(SwingConstants.CENTER);
			txtQtde.setFont(new Font("Arial", Font.PLAIN, 13));
			txtQtde.setColumns(10);
			txtQtde.setBounds(219, 223, 85, 22);
			pnlDados.add(txtQtde);
			
			
			
			txtAddQtde = new JTextField();
			txtAddQtde.setText("0");
			txtAddQtde.setHorizontalAlignment(SwingConstants.CENTER);
			txtAddQtde.setFont(new Font("Arial", Font.PLAIN, 13));
			txtAddQtde.setColumns(10);
			txtAddQtde.setBounds(465, 223, 107, 22);
			pnlDados.add(txtAddQtde);
			
			JLabel lblAddQtd = new JLabel("Add Qtd");
			lblAddQtd.setHorizontalAlignment(SwingConstants.LEFT);
			lblAddQtd.setFont(new Font("Arial", Font.PLAIN, 13));
			lblAddQtd.setBounds(370, 223, 85, 25);
			pnlDados.add(lblAddQtd);
			
			JButton btnImprimir = new JButton("Imprimir");
			btnImprimir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						p.imprimir(codigoProduto);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnImprimir.setBounds(12, 24, 97, 23);
			pnlDados.add(btnImprimir);
			
		}
		
		btnSalva = new JButton("");
		btnSalva.setEnabled(false);
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				p.setDescricao(txtDescricao.getText()); 
				p.setSetor_id(p.codigoSetor(cbSetor.getSelectedItem().toString()));
				p.setCodBarras(txtCod.getText()); 
				p.setQtde(Integer.parseInt(txtQtde.getText()));
				p.setPrecoCompra(Double.parseDouble(txtPrecoCompra.getText()));
				p.setPrecoVenda(Double.parseDouble(txtPrecoVenda.getText())); 
				p.setQtdAdd(Integer.parseInt(txtAddQtde.getText()));
				
				
				
				if (validarCampos()== true) {
					
					if(edicao == false){
						
						p.insertProduto();
						FrmProduto ls = new FrmProduto(); 
						ls.setVisible(true);
						
						limpar();
						botoes(0);
						campos(0);
						dispose();
						
					}else{
						
						p.setId(Integer.parseInt(txtId.getText()));
						p.editarP();
						p.addQtde();
						JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
						dispose();
						FrmProduto ls = new FrmProduto(); 
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
		btnSalva.setBounds(20, 418, 136, 39);
		contentPanel.add(btnSalva);
		
		btnCancela = new JButton("");
		btnCancela.setEnabled(false);
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				limpar();
			}
		});
		btnCancela.setIcon(new ImageIcon(FrmCadastrase.class.getResource("/com/foodtruck/image/btnCancelar.png")));
		btnCancela.setBounds(192, 418, 136, 39);
		contentPanel.add(btnCancela);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botoes(1);
				campos(1);
				edicao = true;
				txtAddQtde.requestFocus();
				txtAddQtde.setEditable(true);
				txtQtde.setEditable(false);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(FrmLoja.class.getResource("/com/foodtruck/image/btnEditar.png")));
		btnEditar.setBounds(360, 418, 136, 39);
		contentPanel.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 516, 681, 206);
		contentPanel.add(scrollPane);
		
		aTable = new JTable();
		aTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int linha = aTable.getSelectedRow(); 
				txtId.setText(aTable.getValueAt(linha, 0).toString());
				txtCod.setText(aTable.getValueAt(linha, 2).toString());
				String setor=	aTable.getValueAt(linha, 1).toString();
				cbSetor.setSelectedItem(setor);
				txtDescricao.setText(aTable.getValueAt(linha, 3).toString());
				txtQtde.setText(aTable.getValueAt(linha, 4).toString());
				txtPrecoCompra.setText(aTable.getValueAt(linha, 5).toString());
				txtPrecoVenda.setText(aTable.getValueAt(linha, 6).toString());
				botoes(2);
				campos(0);
		
			}
		});
		aTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = aTable.getSelectedRow(); 
				txtId.setText(aTable.getValueAt(linha, 0).toString());
				txtCod.setText(aTable.getValueAt(linha, 2).toString());
				String setor=	aTable.getValueAt(linha, 1).toString();
				cbSetor.setSelectedItem(setor);
				txtDescricao.setText(aTable.getValueAt(linha, 3).toString());
				txtQtde.setText(aTable.getValueAt(linha, 4).toString());
				txtPrecoCompra.setText(aTable.getValueAt(linha, 5).toString());
				txtPrecoVenda.setText(aTable.getValueAt(linha, 6).toString());
				botoes(2);
				campos(0);
					
			}
		});
		p.carregarProduto(aTable);
		scrollPane.setViewportView(aTable);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Produto p = new Produto(); 
				p.pesquisar(aTable, txtPesquisa.getText());
			}
		});
		txtPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPesquisa.setBounds(167, 477, 517, 26);
		contentPanel.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		
		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesquisa.setBounds(39, 479, 117, 22);
		contentPanel.add(lblPesquisa);
		
		
		
	}
}
