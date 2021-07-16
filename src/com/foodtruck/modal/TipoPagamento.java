package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class TipoPagamento {
	
	private int id; 
	private String descricao;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	Connection conn = ConnectionFactory.conectar(); 
	PreparedStatement robo = null; 
	ResultSet rs = null; 
	
	
	// Medoto de insert no banco de dados
	public void insertPagamento(){
		String inserirSetor= "insert into tipo_pagamento (descricao)"
				+ " values('"+ getDescricao()+"');";
		
		try {
			robo = conn.prepareStatement(inserirSetor); 
			robo.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Tipo de Pagamento cadastrado com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// Seleziona loja
	public int select(){
		 int id = 0; 
		 String seleziona ="select *  from tipo_pagamento order by id desc limit 1;";
		
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
	public void excluir() {
		String deletar = "DELETE FROM tipo_pagamento WHERE id=" + getId() + ";";
		
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
	public void editar() {
	
		
		String editar = "UPDATE tipo_pagamento SET descricao='" + getDescricao() + "' WHERE id=" + getId() + ";";
		
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
	public void carregarPagamento(JTable nameTable) {

		String[] nomeColunas = {"ID " ,"DESCRIÇÃO"};

		DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
		aModel.setColumnIdentifiers(nomeColunas);

		// consultar os dados
		String select = "SELECT * FROM tipo_pagamento;";

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
	// ========================= CARREGA PAGAMENTO
	public void carrPagamento(JComboBox combobox) {

		String select = "SELECT * FROM tipo_pagamento   ;";
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
	
	//============================================================== =======================SETOR CODIGO COMBOX SELEZIONATA
	public int codigoPagamento(String nomePag) {
		int codigo = 0;

		String select = "SELECT id FROM tipo_pagamento WHERE descricao='" + nomePag+ "';";

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


	
}

	


