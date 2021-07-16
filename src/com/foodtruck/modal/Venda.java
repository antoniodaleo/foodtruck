package com.foodtruck.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;





public class Venda {
	private int id; 
	private int usuario_id; 
	private double total_venda = 0; 
	private int tipo_pagamento_id; 
	private int qtd; 
	
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public double getTotal_venda() {
		return total_venda;
	}
	public void setTotal_venda(double total_venda) {
		this.total_venda = total_venda;
	}
	public int getTipo_pagamento_id() {
		return tipo_pagamento_id;
	}
	public void setTipo_pagamento_id(int tipo_pagamento_id) {
		this.tipo_pagamento_id = tipo_pagamento_id;
	}
	Connection conn = ConnectionFactory.conectar(); 
	PreparedStatement robo = null; 
	ResultSet rs = null;
	
	
	//----------------------------------------------------- Apriamo la vendita
	public int abrirVenda(int usuario, int pagamento) {
		int id = 0; 
		String insert = "INSERT INTO venda (usuario_id, tipo_pagamento_id) VALUES ("
				+ usuario + ",1);";

		try {
			robo = conn.prepareStatement(insert);
			robo.executeUpdate();

			String ultimoId = "SELECT id FROM venda ORDER BY id DESC LIMIT 1";
			robo = conn.prepareStatement(ultimoId);
			rs = robo.executeQuery();
			
			if(rs.next()){
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id; 

	}
	
	// ----------------------------------------------->>>>>>> METODO ADIÇIONAR PRODUTO, VENDA, E QTD NA VENDA
		public void carrinhoDeCompra(int venda, int produto, int qtde, double sub){
			String inserir = "INSERT INTO item_produtos (venda_id, produto_id, qtdeV, sub) "
					+ "values ("+venda+","+produto+","+qtde+","+sub+");";
			
			try {
				robo = conn.prepareStatement(inserir); 
				robo.executeUpdate();
				System.out.println("Item no carrinho");
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		// ----------------------------------------------->>>>>>> METODO ADIÇIONAR NOME CLIENTE
				public void inserirCliente(String cliente){
					String seleziona = "select id from item_produtos order by id desc limit 1";
					
					try {
						robo = conn.prepareStatement(seleziona); 
						rs =robo.executeQuery(); 
						
						if (rs.next() ) {
							setId(rs.getInt("id"));
							
						}
						
						String insCliente= "update item_produtos set cliente = '"+cliente+"' where id = "+getId()+";";
						robo = conn.prepareStatement(insCliente);  
						robo.executeUpdate(); 
						
						System.out.println("Nome do cliente inserido");
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			
		
		// ----------------------------------------------->>>>>>> METODO INSERIR TOTALE VENDA  
		public void inserirTotale(){
			String inserirVenda = "select id from venda order by id desc limit 1";
			
			
			try {
				robo = conn.prepareStatement(inserirVenda); 
				rs = robo.executeQuery(); 
				
				if (rs.next()) {
					setId(rs.getInt("id"));
					String update  = "UPDATE venda SET totaleVenda = "+getTotal_venda()+" WHERE id = "+getId()+";";
				
					robo = conn.prepareStatement(update);  
					robo.executeUpdate(); 
					System.out.println("Venda atualizada ");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		// ----------------------------------------------->>>>>>> METODO INSERIR TIPO PAGAMENTO 
			public void inserirPagamento(){
				String inserirVenda = "select id from venda order by id desc limit 1";
				
				conn = ConnectionFactory.conectar(); 
				try {
					robo = conn.prepareStatement(inserirVenda); 
					rs = robo.executeQuery(); 
					
					if (rs.next()) {
						setId(rs.getInt("id"));
						String update  = "UPDATE venda SET tipo_pagamento_id = "+getTipo_pagamento_id()+" WHERE id = "+getId()+";";
					
						robo = conn.prepareStatement(update);  
						robo.executeUpdate(); 
						System.out.println("Pagamento atualizada ");
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}	
		
		
		
	// --------------------------------------------->>> Diminuendo lo stock 
		public void lowStock(int produto, int qtdeN){
			String low = "UPDATE produto SET qtde =(qtde -"+qtdeN+") where id = "+produto+";";
			
			conn = ConnectionFactory.conectar(); 
			try {
				robo = conn.prepareStatement(low); 
				robo.executeUpdate();
				System.out.println("Stock aggiornato");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	//======================================================= Cancelar venda  baxa no estoque
		public void cancelaVenda(int id){
			String select = "select v.id, p.descricao, p.qtde, ip.qtdeV, v.totaleVenda from item_produtos ip inner join venda v on ip.venda_id = v.id inner join produto p on ip.produto_id = p.id where ip.venda_id = "+id+" ;"; 
			
			conn = ConnectionFactory.conectar(); 
			
			try {
				robo = conn.prepareStatement(select); 
				rs = robo.executeQuery(); 
				
				 while (rs.next()) {
					
				    String desc = rs.getString("descricao");
					int qtdStock = rs.getInt("qtde"); 
					int qtdVendute = rs.getInt("qtdeV");
					int storno = qtdStock + qtdVendute; 
					
					String stornaMerce = "update produto set qtde = "+storno+" where descricao = '"+desc+"';";
					robo = conn.prepareStatement(stornaMerce); 
					robo.executeUpdate();
		
				 } 
				 
				String deletaIt = "delete from item_produtos where venda_id = "+id+";"; 
				robo = conn.prepareStatement(deletaIt); 
				robo.executeUpdate();
				
				String deletaVenda = "delete from venda where id = "+id+";";
				robo = conn.prepareStatement(deletaVenda); 
				robo.executeUpdate(); 
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			
			} finally {			
				try {
					if(robo != null){
						robo.close();
						conn.close();
					}
					conn.close();
				} catch (SQLException e) {
					// LOGGING
					e.printStackTrace();
				}
			}
			
			
			
		}
	
		// Seleziona loja
		public void selectTotaleVendita(int id){
			 
			 String seleziona ="select id, totaleVenda  from venda where id = "+id+";";
			
			try {
				robo = conn.prepareStatement(seleziona); 
				rs = robo.executeQuery(); 
				
				if (rs.next()) {
				 setId(rs.getInt("id"));
				 setTotal_venda(rs.getDouble("totaleVenda"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
}
