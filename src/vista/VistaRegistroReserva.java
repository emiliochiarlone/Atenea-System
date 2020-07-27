package vista;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Atenea;
import modelo.Mensaje;
import modelo.ProdVenta;
import modelo.Producto;
import modelo.Reserva;
import modelo.Venta;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class VistaRegistroReserva extends JFrame{

	private JFrame frame;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tMonto;
	private JTextField tDomicilio;
	private JTextField tDescripcion;
	private JSpinner sFecha;

	
	public VistaRegistroReserva(Atenea atenea, JLabel label, JComboBox combobox) {
		initialize(atenea, label, combobox);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea, JLabel label, JComboBox combobox) {
		setTitle("Registrar nueva reserva");
		
		Reserva reserva = new Reserva();
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		JLabel lblRegistroDeNueva = new JLabel("Registro de nueva reserva");
		lblRegistroDeNueva.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblRegistroDeNueva.setBounds(10, 11, 176, 14);
		contentPane.add(lblRegistroDeNueva);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		lblNombreDelCliente.setBounds(20, 36, 123, 14);
		contentPane.add(lblNombreDelCliente);
		
		tNombre = new JTextField();
		tNombre.setBounds(146, 33, 96, 20);
		contentPane.add(tNombre);
		tNombre.setColumns(10);
		
		JComboBox cbProductos = new JComboBox();
		cbProductos.setMaximumRowCount(16);
		cbProductos.setBounds(323, 164, 242, 22);
		contentPane.add(cbProductos);
		
		tMonto = new JTextField();
		tMonto.setColumns(10);
		tMonto.setBounds(146, 64, 96, 20);
		contentPane.add(tMonto);
		
		tDomicilio = new JTextField();
		tDomicilio.setEnabled(false);
		tDomicilio.setColumns(10);
		tDomicilio.setBounds(146, 123, 96, 20);
		contentPane.add(tDomicilio);
		
		sFecha = new JSpinner();
		sFecha.setModel(new SpinnerDateModel(new Date(1594004400000L), null, null, Calendar.DAY_OF_YEAR));
		sFecha.setBounds(146, 154, 96, 20);
		contentPane.add(sFecha);
		sFecha.setValue(new Date());
		
		tDescripcion = new JTextField();
		tDescripcion.setColumns(10);
		tDescripcion.setBounds(146, 189, 123, 40);
		contentPane.add(tDescripcion);
		
		JLabel lblMonto = new JLabel("Monto ($):");
		lblMonto.setBounds(20, 64, 101, 14);
		contentPane.add(lblMonto);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(145, 91, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblPropina = new JLabel("Env\u00EDo a domicilio:");
		lblPropina.setBounds(20, 95, 123, 14);
		contentPane.add(lblPropina);
		
		JLabel lblDomicilioDelCliente = new JLabel("Domicilio del cliente:");
		lblDomicilioDelCliente.setBounds(20, 126, 123, 14);
		contentPane.add(lblDomicilioDelCliente);
		
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(20, 189, 76, 14);
		contentPane.add(lblDescripcin);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(386, 74, 46, 20);
		contentPane.add(spinner);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(20, 154, 123, 14);
		contentPane.add(lblFecha);
		
		JLabel lblCantidadProducto = new JLabel("Cantidad");
		lblCantidadProducto.setBounds(453, 139, 76, 14);
		contentPane.add(lblCantidadProducto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 240, 193, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(333, 240, 196, 14);
		contentPane.add(separator_1);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(312, 77, 101, 14);
		contentPane.add(lblCantidad);

		JComboBox cbCatalogo = new JComboBox();
		cbCatalogo.setMaximumRowCount(16);
		cbCatalogo.setBounds(386, 32, 180, 22);
		
		ArrayList<Producto> catalogo = atenea.getCatalogo().getProductos();
		ArrayList<String> catalogonombres = new ArrayList<String>();
		
		
		for (Producto i: catalogo) {
			catalogonombres.add(i.getNombre()); //+ " x " + Integer.toString(bevande.getStock().getCantidadProducto(i.getId())));
		}
		Collections.sort(catalogonombres);
		for (String i: catalogonombres) {
			cbCatalogo.addItem(i);
		}
		contentPane.add(cbCatalogo);
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdVenta prod = new ProdVenta(atenea.getCatalogo().getIdProducto((String) cbCatalogo.getSelectedItem()),(int)spinner.getValue());
				reserva.agregarProducto(prod);
				cbProductos.addItem((String)cbCatalogo.getSelectedItem() + "  X  " + spinner.getValue());
				System.out.println("");
			}
		});
		btnAgregarProducto.setBounds(322, 105, 181, 23);
		contentPane.add(btnAgregarProducto);
		
		JButton bEliminarProductos = new JButton("Eliminar productos");
		bEliminarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreProd = (String) cbProductos.getSelectedItem();
				reserva.setProductos(new ArrayList<ProdVenta>());
				cbProductos.removeAllItems();
			}
		});
		bEliminarProductos.setBounds(323, 197, 240, 23);
		contentPane.add(bEliminarProductos);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(313, 33, 76, 14);
		contentPane.add(lblProducto);
		
		JButton btnNewButton = new JButton("Registrar reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar la reserva?", "Confirmar reserva", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					String sMonto = tMonto.getText();
					boolean flag = false;
					for (int i = 0; i< sMonto.length(); i++) {
						if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
							JOptionPane.showMessageDialog(null, "Ingrese números en el campo 'Monto' ");
							flag = true;
							i = sMonto.length();
						}
					}
					if (!flag) {
						if (tNombre.getText().equals("") || tMonto.getText().equals("") || reserva.getProductos().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Llene los campos correctamente");
						}
						else{   
							reserva.setFecha((Date) sFecha.getValue());
							reserva.setCliente(tNombre.getText());
							reserva.setMonto(Double.parseDouble(tMonto.getText()));
							reserva.setDescripcion(tDescripcion.getText());
							if (chckbxNewCheckBox.isSelected()) {
								reserva.setEntregaDomicilio(true);
								reserva.setDireccion(tDomicilio.getText());
							}
							else {
								reserva.setEntregaDomicilio(false);
							}
							try {
								if (atenea.NuevaReserva(reserva) == Mensaje.nostock) {
									JOptionPane.showMessageDialog(null, "No dispone del stock suficiente para realizar la venta");
								}
								else {
									JOptionPane.showMessageDialog(null, "Reserva registrada correctamente");
									if (atenea.getStock().getFaltasStock().isEmpty()) {
							    		label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/nofaltastock.jpg")));
							    	}
							    	else {
							    		label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/faltastock.png")));
							    	}
									ArrayList<Reserva> reservas = atenea.getListaReservas().getReservas();
									ArrayList<String> prodnombre = new ArrayList<String>();
									for (Reserva j : reservas) {
										prodnombre.add(j.toString());
									}
									combobox.removeAllItems();
									Collections.sort(prodnombre);
									for (String j: prodnombre) {
										combobox.addItem(j);
									}
									dispose();
								}
							} catch (HeadlessException | IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 11));
		btnNewButton.setBounds(386, 256, 138, 28);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea cancelar la reserva?", "Salir de la reserva", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnCancelar.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 11));
		btnCancelar.setBounds(44, 254, 142, 32);
		contentPane.add(btnCancelar);
		
		JLabel lblProducto_1 = new JLabel("Producto");
		lblProducto_1.setBounds(313, 139, 76, 14);
		contentPane.add(lblProducto_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(399, 139, 76, 14);
		contentPane.add(lblX);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(289, 35, 2, 194);
		contentPane.add(separator_2);
		
		
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					tDomicilio.setEnabled(true);
				}
				else {
					tDomicilio.setEnabled(false);
				}
			}
		});
		
		
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
