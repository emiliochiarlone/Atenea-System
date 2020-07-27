package vista;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import modelo.Atenea;
import modelo.ProdVenta;
import modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.TextField;
import java.awt.event.ActionEvent;

public class VistaRegistrarCompraStock {

	private JFrame frame;

	public VistaRegistrarCompraStock(Atenea atenea, JComboBox cbProductos, JLabel label, JTextField textfield) {
		initialize(atenea, cbProductos, label, textfield);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea, JComboBox pcbProductos, JLabel label, JTextField textfield) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 312, 434);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox cbProductos = new JComboBox();
		cbProductos.setBounds(97, 11, 181, 22);
		frame.getContentPane().add(cbProductos);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(110, 60, 46, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(22, 15, 65, 14);
		frame.getContentPane().add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(22, 63, 65, 14);
		frame.getContentPane().add(lblCantidad);
		
		ArrayList<ProdVenta> prodcompra = new ArrayList<ProdVenta>();
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setBounds(56, 110, 181, 23);
		frame.getContentPane().add(btnAgregarProducto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 161, 230, 2);
		frame.getContentPane().add(separator);
		
		JComboBox cbProdCompra = new JComboBox();
		cbProdCompra.setMaximumRowCount(16);
		cbProdCompra.setBounds(34, 229, 222, 22);
		frame.getContentPane().add(cbProdCompra);
		
		JLabel lblProductosDeLa = new JLabel("Productos de la compra:");
		lblProductosDeLa.setBounds(81, 174, 166, 14);
		frame.getContentPane().add(lblProductosDeLa);
		
		JLabel lblProducto_1 = new JLabel("Producto");
		lblProducto_1.setBounds(42, 207, 76, 14);
		frame.getContentPane().add(lblProducto_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(128, 207, 46, 14);
		frame.getContentPane().add(lblX);
		
		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setBounds(177, 207, 46, 14);
		frame.getContentPane().add(lblCantidad_1);
		
		JButton btnEliminarTodosLos = new JButton("Eliminar todos los productos");
		btnEliminarTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prodcompra.clear();
				cbProdCompra.removeAllItems();		
			}
		});
		btnEliminarTodosLos.setBounds(22, 278, 256, 31);
		frame.getContentPane().add(btnEliminarTodosLos);
		
		JButton btnRegistrarCompra = new JButton("Registrar compra");
		btnRegistrarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea registrar la compra?", "Confirmar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				try {
					if (prodcompra.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay productos seleccionados");
					}
					else {
						for (ProdVenta i : prodcompra) {
							atenea.getStock().NuevaCompra(i.getId_producto(), i.getCantidad(),atenea.getCatalogo().getProducto(i.getId_producto()).getCant_min());
						}
						JOptionPane.showMessageDialog(null, "Compra registrada correctamente");
						ArrayList<ProdVenta> prodstock = atenea.getStock().getProductos();
						pcbProductos.removeAllItems();
						if (atenea.getStock().getFaltasStock().isEmpty()) {
							label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/nofaltastock.jpg")));
						}
						for (ProdVenta i: prodstock) {
							pcbProductos.addItem(atenea.getCatalogo().getProducto(i.getId_producto()).getNombre() + " X " + i.getCantidad());
						}
						textfield.setText(Double.toString(atenea.getStock().getValorTotal(atenea.getCatalogo())));
						frame.dispose();
					}
				}
				catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnRegistrarCompra.setBounds(44, 359, 193, 23);
		frame.getContentPane().add(btnRegistrarCompra);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 334, 230, 2);
		frame.getContentPane().add(separator_1);
		
		ArrayList<Producto> catalogo = atenea.getCatalogo().getProductos();
		ArrayList<String> catalogonombres = new ArrayList<String>();
		for (Producto i: catalogo) {
			catalogonombres.add(i.getNombre());
		}
		Collections.sort(catalogonombres);
		for (String i: catalogonombres) {
			cbProductos.addItem(i);
		}
		
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdVenta prod = new ProdVenta(atenea.getCatalogo().getIdProducto((String) cbProductos.getSelectedItem()),(int)spinner.getValue());
				prodcompra.add(prod);
				cbProdCompra.addItem(atenea.getCatalogo().getProducto(prod.getId_producto()).getNombre() + " X " + Integer.toString(prod.getCantidad()));
			}
		});
	}
}
