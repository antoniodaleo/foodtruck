package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	public static Connection conectar(){
		String enderecoBanco = "jdbc:mysql://localhost/data_food";
		String usuario = "root";
		String senha = "123";
		Connection conn = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			try {
				conn = DriverManager.getConnection(enderecoBanco, usuario, senha); 
				System.out.println("Conectado");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Conexão falhou");
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Driver não carregado");
		}
		return conn; 
		
	}
	
}
