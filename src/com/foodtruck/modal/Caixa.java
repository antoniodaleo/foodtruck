package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

public class Caixa {
	private int id; 
	private double giacenza, deposito,saco; 
	private String data_fe; 
	
	public String getData_fe() {
		return data_fe;
	}
	public void setData_fe(String data_fe) {
		this.data_fe = data_fe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getGiacenza() {
		return giacenza;
	}
	public void setGiacenza(double giacenza) {
		this.giacenza = giacenza;
	}
	public double getDeposito() {
		return deposito;
	}
	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}
	public double getSaco() {
		return saco;
	}
	public void setSaco(double saco) {
		this.saco = saco;
	}
	
	Connection conn = null; 
	PreparedStatement robo = null; 
	ResultSet rs  = null; 
	
	
	// Aggiorna caixa
	public void aggiornaCaixa(){
		int id = 0; 
		
		String select = "select*from caixa order by id desc limit 1";
		
		conn = ConnectionFactory.conectar();
		try {
			robo = conn.prepareStatement(select);
			rs = robo.executeQuery(); 
			
			if (rs.next()) {
				id = rs.getInt("id");
			}
			
			String update = "update caixa set giacenza = giacenza + "+getDeposito()+" where id = "+id+";";
			robo = conn.prepareStatement(update); 
			robo.executeUpdate(); 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sacaCaixa(){
		int id = 0; 
		
		String select = "select*from caixa order by id desc limit 1";
		
		conn = ConnectionFactory.conectar();
		try {
			robo = conn.prepareStatement(select);
			rs = robo.executeQuery(); 
			
			if (rs.next()) {
				id = rs.getInt("id");
			}
			
			String update = "update caixa set giacenza = giacenza - "+getSaco()+" where id = "+id+";";
			robo = conn.prepareStatement(update); 
			robo.executeUpdate(); 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//Mostra giacenza 
	public void mostraGiacenza(){
		String select = "select*from caixa order by id desc limit 1";
		conn = ConnectionFactory.conectar(); 
		
		try {
			robo = conn.prepareStatement(select) ; 
			rs = robo.executeQuery(); 
			if (rs.next()) {
				setId( rs.getInt("id"));
				setGiacenza( rs.getDouble("giacenza"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alimentaCaixa(double totaleVendita){
		String select = "select*from caixa order by id desc limit 1";
		conn = ConnectionFactory.conectar(); 
		
		try {
			robo = conn.prepareStatement(select);
			rs= robo.executeQuery(); 
			if (rs.next()) {
				setId(rs.getInt("id"));
				
			}
			
			String update = "update caixa set giacenza = giacenza + "+totaleVendita+" where id = "+getId()+";";
			robo = conn.prepareStatement(update); 
			robo.executeUpdate(); 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void aberturaCaixa(){
		
		
		String apri = "insert into caixa (giacenza) values ("+getGiacenza()+");";
		conn = ConnectionFactory.conectar(); 
		try {
			robo = conn.prepareStatement(apri); 
			robo.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "Abertura caixa efetuada com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}; 
	
	
	
	public void fechaturaCaixa(){
		
		String select = "select*from caixa order by id desc limit 1";
		conn = ConnectionFactory.conectar(); 
		try {
			robo = conn.prepareStatement(select); 
			rs = robo.executeQuery();
			if (rs.next()) {
				setId(rs.getInt("id"));
				
			}
			
			String aggiorna = "update caixa set giacenza = giacenza - "+getSaco()+", data_fe = '"+getData_fe()+"' where id = "+getId()+";";
				robo = conn.prepareStatement(aggiorna); 
				robo.executeUpdate(); 
				JOptionPane.showMessageDialog(null, "Caixa fechada com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}; 
	
	
	
}
