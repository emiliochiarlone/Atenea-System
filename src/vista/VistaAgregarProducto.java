package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import modelo.Atenea;
import modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class VistaAgregarProducto {

	private JFrame frmAgregarNuevoProducto;
	private JTextField tfNombre;
	private JTextField tfPrecioC;
	private JTextField tfPrecioV;
	private JTextField tfCUnidades;
	private JButton btnNewButton;
	private JSeparator separator;

	/**
	 * Create the application.
	 * @param cbProductos 
	 */
	public VistaAgregarProducto(Atenea atenea, JComboBox<String> cbProductos) {
		initialize(atenea, cbProductos);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea, JComboBox<String> cbProductos) {
		frmAgregarNuevoProducto = new JFrame();
		frmAgregarNuevoProducto.setTitle("Agregar nuevo producto");
		frmAgregarNuevoProducto.setBounds(100, 100, 377, 241);
		frmAgregarNuevoProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgregarNuevoProducto.getContentPane().setLayout(null);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(157, 35, 183, 20);
		frmAgregarNuevoProducto.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblAgregarNuevoProducto = new JLabel("Agregar nuevo producto al cat\u00E1logo");
		lblAgregarNuevoProducto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblAgregarNuevoProducto.setBounds(10, 11, 330, 20);
		frmAgregarNuevoProducto.getContentPane().add(lblAgregarNuevoProducto);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(20, 38, 86, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblNewLabel);
		
		JLabel lblPrecioDeCompra = new JLabel("Precio de compra ($):");
		lblPrecioDeCompra.setBounds(20, 66, 148, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblPrecioDeCompra);
		
		tfPrecioC = new JTextField();
		tfPrecioC.setColumns(10);
		tfPrecioC.setBounds(157, 63, 96, 20);
		frmAgregarNuevoProducto.getContentPane().add(tfPrecioC);
		
		tfPrecioV = new JTextField();
		tfPrecioV.setColumns(10);
		tfPrecioV.setBounds(157, 91, 96, 20);
		frmAgregarNuevoProducto.getContentPane().add(tfPrecioV);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de venta ($):");
		lblPrecioDeVenta.setBounds(20, 94, 148, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblPrecioDeVenta);
		
		tfCUnidades = new JTextField();
		tfCUnidades.setColumns(10);
		tfCUnidades.setBounds(214, 119, 39, 20);
		frmAgregarNuevoProducto.getContentPane().add(tfCUnidades);
		
		JLabel lblUnidadesParaAviso = new JLabel("Unidades para aviso de falta:");
		lblUnidadesParaAviso.setBounds(20, 122, 184, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblUnidadesParaAviso);
		
		btnNewButton = new JButton("Agregar producto al cat\u00E1logo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea agregar el producto?", "Confirmar producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (tfNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "No se puede registrar un producto sin nombre");
					}
					else {
						if (tfPrecioC.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar el precio de costo del producto");
						}
						else {
							String sMonto = tfPrecioC.getText();
							boolean flag = false;
							for (int i = 0; i< sMonto.length(); i++) {
								if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
									JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
									flag = true;
									i = sMonto.length();
								}
							}
							if (!flag) {
								sMonto = tfPrecioV.getText();
								for (int i = 0; i< sMonto.length(); i++) {
									if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
										JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
										flag = true;
										i = sMonto.length();
									}
								}
							}
							if (!flag) {
								Producto producto = new Producto();
								producto.setNombre(tfNombre.getText());
								if (tfCUnidades.getText().equals("")) {
									producto.setCant_min(0);
								}
								else {
									producto.setCant_min(Integer.parseInt(tfCUnidades.getText()));
								}
								producto.setPrecio_costo(Double.parseDouble(tfPrecioC.getText()));
								if (tfPrecioV.getText().equals("")) {
									producto.setPrecio_venta(0);
								}
								else {
									producto.setPrecio_venta(Double.parseDouble(tfPrecioV.getText()));
								}
								try {
									atenea.getCatalogo().agregarProducto(producto);
									atenea.getStock().calcularFaltas(atenea.getCatalogo());
									cbProductos.removeAllItems();
									ArrayList<Producto> productos = atenea.getCatalogo().getProductos();
									ArrayList<String> prodnombre = new ArrayList<String>();
									for (Producto j : productos) {
										prodnombre.add(j.getNombre());
									}
									Collections.sort(prodnombre);
									for (String j: prodnombre) {
										cbProductos.addItem(j);
									}
									frmAgregarNuevoProducto.dispose();
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Producto agregado");
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(97, 168, 203, 23);
		frmAgregarNuevoProducto.getContentPane().add(btnNewButton);
		
		separator = new JSeparator();
		separator.setBounds(72, 150, 255, 2);
		frmAgregarNuevoProducto.getContentPane().add(separator);
		frmAgregarNuevoProducto.setVisible(true);
	}

}
