package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import modelo.Atenea;
import modelo.Producto;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class VistaCatalogo {

	private JFrame frmCatlogo;
	private JTextField tfNombre;
	private JTextField tfPrecioC;
	private JTextField tfPrecioV;
	private JTextField tfUnidades;

	public VistaCatalogo(Atenea atenea) {
		initialize(atenea);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea) {
		frmCatlogo = new JFrame();
		frmCatlogo.setTitle("Cat\u00E1logo");
		frmCatlogo.setBounds(100, 100, 335, 420);
		frmCatlogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCatlogo.getContentPane().setLayout(null);
		
		JLabel lblCatlogo = new JLabel("Cat\u00E1logo");
		lblCatlogo.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblCatlogo.setBounds(10, 11, 145, 20);
		frmCatlogo.getContentPane().add(lblCatlogo);
		
		JComboBox<String> cbProductos = new JComboBox<String>();
		cbProductos.setBounds(10, 39, 283, 22);
		frmCatlogo.getContentPane().add(cbProductos);
		
		JLabel lblDatosDelProducto = new JLabel("Datos del producto");
		lblDatosDelProducto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDatosDelProducto.setBounds(10, 84, 145, 20);
		frmCatlogo.getContentPane().add(lblDatosDelProducto);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(102, 115, 191, 20);
		frmCatlogo.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 118, 56, 14);
		frmCatlogo.getContentPane().add(lblNombre);
		
		JLabel lblPrecioDeCompra = new JLabel("Precio de compra ($):");
		lblPrecioDeCompra.setBounds(20, 147, 135, 14);
		frmCatlogo.getContentPane().add(lblPrecioDeCompra);
		
		tfPrecioC = new JTextField();
		tfPrecioC.setEditable(false);
		tfPrecioC.setColumns(10);
		tfPrecioC.setBounds(190, 144, 74, 20);
		frmCatlogo.getContentPane().add(tfPrecioC);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de venta ($):");
		lblPrecioDeVenta.setBounds(20, 178, 135, 14);
		frmCatlogo.getContentPane().add(lblPrecioDeVenta);
		
		tfPrecioV = new JTextField();
		tfPrecioV.setEditable(false);
		tfPrecioV.setColumns(10);
		tfPrecioV.setBounds(190, 175, 74, 20);
		frmCatlogo.getContentPane().add(tfPrecioV);
		
		JLabel lblUnidadesParaAviso = new JLabel("Unidades para aviso de falta:");
		lblUnidadesParaAviso.setBounds(20, 209, 171, 14);
		frmCatlogo.getContentPane().add(lblUnidadesParaAviso);
		
		tfUnidades = new JTextField();
		tfUnidades.setEditable(false);
		tfUnidades.setColumns(10);
		tfUnidades.setBounds(190, 208, 74, 20);
		frmCatlogo.getContentPane().add(tfUnidades);
		
		JCheckBox cbEditable = new JCheckBox("Editable");
		cbEditable.setBounds(102, 241, 78, 23);
		frmCatlogo.getContentPane().add(cbEditable);
		
		JButton btnAgregarNuevoProducto = new JButton("Agregar nuevo producto");
		btnAgregarNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaAgregarProducto(atenea, cbProductos); 
			            
			         }
			      });
			}
		});
		btnAgregarNuevoProducto.setBounds(10, 350, 283, 23);
		frmCatlogo.getContentPane().add(btnAgregarNuevoProducto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 329, 283, 2);
		frmCatlogo.getContentPane().add(separator);
		
		JButton btnGuardarDatosDel = new JButton("Guardar datos del producto");
		btnGuardarDatosDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = atenea.getCatalogo().getProducto(atenea.getCatalogo().getIdProducto((String)cbProductos.getSelectedItem()));
				producto.setNombre(tfNombre.getText());
				producto.setCant_min(Integer.parseInt(tfUnidades.getText()));
				producto.setPrecio_costo(Double.parseDouble(tfPrecioC.getText()));
				producto.setPrecio_venta(Double.parseDouble(tfPrecioV.getText()));
				try {
					atenea.getCatalogo().almacenarObjeto("catalogo.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cbProductos.removeAllItems();
				ArrayList<Producto> productos = atenea.getCatalogo().getProductos();
				ArrayList<String> prodnombre = new ArrayList<String>();
				for (Producto i : productos) {
					prodnombre.add(i.getNombre());
				}
				Collections.sort(prodnombre);
				for (String i: prodnombre) {
					cbProductos.addItem(i);
				}
			}
		});
		btnGuardarDatosDel.setBounds(35, 278, 224, 32);
		frmCatlogo.getContentPane().add(btnGuardarDatosDel);
		
		ArrayList<Producto> productos = atenea.getCatalogo().getProductos();
		ArrayList<String> prodnombre = new ArrayList<String>();
		frmCatlogo.setVisible(true);
		
		for (Producto i : productos) {
			prodnombre.add(i.getNombre());
		}
		Collections.sort(prodnombre);
		for (String i: prodnombre) {
			cbProductos.addItem(i);
		}
		
		cbEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbEditable.isSelected()) {
					tfNombre.setEditable(true);
					tfPrecioC.setEditable(true);
					tfPrecioV.setEditable(true);
					tfUnidades.setEditable(true);
				}
				else {
					tfNombre.setEditable(false);
					tfPrecioC.setEditable(false);
					tfPrecioV.setEditable(false);
					tfUnidades.setEditable(false);
				}
			}
		});
		Producto producto = new Producto();	
		for (Producto i : productos) {
			if (i.getNombre().equals(cbProductos.getSelectedItem())) {
				producto = i;
			}
		}
		tfNombre.setText(producto.getNombre());
		tfPrecioC.setText(Double.toString(producto.getPrecio_costo()));
		tfPrecioV.setText(Double.toString(producto.getPrecio_venta()));
		tfUnidades.setText(String.valueOf(producto.getCant_min()));
		
		cbProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != cbProductos.getSelectedItem()) {
					for (Producto i : productos ) {
						if (cbProductos.getSelectedItem().equals(i.getNombre())) {
							tfNombre.setText(i.getNombre());
							tfPrecioC.setText(Double.toString(i.getPrecio_costo()));
							tfPrecioV.setText(Double.toString(i.getPrecio_venta()));
							tfUnidades.setText(String.valueOf(i.getCant_min()));
						}
					}
				}
			}
		});
		
	
	}
}
