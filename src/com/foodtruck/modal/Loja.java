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

public class Loja {
	
	private int id; 
	private String cnpj, nome, endereco, numero, bairro, cidade, estado, telefone, celular;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	} 
	
	
	Connection conn = ConnectionFactory.conectar(); 
	PreparedStatement robo = null; 
	ResultSet rs = null; 
	
	
	
	
	
	// Medoto de insert no banco de dados
	public void insertLoja(){
		String inserirLoja= "insert into loja (cnpj, nome, endereco, numero, bairro, cidade, estado, telefone, celular)"
				+ " values('"+ getCnpj()+"', '"+getNome()+"','"+getEndereco()+"','"+getNumero()+"',"
				+ "'"+getBairro()+"','"+getCidade()+"','"+getEstado()+"','"+getTelefone()+"','"+getCelular()+"');";
		
		try {
			robo = conn.prepareStatement(inserirLoja); 
			robo.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Loja cadastrada com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// Seleziona loja
	public int select(){
		 int id = 0; 
		 String seleziona ="select *  from loja order by id desc limit 1;";
		
		try {
			robo = conn.prepareStatement(seleziona); 
			rs = robo.executeQuery(); 
			
			if (rs.next()) {
			 setId(rs.getInt("id"));
			 setCnpj(rs.getString("cnpj"));
			 setNome(rs.getString("nome"));
			 setEndereco(rs.getString("endereco"));
			 setNumero(rs.getString("numero"));
			 setBairro(rs.getString("bairro"));
			 setCidade(rs.getString("cidade"));
			 setEstado(rs.getString("estado"));
			 setTelefone(rs.getString("telefone"));
			 setCelular(rs.getString("celular")); 
			 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	// -------------------------------------- ----------------------------------------EXCLUIR FORNECEDORES
	public void excluir() {
		String deletar = "DELETE FROM loja WHERE id=" + getId() + ";";
		
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
		/*
		 * String editar = "UPDATE fornecedores SET nome='" + getNome() + "',cnpj='"
		        + getCnpj() + "',rua='" + getRua() + "',numero =  "+getNumero()+" "
				+ ", bairro = '"+getBairro()+"', cidade = '"+getCidade()+"', estado = '"+getEstado()+"'"
				+ ", telefone = '"+getTelefone()+"' , celular ='"+getCelular()+"', "
				+ "email = '"+getEmail()+"', site= '"+getSite()+"'   WHERE id=" + getId() + ";";

		 * 
		 * */
		
		String editar = "UPDATE loja SET cnpj='" + getCnpj() + "',  nome = '"+getNome()+"'"
				+ ",endereco = '"+getEndereco()+"', numero = '"+getNumero()+"', bairro = '"+getBairro()+"'"
						+ ",cidade = '"+getCidade()+"', estado ='"+getEstado()+"', telefone = '"+getTelefone()+"'"
								+ ",celular = '"+getCelular()+"'    WHERE id=" + getId() + ";";
		
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
		public void carregarLoja(JTable nameTable) {

			String[] nomeColunas = {"ID " ,"CNPJ", "Nome", "Endereço", "Numero", "Bairro", "Cidade", "Estado"
					,"Telefone", "Celular"};

			DefaultTableModel aModel = (DefaultTableModel) nameTable.getModel();
			aModel.setColumnIdentifiers(nomeColunas);

			// consultar os dados
			String select = "SELECT * FROM loja;";

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
