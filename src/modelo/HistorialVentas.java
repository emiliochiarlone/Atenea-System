package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class HistorialVentas implements Serializable, iAlmacenable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7042440676245506428L;
	
	private ArrayList<Venta> ventas = new ArrayList<Venta>();

	private int id_generator;
	

	public HistorialVentas() {
		this.ventas = new ArrayList<Venta>();
		this.id_generator = 1;
	}


	public ArrayList<Venta> getVentas() {
		return ventas;
	}
	public void setVentas(ArrayList<Venta> ventas) throws FileNotFoundException, IOException {
		this.ventas = ventas;
		this.almacenarObjeto("historialVentas.txt");
	}
	
	public void AgregarVenta (Venta venta) throws FileNotFoundException, IOException {
		venta.setId(this.id_generator);
		this.id_generator++;
		this.ventas.add(venta);
		this.almacenarObjeto("historialVentas.txt");
		
	}
	
	public int getCantidadVentas() {
		return this.ventas.size();
	}
	
	public int getId_generator() {
		return id_generator;
	}

	public void setId_generator(int id_generator) throws FileNotFoundException, IOException {
		this.id_generator = id_generator;
		this.almacenarObjeto("historialVentas.txt");
	}
	
	public double getDineroTotal() {
		ArrayList<Venta> vtas = this.ventas;
		double total = 0;
		for (Venta i : vtas) {
			total = total+i.getMonto();
		}
		return total;
	}
	
	public double calcularGanancia (Venta venta, Catalogo catalogo) {
		double total = venta.getMonto();
		ArrayList<ProdVenta> prodsventa = venta.getProductos();
		for (ProdVenta i: prodsventa) {
			total = total - (catalogo.getProducto(i.getId_producto()).getPrecio_costo()*i.getCantidad());
		}
		return total;
	}

	public double calcularGananciaTotal (Catalogo catalogo){
		ArrayList<Venta> ventas = this.getVentas();
		double total = 0;
		for (Venta i: ventas) {
			total = total + i.getGanancia();
		}
		return total;
	}
	
	public double calcularGananciaEntreFechas (Date fechaDesde, Date fechaHasta) {
		double total = 0;
		for (Venta i: this.ventas) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)){
				total = total + i.getGanancia();
			}
		}
		return total;
	}
	
	public int cantidadVentasEntreFechas (Date fechaDesde, Date fechaHasta) {
		int total = 0;
		for (Venta i: this.ventas) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)){
				total = total + 1;
			}
		}
		return total;
	}
	
	public double getDineroTotalEntreFechas(Date fechaDesde, Date fechaHasta) {
		double total = 0;
		for (Venta i: this.ventas) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)){
				total = total + i.getMonto();
			}
		}
		return total;
	}
}
