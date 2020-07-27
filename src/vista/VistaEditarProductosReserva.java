package vista;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import modelo.Atenea;
import modelo.Mensaje;
import modelo.ProdVenta;
import modelo.Producto;
import modelo.Reserva;

public class VistaEditarProductosReserva extends JFrame {



	public VistaEditarProductosReserva(Atenea atenea, JComboBox cbReservas, Reserva reserva, JLabel labelFaltas, JComboBox<String> cbProductos) {
		super();
		setTitle("Editar productos de la reserva");
		getContentPane().setLayout(null);
		initialize(atenea, cbReservas, reserva, labelFaltas);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea, JComboBox cbReservas, Reserva reserva, JLabel label) {
		setBounds(100, 100, 328, 465);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		JComboBox cbProductos = new JComboBox();
		cbProductos.setBounds(97, 11, 181, 22);
		getContentPane().add(cbProductos);
		
		ArrayList<ProdVenta> productos = new ArrayList<ProdVenta>();
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(110, 60, 46, 20);
		getContentPane().add(spinner);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(22, 15, 65, 14);
		getContentPane().add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(22, 63, 65, 14);
		getContentPane().add(lblCantidad);
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setBounds(56, 110, 181, 23);
		getContentPane().add(btnAgregarProducto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 161, 230, 2);
		getContentPane().add(separator);
		
		JComboBox cbProdCompra = new JComboBox();
		cbProdCompra.setMaximumRowCount(16);
		cbProdCompra.setBounds(34, 229, 222, 22);
		getContentPane().add(cbProdCompra);
		
		JLabel lblProductosDeLa = new JLabel("Productos de la reserva:");
		lblProductosDeLa.setBounds(81, 174, 166, 14);
		getContentPane().add(lblProductosDeLa);
		
		JLabel lblProducto_1 = new JLabel("Producto");
		lblProducto_1.setBounds(42, 207, 76, 14);
		getContentPane().add(lblProducto_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(128, 207, 46, 14);
		getContentPane().add(lblX);
		
		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setBounds(177, 207, 46, 14);
		getContentPane().add(lblCantidad_1);
		
		JButton button = new JButton("Eliminar productos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productos.clear();
				cbProdCompra.removeAllItems();		
			}
		});
		button.setBounds(22, 283, 256, 23);
		getContentPane().add(button);
		
		JButton btnRegistrarCompra = new JButton("Guardar cambios");
		btnRegistrarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea editar la reserva?", "Confirmar edición", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					boolean flag = true;
					if (productos.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay productos seleccionados");
					}
					else {
						try {
							ArrayList<ProdVenta> prodventas = reserva.getProductos();
							for (ProdVenta i: prodventas) {
								try { //Se suman los productos al stock para poder calcular si se puede realizar la reserva o no
									atenea.getStock().NuevaCompra(i.getId_producto(), i.getCantidad(), atenea.getCatalogo().getProducto(i.getId_producto()).getCant_min());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if (atenea.getStock().VentaEsPosible(productos)) { // Si los productos seleccionados se encuentran en stock
								reserva.getProductos().clear();   
								for (ProdVenta i: productos) {
									reserva.agregarProducto(i); //Agrego productos a la reserva
								}
								cbReservas.removeAllItems();  // Se limpian los combobox
								cbProductos.removeAllItems();  
								ArrayList<ProdVenta> prodcompra = reserva.getProductos();
								for (ProdVenta i : prodcompra) {
									atenea.getStock().getProdVenta(i.getId_producto()).Descontar(i.getCantidad()); // Vuelvo a descontar productos del stock
								}
								atenea.getStock().calcularFaltas(atenea.getCatalogo());  // Actualiza las faltas de stock
								if (atenea.getStock().getFaltasStock().isEmpty()) {
									label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/nofaltastock.jpg"))); // Cambia el icono del menu pricipal
								}
								else {
									label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/faltastock.png")));
								}
								ArrayList<Reserva> reservas = atenea.getListaReservas().getReservas();
								ArrayList<String> stringreservas = new ArrayList<String>();
								for (Reserva i : reservas) {
									stringreservas.add(i.toString());
								}
								Collections.sort(stringreservas);
								for (String i : stringreservas) {
									cbReservas.addItem(i);  //Agrego las reservas al CBox
								}
								for (ProdVenta i: prodcompra) {
									cbProductos.addItem(atenea.getCatalogo().getProducto(i.getId_producto()).getNombre() + " X " + i.getCantidad());
								} //Agrego los productos al otro CBox
								atenea.getListaReservas().almacenarObjeto("listaReservas.txt");
								atenea.getStock().almacenarObjeto("stock.txt");  // Almaceno los objetos
								JOptionPane.showMessageDialog(null, "Reserva editada con éxito");
								dispose();
								
							}
							else {
								JOptionPane.showMessageDialog(null, "No dispone de stock para reservar estos productos");
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnRegistrarCompra.setBounds(44, 359, 212, 49);
		getContentPane().add(btnRegistrarCompra);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 331, 230, 2);
		getContentPane().add(separator_1);
		
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
				productos.add(prod);
				cbProdCompra.addItem(atenea.getCatalogo().getProducto(prod.getId_producto()).getNombre() + " X " + Integer.toString(prod.getCantidad()));
			}
		});
	}

}
