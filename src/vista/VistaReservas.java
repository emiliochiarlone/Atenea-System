package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import modelo.Atenea;
import modelo.Mensaje;
import modelo.ProdVenta;
import modelo.Producto;
import modelo.Reserva;
import modelo.Venta;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class VistaReservas {

	private JFrame frmReservas;
	private JTextField tfNombre;
	private JTextField tfMonto;
	private JTextField tfDomicilio;
	private JTextField tfFecha;
	private JTextField tfDescripcion;
	private Reserva reserva;
	private JLabel lCambios;


	public VistaReservas(Atenea atenea, JLabel label) {
		initialize(atenea, label);
	}

	
	private void initialize(Atenea atenea, JLabel label) {
		frmReservas = new JFrame();
		frmReservas.setTitle("Reservas");
		frmReservas.setBounds(100, 100, 413, 738);
		frmReservas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmReservas.getContentPane().setLayout(null);
		
		reserva = new Reserva();
		JLabel lblHistorialDeVentas = new JLabel("Reservas");
		lblHistorialDeVentas.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblHistorialDeVentas.setBounds(10, 11, 145, 14);
		frmReservas.getContentPane().add(lblHistorialDeVentas);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la reserva:");
		lblDatosDeLa.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDatosDeLa.setBounds(10, 125, 145, 14);
		frmReservas.getContentPane().add(lblDatosDeLa);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(168, 158, 215, 20);
		frmReservas.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel labelCliente = new JLabel("Nombre del cliente:");
		labelCliente.setBounds(10, 161, 123, 14);
		frmReservas.getContentPane().add(labelCliente);
		
		JLabel label_1 = new JLabel("Monto ($):");
		label_1.setBounds(10, 192, 101, 14);
		frmReservas.getContentPane().add(label_1);
		
		tfMonto = new JTextField();
		tfMonto.setEditable(false);
		tfMonto.setColumns(10);
		tfMonto.setBounds(168, 189, 96, 20);
		frmReservas.getContentPane().add(tfMonto);
		
		tfDomicilio = new JTextField();
		tfDomicilio.setEditable(false);
		tfDomicilio.setColumns(10);
		tfDomicilio.setBounds(168, 244, 215, 20);
		frmReservas.getContentPane().add(tfDomicilio);
		
		JLabel label_3 = new JLabel("Domicilio del cliente:");
		label_3.setBounds(10, 247, 139, 14);
		frmReservas.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Fecha:");
		label_4.setBounds(10, 278, 123, 14);
		frmReservas.getContentPane().add(label_4);
		
		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(168, 275, 215, 20);
		frmReservas.getContentPane().add(tfFecha);
		
		JLabel label_5 = new JLabel("Descripci\u00F3n:");
		label_5.setBounds(10, 316, 76, 14);
		frmReservas.getContentPane().add(label_5);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setEditable(false);
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(168, 306, 215, 94);
		frmReservas.getContentPane().add(tfDescripcion);
		
		JComboBox<String> cbProductos = new JComboBox<String>();
		cbProductos.setBounds(168, 411, 188, 22);
		frmReservas.getContentPane().add(cbProductos);
		
		JLabel lblProductosVendidos = new JLabel("Productos reservados:");
		lblProductosVendidos.setBounds(10, 415, 133, 14);
		frmReservas.getContentPane().add(lblProductosVendidos);
		frmReservas.setVisible(true);
		
		ArrayList<Reserva> reservas = atenea.getListaReservas().getReservas();
		JComboBox<String> cbReservas = new JComboBox<String>();
		cbReservas.setBounds(10, 42, 373, 22);
		frmReservas.getContentPane().add(cbReservas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 630, 332, 2);
		frmReservas.getContentPane().add(separator);
		
		JLabel lblModoDeEntrega = new JLabel("Entrega a domicilio:");
		lblModoDeEntrega.setBounds(10, 217, 139, 14);
		frmReservas.getContentPane().add(lblModoDeEntrega);
		
		JButton btnMarcarComoVendido = new JButton("Marcar como vendido");
		btnMarcarComoVendido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar la reserva como venta?", "Confirmar venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						if (atenea.getListaReservas().marcarVendida(reserva.getId(), atenea) == Mensaje.listo) {
							JOptionPane.showMessageDialog(null, "La reserva se ha registrado como venta");
							cbReservas.removeAllItems();
							ArrayList<Reserva> reservas = atenea.getListaReservas().getReservas();
							ArrayList<String> resernombre = new ArrayList<String>();
							for (Reserva i : reservas) {
								resernombre.add(i.toString());
							}
							Collections.sort(resernombre);
							for (String i: resernombre) {
								cbReservas.addItem(i);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Error");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnMarcarComoVendido.setBounds(10, 546, 158, 73);
		frmReservas.getContentPane().add(btnMarcarComoVendido);
		
		JButton btnNuevaReserva = new JButton("Nueva reserva");
		btnNuevaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaRegistroReserva(atenea, label, cbReservas); 
			         }
			      });
			}
		});
		btnNuevaReserva.setForeground(Color.DARK_GRAY);
		btnNuevaReserva.setBounds(44, 643, 296, 45);
		frmReservas.getContentPane().add(btnNuevaReserva);
		
		JCheckBox chckbxEditable = new JCheckBox("Editable");
		chckbxEditable.setBounds(10, 450, 101, 23);
		frmReservas.getContentPane().add(chckbxEditable);
		
		JButton btnEditarProductos = new JButton("Editar productos");
		btnEditarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaEditarProductosReserva(atenea, cbReservas, reserva, label, cbProductos); 
			         }
			      });
			}
		});
		btnEditarProductos.setEnabled(false);
		btnEditarProductos.setBounds(168, 451, 188, 39);
		frmReservas.getContentPane().add(btnEditarProductos);
		
		JCheckBox cbEntrega = new JCheckBox("");
		cbEntrega.setSelected(true);
		cbEntrega.setEnabled(false);
		cbEntrega.setBounds(163, 216, 101, 23);
		frmReservas.getContentPane().add(cbEntrega);
		
		JButton btnGuardarCambios = new JButton("Guardar cambios ");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserva.setCliente(tfNombre.getText());
				reserva.setDescripcion(tfDescripcion.getText());
				reserva.setDireccion(tfDomicilio.getText());
				reserva.setMonto(Double.parseDouble(tfMonto.getText()));
				reserva.setEntregaDomicilio(cbEntrega.isSelected());
				try {
					atenea.getListaReservas().almacenarObjeto("listaReservas.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cbReservas.removeAllItems();
				ArrayList<Reserva> reservas = atenea.getListaReservas().getReservas();
				ArrayList<String> resernombre = new ArrayList<String>();
				for (Reserva i : reservas) {
					resernombre.add(i.toString());
				}
				Collections.sort(resernombre);
				for (String i: resernombre) {
					cbReservas.addItem(i);
				}
				lCambios.setVisible(true);
			}
		});
		btnGuardarCambios.setBounds(215, 546, 172, 73);
		frmReservas.getContentPane().add(btnGuardarCambios);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(191, 521, 2, 111);
		frmReservas.getContentPane().add(separator_1);
		
		lCambios = new JLabel("Los cambios se han guardado.");
		lCambios.setForeground(Color.GREEN);
		lCambios.setBounds(218, 531, 179, 14);
		lCambios.setVisible(false);
		frmReservas.getContentPane().add(lCambios);
		
		
		ArrayList<String> strReservas = new ArrayList<String>();
		for (Reserva i : reservas) {
			strReservas.add(i.toString());
		}
		Collections.sort(strReservas);
		for (String i : strReservas) {
			cbReservas.addItem(i);
		}
		
		
		cbReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbProductos.removeAllItems();
				for (Reserva i : reservas) {
					if (cbReservas.getSelectedItem() != null) {
						if (cbReservas.getSelectedItem().equals(i.toString())) {
							reserva = i;
							tfNombre.setText(i.getCliente());
							tfMonto.setText(Double.toString(i.getMonto()));
							tfDescripcion.setText(i.getDescripcion());
							if (i.getEntregaDomicilio()){
								cbEntrega.setSelected(true);
							}
							else {
								cbEntrega.setSelected(false);
							}
							tfDomicilio.setText(i.getDireccion());
							tfFecha.setText(i.getFecha().toString());
							ArrayList<ProdVenta> productosreserva = i.getProductos();
							for (ProdVenta j : productosreserva ) {
								cbProductos.addItem(atenea.getCatalogo().getProducto(j.getId_producto()) + " X " + j.getCantidad());
							}
							lCambios.setVisible(false);
						}
					}
				}
			}
		});
		
		chckbxEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxEditable.isSelected()) {
					tfNombre.setEditable(true);
					tfMonto.setEditable(true);
					tfDescripcion.setEditable(true);
					tfDomicilio.setEditable(true);
					cbEntrega.setEnabled(true);
					btnEditarProductos.setEnabled(true);
				}
				else {
					tfNombre.setEditable(false);
					tfMonto.setEditable(false);
					tfDescripcion.setEditable(false);
					tfDomicilio.setEditable(false);
					cbEntrega.setEnabled(false);
					btnEditarProductos.setEnabled(false);
				}
				
			}
		});
		
		for (Reserva i : reservas) {
			if (cbReservas.getSelectedItem() != null) {
				if (cbReservas.getSelectedItem().equals(i.toString())) {
					reserva = i;
					tfNombre.setText(i.getCliente());
					tfMonto.setText(Double.toString(i.getMonto()));
					tfDescripcion.setText(i.getDescripcion());
					if (i.getEntregaDomicilio()){
						cbEntrega.setSelected(true);
					}
					else {
						cbEntrega.setSelected(false);
					}
					tfDomicilio.setText(i.getDireccion());
					tfFecha.setText(i.getFecha().toString());
					ArrayList<ProdVenta> productosreserva = i.getProductos();
					for (ProdVenta j : productosreserva ) {
						cbProductos.addItem(atenea.getCatalogo().getProducto(j.getId_producto()) + " X " + j.getCantidad());
					}
				}
			}
		}
		
	}
}
