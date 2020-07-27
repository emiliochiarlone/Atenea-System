package vista;

import java.awt.EventQueue;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

import modelo.Atenea;

import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.SwingConstants;

public class VistaMenuPrincipal {

	private JFrame frame;

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public VistaMenuPrincipal(Atenea atenea) throws FileNotFoundException, IOException {
		initialize(atenea);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void initialize(Atenea atenea) throws FileNotFoundException, IOException {
		frame = new JFrame();
		try {
            FondoSwing fondo = new FondoSwing(ImageIO.read(new File("./src/imagenes/background.jpg")));
            JPanel panel = (JPanel) frame.getContentPane();
            panel.setBorder(fondo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, this, ex.getMessage(), 0);
        }
//		frame.getContentPane().setBackground(new Color(85, 107, 47));
//		frame.getContentPane().setForeground(Color.WHITE);
//		frame.setForeground(Color.GRAY);
		frame.setBounds(100, 100, 679, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Menú principal");
		
		JButton btnCatlogo = new JButton("Cat\u00E1logo");
		btnCatlogo.setBounds(116, 336, 186, 23);
		frame.getContentPane().add(btnCatlogo);
		
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/catalogo.png")));
		lblNewLabel.setBounds(67, 330, 39, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/menu.png")));
		lblNewLabel_1.setBounds(32, 229, 20, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblMen = new JLabel("Men\u00FA");
		lblMen.setFont(new Font("MS UI Gothic", Font.BOLD, 17));
		lblMen.setForeground(Color.BLACK);
		lblMen.setBounds(62, 229, 48, 14);
		frame.getContentPane().add(lblMen);
		
		JButton btnHistorialDeVentas = new JButton("Historial de ventas");
		btnHistorialDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaHistorialVentas(atenea); 
			         }
			      });
			}
		});
		btnHistorialDeVentas.setBounds(116, 430, 186, 23);
		frame.getContentPane().add(btnHistorialDeVentas);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/historialventas.jpg")));
		lblNewLabel_2.setBounds(67, 421, 43, 45);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/nuevaVenta.png")));
		lblNewLabel_3.setBounds(67, 285, 39, 34);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel label = new JLabel(""); 
		
		JButton btnVerStockDisponible = new JButton("Gesti\u00F3n de stock");
		btnVerStockDisponible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaStockDisponible(atenea, label); 
			         }
			      });
			}
		});
		btnVerStockDisponible.setBounds(116, 477, 186, 23);
		frame.getContentPane().add(btnVerStockDisponible);
		atenea.getStock().calcularFaltas(atenea.getCatalogo());
		if (atenea.getStock().faltaStock()) {
			label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/faltastock.png")));
		}
		else {
			label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/nofaltastock.jpg")));
		}
		label.setBounds(67, 507, 43, 50);
		frame.getContentPane().add(label);
		
		JButton btnVerFaltasDe = new JButton("Faltas de stock");
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
		btnVerFaltasDe.setBounds(116, 524, 186, 23);
		frame.getContentPane().add(btnVerFaltasDe);
		
		JButton btnRegistrarNuevaVenta = new JButton("Registrar nueva venta");
		btnRegistrarNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaRegistroVenta(atenea, label); 
			         }
			      });
			}
		});
		btnRegistrarNuevaVenta.setBounds(116, 289, 186, 23);
		frame.getContentPane().add(btnRegistrarNuevaVenta);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(617, 166, 20, 14);
		frame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/logo2.jpg")));
		lblNewLabel_4.setBounds(353, 289, 249, 244);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/stock.jpg")));
		lblNewLabel_5.setBounds(67, 466, 48, 34);
		frame.getContentPane().add(lblNewLabel_5);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(27, 308, 8, 213);
		frame.getContentPane().add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(67, 568, 249, 14);
		frame.getContentPane().add(separator_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/logo.jpg")));
		lblNewLabel_6.setBounds(32, 18, 595, 162);
		frame.getContentPane().add(lblNewLabel_6);
		
		JButton bReservas = new JButton("Reservas");
		bReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaReservas(atenea, label); 
			         }
			      });
			}
		});
		bReservas.setBounds(116, 383, 186, 23);
		frame.getContentPane().add(bReservas);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/reservas.jpg")));
		label_1.setBounds(67, 375, 35, 34);
		frame.getContentPane().add(label_1);
		
		btnCatlogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaCatalogo(atenea); 
			         }
			      });
			}
		});
	}
}
