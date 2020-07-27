package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Reserva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5439614707802172304L;
	private int id;
	private double monto;
	private String descripcion;
	private Date fecha;
	private String cliente;
	private ArrayList<ProdVenta> productos;
	private String direccion;
	private boolean entregaDomicilio;
	
	public Reserva() {
		this.productos = new ArrayList<ProdVenta>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public boolean getEntregaDomicilio() {
		return entregaDomicilio;
	}
	public void setEntregaDomicilio(boolean entregaDomicilio) {
		this.entregaDomicilio = entregaDomicilio;
	}
	
	@Override
	public String toString() {
		 return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900)+ " " + cliente + "  $" + Double.toString(monto);
	}
	
	public String dateToString() {
		return  Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900);
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
}
