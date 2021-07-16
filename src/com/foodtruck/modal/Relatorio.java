package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class Relatorio {
	
	
	Connection conn = null; 
	PreparedStatement robo = null; 
	ResultSet rs = null; 
	
	// ------------------------------------------------------------------  POPULARE FECHAR CASSA vista
		public void fecharCassa(JTable nameTable, String  nome ) {

			String[] nomeColunas = { "ID","Vendedor","Totale Venda","Pagamento", "Data Vendita"};

			DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
			aModel.setColumnIdentifiers(nomeColunas);

			 String select = " SELECT V.ID , U.NOME AS VENDEDOR, V.TOTALEVENDA, T.DESCRICAO, V.DATA_VENDA "
			 		+ "FROM VENDA V "
			 		+ "INNER JOIN USUARIO U ON V.USUARIO_ID = U.ID "
			 		+ "INNER JOIN TIPO_PAGAMENTO T ON V.TIPO_PAGAMENTO_ID = T.ID "
			 		+ "WHERE V.DATA_VENDA BETWEEN DATE(NOW()) AND DATE_ADD(DATE(NOW()), INTERVAL 1 DAY) "
			 		+ "AND T.DESCRICAO = 'A VISTA' AND U.NOME = '"+nome+"' ORDER BY V.ID DESC ;";

			conn = ConnectionFactory.conectar();

			try {
				robo = (PreparedStatement) conn.prepareStatement(select);
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
		
		
		// ------------------------------------------------------------------  POPULARE FECHAR CASSA cartao
			public void fecharCassaCartao(JTable nameTable , String nome) {

				String[] nomeColunas = { "ID","Vendedor","Totale Venda","Pagamento", "Data Vendita"};

				DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
				aModel.setColumnIdentifiers(nomeColunas);

				 String select = " SELECT V.ID , U.NOME AS VENDEDOR, V.TOTALEVENDA, T.DESCRICAO, V.DATA_VENDA "
				 		+ "FROM VENDA V "
				 		+ "INNER JOIN USUARIO U ON V.USUARIO_ID = U.ID "
				 		+ "INNER JOIN TIPO_PAGAMENTO T ON V.TIPO_PAGAMENTO_ID = T.ID "
				 		+ "WHERE V.DATA_VENDA BETWEEN DATE(NOW()) AND DATE_ADD(DATE(NOW()), INTERVAL 1 DAY) "
				 		+ "AND T.DESCRICAO = 'CARTAO'  AND U.NOME = '"+nome+"' ORDER BY V.ID DESC ;";

				conn = ConnectionFactory.conectar();

				try {
					robo = (PreparedStatement) conn.prepareStatement(select);
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
	
	
	
			// ------------------------------------------------------------------  POPULARE FECHAR TOTALCAIXAvista
			public void totalCaixa(JTable nameTable, String nome) {

				String[] nomeColunas = {"Total dinheiro"};

				DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
				aModel.setColumnIdentifiers(nomeColunas);

				 String select = "SELECT  SUM(V.TOTALEVENDA) AS TOTAL, U.NOME "
				 		+ "FROM VENDA V "
				 		+ "INNER JOIN USUARIO U ON V.USUARIO_ID = U.ID "
				 		+ "INNER JOIN TIPO_PAGAMENTO T ON V.TIPO_PAGAMENTO_ID = T.ID "
				 		+ "WHERE V.DATA_VENDA BETWEEN DATE(NOW()) AND DATE_ADD(DATE(NOW()), INTERVAL 1 DAY) "
				 		+ "AND T.DESCRICAO = 'A VISTA'  AND U.NOME = '"+nome+"' ORDER BY V.ID DESC ;";


				conn = ConnectionFactory.conectar();

				try {
					robo = (PreparedStatement) conn.prepareStatement(select);
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
			
			// ------------------------------------------------------------------  POPULARE FECHAR TOTALCAIXAvista
						public void totalCaixaCarta(JTable nameTable, String nome) {

							String[] nomeColunas = {"Total Cartas"};

							DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
							aModel.setColumnIdentifiers(nomeColunas);

							 String select = "SELECT  SUM(V.TOTALEVENDA) AS TOTAL, U.NOME "
							 		+ "FROM VENDA V "
							 		+ "INNER JOIN USUARIO U ON V.USUARIO_ID = U.ID "
							 		+ "INNER JOIN TIPO_PAGAMENTO T ON V.TIPO_PAGAMENTO_ID = T.ID "
							 		+ "WHERE V.DATA_VENDA BETWEEN DATE(NOW()) AND DATE_ADD(DATE(NOW()), INTERVAL 1 DAY) "
							 		+ "AND T.DESCRICAO = 'cartao'  AND U.NOME = '"+nome+"' ORDER BY V.ID DESC ;";


							conn = ConnectionFactory.conectar();

							try {
								robo = (PreparedStatement) conn.prepareStatement(select);
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
						
						// ------------------------------------------------------------------  POPULARE FECHAR TOTALCAIXAvista
						public void totalGenerale(JTable nameTable, String nome) {

							String[] nomeColunas = {"Total Vendas"};

							DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
							aModel.setColumnIdentifiers(nomeColunas);

							 String select = "SELECT  SUM(V.TOTALEVENDA) AS TOTAL, U.NOME "
							 		+ "FROM VENDA V  "
							 		+ "INNER JOIN USUARIO U ON V.USUARIO_ID = U.ID "
							 		+ "WHERE DATA_VENDA BETWEEN DATE(NOW()) AND DATE_ADD(DATE(NOW()), INTERVAL 1 DAY)"
							 		+ "AND U.NOME = '"+nome+"';";


							conn = ConnectionFactory.conectar();

							try {
								robo = (PreparedStatement) conn.prepareStatement(select);
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
	
	
	
	
}
