package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.Atenea;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.Color;

public class VistaFaltasStock {

	private JFrame frmFaltasDeStock;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VistaFaltasStock(Atenea atenea) {
		initialize(atenea);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea) {
		frmFaltasDeStock = new JFrame();
		frmFaltasDeStock.setTitle("Faltas de stock");
		frmFaltasDeStock.setBounds(100, 100, 450, 144);
		frmFaltasDeStock.setVisible(true);
		frmFaltasDeStock.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFaltasDeStock.getContentPane().setLayout(null);
		
		JLabel lblLosProductosQue = new JLabel("Los productos que se encuentran en falta son:");
		lblLosProductosQue.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblLosProductosQue.setBounds(23, 21, 401, 20);
		frmFaltasDeStock.getContentPane().add(lblLosProductosQue);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(104, 52, 211, 22);
		frmFaltasDeStock.getContentPane().add(comboBox);
		
		JLabel lNoFalta = new JLabel("No hay productos en falta actualmente");
		lNoFalta.setForeground(Color.RED);
		lNoFalta.setBounds(114, 80, 252, 14);
		frmFaltasDeStock.getContentPane().add(lNoFalta);
		lNoFalta.setVisible(false);
		
		ArrayList<Integer> prodfalta = atenea.getStock().getFaltasStock();
		System.out.println(""); //borrarrr
		if (prodfalta.isEmpty()) {
			lNoFalta.setVisible(true);
		}
		else {
			for (Integer i : prodfalta) {
				comboBox.addItem(atenea.getCatalogo().getProducto(i).getNombre());
			}
		}
	}
}
