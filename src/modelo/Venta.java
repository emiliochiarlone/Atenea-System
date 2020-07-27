package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Venta implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4849454323778765413L;
	private int id;
	private double monto;
	private String descripcion;
	private Date fecha;
	private String cliente;
	private ArrayList<ProdVenta> productos;
	private String direccion;
	private boolean entregaDomicilio;
	private double ganancia;
	
	
	public Venta() {
		this.productos = new ArrayList<ProdVenta>();
	}


	public boolean getEntregaDomicilio() {
		return entregaDomicilio;
	}


	public void setEntregaDomicilio(boolean entregaDomicilio) {
		this.entregaDomicilio = entregaDomicilio;
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getcliente() {
		return cliente;
	}


	public void setcliente(String cliente) {
		this.cliente = cliente;
	}


	public double getMonto() {
		return monto;
	}


	public void setMonto(double monto) {
		this.monto = monto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public ArrayList<ProdVenta> getProductos() {
		return productos;
	}


	public void setProductos(ArrayList<ProdVenta> productos) {
		this.productos = productos;
	}



	public Mensaje agregarProducto(ProdVenta prod) {
		for (ProdVenta i: this.productos) {
			if (i.getId_producto()==prod.getId_producto()) {
				i.Agregar(prod.getCantidad());
				return Mensaje.listo;
			}
		}
		this.productos.add(prod);
		return Mensaje.listo;
	}
	
	public Mensaje quitarProducto(int id) {
		for (ProdVenta i: this.productos) {
			if (i.getId_producto() == id) {
				this.productos.remove(i);
				return Mensaje.listo;
			}
		}
		return Mensaje.noexiste;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	
	public String toString() {
		return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900)+ " " + cliente + "  $" + Double.toString(monto);
	}


	public double getGanancia() {
		return ganancia;
	}


	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	
	public String dateToString() {
		return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900);
	}
}
