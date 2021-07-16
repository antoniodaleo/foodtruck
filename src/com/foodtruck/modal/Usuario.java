package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Usuario {
	private int id; 
	private String nome, senha; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	Connection conn = null; 
	ResultSet rs = null; 
	PreparedStatement robo = null; 
	
	/*
	 * INIZIO CREAZIONE DI METODI
	 * */
	
	
	/*
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> METODO LOGAR
	 * */
	public boolean logar (String nome, String senha){
		boolean existe = false; 
		
		conn = ConnectionFactory.conectar(); 
		String verifica = "Select * From usuario Where nome = '"+nome+"' And senha = '"+senha+"';";
		
		try {
			robo= conn.prepareStatement(verifica); 
			rs = robo.executeQuery(); 
			
			if (rs.next()) {
				existe = true; 
				this.id = rs.getInt("id"); 
				this.nome = rs.getString("nome"); 
				this.senha = rs.getString("senha"); 
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe; 
	}
	
	
	/*
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SALVAR USUARIO
	 * */
	public void salvarUsuario(){
		String salva = "Insert Into usuario(nome, senha) "
				+ "values ('"+getNome()+"','"+getSenha()+"');";
		
		conn = ConnectionFactory.conectar(); 
		
		try {
			robo = conn.prepareStatement(salva); 
			robo.executeUpdate(); 
			
			JOptionPane.showMessageDialog(null, "Usuario cadastrado!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro no cadastrar!");
		}
		
	}
	
	// Seleziona loja
		public int select(){
			 int id = 0; 
			 String seleziona ="select *  from usuario order by id desc limit 1;";
			
			try {
				robo = conn.prepareStatement(seleziona); 
				rs = robo.executeQuery(); 
				
				if (rs.next()) {
				 setId(rs.getInt("id"));
				 setNome(rs.getString("nome"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return id;
		}
		
		// -------------------------------------- ----------------------------------------EXCLUIR FORNECEDORES
		public void excluirU() {
			String deletar = "DELETE FROM usuario WHERE id=" + getId() + ";";
			
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
		public void editarU() {
		
			
			String editar = "UPDATE usuario SET nome='" + getNome() + "', senha= '"+getSenha()+"' "
					+ "WHERE id=" + getId() + ";";
			
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
		public void carregarUsuario(JTable nameTable) {

			String[] nomeColunas = {"ID " ,"NOME","SENHA"};

			DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
			aModel.setColumnIdentifiers(nomeColunas);

			// consultar os dados
			String select = "SELECT * FROM usuario;";

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
