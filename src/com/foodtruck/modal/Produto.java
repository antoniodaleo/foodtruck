package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.foodtruck.modal.ConnectionFactory;
import com.itextpdf.text.io.FileChannelRandomAccessSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

public class Produto {
	private int id; 
	private int setor_id; 
	private String codBarras; 
	private String descricao; 
	private int qtde; 
	private double precoVenda; 
	private double precoCompra; 
	private int qtdAdd; 
	
	
	public int getQtdAdd() {
		return qtdAdd;
	}
	public void setQtdAdd(int qtdAdd) {
		this.qtdAdd = qtdAdd;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}
	public int getSetor_id() {
		return setor_id;
	}
	public void setSetor_id(int setor_id) {
		this.setor_id = setor_id;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	

	Connection conn = null; 
	PreparedStatement robo = null; 
	ResultSet rs = null; 
	
	
	// Medoto de insert no banco de dados
		public void insertProduto(){
			String inserirSetor= "insert into produto (setor_id, codigoBarras, descricao,qtde, precoCompra, precoVenda)"
					+ " values("+getSetor_id()+",'"+getCodBarras()+"','"+ getDescricao()+"',"+getQtde()+",'"+getPrecoCompra()+"','"+getPrecoVenda()+"');";
			
			try {
				robo = conn.prepareStatement(inserirSetor); 
				robo.executeUpdate(); 
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		// Seleziona loja
		public int selectProduto(){
			 int id = 0; 
			 String seleziona ="select *  from produto order by id desc limit 1;";
			
			try {
				robo = conn.prepareStatement(seleziona); 
				rs = robo.executeQuery(); 
				
				if (rs.next()) {
				 setId(rs.getInt("id"));
				 setDescricao(rs.getString("descricao"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return id;
		}
		
		// -------------------------------------- ----------------------------------------EXCLUIR FORNECEDORES
		public void excluirP() {
			String deletar = "DELETE FROM produto WHERE id=" + getId() + ";";
			
			conn = ConnectionFactory.conectar(); 
			
			try {
				robo = conn.prepareStatement(deletar);
				robo.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// -------------------------------------- ----------------------------------------EDITAR FORNECEDORES
		public void editarP() {
		
			
			String editar = "UPDATE produto SET setor_id = "+getSetor_id()+", descricao='" + getDescricao() + "',codigoBarras='"+ getCodBarras()+"', qtde = "+ getQtde()+", precoCompra = "+ getPrecoCompra()+",precoVenda = "+ getPrecoVenda()+"  WHERE id=" + getId() + ";";
			
			conn = ConnectionFactory.conectar(); 
			
			try {
				robo = conn.prepareStatement(editar);
				robo.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// -------------------------------------- ----------------------------------------EDITAR add qtde
				public void addQtde() {
				
					
					String editar = "UPDATE produto SET setor_id = "+getSetor_id()+", descricao='" + getDescricao() + "',codigoBarras='"+ getCodBarras()+"', qtde = "+ getQtde()+" + "+getQtdAdd()+", precoCompra = "+ getPrecoCompra()+",precoVenda = "+ getPrecoVenda()+"  WHERE id=" + getId() + ";";
					
					conn = ConnectionFactory.conectar(); 
					
					try {
						robo = conn.prepareStatement(editar);
						robo.executeUpdate();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
		// --------------------------------------CARICARE I DATI DELLE VENDITE NELLA NOSTRA TABELLA
		public void carregarProduto(JTable nameTable) {

			String[] nomeColunas = {"ID " ,"SETOR" ,"CODIGO","DESCRIÇÃO","QTDE","PREÇO DE COMPRA","PREÇO DE VENDA" };

			DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
			aModel.setColumnIdentifiers(nomeColunas);

			// consultar os dados
			String select = "select p.id, s.descricao, p.codigoBarras, p.descricao,p.qtde,p.precoCompra, p.precoVenda from produto p inner join setor s on p.setor_id = s.id;";

			conn = ConnectionFactory.conectar();

			try {
				robo = conn.prepareStatement(select);
				rs = robo.executeQuery();

				// transformar os dados do resultset em metadados.
				ResultSetMetaData rsmt = rs.getMetaData();

				int numColunas = rsmt.getColumnCount();

				while (rs.next()) {

					Object[] objetos = new Object[numColunas];

					for (int i = 0; i < numColunas; i++) {

						objetos[i] = rs.getObject(i + 1);

					}

					aModel.addRow(objetos);
				}

				nameTable.setModel(aModel);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//============================================================== =======================SETOR CODIGO COMBOX SELEZIONATA
				public int codigoSetor(String nomeSet) {
					int codigo = 0;

					String select = "SELECT id FROM setor WHERE descricao='" + nomeSet+ "';";

					try {
						robo = conn.prepareStatement(select);
						rs = robo.executeQuery();

						if (rs.next()) {
							codigo = rs.getInt("id");
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return codigo;
				}
	
				
				//============================================================== =======================SETOR COMBO BOX
				public void carrSetor(JComboBox combobox) {

					String select = "SELECT * FROM setor ORDER BY descricao ASC;";
					conn = ConnectionFactory.conectar();
					
					
					try {
						robo = conn.prepareStatement(select);
						rs = robo.executeQuery();

						while (rs.next()) {

							combobox.addItem(rs.getString("descricao"));

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				// ==================================================================== Pesquisar codigoBarras
				public boolean pesqProduto (){
					boolean existe = false; 
					
					String select = "SELECT * FROM produto WHERE codigoBarras = '"+getCodBarras()+"';";
					conn = ConnectionFactory.conectar(); 
					
					try {
						robo = conn.prepareStatement(select); 
						rs = robo.executeQuery(); 
						
						
						if (rs.next()) {
							existe = true; 
							setId( rs.getInt("id"));
							setDescricao(rs.getString("descricao")) ; 
							setPrecoVenda(rs.getDouble("precoVenda")); 
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return existe; 
					
					
				}
				
				// ==================================================================== Pesquisar codigoBarras
				public boolean pesqProdutoNome (){
					boolean existe = false; 
					
					String select = "SELECT * FROM produto WHERE descricao = '"+getDescricao()+"';";
					conn = ConnectionFactory.conectar(); 
					
					try {
						robo = conn.prepareStatement(select); 
						rs = robo.executeQuery(); 
						
						
						if (rs.next()) {
							existe = true; 
							setId( rs.getInt("id"));
							setDescricao(rs.getString("descricao")) ; 
							setPrecoVenda(rs.getDouble("precoVenda")); 
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return existe; 
					
					
				}
				
				
				// --------------------------------------------------------PESQUISA DO PRODUTO POR NOME TABELA PESQU PRODUTO
				public void pesquisar(JTable nameTable, String produto) {

					String[] nomeColunas = {"ID " ,"SETOR" ,"CODIGO","DESCRIÇÃO","QTDE","PREÇO DE COMPRA","PREÇO DE VENDA" };

					DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
					aModel.setColumnIdentifiers(nomeColunas);
					
					
					// consultar os dados
					String select = "SELECT p.id ,s.descricao, p.codigoBarras,   p.descricao, p.precoCompra, p.precoVenda  FROM produto p "
							+ "INNER JOIN setor s ON p.setor_id = s.id WHERE p.descricao LIKE '%"
							+ produto + "%' ;";
					

					conn = ConnectionFactory.conectar();

					try {
						robo = conn.prepareStatement(select);
						rs = robo.executeQuery();

						// transformar os dados do resultset em metadados.
						ResultSetMetaData rsmt = rs.getMetaData();

						int numColunas = rsmt.getColumnCount();

						aModel.setNumRows(0);

						while (rs.next()) {

							Object[] objetos = new Object[numColunas];

							for (int i = 0; i < numColunas; i++) {

								objetos[i] = rs.getObject(i + 1);

							}

							aModel.addRow(objetos);
						}

						nameTable.setModel(aModel);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				// --------------------------------------CARICARE I DATI DELLE VENDITE NELLA NOSTRA TABELLA
				public void carregarItemVendidos(JTable nameTable, int id) {

					String[] nomeColunas = {"qtd " ,"descrição" ,"preço","subtotal"};

					DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
					aModel.setColumnIdentifiers(nomeColunas);
					
					// consultar os dados
					String select = "select i.qtdeV, p.descricao, p.precoVenda, i.sub "
							+ "from item_produtos i "
							+ "inner join produto p on i.produto_id = p.id "
							+ "where i.venda_id = "+id+";";

					conn = ConnectionFactory.conectar();

					try {
						robo = conn.prepareStatement(select);
						rs = robo.executeQuery();

						// transformar os dados do resultset em metadados.
						ResultSetMetaData rsmt = rs.getMetaData();

						int numColunas = rsmt.getColumnCount();

						while (rs.next()) {

							Object[] objetos = new Object[numColunas];

							for (int i = 0; i < numColunas; i++) {

								objetos[i] = rs.getObject(i + 1);

							}

							aModel.addRow(objetos);
						}

						nameTable.setModel(aModel);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				
				// --------------------------------------CARICARE I DATI DELLE VENDITE NELLA NOSTRA TABELLA
				public void carregarProdutoV(JTable nameTable) {

					String[] nomeColunas = {"ID " ,"CODIGO","DESCRIÇÃO" };

					DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
					aModel.setColumnIdentifiers(nomeColunas);

					// consultar os dados
					String select = "select p.id,p.codigoBarras, p.descricao from produto p inner join setor s on p.setor_id = s.id;";

					conn = ConnectionFactory.conectar();

					try {
						robo = conn.prepareStatement(select);
						rs = robo.executeQuery();

						// transformar os dados do resultset em metadados.
						ResultSetMetaData rsmt = rs.getMetaData();

						int numColunas = rsmt.getColumnCount();

						while (rs.next()) {

							Object[] objetos = new Object[numColunas];

							for (int i = 0; i < numColunas; i++) {

								objetos[i] = rs.getObject(i + 1);

							}

							aModel.addRow(objetos);
						}

						nameTable.setModel(aModel);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				// --------------------------------------------------------PESQUISA DO PRODUTO POR NOME TABELA PESQU PRODUTO
				public void pesquisarPV(JTable nameTable, String produto) {

					String[] nomeColunas = {"ID " ,"CODIGO","DESCRIÇÃO" };

					DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
					aModel.setColumnIdentifiers(nomeColunas);
					
					
					// consultar os dados
					String select = "SELECT p.id , p.codigoBarras,   p.descricao  FROM produto p "
							+ "INNER JOIN setor s ON p.setor_id = s.id WHERE p.descricao LIKE '%"
							+ produto + "%' ;";
					

					conn = ConnectionFactory.conectar();

					try {
						robo = conn.prepareStatement(select);
						rs = robo.executeQuery();

						// transformar os dados do resultset em metadados.
						ResultSetMetaData rsmt = rs.getMetaData();

						int numColunas = rsmt.getColumnCount();

						aModel.setNumRows(0);

						while (rs.next()) {

							Object[] objetos = new Object[numColunas];

							for (int i = 0; i < numColunas; i++) {

								objetos[i] = rs.getObject(i + 1);

							}

							aModel.addRow(objetos);
						}

						nameTable.setModel(aModel);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				public void imprimir (int id) throws JRException, Exception
				{
					
					
					String select = "SELECT data_food.produto.descricao, data_food.produto.qtde, data_food.setor.id AS CODSETOR FROM data_food.produto INNER JOIN data_food.setor ON  data_food.produto.setor_id = data_food.setor.id where produto.id = "+id+"   ORDER BY data_food.produto.descricao DESC";
					Connection conn = ConnectionFactory.conectar(); 
					robo = conn.prepareStatement(select);
					rs = robo.executeQuery(); 
					
					JRResultSetDataSource relatResult = new JRResultSetDataSource(rs);
					 Map parameters = new HashMap();
					
					JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\ANTONIO\\Desktop\\Sistema Ufficiale AÇAI\\FOOD\\src\\com\\foodtruck\\view\\Blank_A4.jasper", parameters, relatResult);
					JasperViewer jv = new JasperViewer(jp); 
					jv.setVisible(true);
						
					
				} 
				
				public void imprimirVenda (int id) throws JRException, Exception
				{
					
					
					String selectV = "select i.qtdeV, p.descricao ,i.sub, v.totaleVenda, i.cliente from item_produtos i inner join produto p on i.produto_id = p.id inner join venda v on i.venda_id = v.id where i.venda_id = "+id+";";
					Connection conn = ConnectionFactory.conectar(); 
					robo = conn.prepareStatement(selectV);
					rs = robo.executeQuery(); 
					
					JRResultSetDataSource relatResult = new JRResultSetDataSource(rs);
					 Map parameters = new HashMap();
					
					JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\ANTONIO\\Desktop\\Sistema Ufficiale AÇAI\\FOOD\\src\\com\\foodtruck\\view\\scontrino.jasper", parameters, relatResult);
					JasperViewer jv = new JasperViewer(jp); 
					jv.setVisible(false);
					JasperPrintManager.printPage(jp, 0, false);
						
					
				} 
}
