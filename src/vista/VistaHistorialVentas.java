package vista;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import modelo.Atenea;
import modelo.ProdVenta;
import modelo.Reserva;
import modelo.Venta;
import javax.swing.JButton;


public class VistaHistorialVentas {

	private JFrame frame;
	private JTextField tfNombre;
	private JTextField tfMonto;
	private JTextField tfDomicilio;
	private JTextField tfFecha;
	private JTextField tfDescripcion;

	/**
	 * Create the application.
	 */
	public VistaHistorialVentas(Atenea atenea) {
		initialize(atenea);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea) {
		frame = new JFrame();
		frame.setTitle("Historial de ventas");
		frame.setBounds(100, 100, 413, 578);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblHistorialDeVentas = new JLabel("Historial de ventas");
		lblHistorialDeVentas.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblHistorialDeVentas.setBounds(10, 11, 145, 14);
		frame.getContentPane().add(lblHistorialDeVentas);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la venta:");
		lblDatosDeLa.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDatosDeLa.setBounds(10, 126, 145, 14);
		frame.getContentPane().add(lblDatosDeLa);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(169, 164, 215, 20);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel label = new JLabel("Nombre del cliente:");
		label.setBounds(10, 167, 123, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Monto ($):");
		label_1.setBounds(10, 192, 101, 14);
		frame.getContentPane().add(label_1);
		
		tfMonto = new JTextField();
		tfMonto.setEditable(false);
		tfMonto.setColumns(10);
		tfMonto.setBounds(169, 189, 96, 20);
		frame.getContentPane().add(tfMonto);
		
		tfDomicilio = new JTextField();
		tfDomicilio.setEditable(false);
		tfDomicilio.setColumns(10);
		tfDomicilio.setBounds(168, 244, 215, 20);
		frame.getContentPane().add(tfDomicilio);
		
		JLabel label_3 = new JLabel("Domicilio del cliente:");
		label_3.setBounds(10, 247, 139, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Fecha:");
		label_4.setBounds(10, 278, 123, 14);
		frame.getContentPane().add(label_4);
		
		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(168, 275, 215, 20);
		frame.getContentPane().add(tfFecha);
		
		JLabel label_5 = new JLabel("Descripci\u00F3n:");
		label_5.setBounds(10, 313, 76, 14);
		frame.getContentPane().add(label_5);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setEditable(false);
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(168, 306, 215, 94);
		frame.getContentPane().add(tfDescripcion);
		
		JComboBox<String> cbProductos = new JComboBox<String>();
		cbProductos.setBounds(168, 411, 188, 22);
		frame.getContentPane().add(cbProductos);
		
		JLabel lblProductosVendidos = new JLabel("Productos vendidos:");
		lblProductosVendidos.setBounds(10, 415, 133, 14);
		frame.getContentPane().add(lblProductosVendidos);
		frame.setVisible(true);
		
		ArrayList<Venta> ventas = atenea.getHistorialVentas().getVentas();
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 45, 373, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnVerResumenContable = new JButton("Resumen contable total");
		btnVerResumenContable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaResumenContable(atenea); 
			         }
			      });
			}
		});
		btnVerResumenContable.setBounds(84, 491, 207, 37);
		frame.getContentPane().add(btnVerResumenContable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(36, 478, 315, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblModoDeEntrega = new JLabel("Modo de entrega:");
		lblModoDeEntrega.setBounds(10, 217, 139, 14);
		frame.getContentPane().add(lblModoDeEntrega);
		
		JLabel lModoEntrega = new JLabel("");
		lModoEntrega.setFont(new Font("Gadugi", Font.PLAIN, 11));
		lModoEntrega.setBounds(168, 220, 188, 14);
		frame.getContentPane().add(lModoEntrega);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbProductos.removeAllItems();
				for (Venta i : ventas) {
					if (comboBox.getSelectedItem() != null) {
						if (comboBox.getSelectedItem().equals(i.toString())) {
							tfNombre.setText(i.getcliente());
							tfMonto.setText(Double.toString(i.getMonto()));
							tfDescripcion.setText(i.getDescripcion());
							if (i.getEntregaDomicilio()){
								lModoEntrega.setText("Entrega a domicilio");
							}
							else {
								lModoEntrega.setText("Pasó a retirar");
							}
							tfDomicilio.setText(i.getDireccion());
							tfFecha.setText(i.dateToString());
							ArrayList<ProdVenta> productosventa = i.getProductos();
							for (ProdVenta j : productosventa ) {
								cbProductos.addItem(atenea.getCatalogo().getProducto(j.getId_producto()) + " X " + j.getCantidad());
							}
						}
					}
				}
			}
		});
		
		ArrayList<String> strVentas = new ArrayList<String>();
		for (Venta i : ventas) {
			strVentas.add(i.toString());
		}
		Collections.sort(strVentas);
		for (String i : strVentas) {
			comboBox.addItem(i);
		}
	}
}
