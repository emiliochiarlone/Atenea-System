package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ListaReservas implements Serializable, iAlmacenable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 84867014601078182L;
	private int id_generator;
	private ArrayList<Reserva> reservas;
	
	public ListaReservas() {
		this.reservas = new ArrayList<Reserva>();
		this.id_generator = 1;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(ArrayList<Reserva> reservas) throws FileNotFoundException, IOException {
		this.reservas = reservas;
		this.almacenarObjeto("listaReservas.txt");
	}
	public int getId_generator() {
		return id_generator;
	}
	public void setId_generator(int id_generator) throws FileNotFoundException, IOException {
		this.id_generator = id_generator;
	}
	
	public Reserva getReserva(int id) {
		ArrayList<Reserva> reservas = this.reservas;
		for (Reserva i: reservas) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}
	
	public Mensaje eliminarReserva(int id) throws FileNotFoundException, IOException {
		if (id < 1) {
			return Mensaje.error;
		}
		Reserva reserva = getReserva(id);
		this.reservas.remove(reserva);
		if (reserva != null) {
			this.almacenarObjeto("listaReservas.txt");
			return Mensaje.listo;
		}
		return Mensaje.noexiste;
	}
	
	public void AgregarReserva (Reserva reserva) throws FileNotFoundException, IOException {
		reserva.setId(this.id_generator);
		this.id_generator++;
		this.reservas.add(reserva);
		this.almacenarObjeto("listaReservas.txt");
		
	}
	
	public Mensaje marcarVendida (int id, Atenea atenea) throws FileNotFoundException, IOException {
		if ((id < 1) || (atenea.equals(null))) {
			return Mensaje.error;
		}
		Venta venta = new Venta();
		Reserva reserva = getReserva(id);
		venta.setcliente(reserva.getCliente());
		venta.setDescripcion(reserva.getDescripcion());
		venta.setDireccion(reserva.getDireccion());
		venta.setEntregaDomicilio(reserva.getEntregaDomicilio());
		venta.setFecha(reserva.getFecha());
		venta.setMonto(reserva.getMonto());
		venta.setProductos(reserva.getProductos());
		venta.setGanancia(atenea.getHistorialVentas().calcularGanancia(venta, atenea.getCatalogo()));
		atenea.getHistorialVentas().AgregarVenta(venta);
		eliminarReserva(id);
		return Mensaje.listo;
	}
	
	public Reserva getReserva(String reserva) {
		ArrayList<Reserva> reservas = this.reservas;
		for (Reserva i : reservas) {
			if (i.toString().equals(reserva)) {
				return i;
			}
		}
		return null;
		
	}
	
//	public Mensaje editarProductosReserva(int id, ArrayList<ProdVenta> productos) {
//		
//	}
}
