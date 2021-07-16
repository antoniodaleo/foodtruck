package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class datateste {
	private String data_fine;
	
	
	public String getData_fine() {
		return data_fine;
	}


	public void setData_fine(String data_fine) {
		this.data_fine = data_fine;
	}


	Connection conn = null; 
	PreparedStatement robo = null; 
	ResultSet rs  = null; 
	
	

	
	
	
	public void fechaturaCaixa(){
		
		
		String insert = "insert into datateste (data_fine)values ('"+getData_fine()+"') ;";
		conn = ConnectionFactory.conectar(); 
		try {
			robo = conn.prepareStatement(insert); 
			robo.executeUpdate();
			
			
			JOptionPane.showMessageDialog(null, "Caixa fechada com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}; 
	
	
	
	
}
