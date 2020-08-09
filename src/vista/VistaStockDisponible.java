package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import modelo.Atenea;
import modelo.ProdVenta;
import modelo.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaStockDisponible {

	private JFrame frame;
	private JTextField tfValorStock;

	/**
	 * Create the application.
	 * @param bevande 
	 */
	public VistaStockDisponible(Atenea atenea, JLabel label) {
		initialize(atenea, label);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea, JLabel label) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 529, 163);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Gestión de stock");
		
		JLabel lblStockDisponible = new JLabel("Stock disponible:");
		lblStockDisponible.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblStockDisponible.setBounds(10, 11, 134, 14);
		frame.getContentPane().add(lblStockDisponible);
		
		JComboBox cbProductos = new JComboBox();
		cbProductos.setBounds(154, 9, 349, 22);
		frame.getContentPane().add(cbProductos);
		
		ArrayList<ProdVenta> prodsventa = atenea.getStock().getProductos();
		
		JButton btnNewButton = new JButton("Registrar compra de mercader\u00EDa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaRegistrarCompraStock(atenea, cbProductos, label, tfValorStock ); 
			            
			         }
			      });
			}
		});
		btnNewButton.setBounds(271, 81, 232, 32);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblValorDeStock = new JLabel("Valor de Stock:");
		lblValorDeStock.setBounds(10, 53, 96, 14);
		frame.getContentPane().add(lblValorDeStock);
		
		tfValorStock = new JTextField();
		tfValorStock.setEditable(false);
		tfValorStock.setBounds(154, 50, 96, 20);
		frame.getContentPane().add(tfValorStock);
		tfValorStock.setColumns(10);
		tfValorStock.setText(Double.toString(atenea.getStock().getValorTotal(atenea.getCatalogo())));
		
		JButton btnVerFaltasDe = new JButton("Ver faltas de stock");
		btnVerFaltasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaFaltasStock(atenea); 
			         }
			      });
			}
		});
		btnVerFaltasDe.setBounds(10, 81, 232, 32);
		frame.getContentPane().add(btnVerFaltasDe);
		
		ArrayList<ProdVenta> prodstock = atenea.getStock().getProductos();
		ArrayList<String> prodnombres = new ArrayList<String>();
		
		
		for (ProdVenta i: prodstock) {
			prodnombres.add(atenea.getCatalogo().getProducto(i.getId_producto()).getNombre() + "     x     " + i.getCantidad());
		}
		Collections.sort(prodnombres);
		for (String i: prodnombres) {
			cbProductos.addItem(i);
		}
	}
}
