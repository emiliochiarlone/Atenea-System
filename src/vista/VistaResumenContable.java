package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import modelo.Atenea;

public class VistaResumenContable {

	private JFrame frmResumenContable;
	private JTextField tfCantidadVentas;
	private JTextField tfDineroTotal;
	private JTextField tfGanancia;


	/**
	 * Create the application.
	 */
	public VistaResumenContable(Atenea atenea) {
		initialize(atenea);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Atenea atenea) {
		frmResumenContable = new JFrame();
		frmResumenContable.setVisible(true);
		frmResumenContable.setTitle("Resumen contable");
		frmResumenContable.setBounds(100, 100, 417, 186);
		frmResumenContable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmResumenContable.getContentPane().setLayout(null);
		
		JLabel lblResumenContable = new JLabel("Resumen contable");
		lblResumenContable.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 22));
		lblResumenContable.setBounds(20, 11, 253, 30);
		frmResumenContable.getContentPane().add(lblResumenContable);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 360, 2);
		frmResumenContable.getContentPane().add(separator);
		
		tfCantidadVentas = new JTextField();
		tfCantidadVentas.setEditable(false);
		tfCantidadVentas.setBounds(267, 62, 86, 20);
		frmResumenContable.getContentPane().add(tfCantidadVentas);
		tfCantidadVentas.setColumns(10);
		
		JLabel lblCantidadDeVentas = new JLabel("Cantidad de ventas realizadas:");
		lblCantidadDeVentas.setBounds(20, 65, 219, 14);
		frmResumenContable.getContentPane().add(lblCantidadDeVentas);
		
		JLabel lblDineroTotalAcumulado = new JLabel("Dinero total acumulado en ventas ($):");
		lblDineroTotalAcumulado.setBounds(20, 92, 219, 14);
		frmResumenContable.getContentPane().add(lblDineroTotalAcumulado);
		
		tfDineroTotal = new JTextField();
		tfDineroTotal.setEditable(false);
		tfDineroTotal.setBounds(267, 89, 86, 20);
		frmResumenContable.getContentPane().add(tfDineroTotal);
		tfDineroTotal.setColumns(10);
		
		JLabel lblGananciaBruta = new JLabel("Ganancia bruta total ($):");
		lblGananciaBruta.setBounds(20, 120, 219, 14);
		frmResumenContable.getContentPane().add(lblGananciaBruta);
		
		tfGanancia = new JTextField();
		tfGanancia.setEditable(false);
		tfGanancia.setColumns(10);
		tfGanancia.setBounds(267, 117, 86, 20);
		frmResumenContable.getContentPane().add(tfGanancia);
		
		tfCantidadVentas.setText(Integer.toString(atenea.getHistorialVentas().getCantidadVentas()));
		tfDineroTotal.setText(Double.toString(atenea.getHistorialVentas().getDineroTotal()));
		tfGanancia.setText(Double.toString(atenea.getHistorialVentas().calcularGananciaTotal(atenea.getCatalogo())));
	}
}
