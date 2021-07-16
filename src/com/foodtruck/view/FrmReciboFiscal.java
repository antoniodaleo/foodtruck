package com.foodtruck.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import com.foodtruck.modal.Produto;
import com.foodtruck.modal.Venda;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class FrmReciboFiscal extends JFrame {

	private JPanel contentPane;
	private JTable aTableItem;
	private JTextField txtTotal;
	private JPanel panel;
	
	
	public void printComponenet(){
	    PrinterJob pj = PrinterJob.getPrinterJob();
	    pj.setJobName(" panel ");
	    pj.setPrintable (new Printable() {    
	        public int print(Graphics pg, PageFormat pf, int pageNum){
	            if (pageNum > 0){
	                return Printable.NO_SUCH_PAGE;
	            }
	            Graphics2D g2 = (Graphics2D) pg;
	            g2.translate(pf.getImageableX(), pf.getImageableY());
	            panel.paint(g2);        // o JPanel aqui
	                    // o JPanel aqui
	            return Printable.PAGE_EXISTS;
	        }
	    });
	    boolean ok = pj.printDialog(); 
	    if (ok) {
			try {
				pj.print();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	    
	    
	}
	
	
	
	
	
		public FrmReciboFiscal(int id)  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 442);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 286, 403);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRistorante = new JLabel("Ristorante");
		lblRistorante.setBounds(10, 11, 94, 14);
		panel.add(lblRistorante);
		
		JLabel label = new JLabel("Ristorante");
		label.setBounds(10, 36, 94, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Ristorante");
		label_1.setBounds(10, 61, 94, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Ristorante");
		label_2.setBounds(10, 86, 94, 14);
		panel.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(10, 111, 266, 131);
		panel.add(scrollPane);
		
		aTableItem = new JTable();

		
		aTableItem.setShowVerticalLines(false);
		aTableItem.setShowHorizontalLines(false);
		aTableItem.setShowGrid(false);
		aTableItem.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		
		JTableHeader t = aTableItem.getTableHeader();
		aTableItem.getTableHeader().setBorder(BorderFactory.createBevelBorder(0));
		t.setBackground(Color.WHITE);
		//t.setOpaque(true);
		((DefaultTableCellRenderer) t.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		Produto p = new Produto(); 
		p.carregarItemVendidos(aTableItem, id);
		aTableItem.setBackground(Color.WHITE);
		scrollPane.setViewportView(aTableItem);
		scrollPane.setBorder(BorderFactory.createBevelBorder(0));
		
		txtTotal = new JTextField();
		Venda v = new Venda(); 
		v.selectTotaleVendita(id);
		txtTotal.setText(v.getTotal_venda()+ "");
		txtTotal.setBorder(null);
		txtTotal.setBounds(190, 253, 86, 28);
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(91, 260, 46, 14);
		panel.add(lblTotal);
		
		//printComponenet(); 
		
	}
}
