package com.foodtruck.modal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.foodtruck.modal.ConnectionFactory;


public class Setor {



	
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
	public void insertSetor(){
		String inserirSetor= "insert into setor (descricao)"
				+ " values('"+ getDescricao()+"');";
		
		try {
			robo = conn.prepareStatement(inserirSetor); 
			robo.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Setor cadastrado com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// Seleziona loja
	public int select(){
		 int id = 0; 
		 String seleziona ="select *  from setor order by id desc limit 1;";
		
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
		String deletar = "DELETE FROM setor WHERE id=" + getId() + ";";
		
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
	
		
		String editar = "UPDATE setor SET descricao='" + getDescricao() + "' WHERE id=" + getId() + ";";
		
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
	public void carregarSetor(JTable nameTable) {

		String[] nomeColunas = {"ID " ,"DESCRIÇÃO"};

		DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
		aModel.setColumnIdentifiers(nomeColunas);

		// consultar os dados
		String select = "SELECT * FROM setor;";

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
}

	

