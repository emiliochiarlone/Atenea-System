package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import modelo.Atenea;
import modelo.Mensaje;
import modelo.ProdVenta;
import modelo.Producto;
import modelo.Venta;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class VistaRegistroVenta extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tMonto;
	private JTextField tDomicilio;
	private JTextField tDescripcion;
	private JSpinner sFecha;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public VistaRegistroVenta(Atenea atenea, JLabel label) {
		setTitle("Registrar nueva venta");
		
		Venta venta = new Venta();
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		JLabel lblRegistroDeNueva = new JLabel("Registro de nueva venta");
		lblRegistroDeNueva.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		lblRegistroDeNueva.setBounds(10, 11, 133, 14);
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
				venta.agregarProducto(prod);
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
				venta.setProductos(new ArrayList<ProdVenta>());
				cbProductos.removeAllItems();
			}
		});
		bEliminarProductos.setBounds(323, 197, 240, 23);
		contentPane.add(bEliminarProductos);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(313, 33, 76, 14);
		contentPane.add(lblProducto);
		
		JButton btnNewButton = new JButton("Registrar venta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar la venta?", "Confirmar venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					String sMonto = tMonto.getText();
					boolean flag = false;
					for (int i = 0; i< sMonto.length(); i++) {
						if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
							JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
							flag = true;
							i = sMonto.length();
						}
					}
					if (!flag) {
						if (tNombre.getText().equals("") || tMonto.getText().equals("") || venta.getProductos().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Llene los campos de la venta correctamente");
						}
						else{   
							venta.setFecha((Date) sFecha.getValue());
							venta.setcliente(tNombre.getText());
							venta.setMonto(Double.parseDouble(tMonto.getText()));
							venta.setDescripcion(tDescripcion.getText());
							venta.setGanancia(atenea.getHistorialVentas().calcularGanancia(venta, atenea.getCatalogo()));
							if (chckbxNewCheckBox.isSelected()) {
								venta.setEntregaDomicilio(true);
								venta.setDireccion(tDomicilio.getText());
							}
							else {
								venta.setEntregaDomicilio(false);
							}
							try {
								if (atenea.NuevaVenta(venta) == Mensaje.nostock) {
									JOptionPane.showMessageDialog(null, "No dispone del stock suficiente para realizar la venta");
								}
								else {
									JOptionPane.showMessageDialog(null, "Venta registrada correctamente");
									if (atenea.getStock().getFaltasStock().isEmpty()) {
							    		label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/nofaltastock.png")));
							    	}
							    	else {
							    		label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/faltastock.png")));
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
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar la venta?", "Salir de la venta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
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
